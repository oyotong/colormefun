package shop.common.util;

import com.colormefun.entity.MfOrder;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by tong-note on 2014/8/26.
 */
public class ReportUtils {
    private static String templatePath = "/WEB-INF/template";
    private static String temporaryPath = "/WEB-INF/temp";
    public static void exportExcel(Map beans, String templateName, String outputFileName,HttpServletRequest request, HttpServletResponse response){
        String basePath = request.getSession().getServletContext().getRealPath("/");
        String templateFileName = basePath + "/" + templatePath + "/" + templateName;
        String destFileName = basePath + "/" + temporaryPath + "/" + UUID.randomUUID();
        File tempFile = new File(destFileName);

        if(!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdirs();
        }

        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            // Create Excel
            XLSTransformer transformer = new XLSTransformer();
            FileInputStream in = new FileInputStream(templateFileName);
//            transformer.transformXLS(templateFileName, beans, destFileName);
            OutputStream out=new BufferedOutputStream(new FileOutputStream(destFileName));
            XSSFWorkbook workbook = (XSSFWorkbook) transformer.transformXLS(in,
                    beans);
            resetCellFormula(workbook);
            workbook.write(out);

            // Send to response
            if (outputFileName == null) {
                outputFileName = templateName;
            }
            response.reset();
            response.setContentType("application/vnd.ms-excel"); //改成输出excel文件
            response.setHeader("Content-disposition", "attachment; success=true; filename = " + outputFileName);

            bin = new BufferedInputStream(new FileInputStream(tempFile));
            bout = new BufferedOutputStream(response.getOutputStream());

            int i = 0;
            byte[] buffer = new byte[1024];
            while ((i = bin.read(buffer)) != -1) {
                bout.write(buffer, 0, i);
            }
        }catch (Exception e){
            e.printStackTrace();
            sendErrorMessage(response, e.getMessage());
        }finally {
            if(null != bin) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != bout) {
                try {
                    bout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(tempFile.exists()) {
            tempFile.delete();
        }
    }

    public static void sendErrorMessage(HttpServletResponse response, String message) {
        try {
            response.reset();
            response.setContentType("text/html"); //改成输出excel文件
            PrintWriter pw = response.getWriter();

            pw.println("<script type=\"text/javascript\">\n" +
                    "alert(\""+message+"\");\n" +
                    "window.close();\n" +
                    "</script>");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 重新设置单元格计算公式
     *
     * */
    public static void resetCellFormula(XSSFWorkbook wb) {
        XSSFFormulaEvaluator e = new XSSFFormulaEvaluator(wb);
        int sheetNum = wb.getNumberOfSheets();
        for (int i = 0; i < sheetNum; i++) {
            XSSFSheet sheet = wb.getSheetAt(i);
            int rows = sheet.getLastRowNum() + 1;
            for (int j = 0; j < rows; j++) {
                XSSFRow row = sheet.getRow(j);
                if (row == null)
                    continue;
                int cols = row.getLastCellNum();
                for (int k = 0; k < cols; k++) {
                    XSSFCell cell = row.getCell(k);
                    if(cell!=null)
                        System.out.println("cell["+j+","+k+"]=:"+cell.getCellType());
                    if (cell == null)
                        continue;
                    if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
                        cell.setCellFormula(cell.getCellFormula());
                        System.out.println("----公式："+cell.getCellFormula());
                        cell=e.evaluateInCell(cell);
                        System.out.println("-----------"+cell.getNumericCellValue());
                    }
                }
            }
        }
    }
}

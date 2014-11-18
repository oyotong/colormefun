package shop.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * FreeMarker tool
 */
public class FreeMarkerUtil {

    private static Configuration cfg = new Configuration();
    private static final Logger logger = Logger.getLogger(FreeMarkerUtil.class);
    private static final String DEFAULT_ENCODING = "UTF-8";
    public static final String FMK_NUMBER_FORMATE = "#";

    public static void init(String template_url) throws Exception, IOException {
        if (template_url == null) {
            throw new Exception("the template url is null");
        }
        // set the template position
        cfg.setDirectoryForTemplateLoading(new File(template_url));
        cfg.setDefaultEncoding(DEFAULT_ENCODING);
//        cfg.setDirectoryForTemplateLoading(new File(FreeMarkerUtil.class.getResource(template_url).getFile()));
    }

    public static void makeFile(Map<String, Object> root, String savePath,
                                String fileName, String templateName) throws Exception {
        Template template = cfg.getTemplate(templateName);
        String realFileName = savePath + fileName;
        String realSavePath = savePath;
        File newsDir = new File(realSavePath);
        if (!newsDir.exists()) {
            newsDir.mkdirs();
        }
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(
                    realFileName), DEFAULT_ENCODING);

            template.process(root, out);
            out.flush();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

    }

    public static String getReplacedString(Map<String, Object> argsMap, String templateName) throws Exception {
        Template temp = cfg.getTemplate(templateName);

        temp.setClassicCompatible(true);
        StringWriter w = new StringWriter();
        temp.process(argsMap, w);
        w.flush();

        return w.getBuffer().toString();

    }

    public static String renderTempString(Map<String, Object> argsMap, String tempBody) {
        try {
            Configuration config = new Configuration();
            config.setTemplateLoader(new StringTemplateLoader(tempBody));
            config.setDefaultEncoding(DEFAULT_ENCODING);
            config.setNumberFormat(FMK_NUMBER_FORMATE);

            Template template = config.getTemplate("", DEFAULT_ENCODING);
            template.setClassicCompatible(true);

            StringWriter writer = new StringWriter();
            template.process(argsMap, writer);
            writer.flush();
            tempBody = writer.getBuffer().toString();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return tempBody;

    }


}
package shop.common.util.file;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MyHttpServletRequest extends HttpServletRequestWrapper implements
		HttpServletRequest {

	private Hashtable<String, String[]> parameterMap = new Hashtable<String, String[]>();
	private Hashtable<String, FileItem> fileItemMap = new Hashtable<String, FileItem>();
	private String charSet;
	private String tempPath;
	private long sizeMax;

	public MyHttpServletRequest(HttpServletRequest request, String charSet,
			String tempPath,long sizeMax) {

		super(request);

		this.charSet = charSet;
		this.tempPath = tempPath;
		this.sizeMax = sizeMax;

		parameterMap.putAll(request.getParameterMap());

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		// 用于接收文件的内存大小（字节）
		factory.setSizeThreshold(1024 * 10);
		// 用于接收文件的临时目录
		factory.setRepository(new File(this.tempPath));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		// 接收文件的最大字节数
		upload.setSizeMax(this.sizeMax);
		// 接收信息的编码格式（与上传页面的编码格式一致）
		upload.setHeaderEncoding(this.charSet);

		// Parse the request
		try {
			List items = upload.parseRequest(request);

			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				
				if (item.isFormField()) {
					processFormField(item);
				} else {
					processUploadedFile(item);
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}

	private void processUploadedFile(FileItem item) {

		String name = item.getFieldName();
		String value = item.getName();

		String[] temp = new String[] { value };

		parameterMap.put(name, temp);

		fileItemMap.put(name, item);
	}

	private void processFormField(FileItem item) {
		try {
			// getString("UTF-8")---接收值并指定编码（与上传页面的编码格式一致）

			String name = item.getFieldName();
			String value = item.getString(this.charSet);

			String[] temp = parameterMap.get(name);

			if (temp == null) {
				temp = new String[1];
			} else {
				String[] temp2 = new String[temp.length + 1];
				System.arraycopy(temp, 0, temp2, 0, temp.length);
				temp = temp2;
			}

			temp[temp.length - 1] = value;

			parameterMap.put(name, temp);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public FileItem getFileItem(String name) {
		return fileItemMap.get(name);
	}

	@Override
	public String getParameter(String name) {
		String[] values = parameterMap.get(name);
		return (values == null || values.length == 0) ? null : values[0];
	}

	@Override
	public Map getParameterMap() {
		return parameterMap;
	}

	@Override
	public Enumeration getParameterNames() {
		return parameterMap.elements();
	}

	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}
	
}

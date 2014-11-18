package shop.common.util.file;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet Filter implementation class FileUploadFilter
 */
public class FileUploadFilter implements Filter {

	private String charSet;
	private String tempPath;
	private long sizeMax;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload
				.isMultipartContent((HttpServletRequest) request);
		if (isMultipart) {
			request = new MyHttpServletRequest((HttpServletRequest) request,
					this.charSet, this.tempPath, this.sizeMax);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.charSet = fConfig.getInitParameter("CharSet");
		this.tempPath = fConfig.getInitParameter("TempPath");

		String temp = fConfig.getInitParameter("SizeMax");
		this.sizeMax = (temp == null) ? 1024 * 1024 * 10 : Long.parseLong(temp);

		if (this.charSet == null) {
			this.charSet = "UTF-8";
		}
		if (this.tempPath == null) {
			this.tempPath = "C:/TEMP";
		}

	}

}

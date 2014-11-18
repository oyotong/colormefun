package shop.common.util.encoding;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.common.context.ApplicationContext;

public class CharSetFilter implements Filter {
	private FilterConfig config;
	private String charSet;

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		String method = request.getMethod();

		if ("GET".equals(method)) {
//			Map<String, String[]> parameters = request.getParameterMap();
//			for (String key : parameters.keySet()) {
//				String[] values = parameters.get(key);
//				for (int i = 0; i < values.length; i++) {
//					values[i] = new String(values[i].getBytes("ISO8859-1"),
//							charSet);
//				}
//			}
		} else {
			request.setCharacterEncoding(charSet);
		}

		response.setCharacterEncoding(charSet);
		
		chain.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {

		this.config = arg0;
		this.charSet = ApplicationContext.getContext().getConfiguration(
				"Encoding.CharSet");
		if (null == this.charSet) {
			this.charSet = config.getInitParameter("CharSet");
		}
		if (null == charSet) {
			charSet = "UTF-8";
		}
	}

}

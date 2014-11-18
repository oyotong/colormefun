package shop.portal.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class URLReWriteFilter implements Filter {

	private static String URL_REGX = "^/([\\w\\+\\-]+)/([0-9a-zA-Z\\-\\+]+)_?(([0-9a-zA-Z_\\-\\+]+?)*)\\.htm$";
	private Pattern urlPattern = Pattern.compile(URL_REGX);// (_([0-9a-zA-Z_]+?)_([0-9a-zA-Z_]+?))

	/**
	 * Default constructor.
	 */
	public URLReWriteFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		String path = request.getServletPath();
		
		if(!path.endsWith(".htm")){
			chain.doFilter(request, resp);
			return;
		}
		
		Matcher matcher = urlPattern.matcher(path);
		if (!matcher.find()) {
			chain.doFilter(request, resp);
			return;
		}

		String url = "/" + matcher.group(1) + "/" + matcher.group(1)
				+ ".jsp?id=" + matcher.group(2);

		if (matcher.groupCount() > 2) {
			StringBuffer param = new StringBuffer(100);
			String temp = matcher.group(3).replaceAll("__", "_%5F");
			temp = temp.startsWith("_")?temp.replaceFirst("_", "%5F"):temp;
			if (temp.trim().length() > 2) {
				String[] tempa = temp.split("_");
				for (int i = 0; i < tempa.length; i = i + 2) {
					String key = tempa[i];
					String value = (tempa.length==i+1)?"":tempa[i + 1];
					param.append("&" + key + "=" + value);
				}
			}
			url += param;
			url = url.replaceAll("%5F", "_");
		}

		request.getRequestDispatcher(url).forward(request, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public static void main(String[] args) {
		Matcher matcher = Pattern.compile(URL_REGX).matcher(
				"/product/ForderBy_sortId_test_100a.htm");
		if (matcher.find()) {
			String type = matcher.group(1);
			String id = matcher.group(2);
			System.out.println("type:" + type);
			System.out.println("id:" + id);
			if (matcher.groupCount() > 2) {
				String param = matcher.group(3);
				System.out.println("param:" + param);
				param = param.replaceAll("__", "_%5F");
				String[] temp = param.split("_");
				for (String string : temp) {
					try {
						System.out.println(URLDecoder.decode(string, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

}

package shop.common.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationFilter implements Filter {

	ApplicationContext context = ApplicationContext.getContext();

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();

		context.setRequest(request);
		context.setResponse(response);
		context.setSession(session);

		if (request.getSession().getServletContext().getAttribute("ROOT") == null) {

			String root = ApplicationContext.getContext().getConfiguration(
					"Servlet.Context.ROOT");

			if (null == root) {

				String url = request.getRequestURL().toString();
				root = url.substring(0, url.indexOf("/", 8));
				if (request.getContextPath().length() > 1) {
					root += request.getContextPath();
				}

			}
			request.getSession().getServletContext().setAttribute("ROOT", root);
		}

		chain.doFilter(request, response);

		context.setRequest(null);
		context.setResponse(null);
		context.setSession(null);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}

package shop.common.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.common.context.ApplicationContext;

public class LoginFilter implements Filter {

	private String sessionUserName;
	private String loginPath;
	private String errorMessageName;
	private String errorMessage;
	private String[] excludePath;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String path = request.getServletPath();
		for (String ex : this.excludePath) {
			if (ex.length() > 3 && path.endsWith(ex)) {
				chain.doFilter(request, response);
				return;
			}
		}

		HttpSession session = request.getSession();

		Object user = session.getAttribute(this.sessionUserName);

		if (null == user) {
			
			session.setAttribute("LoginAfterPath", request.getRequestURL()+"");
			
			request.setAttribute(this.errorMessageName, this.errorMessage);
			request.getRequestDispatcher(this.loginPath).forward(request,
					response);
			return;
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {

		String excludePath = getInitParameter(filterConfig,"ExcludePath");

		this.sessionUserName = getInitParameter(filterConfig, "SessionUser");
		this.loginPath = getInitParameter(filterConfig, "LoginPath");
		this.errorMessageName = getInitParameter(filterConfig,
				"ErrorMessageName");
		this.errorMessage = getInitParameter(filterConfig, "ErrorMessage");
		this.excludePath = excludePath.split(",");
		
		
	}

	public String getInitParameter(FilterConfig filterConfig, String name) {

		String value = filterConfig.getInitParameter(name);
		if (value.startsWith("${") && value.endsWith("}")) {
			name = value.substring(2, value.lastIndexOf('}'));
			value = ApplicationContext.getContext().getConfiguration(name);
		}

		return value;
	}

}

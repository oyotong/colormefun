package shop.common.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import shop.common.util.ParamUtils;
import shop.common.util.StringUtils;
import shop.company.entity.SiteParameterItem;

public class IPFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String rip = request.getRemoteAddr();
		
		List<SiteParameterItem> list = ParamUtils.paramList("IP_BOUND");

		if (null != list)
			for (SiteParameterItem it : list) {
				if (StringUtils.compare(rip, it.getParamItemValue()) > -1
						&& StringUtils.compare(rip, it.getParamItemValue1()) < 0) {
					((HttpServletResponse)response).sendError(500);
					return;
				}
			}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}

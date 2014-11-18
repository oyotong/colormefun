package shop.common.util.page;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import shop.common.context.ApplicationContext;

//@Controller
public class PaginationFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {


		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = (HttpSession) request.getSession();

        if(request.getAttribute("pagination") != null){
            arg2.doFilter(arg0,arg1);
            return;
        }

		String path = request.getServletPath();
		
		Map<String, Pagination> paginationMap = (Map<String, Pagination>) session
				.getAttribute("paginationMap");

		if (null == paginationMap) {
			paginationMap = new Hashtable<String, Pagination>();
			session.setAttribute("paginationMap", paginationMap);
		}

		Pagination pagination = paginationMap.get(path);

		if (null == pagination) {
			pagination = new Pagination();
			paginationMap.put(path, pagination);
		}
		
		pagination.setPath(path);

		pagination.addAllParameter(request.getParameterMap());

		String pageString = pagination.getParameter("_page");
		String rowCount = pagination.getParameter("_rowCount");
		String orderBy[] = pagination.getParameterValues("_orderBy");

		if (null != pageString) {
			try {
				pagination.setPage(Integer.parseInt(pageString));
			} catch (Exception e) {
			}
		}

		if (null != rowCount && rowCount.length() > 0) {
			try {
				pagination.setRowCount(Integer.parseInt(rowCount));
			} catch (Exception e) {
			}

		}

		if (null != orderBy && orderBy.length > 0) {
			pagination.setOrderBys(orderBy);
		}

		ApplicationContext.getContext().setPagination(pagination);
		request.setAttribute("pagination", pagination);
		
		arg2.doFilter(arg0, arg1);

		ApplicationContext.getContext().setPagination(null);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}

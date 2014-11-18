package shop.common.context;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.colormefun.entity.MfUser;
import org.springframework.web.context.support.WebApplicationContextUtils;

import shop.common.exception.ApplicationException;
import shop.common.util.page.Pagination;
import shop.company.entity.AuthUser;

public class ApplicationContext {

	private static ServletContext application;
	private static final Map<String, String> configuration = new HashMap<String, String>();
	private static final Map<String, Object> attributes = new HashMap<String, Object>();
	private static final ThreadLocal<Pagination> pagination = new ThreadLocal<Pagination>();
	private static final ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();
	private static final ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private static final ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

	private static final ApplicationContext context = new ApplicationContext();

	private ApplicationContext() {
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public String getInitParameter(String name) {
		return application.getInitParameter(name);
	}

	public Pagination getPagination() {
		Pagination page = pagination.get();
		if(null == page){
			page = new Pagination();
			pagination.set(page);
		}
		return page;
	}

	public void setPagination(Pagination pagination) {
		ApplicationContext.pagination.set(pagination);
	}

	public HttpSession getSession() {
		return session.get();
	}

	public void setSession(HttpSession session) {
		ApplicationContext.session.set(session);
	}

	public HttpServletRequest getRequest() {
		return request.get();
	}

	public void setRequest(HttpServletRequest request) {
		ApplicationContext.request.set(request);
	}

	public HttpServletResponse getResponse() {
		return response.get();
	}

	public void setResponse(HttpServletResponse response) {
		ApplicationContext.response.set(response);
	}

	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext context) {
		application = context;
	}

	public String getConfiguration(String key) {
		return configuration.get(key);
	}

	public void addConfiguration(String key, String value) {
		configuration.put(key, value);
	}

	public Map<String, String> getConfigurationMap() {
		return configuration;
	}

	public void addAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	public void updateCurrentUser() {

		if (null == getSession())
			return;

	}

	public AuthUser getCurrentAuthUser() {
		if (null == getSession()) {
			return null;
		}
		return (AuthUser) getSession().getAttribute(
				getContext().getConfiguration(shop.Constants.SESSION_AUTH_USER_NAME));
	}

    public MfUser getCurrentMfUser() {
        if (null == getSession()) {
            return null;
        }
        return (MfUser) getSession().getAttribute(
                getContext().getConfiguration(shop.Constants.SESSION_MF_USER_NAME));
    }

}

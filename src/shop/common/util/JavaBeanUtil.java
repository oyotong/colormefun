package shop.common.util;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import shop.common.util.page.Pagination;
import shop.company.service.QSLCourseServiceImpl;

public class JavaBeanUtil {
	private final static ApplicationContext context;
	static {
		context = WebApplicationContextUtils
				.getWebApplicationContext(shop.common.context.ApplicationContext
						.getContext().getApplication());
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static Method findMethodByName(Class type, String name) {
		Method[] methods = type.getMethods();
		for (Method method : methods) {
			if (name.equalsIgnoreCase(method.getName())) {
				return method;
			}
		}
		return null;
	}

	public static Object invokeMethod(String beanName, String methodName) {
		return invokeMethodx(beanName, methodName);
	}

	public static Object invokeMethod1(String beanName, String methodName,
			Object args) {
		return invokeMethodx(beanName, methodName, args);
	}

	public static Object invokeMethod2(String beanName, String methodName,
			Object arg1, Object arg2) {
		return invokeMethodx(beanName, methodName, arg1, arg2);
	}

	public static Object invokeMethod3(String beanName, String methodName,
			Object arg1, Object arg2, Object arg3) {
		return invokeMethodx(beanName, methodName, arg1, arg2, arg3);
	}
	
	public static Object invokeMethod4(String beanName, String methodName,
			Object arg1, Object arg2, Object arg3, Object arg4) {
		return invokeMethodx(beanName, methodName, arg1, arg2, arg3, arg4);
	}

	public static Object invokeMethodx(String beanName, String methodName,
			Object... args) {

//		HttpServletRequest request = shop.common.context.ApplicationContext
//				.getContext().getRequest();
//
//		HttpSession session = request.getSession();
//
//		Map<String, Pagination> paginationMap = (Map<String, Pagination>) session
//				.getAttribute("paginationMap");
//
//		if (null == paginationMap) {
//			paginationMap = new Hashtable<String, Pagination>();
//			session.setAttribute("paginationMap", paginationMap);
//		}
//
//		Pagination pagination = paginationMap.get(beanName + "_" + methodName);
//
//		if (null == pagination) {
//			pagination = new Pagination();
//			paginationMap.put(beanName + "_" + methodName, pagination);
//		}
//
//		String pageString = pagination.getParameter("_page");
//		String rowCount = pagination.getParameter("_rowCount");
//		String orderBy[] = pagination.getParameterValues("_orderBy");
//
//		if (null != pageString) {
//			try {
//				pagination.setPage(Integer.parseInt(pageString));
//			} catch (Exception e) {
//			}
//		}
//
//		if (null != rowCount && rowCount.length() > 0) {
//			try {
//				pagination.setRowCount(Integer.parseInt(rowCount));
//			} catch (Exception e) {
//			}
//
//		}
//
//		if (null != orderBy && orderBy.length > 0) {
//			pagination.setOrderBys(orderBy);
//		}
//
//		shop.common.context.ApplicationContext.getContext().setPagination(
//				pagination);
//
		Object obj = null;
		try {
			Object bean = getBean(beanName + "Impl");//
			Method m = findMethodByName(bean.getClass(), methodName);
			if (null != m)
				obj = m.invoke(bean, args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

//		session.setAttribute("pagination", pagination);
		return obj;
	}

	public static void copy2(Object source, Object target) {
		Class sourceClass = source.getClass();
		Class targetClass = target.getClass();

		Method sMethods[] = sourceClass.getMethods();
		for (Method m : sMethods) {
			if (!m.getName().startsWith("get")
					|| m.getName().equals("getClass")) {
				continue;
			}
			Object value = null;
			try {
				value = m.invoke(source);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (null == value)
				continue;

			Method m2 = findMethodByName(targetClass, "set"
					+ m.getName().substring(3));
			try {
				m2.invoke(target, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

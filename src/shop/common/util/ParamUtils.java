package shop.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.support.WebApplicationContextUtils;

import shop.common.context.ApplicationContext;
import shop.company.entity.SiteParameterItem;
import shop.company.service.SiteParameterItemService;

public class ParamUtils {
	private static SiteParameterItemService itemService;
	static {
		itemService = (SiteParameterItemService) WebApplicationContextUtils
				.getWebApplicationContext(
						ApplicationContext.getContext().getApplication())
				.getBeansOfType(SiteParameterItemService.class).values()
				.iterator().next();

	}

	public static int intParam(String paramKey, String name) {
		try {
			return Integer.parseInt(param(paramKey, name));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static double doubleParam(String paramKey, String name) {
		try {
			return Double.parseDouble(param(paramKey, name));
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static String param(String paramKey, String name) {
		SiteParameterItem paramItem = itemService
				.getSiteParameterItemByParameterAndKey(paramKey, name);
		return (null == paramItem) ? "" : paramItem.getParamItemValue();
	}
	
	public static List<SiteParameterItem> paramList(String paramKey) {
		List<SiteParameterItem> paramList = itemService
				.findSiteParameterItemByParameter(paramKey);
		return (null == paramList) ? new ArrayList<SiteParameterItem>() : paramList;
	}
}

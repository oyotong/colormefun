package shop.common.util;

import org.springframework.web.context.support.WebApplicationContextUtils;
import shop.common.context.ApplicationContext;
import shop.company.entity.AuthUser;
import shop.company.entity.SiteParameterItem;
import shop.company.service.AuthUserService;
import shop.company.service.SiteParameterItemService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserUtils {
	private static AuthUserService userService;
	static {
        userService = (AuthUserService) WebApplicationContextUtils
				.getWebApplicationContext(
						ApplicationContext.getContext().getApplication())
				.getBeansOfType(AuthUserService.class).values()
				.iterator().next();

	}

	public static AuthUser getAuthUserById(Serializable id) {
		return userService.getAuthUserById(id);
	}
}

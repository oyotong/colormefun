package shop.company.action;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import shop.common.action.SimpleCaptcha;
import shop.common.action.auto.AbstractAction;
import shop.common.exception.ApplicationException;
import shop.company.entity.AuthUser;
import shop.company.service.AuthPurviewService;
import shop.company.service.AuthUserShortcutmenuService;
import shop.company.service.UserService;

import com.opensymphony.xwork2.Preparable;

@Controller
@Scope("prototype")
@Namespaces({ @Namespace("/admin/user") })
@ParentPackage("my-default")
public class UserAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private AuthPurviewService authPurviewService;
	private AuthUserShortcutmenuService authUserShortcutmenuService;
	private AuthUser user;

	private String errorMessage;
	private String message;
	private String validateCode;

	private Object json;

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public UserAction() {
	}

	@Autowired
	public UserAction(UserService userService,
			AuthPurviewService authPurviewService,
			AuthUserShortcutmenuService authUserShortcutmenuService) {
		this.userService = userService;
		this.authPurviewService = authPurviewService;
		this.authUserShortcutmenuService = authUserShortcutmenuService;
	}

	@Action(value = "login", results = {
			@Result(name = "success", type = "redirect", location = "/admin/index.jsp"),
			@Result(name = "input", location = "/admin/login.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String login() {
		try {
			String captcha = (String) getContext().getSession().getAttribute(
					SimpleCaptcha.NAME);
			if (null == validateCode || !validateCode.equals(captcha)) {
				throw new ApplicationException("验证码无效。");
			}

			getContext().getSession().setAttribute(
					getContext().getConfiguration(shop.Constants.SESSION_AUTH_USER_NAME),
					userService.login(user));
			getContext().getSession().setAttribute("menu",
					authPurviewService.findAllAuthPurviewAndKidsByUser(user));

			getContext().getSession().setAttribute("shortmenu",
					authUserShortcutmenuService.findAllAuthUserShortcutmenuByUser(user));

		} catch (ApplicationException e) {
			addActionError(e.getMessage());
			return "input";
		} catch (Exception e) {
			addActionError(e+"");
			return "input";
		}
		addActionMessage("登录成功。");
		return "success";
	}

	@Action(value = "logout", results = {
			@Result(name = "success", type = "redirect", location = "/admin/login.jsp"),
			@Result(name = "input", location = "/admin/index.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String logout() {
		try {
			HttpSession session = getContext().getSession();
			Enumeration<String> en = session.getAttributeNames();
			
			while(en.hasMoreElements()){
				session.removeAttribute(en.nextElement());
			}
		} catch (ApplicationException e) {
			addActionError(e.getMessage());
			return "input";
		}
		addActionMessage("登出成功，谢谢使用。");
		return "success";
	}

	public void prepare() throws Exception {

	}

	public AuthUser getUser() {
		return user;
	}

	public void setUser(AuthUser user) {
		this.user = user;
	}

	public AuthPurviewService getAuthPurviewService() {
		return authPurviewService;
	}

	public void setAuthPurviewService(AuthPurviewService authPurviewService) {
		this.authPurviewService = authPurviewService;
	}

}

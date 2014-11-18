package shop.company.action;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import shop.common.action.auto.AbstractAction;
import shop.company.entity.AuthPurview;
import shop.company.entity.AuthRole;
import shop.company.service.AuthPurviewService;
import shop.company.service.AuthRoleService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/system/role") })
@ParentPackage("my-default")
public class AuthRoleAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private AuthRoleService authRoleService;
	private AuthPurviewService authPurviewService;
	private List<AuthRole> authRoleList;
	private List<AuthPurview> authPurviewList;
	private AuthRole authRole;

	private String[] cids;

	private Object json;

	public AuthPurviewService getAuthPurviewService() {
		return authPurviewService;
	}

	public void setAuthPurviewService(AuthPurviewService authPurviewService) {
		this.authPurviewService = authPurviewService;
	}

	public AuthRoleService getAuthRoleService() {
		return authRoleService;
	}

	public void setAuthRoleService(AuthRoleService authRoleService) {
		this.authRoleService = authRoleService;
	}

	public List<AuthPurview> getAuthPurviewList() {
		return authPurviewList;
	}

	public void setAuthPurviewList(List<AuthPurview> menuList) {
		this.authPurviewList = menuList;
	}

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}

	public void setCids(String[] cids) {
		this.cids = cids;
	}

	public String[] getCids() {
		return this.cids;
	}

	public List<AuthRole> getAuthRoleList() {
		return authRoleList;
	}

	public void setAuthRoleList(List<AuthRole> authRoleList) {
		this.authRoleList = authRoleList;
	}

	public AuthRoleAction() {
	}

	@Autowired
	public AuthRoleAction(AuthRoleService authRoleService,AuthPurviewService authPurviewService) {
		this.authPurviewService = authPurviewService;
		this.authRoleService = authRoleService;
	}

	@Action(value = "add", results = { @Result(location = "/admin/system/role/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		this.authPurviewList = this.authPurviewService.findAllAuthPurviewWithKids();
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/system/role/list.jsp"),
			@Result(name = "input", location = "/admin/system/role/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.authRoleList = authRoleService.findAllAuthRoleWithPage();
		return ActionSupport.SUCCESS;
	}

	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/system/role/list.jsp"),
			@Result(name = "input", location = "/admin/system/role/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.authRoleList = authRoleService
				.findAllAuthRoleBySearchWithPage(authRole);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/system/role/edit.jsp"),
			@Result(name = "input", location = "/admin/system/role/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authRole.roleId", message = "You must select a Id.") })
	public String edit() {
		java.io.Serializable id = null;
		if (null != cids && cids.length > 0) {
			id = cids[0];
		} else {
			id = authRole.getRoleId();
		}
		authRole = this.authRoleService.getAuthRoleById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/system/role/view.jsp"),
			@Result(name = "input", location = "/admin/system/role/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authRole.roleId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if (null != cids && cids.length > 0) {
			id = cids[0];
		} else {
			id = authRole.getRoleId();
		}
		authRole = this.authRoleService.getAuthRoleById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/system/role/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authRole.roleId", message = "You must select a Id.") })
	public String remove() {
		if (null != cids && cids.length > 0) {
			authRoleService.deleteAuthRoleByIds(cids);
		} else {
			authRoleService.deleteAuthRole(authRole);
		}
		return ActionSupport.SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/system/role/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authRole.roleName", message = "You must enter a value for roleName(save).") })
	public String save() {
		try{
			this.authRoleService.saveAuthRole(authRole);
			return ActionSupport.SUCCESS;
		}catch(Exception e ){
			this.authPurviewList = this.authPurviewService.findAllAuthPurviewWithKids();
			addActionError(e.getMessage());
			return ActionSupport.INPUT;
		}
		
	}

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/system/role/editPurview.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authRole.roleId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authRole.roleName", message = "You must enter a value for roleName(save).") })
	public String update() {
		this.authRoleService.updateAuthRole(authRole);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = authRoleService.findAllAuthRoleByNameWithIdAndName(authRole
				.getRoleName());
		return ActionSupport.SUCCESS;
	}

	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/system/role/select.jsp"),
			@Result(name = "input", location = "/admin/system/role/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.authRoleList = authRoleService
				.findAllAuthRoleBySearchWithPage(authRole);
		return ActionSupport.SUCCESS;
	}

	/* /admin/system/role/selected.jsp */
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/system/role/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/system/role/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if (null == cids || cids.length == 0) {
			cids = new String[1];
			cids[0] = authRole.getRoleId();
		}
		// this.authRoleList = authRoleService.findAllAuthRoleByIds(cids);
		this.json = authRoleService.findAllAuthRoleByIds(cids);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "editPurview", results = {
			@Result(name = "success", location = "/admin/system/role/editPurview.jsp"),
			@Result(name = "input", location = "/admin/system/role/editPurview.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String editPurview() {
		this.authPurviewList = this.authPurviewService.findAllAuthPurviewWithKids();
		this.authRole = this.authRoleService.getAuthRoleById(this.cids[0]);
		return ActionSupport.SUCCESS;
	}

	public List<AuthRole> getAuthRoles() {
		return authRoleList;
	}

	public void prepare() throws Exception {

	}

	public AuthRole getAuthRole() {
		return authRole;
	}

	public void setAuthRole(AuthRole authRole) {
		this.authRole = authRole;
	}

}

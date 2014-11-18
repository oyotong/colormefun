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
import shop.company.entity.AuthUserShortcutmenu;
import shop.company.service.AuthUserShortcutmenuService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/user/shortMenu") })
@ParentPackage("my-default")
public class AuthUserShortcutmenuAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private AuthUserShortcutmenuService authUserShortcutmenuService;
	private List<AuthUserShortcutmenu> authUserShortcutmenuList;
	private AuthUserShortcutmenu authUserShortcutmenu;
	
	private Integer[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(Integer[] cids){
		this.cids = cids;
	}
	
	public Integer[] getCids(){
		return this.cids;
	}

	public List<AuthUserShortcutmenu> getAuthUserShortcutmenuList() {
		return authUserShortcutmenuList;
	}

	public void setAuthUserShortcutmenuList(List<AuthUserShortcutmenu> authUserShortcutmenuList) {
		this.authUserShortcutmenuList = authUserShortcutmenuList;
	}

	public AuthUserShortcutmenuAction() {
	}

	@Autowired
	public AuthUserShortcutmenuAction(AuthUserShortcutmenuService authUserShortcutmenuService) {
		this.authUserShortcutmenuService = authUserShortcutmenuService;
	}

	@Action(value = "add", results = { @Result(location = "/admin/authUserShortcutmenu/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/authUserShortcutmenu/list.jsp"),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.authUserShortcutmenuList = authUserShortcutmenuService.findAllAuthUserShortcutmenuWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/authUserShortcutmenu/list.jsp"),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.authUserShortcutmenuList = authUserShortcutmenuService.findAllAuthUserShortcutmenuBySearchWithPage(authUserShortcutmenu);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/authUserShortcutmenu/edit.jsp"),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUserShortcutmenu.shortMenuId", message = "You must select a Id.") })
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = authUserShortcutmenu.getShortMenuId();
		}
		authUserShortcutmenu = this.authUserShortcutmenuService.getAuthUserShortcutmenuById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/authUserShortcutmenu/view.jsp"),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUserShortcutmenu.shortMenuId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = authUserShortcutmenu.getShortMenuId();
		}
		authUserShortcutmenu = this.authUserShortcutmenuService.getAuthUserShortcutmenuById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUserShortcutmenu.shortMenuId", message = "You must select a Id.") })
	public String remove() {
		if(null != cids && cids.length > 0){
			authUserShortcutmenuService.deleteAuthUserShortcutmenuByIds(cids);
		}else{
			authUserShortcutmenuService.deleteAuthUserShortcutmenu(authUserShortcutmenu);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUserShortcutmenu.shortMenuId", message = "You must enter a value for shortMenuId(save).")})
	public String save() {
		this.authUserShortcutmenuService.saveAuthUserShortcutmenu(authUserShortcutmenu);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUserShortcutmenu.shortMenuId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUserShortcutmenu.shortMenuId", message = "You must enter a value for shortMenuId(save).")})
	public String update() {
		this.authUserShortcutmenuService.updateAuthUserShortcutmenu(authUserShortcutmenu);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = authUserShortcutmenuService.findAllAuthUserShortcutmenuByNameWithIdAndName(""+authUserShortcutmenu.getShortMenuId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/authUserShortcutmenu/select.jsp"),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.authUserShortcutmenuList = authUserShortcutmenuService.findAllAuthUserShortcutmenuBySearchWithPage(authUserShortcutmenu);
		return ActionSupport.SUCCESS;
	}
	/*/admin/authUserShortcutmenu/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/authUserShortcutmenu/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/authUserShortcutmenu/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = authUserShortcutmenu.getShortMenuId();
		}
		//this.authUserShortcutmenuList = authUserShortcutmenuService.findAllAuthUserShortcutmenuByIds(cids);
		this.json = authUserShortcutmenuService.findAllAuthUserShortcutmenuByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<AuthUserShortcutmenu> getAuthUserShortcutmenus() {
		return authUserShortcutmenuList;
	}

	public void prepare() throws Exception {
		
	}

	public AuthUserShortcutmenu getAuthUserShortcutmenu() {
		return authUserShortcutmenu;
	}

	public void setAuthUserShortcutmenu(AuthUserShortcutmenu authUserShortcutmenu) {
		this.authUserShortcutmenu = authUserShortcutmenu;
	}

}

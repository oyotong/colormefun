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
import shop.company.service.AuthPurviewService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/system/menu") })
@ParentPackage("my-default")
public class AuthMenuAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private AuthPurviewService authPurviewService;
	private List<AuthPurview> authPurviewList;
	private AuthPurview authPurview;
	
	private String[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(String[] cids){
		this.cids = cids;
	}
	
	public String[] getCids(){
		return this.cids;
	}

	public List<AuthPurview> getAuthPurviewList() {
		return authPurviewList;
	}

	public void setAuthPurviewList(List<AuthPurview> authPurviewList) {
		this.authPurviewList = authPurviewList;
	}

	public AuthMenuAction() {
	}

	@Autowired
	public AuthMenuAction(AuthPurviewService authPurviewService) {
		this.authPurviewService = authPurviewService;
	}

	@Action(value = "add", results = { @Result(location = "/admin/system/menu/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/system/menu/list.jsp"),
			@Result(name = "input", location = "/admin/system/menu/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.authPurviewList = authPurviewService.findAllAuthPurviewWithPage();//
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/system/menu/list.jsp"),
			@Result(name = "input", location = "/admin/system/menu/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.authPurviewList = authPurviewService.findAllAuthPurviewBySearchWithPage(authPurview);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/system/menu/edit.jsp"),
			@Result(name = "input", location = "/admin/system/menu/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		String id = "";
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = authPurview.getPurviewId();
		}
		id = id.replace(", ", "");
		authPurview = this.authPurviewService.getAuthPurviewById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/system/menu/view.jsp"),
			@Result(name = "input", location = "/admin/system/menu/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authPurview.purviewId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = authPurview.getPurviewId();
		}
		authPurview = this.authPurviewService.getAuthPurviewById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "search" }),
			@Result(name = "input", location = "/admin/system/menu/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			authPurviewService.deleteAuthPurviewByIds(cids);
		}else{
			authPurviewService.deleteAuthPurview(authPurview);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "search" }),
			@Result(name = "input", location = "/admin/system/menu/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authPurview.purviewName", message = "You must enter a value for purviewName(save).")})
	public String save() {
		this.authPurviewService.saveAuthPurview(authPurview);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "search" }),
			@Result(name = "input", location = "/admin/system/menu/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authPurview.purviewId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authPurview.purviewName", message = "You must enter a value for purviewName(save).")})
	public String update() {
		this.authPurviewService.updateAuthPurview(authPurview);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = authPurviewService.findAllAuthPurviewByNameWithIdAndName(authPurview.getPurviewName());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/system/menu/select.jsp"),
			@Result(name = "input", location = "/admin/system/menu/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.authPurviewList = authPurviewService.findAllAuthPurviewBySearchWithPage(authPurview);
		return ActionSupport.SUCCESS;
	}
	/*/admin/authPurview/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/system/menu/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/system/menu/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = authPurview.getPurviewId();
		}
		//this.authPurviewList = authPurviewService.findAllAuthPurviewByIds(cids);
		this.json = authPurviewService.findAllAuthPurviewByIds(cids);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "menuJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String menuJson() {
		json = authPurviewService.findAllAuthPurviewWithKids();
		return ActionSupport.SUCCESS;
	}
	
	public List<AuthPurview> getAuthPurviews() {
		return authPurviewList;
	}

	public void prepare() throws Exception {
		
	}

	public AuthPurview getAuthPurview() {
		return authPurview;
	}

	public void setAuthPurview(AuthPurview authPurview) {
		this.authPurview = authPurview;
	}

}

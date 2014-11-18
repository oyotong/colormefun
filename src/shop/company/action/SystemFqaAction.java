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
import shop.company.entity.SystemFqa;
import shop.company.service.SystemFqaService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/info/fqa") })
@ParentPackage("my-default")
public class SystemFqaAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SystemFqaService systemFqaService;
	private List<SystemFqa> systemFqaList;
	private SystemFqa systemFqa;
	
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

	public List<SystemFqa> getSystemFqaList() {
		return systemFqaList;
	}

	public void setSystemFqaList(List<SystemFqa> systemFqaList) {
		this.systemFqaList = systemFqaList;
	}

	public SystemFqaAction() {
	}

	@Autowired
	public SystemFqaAction(SystemFqaService systemFqaService) {
		this.systemFqaService = systemFqaService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success",location = "/admin/info/fqa/add.jsp"),
			@Result(name = "input", location = "/admin/info/fqa/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/info/fqa/list.jsp"),
			@Result(name = "input", location = "/admin/info/fqa/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		return search();
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/info/fqa/list.jsp"),
			@Result(name = "input", location = "/admin/info/fqa/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.systemFqaList = systemFqaService.findAllSystemFqaBySearchWithPage(systemFqa);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/info/fqa/edit.jsp"),
			@Result(name = "input", location = "/admin/info/fqa/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemFqa.getFqaId();
		}
		systemFqa = this.systemFqaService.getSystemFqaById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/info/fqa/view.jsp"),
			@Result(name = "input", location = "/admin/info/fqa/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemFqa.fqaId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemFqa.getFqaId();
		}
		systemFqa = this.systemFqaService.getSystemFqaById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/info/fqa/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			systemFqaService.deleteSystemFqaByIds(cids);
		}else{
			systemFqaService.deleteSystemFqa(systemFqa);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/info/fqa/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	/*@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemFqa.fqaId", message = "You must enter a value for fqaId(save).")})*/
	public String save() {
		this.systemFqaService.saveSystemFqa(systemFqa);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/info/fqa/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemFqa.fqaId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemFqa.fqaQuestion", message = "You must enter a value for fqaQuestion(save).")})
	public String update() {
		this.systemFqaService.updateSystemFqa(systemFqa);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = systemFqaService.findAllSystemFqaByNameWithIdAndName(""+systemFqa.getFqaId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/info/fqa/select.jsp"),
			@Result(name = "input", location = "/admin/info/fqa/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.systemFqaList = systemFqaService.findAllSystemFqaBySearchWithPage(systemFqa);
		return ActionSupport.SUCCESS;
	}
	/*/admin/info/fqa/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/info/fqa/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/info/fqa/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = systemFqa.getFqaId();
		}
		//this.systemFqaList = systemFqaService.findAllSystemFqaByIds(cids);
		this.json = systemFqaService.findAllSystemFqaByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SystemFqa> getSystemFqas() {
		return systemFqaList;
	}

	public void prepare() throws Exception {
		
	}

	public SystemFqa getSystemFqa() {
		return systemFqa;
	}

	public void setSystemFqa(SystemFqa systemFqa) {
		this.systemFqa = systemFqa;
	}

}

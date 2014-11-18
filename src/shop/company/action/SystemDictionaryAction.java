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
import shop.company.entity.SystemDictionary;
import shop.company.service.SystemDictionaryService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/systemDictionary") })
@ParentPackage("my-default")
public class SystemDictionaryAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SystemDictionaryService systemDictionaryService;
	private List<SystemDictionary> systemDictionaryList;
	private SystemDictionary systemDictionary;
	
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

	public List<SystemDictionary> getSystemDictionaryList() {
		return systemDictionaryList;
	}

	public void setSystemDictionaryList(List<SystemDictionary> systemDictionaryList) {
		this.systemDictionaryList = systemDictionaryList;
	}

	public SystemDictionaryAction() {
	}

	@Autowired
	public SystemDictionaryAction(SystemDictionaryService systemDictionaryService) {
		this.systemDictionaryService = systemDictionaryService;
	}

	@Action(value = "add", results = { @Result(location = "/admin/systemDictionary/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/systemDictionary/list.jsp"),
			@Result(name = "input", location = "/admin/systemDictionary/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.systemDictionaryList = systemDictionaryService.findAllSystemDictionaryWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/systemDictionary/list.jsp"),
			@Result(name = "input", location = "/admin/systemDictionary/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.systemDictionaryList = systemDictionaryService.findAllSystemDictionaryBySearchWithPage(systemDictionary);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/systemDictionary/edit.jsp"),
			@Result(name = "input", location = "/admin/systemDictionary/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionary.dictId", message = "You must select a Id.") })
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemDictionary.getDictId();
		}
		systemDictionary = this.systemDictionaryService.getSystemDictionaryById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/systemDictionary/view.jsp"),
			@Result(name = "input", location = "/admin/systemDictionary/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionary.dictId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemDictionary.getDictId();
		}
		systemDictionary = this.systemDictionaryService.getSystemDictionaryById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDictionary/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionary.dictId", message = "You must select a Id.") })
	public String remove() {
		if(null != cids && cids.length > 0){
			systemDictionaryService.deleteSystemDictionaryByIds(cids);
		}else{
			systemDictionaryService.deleteSystemDictionary(systemDictionary);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDictionary/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionary.dictName", message = "You must enter a value for dictName(save).")})
	public String save() {
		this.systemDictionaryService.saveSystemDictionary(systemDictionary);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDictionary/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionary.dictId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionary.dictName", message = "You must enter a value for dictName(save).")})
	public String update() {
		this.systemDictionaryService.updateSystemDictionary(systemDictionary);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = systemDictionaryService.findAllSystemDictionaryByNameWithIdAndName(systemDictionary.getDictName());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/systemDictionary/select.jsp"),
			@Result(name = "input", location = "/admin/systemDictionary/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.systemDictionaryList = systemDictionaryService.findAllSystemDictionaryBySearchWithPage(systemDictionary);
		return ActionSupport.SUCCESS;
	}
	/*/admin/systemDictionary/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/systemDictionary/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/systemDictionary/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = systemDictionary.getDictId();
		}
		//this.systemDictionaryList = systemDictionaryService.findAllSystemDictionaryByIds(cids);
		this.json = systemDictionaryService.findAllSystemDictionaryByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SystemDictionary> getSystemDictionarys() {
		return systemDictionaryList;
	}

	public void prepare() throws Exception {
		
	}

	public SystemDictionary getSystemDictionary() {
		return systemDictionary;
	}

	public void setSystemDictionary(SystemDictionary systemDictionary) {
		this.systemDictionary = systemDictionary;
	}

}

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
import shop.company.entity.SystemDbBackup;
import shop.company.service.SystemDbBackupService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/systemDbBackup") })
@ParentPackage("my-default")
public class SystemDbBackupAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SystemDbBackupService systemDbBackupService;
	private List<SystemDbBackup> systemDbBackupList;
	private SystemDbBackup systemDbBackup;
	
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

	public List<SystemDbBackup> getSystemDbBackupList() {
		return systemDbBackupList;
	}

	public void setSystemDbBackupList(List<SystemDbBackup> systemDbBackupList) {
		this.systemDbBackupList = systemDbBackupList;
	}

	public SystemDbBackupAction() {
	}

	@Autowired
	public SystemDbBackupAction(SystemDbBackupService systemDbBackupService) {
		this.systemDbBackupService = systemDbBackupService;
	}

	@Action(value = "add", results = { @Result(location = "/admin/systemDbBackup/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/systemDbBackup/list.jsp"),
			@Result(name = "input", location = "/admin/systemDbBackup/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		return search();
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/systemDbBackup/list.jsp"),
			@Result(name = "input", location = "/admin/systemDbBackup/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.systemDbBackupList = systemDbBackupService.findAllSystemDbBackupBySearchWithPage(systemDbBackup);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/systemDbBackup/edit.jsp"),
			@Result(name = "input", location = "/admin/systemDbBackup/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDbBackup.bkId", message = "You must select a Id.") })
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemDbBackup.getBkId();
		}
		systemDbBackup = this.systemDbBackupService.getSystemDbBackupById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/systemDbBackup/view.jsp"),
			@Result(name = "input", location = "/admin/systemDbBackup/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDbBackup.bkId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemDbBackup.getBkId();
		}
		systemDbBackup = this.systemDbBackupService.getSystemDbBackupById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDbBackup/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDbBackup.bkId", message = "You must select a Id.") })
	public String remove() {
		if(null != cids && cids.length > 0){
			systemDbBackupService.deleteSystemDbBackupByIds(cids);
		}else{
			systemDbBackupService.deleteSystemDbBackup(systemDbBackup);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDbBackup/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDbBackup.bkTitle", message = "You must enter a value for bkTitle(save).")})
	public String save() {
		this.systemDbBackupService.saveSystemDbBackup(systemDbBackup);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDbBackup/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDbBackup.bkId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDbBackup.bkTitle", message = "You must enter a value for bkTitle(save).")})
	public String update() {
		this.systemDbBackupService.updateSystemDbBackup(systemDbBackup);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = systemDbBackupService.findAllSystemDbBackupByNameWithIdAndName(""+systemDbBackup.getBkTitle());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/systemDbBackup/select.jsp"),
			@Result(name = "input", location = "/admin/systemDbBackup/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.systemDbBackupList = systemDbBackupService.findAllSystemDbBackupBySearchWithPage(systemDbBackup);
		return ActionSupport.SUCCESS;
	}
	/*/admin/systemDbBackup/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/systemDbBackup/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/systemDbBackup/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = systemDbBackup.getBkId();
		}
		//this.systemDbBackupList = systemDbBackupService.findAllSystemDbBackupByIds(cids);
		this.json = systemDbBackupService.findAllSystemDbBackupByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SystemDbBackup> getSystemDbBackups() {
		return systemDbBackupList;
	}

	public void prepare() throws Exception {
		
	}

	public SystemDbBackup getSystemDbBackup() {
		return systemDbBackup;
	}

	public void setSystemDbBackup(SystemDbBackup systemDbBackup) {
		this.systemDbBackup = systemDbBackup;
	}

}

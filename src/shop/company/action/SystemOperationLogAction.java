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

import shop.company.entity.SystemOperationLog;
import shop.company.service.SystemOperationLogService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/systemOperationLog") })
@ParentPackage("my-default")
public class SystemOperationLogAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SystemOperationLogService systemOperationLogService;
	private List<SystemOperationLog> systemOperationLogList;
	private SystemOperationLog systemOperationLog;
	
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

	public List<SystemOperationLog> getSystemOperationLogList() {
		return systemOperationLogList;
	}

	public void setSystemOperationLogList(List<SystemOperationLog> systemOperationLogList) {
		this.systemOperationLogList = systemOperationLogList;
	}

	public SystemOperationLogAction() {
	}

	@Autowired
	public SystemOperationLogAction(SystemOperationLogService systemOperationLogService) {
		this.systemOperationLogService = systemOperationLogService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/systemOperationLog/add.jsp"),
			@Result(name = "input", location = "/admin/systemOperationLog/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/systemOperationLog/list.jsp"),
			@Result(name = "input", location = "/admin/systemOperationLog/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.systemOperationLogList = systemOperationLogService.findAllSystemOperationLogWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/systemOperationLog/list.jsp"),
			@Result(name = "input", location = "/admin/systemOperationLog/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.systemOperationLogList = systemOperationLogService.findAllSystemOperationLogBySearchWithPage(systemOperationLog);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/systemOperationLog/edit.jsp"),
			@Result(name = "input", location = "/admin/systemOperationLog/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemOperationLog.getOpId();
		}
		systemOperationLog = this.systemOperationLogService.getSystemOperationLogById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/systemOperationLog/view.jsp"),
			@Result(name = "input", location = "/admin/systemOperationLog/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemOperationLog.opId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemOperationLog.getOpId();
		}
		systemOperationLog = this.systemOperationLogService.getSystemOperationLogById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemOperationLog/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			systemOperationLogService.deleteSystemOperationLogByIds(cids);
		}else{
			systemOperationLogService.deleteSystemOperationLog(systemOperationLog);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemOperationLog/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemOperationLog.opId", message = "You must enter a value for opId(save).")})
	public String save() {
		this.systemOperationLogService.saveSystemOperationLog(systemOperationLog);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemOperationLog/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemOperationLog.opId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemOperationLog.opId", message = "You must enter a value for opId(save).")})
	public String update() {
		this.systemOperationLogService.updateSystemOperationLog(systemOperationLog);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = systemOperationLogService.findAllSystemOperationLogByNameWithIdAndName(""+systemOperationLog.getOpId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/systemOperationLog/select.jsp"),
			@Result(name = "input", location = "/admin/systemOperationLog/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.systemOperationLogList = systemOperationLogService.findAllSystemOperationLogBySearchWithPage(systemOperationLog);
		return ActionSupport.SUCCESS;
	}
	/*/admin/systemOperationLog/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/systemOperationLog/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/systemOperationLog/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = systemOperationLog.getOpId();
		}
		//this.systemOperationLogList = systemOperationLogService.findAllSystemOperationLogByIds(cids);
		this.json = systemOperationLogService.findAllSystemOperationLogByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SystemOperationLog> getSystemOperationLogs() {
		return systemOperationLogList;
	}

	public void prepare() throws Exception {
		
	}

	public SystemOperationLog getSystemOperationLog() {
		return systemOperationLog;
	}

	public void setSystemOperationLog(SystemOperationLog systemOperationLog) {
		this.systemOperationLog = systemOperationLog;
	}

}

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

import shop.company.entity.SystemLoginLog;
import shop.company.service.SystemLoginLogService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/systemLoginLog") })
@ParentPackage("my-default")
public class SystemLoginLogAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SystemLoginLogService systemLoginLogService;
	private List<SystemLoginLog> systemLoginLogList;
	private SystemLoginLog systemLoginLog;
	
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

	public List<SystemLoginLog> getSystemLoginLogList() {
		return systemLoginLogList;
	}

	public void setSystemLoginLogList(List<SystemLoginLog> systemLoginLogList) {
		this.systemLoginLogList = systemLoginLogList;
	}

	public SystemLoginLogAction() {
	}

	@Autowired
	public SystemLoginLogAction(SystemLoginLogService systemLoginLogService) {
		this.systemLoginLogService = systemLoginLogService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/systemLoginLog/add.jsp"),
			@Result(name = "input", location = "/admin/systemLoginLog/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/systemLoginLog/list.jsp"),
			@Result(name = "input", location = "/admin/systemLoginLog/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.systemLoginLogList = systemLoginLogService.findAllSystemLoginLogWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/systemLoginLog/list.jsp"),
			@Result(name = "input", location = "/admin/systemLoginLog/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.systemLoginLogList = systemLoginLogService.findAllSystemLoginLogBySearchWithPage(systemLoginLog);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/systemLoginLog/edit.jsp"),
			@Result(name = "input", location = "/admin/systemLoginLog/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemLoginLog.getLoginId();
		}
		systemLoginLog = this.systemLoginLogService.getSystemLoginLogById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/systemLoginLog/view.jsp"),
			@Result(name = "input", location = "/admin/systemLoginLog/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemLoginLog.loginId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemLoginLog.getLoginId();
		}
		systemLoginLog = this.systemLoginLogService.getSystemLoginLogById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemLoginLog/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			systemLoginLogService.deleteSystemLoginLogByIds(cids);
		}else{
			systemLoginLogService.deleteSystemLoginLog(systemLoginLog);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemLoginLog/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemLoginLog.loginId", message = "You must enter a value for loginId(save).")})
	public String save() {
		this.systemLoginLogService.saveSystemLoginLog(systemLoginLog);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemLoginLog/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemLoginLog.loginId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemLoginLog.loginId", message = "You must enter a value for loginId(save).")})
	public String update() {
		this.systemLoginLogService.updateSystemLoginLog(systemLoginLog);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = systemLoginLogService.findAllSystemLoginLogByNameWithIdAndName(""+systemLoginLog.getLoginId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/systemLoginLog/select.jsp"),
			@Result(name = "input", location = "/admin/systemLoginLog/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.systemLoginLogList = systemLoginLogService.findAllSystemLoginLogBySearchWithPage(systemLoginLog);
		return ActionSupport.SUCCESS;
	}
	/*/admin/systemLoginLog/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/systemLoginLog/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/systemLoginLog/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = systemLoginLog.getLoginId();
		}
		//this.systemLoginLogList = systemLoginLogService.findAllSystemLoginLogByIds(cids);
		this.json = systemLoginLogService.findAllSystemLoginLogByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SystemLoginLog> getSystemLoginLogs() {
		return systemLoginLogList;
	}

	public void prepare() throws Exception {
		
	}

	public SystemLoginLog getSystemLoginLog() {
		return systemLoginLog;
	}

	public void setSystemLoginLog(SystemLoginLog systemLoginLog) {
		this.systemLoginLog = systemLoginLog;
	}

}

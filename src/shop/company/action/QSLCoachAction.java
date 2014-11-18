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

import shop.company.entity.QSLCoach;
import shop.company.service.QSLCoachService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/company/qSLCoach") })
@ParentPackage("my-default")
public class QSLCoachAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLCoachService qSLCoachService;
	private List<QSLCoach> qSLCoachList;
	private QSLCoach qSLCoach;
	
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

	public List<QSLCoach> getQSLCoachList() {
		return qSLCoachList;
	}

	public void setQSLCoachList(List<QSLCoach> qSLCoachList) {
		this.qSLCoachList = qSLCoachList;
	}

	public QSLCoachAction() {
	}

	@Autowired
	public QSLCoachAction(QSLCoachService qSLCoachService) {
		this.qSLCoachService = qSLCoachService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/company/qSLCoach/add.jsp"),
			@Result(name = "input", location = "/company/qSLCoach/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/company/qSLCoach/list.jsp"),
			@Result(name = "input", location = "/company/qSLCoach/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.qSLCoachList = qSLCoachService.findAllQSLCoachWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/company/qSLCoach/list.jsp"),
			@Result(name = "input", location = "/company/qSLCoach/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.qSLCoachList = qSLCoachService.findAllQSLCoachBySearchWithPage(qSLCoach);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/company/qSLCoach/edit.jsp"),
			@Result(name = "input", location = "/company/qSLCoach/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLCoach.getId();
		}
		qSLCoach = this.qSLCoachService.getQSLCoachById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/company/qSLCoach/view.jsp"),
			@Result(name = "input", location = "/company/qSLCoach/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCoach.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLCoach.getId();
		}
		qSLCoach = this.qSLCoachService.getQSLCoachById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCoach/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			qSLCoachService.deleteQSLCoachByIds(cids);
		}else{
			qSLCoachService.deleteQSLCoach(qSLCoach);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCoach/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.qSLCoachService.saveQSLCoach(qSLCoach);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCoach/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCoach.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCoach.firstName", message = "You must enter a value for firstName(save).")})
	public String update() {
		this.qSLCoachService.updateQSLCoach(qSLCoach);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = qSLCoachService.findAllQSLCoachByNameWithIdAndName(""+qSLCoach.getFirstName());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/company/qSLCoach/select.jsp"),
			@Result(name = "input", location = "/company/qSLCoach/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.qSLCoachList = qSLCoachService.findAllQSLCoachBySearchWithPage(qSLCoach);
		return ActionSupport.SUCCESS;
	}
	/*/company/qSLCoach/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/company/qSLCoach/selectedJson.jsp"),
			@Result(name = "input", location = "/company/qSLCoach/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = qSLCoach.getId();
		}
		//this.qSLCoachList = qSLCoachService.findAllQSLCoachByIds(cids);
		this.json = qSLCoachService.findAllQSLCoachByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<QSLCoach> getQSLCoachs() {
		return qSLCoachList;
	}

	public QSLCoach getQSLCoach() {
		return qSLCoach;
	}

	public void setQSLCoach(QSLCoach qSLCoach) {
		this.qSLCoach = qSLCoach;
	}

}

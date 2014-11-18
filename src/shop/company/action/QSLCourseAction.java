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

import shop.company.entity.QSLCourse;
import shop.company.service.QSLCourseService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/company/qSLCourse") })
@ParentPackage("my-default")
public class QSLCourseAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLCourseService qSLCourseService;
	private List<QSLCourse> qSLCourseList;
	private QSLCourse qSLCourse;
	
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

	public List<QSLCourse> getQSLCourseList() {
		return qSLCourseList;
	}

	public void setQSLCourseList(List<QSLCourse> qSLCourseList) {
		this.qSLCourseList = qSLCourseList;
	}

	public QSLCourseAction() {
	}

	@Autowired
	public QSLCourseAction(QSLCourseService qSLCourseService) {
		this.qSLCourseService = qSLCourseService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/company/qSLCourse/add.jsp"),
			@Result(name = "input", location = "/company/qSLCourse/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/company/qSLCourse/list.jsp"),
			@Result(name = "input", location = "/company/qSLCourse/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.qSLCourseList = qSLCourseService.findAllQSLCourseWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/company/qSLCourse/list.jsp"),
			@Result(name = "input", location = "/company/qSLCourse/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.qSLCourseList = qSLCourseService.findAllQSLCourseBySearchWithPage(qSLCourse);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/company/qSLCourse/edit.jsp"),
			@Result(name = "input", location = "/company/qSLCourse/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLCourse.getId();
		}
		qSLCourse = this.qSLCourseService.getQSLCourseById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/company/qSLCourse/view.jsp"),
			@Result(name = "input", location = "/company/qSLCourse/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCourse.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLCourse.getId();
		}
		qSLCourse = this.qSLCourseService.getQSLCourseById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCourse/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			qSLCourseService.deleteQSLCourseByIds(cids);
		}else{
			qSLCourseService.deleteQSLCourse(qSLCourse);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCourse/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.qSLCourseService.saveQSLCourse(qSLCourse);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCourse/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCourse.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCourse.coachName", message = "You must enter a value for coachName(save).")})
	public String update() {
		this.qSLCourseService.updateQSLCourse(qSLCourse);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = qSLCourseService.findAllQSLCourseByNameWithIdAndName(""+qSLCourse.getCoachName());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/company/qSLCourse/select.jsp"),
			@Result(name = "input", location = "/company/qSLCourse/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.qSLCourseList = qSLCourseService.findAllQSLCourseBySearchWithPage(qSLCourse);
		return ActionSupport.SUCCESS;
	}
	/*/company/qSLCourse/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/company/qSLCourse/selectedJson.jsp"),
			@Result(name = "input", location = "/company/qSLCourse/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = qSLCourse.getId();
		}
		//this.qSLCourseList = qSLCourseService.findAllQSLCourseByIds(cids);
		this.json = qSLCourseService.findAllQSLCourseByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<QSLCourse> getQSLCourses() {
		return qSLCourseList;
	}

	public QSLCourse getQSLCourse() {
		return qSLCourse;
	}

	public void setQSLCourse(QSLCourse qSLCourse) {
		this.qSLCourse = qSLCourse;
	}

}

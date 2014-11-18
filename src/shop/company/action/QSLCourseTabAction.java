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

import shop.company.entity.QSLCourseTab;
import shop.company.service.QSLCourseTabService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/company/qSLCourseTab") })
@ParentPackage("my-default")
public class QSLCourseTabAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLCourseTabService qSLCourseTabService;
	private List<QSLCourseTab> qSLCourseTabList;
	private QSLCourseTab qSLCourseTab;
	
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

	public List<QSLCourseTab> getQSLCourseTabList() {
		return qSLCourseTabList;
	}

	public void setQSLCourseTabList(List<QSLCourseTab> qSLCourseTabList) {
		this.qSLCourseTabList = qSLCourseTabList;
	}

	public QSLCourseTabAction() {
	}

	@Autowired
	public QSLCourseTabAction(QSLCourseTabService qSLCourseTabService) {
		this.qSLCourseTabService = qSLCourseTabService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/company/qSLCourseTab/add.jsp"),
			@Result(name = "input", location = "/company/qSLCourseTab/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/company/qSLCourseTab/list.jsp"),
			@Result(name = "input", location = "/company/qSLCourseTab/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.qSLCourseTabList = qSLCourseTabService.findAllQSLCourseTabWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/company/qSLCourseTab/list.jsp"),
			@Result(name = "input", location = "/company/qSLCourseTab/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.qSLCourseTabList = qSLCourseTabService.findAllQSLCourseTabBySearchWithPage(qSLCourseTab);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/company/qSLCourseTab/edit.jsp"),
			@Result(name = "input", location = "/company/qSLCourseTab/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLCourseTab.getId();
		}
		qSLCourseTab = this.qSLCourseTabService.getQSLCourseTabById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/company/qSLCourseTab/view.jsp"),
			@Result(name = "input", location = "/company/qSLCourseTab/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCourseTab.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLCourseTab.getId();
		}
		qSLCourseTab = this.qSLCourseTabService.getQSLCourseTabById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCourseTab/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			qSLCourseTabService.deleteQSLCourseTabByIds(cids);
		}else{
			qSLCourseTabService.deleteQSLCourseTab(qSLCourseTab);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCourseTab/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.qSLCourseTabService.saveQSLCourseTab(qSLCourseTab);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLCourseTab/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCourseTab.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLCourseTab.id", message = "You must enter a value for id(save).")})
	public String update() {
		this.qSLCourseTabService.updateQSLCourseTab(qSLCourseTab);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = qSLCourseTabService.findAllQSLCourseTabByNameWithIdAndName(""+qSLCourseTab.getId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/company/qSLCourseTab/select.jsp"),
			@Result(name = "input", location = "/company/qSLCourseTab/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.qSLCourseTabList = qSLCourseTabService.findAllQSLCourseTabBySearchWithPage(qSLCourseTab);
		return ActionSupport.SUCCESS;
	}
	/*/company/qSLCourseTab/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/company/qSLCourseTab/selectedJson.jsp"),
			@Result(name = "input", location = "/company/qSLCourseTab/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = qSLCourseTab.getId();
		}
		//this.qSLCourseTabList = qSLCourseTabService.findAllQSLCourseTabByIds(cids);
		this.json = qSLCourseTabService.findAllQSLCourseTabByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<QSLCourseTab> getQSLCourseTabs() {
		return qSLCourseTabList;
	}

	public QSLCourseTab getQSLCourseTab() {
		return qSLCourseTab;
	}

	public void setQSLCourseTab(QSLCourseTab qSLCourseTab) {
		this.qSLCourseTab = qSLCourseTab;
	}

}

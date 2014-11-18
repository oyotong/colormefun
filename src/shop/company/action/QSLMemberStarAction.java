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

import shop.company.entity.QSLMemberStar;
import shop.company.service.QSLMemberStarService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/company/qSLMemberStar") })
@ParentPackage("my-default")
public class QSLMemberStarAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLMemberStarService qSLMemberStarService;
	private List<QSLMemberStar> qSLMemberStarList;
	private QSLMemberStar qSLMemberStar;
	
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

	public List<QSLMemberStar> getQSLMemberStarList() {
		return qSLMemberStarList;
	}

	public void setQSLMemberStarList(List<QSLMemberStar> qSLMemberStarList) {
		this.qSLMemberStarList = qSLMemberStarList;
	}

	public QSLMemberStarAction() {
	}

	@Autowired
	public QSLMemberStarAction(QSLMemberStarService qSLMemberStarService) {
		this.qSLMemberStarService = qSLMemberStarService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/company/qSLMemberStar/add.jsp"),
			@Result(name = "input", location = "/company/qSLMemberStar/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/company/qSLMemberStar/list.jsp"),
			@Result(name = "input", location = "/company/qSLMemberStar/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.qSLMemberStarList = qSLMemberStarService.findAllQSLMemberStarWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/company/qSLMemberStar/list.jsp"),
			@Result(name = "input", location = "/company/qSLMemberStar/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.qSLMemberStarList = qSLMemberStarService.findAllQSLMemberStarBySearchWithPage(qSLMemberStar);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/company/qSLMemberStar/edit.jsp"),
			@Result(name = "input", location = "/company/qSLMemberStar/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLMemberStar.getId();
		}
		qSLMemberStar = this.qSLMemberStarService.getQSLMemberStarById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/company/qSLMemberStar/view.jsp"),
			@Result(name = "input", location = "/company/qSLMemberStar/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLMemberStar.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLMemberStar.getId();
		}
		qSLMemberStar = this.qSLMemberStarService.getQSLMemberStarById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLMemberStar/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			qSLMemberStarService.deleteQSLMemberStarByIds(cids);
		}else{
			qSLMemberStarService.deleteQSLMemberStar(qSLMemberStar);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLMemberStar/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.qSLMemberStarService.saveQSLMemberStar(qSLMemberStar);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLMemberStar/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLMemberStar.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLMemberStar.id", message = "You must enter a value for id(save).")})
	public String update() {
		this.qSLMemberStarService.updateQSLMemberStar(qSLMemberStar);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = qSLMemberStarService.findAllQSLMemberStarByNameWithIdAndName(""+qSLMemberStar.getId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/company/qSLMemberStar/select.jsp"),
			@Result(name = "input", location = "/company/qSLMemberStar/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.qSLMemberStarList = qSLMemberStarService.findAllQSLMemberStarBySearchWithPage(qSLMemberStar);
		return ActionSupport.SUCCESS;
	}
	/*/company/qSLMemberStar/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/company/qSLMemberStar/selectedJson.jsp"),
			@Result(name = "input", location = "/company/qSLMemberStar/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = qSLMemberStar.getId();
		}
		//this.qSLMemberStarList = qSLMemberStarService.findAllQSLMemberStarByIds(cids);
		this.json = qSLMemberStarService.findAllQSLMemberStarByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<QSLMemberStar> getQSLMemberStars() {
		return qSLMemberStarList;
	}

	public QSLMemberStar getQSLMemberStar() {
		return qSLMemberStar;
	}

	public void setQSLMemberStar(QSLMemberStar qSLMemberStar) {
		this.qSLMemberStar = qSLMemberStar;
	}

}

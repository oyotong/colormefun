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

import shop.company.entity.QSLVenues;
import shop.company.service.QSLVenuesService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/company/qSLVenues") })
@ParentPackage("my-default")
public class QSLVenuesAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLVenuesService qSLVenuesService;
	private List<QSLVenues> qSLVenuesList;
	private QSLVenues qSLVenues;
	
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

	public List<QSLVenues> getQSLVenuesList() {
		return qSLVenuesList;
	}

	public void setQSLVenuesList(List<QSLVenues> qSLVenuesList) {
		this.qSLVenuesList = qSLVenuesList;
	}

	public QSLVenuesAction() {
	}

	@Autowired
	public QSLVenuesAction(QSLVenuesService qSLVenuesService) {
		this.qSLVenuesService = qSLVenuesService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/company/qSLVenues/add.jsp"),
			@Result(name = "input", location = "/company/qSLVenues/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/company/qSLVenues/list.jsp"),
			@Result(name = "input", location = "/company/qSLVenues/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.qSLVenuesList = qSLVenuesService.findAllQSLVenuesWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/company/qSLVenues/list.jsp"),
			@Result(name = "input", location = "/company/qSLVenues/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.qSLVenuesList = qSLVenuesService.findAllQSLVenuesBySearchWithPage(qSLVenues);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/company/qSLVenues/edit.jsp"),
			@Result(name = "input", location = "/company/qSLVenues/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLVenues.getId();
		}
		qSLVenues = this.qSLVenuesService.getQSLVenuesById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/company/qSLVenues/view.jsp"),
			@Result(name = "input", location = "/company/qSLVenues/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLVenues.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLVenues.getId();
		}
		qSLVenues = this.qSLVenuesService.getQSLVenuesById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLVenues/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			qSLVenuesService.deleteQSLVenuesByIds(cids);
		}else{
			qSLVenuesService.deleteQSLVenues(qSLVenues);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLVenues/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.qSLVenuesService.saveQSLVenues(qSLVenues);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLVenues/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLVenues.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLVenues.id", message = "You must enter a value for id(save).")})
	public String update() {
		this.qSLVenuesService.updateQSLVenues(qSLVenues);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = qSLVenuesService.findAllQSLVenuesByNameWithIdAndName(""+qSLVenues.getId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/company/qSLVenues/select.jsp"),
			@Result(name = "input", location = "/company/qSLVenues/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.qSLVenuesList = qSLVenuesService.findAllQSLVenuesBySearchWithPage(qSLVenues);
		return ActionSupport.SUCCESS;
	}
	/*/company/qSLVenues/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/company/qSLVenues/selectedJson.jsp"),
			@Result(name = "input", location = "/company/qSLVenues/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = qSLVenues.getId();
		}
		//this.qSLVenuesList = qSLVenuesService.findAllQSLVenuesByIds(cids);
		this.json = qSLVenuesService.findAllQSLVenuesByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<QSLVenues> getQSLVenuess() {
		return qSLVenuesList;
	}

	public QSLVenues getQSLVenues() {
		return qSLVenues;
	}

	public void setQSLVenues(QSLVenues qSLVenues) {
		this.qSLVenues = qSLVenues;
	}

}

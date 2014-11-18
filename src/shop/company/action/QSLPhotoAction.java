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

import shop.company.entity.QSLPhoto;
import shop.company.service.QSLPhotoService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/company/qSLPhoto") })
@ParentPackage("my-default")
public class QSLPhotoAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLPhotoService qSLPhotoService;
	private List<QSLPhoto> qSLPhotoList;
	private QSLPhoto qSLPhoto;
	
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

	public List<QSLPhoto> getQSLPhotoList() {
		return qSLPhotoList;
	}

	public void setQSLPhotoList(List<QSLPhoto> qSLPhotoList) {
		this.qSLPhotoList = qSLPhotoList;
	}

	public QSLPhotoAction() {
	}

	@Autowired
	public QSLPhotoAction(QSLPhotoService qSLPhotoService) {
		this.qSLPhotoService = qSLPhotoService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/company/qSLPhoto/add.jsp"),
			@Result(name = "input", location = "/company/qSLPhoto/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/company/qSLPhoto/list.jsp"),
			@Result(name = "input", location = "/company/qSLPhoto/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.qSLPhotoList = qSLPhotoService.findAllQSLPhotoWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/company/qSLPhoto/list.jsp"),
			@Result(name = "input", location = "/company/qSLPhoto/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.qSLPhotoList = qSLPhotoService.findAllQSLPhotoBySearchWithPage(qSLPhoto);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/company/qSLPhoto/edit.jsp"),
			@Result(name = "input", location = "/company/qSLPhoto/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLPhoto.getId();
		}
		qSLPhoto = this.qSLPhotoService.getQSLPhotoById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/company/qSLPhoto/view.jsp"),
			@Result(name = "input", location = "/company/qSLPhoto/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLPhoto.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLPhoto.getId();
		}
		qSLPhoto = this.qSLPhotoService.getQSLPhotoById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLPhoto/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			qSLPhotoService.deleteQSLPhotoByIds(cids);
		}else{
			qSLPhotoService.deleteQSLPhoto(qSLPhoto);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLPhoto/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.qSLPhotoService.saveQSLPhoto(qSLPhoto);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLPhoto/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLPhoto.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLPhoto.id", message = "You must enter a value for id(save).")})
	public String update() {
		this.qSLPhotoService.updateQSLPhoto(qSLPhoto);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = qSLPhotoService.findAllQSLPhotoByNameWithIdAndName(""+qSLPhoto.getId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/company/qSLPhoto/select.jsp"),
			@Result(name = "input", location = "/company/qSLPhoto/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.qSLPhotoList = qSLPhotoService.findAllQSLPhotoBySearchWithPage(qSLPhoto);
		return ActionSupport.SUCCESS;
	}
	/*/company/qSLPhoto/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/company/qSLPhoto/selectedJson.jsp"),
			@Result(name = "input", location = "/company/qSLPhoto/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = qSLPhoto.getId();
		}
		//this.qSLPhotoList = qSLPhotoService.findAllQSLPhotoByIds(cids);
		this.json = qSLPhotoService.findAllQSLPhotoByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<QSLPhoto> getQSLPhotos() {
		return qSLPhotoList;
	}

	public QSLPhoto getQSLPhoto() {
		return qSLPhoto;
	}

	public void setQSLPhoto(QSLPhoto qSLPhoto) {
		this.qSLPhoto = qSLPhoto;
	}

}

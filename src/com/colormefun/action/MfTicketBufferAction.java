package com.colormefun.action;

import java.io.Serializable;
import java.util.List;

import com.colormefun.entity.MfTicketBufferPK;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfTicketBuffer;
import com.colormefun.service.MfTicketBufferService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfTicketBuffer") })
@ParentPackage("my-default")
public class MfTicketBufferAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfTicketBufferService mfTicketBufferService;
	private List<MfTicketBuffer> mfTicketBufferList;
	private MfTicketBuffer mfTicketBuffer;
	
	private MfTicketBufferPK[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(MfTicketBufferPK[] cids){
		this.cids = cids;
	}
	
	public MfTicketBufferPK[] getCids(){
		return this.cids;
	}

	public List<MfTicketBuffer> getMfTicketBufferList() {
		return mfTicketBufferList;
	}

	public void setMfTicketBufferList(List<MfTicketBuffer> mfTicketBufferList) {
		this.mfTicketBufferList = mfTicketBufferList;
	}

	public MfTicketBufferAction() {
	}

	@Autowired
	public MfTicketBufferAction(MfTicketBufferService mfTicketBufferService) {
		this.mfTicketBufferService = mfTicketBufferService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfTicketBuffer/add.jsp"),
			@Result(name = "input", location = "/admin/mfTicketBuffer/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfTicketBuffer/list.jsp"),
			@Result(name = "input", location = "/admin/mfTicketBuffer/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfTicketBufferList = mfTicketBufferService.findAllMfTicketBufferWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfTicketBuffer/list.jsp"),
			@Result(name = "input", location = "/admin/mfTicketBuffer/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfTicketBufferList = mfTicketBufferService.findAllMfTicketBufferBySearchWithPage(mfTicketBuffer);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfTicketBuffer/edit.jsp"),
			@Result(name = "input", location = "/admin/mfTicketBuffer/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfTicketBuffer.getIndexNo();
		}
		mfTicketBuffer = this.mfTicketBufferService.getMfTicketBufferById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfTicketBuffer/view.jsp"),
			@Result(name = "input", location = "/admin/mfTicketBuffer/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfTicketBuffer.indexNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfTicketBuffer.getIndexNo();
		}
		mfTicketBuffer = this.mfTicketBufferService.getMfTicketBufferById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfTicketBuffer/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfTicketBufferService.deleteMfTicketBufferByIds(cids);
		}else{
			mfTicketBufferService.deleteMfTicketBuffer(mfTicketBuffer);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfTicketBuffer/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfTicketBufferService.saveMfTicketBuffer(mfTicketBuffer);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfTicketBuffer/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfTicketBuffer.indexNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfTicketBuffer.indexNo", message = "You must enter a value for indexNo(save).")})
	public String update() {
		this.mfTicketBufferService.updateMfTicketBuffer(mfTicketBuffer);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfTicketBufferService.findAllMfTicketBufferByNameWithIdAndName(""+mfTicketBuffer.getIndexNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfTicketBuffer/select.jsp"),
			@Result(name = "input", location = "/admin/mfTicketBuffer/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfTicketBufferList = mfTicketBufferService.findAllMfTicketBufferBySearchWithPage(mfTicketBuffer);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfTicketBuffer/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfTicketBuffer/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfTicketBuffer/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new MfTicketBufferPK[1];
			cids[0] = new MfTicketBufferPK(mfTicketBuffer.getCaseNo(), mfTicketBuffer.getIndexNo());
		}
		//this.mfTicketBufferList = mfTicketBufferService.findAllMfTicketBufferByIds(cids);
		this.json = mfTicketBufferService.findAllMfTicketBufferByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfTicketBuffer> getMfTicketBuffers() {
		return mfTicketBufferList;
	}

	public MfTicketBuffer getMfTicketBuffer() {
		return mfTicketBuffer;
	}

	public void setMfTicketBuffer(MfTicketBuffer mfTicketBuffer) {
		this.mfTicketBuffer = mfTicketBuffer;
	}

}

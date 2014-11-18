package com.colormefun.action;

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

import com.colormefun.entity.MfOrderDetail;
import com.colormefun.service.MfOrderDetailService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfOrderDetail") })
@ParentPackage("my-default")
public class MfOrderDetailAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfOrderDetailService mfOrderDetailService;
	private List<MfOrderDetail> mfOrderDetailList;
	private MfOrderDetail mfOrderDetail;
	
	private String[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(String[] cids){
		this.cids = cids;
	}
	
	public String[] getCids(){
		return this.cids;
	}

	public List<MfOrderDetail> getMfOrderDetailList() {
		return mfOrderDetailList;
	}

	public void setMfOrderDetailList(List<MfOrderDetail> mfOrderDetailList) {
		this.mfOrderDetailList = mfOrderDetailList;
	}

	public MfOrderDetailAction() {
	}

	@Autowired
	public MfOrderDetailAction(MfOrderDetailService mfOrderDetailService) {
		this.mfOrderDetailService = mfOrderDetailService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfOrderDetail/add.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetail/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfOrderDetail/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfOrderDetailList = mfOrderDetailService.findAllMfOrderDetailWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfOrderDetail/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfOrderDetailList = mfOrderDetailService.findAllMfOrderDetailBySearchWithPage(mfOrderDetail);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfOrderDetail/edit.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetail/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderDetail.getOrderNo();
		}
		mfOrderDetail = this.mfOrderDetailService.getMfOrderDetailById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfOrderDetail/view.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetail/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderDetail.orderNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderDetail.getOrderNo();
		}
		mfOrderDetail = this.mfOrderDetailService.getMfOrderDetailById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfOrderDetailService.deleteMfOrderDetailByIds(cids);
		}else{
			mfOrderDetailService.deleteMfOrderDetail(mfOrderDetail);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderDetail/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfOrderDetailService.saveMfOrderDetail(mfOrderDetail);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderDetail/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderDetail.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderDetail.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfOrderDetailService.updateMfOrderDetail(mfOrderDetail);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfOrderDetailService.findAllMfOrderDetailByNameWithIdAndName(""+mfOrderDetail.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfOrderDetail/select.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetail/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfOrderDetailList = mfOrderDetailService.findAllMfOrderDetailBySearchWithPage(mfOrderDetail);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfOrderDetail/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfOrderDetail/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetail/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = mfOrderDetail.getOrderNo();
		}
		//this.mfOrderDetailList = mfOrderDetailService.findAllMfOrderDetailByIds(cids);
		this.json = mfOrderDetailService.findAllMfOrderDetailByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfOrderDetail> getMfOrderDetails() {
		return mfOrderDetailList;
	}

	public MfOrderDetail getMfOrderDetail() {
		return mfOrderDetail;
	}

	public void setMfOrderDetail(MfOrderDetail mfOrderDetail) {
		this.mfOrderDetail = mfOrderDetail;
	}

}

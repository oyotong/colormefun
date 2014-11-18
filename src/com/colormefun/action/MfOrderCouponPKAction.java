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

import com.colormefun.entity.MfOrderCouponPK;
import com.colormefun.service.MfOrderCouponPKService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfOrderCouponPK") })
@ParentPackage("my-default")
public class MfOrderCouponPKAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfOrderCouponPKService mfOrderCouponPKService;
	private List<MfOrderCouponPK> mfOrderCouponPKList;
	private MfOrderCouponPK mfOrderCouponPK;
	
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

	public List<MfOrderCouponPK> getMfOrderCouponPKList() {
		return mfOrderCouponPKList;
	}

	public void setMfOrderCouponPKList(List<MfOrderCouponPK> mfOrderCouponPKList) {
		this.mfOrderCouponPKList = mfOrderCouponPKList;
	}

	public MfOrderCouponPKAction() {
	}

	@Autowired
	public MfOrderCouponPKAction(MfOrderCouponPKService mfOrderCouponPKService) {
		this.mfOrderCouponPKService = mfOrderCouponPKService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfOrderCouponPK/add.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfOrderCouponPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfOrderCouponPKList = mfOrderCouponPKService.findAllMfOrderCouponPKWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfOrderCouponPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfOrderCouponPKList = mfOrderCouponPKService.findAllMfOrderCouponPKBySearchWithPage(mfOrderCouponPK);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfOrderCouponPK/edit.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderCouponPK.getOrderNo();
		}
		mfOrderCouponPK = this.mfOrderCouponPKService.getMfOrderCouponPKById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfOrderCouponPK/view.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderCouponPK.orderNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderCouponPK.getOrderNo();
		}
		mfOrderCouponPK = this.mfOrderCouponPKService.getMfOrderCouponPKById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfOrderCouponPKService.deleteMfOrderCouponPKByIds(cids);
		}else{
			mfOrderCouponPKService.deleteMfOrderCouponPK(mfOrderCouponPK);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfOrderCouponPKService.saveMfOrderCouponPK(mfOrderCouponPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderCouponPK.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderCouponPK.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfOrderCouponPKService.updateMfOrderCouponPK(mfOrderCouponPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfOrderCouponPKService.findAllMfOrderCouponPKByNameWithIdAndName(""+mfOrderCouponPK.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfOrderCouponPK/select.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfOrderCouponPKList = mfOrderCouponPKService.findAllMfOrderCouponPKBySearchWithPage(mfOrderCouponPK);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfOrderCouponPK/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfOrderCouponPK/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCouponPK/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = mfOrderCouponPK.getOrderNo();
		}
		//this.mfOrderCouponPKList = mfOrderCouponPKService.findAllMfOrderCouponPKByIds(cids);
		this.json = mfOrderCouponPKService.findAllMfOrderCouponPKByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfOrderCouponPK> getMfOrderCouponPKs() {
		return mfOrderCouponPKList;
	}

	public MfOrderCouponPK getMfOrderCouponPK() {
		return mfOrderCouponPK;
	}

	public void setMfOrderCouponPK(MfOrderCouponPK mfOrderCouponPK) {
		this.mfOrderCouponPK = mfOrderCouponPK;
	}

}

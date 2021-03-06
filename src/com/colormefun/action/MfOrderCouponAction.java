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

import com.colormefun.entity.MfOrderCoupon;
import com.colormefun.service.MfOrderCouponService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfOrderCoupon") })
@ParentPackage("my-default")
public class MfOrderCouponAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfOrderCouponService mfOrderCouponService;
	private List<MfOrderCoupon> mfOrderCouponList;
	private MfOrderCoupon mfOrderCoupon;
	
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

	public List<MfOrderCoupon> getMfOrderCouponList() {
		return mfOrderCouponList;
	}

	public void setMfOrderCouponList(List<MfOrderCoupon> mfOrderCouponList) {
		this.mfOrderCouponList = mfOrderCouponList;
	}

	public MfOrderCouponAction() {
	}

	@Autowired
	public MfOrderCouponAction(MfOrderCouponService mfOrderCouponService) {
		this.mfOrderCouponService = mfOrderCouponService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfOrderCoupon/add.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCoupon/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfOrderCoupon/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCoupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfOrderCouponList = mfOrderCouponService.findAllMfOrderCouponWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfOrderCoupon/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCoupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfOrderCouponList = mfOrderCouponService.findAllMfOrderCouponBySearchWithPage(mfOrderCoupon);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfOrderCoupon/edit.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCoupon/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderCoupon.getOrderNo();
		}
		mfOrderCoupon = this.mfOrderCouponService.getMfOrderCouponById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfOrderCoupon/view.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCoupon/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderCoupon.orderNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderCoupon.getOrderNo();
		}
		mfOrderCoupon = this.mfOrderCouponService.getMfOrderCouponById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderCoupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfOrderCouponService.deleteMfOrderCouponByIds(cids);
		}else{
			mfOrderCouponService.deleteMfOrderCoupon(mfOrderCoupon);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderCoupon/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfOrderCouponService.saveMfOrderCoupon(mfOrderCoupon);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderCoupon/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderCoupon.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderCoupon.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfOrderCouponService.updateMfOrderCoupon(mfOrderCoupon);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfOrderCouponService.findAllMfOrderCouponByNameWithIdAndName(""+mfOrderCoupon.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfOrderCoupon/select.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCoupon/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfOrderCouponList = mfOrderCouponService.findAllMfOrderCouponBySearchWithPage(mfOrderCoupon);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfOrderCoupon/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfOrderCoupon/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfOrderCoupon/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = mfOrderCoupon.getOrderNo();
		}
		//this.mfOrderCouponList = mfOrderCouponService.findAllMfOrderCouponByIds(cids);
		this.json = mfOrderCouponService.findAllMfOrderCouponByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfOrderCoupon> getMfOrderCoupons() {
		return mfOrderCouponList;
	}

	public MfOrderCoupon getMfOrderCoupon() {
		return mfOrderCoupon;
	}

	public void setMfOrderCoupon(MfOrderCoupon mfOrderCoupon) {
		this.mfOrderCoupon = mfOrderCoupon;
	}

}

package com.colormefun.action;

import java.io.Serializable;
import java.util.List;

import com.colormefun.entity.MfCartCouponPK;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfCartCoupon;
import com.colormefun.service.MfCartCouponService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfCartCoupon") })
@ParentPackage("my-default")
public class MfCartCouponAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCartCouponService mfCartCouponService;
	private List<MfCartCoupon> mfCartCouponList;
	private MfCartCoupon mfCartCoupon;
	
	private MfCartCouponPK[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(MfCartCouponPK[] cids){
		this.cids = cids;
	}
	
	public MfCartCouponPK[] getCids(){
		return this.cids;
	}

	public List<MfCartCoupon> getMfCartCouponList() {
		return mfCartCouponList;
	}

	public void setMfCartCouponList(List<MfCartCoupon> mfCartCouponList) {
		this.mfCartCouponList = mfCartCouponList;
	}

	public MfCartCouponAction() {
	}

	@Autowired
	public MfCartCouponAction(MfCartCouponService mfCartCouponService) {
		this.mfCartCouponService = mfCartCouponService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfCartCoupon/add.jsp"),
			@Result(name = "input", location = "/admin/mfCartCoupon/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfCartCoupon/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartCoupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfCartCouponList = mfCartCouponService.findAllMfCartCouponWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfCartCoupon/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartCoupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfCartCouponList = mfCartCouponService.findAllMfCartCouponBySearchWithPage(mfCartCoupon);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfCartCoupon/edit.jsp"),
			@Result(name = "input", location = "/admin/mfCartCoupon/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartCoupon.getCouponId();
		}
		mfCartCoupon = this.mfCartCouponService.getMfCartCouponById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfCartCoupon/view.jsp"),
			@Result(name = "input", location = "/admin/mfCartCoupon/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartCoupon.couponId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartCoupon.getCouponId();
		}
		mfCartCoupon = this.mfCartCouponService.getMfCartCouponById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartCoupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfCartCouponService.deleteMfCartCouponByIds(cids);
		}else{
			mfCartCouponService.deleteMfCartCoupon(mfCartCoupon);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartCoupon/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfCartCouponService.saveMfCartCoupon(mfCartCoupon);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartCoupon/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartCoupon.couponId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartCoupon.couponId", message = "You must enter a value for couponId(save).")})
	public String update() {
		this.mfCartCouponService.updateMfCartCoupon(mfCartCoupon);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfCartCouponService.findAllMfCartCouponByNameWithIdAndName(""+mfCartCoupon.getCouponId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfCartCoupon/select.jsp"),
			@Result(name = "input", location = "/admin/mfCartCoupon/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfCartCouponList = mfCartCouponService.findAllMfCartCouponBySearchWithPage(mfCartCoupon);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfCartCoupon/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfCartCoupon/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfCartCoupon/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new MfCartCouponPK[1];
			cids[0] = new MfCartCouponPK(mfCartCoupon.getCartNo(), mfCartCoupon.getCouponId());
		}
		//this.mfCartCouponList = mfCartCouponService.findAllMfCartCouponByIds(cids);
		this.json = mfCartCouponService.findAllMfCartCouponByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfCartCoupon> getMfCartCoupons() {
		return mfCartCouponList;
	}

	public MfCartCoupon getMfCartCoupon() {
		return mfCartCoupon;
	}

	public void setMfCartCoupon(MfCartCoupon mfCartCoupon) {
		this.mfCartCoupon = mfCartCoupon;
	}

}

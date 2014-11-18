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

import com.colormefun.entity.MfCartCouponPK;
import com.colormefun.service.MfCartCouponPKService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfCartCouponPK") })
@ParentPackage("my-default")
public class MfCartCouponPKAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCartCouponPKService mfCartCouponPKService;
	private List<MfCartCouponPK> mfCartCouponPKList;
	private MfCartCouponPK mfCartCouponPK;
	
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

	public List<MfCartCouponPK> getMfCartCouponPKList() {
		return mfCartCouponPKList;
	}

	public void setMfCartCouponPKList(List<MfCartCouponPK> mfCartCouponPKList) {
		this.mfCartCouponPKList = mfCartCouponPKList;
	}

	public MfCartCouponPKAction() {
	}

	@Autowired
	public MfCartCouponPKAction(MfCartCouponPKService mfCartCouponPKService) {
		this.mfCartCouponPKService = mfCartCouponPKService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfCartCouponPK/add.jsp"),
			@Result(name = "input", location = "/admin/mfCartCouponPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfCartCouponPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartCouponPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfCartCouponPKList = mfCartCouponPKService.findAllMfCartCouponPKWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfCartCouponPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartCouponPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfCartCouponPKList = mfCartCouponPKService.findAllMfCartCouponPKBySearchWithPage(mfCartCouponPK);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfCartCouponPK/edit.jsp"),
			@Result(name = "input", location = "/admin/mfCartCouponPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartCouponPK.getCouponId();
		}
		mfCartCouponPK = this.mfCartCouponPKService.getMfCartCouponPKById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfCartCouponPK/view.jsp"),
			@Result(name = "input", location = "/admin/mfCartCouponPK/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartCouponPK.couponId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartCouponPK.getCouponId();
		}
		mfCartCouponPK = this.mfCartCouponPKService.getMfCartCouponPKById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartCouponPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfCartCouponPKService.deleteMfCartCouponPKByIds(cids);
		}else{
			mfCartCouponPKService.deleteMfCartCouponPK(mfCartCouponPK);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartCouponPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfCartCouponPKService.saveMfCartCouponPK(mfCartCouponPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartCouponPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartCouponPK.couponId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartCouponPK.couponId", message = "You must enter a value for couponId(save).")})
	public String update() {
		this.mfCartCouponPKService.updateMfCartCouponPK(mfCartCouponPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfCartCouponPKService.findAllMfCartCouponPKByNameWithIdAndName(""+mfCartCouponPK.getCouponId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfCartCouponPK/select.jsp"),
			@Result(name = "input", location = "/admin/mfCartCouponPK/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfCartCouponPKList = mfCartCouponPKService.findAllMfCartCouponPKBySearchWithPage(mfCartCouponPK);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfCartCouponPK/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfCartCouponPK/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfCartCouponPK/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = mfCartCouponPK.getCouponId();
		}
		//this.mfCartCouponPKList = mfCartCouponPKService.findAllMfCartCouponPKByIds(cids);
		this.json = mfCartCouponPKService.findAllMfCartCouponPKByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfCartCouponPK> getMfCartCouponPKs() {
		return mfCartCouponPKList;
	}

	public MfCartCouponPK getMfCartCouponPK() {
		return mfCartCouponPK;
	}

	public void setMfCartCouponPK(MfCartCouponPK mfCartCouponPK) {
		this.mfCartCouponPK = mfCartCouponPK;
	}

}

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

import com.colormefun.entity.MfOrderDetailPK;
import com.colormefun.service.MfOrderDetailPKService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfOrderDetailPK") })
@ParentPackage("my-default")
public class MfOrderDetailPKAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfOrderDetailPKService mfOrderDetailPKService;
	private List<MfOrderDetailPK> mfOrderDetailPKList;
	private MfOrderDetailPK mfOrderDetailPK;
	
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

	public List<MfOrderDetailPK> getMfOrderDetailPKList() {
		return mfOrderDetailPKList;
	}

	public void setMfOrderDetailPKList(List<MfOrderDetailPK> mfOrderDetailPKList) {
		this.mfOrderDetailPKList = mfOrderDetailPKList;
	}

	public MfOrderDetailPKAction() {
	}

	@Autowired
	public MfOrderDetailPKAction(MfOrderDetailPKService mfOrderDetailPKService) {
		this.mfOrderDetailPKService = mfOrderDetailPKService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfOrderDetailPK/add.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfOrderDetailPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfOrderDetailPKList = mfOrderDetailPKService.findAllMfOrderDetailPKWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfOrderDetailPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfOrderDetailPKList = mfOrderDetailPKService.findAllMfOrderDetailPKBySearchWithPage(mfOrderDetailPK);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfOrderDetailPK/edit.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderDetailPK.getOrderNo();
		}
		mfOrderDetailPK = this.mfOrderDetailPKService.getMfOrderDetailPKById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfOrderDetailPK/view.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderDetailPK.orderNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfOrderDetailPK.getOrderNo();
		}
		mfOrderDetailPK = this.mfOrderDetailPKService.getMfOrderDetailPKById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfOrderDetailPKService.deleteMfOrderDetailPKByIds(cids);
		}else{
			mfOrderDetailPKService.deleteMfOrderDetailPK(mfOrderDetailPK);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfOrderDetailPKService.saveMfOrderDetailPK(mfOrderDetailPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderDetailPK.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfOrderDetailPK.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfOrderDetailPKService.updateMfOrderDetailPK(mfOrderDetailPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfOrderDetailPKService.findAllMfOrderDetailPKByNameWithIdAndName(""+mfOrderDetailPK.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfOrderDetailPK/select.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfOrderDetailPKList = mfOrderDetailPKService.findAllMfOrderDetailPKBySearchWithPage(mfOrderDetailPK);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfOrderDetailPK/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfOrderDetailPK/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfOrderDetailPK/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = mfOrderDetailPK.getOrderNo();
		}
		//this.mfOrderDetailPKList = mfOrderDetailPKService.findAllMfOrderDetailPKByIds(cids);
		this.json = mfOrderDetailPKService.findAllMfOrderDetailPKByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfOrderDetailPK> getMfOrderDetailPKs() {
		return mfOrderDetailPKList;
	}

	public MfOrderDetailPK getMfOrderDetailPK() {
		return mfOrderDetailPK;
	}

	public void setMfOrderDetailPK(MfOrderDetailPK mfOrderDetailPK) {
		this.mfOrderDetailPK = mfOrderDetailPK;
	}

}

package com.colormefun.action;

import java.io.Serializable;
import java.util.List;

import com.colormefun.entity.MfCartDetailPK;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfCartDetail;
import com.colormefun.service.MfCartDetailService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfCartDetail") })
@ParentPackage("my-default")
public class MfCartDetailAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCartDetailService mfCartDetailService;
	private List<MfCartDetail> mfCartDetailList;
	private MfCartDetail mfCartDetail;
	
	private MfCartDetailPK[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(MfCartDetailPK[] cids){
		this.cids = cids;
	}
	
	public MfCartDetailPK[] getCids(){
		return this.cids;
	}

	public List<MfCartDetail> getMfCartDetailList() {
		return mfCartDetailList;
	}

	public void setMfCartDetailList(List<MfCartDetail> mfCartDetailList) {
		this.mfCartDetailList = mfCartDetailList;
	}

	public MfCartDetailAction() {
	}

	@Autowired
	public MfCartDetailAction(MfCartDetailService mfCartDetailService) {
		this.mfCartDetailService = mfCartDetailService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfCartDetail/add.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetail/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfCartDetail/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfCartDetailList = mfCartDetailService.findAllMfCartDetailWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfCartDetail/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfCartDetailList = mfCartDetailService.findAllMfCartDetailBySearchWithPage(mfCartDetail);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfCartDetail/edit.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetail/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartDetail.getCaseNo();
		}
		mfCartDetail = this.mfCartDetailService.getMfCartDetailById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfCartDetail/view.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetail/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartDetail.caseNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartDetail.getCaseNo();
		}
		mfCartDetail = this.mfCartDetailService.getMfCartDetailById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfCartDetailService.deleteMfCartDetailByIds(cids);
		}else{
			mfCartDetailService.deleteMfCartDetail(mfCartDetail);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartDetail/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfCartDetailService.saveMfCartDetail(mfCartDetail);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartDetail/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartDetail.caseNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartDetail.caseNo", message = "You must enter a value for caseNo(save).")})
	public String update() {
		this.mfCartDetailService.updateMfCartDetail(mfCartDetail);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfCartDetailService.findAllMfCartDetailByNameWithIdAndName(""+mfCartDetail.getCaseNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfCartDetail/select.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetail/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfCartDetailList = mfCartDetailService.findAllMfCartDetailBySearchWithPage(mfCartDetail);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfCartDetail/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfCartDetail/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetail/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new MfCartDetailPK[1];
			cids[0] = new MfCartDetailPK(mfCartDetail.getCartNo(),mfCartDetail.getCaseNo());
		}
		//this.mfCartDetailList = mfCartDetailService.findAllMfCartDetailByIds(cids);
		this.json = mfCartDetailService.findAllMfCartDetailByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfCartDetail> getMfCartDetails() {
		return mfCartDetailList;
	}

	public MfCartDetail getMfCartDetail() {
		return mfCartDetail;
	}

	public void setMfCartDetail(MfCartDetail mfCartDetail) {
		this.mfCartDetail = mfCartDetail;
	}

}

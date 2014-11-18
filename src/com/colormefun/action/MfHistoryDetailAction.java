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

import com.colormefun.entity.MfHistoryDetail;
import com.colormefun.service.MfHistoryDetailService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfHistoryDetail") })
@ParentPackage("my-default")
public class MfHistoryDetailAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfHistoryDetailService mfHistoryDetailService;
	private List<MfHistoryDetail> mfHistoryDetailList;
	private MfHistoryDetail mfHistoryDetail;
	
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

	public List<MfHistoryDetail> getMfHistoryDetailList() {
		return mfHistoryDetailList;
	}

	public void setMfHistoryDetailList(List<MfHistoryDetail> mfHistoryDetailList) {
		this.mfHistoryDetailList = mfHistoryDetailList;
	}

	public MfHistoryDetailAction() {
	}

	@Autowired
	public MfHistoryDetailAction(MfHistoryDetailService mfHistoryDetailService) {
		this.mfHistoryDetailService = mfHistoryDetailService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfHistoryDetail/add.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetail/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetail/list.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfHistoryDetailList = mfHistoryDetailService.findAllMfHistoryDetailWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetail/list.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfHistoryDetailList = mfHistoryDetailService.findAllMfHistoryDetailBySearchWithPage(mfHistoryDetail);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetail/edit.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetail/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfHistoryDetail.getOrderNo();
		}
		mfHistoryDetail = this.mfHistoryDetailService.getMfHistoryDetailById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetail/view.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetail/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistoryDetail.orderNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfHistoryDetail.getOrderNo();
		}
		mfHistoryDetail = this.mfHistoryDetailService.getMfHistoryDetailById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistoryDetail/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfHistoryDetailService.deleteMfHistoryDetailByIds(cids);
		}else{
			mfHistoryDetailService.deleteMfHistoryDetail(mfHistoryDetail);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistoryDetail/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfHistoryDetailService.saveMfHistoryDetail(mfHistoryDetail);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistoryDetail/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistoryDetail.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistoryDetail.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfHistoryDetailService.updateMfHistoryDetail(mfHistoryDetail);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfHistoryDetailService.findAllMfHistoryDetailByNameWithIdAndName(""+mfHistoryDetail.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetail/select.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetail/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfHistoryDetailList = mfHistoryDetailService.findAllMfHistoryDetailBySearchWithPage(mfHistoryDetail);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfHistoryDetail/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetail/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetail/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = mfHistoryDetail.getOrderNo();
		}
		//this.mfHistoryDetailList = mfHistoryDetailService.findAllMfHistoryDetailByIds(cids);
		this.json = mfHistoryDetailService.findAllMfHistoryDetailByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfHistoryDetail> getMfHistoryDetails() {
		return mfHistoryDetailList;
	}

	public MfHistoryDetail getMfHistoryDetail() {
		return mfHistoryDetail;
	}

	public void setMfHistoryDetail(MfHistoryDetail mfHistoryDetail) {
		this.mfHistoryDetail = mfHistoryDetail;
	}

}

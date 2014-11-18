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

import com.colormefun.entity.MfHistory;
import com.colormefun.service.MfHistoryService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfHistory") })
@ParentPackage("my-default")
public class MfHistoryAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfHistoryService mfHistoryService;
	private List<MfHistory> mfHistoryList;
	private MfHistory mfHistory;
	
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

	public List<MfHistory> getMfHistoryList() {
		return mfHistoryList;
	}

	public void setMfHistoryList(List<MfHistory> mfHistoryList) {
		this.mfHistoryList = mfHistoryList;
	}

	public MfHistoryAction() {
	}

	@Autowired
	public MfHistoryAction(MfHistoryService mfHistoryService) {
		this.mfHistoryService = mfHistoryService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfHistory/add.jsp"),
			@Result(name = "input", location = "/admin/mfHistory/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfHistory/list.jsp"),
			@Result(name = "input", location = "/admin/mfHistory/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfHistoryList = mfHistoryService.findAllMfHistoryWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfHistory/list.jsp"),
			@Result(name = "input", location = "/admin/mfHistory/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfHistoryList = mfHistoryService.findAllMfHistoryBySearchWithPage(mfHistory);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfHistory/edit.jsp"),
			@Result(name = "input", location = "/admin/mfHistory/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfHistory.getOrderNo();
		}
		mfHistory = this.mfHistoryService.getMfHistoryById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfHistory/view.jsp"),
			@Result(name = "input", location = "/admin/mfHistory/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistory.orderNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfHistory.getOrderNo();
		}
		mfHistory = this.mfHistoryService.getMfHistoryById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistory/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfHistoryService.deleteMfHistoryByIds(cids);
		}else{
			mfHistoryService.deleteMfHistory(mfHistory);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistory/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfHistoryService.saveMfHistory(mfHistory);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistory/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistory.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistory.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfHistoryService.updateMfHistory(mfHistory);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfHistoryService.findAllMfHistoryByNameWithIdAndName(""+mfHistory.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfHistory/select.jsp"),
			@Result(name = "input", location = "/admin/mfHistory/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfHistoryList = mfHistoryService.findAllMfHistoryBySearchWithPage(mfHistory);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfHistory/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfHistory/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfHistory/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = mfHistory.getOrderNo();
		}
		//this.mfHistoryList = mfHistoryService.findAllMfHistoryByIds(cids);
		this.json = mfHistoryService.findAllMfHistoryByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfHistory> getMfHistorys() {
		return mfHistoryList;
	}

	public MfHistory getMfHistory() {
		return mfHistory;
	}

	public void setMfHistory(MfHistory mfHistory) {
		this.mfHistory = mfHistory;
	}

}

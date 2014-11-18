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

import com.colormefun.entity.MfCartDetailPK;
import com.colormefun.service.MfCartDetailPKService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfCartDetailPK") })
@ParentPackage("my-default")
public class MfCartDetailPKAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCartDetailPKService mfCartDetailPKService;
	private List<MfCartDetailPK> mfCartDetailPKList;
	private MfCartDetailPK mfCartDetailPK;
	
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

	public List<MfCartDetailPK> getMfCartDetailPKList() {
		return mfCartDetailPKList;
	}

	public void setMfCartDetailPKList(List<MfCartDetailPK> mfCartDetailPKList) {
		this.mfCartDetailPKList = mfCartDetailPKList;
	}

	public MfCartDetailPKAction() {
	}

	@Autowired
	public MfCartDetailPKAction(MfCartDetailPKService mfCartDetailPKService) {
		this.mfCartDetailPKService = mfCartDetailPKService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfCartDetailPK/add.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetailPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfCartDetailPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfCartDetailPKList = mfCartDetailPKService.findAllMfCartDetailPKWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfCartDetailPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfCartDetailPKList = mfCartDetailPKService.findAllMfCartDetailPKBySearchWithPage(mfCartDetailPK);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfCartDetailPK/edit.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetailPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartDetailPK.getCaseNo();
		}
		mfCartDetailPK = this.mfCartDetailPKService.getMfCartDetailPKById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfCartDetailPK/view.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetailPK/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartDetailPK.caseNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCartDetailPK.getCaseNo();
		}
		mfCartDetailPK = this.mfCartDetailPKService.getMfCartDetailPKById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfCartDetailPKService.deleteMfCartDetailPKByIds(cids);
		}else{
			mfCartDetailPKService.deleteMfCartDetailPK(mfCartDetailPK);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartDetailPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfCartDetailPKService.saveMfCartDetailPK(mfCartDetailPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCartDetailPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartDetailPK.caseNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCartDetailPK.caseNo", message = "You must enter a value for caseNo(save).")})
	public String update() {
		this.mfCartDetailPKService.updateMfCartDetailPK(mfCartDetailPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfCartDetailPKService.findAllMfCartDetailPKByNameWithIdAndName(""+mfCartDetailPK.getCaseNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfCartDetailPK/select.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetailPK/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfCartDetailPKList = mfCartDetailPKService.findAllMfCartDetailPKBySearchWithPage(mfCartDetailPK);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfCartDetailPK/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfCartDetailPK/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfCartDetailPK/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = mfCartDetailPK.getCaseNo();
		}
		//this.mfCartDetailPKList = mfCartDetailPKService.findAllMfCartDetailPKByIds(cids);
		this.json = mfCartDetailPKService.findAllMfCartDetailPKByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfCartDetailPK> getMfCartDetailPKs() {
		return mfCartDetailPKList;
	}

	public MfCartDetailPK getMfCartDetailPK() {
		return mfCartDetailPK;
	}

	public void setMfCartDetailPK(MfCartDetailPK mfCartDetailPK) {
		this.mfCartDetailPK = mfCartDetailPK;
	}

}

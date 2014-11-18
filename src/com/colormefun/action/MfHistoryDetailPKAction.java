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

import com.colormefun.entity.MfHistoryDetailPK;
import com.colormefun.service.MfHistoryDetailPKService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfHistoryDetailPK") })
@ParentPackage("my-default")
public class MfHistoryDetailPKAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfHistoryDetailPKService mfHistoryDetailPKService;
	private List<MfHistoryDetailPK> mfHistoryDetailPKList;
	private MfHistoryDetailPK mfHistoryDetailPK;
	
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

	public List<MfHistoryDetailPK> getMfHistoryDetailPKList() {
		return mfHistoryDetailPKList;
	}

	public void setMfHistoryDetailPKList(List<MfHistoryDetailPK> mfHistoryDetailPKList) {
		this.mfHistoryDetailPKList = mfHistoryDetailPKList;
	}

	public MfHistoryDetailPKAction() {
	}

	@Autowired
	public MfHistoryDetailPKAction(MfHistoryDetailPKService mfHistoryDetailPKService) {
		this.mfHistoryDetailPKService = mfHistoryDetailPKService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfHistoryDetailPK/add.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetailPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfHistoryDetailPKList = mfHistoryDetailPKService.findAllMfHistoryDetailPKWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetailPK/list.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfHistoryDetailPKList = mfHistoryDetailPKService.findAllMfHistoryDetailPKBySearchWithPage(mfHistoryDetailPK);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetailPK/edit.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfHistoryDetailPK.getOrderNo();
		}
		mfHistoryDetailPK = this.mfHistoryDetailPKService.getMfHistoryDetailPKById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetailPK/view.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistoryDetailPK.orderNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfHistoryDetailPK.getOrderNo();
		}
		mfHistoryDetailPK = this.mfHistoryDetailPKService.getMfHistoryDetailPKById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfHistoryDetailPKService.deleteMfHistoryDetailPKByIds(cids);
		}else{
			mfHistoryDetailPKService.deleteMfHistoryDetailPK(mfHistoryDetailPK);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfHistoryDetailPKService.saveMfHistoryDetailPK(mfHistoryDetailPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistoryDetailPK.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfHistoryDetailPK.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfHistoryDetailPKService.updateMfHistoryDetailPK(mfHistoryDetailPK);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfHistoryDetailPKService.findAllMfHistoryDetailPKByNameWithIdAndName(""+mfHistoryDetailPK.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetailPK/select.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfHistoryDetailPKList = mfHistoryDetailPKService.findAllMfHistoryDetailPKBySearchWithPage(mfHistoryDetailPK);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfHistoryDetailPK/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfHistoryDetailPK/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfHistoryDetailPK/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = mfHistoryDetailPK.getOrderNo();
		}
		//this.mfHistoryDetailPKList = mfHistoryDetailPKService.findAllMfHistoryDetailPKByIds(cids);
		this.json = mfHistoryDetailPKService.findAllMfHistoryDetailPKByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfHistoryDetailPK> getMfHistoryDetailPKs() {
		return mfHistoryDetailPKList;
	}

	public MfHistoryDetailPK getMfHistoryDetailPK() {
		return mfHistoryDetailPK;
	}

	public void setMfHistoryDetailPK(MfHistoryDetailPK mfHistoryDetailPK) {
		this.mfHistoryDetailPK = mfHistoryDetailPK;
	}

}

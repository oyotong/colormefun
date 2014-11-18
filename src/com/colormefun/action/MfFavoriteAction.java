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

import com.colormefun.entity.MfFavorite;
import com.colormefun.service.MfFavoriteService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfFavorite") })
@ParentPackage("my-default")
public class MfFavoriteAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfFavoriteService mfFavoriteService;
	private List<MfFavorite> mfFavoriteList;
	private MfFavorite mfFavorite;
	
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

	public List<MfFavorite> getMfFavoriteList() {
		return mfFavoriteList;
	}

	public void setMfFavoriteList(List<MfFavorite> mfFavoriteList) {
		this.mfFavoriteList = mfFavoriteList;
	}

	public MfFavoriteAction() {
	}

	@Autowired
	public MfFavoriteAction(MfFavoriteService mfFavoriteService) {
		this.mfFavoriteService = mfFavoriteService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfFavorite/add.jsp"),
			@Result(name = "input", location = "/admin/mfFavorite/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfFavorite/list.jsp"),
			@Result(name = "input", location = "/admin/mfFavorite/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfFavoriteList = mfFavoriteService.findAllMfFavoriteWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfFavorite/list.jsp"),
			@Result(name = "input", location = "/admin/mfFavorite/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfFavoriteList = mfFavoriteService.findAllMfFavoriteBySearchWithPage(mfFavorite);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfFavorite/edit.jsp"),
			@Result(name = "input", location = "/admin/mfFavorite/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfFavorite.getFavoriteId();
		}
		mfFavorite = this.mfFavoriteService.getMfFavoriteById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfFavorite/view.jsp"),
			@Result(name = "input", location = "/admin/mfFavorite/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfFavorite.favoriteId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfFavorite.getFavoriteId();
		}
		mfFavorite = this.mfFavoriteService.getMfFavoriteById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfFavorite/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfFavoriteService.deleteMfFavoriteByIds(cids);
		}else{
			mfFavoriteService.deleteMfFavorite(mfFavorite);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfFavorite/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfFavoriteService.saveMfFavorite(mfFavorite);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfFavorite/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfFavorite.favoriteId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfFavorite.favoriteId", message = "You must enter a value for favoriteId(save).")})
	public String update() {
		this.mfFavoriteService.updateMfFavorite(mfFavorite);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfFavoriteService.findAllMfFavoriteByNameWithIdAndName(""+mfFavorite.getFavoriteId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfFavorite/select.jsp"),
			@Result(name = "input", location = "/admin/mfFavorite/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfFavoriteList = mfFavoriteService.findAllMfFavoriteBySearchWithPage(mfFavorite);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfFavorite/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfFavorite/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfFavorite/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = mfFavorite.getFavoriteId();
		}
		//this.mfFavoriteList = mfFavoriteService.findAllMfFavoriteByIds(cids);
		this.json = mfFavoriteService.findAllMfFavoriteByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfFavorite> getMfFavorites() {
		return mfFavoriteList;
	}

	public MfFavorite getMfFavorite() {
		return mfFavorite;
	}

	public void setMfFavorite(MfFavorite mfFavorite) {
		this.mfFavorite = mfFavorite;
	}

}

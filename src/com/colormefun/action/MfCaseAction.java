package com.colormefun.action;

import java.io.File;
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

import com.colormefun.entity.MfCase;
import com.colormefun.service.MfCaseService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.util.FileUtils;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/mfCase") })
@ParentPackage("my-default")
public class MfCaseAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCaseService mfCaseService;
	private List<MfCase> list;
	private MfCase mfCase;
	
	private Integer[] cids;
	
	private Object json;

    private File image1;
    private String image1FileName;
    private String image1ContentType;
	
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

	public List<MfCase> getList() {
		return list;
	}

	public void setList(List<MfCase> list) {
		this.list = list;
	}

	public MfCaseAction() {
	}

	@Autowired
	public MfCaseAction(MfCaseService mfCaseService) {
		this.mfCaseService = mfCaseService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/mfCase/add.jsp"),
			@Result(name = "input", location = "/admin/mfCase/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/mfCase/list.jsp"),
			@Result(name = "input", location = "/admin/mfCase/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.list = mfCaseService.findAllMfCaseWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/mfCase/list.jsp"),
			@Result(name = "input", location = "/admin/mfCase/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.list = mfCaseService.findAllMfCaseBySearchWithPage(mfCase);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/mfCase/add.jsp"),
			@Result(name = "input", location = "/admin/mfCase/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCase.getCaseNo();
		}
		mfCase = this.mfCaseService.getMfCaseById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/mfCase/view.jsp"),
			@Result(name = "input", location = "/admin/mfCase/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCase.caseNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCase.getCaseNo();
		}
		mfCase = this.mfCaseService.getMfCaseById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCase/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfCaseService.deleteMfCaseByIds(cids);
		}else{
			mfCaseService.deleteMfCase(mfCase);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCase/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
        if (null != image1) {
            mfCase.setPicture(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }
		this.mfCaseService.saveMfCase(mfCase);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/mfCase/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCase.caseNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCase.title", message = "You must enter a value for title(save).")})
	public String update() {
        if (null != image1) {
            mfCase.setPicture(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }
		this.mfCaseService.updateMfCase(mfCase);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfCaseService.findAllMfCaseByNameWithIdAndName("" + mfCase.getTitle());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/mfCase/select.jsp"),
			@Result(name = "input", location = "/admin/mfCase/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.list = mfCaseService.findAllMfCaseBySearchWithPage(mfCase);
		return ActionSupport.SUCCESS;
	}
	/*/admin/mfCase/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/mfCase/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/mfCase/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = mfCase.getCaseNo();
		}
		//this.list = mfCaseService.findAllMfCaseByIds(cids);
		this.json = mfCaseService.findAllMfCaseByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfCase> getMfCases() {
		return list;
	}

	public MfCase getMfCase() {
		return mfCase;
	}

	public void setMfCase(MfCase mfCase) {
		this.mfCase = mfCase;
	}

    public File getImage1() {
        return image1;
    }

    public void setImage1(File image1) {
        this.image1 = image1;
    }

    public String getImage1FileName() {
        return image1FileName;
    }

    public void setImage1FileName(String image1FileName) {
        this.image1FileName = image1FileName;
    }

    public String getImage1ContentType() {
        return image1ContentType;
    }

    public void setImage1ContentType(String image1ContentType) {
        this.image1ContentType = image1ContentType;
    }

}

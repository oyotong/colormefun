package com.colormefun.action;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import freemarker.template.utility.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfUser;
import com.colormefun.service.MfUserService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.util.FileUtils;
import shop.common.util.StringUtils;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/user") })
@ParentPackage("my-default")
public class MfUserAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfUserService mfUserService;
	private List<MfUser> userList;
	private MfUser user;
	
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

	public List<MfUser> getUserList() {
		return userList;
	}

	public void setUserList(List<MfUser> userList) {
		this.userList = userList;
	}

	public MfUserAction() {
	}

	@Autowired
	public MfUserAction(MfUserService mfUserService) {
		this.mfUserService = mfUserService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/user/add.jsp"),
			@Result(name = "input", location = "/admin/user/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/user/list.jsp"),
			@Result(name = "input", location = "/admin/user/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.userList = mfUserService.findAllMfUserWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/user/list.jsp"),
			@Result(name = "input", location = "/admin/user/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.userList = mfUserService.findAllMfUserBySearchWithPage(user);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/user/edit.jsp"),
			@Result(name = "input", location = "/admin/user/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = user.getUserNo();
		}
		user = this.mfUserService.getMfUserById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/user/view.jsp"),
			@Result(name = "input", location = "/admin/user/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "cids", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = user.getUserNo();
		}
		user = this.mfUserService.getMfUserById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/user/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfUserService.deleteMfUserByIds(cids);
		}else{
			mfUserService.deleteMfUser(user);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/user/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
        if(user.getPassword() == null){
            String pwd = StringUtils.getPassword(10);
            user.setPassword2(pwd);
            user.setPassword(StringUtils.toMD5(pwd));
        }
        if (null != image1) {
            user.setPic1(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }
		this.mfUserService.saveMfUser(user);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/user/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.userNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.userNo", message = "You must enter a value for userNo(save).")})
	public String update() {
		this.mfUserService.updateMfUser(user);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfUserService.findAllMfUserByNameWithIdAndName("" + user.getUserNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/user/select.jsp"),
			@Result(name = "input", location = "/admin/user/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.userList = mfUserService.findAllMfUserBySearchWithPage(user);
		return ActionSupport.SUCCESS;
	}
	/*/admin/user/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/user/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/user/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = user.getUserNo();
		}
		//this.userList = mfUserService.findAllMfUserByIds(cids);
		this.json = mfUserService.findAllMfUserByIds(cids);
		return ActionSupport.SUCCESS;
	}

    @Action(value = "resetPwd", results = {
            @Result(name = "success", location = "/admin/user/resetPwd.jsp"),
            @Result(name = "input", location = "/admin/user/resetPwd.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String resetPwd() {
        this.user = mfUserService.resetPwdById(cids[0]);
        return ActionSupport.SUCCESS;
    }

	public List<MfUser> getMfUsers() {
		return userList;
	}

	public MfUser getUser() {
		return user;
	}

	public void setUser(MfUser user) {
		this.user = user;
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

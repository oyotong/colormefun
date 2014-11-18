package com.colormefun.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfVipCase;
import com.colormefun.service.MfVipCaseService;
import shop.common.action.SimpleCaptcha;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.context.ApplicationContext;
import shop.common.entity.JSONMessage;
import shop.common.exception.ApplicationException;
import shop.common.util.EmailSender;
import shop.common.util.StringUtils;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/vipCase") , @Namespace("/user/vipCase") })
@ParentPackage("my-default")
public class MfVipCaseAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfVipCaseService mfVipCaseService;
	private List<MfVipCase> list;
	private MfVipCase mfCase;
	
	private Integer[] cids;
	private String caseType = MfVipCase.CaseType.vip.name();
	private Object json;

    private String validateCode;
	
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

	public List<MfVipCase> getList() {
		return list;
	}

	public void setList(List<MfVipCase> list) {
		this.list = list;
	}

	public MfVipCaseAction() {
	}

	@Autowired
	public MfVipCaseAction(MfVipCaseService mfVipCaseService) {
		this.mfVipCaseService = mfVipCaseService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/vipCase/add.jsp"),
			@Result(name = "input", location = "/admin/vipCase/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/vipCase/list.jsp"),
			@Result(name = "input", location = "/admin/vipCase/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
        if(caseType == null){
            caseType = MfVipCase.CaseType.vip.name();
        }
        if(null == mfCase){
            mfCase = new MfVipCase();
            mfCase.setCaseType(caseType);
        }
		this.list = mfVipCaseService.findAllMfVipCaseBySearchWithPage(mfCase);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/vipCase/list.jsp"),
			@Result(name = "input", location = "/admin/vipCase/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.list = mfVipCaseService.findAllMfVipCaseBySearchWithPage(mfCase);
        caseType = mfCase.getCaseType();
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/vipCase/edit.jsp"),
			@Result(name = "input", location = "/admin/vipCase/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCase.getCaseNo();
		}
		mfCase = this.mfVipCaseService.getMfVipCaseById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/vipCase/view.jsp"),
			@Result(name = "input", location = "/admin/vipCase/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCase.getCaseNo();
		}
		mfCase = this.mfVipCaseService.getMfVipCaseById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/vipCase/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfVipCaseService.deleteMfVipCaseByIds(cids);
		}else{
			mfVipCaseService.deleteMfVipCase(mfCase);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/vipCase/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfVipCaseService.saveMfVipCase(mfCase);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/vipCase/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCase.caseNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCase.companyName", message = "You must enter a value for companyName(save).")})
	public String update() {
		this.mfVipCaseService.updateMfVipCase(mfCase);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfVipCaseService.findAllMfVipCaseByNameWithIdAndName("" + mfCase.getCompanyName());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/vipCase/select.jsp"),
			@Result(name = "input", location = "/admin/vipCase/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.list = mfVipCaseService.findAllMfVipCaseBySearchWithPage(mfCase);
		return ActionSupport.SUCCESS;
	}
	/*/admin/vipCase/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/vipCase/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/vipCase/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = mfCase.getCaseNo();
		}
		//this.list = mfVipCaseService.findAllMfVipCaseByIds(cids);
		this.json = mfVipCaseService.findAllMfVipCaseByIds(cids);
		return ActionSupport.SUCCESS;
	}


    // /////////////////// My Vip case ///////////////////////
    @Action(value = "addVip", results = {
            @Result(name = "success", location = "/user/vip.jsp"),
            @Result(name = "input", location = "/user/vip.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String addVip() throws Exception {
        return SUCCESS;
    }

    @Action(value = "saveMyCase", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String saveMyCase() {
        JSONMessage errMsg = new JSONMessage();

        String captcha = (String) getContext().getSession().getAttribute(
                SimpleCaptcha.NAME);
        if (null == validateCode || !validateCode.equals(captcha)) {
            errMsg.isError = true;
            errMsg.message = ("验证码无效");
            json = errMsg;
            return INPUT;
        }

        if(this.mfCase == null || this.mfCase.getCaseType() == null
                || (!this.mfCase.getCaseType().equals(MfVipCase.CaseType.vip.name())
                && !this.mfCase.getCaseType().equals(MfVipCase.CaseType.ent_vip.name()))){
            errMsg.isError = true;
            errMsg.message = ("错误的活动类型");
            json = errMsg;
            return INPUT;
        }

        try {
            Date now = new Date();
            mfCase.setActive("Y");
            mfCase.setCreatedDate(now);

            String caseKey = StringUtils.toMD5(now.getTime()+"-"+mfCase.getCaseType()+"-"+mfCase.getContactName()+"-"+mfCase.getContactPhone());
            mfCase.setCaseKey(caseKey);

            this.mfVipCaseService.saveMfVipCase(mfCase);
            errMsg.isError = false;
            errMsg.message = "您申请的活动已经成功提交，我们会尽快联系您，谢谢您的支持与合作！";
            this.json = errMsg;

            // TODO: 发送短信 和 邮件
            if(StringUtils.isNotNull(mfCase.getEmail())) {
                Map data = new HashMap<String , Object>();
                data.put("ROOT", ApplicationContext.getContext().getConfiguration("Servlet.Context.ROOT"));
                data.put("caseKey", caseKey);
                data.put("mfCase", mfCase);
                EmailSender.getInstance().send(mfCase.getEmail(), null, null, "您申请的活动已经成功提交，我们会尽快联系您，谢谢您的支持与合作！", "submit_vip_success.ftl", data, null);
            }

            return INPUT;
        }catch (ApplicationException e){
            errMsg.isError = true;
            errMsg.message = e.getMessage();
            this.json = errMsg;
            return INPUT;
        } catch (Exception e) {
            errMsg.isError = true;
            errMsg.message = "系统内部错误";
            this.json = errMsg;
            return INPUT;
        }
    }


	public List<MfVipCase> getMfVipCases() {
		return list;
	}

	public MfVipCase getMfCase() {
		return mfCase;
	}

	public void setMfCase(MfVipCase mfCase) {
		this.mfCase = mfCase;
	}

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}

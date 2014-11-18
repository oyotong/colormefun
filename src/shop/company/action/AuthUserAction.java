package shop.company.action;

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

import shop.common.action.auto.AbstractAction;
import shop.common.exception.ApplicationException;
import shop.company.entity.AuthUser;
import shop.company.entity.SystemOperationLog;
import shop.company.service.AuthRoleService;
import shop.company.service.AuthUserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces({ @Namespace("/admin/system/user") })
@ParentPackage("my-default")
public class AuthUserAction extends AbstractAction implements Preparable,
        Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private AuthUserService authUserService;

    private AuthRoleService authRoleService;
    private List<AuthUser> authUserList;
    private AuthUser authUser;
    // private List<AuthRole> authRoleList;
    private List<SystemOperationLog> systemOperationLogList;
    private SystemOperationLog systemOperationLog;

    private String newUserPassword;
    private String newUserPassword2;
    private String oldUserPassword;

    private String new2UserPassword;
    private String new2UserPassword2;
    private String old2UserPassword;

    private String[] cids;

    private Object json;

    public AuthUserAction() {
    }

    @Autowired
    public AuthUserAction(AuthUserService authUserService,
                          AuthRoleService authRoleService) {
        this.authUserService = authUserService;
        this.authRoleService = authRoleService;

        getContext().getSession().setAttribute("authRoleList",
                authRoleService.findAllAuthRoleWithoutPurview());
    }

    @Action(value = "add", results = { @Result(location = "/admin/system/user/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String add() throws Exception {
        return SUCCESS;
    }

    @Action(value = "list", results = {
            @Result(name = "success", location = "/admin/system/user/list.jsp"),
            @Result(name = "input", location = "/admin/system/user/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String list() {
        this.authUserList = authUserService.findAllAuthUserWithPage();
        return ActionSupport.SUCCESS;
    }


    @Action(value = "search", results = {
            @Result(name = "success", location = "/admin/system/user/list.jsp"),
            @Result(name = "input", location = "/admin/system/user/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String search() {
        this.authUserList = authUserService
                .findAllAuthUserBySearchWithPage(authUser);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "edit", results = {
            @Result(name = "success", location = "/admin/system/user/edit.jsp"),
            @Result(name = "input", location = "/admin/system/user/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    @Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUser.userName", message = "You must select a Id.") })
    public String edit() {
        java.io.Serializable id = null;
        if (null != cids && cids.length > 0) {
            id = cids[0];
        } else {
            id = authUser.getUserName();
        }
        authUser = this.authUserService.getAuthUserById(id);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "view", results = {
            @Result(name = "success", location = "/admin/system/user/view.jsp"),
            @Result(name = "input", location = "/admin/system/user/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    @Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUser.userName", message = "You must select a Id.") })
    public String view() {
        java.io.Serializable id = null;
        if (null != cids && cids.length > 0) {
            id = cids[0];
        } else {
            id = authUser.getUserName();
        }
        authUser = this.authUserService.getAuthUserById(id);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "remove", results = {
            @Result(name = "success", type = "redirectAction", params = {
                    "actionName", "list" }),
            @Result(name = "input", location = "/admin/system/user/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    @Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUser.userName", message = "You must select a Id.") })
    public String remove() {
        if (null != cids && cids.length > 0) {
            authUserService.deleteAuthUserByIds(cids);
        } else {
            authUserService.deleteAuthUser(authUser);
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "save", results = {
            @Result(name = "success", type = "redirectAction", params = {
                    "actionName", "list" }),
            @Result(name = "input", location = "/admin/system/user/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    @Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUser.userRealname", message = "You must enter a value for userRealname(save).") })
    public String save() {
        this.authUserService.saveAuthUser(authUser);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "update", results = {
            @Result(name = "success", type = "redirectAction", params = {
                    "actionName", "list" }),
            @Result(name = "input", location = "/admin/system/user/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    @Validations(requiredFields = {
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUser.userName", message = "You must select a Id."),
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "authUser.userRealname", message = "You must enter a value for userRealname(save).") })
    public String update() {
        this.authUserService.updateAuthUser(authUser);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "searchJson", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String searchJson() {
        json = authUserService.findAllAuthUserByNameWithIdAndName(authUser
                .getUserRealname());
        return ActionSupport.SUCCESS;
    }

    @Action(value = "select", results = {
            @Result(name = "success", location = "/admin/system/user/select.jsp"),
            @Result(name = "input", location = "/admin/system/user/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String select() {
        this.authUserList = authUserService
                .findAllAuthUserBySearchWithPage(authUser);
        return ActionSupport.SUCCESS;
    }

    /* /admin/system/user/selected.jsp */
    @Action(value = "selected", results = {
            @Result(name = "success", location = "/admin/system/user/selectedJson.jsp"),
            @Result(name = "input", location = "/admin/system/user/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String selected() {
        if (null == cids || cids.length == 0) {
            cids = new String[1];
            cids[0] = authUser.getUserName();
        }
        // this.authUserList = authUserService.findAllAuthUserByIds(cids);
        this.json = authUserService.findAllAuthUserByIds(cids);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "editPassword", results = {
            @Result(name = "success", location = "/admin/system/user/editPassword.jsp"),
            @Result(name = "input", location = "/admin/system/user/editPassword.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    @Validations(requiredFields = {
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "newUserPassword", message = "请输入新密码。"),
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "oldUserPassword", message = "请输入旧密码。"),
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "newUserPassword2", message = "请再次输入新密码。") })
    public String editPassword() {
        try {
            this.authUserService.updatePassword(getCurrentAuthUser(),
                    newUserPassword, newUserPassword2, oldUserPassword);
            this.authUserService.updatePassword2(getCurrentAuthUser(),
                    new2UserPassword, new2UserPassword2, old2UserPassword);
            addActionMessage("更新密码成功！");
        } catch (ApplicationException e) {
            addActionError(e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "editPassword2", results = {
            @Result(name = "success", location = "/admin/account/szcaiwumm.jsp"),
            @Result(name = "input", location = "/admin/account/szcaiwumm.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    @Validations(requiredFields = {
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "newUserPassword", message = "请输入新密码。"),
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "oldUserPassword", message = "请输入旧密码。"),
            @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "newUserPassword2", message = "请再次输入新密码。") })
    public String editPassword2() {
        try {
            this.authUserService.updatePassword2(getCurrentAuthUser(),
                    newUserPassword, newUserPassword2, oldUserPassword);
            addActionMessage("更新密码成功！");
        } catch (ApplicationException e) {
            addActionError(e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }

    public List<AuthUser> getAuthUsers() {
        return authUserList;
    }

    public void prepare() throws Exception {

    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }

    public String getNewUserPassword2() {
        return newUserPassword2;
    }

    public void setNewUserPassword2(String newUserPassword2) {
        this.newUserPassword2 = newUserPassword2;
    }

    public String getOldUserPassword() {
        return oldUserPassword;
    }

    public void setOldUserPassword(String oldUserPassword) {
        this.oldUserPassword = oldUserPassword;
    }

    public AuthUserService getAuthUserService() {
        return authUserService;
    }

    public void setAuthUserService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    public AuthRoleService getAuthRoleService() {
        return authRoleService;
    }

    public void setAuthRoleService(AuthRoleService authRoleService) {
        this.authRoleService = authRoleService;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public void setCids(String[] cids) {
        this.cids = cids;
    }

    public String[] getCids() {
        return this.cids;
    }

    public List<AuthUser> getAuthUserList() {
        return authUserList;
    }

    public void setAuthUserList(List<AuthUser> authUserList) {
        this.authUserList = authUserList;
    }

    public List<SystemOperationLog> getSystemOperationLogList() {
        return systemOperationLogList;
    }

    public void setSystemOperationLogList(
            List<SystemOperationLog> systemOperationLogList) {
        this.systemOperationLogList = systemOperationLogList;
    }

    public SystemOperationLog getSystemOperationLog() {
        return systemOperationLog;
    }

    public void setSystemOperationLog(SystemOperationLog systemOperationLog) {
        this.systemOperationLog = systemOperationLog;
    }

    public String getNew2UserPassword() {
        return new2UserPassword;
    }

    public void setNew2UserPassword(String new2UserPassword) {
        this.new2UserPassword = new2UserPassword;
    }

    public String getNew2UserPassword2() {
        return new2UserPassword2;
    }

    public void setNew2UserPassword2(String new2UserPassword2) {
        this.new2UserPassword2 = new2UserPassword2;
    }

    public String getOld2UserPassword() {
        return old2UserPassword;
    }

    public void setOld2UserPassword(String old2UserPassword) {
        this.old2UserPassword = old2UserPassword;
    }


}

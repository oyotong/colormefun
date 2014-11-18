package com.colormefun.action;

import com.colormefun.entity.MfFavorite;
import com.colormefun.entity.MfOrder;
import com.colormefun.entity.MfTicketBuffer;
import com.colormefun.entity.MfUser;
import com.colormefun.service.MfFavoriteService;
import com.colormefun.service.MfOrderService;
import com.colormefun.service.MfTicketBufferService;
import com.colormefun.service.MfUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import shop.Constants;
import shop.common.action.SimpleCaptcha;
import shop.common.action.auto.AbstractAction;
import shop.common.entity.JSONMessage;
import shop.common.exception.ApplicationException;
import shop.common.util.FileUtils;
import shop.common.util.ParamUtils;
import shop.common.util.SMSUtils;
import shop.common.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.Serializable;
import java.util.*;

@Controller("MfOutUserAction")
@Scope("prototype")
@Namespaces( { @Namespace("/user") })
@ParentPackage("my-default")
public class UserAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfUserService mfUserService;
	private MfOrderService mfOrderService;
	private MfFavoriteService mfFavoriteService;
	private MfTicketBufferService mfTicketBufferService;
	private List<MfUser> userList;
	private MfUser user;
	private List<MfOrder> myOrders;
    private List<MfFavorite> myFavorites;
    private MfFavorite myFavorite;
    private MfTicketBuffer mfTicketBuffer;
	private Integer[] cids;

	private Object json;

    private File image1;
    private String image1FileName;
    private String image1ContentType;

    private String validateCode;

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

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

	public UserAction() {
	}

	@Autowired
	public UserAction(MfUserService mfUserService,MfOrderService mfOrderService,MfFavoriteService mfFavoriteService,MfTicketBufferService mfTicketBufferService ) {
		this.mfUserService = mfUserService;
        this.mfOrderService = mfOrderService;
        this.mfFavoriteService = mfFavoriteService;
        this.mfTicketBufferService = mfTicketBufferService;
	}

    @Action(value = "myInfo", results = {
            @Result(name = "success", location = "/user/userInfo.jsp"),
            @Result(name = "input", location = "/index.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String myInfo() {
        this.user = getCurrentMfUser();
        if(null == user){
            return INPUT;
        }
        return SUCCESS;
    }

    @Action(value = "myFavorites", results = {
            @Result(name = "success", location = "/user/myFavorites.jsp"),
            @Result(name = "input", location = "/index.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String myFavorites() {
        this.user = getCurrentMfUser();
        if(null == user){
            return INPUT;
        }
        this.myFavorites = mfFavoriteService.findMyFavorites();
        return SUCCESS;
    }

    @Action(value = "removeFavorite", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String removeFavorite() {
        JSONMessage errMsg = new JSONMessage();
        try {
            mfFavoriteService.deleteMfFavoriteByIds(cids);
            errMsg.isError = false;
        }catch (Exception e){
            errMsg.isError = true;
            errMsg.message = "删除收藏活动失败";
        }

        this.json = errMsg;

        return SUCCESS;
    }

    @Action(value = "index", results = {
            @Result(name = "success", location = "/user/index.jsp"),
            @Result(name = "input", location = "/index.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String index() {
        this.user = getCurrentMfUser();
        if(null == user){
            return INPUT;
        }
        this.myOrders = mfOrderService.findMyOrders(null);
        this.myFavorites = mfFavoriteService.findMyFavorites();
        return SUCCESS;
    }

    @Action(value = "logoff", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String logoff() {

        getContext().getSession().setAttribute(
                getContext().getConfiguration(Constants.SESSION_MF_USER_NAME), null);
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = false;
        json = errMsg;
        return ActionSupport.SUCCESS;
    }

	@Action(value = "login", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String login() {

        String loginErrMsg = "无效的手机号或密码";

        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

//        MfUser currentUser = getCurrentMfUser();
//
//        if(null != currentUser){
//            errMsg.isError = false;
//            json = errMsg;
//            return SUCCESS;
//        }

        if(user == null){
            errMsg.message = (loginErrMsg);
            json = errMsg;
            return INPUT;
        }

        String mobilePhone = user.getMobilePhone();
        String password = user.getPassword();

        if(null == mobilePhone || null == password || mobilePhone.trim().length() == 0 || password.trim().length() == 0){
            errMsg.message = (loginErrMsg);
            json = errMsg;
            return INPUT;
        }
		user = this.mfUserService.login(user);
        if(null == user){
            errMsg.message = (loginErrMsg);
            json = errMsg;
            return INPUT;
        }
        if(StringUtils.isNull(user.getPic1())){
            user.setPic1("/resources/images/pic/portrait.jpg");
        }
        errMsg.isError = false;
        errMsg.data = user;
        json = errMsg;
        getContext().getSession().setAttribute(
                getContext().getConfiguration(Constants.SESSION_MF_USER_NAME), user);

		return ActionSupport.SUCCESS;
	}

    @Action(value = "saveUserInfo", results = {
            @Result(name = "success", location = "/user/userInfo.jsp"),
            @Result(name = "input", location = "/user/userInfo.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
     public String saveUserInfo() {
//        if(user.getPassword() == null){
//            String pwd = StringUtils.getPassword(10);
//            user.setPassword2(pwd);
//            user.setPassword(StringUtils.toMD5(pwd));
//        }
        if (null != image1) {
            user.setPic1(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }
        if (StringUtils.isNull(user.getPassword())){
            MfUser oldUser = this.mfUserService.getMfUserById(user.getUserNo());
            user.setPassword(oldUser.getPassword());
        }
        this.mfUserService.saveMfUser(user);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "register", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String register() {

        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        String captcha = (String) getContext().getSession().getAttribute(
                SimpleCaptcha.NAME);
        if (null == validateCode || !validateCode.equals(captcha)) {
            errMsg.message = ("验证码无效");
            json = errMsg;
            return INPUT;
        }

        if(user == null){
            errMsg.message = ("无效的注册信息");
            json = errMsg;
            return INPUT;
        }

        String mobilePhone = user.getMobilePhone();

        MfUser oldUser = this.mfUserService.getMfUserByMobilePhone(mobilePhone);
        if(null != oldUser){
            errMsg.message = ("该手机号已经注册");
            json = errMsg;
            return INPUT;
        }

        if(user.getUserName() == null){
            user.setUserName("[匿名]");
        }

        try {
            this.mfUserService.saveMfUser(user);

            getContext().getSession().setAttribute(
                    getContext().getConfiguration(Constants.SESSION_MF_USER_NAME), user);

            errMsg.isError = false;
            json = errMsg;
            return ActionSupport.SUCCESS;
        }catch (Throwable e){
            errMsg.message = e.getMessage();
            json = errMsg;
            return INPUT;
        }

    }

    @Action(value = "isLogin", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String isLogin() {
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        MfUser oldUser = getCurrentMfUser();
        if(null != oldUser){
            errMsg.isError = false;
            errMsg.data = oldUser;
            json = errMsg;

            return SUCCESS;
        }

        Map map = new HashMap();
        map.put("userName",false);
        errMsg.isError = false;
        errMsg.data = map;
        json = errMsg;

        return SUCCESS;
    }

    @Action(value = "checkMobilePhone", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String checkMobilePhone() {
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        if(user == null){
            errMsg.message = ("无效的信息");
            json = errMsg;
            return INPUT;
        }

        String mobilePhone = user.getMobilePhone();

        MfUser oldUser = this.mfUserService.getMfUserByMobilePhone(mobilePhone);
        if(null != oldUser){
            errMsg.message = ("该手机号已经注册");
            json = errMsg;
            return INPUT;
        }
        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }

    @Action(value = "validateImgCode", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String validateImgCode() {
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        String captcha = (String) getContext().getSession().getAttribute(
                SimpleCaptcha.NAME);
        if (null == validateCode || !validateCode.equals(captcha)) {
            errMsg.message = ("验证码无效");
            json = errMsg;
            return INPUT;
        }
        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }

    @Action(value = "changePassword", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String changePassword() {
        validateMobileCode();

        JSONMessage errMsg = (JSONMessage)json;

        if(errMsg.isError){
            return INPUT;
        }

        String mobilePhone = user.getMobilePhone();
        MfUser oldUser = this.mfUserService.getMfUserByMobilePhone(mobilePhone);
        if(null == oldUser){
            errMsg.isError = true;
            errMsg.message = ("没有找到该手机号");
            json = errMsg;
            return INPUT;
        }

        oldUser.setPassword(user.getPassword());
        this.mfUserService.saveMfUser(oldUser);

        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }
    @Action(value = "validateMobileCode", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String validateMobileCode() {
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        if(user == null || validateCode == null){
            errMsg.message = ("无效的信息");
            json = errMsg;
            return INPUT;
        }

        String mobilePhone = user.getMobilePhone();

        String randomCode = (String)getContext().getSession().getAttribute(Constants.SESSION_MOBILE_VALIDATE_CODE+"_"+mobilePhone);
        if(null == randomCode || !randomCode.equals(validateCode)){
            errMsg.message = ("无效的验证码");
            json = errMsg;
            return INPUT;
        }
        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }

    @Action(value = "sendValidateCode", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String sendValidateCode() {
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        if(user == null){
            errMsg.message = ("无效的信息");
            json = errMsg;
            return INPUT;
        }

        String captcha = (String) getContext().getSession().getAttribute(
                SimpleCaptcha.NAME);
        if (null == validateCode || !validateCode.equals(captcha)) {
            errMsg.message = ("验证码无效");
            json = errMsg;
            return INPUT;
        }

        final String mobilePhone = user.getMobilePhone();

        MfUser oldUser = this.mfUserService.getMfUserByMobilePhone(mobilePhone);
        if(null == oldUser){
            errMsg.message = ("没有找到该手机号");
            json = errMsg;
            return INPUT;
        }

        // get random 4-bit code
        String randomCode = ((int)(new Random().nextDouble() * 1000000) + 100000) + "";
        if(randomCode.length() > 6){
            randomCode = randomCode.substring(0,6);
        }
        // save random code in session and set a timeout.
        final HttpSession session = getContext().getSession();
        session.setAttribute(
                Constants.SESSION_MOBILE_VALIDATE_CODE+"_"+mobilePhone, randomCode);
        session.setAttribute(
                Constants.SESSION_MOBILE_VALIDATE_TIMEOUT+"_"+mobilePhone,Constants.MOBILE_VALIDATE_TIMEOUT); //分钟

        Thread timeoutThread = (Thread)session.getAttribute(
                Constants.SESSION_MOBILE_VALIDATE_TIMEOUT_THREAD+"_"+mobilePhone);

        if(null == timeoutThread) {
            timeoutThread = new Thread() {
                @Override
                public void run() {
                    int maxTime = 30;
                    while (-- maxTime < 0) {
                        try {
                            sleep(1000 * 60);
                        } catch (InterruptedException e) {
                        }
                        Integer time = (Integer) session.getAttribute(Constants.SESSION_MOBILE_VALIDATE_TIMEOUT+"_"+mobilePhone);
                        if(time == null){
                            break;
                        }
                        if (--time < 0) {
                            session.removeAttribute(Constants.SESSION_MOBILE_VALIDATE_CODE+"_"+mobilePhone);
                            session.removeAttribute(Constants.SESSION_MOBILE_VALIDATE_TIMEOUT+"_"+mobilePhone);
                            session.removeAttribute(Constants.SESSION_MOBILE_VALIDATE_TIMEOUT_THREAD+"_"+mobilePhone);
                            return;
                        }
                    }
                }
            };
            timeoutThread.start();
            session.setAttribute(Constants.SESSION_MOBILE_VALIDATE_TIMEOUT_THREAD,timeoutThread);
        }

        // send to mobile phone
        String smsMsg = ParamUtils.param("SMS_TEMP", "VALIDATE_CODE").replace("${validateCode}", randomCode);
        System.out.println(smsMsg);
        SMSUtils.sendMessage(mobilePhone, smsMsg);

        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }

	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/user/view.jsp"),
			@Result(name = "input", location = "/admin/user/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "cids", message = "You must select a Id.") })
	public String view() {
		Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = user.getUserNo();
		}
		user = this.mfUserService.getMfUserById(id);
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

    @Action(value = "addFavorite", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String addFavorite() {
        JSONMessage errMsg = new JSONMessage();
        json = errMsg;
//        errMsg.isError = true;

//        if(user == null || validateCode == null){
//            errMsg.message = ("无效的信息");
//            json = errMsg;
//            return INPUT;
//        }

        MfUser mfUser = getCurrentMfUser();
        if(null == mfUser){
            errMsg.message = "你还没有登录";
            return INPUT;
        }

        if(this.myFavorite == null || this.myFavorite.getMfCase() == null || this.myFavorite.getMfCase().getCaseNo() == null){
            errMsg.message = "无效的参数";
            return INPUT;
        }

        myFavorite.setUser(mfUser);
        myFavorite.setCreatedDate(new Date());
        myFavorite.setActive("Y");

        try{
            this.mfFavoriteService.saveMfFavorite(myFavorite);
        }catch (Exception e){
            errMsg.message = "添加该活动到收藏夹失败，可能您之前已经添加了该活动";
            return INPUT;
        }

        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }

    @Action(value = "addToBuffer", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String addToBuffer() {
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;
        json = errMsg;

        MfUser mfUser = getCurrentMfUser();
        if(null == mfUser){
            String captcha = (String) getContext().getSession().getAttribute(
                    SimpleCaptcha.NAME);
            if (null == validateCode || !validateCode.equals(captcha)) {
                errMsg.message = ("验证码无效");
                return INPUT;
            }
        }

        if(null == this.mfTicketBuffer || null == this.mfTicketBuffer.getCaseNo()){
            errMsg.message = ("无效的参数");
            return INPUT;
        }

        try {
            this.mfTicketBufferService.addToTicketBuffer(this.mfTicketBuffer);
            errMsg.message = "";
            errMsg.isError = false;
            return SUCCESS;
        }catch (ApplicationException e){
            errMsg.message = e.getMessage();
            return INPUT;
        }
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

    public List<MfOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<MfOrder> myOrders) {
        this.myOrders = myOrders;
    }

    public List<MfFavorite> getMyFavorites() {
        return myFavorites;
    }

    public void setMyFavorites(List<MfFavorite> myFavorites) {
        this.myFavorites = myFavorites;
    }

    public MfFavorite getMyFavorite() {
        return myFavorite;
    }

    public void setMyFavorite(MfFavorite myFavorite) {
        this.myFavorite = myFavorite;
    }

    public MfTicketBuffer getMfTicketBuffer() {
        return mfTicketBuffer;
    }

    public void setMfTicketBuffer(MfTicketBuffer mfTicketBuffer) {
        this.mfTicketBuffer = mfTicketBuffer;
    }
}

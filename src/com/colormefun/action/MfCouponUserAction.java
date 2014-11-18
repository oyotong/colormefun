package com.colormefun.action;

import com.colormefun.entity.MfCoupon;
import com.colormefun.entity.MfUser;
import com.colormefun.service.MfCouponService;
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
import shop.common.action.auto.AbstractAction;
import shop.common.entity.JSONMessage;
import shop.common.exception.ApplicationException;
import shop.common.util.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/user/coupon") })
@ParentPackage("my-default")
public class MfCouponUserAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCouponService couponService;
	private List<MfCoupon> list;
	private MfCoupon coupon;

    private String action;

	private Object json;

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}

    public List<MfCoupon> getList() {
        return list;
    }

    public void setList(List<MfCoupon> list) {
        this.list = list;
    }

    public MfCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(MfCoupon coupon) {
        this.coupon = coupon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MfCouponUserAction() {
	}

	@Autowired
	public MfCouponUserAction(MfCouponService couponService) {
		this.couponService = couponService;
	}


	@Action(value = "add", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() {
        MfUser currentMfUser = getCurrentMfUser();
        JSONMessage message = new JSONMessage();
        try {
            String id = coupon.getCouponNo();
            if(null == id){
                throw new ApplicationException("无效的优惠劵编号");
            }
            coupon = this.couponService.getMfCouponByCouponNo(id);

            if (null == coupon) {
                throw new ApplicationException("无效的优惠劵编号");
            }
            if (!"released".equals(coupon.getStatus()) || "N".equals(coupon.getActive()) || isExpired(coupon) ){
                throw new ApplicationException("无效的优惠劵状态");
            }
//            if(coupon.getUserNo() != null && !coupon.getUserNo().equals(currentMfUser.getUserNo())){
//                throw new ApplicationException("无法使用该优惠劵");
//            }
            coupon.setStatus("locked");
            coupon.setUserNo(currentMfUser.getUserNo());
            couponService.saveMfCoupon(coupon);

            //Set to Session
            addToSession(coupon);

            message.isError = false;
            message.message = "成功添加优惠劵【"+id+"】";
            this.json = message;
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            message.setMessage(e.getMessage());
            this.json = message;
            return ActionSupport.INPUT;
        }
	}

    @Action(value = "remove", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String remove() {
        JSONMessage message = new JSONMessage();
        try {
            cleanSession();
            message.isError = false;
            message.message = "成功取消优惠劵";
            this.json = message;
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            message.setMessage(e.getMessage());
            this.json = message;
            return ActionSupport.INPUT;
        }
    }

    private void addToSession(MfCoupon coupon){
        List<MfCoupon> list = (List<MfCoupon>) getContext().getSession().getAttribute(Constants.SESSION_COUPON_LIST);
        if(null == list){
            list = new ArrayList<MfCoupon>();
            getContext().getSession().setAttribute(Constants.SESSION_COUPON_LIST, list);
        }
        list.add(coupon);
    }

    private void cleanSession(){
        List<MfCoupon> list = (List<MfCoupon>) getContext().getSession().getAttribute(Constants.SESSION_COUPON_LIST);
        if(null == list){
            list = new ArrayList<MfCoupon>();
            getContext().getSession().setAttribute(Constants.SESSION_COUPON_LIST, list);
        }
        for(MfCoupon c : list){
            c.setStatus("released");
        }
        couponService.saveAllMfCoupon(list);
        list.clear();
    }

    private boolean isExpired(MfCoupon coupon) {
        return (null == coupon || coupon.getDeadline() == null || coupon.getDeadline().before(DateUtils.now()));
    }

}

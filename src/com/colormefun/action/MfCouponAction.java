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

import com.colormefun.entity.MfCoupon;
import com.colormefun.service.MfCouponService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.exception.ApplicationException;
import shop.common.util.DateUtils;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/coupon") })
@ParentPackage("my-default")
public class MfCouponAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCouponService couponService;
	private List<MfCoupon> list;
	private MfCoupon coupon;

    private String action;

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

    public MfCouponAction() {
	}

	@Autowired
	public MfCouponAction(MfCouponService couponService) {
		this.couponService = couponService;
	}

	@Action(value = "add", results = {
			@Result(name = "success", location = "/admin/coupon/add.jsp"),
			@Result(name = "input", location = "/admin/coupon/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/coupon/list.jsp"),
			@Result(name = "input", location = "/admin/coupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.list = couponService.findAllMfCouponWithPage();
		return ActionSupport.SUCCESS;
	}

	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/coupon/list.jsp"),
			@Result(name = "input", location = "/admin/coupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.list = couponService.findAllMfCouponBySearchWithPage(coupon);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/coupon/list.jsp"),
			@Result(name = "input", location = "/admin/coupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = coupon.getCouponId();
		}
		coupon = this.couponService.getMfCouponById(id);
        try {
            if (action == null || id == null) {
                throw new ApplicationException("参数错误");
            }
            if (action.equals("released")) {
                if (!"created".equals(coupon.getStatus()) || "N".equals(coupon.getActive()) || isExpired(coupon) ){
                    throw new ApplicationException("状态错误");
                }
                coupon.setStatus("released");
                couponService.saveMfCoupon(coupon);
            } else if (action.equals("active")) {//激活
                if (!"N".equals(coupon.getActive())|| isExpired(coupon)){
                    throw new ApplicationException("状态错误");
                }
                coupon.setActive("Y");
                couponService.saveMfCoupon(coupon);
            } else if (action.equals("unactivated")) {
                if ("N".equals(coupon.getActive())|| isExpired(coupon)){
                    throw new ApplicationException("状态错误");
                }
                coupon.setActive("N");
                couponService.saveMfCoupon(coupon);

            } else if (action.equals("locked")) {//解锁
                if (!"locked".equals(coupon.getStatus()) || "N".equals(coupon.getActive())|| isExpired(coupon)){
                    throw new ApplicationException("状态错误");
                }
                coupon.setStatus("released");
                couponService.saveMfCoupon(coupon);
            }

            return ActionSupport.SUCCESS;
        }catch (Exception e){
            addActionError(e.getMessage());
            return ActionSupport.ERROR;
        }
	}

    private boolean isExpired(MfCoupon coupon) {
        return (null == coupon || coupon.getDeadline() == null || coupon.getDeadline().before(DateUtils.now()));
    }

    @Action(value = "view", results = {
			@Result(name = "success", location = "/admin/coupon/view.jsp"),
			@Result(name = "input", location = "/admin/coupon/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "coupon.couponId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = coupon.getCouponId();
		}
		coupon = this.couponService.getMfCouponById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/coupon/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			couponService.deleteMfCouponByIds(cids);
		}else{
			couponService.deleteMfCoupon(coupon);
		}
		return ActionSupport.SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/coupon/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.couponService.saveMfCoupon(coupon);
		return ActionSupport.SUCCESS;
	}

    @Action(value = "create", results = {
            @Result(name = "success", location = "/admin/coupon/list.jsp"),
            @Result(name = "input", location = "/admin/coupon/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String create() {
        try{
            this.couponService.createMfCoupon(coupon);
            list = couponService.findAllMfCouponBySearchWithPage(coupon);

        } catch (ApplicationException e) {
            addActionError(e.getMessage());
            return ActionSupport.ERROR;
        } catch (Exception e) {
            addActionError(e.getMessage());
            return ActionSupport.ERROR;
        }
        return ActionSupport.SUCCESS;
    }

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/coupon/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "coupon.couponId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "coupon.couponId", message = "You must enter a value for couponId(save).")})
	public String update() {
		this.couponService.updateMfCoupon(coupon);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = couponService.findAllMfCouponByNameWithIdAndName("" + coupon.getCouponId());
		return ActionSupport.SUCCESS;
	}

	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/coupon/select.jsp"),
			@Result(name = "input", location = "/admin/coupon/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.list = couponService.findAllMfCouponBySearchWithPage(coupon);
		return ActionSupport.SUCCESS;
	}
	/*/admin/coupon/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/coupon/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/coupon/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = coupon.getCouponId();
		}
		//this.list = couponService.findAllMfCouponByIds(cids);
		this.json = couponService.findAllMfCouponByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<MfCoupon> getMfCoupons() {
		return list;
	}

	public MfCoupon getMfCoupon() {
		return coupon;
	}

	public void setMfCoupon(MfCoupon coupon) {
		this.coupon = coupon;
	}

}

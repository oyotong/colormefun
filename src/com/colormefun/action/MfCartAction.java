package com.colormefun.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.colormefun.entity.MfCase;
import com.colormefun.entity.MfCoupon;
import com.colormefun.entity.MfUser;
import com.colormefun.service.MfCaseService;
import com.colormefun.service.MfCouponService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfCart;
import com.colormefun.service.MfCartService;
import shop.Constants;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.entity.JSONMessage;
import shop.common.util.DateUtils;
import shop.common.util.StringUtils;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/user/cart") })
@ParentPackage("my-default")
public class MfCartAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfCartService mfCartService;
	private MfCaseService mfCaseService;
	private MfCouponService couponService;
	private List<MfCart> mfCartList;
	private MfCart mfCart;
	private MfCase mfCase;

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

	public List<MfCart> getMfCartList() {
		return mfCartList;
	}

	public void setMfCartList(List<MfCart> mfCartList) {
		this.mfCartList = mfCartList;
	}

	public MfCartAction() {
	}

	@Autowired
	public MfCartAction(MfCartService mfCartService, MfCaseService mfCaseService, MfCouponService couponService) {
		this.mfCartService = mfCartService;
        this.mfCaseService = mfCaseService;
        this.couponService = couponService;

	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/user/cart/add.jsp"),
			@Result(name = "input", location = "/user/cart/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/user/cart/list.jsp"),
			@Result(name = "input", location = "/user/cart/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.mfCartList = mfCartService.findAllMfCartWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/user/cart/list.jsp"),
			@Result(name = "input", location = "/user/cart/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.mfCartList = mfCartService.findAllMfCartBySearchWithPage(mfCart);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/user/cart/edit.jsp"),
			@Result(name = "input", location = "/user/cart/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCart.getCartNo();
		}
		mfCart = this.mfCartService.getMfCartById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/user/cart/view.jsp"),
			@Result(name = "input", location = "/user/cart/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCart.cartNo", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = mfCart.getCartNo();
		}
		mfCart = this.mfCartService.getMfCartById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/user/cart/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfCartService.deleteMfCartByIds(cids);
		}else{
			mfCartService.deleteMfCart(mfCart);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/user/cart/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfCartService.saveMfCart(mfCart);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/user/cart/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCart.cartNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "mfCart.cartNo", message = "You must enter a value for cartNo(save).")})
	public String update() {
		this.mfCartService.updateMfCart(mfCart);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfCartService.findAllMfCartByNameWithIdAndName(""+mfCart.getCartNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/user/cart/select.jsp"),
			@Result(name = "input", location = "/user/cart/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.mfCartList = mfCartService.findAllMfCartBySearchWithPage(mfCart);
		return ActionSupport.SUCCESS;
	}
	/*/user/cart/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/user/cart/selectedJson.jsp"),
			@Result(name = "input", location = "/user/cart/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = mfCart.getCartNo();
		}
		//this.mfCartList = mfCartService.findAllMfCartByIds(cids);
		this.json = mfCartService.findAllMfCartByIds(cids);
		return ActionSupport.SUCCESS;
	}

    //////////// 前 台 ////////////
    // http://localhost/user/cart/showCart.do

    @Action(value = "getCartCount", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String getCartCount() {
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = false;
        this.mfCartList = getCartListFromSession();
        MfUser currentMfUser = getCurrentMfUser();
        if(mfCartList.size() == 0 && currentMfUser != null){
            this.mfCartList = mfCartService.findAllMfCartByUser(currentMfUser);
            setCartListtoSession(this.mfCartList);
        }
        if(null == this.mfCartList){
            errMsg.data = 0;
        }else{
            errMsg.data = this.mfCartList.size();
        }
        json = errMsg;
        return ActionSupport.SUCCESS;
    }
    @Action(value = "showCart", results = {
            @Result(name = "success", location = "/user/cart.jsp"),
            @Result(name = "input", location = "/user/cart.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String showCart() {
        this.mfCartList = getCartListFromSession();
        MfUser currentMfUser = getCurrentMfUser();
        if(mfCartList.size() == 0 && currentMfUser != null){
            this.mfCartList = mfCartService.findAllMfCartByUser(currentMfUser);
            setCartListtoSession(this.mfCartList);
        }
        if(null == this.mfCartList){
            return INPUT;
        }
        for (MfCart cart:mfCartList){
            cart.getMfCase().setTicketPrice2(cart.getMfCase().getTicketPrice().multiply(new BigDecimal(cart.getQty())));
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "confirmOrder", results = {
            @Result(name = "success", location = "/user/confirmOrder.jsp"),
            @Result(name = "input", location = "/user/confirmOrder.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String confirmOrder() {
        return showCart();
    }

    //http://localhost/user/cart/addToCart.do?mfCase.caseNo=1&mfCase.ticketNumber=5
    @Action(value = "addToCart", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String addToCart() {

        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        MfUser currentUser = getCurrentMfUser();

        Integer caseNo = mfCase.getCaseNo();
        Integer num = mfCase.getTicketNumber();

        if(null == caseNo){
            errMsg.message = ("无效的活动");
            json = errMsg;
            return INPUT;
        }
        if(null == num || num < 0){
            errMsg.message = ("无效的数量");
            json = errMsg;
            return INPUT;
        }
        this.mfCartList = getCartListFromSession();
        this.mfCart = findCartByCaseNo(caseNo);
        if(null != mfCart){
            Integer newQty = mfCart.getQty() + num;
            if(newQty > mfCart.getMfCase().getTicketNumber()){
                errMsg.message = ("无效的数量");
                json = errMsg;
                return INPUT;
            }
            mfCart.setQty(newQty);
            mfCart.getMfCase().setTicketPrice2(mfCart.getMfCase().getTicketPrice().multiply(new BigDecimal(newQty)));
        }else {

            this.mfCase = this.mfCaseService.getMfCaseById(caseNo);

            if (mfCase == null) {
                errMsg.message = ("无效的活动");
                json = errMsg;
                return INPUT;
            }

            if (mfCase.getTicketNumber() < num) {
                errMsg.message = ("无效的数量");
                json = errMsg;
                return INPUT;
            }

            this.mfCart = new MfCart();
            mfCart = new MfCart();
            mfCart.setActive("Y");
            mfCart.setCreatedDate(DateUtils.now());
            mfCart.setMfCase(mfCase);
            mfCart.setQty(num);
            mfCart.getMfCase().setTicketPrice2(mfCase.getTicketPrice().multiply(new BigDecimal(num)));

            if (null != currentUser) {
                mfCart.setUser(currentUser);
                this.mfCartService.saveMfCart(mfCart);
            }
            if (null == mfCartList) {
                mfCartList = new ArrayList<MfCart>();
            }
            this.mfCartList.add(mfCart);
        }
        setCartListtoSession(this.mfCartList);

        this.json = createCartData(errMsg);

        return ActionSupport.SUCCESS;
    }

    @Action(value = "updateQty", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String updateQty() {

        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        Integer caseNo = mfCart.getMfCase().getCaseNo();
        Integer newQty = mfCart.getQty();
        mfCart = findCartByCaseNo(caseNo);

        if(mfCart == null){
            errMsg.message = ("无效的活动");
            json = errMsg;
            return INPUT;
        }

        if(newQty > mfCart.getMfCase().getTicketNumber()){
            errMsg.message = ("无效的数量");
            json = errMsg;
            return INPUT;
        }

        mfCart.setQty(newQty);
        mfCart.getMfCase().setTicketPrice2(mfCart.getMfCase().getTicketPrice().multiply(new BigDecimal(newQty)));

        MfUser currentUser = getCurrentMfUser();
        if(null != currentUser){
            mfCartService.saveMfCart(mfCart);
        }

        this.json = createCartData(errMsg);;
        return SUCCESS;
    }

    @Action(value = "removeFormCart", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String removeFormCart() {

        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        Integer caseNo = mfCart.getMfCase().getCaseNo();
        MfCart cart = findCartByCaseNo(caseNo);

        if(cart == null){
            errMsg.message = ("无效的活动");
            json = errMsg;
            return INPUT;
        }

        this.mfCartList = getCartListFromSession();

        this.mfCartList.remove(cart);

        MfUser currentUser = new MfUser();
        if(null != currentUser){
            mfCartService.deleteMfCart(cart);
        }


        errMsg.message = "活动【"+cart.getMfCase().getTitle()+"】已经成功从购物车中删除";
        this.json = createCartData(errMsg);
        return SUCCESS;
    }

    @Action(value = "getCart", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String getCart() {
        JSONMessage errMsg = new JSONMessage();
        this.json = createCartData(errMsg);
        return SUCCESS;
    }

    private JSONMessage createCartData(JSONMessage errMsg) {

        if(null == mfCartList){
            mfCartList = getCartListFromSession();
        }

        for(MfCart cart : mfCartList){
            cart.getMfCase().setTicketPrice2(cart.getMfCase().getTicketPrice().multiply(new BigDecimal(cart.getQty())));
            cart.getMfCase().setStartDateStr(DateUtils.date(cart.getMfCase().getStartDate(),"MM月dd日（E）"));
            cart.getMfCase().setTicketPriceStr(StringUtils.fmtMoney(cart.getMfCase().getTicketPrice()));
            cart.getMfCase().setTicketPrice2Str(StringUtils.fmtMoney(cart.getMfCase().getTicketPrice2()));

        }

        errMsg.isError = false;
        java.util.Map map = new HashMap();
        map.put("list", this.mfCartList);

        map.put("couponTotal", StringUtils.fmtMoney(new BigDecimal(0).subtract(getCouponPrice())));
        map.put("total", StringUtils.fmtMoney(getTotalPrice()));
        map.put("balance", StringUtils.fmtMoney(getBalance()));
        errMsg.data = map;
        return errMsg;
    }

    public BigDecimal getBalance() {
        BigDecimal couponPrice = getCouponPrice();
        BigDecimal total = getTotalPrice();

        BigDecimal balance = total.subtract(couponPrice);
        if(balance.doubleValue() < 0){
            balance = new BigDecimal("0");
        }
        return balance;
    }

    private MfCart findCartByCaseNo(Integer caseNo){
        if(mfCartList == null){
            mfCartList = getCartListFromSession();
        }
        if(mfCartList == null){
            return null;
        }
        for(MfCart cart : mfCartList){
            if(cart.getMfCase().getCaseNo().equals(caseNo)){
                return cart;
            }
        }
        return null;
    }

    private List<MfCart> getCartListFromSession() {
        List<MfCart> list = (List<MfCart>) getContext().getSession().getAttribute(Constants.SESSION_CART_LIST);
        if(null == list){
            list = new ArrayList<MfCart>();
        }
        return list;
    }

    private void setCartListtoSession(List<MfCart> list) {
        getContext().getSession().setAttribute(Constants.SESSION_CART_LIST, list);
    }

    public BigDecimal getCouponPrice(){
        List<MfCoupon> list = (List<MfCoupon>) getContext().getSession().getAttribute(Constants.SESSION_COUPON_LIST);
        MfUser currentMfUser = getCurrentMfUser();
        if((null == list || list.size() == 0) && null != currentMfUser){
            list = couponService.findAllMfCouponByUserNoAndStatus(currentMfUser.getUserNo(), "locked");
            if(null == list){
                list = new ArrayList<MfCoupon>();
            }
            getContext().getSession().setAttribute(Constants.SESSION_COUPON_LIST, list);
        }

        BigDecimal total = new BigDecimal(0);
        if(null != list) {
            for (MfCoupon c : list) {
                total = total.add(c.getDeduction());
            }
        }
        return total;
    }

    //////////// getter and setter ////////////

	public List<MfCart> getMfCarts() {
		return mfCartList;
	}

	public MfCart getMfCart() {
		return mfCart;
	}

	public void setMfCart(MfCart mfCart) {
		this.mfCart = mfCart;
	}

    public MfCase getMfCase() {
        return mfCase;
    }

    public void setMfCase(MfCase mfCase) {
        this.mfCase = mfCase;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal total = new BigDecimal(0);
        List<MfCart> list = getCartListFromSession();
        for (MfCart c : list){
            total = total.add(c.getMfCase().getTicketPrice2());
        }
        return total;
    }

}

package com.colormefun.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.UtilDate;
import com.colormefun.entity.*;
import com.colormefun.service.MfCaseService;
import com.colormefun.service.MfCouponService;
import com.colormefun.service.MfOrderService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.service.MfCartService;
import shop.Constants;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.context.ApplicationContext;
import shop.common.entity.JSONMessage;
import shop.common.util.DateUtils;
import shop.common.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private MfOrderService mfOrderService;
    private String orderNo;
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
	public MfCartAction(MfCartService mfCartService, MfCaseService mfCaseService, MfCouponService couponService, MfOrderService mfOrderService) {
		this.mfCartService = mfCartService;
        this.mfCaseService = mfCaseService;
        this.couponService = couponService;
        this.mfOrderService = mfOrderService;
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
        String result = showCart();
        MfOrder order = mfOrderService.createOrderByCart(this.mfCartList);
        this.orderNo = order.getOrderNo();
        return result;
    }

    @Action(value = "toAlipay", results = {
            @Result(name = "success", location = "/user/confirmOrder.jsp"),
            @Result(name = "input", location = "/user/confirmOrder.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String toAlipay() {

        HttpServletResponse response = ApplicationContext.getContext().getResponse();
        HttpServletRequest request = ApplicationContext.getContext().getRequest();
        MfOrder order = this.mfOrderService.getMfOrderById(orderNo);
        if(order == null || null == order.getDetails() || order.getDetails().size() == 0){
            return ActionSupport.INPUT;
        }
        Set<MfOrderDetail> details = order.getDetails();
        ////////////////////////////////////请求参数//////////////////////////////////////
        try {
            //支付类型
            String payment_type = "1";
            //必填，不能修改
            //服务器异步通知页面路径
            String notify_url = "http://www.colormefun.cn/alipay/notify_url.jsp";
            //需http://格式的完整路径，不能加?id=123这类自定义参数

            //页面跳转同步通知页面路径
            String return_url = "http://www.colormefun.cn/alipay/return_url.jsp";
            //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

            //卖家支付宝帐户
            String seller_email = "colormefun.web@qq.com";
            //必填

            //商户订单号
            String out_trade_no = order.getOrderNo();
            //商户网站订单系统中唯一订单号，必填

            //订单名称
            String subject = getOrderSubject(details);
            //必填

            //付款金额
            String total_fee = order.getSummary().toString();
            //必填

            //订单描述
            String body = getOrderBody(details);
            //商品展示地址
            String show_url = "http://www.colormefun.cn/detail.jsp?caseNo=" + getCaseNo(details);
            //需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

            //防钓鱼时间戳
            String anti_phishing_key = AlipaySubmit.query_timestamp();
            //若要使用请调用类文件submit中的query_timestamp函数

            //客户端的IP地址
            String exter_invoke_ip = request.getRemoteAddr();
            //非局域网的外网IP地址，如：221.0.0.1


            //////////////////////////////////////////////////////////////////////////////////

            //把请求参数打包成数组
            Map<String, String> sParaTemp = new HashMap<String, String>();
            sParaTemp.put("service", "create_direct_pay_by_user");
            sParaTemp.put("partner", AlipayConfig.partner);
            sParaTemp.put("_input_charset", AlipayConfig.input_charset);
            sParaTemp.put("sign_type", AlipayConfig.sign_type);
            sParaTemp.put("sign", AlipayConfig.key);
            sParaTemp.put("payment_type", payment_type);
            sParaTemp.put("notify_url", notify_url);
            sParaTemp.put("return_url", return_url);
            sParaTemp.put("seller_email", seller_email);
            sParaTemp.put("out_trade_no", out_trade_no);
            sParaTemp.put("subject", subject);
            sParaTemp.put("total_fee", total_fee);
//		sParaTemp.put("seller_id", AlipayConfig.partner);
            sParaTemp.put("body", body);
            sParaTemp.put("show_url", show_url);
            sParaTemp.put("anti_phishing_key", anti_phishing_key);
            sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

            //建
            String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
            response.setContentType("text/html charset=utf-8");
            response.getWriter().println(sHtmlText);
        }catch (Exception e){
            return ActionSupport.INPUT;
        }
        return null;
    }

    private String getCaseNo(Set<MfOrderDetail> details) {
        return details.iterator().next().getCaseNo()+"";
    }

    private String getOrderBody(Set<MfOrderDetail> details) {
        String body = "";
        for(MfOrderDetail detail : details){
            body += detail.getMfCase().getTitle()+" × "+detail.getQty()+", ";
        }
        if(body.endsWith(", ")){
            body = body.substring(0, body.length() - 1);
        }
        if(body.length()==0){
            body = "无";
        }
        return body;
    }

    private String getOrderSubject(Set<MfOrderDetail> details) {
        String subject = "";
        for(MfOrderDetail detail : details){
            subject += detail.getMfCase().getTitle()+" ,";
        }
        if(subject.endsWith(", ")){
            subject = subject.substring(0, subject.length() - 1);
        }
        if(subject.length()==0){
            subject = "无标题订单";
        }
        return subject;
    }

    private String getOrderTotalFee(Set<MfOrderDetail> details) {
        BigDecimal totalFee = new BigDecimal(0);
        for(MfOrderDetail detail : details){
            totalFee = totalFee.add(detail.getPrice());
        }
        return totalFee.toString();
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

package com.colormefun.action;

import java.io.Serializable;
import java.util.*;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.colormefun.entity.MfOrder;
import com.colormefun.service.MfOrderService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.entity.JSONMessage;
import shop.common.exception.ApplicationException;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/order") ,@Namespace("/user/order")})
@ParentPackage("my-default")
public class MfOrderAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfOrderService mfOrderService;
	private List<MfOrder> list;
	private MfOrder order;
	
	private String[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(String[] cids){
		this.cids = cids;
	}
	
	public String[] getCids(){
		return this.cids;
	}

	public List<MfOrder> getList() {
		return list;
	}

	public void setList(List<MfOrder> list) {
		this.list = list;
	}

	public MfOrderAction() {
	}

	@Autowired
	public MfOrderAction(MfOrderService mfOrderService) {
		this.mfOrderService = mfOrderService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/order/add.jsp"),
			@Result(name = "input", location = "/admin/order/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/order/list.jsp"),
			@Result(name = "input", location = "/admin/order/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.list = mfOrderService.findAllMfOrderWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/order/list.jsp"),
			@Result(name = "input", location = "/admin/order/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.list = mfOrderService.findAllMfOrderBySearchWithPage(order);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/order/edit.jsp"),
			@Result(name = "input", location = "/admin/order/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = order.getOrderNo();
		}
		order = this.mfOrderService.getMfOrderById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/order/view.jsp"),
			@Result(name = "input", location = "/admin/order/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = order.getOrderNo();
		}
		order = this.mfOrderService.getMfOrderById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/order/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			mfOrderService.deleteMfOrderByIds(cids);
		}else{
			mfOrderService.deleteMfOrder(order);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/order/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.mfOrderService.saveMfOrder(order);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/order/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "order.orderNo", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "order.orderNo", message = "You must enter a value for orderNo(save).")})
	public String update() {
		this.mfOrderService.updateMfOrder(order);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = mfOrderService.findAllMfOrderByNameWithIdAndName("" + order.getOrderNo());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/order/select.jsp"),
			@Result(name = "input", location = "/admin/order/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.list = mfOrderService.findAllMfOrderBySearchWithPage(order);
		return ActionSupport.SUCCESS;
	}
	/*/admin/order/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/order/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/order/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = order.getOrderNo();
		}
		//this.list = mfOrderService.findAllMfOrderByIds(cids);
		this.json = mfOrderService.findAllMfOrderByIds(cids);
		return ActionSupport.SUCCESS;
	}

    ////////////////////////// My Order /////////////////////////////
    @Action(value = "myOrder", results = {
            @Result(name = "success", location = "/user/myOrder.jsp"),
            @Result(name = "input", location = "/user/myOrder.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String myOrder(){
        list = this.mfOrderService.findMyOrders(order);
        return SUCCESS;
    }

//    removeOrder, payOrder
    @Action(value = "removeOrder", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String removeOrder(){
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        try{
            this.mfOrderService.removeOrder(order);
        }catch (ApplicationException e){
            errMsg.message = e.getMessage();
            json = errMsg;
            return INPUT;
        }

        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }
    @Action(value = "payOrder", results = {
            @Result(name = "success", location = "/common/json.jsp"),
            @Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String payOrder(){
        JSONMessage errMsg = new JSONMessage();
        errMsg.isError = true;

        try{
            this.mfOrderService.completePayOrder(order.getOrderNo());
        }catch (ApplicationException e){
            errMsg.message = e.getMessage();
            json = errMsg;
            return INPUT;
        }

        errMsg.isError = false;
        json = errMsg;
        return SUCCESS;
    }

	public List<MfOrder> getMfOrders() {
		return list;
	}

	public MfOrder getOrder() {
		return order;
	}

	public void setOrder(MfOrder order) {
		this.order = order;
	}

}

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

import shop.company.entity.QSLProduct;
import shop.company.service.QSLProductService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/company/qSLProduct") })
@ParentPackage("my-default")
public class QSLProductAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLProductService qSLProductService;
	private List<QSLProduct> qSLProductList;
	private QSLProduct qSLProduct;
	
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

	public List<QSLProduct> getQSLProductList() {
		return qSLProductList;
	}

	public void setQSLProductList(List<QSLProduct> qSLProductList) {
		this.qSLProductList = qSLProductList;
	}

	public QSLProductAction() {
	}

	@Autowired
	public QSLProductAction(QSLProductService qSLProductService) {
		this.qSLProductService = qSLProductService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/company/qSLProduct/add.jsp"),
			@Result(name = "input", location = "/company/qSLProduct/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/company/qSLProduct/list.jsp"),
			@Result(name = "input", location = "/company/qSLProduct/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.qSLProductList = qSLProductService.findAllQSLProductWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/company/qSLProduct/list.jsp"),
			@Result(name = "input", location = "/company/qSLProduct/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.qSLProductList = qSLProductService.findAllQSLProductBySearchWithPage(qSLProduct);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/company/qSLProduct/edit.jsp"),
			@Result(name = "input", location = "/company/qSLProduct/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLProduct.getId();
		}
		qSLProduct = this.qSLProductService.getQSLProductById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/company/qSLProduct/view.jsp"),
			@Result(name = "input", location = "/company/qSLProduct/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLProduct.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = qSLProduct.getId();
		}
		qSLProduct = this.qSLProductService.getQSLProductById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLProduct/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			qSLProductService.deleteQSLProductByIds(cids);
		}else{
			qSLProductService.deleteQSLProduct(qSLProduct);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLProduct/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.qSLProductService.saveQSLProduct(qSLProduct);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/company/qSLProduct/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLProduct.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "qSLProduct.id", message = "You must enter a value for id(save).")})
	public String update() {
		this.qSLProductService.updateQSLProduct(qSLProduct);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = qSLProductService.findAllQSLProductByNameWithIdAndName(""+qSLProduct.getId());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/company/qSLProduct/select.jsp"),
			@Result(name = "input", location = "/company/qSLProduct/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.qSLProductList = qSLProductService.findAllQSLProductBySearchWithPage(qSLProduct);
		return ActionSupport.SUCCESS;
	}
	/*/company/qSLProduct/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/company/qSLProduct/selectedJson.jsp"),
			@Result(name = "input", location = "/company/qSLProduct/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = qSLProduct.getId();
		}
		//this.qSLProductList = qSLProductService.findAllQSLProductByIds(cids);
		this.json = qSLProductService.findAllQSLProductByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<QSLProduct> getQSLProducts() {
		return qSLProductList;
	}

	public QSLProduct getQSLProduct() {
		return qSLProduct;
	}

	public void setQSLProduct(QSLProduct qSLProduct) {
		this.qSLProduct = qSLProduct;
	}

}

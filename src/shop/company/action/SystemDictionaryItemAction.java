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
import shop.company.entity.SystemDictionaryItem;
import shop.company.service.SystemDictionaryItemService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/systemDictionaryItem") })
@ParentPackage("my-default")
public class SystemDictionaryItemAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SystemDictionaryItemService systemDictionaryItemService;
	private List<SystemDictionaryItem> systemDictionaryItemList;
	private SystemDictionaryItem systemDictionaryItem;
	
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

	public List<SystemDictionaryItem> getSystemDictionaryItemList() {
		return systemDictionaryItemList;
	}

	public void setSystemDictionaryItemList(List<SystemDictionaryItem> systemDictionaryItemList) {
		this.systemDictionaryItemList = systemDictionaryItemList;
	}

	public SystemDictionaryItemAction() {
	}

	@Autowired
	public SystemDictionaryItemAction(SystemDictionaryItemService systemDictionaryItemService) {
		this.systemDictionaryItemService = systemDictionaryItemService;
	}

	@Action(value = "add", results = { @Result(location = "/admin/systemDictionaryItem/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/systemDictionaryItem/list.jsp"),
			@Result(name = "input", location = "/admin/systemDictionaryItem/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.systemDictionaryItemList = systemDictionaryItemService.findAllSystemDictionaryItemWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/systemDictionaryItem/list.jsp"),
			@Result(name = "input", location = "/admin/systemDictionaryItem/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.systemDictionaryItemList = systemDictionaryItemService.findAllSystemDictionaryItemBySearchWithPage(systemDictionaryItem);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/systemDictionaryItem/edit.jsp"),
			@Result(name = "input", location = "/admin/systemDictionaryItem/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionaryItem.dictItemId", message = "You must select a Id.") })
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemDictionaryItem.getDictItemId();
		}
		systemDictionaryItem = this.systemDictionaryItemService.getSystemDictionaryItemById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/systemDictionaryItem/view.jsp"),
			@Result(name = "input", location = "/admin/systemDictionaryItem/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionaryItem.dictItemId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = systemDictionaryItem.getDictItemId();
		}
		systemDictionaryItem = this.systemDictionaryItemService.getSystemDictionaryItemById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDictionaryItem/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionaryItem.dictItemId", message = "You must select a Id.") })
	public String remove() {
		if(null != cids && cids.length > 0){
			systemDictionaryItemService.deleteSystemDictionaryItemByIds(cids);
		}else{
			systemDictionaryItemService.deleteSystemDictionaryItem(systemDictionaryItem);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDictionaryItem/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionaryItem.dictItemKey", message = "You must enter a value for dictItemKey(save).")})
	public String save() {
		this.systemDictionaryItemService.saveSystemDictionaryItem(systemDictionaryItem);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/systemDictionaryItem/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionaryItem.dictItemId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "systemDictionaryItem.dictItemKey", message = "You must enter a value for dictItemKey(save).")})
	public String update() {
		this.systemDictionaryItemService.updateSystemDictionaryItem(systemDictionaryItem);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = systemDictionaryItemService.findAllSystemDictionaryItemByNameWithIdAndName(""+systemDictionaryItem.getDictItemKey());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/systemDictionaryItem/select.jsp"),
			@Result(name = "input", location = "/admin/systemDictionaryItem/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.systemDictionaryItemList = systemDictionaryItemService.findAllSystemDictionaryItemBySearchWithPage(systemDictionaryItem);
		return ActionSupport.SUCCESS;
	}
	/*/admin/systemDictionaryItem/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/systemDictionaryItem/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/systemDictionaryItem/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = systemDictionaryItem.getDictItemId();
		}
		//this.systemDictionaryItemList = systemDictionaryItemService.findAllSystemDictionaryItemByIds(cids);
		this.json = systemDictionaryItemService.findAllSystemDictionaryItemByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SystemDictionaryItem> getSystemDictionaryItems() {
		return systemDictionaryItemList;
	}

	public void prepare() throws Exception {
		
	}

	public SystemDictionaryItem getSystemDictionaryItem() {
		return systemDictionaryItem;
	}

	public void setSystemDictionaryItem(SystemDictionaryItem systemDictionaryItem) {
		this.systemDictionaryItem = systemDictionaryItem;
	}

}

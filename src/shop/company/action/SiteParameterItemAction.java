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

import shop.company.entity.SiteParameterItem;
import shop.company.service.SiteParameterItemService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/siteParameterItem") })
@ParentPackage("my-default")
public class SiteParameterItemAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SiteParameterItemService siteParameterItemService;
	private List<SiteParameterItem> siteParameterItemList;
	private SiteParameterItem siteParameterItem;
	
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

	public List<SiteParameterItem> getSiteParameterItemList() {
		return siteParameterItemList;
	}

	public void setSiteParameterItemList(List<SiteParameterItem> siteParameterItemList) {
		this.siteParameterItemList = siteParameterItemList;
	}

	public SiteParameterItemAction() {
	}

	@Autowired
	public SiteParameterItemAction(SiteParameterItemService siteParameterItemService) {
		this.siteParameterItemService = siteParameterItemService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/siteParameterItem/add.jsp"),
			@Result(name = "input", location = "/admin/siteParameterItem/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/siteParameterItem/list.jsp"),
			@Result(name = "input", location = "/admin/siteParameterItem/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.siteParameterItemList = siteParameterItemService.findAllSiteParameterItemWithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/siteParameterItem/list.jsp"),
			@Result(name = "input", location = "/admin/siteParameterItem/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.siteParameterItemList = siteParameterItemService.findAllSiteParameterItemBySearchWithPage(siteParameterItem);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/siteParameterItem/edit.jsp"),
			@Result(name = "input", location = "/admin/siteParameterItem/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = siteParameterItem.getParamItemId();
		}
		siteParameterItem = this.siteParameterItemService.getSiteParameterItemById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/siteParameterItem/view.jsp"),
			@Result(name = "input", location = "/admin/siteParameterItem/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameterItem.paramItemId", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = siteParameterItem.getParamItemId();
		}
		siteParameterItem = this.siteParameterItemService.getSiteParameterItemById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/siteParameterItem/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			siteParameterItemService.deleteSiteParameterItemByIds(cids);
		}else{
			siteParameterItemService.deleteSiteParameterItem(siteParameterItem);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/siteParameterItem/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.siteParameterItemService.saveSiteParameterItem(siteParameterItem);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/siteParameterItem/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameterItem.paramItemId", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameterItem.paramKey", message = "You must enter a value for paramKey(save).")})
	public String update() {
		this.siteParameterItemService.updateSiteParameterItem(siteParameterItem);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = siteParameterItemService.findAllSiteParameterItemByNameWithIdAndName(""+siteParameterItem.getParamKey());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/siteParameterItem/select.jsp"),
			@Result(name = "input", location = "/admin/siteParameterItem/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.siteParameterItemList = siteParameterItemService.findAllSiteParameterItemBySearchWithPage(siteParameterItem);
		return ActionSupport.SUCCESS;
	}
	/*/admin/siteParameterItem/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/siteParameterItem/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/siteParameterItem/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = siteParameterItem.getParamItemId();
		}
		//this.siteParameterItemList = siteParameterItemService.findAllSiteParameterItemByIds(cids);
		this.json = siteParameterItemService.findAllSiteParameterItemByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SiteParameterItem> getSiteParameterItems() {
		return siteParameterItemList;
	}

	public SiteParameterItem getSiteParameterItem() {
		return siteParameterItem;
	}

	public void setSiteParameterItem(SiteParameterItem siteParameterItem) {
		this.siteParameterItem = siteParameterItem;
	}

}

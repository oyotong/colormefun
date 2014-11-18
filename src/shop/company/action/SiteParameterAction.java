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

import shop.company.entity.SiteParameter;
import shop.company.entity.SiteParameterItem;
import shop.company.service.SiteParameterItemService;
import shop.company.service.SiteParameterService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/sysParam") })
@ParentPackage("my-default")
public class SiteParameterAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private SiteParameterItemService siteParameterItemService;
    private SiteParameterService siteParameterService;
	private List<SiteParameter> siteParameterList;
	private List<SiteParameterItem> siteParameterItemList;
	private SiteParameter siteParameter;
	
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

	public List<SiteParameter> getSiteParameterList() {
		return siteParameterList;
	}

	public void setSiteParameterList(List<SiteParameter> siteParameterList) {
		this.siteParameterList = siteParameterList;
	}

	public SiteParameterAction() {
	}

	@Autowired
	public SiteParameterAction(SiteParameterService siteParameterService, SiteParameterItemService siteParameterItemService) {
		this.siteParameterService = siteParameterService;
        this.siteParameterItemService = siteParameterItemService;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "/admin/sysParam/add.jsp"),
			@Result(name = "input", location = "/admin/sysParam/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/sysParam/list.jsp"),
			@Result(name = "input", location = "/admin/sysParam/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.siteParameterList = siteParameterService.findAllSiteParameter();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/sysParam/list.jsp"),
			@Result(name = "input", location = "/admin/sysParam/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.siteParameterList = siteParameterService.findAllSiteParameterBySearchWithPage(siteParameter);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/sysParam/edit.jsp"),
			@Result(name = "input", location = "/admin/sysParam/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = siteParameter.getParamKey();
		}
		siteParameter = this.siteParameterService.getSiteParameterById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/sysParam/view.jsp"),
			@Result(name = "input", location = "/admin/sysParam/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = siteParameter.getParamKey();
		}
		siteParameter = this.siteParameterService.getSiteParameterById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/sysParam/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			siteParameterService.deleteSiteParameterByIds(cids);
		}else{
			siteParameterService.deleteSiteParameter(siteParameter);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/sysParam/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.siteParameterItemService.updateAll(siteParameterItemList);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/sysParam/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must enter a value for paramKey(save).")})
	public String update() {
		this.siteParameterService.updateSiteParameter(siteParameter);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = siteParameterService.findAllSiteParameterByNameWithIdAndName(""+siteParameter.getParamKey());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/sysParam/select.jsp"),
			@Result(name = "input", location = "/admin/sysParam/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.siteParameterList = siteParameterService.findAllSiteParameterBySearchWithPage(siteParameter);
		return ActionSupport.SUCCESS;
	}
	/*/admin/sysParam/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/sysParam/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/sysParam/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new String[1];
			cids[0] = siteParameter.getParamKey();
		}
		//this.siteParameterList = siteParameterService.findAllSiteParameterByIds(cids);
		this.json = siteParameterService.findAllSiteParameterByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<SiteParameter> getSiteParameters() {
		return siteParameterList;
	}

	public SiteParameter getSiteParameter() {
		return siteParameter;
	}

	public void setSiteParameter(SiteParameter siteParameter) {
		this.siteParameter = siteParameter;
	}

    public List<SiteParameterItem> getSiteParameterItemList() {
        return siteParameterItemList;
    }

    public void setSiteParameterItemList(List<SiteParameterItem> siteParameterItemList) {
        this.siteParameterItemList = siteParameterItemList;
    }
}

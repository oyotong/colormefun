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
import shop.company.entity.SiteParameter;
import shop.company.entity.SiteParameterItem;
import shop.company.service.SiteParameterItemService;
import shop.company.service.SiteParameterService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces({ @Namespace("/admin/surparameter") })
@ParentPackage("my-default")
public class SecurityParameterAction extends AbstractAction implements
		Preparable, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SiteParameterService siteParameterService;
	private List<SiteParameter> siteParameterList;
	private SiteParameterItemService siteParameterItemService;
	private List<SiteParameterItem> siteParameterItemList;
	private SiteParameter siteParameter;
	private SiteParameterItem siteParameterItem;

	private String[] cids;

	private Object json;

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}

	public void setCids(String[] cids) {
		this.cids = cids;
	}

	public String[] getCids() {
		return this.cids;
	}

	public List<SiteParameter> getSiteParameterList() {
		return siteParameterList;
	}

	public void setSiteParameterList(List<SiteParameter> siteParameterList) {
		this.siteParameterList = siteParameterList;
	}

	public SecurityParameterAction() {
	}

	@Autowired
	public SecurityParameterAction(SiteParameterService siteParameterService,
			SiteParameterItemService siteParameterItemService) {
		this.siteParameterService = siteParameterService;
		this.siteParameterItemService = siteParameterItemService;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/surparameter/edit.jsp"),
			@Result(name = "input", location = "/admin/surparameter/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must select a Id.") })
	public String edit() {
		java.io.Serializable id = null;
		if (null != cids && cids.length > 0) {
			id = cids[0];
		} else {
			id = siteParameter.getParamKey();
		}
		siteParameter = this.siteParameterService.getSiteParameterById(id);
		return ActionSupport.SUCCESS;
	}

	// TODO
	@Action(value = "setlimitaddr", results = {
			@Result(name = "success", location = "/admin/surparameter/setlimitaddr.jsp"),
			@Result(name = "input", location = "/admin/surparameter/setlimitaddr.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String setlimitaddr() {

		return ActionSupport.SUCCESS;
	}

	@Action(value = "setlimitip", results = {
			@Result(name = "success", location = "/admin/surparameter/setlimitip.jsp"),
			@Result(name = "input", location = "/admin/surparameter/setlimitip.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String setlimitip() {

		if (null != this.siteParameterItem){
			this.siteParameterItem.setParamKey("IP_BOUND");
			this.siteParameterItem.setParamItemName("IP");
			this.siteParameterItemService.saveSiteParameterItem(siteParameterItem);
			addActionMessage("�ɹ�����IP�Ρ�");
		}else if(null != cids){
			this.siteParameterItemService.deleteSiteParameterItemByIds(new Integer[]{Integer.parseInt(cids[0])});
			addActionMessage("�ɹ�ɾ��IP�Ρ�");
		}

		this.siteParameterItemList = this.siteParameterItemService.findSiteParameterItemByParameter("IP_BOUND");
		return ActionSupport.SUCCESS;
	}

	// TODO
	@Action(value = "setpwdlength", results = {
			@Result(name = "success", location = "/admin/surparameter/setpwdlength.jsp"),
			@Result(name = "input", location = "/admin/surparameter/setpwdlength.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String setpwdlength() {

		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/surparameter/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must select a Id.") })
	public String remove() {
		if (null != cids && cids.length > 0) {
			siteParameterService.deleteSiteParameterByIds(cids);
		} else {
			siteParameterService.deleteSiteParameter(siteParameter);
		}
		return ActionSupport.SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/surparameter/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must enter a value for paramKey(save).") })
	public String save() {
		this.siteParameterService.saveSiteParameter(siteParameter);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/surparameter/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "siteParameter.paramKey", message = "You must enter a value for paramKey(save).") })
	public String update() {
		this.siteParameterService.updateSiteParameter(siteParameter);
		return ActionSupport.SUCCESS;
	}

	public List<SiteParameter> getSiteParameters() {
		return siteParameterList;
	}

	public void prepare() throws Exception {

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

	public void setSiteParameterItemList(
            List<SiteParameterItem> siteParameterItemList) {
		this.siteParameterItemList = siteParameterItemList;
	}

	public SiteParameterItem getSiteParameterItem() {
		return siteParameterItem;
	}

	public void setSiteParameterItem(SiteParameterItem siteParameterItem) {
		this.siteParameterItem = siteParameterItem;
	}

}

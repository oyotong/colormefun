package ${actionPackage};

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

import ${entityPackage}.${pojo.ClassName};
import ${servicePackage}.${pojo.ClassName}Service;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("${subSystem}/${pojo.VarName}") })
@ParentPackage("my-default")
public class ${pojo.ClassName}Action extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ${pojo.ClassName}Service ${pojo.VarName}Service;
	private List<${pojo.ClassName}> ${pojo.VarName}List;
	private ${pojo.ClassName} ${pojo.VarName};
	
	private ${pojo.IdClassName}[] cids;
	
	private Object json;
	
	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
	
	public void setCids(${pojo.IdClassName}[] cids){
		this.cids = cids;
	}
	
	public ${pojo.IdClassName}[] getCids(){
		return this.cids;
	}

	public List<${pojo.ClassName}> get${pojo.ClassName}List() {
		return ${pojo.VarName}List;
	}

	public void set${pojo.ClassName}List(List<${pojo.ClassName}> ${pojo.VarName}List) {
		this.${pojo.VarName}List = ${pojo.VarName}List;
	}

	public ${pojo.ClassName}Action() {
	}

	@Autowired
	public ${pojo.ClassName}Action(${pojo.ClassName}Service ${pojo.VarName}Service) {
		this.${pojo.VarName}Service = ${pojo.VarName}Service;
	}

	@Action(value = "add", results = { 
			@Result(name = "success", location = "${subSystem}/${pojo.VarName}/add.jsp"),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String add() throws Exception {
		return SUCCESS;
	}

	@Action(value = "list", results = {
			@Result(name = "success", location = "${subSystem}/${pojo.VarName}/list.jsp"),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
		this.${pojo.VarName}List = ${pojo.VarName}Service.findAll${pojo.ClassName}WithPage();
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "${subSystem}/${pojo.VarName}/list.jsp"),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.${pojo.VarName}List = ${pojo.VarName}Service.findAll${pojo.ClassName}BySearchWithPage(${pojo.VarName});
		return ActionSupport.SUCCESS;
	}

	@Action(value = "edit", results = {
			@Result(name = "success", location = "${subSystem}/${pojo.VarName}/edit.jsp"),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = ${pojo.VarName}.get${pojo.Id?cap_first}();
		}
		${pojo.VarName} = this.${pojo.VarName}Service.get${pojo.ClassName}ById(id);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "${subSystem}/${pojo.VarName}/view.jsp"),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "${pojo.VarName}.${pojo.Id}", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = ${pojo.VarName}.get${pojo.Id?cap_first}();
		}
		${pojo.VarName} = this.${pojo.VarName}Service.get${pojo.ClassName}ById(id);
		return ActionSupport.SUCCESS;
	}

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			${pojo.VarName}Service.delete${pojo.ClassName}ByIds(cids);
		}else{
			${pojo.VarName}Service.delete${pojo.ClassName}(${pojo.VarName});
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
		this.${pojo.VarName}Service.save${pojo.ClassName}(${pojo.VarName});
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "${pojo.VarName}.${pojo.Id}", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "${pojo.VarName}.${pojo.Name}", message = "You must enter a value for ${pojo.Name}(save).")})
	public String update() {
		this.${pojo.VarName}Service.update${pojo.ClassName}(${pojo.VarName});
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = ${pojo.VarName}Service.findAll${pojo.ClassName}ByNameWithIdAndName(""+${pojo.VarName}.get${pojo.Name?cap_first }());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "${subSystem}/${pojo.VarName}/select.jsp"),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.${pojo.VarName}List = ${pojo.VarName}Service.findAll${pojo.ClassName}BySearchWithPage(${pojo.VarName});
		return ActionSupport.SUCCESS;
	}
	/*${subSystem}/${pojo.VarName}/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "${subSystem}/${pojo.VarName}/selectedJson.jsp"),
			@Result(name = "input", location = "${subSystem}/${pojo.VarName}/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new ${pojo.IdClassName}[1];
			cids[0] = ${pojo.VarName}.get${pojo.Id?cap_first}();
		}
		//this.${pojo.VarName}List = ${pojo.VarName}Service.findAll${pojo.ClassName}ByIds(cids);
		this.json = ${pojo.VarName}Service.findAll${pojo.ClassName}ByIds(cids);
		return ActionSupport.SUCCESS;
	}

	public List<${pojo.ClassName}> get${pojo.ClassName}s() {
		return ${pojo.VarName}List;
	}

	public ${pojo.ClassName} get${pojo.ClassName}() {
		return ${pojo.VarName};
	}

	public void set${pojo.ClassName}(${pojo.ClassName} ${pojo.VarName}) {
		this.${pojo.VarName} = ${pojo.VarName};
	}

}

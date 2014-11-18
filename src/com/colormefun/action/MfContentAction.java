package com.colormefun.action;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.colormefun.entity.MfContent;
import com.colormefun.service.MfContentService;
import com.opensymphony.sitemesh.Content;
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

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import shop.common.util.FileUtils;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/admin/content") })
@ParentPackage("my-default")
public class MfContentAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MfContentService contentService;
	private List<MfContent> list;
	private MfContent content;
	
	private Integer[] cids;
	
	private Object json;

    private File image1;
    private String image1FileName;
    private String image1ContentType;

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

	public MfContentAction() {
	}

	@Autowired
	public MfContentAction(MfContentService contentService) {
		this.contentService = contentService;
	}

	@Action(value = "addMarquee", results = {
			@Result(name = "success", location = "/admin/content/addMarquee.jsp"),
			@Result(name = "input", location = "/admin/content/addMarquee.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String addMarquee() throws Exception {
		return SUCCESS;
	}

    @Action(value = "add", results = {
            @Result(name = "success", location = "/admin/content/add.jsp"),
            @Result(name = "input", location = "/admin/content/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String add() throws Exception {
        return SUCCESS;
    }

	@Action(value = "list", results = {
			@Result(name = "success", location = "/admin/content/list.jsp"),
			@Result(name = "input", location = "/admin/content/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String list() {
        MfContent content = new MfContent();
        content.setContentType(MfContent.ContentType.question.toString());
        this.list = contentService.findAllMfContentBySearchWithPage(content);
		return ActionSupport.SUCCESS;
	}

    @Action(value = "listMarquee", results = {
            @Result(name = "success", location = "/admin/content/listMarquee.jsp"),
            @Result(name = "input", location = "/admin/content/listMarquee.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String listMarquee() {
        MfContent content = new MfContent();
        content.setContentType(MfContent.ContentType.marquee.toString());
        this.list = contentService.findAllMfContentBySearchWithPage(content);
        return ActionSupport.SUCCESS;
    }
	
	@Action(value = "search", results = {
			@Result(name = "success", location = "/admin/content/list.jsp"),
			@Result(name = "input", location = "/admin/content/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String search() {
		this.list = contentService.findAllMfContentBySearchWithPage(content);
		return ActionSupport.SUCCESS;
	}

    @Action(value = "searchMarquee", results = {
            @Result(name = "success", location = "/admin/content/listMarquee.jsp"),
            @Result(name = "input", location = "/admin/content/listMarquee.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String searchMarquee() {
        content.setContentType(MfContent.ContentType.marquee.toString());
        this.list = contentService.findAllMfContentBySearchWithPage(content);
        return ActionSupport.SUCCESS;
    }

	@Action(value = "edit", results = {
			@Result(name = "success", location = "/admin/content/edit.jsp"),
			@Result(name = "input", location = "/admin/content/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String edit() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = content.getId();
		}
		content = this.contentService.getMfContentById(id);
		return ActionSupport.SUCCESS;
	}

    @Action(value = "editMarquee", results = {
            @Result(name = "success", location = "/admin/content/addMarquee.jsp"),
            @Result(name = "input", location = "/admin/content/addMarquee.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String editMarquee() {
        java.io.Serializable id = null;
        if(null != cids && cids.length > 0){
            id = cids[0];
        }else{
            id = content.getId();
        }
        if (null != image1) {
            content.setPic1(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }
        content = this.contentService.getMfContentById(id);
        return ActionSupport.SUCCESS;
    }
	
	@Action(value = "view", results = {
			@Result(name = "success", location = "/admin/content/view.jsp"),
			@Result(name = "input", location = "/admin/content/view.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = { @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "content.id", message = "You must select a Id.") })
	public String view() {
		java.io.Serializable id = null;
		if(null != cids && cids.length > 0){
			id = cids[0];
		}else{
			id = content.getId();
		}
		content = this.contentService.getMfContentById(id);
		return ActionSupport.SUCCESS;
	}

    @Action(value = "about", results = {
            @Result(name = "success", location = "/admin/content/about.jsp"),
            @Result(name = "input", location = "/admin/content/about.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String about() {
        MfContent content = new MfContent();
        content.setContentType(MfContent.ContentType.about.toString());
        this.list = contentService.findAllMfContentBySearchWithPage(content);
        if(null != this.list && this.list.size() > 0){
            this.content = list.get(0);
        }
        return ActionSupport.SUCCESS;
    }

	@Action(value = "remove", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/content/list.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String remove() {
		if(null != cids && cids.length > 0){
			contentService.deleteMfContentByIds(cids);
		}else{
			contentService.deleteMfContent(content);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/content/add.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String save() {
        if (null != image1) {
            content.setPic1(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }
		this.contentService.saveMfContent(content);
		return ActionSupport.SUCCESS;
	}

    @Action(value = "saveAbout", results = {
            @Result(name = "success", type = "redirectAction", params = {
                    "actionName", "about" }),
            @Result(name = "input", location = "/admin/content/about.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String saveAbout() {
        content.setContentType(MfContent.ContentType.about.name());
        this.contentService.saveMfContent(content);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "saveMarquee", results = {
            @Result(name = "success", type = "redirectAction", params = {
                    "actionName", "listMarquee" }),
            @Result(name = "input", location = "/admin/content/addMarquee.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
            "validation.validateAnnotatedMethodOnly", "true" }))
    public String saveMarquee() {
        content.setContentType(MfContent.ContentType.marquee.name());

        if (null != image1) {
            content.setPic1(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }

        this.contentService.saveMfContent(content);
        return ActionSupport.SUCCESS;
    }

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirectAction", params = {
					"actionName", "list" }),
			@Result(name = "input", location = "/admin/content/edit.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	@Validations(requiredFields = {
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "content.id", message = "You must select a Id."),
			@RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "content.title", message = "You must enter a value for title(save).")})
	public String update() {
        if (null != image1) {
            content.setPic1(FileUtils.saveUploadImage(image1,
                    image1FileName));
        }
		this.contentService.updateMfContent(content);
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "searchJson", results = {
			@Result(name = "success", location = "/common/json.jsp"),
			@Result(name = "input", location = "/common/json.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String searchJson() {
		json = contentService.findAllMfContentByNameWithIdAndName(""+content.getTitle());
		return ActionSupport.SUCCESS;
	}
	
	@Action(value = "select", results = {
			@Result(name = "success", location = "/admin/content/select.jsp"),
			@Result(name = "input", location = "/admin/content/select.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String select() {
		this.list = contentService.findAllMfContentBySearchWithPage(content);
		return ActionSupport.SUCCESS;
	}
	/*/admin/content/selected.jsp*/
	@Action(value = "selected", results = {
			@Result(name = "success", location = "/admin/content/selectedJson.jsp"),
			@Result(name = "input", location = "/admin/content/selectedJson.jsp") }, interceptorRefs = @InterceptorRef(value = "MyStack", params = {
			"validation.validateAnnotatedMethodOnly", "true" }))
	public String selected() {
		if(null == cids || cids.length == 0){
			cids = new Integer[1];
			cids[0] = content.getId();
		}
		//this.list = contentService.findAllMfContentByIds(cids);
		this.json = contentService.findAllMfContentByIds(cids);
		return ActionSupport.SUCCESS;
	}

    public List<MfContent> getList() {
        return list;
    }

    public void setList(List<MfContent> list) {
        this.list = list;
    }

    public MfContent getContent() {
        return content;
    }

    public void setContent(MfContent content) {
        this.content = content;
    }

    public File getImage1() {
        return image1;
    }

    public void setImage1(File image1) {
        this.image1 = image1;
    }

    public String getImage1FileName() {
        return image1FileName;
    }

    public void setImage1FileName(String image1FileName) {
        this.image1FileName = image1FileName;
    }

    public String getImage1ContentType() {
        return image1ContentType;
    }

    public void setImage1ContentType(String image1ContentType) {
        this.image1ContentType = image1ContentType;
    }
}

package shop.company.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import shop.company.entity.QSLNews;
import shop.company.service.QSLNewsService;
import shop.common.action.auto.AbstractAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Scope("prototype")
@Namespaces( { @Namespace("/")})
@ParentPackage("my-default")
public class MessageAction extends AbstractAction implements Preparable,
		Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private QSLNewsService qSLNewsService;
	private List<QSLNews> newsList;
	private QSLNews news;
	
	private String message;
	
	public List<QSLNews> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<QSLNews> qSLNewsList) {
		this.newsList = qSLNewsList;
	}

	@Autowired
	public MessageAction(QSLNewsService qSLNewsService) {
		this.qSLNewsService = qSLNewsService;
	}
	
	@Action(value = "addMessage", results = {
			@Result(name = "success", location = "/contact.jsp"),
			@Result(name = "input", location = "/contact.jsp") })
	public String addMessage() {
		
		if(null == news || news.getContent().length() < 5 || news.getContent().length() > 200){
			return ActionSupport.ERROR;
		}
		
		news.setEntryId("user");
		news.setCategoryType("message");
		news.setEntryDatetime(new Date());
		news.setShowFlag("Y");
		news.setTopFlag("Y");
		
		this.qSLNewsService.saveQSLNews(news);
		message = "留言已提交，谢谢意见与建议，我们会尽快和您联系！";
		
		return ActionSupport.SUCCESS;
	}

	public List<QSLNews> getQSLNewss() {
		return newsList;
	}

	public String getMessage() {
		return message;
	}

	public QSLNews getNews() {
		return news;
	}

	public void setNews(QSLNews qSLNews) {
		this.news = qSLNews;
	}

}

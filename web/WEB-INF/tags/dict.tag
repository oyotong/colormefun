<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@tag import="shop.company.entity.SystemDictionaryItem"%>
<%@tag import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="shop.company.service.SystemDictionaryItemService"%>
<%@tag import="shop.company.entity.SystemDictionary"%>
<%@attribute name="var" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="dict" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="key" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="def" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%
if(application.getAttribute("SystemDictionaryItemService") == null){
	application.setAttribute("SystemDictionaryItemService",WebApplicationContextUtils.getWebApplicationContext(application).
			getBeansOfType(SystemDictionaryItemService.class).values().iterator().next());
}
String dict = jspContext.findAttribute("dict").toString();
List<SystemDictionaryItem> systemDictionaryItems = (List<SystemDictionaryItem>)session.getAttribute(dict);
if(systemDictionaryItems == null){
	systemDictionaryItems = ((SystemDictionaryItemService)application
			.getAttribute("SystemDictionaryItemService")).findSystemDictionaryItemByDictId(dict);
	session.setAttribute(dict,systemDictionaryItems);
}
String message = null;
for(SystemDictionaryItem i : systemDictionaryItems){
	if(i.getDictItemKey().equals(jspContext.findAttribute("key"))){
		message = (i.getDictItemValue());
	}
}
if(null == message){message = (jspContext.findAttribute("def")+"");}

if(null != var){jspContext.setAttribute(var+"",message);}
else{out.print(message);}
%>
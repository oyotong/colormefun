<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="shop.company.service.SystemDictionaryItemService"%>
<%@tag import="shop.company.entity.SystemDictionaryItem"%>
<%@tag import="java.util.*"%>
<%@attribute name="name" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="image" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="id" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="classStyle" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="dict" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="showAllText" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="showAllValue" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="value" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="hide" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="disabled" type="java.lang.Boolean" required="false"
	rtexprvalue="true"%>
<%
if(application.getAttribute("SystemDictionaryItemService") == null){
	application.setAttribute("SystemDictionaryItemService",WebApplicationContextUtils.getWebApplicationContext(application).
			getBeansOfType(SystemDictionaryItemService.class).values().iterator().next());
}
String dict = jspContext.findAttribute("dict").toString();

if(session.getAttribute(dict) == null){
	List<SystemDictionaryItem> systemDictionaryItem = ((SystemDictionaryItemService)application
			.getAttribute("SystemDictionaryItemService")).findSystemDictionaryItemByDictId(dict);
	session.setAttribute(dict,systemDictionaryItem);
}
%>
<select name="${name }" id="${id }" class="${classStyle }"${disabled?' disabled':''}>
		<c:if test="${not empty showAllText}">
		<option value="${showAllValue }">${showAllText }</option>
		</c:if>
		<c:forEach var="i" items="${sessionScope[dict] }">
		<c:if test="${empty hide || not fn:contains(hide,i.dictItemKey) }">
		<option  ${value eq i.dictItemKey?'selected="selected"':'' } value="${i.dictItemKey }">${i.dictItemValue }</option>
		</c:if>
		</c:forEach>
</select>
<script type="application/javascript">$('#${id }').val('${value}');</script>
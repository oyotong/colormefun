<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="shop.company.service.SystemDictionaryItemService"%>
<%@tag import="shop.company.entity.SystemDictionaryItem"%>
<%@tag import="java.util.*"%>
<%@attribute name="name" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="id" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="classStyle" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="dict" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="showComment" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="value" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="img" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="colspan" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="hide" type="java.lang.Object" required="false"
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
<div>
<c:set var="num" value="0"></c:set>
<c:forEach var="i" varStatus="idx" items="${sessionScope[dict] }">
<c:if test="${empty hide || not fn:contains(hide,i.dictItemKey) }">
<input id="${id }_${idx.index}" type="radio" name="${name }" onclick="$('#${id}').val($(this).val());" class="${classStyle }"  value="${i.dictItemKey }"  ${value eq i.dictItemKey?'checked="checked"':'' } />
<c:if test="${img eq true}"><img src="${pageContext.request.contextPath }/images/${i.dictItemValue}"/></c:if>
<c:if test="${img ne true}">${i.dictItemValue }</c:if>
<c:if test="${showComment eq 'true'}">(${i.dictItemComment })</c:if>
<c:if test="${not empty colspan}">
<c:set var="num" value="${num+1}"></c:set>
<c:if test="${num mod colspan == 0}"><br/></c:if>
</c:if>
</c:if>
</c:forEach>
<input type="hidden" value="${value }" id="${id }" />
</div>

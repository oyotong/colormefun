<%@tag import="shop.company.service.EshopSortService"%>
<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="shop.company.service.EshopSortService"%>
<%@tag import="shop.company.entity.EshopSort"%>
<%@tag import="java.util.*"%>
<%@attribute name="name" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="id" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="classStyle" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="showAllText" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="showAllValue" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="value" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%
if(application.getAttribute("EshopSortService") == null){
	application.setAttribute("EshopSortService",WebApplicationContextUtils.getWebApplicationContext(application).
			getBeansOfType(EshopSortService.class).values().iterator().next());
}
if(session.getAttribute("_sortList_") == null){
	List<EshopSort> sortList = ((EshopSortService)application
			.getAttribute("EshopSortService")).findAllEshopSortWithTree();
	session.setAttribute("_sortList_",sortList);
}
%>
<select name="${name }" id="${id }" class="${classStyle }">
<c:if test="${not empty showAllText}">
<option value="${showAllValue }">${showAllText }</option>
</c:if>
<c:forEach var="i" items="${sessionScope['_sortList_'] }">
<c:if test="${fn:length(i.sortMask) eq 3 }"><optgroup style="color:red" label="${i.sortName }"></optgroup></c:if>
<c:if test="${fn:length(i.sortMask) eq 6 }"><optgroup style="color:blue" label="  ${i.sortName }"></optgroup></c:if>
<c:if test="${fn:length(i.sortMask) eq 9 }"><option style="color:#cccccc" ${value eq i.sortId?'selected="selected"':'' } value="${i.sortId }">&nbsp;&nbsp;&nbsp;&nbsp;${i.sortName }</option></c:if>
</c:forEach>
</select>
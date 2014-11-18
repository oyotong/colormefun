<%@tag import="shop.company.entity.EshopMemberFlow"%>
<%@tag import="shop.company.service.EshopMemberFlowService"%>
<%@tag import="java.util.*"%><%@tag language="java" isELIgnored="false" pageEncoding="UTF-8" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@attribute name="name" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="id" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="value" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="cssStyle" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%
if(application.getAttribute("EshopMemberFlowService") == null){
	application.setAttribute("EshopMemberFlowService",WebApplicationContextUtils.getWebApplicationContext(application).
			getBeansOfType(EshopMemberFlowService.class).values().iterator().next());
}
if(session.getAttribute("_flowList") == null){
	List<EshopMemberFlow> flowList = ((EshopMemberFlowService)application
			.getAttribute("EshopMemberFlowService")).findAllEshopMemberFlow();
	session.setAttribute("_flowList",flowList);
}
%>
<select name="${name }" id="${id }" class="${cssStyle }">
<option value="">所有</option>
<c:forEach var="f" items="${sessionScope['_flowList'] }">
<c:if test="${old.flowProvince ne f.flowProvince }"><optgroup style="color: red" label="${f.flowProvince }"></optgroup></c:if>
<c:if test="${old.flowCity ne f.flowCity }"><optgroup style="color: blue" label=" ${f.flowCity }"></optgroup></c:if>
<option value="${f.flowId }" ${f.flowProvince eq eshopMemberFlow.flowProvince 
	and f.flowCity eq eshopMemberFlow.flowCity 
	and f.flowArea eq eshopMemberFlow.flowArea?'selected="selected"':'' } >&nbsp;&nbsp;${f.flowArea}[${f.flowId }]</option>
<c:set var="old" value="${f }" />
</c:forEach></select>
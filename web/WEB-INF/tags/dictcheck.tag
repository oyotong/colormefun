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
	rtexprvalue="true"%>
<%@attribute name="showComment" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="checkAll" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="keyEqValue" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="value" type="java.lang.Object" required="false"
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

<input type="hidden" name="${name }" value="${value }" id="${id }"/>
<c:forEach var="i" varStatus="idx" items="${sessionScope[dict] }">
<input id="${id }_${idx.index}" type="checkbox" name="${id }_$" class="${classStyle }" onclick="__${id }checked();" value="${empty keyEqValue?i.dictItemKey:i.dictItemValue }"  ${not empty checkAll?'checked=checked':fn:contains(value , i.dictItemKey)?'checked="checked"':'' } >${i.dictItemValue }
<c:if test="${showComment eq 'true'}">(${i.dictItemComment })</c:if>
</c:forEach>
<script type="text/javascript">
function __${id }checked(){
	var values = "";
	var array = $("input[name='${id }_$']");
	for(var i=0;i<array.length;i++){
		var o = $(array[i]);
		if(o.attr('checked')){
			values += $(array[i]).val() + ",";
		}
	}	
	if(values.charAt(values.length - 1) == ','){
		values = values.substring(0,values.length-1);
	}
	$('#${id }').val(values);
}
__${id }checked();
</script>
</div>

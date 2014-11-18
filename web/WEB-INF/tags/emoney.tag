<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@tag import="shop.company.entity.SystemDictionaryItem"%>
<%@tag import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="currentMember" value="${sessionScope['MemberSessionUser'] }"></c:set>
<c:if test="${not empty currentMember }">
<div id="_emoneyInf"></div>
<script type="text/javascript">
<!--
function _loadEmoneyInf(){
	$('#_emoneyInf').load('${pageContext.request.contextPath }/member/account/emoney.jsp');
	setTimeout("_loadEmoneyInf()",3000); //定时器
}
_loadEmoneyInf();
//-->
</script>
</c:if>
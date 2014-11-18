<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@tag import="java.util.*"%>
<%@attribute name="name" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="id" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="classStyle" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="value" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<fmt:formatDate var="_time_id" value="${value }" pattern="${LONG_DATE}"/>
<input name="${name}" type="text" id="${id}" value="${_time_id }" class="Wdate" onclick="WdatePicker({dateFmt:'${LONG_DATE}'})" />

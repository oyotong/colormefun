<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty actionErrors}">
<div id="suggest">
	<div id="system-message">
	<dd class="error">
	<ul>
	<c:forEach var="m" items="${actionErrors}">
		<li>${m }</li>
	</c:forEach>
	</ul>
	</dd>
	</div>
</div>
</c:if>
<c:if test="${not empty actionMessages}">
<div id="suggest">
	<div id="system-message">
	<dd class="message">
	<ul>
	<c:forEach var="m" items="${actionMessages}">
		<li>${m }</li>
	</c:forEach>
	</ul>
	</dd>
	</div>
</div>
</c:if>
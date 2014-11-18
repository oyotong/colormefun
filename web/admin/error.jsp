<%@page pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false" import="java.util.*" contentType="text/html; charset=UTF-8"  %>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<html>
<head>
<title>出错了</title>
<link rel="stylesheet" type="text/css" href="${ROOT}/css/StyleSheet1.css"></link>
</head>
<body>
<div class="man_zone">
<h1>出错了：</h1>
<pre>
可能是系统内部错误！
</pre>
</div>
</body>
</html>
<%--
<%
StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
exception.printStackTrace(pw);
pageContext.setAttribute("error",sw.toString());
%>
<%@page import="java.io.*"%>
<html>
<head>
<title>出错了</title>
<link rel="stylesheet" type="text/css" href="${ROOT}/css/StyleSheet1.css"></link>
</head>
<body>
<div class="man_zone">
<h1>出错了:</h1>
<pre>
${error }
</pre>
</div>
</body>
</html>
 --%>

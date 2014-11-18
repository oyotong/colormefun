<%@page pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false"	contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>文件没有找到</title>
<link rel="stylesheet" type="text/css" href="${ROOT}/css/StyleSheet1.css"></link>
</head>
<body>
<div id="man_zone">
<h1>${pageContext.errorData.statusCode} 文件没有找到 :</h1>
<pre>
${pageContext.errorData.requestURI}
</pre></div>
</body>
</html>

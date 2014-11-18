<%@tag import="shop.company.entity.SystemDictionaryItem"%><%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="shop.company.service.SystemDictionaryService"%>
<%@tag import="shop.company.entity.SystemDictionary"%>
<%@attribute name="name" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="id" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="value" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="height" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<fck:editor instanceName="${name}" basePath="/js/Fckeditor" height="${height}">   
		<jsp:attribute name="value">${value}</jsp:attribute>   
</fck:editor>
<%--
	
<textarea name="${name}" id="${id }" style="width: 100%; height: ${height}px">${value}</textarea>
<script type="text/javascript">
<!--
var sBasePath = '${ROOT }/js/Fckeditor/';
var oFCKeditor = new FCKeditor( '${id}' ) ;
oFCKeditor.BasePath	= sBasePath ;
oFCKeditor.ReplaceTextarea() ;
//-->
</script>
--%>
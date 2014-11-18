<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@tag import="java.util.*"%>
<%@attribute name="upname" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="name" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="id" type="java.lang.Object" required="true"
	rtexprvalue="false"%>
<%@attribute name="classStyle" type="java.lang.Object" required="false"
	rtexprvalue="false"%>
<%@attribute name="value" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="maxWidth" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<%@attribute name="maxHeight" type="java.lang.Object" required="true"
	rtexprvalue="true"%>
<input value="" class="${classStyle }"  onchange="$('_showImage${id }').attr('width','');$('_showImage${id }').attr('src',this.value)" name="${upname }" type="file">
<input id="${id }" value="${value }" name="${name }" type="hidden">
<img alt="预览图片" id="_showImage${id }" 
 onload="if(this.width > ${maxWidth} && this.height*(${maxWidth}/this.width) <= ${maxHeight} ){this.width = ${maxWidth}}else if(this.height > ${maxHeight}){this.height = ${maxHeight}}" src="${pageContext.request.contextPath}${value }" />
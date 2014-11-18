<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>左侧导航</title>
<style type="text/css">
@IMPORT url("${ROOT}/admin/css/leftmenu.css");
</style>
<script type="text/javascript" src="${ROOT}/admin/js/common.js"></script>
<script type="text/javascript">
	function $(id){return document.getElementById(id);}
	function ChgStyle(t) {
		var obj = $("NavMenu");
		for (i = 0; i < obj.children.length; i++) {
			obj.children[i].id = "";
			obj.children[i].id = (obj.children[i] == t) ? "Menu_On" : "";
		}
	}
</script>
</head>
<body>
<span id="lblmenu">
<table id="Table1" cellpadding="0" cellspacing="0" height="100%">
	<tbody>
		<tr>
			<td style="padding-top: 10px;" class="left_color" id="NavMenu"
				valign="top">
			<div
				style="font-weight: bold; color: rgb(86, 86, 86); font-size: 14px;"><img
				src="${ROOT}/admin/images/title.gif">常用快捷菜单&nbsp;</div>
			<c:forEach var="sm" items="${shortmenu}">
			<a
				href="${ROOT }${sm.menu.purviewUrl }"
				target="frmright" onclick=ChgStyle(this);>${sm.menu.purviewName }</a>
			</c:forEach>
			</td>
		</tr>
	</tbody>
</table>
</span>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="shop.company.entity.AuthRole"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色授权</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
<script type="text/javascript"> 
<!--
jQuery(function(){
    jQuery("#roleName").validate({
        expression: "if (val) return true; else return false;",
        message: "角色名称是必填项。"
    });
});
function CheckAll(){
	$(".purviewId").each(function(){
			  if($(this).attr('checked')){
				  $(this).removeAttr('checked');
			  }else{
			      $(this).attr('checked',true);
			  }

		});
}
// -->
</script>
</head>
<body>
<form name="form1" method="post" action="${ROOT}/admin/system/role/update.do" id="form1">
<input name="authRole.roleId" id="roleId" value="${authRole.roleId }" type="hidden"/>
<div>
<table width="100%">
	<tr>
		<td><span id="Label18">请输入角色名称</span>：<input name="authRole.roleName" id="roleName" value="${authRole.roleName }"
			type="text" maxlength="10"/>
			<input type="submit" name="Button1" value="确定保存"
			id="Button1" class="button"
			onmouseover="this.className='button_mouseover'"
			onmouseout="this.className='button_mouseout'" />&nbsp;</td>
		<td align="right"><input id="AllCheck" type="checkbox"
			name="AllCheck"
			onclick="javascript:CheckAll()" /><label
			for="CheckBox1">全选/反选</label></td>
	</tr>
</table>
<table cellpadding="3" cellspacing="1" class="tableborder1">
	<tr>
		<td class="titlebg1" width="15%"><span id="Label19">分类名</span></td>
		<td class="titlebg1"><span id="Label20">权限</span></td>
	</tr>
	<%AuthRole authRole = ((AuthRole)pageContext.findAttribute("authRole")); %>
	<c:set var="i" value="${0 }"></c:set>
	<c:forEach var="p" items="${authPurviewList }">
		<tr>
			<td class="tablebody1" width="15%"><span id="Label1">${p.purviewName}</span></td>
			<td class="tablebody1">
			<table border="0">
				<tr>
					<c:forEach varStatus="st" var="c" items="${p.authPurviews }">
					<td>
						<input class="purviewId" <%=(null != authRole && authRole.getAuthPurviews().contains(pageContext.findAttribute("c")))?"checked=\"checked\"":"" %> 
							type="checkbox" value="${c.purviewId }" name="authRole.authPurviews.makeNew[${i }].purviewId" />
						<label for="CheckBoxList1_0">${c.purviewName }</label></td>
						<c:set var="i" value="${i+1 }"></c:set>
					<c:if test="${(st.index+1) mod 6 eq 5 }"></tr><tr></c:if>
					</c:forEach>
				</tr>
			</table>
			</td>
		</tr>
	</c:forEach>
</table>

</div>
</form>
</body>
</html>


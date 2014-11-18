<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
<script type="text/javascript">
<!--
function $(id){return document.getElementById(id);}
function OnReset(map){
	for(var k in map){
		$(k).value = map[k];
	}
}
function OnUpdate(form,id) {
	form.action = "${ROOT }/admin/system/role/edit.do?cids="+id;
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该角色吗？"))
	form.action = "${ROOT }/admin/system/role/remove.do?cids="+id;
}
function OnSearch(form) {
	form.action = "${ROOT }/admin/system/role/search.do";
}
function OnEditPurview(form,id) {
	form.action = "${ROOT }/admin/system/role/editPurview.do?cids="+id;
}
function OnSubmit(form) {
	return true;
}
// -->
</script>
</head>
<body style="text-align: left;">
<form name="form1" method="post" action="#" onsubmit="javascript:return OnSubmit(this);" id="form1">
<div id="Panel1" style="width: 100%;">
        <div id="PanelTR1">
            </div><div id="PanelTR2">
		
            </div><table class="tableborder1" cellpadding="3" cellspacing="1">           
            <tbody><tr>
                <td colspan="4" class="titlebg">
                    <span id="Label5">角色列表</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label6">角色编号</span>：</td>
                <td class="tablebody1" width="35%">
                	<input name="authRole.roleId" maxlength="200" value="${authRole.roleId }" id="roleId" type="text"/>
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label7">角色名称</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="authRole.roleName" value="${authRole.roleName }" id="roleName" type="text"/>
                </td>
            </tr> 
            <tr>
                <td class="tablebody1" colspan="4" style="text-align: right;">                
                    <input name="Button1" value="搜索角色" onclick='javascript:OnSearch(this.form);' id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" type="submit"/>
                    <input name="Button2" value="重置内容" onclick='javascript:OnReset({"roleId":"","roleName":""});' id="Button2" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" type="submit"/>&nbsp;
                </td>
            </tr>
        </tbody></table>
</div>        
<span id="lberr" style="color: Red;"></span>
<br/>
<div>
<table class="blueGrid" id="GridView1" border="0" cellpadding="3" cellspacing="1">
		<tbody><tr class="Gridhead" style="font-weight: normal; font-style: normal;">
			<th scope="col">角色编号</th><th scope="col">角色名称</th><th scope="col" width="50%">权限描述</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="r" varStatus="status" items="${authRoleList}" >
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="">
			<td>${r.roleId }</td>
			<td>
                <span><a href="#" onclick="OnEditPurview(document.form1,'${r.roleId}');document.form1.submit();">${r.roleName }</a></span>
            </td><td>
            <c:forEach varStatus="st" var="p" items="${r.authPurviews}">
            	<c:if test="${st.index ne 0}">,</c:if>
                <span>${p.purviewName }</span>
            </c:forEach>
            </td>
            <td>
                <input name="button1" value="修改[名称/权限]" onclick="OnEditPurview(this.form,'${r.roleId}');" class="Gridbutton" type="submit" />
                <input name="button2" value="删除角色" onclick="OnDelete(this.form,'${r.roleId}');" class="Gridbutton" type="submit" />
            </td>
		</tr>
		</c:forEach>
	</tbody></table>
	<m:pagination></m:pagination>
</div>
        <br>
        <span style="color: Red;">操作说明：</span>&nbsp;<br>
        <span style="color: Red;">1、通过所有查询条件的集合查询相应的角色，查询结果以表格形式显示<br></span>
        <span style="color: Red;">2、可以对角色名进行修改、修改权限、删除等操作<br></span>
        <span style="color: Red;">3、点击角色标题查看角色内容<br></span>
  
        </form>
</body></html>
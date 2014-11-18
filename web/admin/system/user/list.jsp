<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理员管理</title>
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
function OnSearch(form){
	form.action = "${ROOT }/admin/system/user/search.do";
}
function OnUpdate(form,id) {
	form.action = "${ROOT }/admin/system/user/edit.do?cids="+id;
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该管理员吗？"))
	form.action = "${ROOT }/admin/system/user/remove.do?cids="+id;
}
function OnSubmit(form) {
	
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
                <td colspan="6" class="titlebg">
                    <span id="Label5">管理员列表</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label6">管理员编号</span>：</td>
                <td class="tablebody1" width="20%">
                	<input name="authUser.userName" maxlength="200" value="${authUser.userName }" id="userName" type="text"/>
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label7">管理员姓名</span>：</td>
                <td class="tablebody1" width="20%">
                    <input name="authUser.userRealname" value="${authUser.userRealname }" id="userRealname" type="text"/>
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label1">角色</span></td>
                <td class="tablebody1" width="15%">
                    <select name="authUser.authRoles.makeNew[0].roleId" id="roleId" class="select">
						<option value="">所有</option>
						<c:forEach var="r" items="${authUser.authRoles }">
							<c:set var="role" value="${r}"></c:set>
						</c:forEach>
						<c:forEach var="r" items="${authRoleList}">
						<option ${role.roleId eq r.roleId?'selected="selected"':'' } value="${r.roleId }">${r.roleName }</option>
						</c:forEach>
					</select></td>
            </tr>
            <tr>
                <td class="tablebody1" colspan="6" style="text-align: right;">                
                    <input name="Button1" value="开始查询" onclick='javascript:OnSearch(this.form);' id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" type="submit"/>
                    <input name="Button2" value="重置内容" onclick='javascript:OnReset({userName:"",userRealname:"",roleId:""});' id="Button2" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" type="submit"/>&nbsp;
                </td>
            </tr>
        </tbody></table>
</div>        
<span id="lberr" style="color: Red;"></span>
<br/>
<div>
<table class="blueGrid" id="GridView1" border="0" cellpadding="3" cellspacing="1">
		<tbody><tr class="Gridhead" style="font-weight: normal; font-style: normal;">
			<th scope="col">管理员编号</th><th scope="col">管理员姓名</th><th scope="col">角色</th><th scope="col">职位描述</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="u" varStatus="status" items="${authUserList}" >
		<c:if test="${u.userName ne 'admin' }">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="">
			<td>${u.userName }</td>
			<td >
                <span><a href="#" onclick="OnUpdate(document.form1,'${u.userName}');document.form1.submit();">${u.userRealname}</a></span>
            </td><td >
                <span ><c:forEach var="r" items="${u.authRoles }">${r.roleName }</c:forEach></span>
            </td >
            <td >${u.userComment }</td>
            <td >
                <input name="button1" value="修改资料" onclick="OnUpdate(this.form,'${u.userName}');" class="Gridbutton" type="submit" />
                <input name="button2" value="删除" onclick="OnDelete(this.form,'${u.userName}');" class="Gridbutton" type="submit" />
            </td>
		</tr>
		</c:if>
		</c:forEach>
	</tbody></table>
	<m:pagination></m:pagination>
</div>
        <br>
        <span id="Label12" style="color: Red;">操作说明：</span>&nbsp;<br>
        <span id="Label13" style="color: Red;">1、通过所有查询条件的集合查询相应的管理员，查询结果以表格形式显示<br></span>
        <span id="Label14" style="color: Red;">2、可以对管理员进行修改、删除操作<br></span>
        <span id="Label16" style="color: Red;">3、点击管理员姓名查看管理员内容<br></span>
  
        </form>
</body></html>
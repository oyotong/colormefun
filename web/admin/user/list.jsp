<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>客户列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<script type="text/javascript">
<!--
function OnResetPwd(form,id, name){
    if(window.confirm("确定要重置"+name+"【"+id+"】的密码吗？")){
        form.action = "${ROOT }/admin/user/resetPwd.do?cids="+id;
        form.submit();
    }
}
function OnReset(map){
	for(var k in map){
		$("#"+k).val(map[k]);
	}
}
function OnSearch(form){
	form.action = "${ROOT }/admin/user/search.do";
}
function OnView(id) {
	var page = "${ROOT }/admin/user/view.do?cids="+id;
	window.open(page,"_blank");
}
function OnUpdate(form,id) {
	form.action = "${ROOT }/admin/user/edit.do?cids="+id;
	form.submit();
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该客户吗？")){
        form.action = "${ROOT }/admin/user/remove.do?cids="+id;
        form.submit();
    }
}
function OnSubmit(form) {
	
}
// -->
</script>
</head>
<body style="text-align: left;">
<form name="form1" method="post" action="#" onsubmit="javascript:return OnSubmit(this);" id="form1">
<div id="Panel1" style="width:100%;">
	        
        <table cellpadding="3" cellspacing="1" class="tableborder1">           
            <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">客户列表</span></td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label1">电话号码</span>：</td>
                <td class="tablebody1">
                    <input type="text" value="${user.mobilePhone }" name="user.mobilePhone" style="width: 100px"  maxlength="20" id="user_mobilePhone" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label2">姓名</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="user.userName" type="text" value="${user.userName }" style="width: 100px"  maxlength="50" id="user_userName" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label5">性别</span>：</td>
                <td class="tablebody1">
                    <input type="radio" value=""${empty user.sex?' checked="checked"':'' } name="user.sex" />未知
                    <input type="radio" value="M"${user.sex eq 'M'?' checked="checked"':'' } name="user.sex" />男
                    <input type="radio" value="F"${user.sex eq 'F'?' checked="checked"':'' } name="user.sex" />女
                </td>
                <td class="tablebody1">
                    <span id="Label7">电子邮件</span>：</td>
                <td class="tablebody1">
                    <input name="user.email" type="text" value="${user.email }" style="width: 200px"  maxlength="200" id="user_email" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label16">生日(从)</span>：</td>
                <td class="tablebody1">
                    <m:date name="user.birthday" value="${user.birthday }" id="user_birthday"></m:date>
                </td>
                <td class="tablebody1">
                    <span id="Label6">生日(到)</span>：</td>
                <td class="tablebody1">
                    <m:date name="user.birthday2" value="${user.birthday2 }" id="user_birthday2"></m:date>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label20">注册时间(从)</span>：</td>
                <td class="tablebody1">
                    <m:time name="user.registeredDate" value="${user.registeredDate }" id="user_registeredDate"></m:time>
                </td>
                <td class="tablebody1">
                    <span id="Label21">注册时间(到)</span>：</td>
                <td class="tablebody1">
                    <m:time name="user.registeredDate2" value="${user.registeredDate2 }" id="user_registeredDate2"></m:time>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label10">喜欢的颜色</span>：</td>
                <td class="tablebody1" colspan="3">
                    <input name="user.myColor" type="text" value="${user.myColor }" style="width: 200px"  maxlength="200" id="user_myColor" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label9">兴趣爱好</span>：</td>
                <td class="tablebody1" colspan="3">
                    <input name="user.favorite" type="text" value="${user.favorite }" style="width: 200px"  maxlength="200" id="user_favorite" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" colspan="4" style="text-align: right;">                
                    <input type="submit" name="Button1" value="开始查询" onclick="javascript:OnSearch(this.form)" id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />
                    <input type="button" name="Button2" value="重置内容" onclick="javascript:OnReset({title:'',entryDatetime:'',entryDatetime2:''})" id="Button2" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                </td>
            </tr>
        </table>   
        
</div>        
        
        <span id="lberr" style="color:Red;"></span>
        <br />
        <div>
	<table class="blueGrid" cellspacing="1" cellpadding="3" border="0" id="GridView1"><%--${mf:substring(n.name,40) } --%>
		<tr class="Gridhead" style="font-weight:normal;font-style:normal;">
			<th scope="col">序号</th><th scope="col">用户名（电话号码）</th><th scope="col">姓名</th><th scope="col">电子邮箱</th>
            <th scope="col">生日</th><th scope="col">性别</th><th scope="col">兴趣爱好</th><th scope="col">喜欢的颜色</th>
            <th scope="col">注册时间</th><th scope="col">照片</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${userList}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td>${status.index + 1 }</td><td>
                       <a href="${ROOT }/admin/user/view.do?cids=${n.userNo}" > <span>${n.mobilePhone }</span></a>
                    </td>
                    <td><span>${n.userName }</span></td>
                    <td>${n.email }</td>
                    <td>${mf:fmtDate(n.birthday)}</td>
                    <td><span>${'F' eq n.sex?'女':('M' eq n.sex?'男':'保密') }</span></td>
                    <td><span title="${n.favorite }">${mf:substring(n.favorite,20) }</span></td>
                    <td><span title="${n.myColor }">${mf:substring(n.myColor,10) }</span></td>
                    <td>${mf:fmtDateTime(n.registeredDate)}</td>
                    <td><img src="${ROOT}${n.pic1 }" height="50px" width="50px" /></td>
                    <td>
                        <input type="button" value="查看" onclick="javascript:OnView(${n.userNo});" class="Gridbutton" />
                        <input type="button" value="删除" onclick="javascript:OnDelete(this.form,${n.userNo});" class="Gridbutton" />
                        <input type="button" value="重置密码" onclick="javascript:OnResetPwd(this.form,${n.userNo}, '${n.userName }');" class="Gridbutton" />
                    </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
	<m:pagination></m:pagination>
</div>
    <div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1。通过所有查询条件的集合查询相应的客户，查询结果以表格形式显示</span><br />
        <span id="Label14" style="color:Red;">2。对客户信息进行修改、删除操作</span><br />
        <span id="Label15" style="color:Red;">3。点击客户名查看客户详细信息</span><br />
    </div>
</form>

</body></html>
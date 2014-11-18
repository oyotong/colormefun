<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>菜单管理</title>
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
	form.action = "${ROOT }/admin/system/menu/search.do";
}
function OnUpdate(form,id) {
	form.action = "${ROOT }/admin/system/menu/edit.do?cids="+id;
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该菜单吗？")){
	form.action = "${ROOT }/admin/system/menu/remove.do?cids="+id;
	}
}
function OnSubmit(form) {
	
}
// -->
</script>
</head>
<body style="text-align: left;">
<m:message></m:message>
<form name="form1" method="post" action="#" onsubmit="javascript:return OnSubmit(this);" id="form1">
<div id="Panel1" style="width: 100%;">
    <div id="PanelTR1">
        </div><div id="PanelTR2">

        </div><table class="tableborder1" cellpadding="3" cellspacing="1">           
        <tbody><tr>
            <td colspan="4" class="titlebg">
                <span id="Label5">菜单列表</span></td>
        </tr>
        <tr>
            <td class="tablebody1" width="15%">
                <span id="Label6">菜单编号</span>：</td>
            <td class="tablebody1" width="35%">
            	<input name="authPurview.purviewId" maxlength="200" value="${authPurview.purviewId }" id="purviewId" type="text"/>
            </td>
            <td class="tablebody1" width="15%">
                <span id="Label7">菜单URL</span>：</td>
            <td class="tablebody1" width="35%">
                <input name="authPurview.purviewUrl" value="${authPurview.purviewUrl }" maxlength="200" id="purviewUrl" type="text"/>
            </td>
        </tr>            
        <tr>
            <td class="tablebody1">
                <span id="Label1">菜单类别</span></td>
            <td class="tablebody1">
                <select name="authPurview.purviewType" id="purviewType" class="select">
		<option selected="selected" value="">所有</option>
		<option value="0">一级</option>
		<option value="1">二级</option>
	</select></td>
            <td class="tablebody1">
                <span id="Label2">菜单名称</span></td>
            <td class="tablebody1">
            	<input name="authPurview.purviewName" maxlength="200" value="${authPurview.purviewName }" id="purviewName" type="text"/>
            </td>
        </tr>
        <tr>
            <td class="tablebody1" colspan="4" style="text-align: right;">                
                <input name="Button1" value="开始查询" onclick='javascript:OnSearch(this.form);' id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" type="submit"/>
                <input name="Button2" value="重置内容" onclick='javascript:OnReset({purviewId:"",purviewUrl:"",purviewType:"-1",purviewName:""});' id="Button2" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" type="submit"/>&nbsp;
            </td>
        </tr>
    </tbody></table>
</div>        
<span id="lberr" style="color: Red;"></span>
<br/>
<div>
<table class="blueGrid" id="GridView1" border="0" cellpadding="3" cellspacing="1">
		<tbody><tr class="Gridhead" style="font-weight: normal; font-style: normal;">
			<th scope="col">序号</th><th scope="col">菜单类型</th><th scope="col">菜单名称</th><th scope="col">菜单URL</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="p" varStatus="status" items="${authPurviewList}" >
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="">
			<td ${p.purviewType eq 0?"style='color: red;'":"" }>${status.index + 1 }</td>
			<td ${p.purviewType eq 0?"style='color: red;'":"" }>
                <span>${p.purviewType eq 0?"一级":"二级"}</span>
            </td><td ${p.purviewType eq 0?"style='color: red;'":"" }>
                <span ><a href="#" onclick="OnUpdate(document.form1,'${p.purviewId}');document.form1.submit();">${p.purviewName }</a></span>
            </td ${p.purviewType eq 0?"style='color: red;'":"" }>
            <td ${p.purviewType eq 0?"style='color: red;'":"" }>${p.purviewUrl }</td>
            <td ${p.purviewType eq 0?"style='color: red;'":"" }>
                <input name="button1" value="修改" onclick="OnUpdate(this.form,'${p.purviewId}');" class="Gridbutton" type="submit" />
                <input name="button2" value="删除" ${sessionScope["Session.UserName"].userName eq "admin"?"":"disable" } onclick="OnDelete(this.form,'${p.purviewId}');" class="Gridbutton" type="submit" />
            </td>
		</tr>
		</c:forEach>
	</tbody></table>
	<m:pagination></m:pagination>
</div>
        <br>
        <span id="Label12" style="color: Red;">操作说明：</span>&nbsp;<br>
        <span id="Label13" style="color: Red;">1、通过所有查询条件的集合查询相应的菜单，查询结果以表格形式显示<br></span>
        <span id="Label14" style="color: Red;">2、可以对菜单名进行修改、删除操作，不可更改菜单URL等其他选项<br></span>
        <span id="Label15" style="color: Red;">3、红色文字表示该内容为一级（<b>顶级</b>）菜单<br></span>
        <span id="Label16" style="color: Red;">4、点击菜单标题查看菜单内容<br></span>
  
        </form>
</body></html>
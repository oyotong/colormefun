<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>常见问题列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<script type="text/javascript">
<!--
function OnReset(map){
	for(var k in map){
		$("#"+k).val(map[k]);
	}
}
function OnSearch(form){
	form.action = "${ROOT }/admin/content/search.do";
}
function OnView(id) {
	var page = "${ROOT }/admin/content/view.do?cids="+id;
	window.open(page,"_blank");
}
function OnUpdate(form,id) {
	form.action = "${ROOT }/admin/content/edit.do?cids="+id;
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该常见问题吗？")){
		form.action = "${ROOT }/admin/content/remove.do?cids="+id;
	}
}
function OnSubmit(form) {
	
}
// -->
</script>
</head>
<body style="text-align: left;">
<form name="form1" method="post" action="#" onsubmit="javascript:return OnSubmit(this);" id="form1">
<input name="content.contentType" type="hidden" value="question" />
<div id="Panel1" style="width:100%;">
	        
        <table cellpadding="3" cellspacing="1" class="tableborder1">           
            <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label5">常见问题列表</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label6">常见问题标题</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                	<input name="content.title" type="text" value="${content.title }" style="width: 300px"  maxlength="200" id="title" />
                </td> 
            </tr>        
            <tr>
                <td class="tablebody1">
                    <span id="Label8">发布时间从</span>：</td>
                <td class="tablebody1" style="width: 326px">
                	<m:time name="content.createdDate" value="${content.createdDate }" id="createdDate"></m:time>
                </td>
                <td class="tablebody1">
                    <span id="Label9">发布时间到</span>：</td>
                <td class="tablebody1">
               		<m:time name="content.createdDate2" value="${content.createdDate2 }" id="createdDate2"></m:time>
				</td>
            </tr>
            <tr>
                <td class="tablebody1" colspan="4" style="text-align: right;">                
                    <input type="submit" name="Button1" value="开始查询" onclick="javascript:OnSearch(this.form)" id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />
                    <input type="button" name="Button2" value="重置内容" onclick="javascript:OnReset({title:'',createdDate:'',createdDate2:''})" id="Button2" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                </td>
            </tr>
        </table>   
        
</div>        
        
        <span id="lberr" style="color:Red;"></span>
        <br />
        <div>
	<table class="blueGrid" cellspacing="1" cellpadding="3" border="0" id="GridView1">
		<tr class="Gridhead" style="font-weight:normal;font-style:normal;">
			<th scope="col">序号</th><th scope="col">问题标题</th><th scope="col">发布时间</th><th scope="col">发布人</th><th scope="col">是否显示</th><th scope="col">是否顺序</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${list}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td>${status.index + 1 }</td><td>
                       <a href="${ROOT }/admin/content/edit.do?cids=${n.id}" > <span title="${n.title }">${mf:substring(n.title,40) }</span></a>
                    </td><td>${mf:fmtDateTime(n.createdDate)} </td><td>${ mf:getAuthUserById(n.createdId).userRealname}</td>

                    <td align="center">${(n.active ne "N")?"是":"否" }</td>
                    <td align="center">${n.seqNo }</td>
                    <td>
                        <input type="submit" value="修改" onclick="javascript:OnUpdate(this.form,${n.id});" class="Gridbutton" />
                        <input type="submit" value="删除" onclick="javascript:OnDelete(this.form,${n.id});" class="Gridbutton" />
                    </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
	<m:pagination></m:pagination>
</div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1。通过所有查询条件的集合查询相应的常见问题，查询结果以表格形式显示<br /></span>
        <span id="Label14" style="color:Red;">2。对常见问题进行修改、删除操作<br /></span>
        <span id="Label15" style="color:Red;">3。点击常见问题标题查看常见问题内容<br /></span>
        
</form>

</body></html>
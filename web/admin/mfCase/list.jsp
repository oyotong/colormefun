<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>活动列表</title>
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
	form.action = "${ROOT }/admin/mfCase/search.do";
}
function OnUpdate(form,id) {
	form.action = "${ROOT }/admin/mfCase/edit.do?cids="+id;
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该活动吗？")){
		form.action = "${ROOT }/admin/mfCase/remove.do?cids="+id;
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
                    <span id="Label0">活动列表</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">活动主题</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.title" type="text" value="${mfCase.title }" style="width: 300px"  maxlength="100" id="mfCase_title" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label2">活动场所</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.location" type="text" value="${mfCase.location }" style="width: 300px"  maxlength="100" id="mfCase_location" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label3">活动地点</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <textarea rows="" cols="" style="width: 85%;height: 20px" name="mfCase.address" >${mfCase.address }</textarea>
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label4">票 价</span>：</td>
                <td class="tablebody1" width="35%">
                    从：<input name="mfCase.ticketPrice" type="text" value="${mfCase.ticketPrice }" style="width: 50px"  maxlength="10" id="ticketPrice" />
                    &nbsp;&nbsp;到：<input name="mfCase.ticketPrice2" type="text" value="${mfCase.ticketPrice2 }" style="width: 50px"  maxlength="10" id="ticketPrice2" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label5">票 数</span>：</td>
                <td class="tablebody1" width="35%">
                    从：<input name="mfCase.ticketNumber" type="text" value="${mfCase.ticketNumber }" style="width: 50px"  maxlength="10" id="mfCase_ticketNumber" />
                    &nbsp;&nbsp;到：<input name="mfCase.ticketNumber2" type="text" value="${mfCase.ticketNumber2 }" style="width: 50px"  maxlength="10" id="mfCase_ticketNumber2" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label6">已销售</span>：</td>
                <td class="tablebody1" width="35%">
                从：<input name="mfCase.salesVolume" type="text" value="${mfCase.salesVolume }" style="width: 50px" maxlength="10" id="mfCase_salesVolume" />
                &nbsp;&nbsp;到：<input name="mfCase.salesVolume2" type="text" value="${mfCase.salesVolume2 }" style="width: 50px" maxlength="10" id="mfCase_salesVolume2" />
                </td>
                <td class="tablebody1">
                    <span id="Label7">发布时间</span>：</td>
                <td class="tablebody1" style="width: 326px">
                    从：<m:time name="mfCase.createdDate" value="${mfCase.createdDate }" id="createdDate"></m:time>
                    <br/>到：<m:time name="mfCase.createdDate2" value="${mfCase.createdDate2 }" id="createdDate2"></m:time>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label8">是否显示</span></td>
                <td class="tablebody1">
                    <input type="radio" value=""${empty mfCase.active?' checked="checked"':'' } name="mfCase.active" />未知
                    <input type="radio" value="Y"${mfCase.active eq 'Y'?' checked="checked"':'' } name="mfCase.active" />是
                    <input type="radio" value="N"${mfCase.active eq 'N'?' checked="checked"':'' } name="mfCase.active" />否
                </td>
                <td class="tablebody1">
                    <span id="Label9">难度级别</span></td>
                <td class="tablebody1">
                    <m:dictlist name="mfCase.level" id="mfCase_level" dict="CASE_LEVEL" value="${mfCase.level}" showAllText="-= 所有 =-" showAllValue=""></m:dictlist>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label10">活动日期</span></td>
                <td class="tablebody1">
                   从：<m:date id="start_date" name="mfCase.startDate" value="${mfCase.startDate}" />
                   <br/>到：<m:date id="start_date2" name="mfCase.startDate2" value="${mfCase.startDate2}" />
                </td>
                <td class="tablebody1">
                    <span id="Label11">时间范围</span></td>
                <td class="tablebody1">
                    <input name="mfCase.timeRange" type="text" value="${mfCase.timeRange }" style="width: 100px"  maxlength="50" id="mfCase_timeRange" />
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
			<th scope="col">序号</th><th scope="col">图片</th><th scope="col">活动主题</th>
            <th scope="col">活动场所</th><th scope="col">活动地址</th>
            <th scope="col">票价</th><th scope="col">票数</th>
            <th scope="col">已销售</th><th scope="col">发布时间</th><th scope="col">发布人</th>
            <th scope="col">是否显示</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${list}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td>${status.index + 1 }</td><td>
           <a href="${ROOT }/admin/mfCase/edit.do?cids=${n.caseNo}" >
               <img height="50px" width="100px" src="${ROOT}${n.picture }" />
           </a>
            <td><span title="${n.title }">${mf:substring(n.title,20) }</span></td>
            <td><span title="${n.location }">${mf:substring(n.location,20) }</span></td>
            <td><span title="${n.address }">${mf:substring(n.address,20) }</span></td>

            <td><span>${n.ticketPrice }</span></td>
            <td><span>${n.ticketNumber }</span></td>
            <td><span>${n.salesVolume }</span></td>

            <td>${mf:fmtDateTime(n.createdDate)} </td><td>${ mf:getAuthUserById(n.createdId).userRealname}</td>

            <td align="center">${(n.active ne "N")?"是":"否" }</td>
            <td>
                <input type="submit" value="修改" onclick="javascript:OnUpdate(this.form,${n.caseNo});" class="Gridbutton" />
                <input type="submit" value="删除" onclick="javascript:OnDelete(this.form,${n.caseNo});" class="Gridbutton" />
            </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
	<m:pagination></m:pagination>
</div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1。通过所有查询条件的集合查询相应的活动，查询结果以表格形式显示<br /></span>
        <span id="Label14" style="color:Red;">2。对常见问题进行修改、删除操作<br /></span>
        <span id="Label15" style="color:Red;">3。点击常见问题标题查看常见问题内容<br /></span>
        
</form>

</body></html>
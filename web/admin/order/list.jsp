<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单列表</title>
<meta http-equiv="order-Type" content="text/html; charset=UTF-8" />
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
	form.action = "${ROOT }/admin/order/search.do";
}
function OnView(id) {
	var page = "${ROOT }/admin/order/view.do?cids="+id;
	window.open(page,"_blank");
}
function OnUpdate(form,id,action) {
    var statusName = '';
    switch(action) {
        case 'active':
            statusName='激活';
            break;
        case 'unactivated':
            statusName='暂停';
            break;
    }
    if(!statusName){
        return false;
    }
    if(window.confirm("确定要【"+statusName+"】该订单吗？")) {
        form.action = "${ROOT }/admin/order/edit.do?action=" + action + "&cids=" + id;
    }
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
                    <span id="Label0">订单列表</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label7">订单编号</span></td>
                <td class="tablebody1" width="85%" colspan="3">
                    <input type="text" value="${order.orderNo }" style="width: 250px" maxlength="50" name="order.orderNo" id="order_orderNo" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label17">是否可用</span></td>
                <td class="tablebody1">
                    <input type="radio" value="Y"${order.active ne 'N'?' checked="checked"':'' } name="order.active" />是
                    <input type="radio" value="N"${order.active eq 'N'?' checked="checked"':'' } name="order.active" />否
                </td>
                <td class="tablebody1">
                    <span id="Label2">订单状态</span></td>
                <td class="tablebody1">
                    <m:dictlist name="order.status" id="order_status" showAllText="-=所有=-" showAllValue="" dict="ORDER_STATUS" value="${order.status}" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">总金额</span></td>
                <td class="tablebody1">
                    从：<input name="order.summary" type="text" value="${order.summary }" style="width: 50px"  maxlength="10" id="order_summary" />&nbsp;&nbsp;
                    到：<input name="order.summary2" type="text" value="${order.summary2 }" style="width: 50px"  maxlength="10" id="order_summary2" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label1">使用优惠劵</span></td>
                <td class="tablebody1">
                    从：<input name="order.couponSummary" type="text" value="${order.couponSummary }" style="width: 50px"  maxlength="10" id="couponSummary" />&nbsp;&nbsp;
                    到：<input name="order.couponSummary2" type="text" value="${order.couponSummary2 }" style="width: 50px"  maxlength="10" id="couponSummary2" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label4">订购人</span></td>
                <td class="tablebody1">
                    <input type="text" name="order.userName" style="width: 100px" value="${order.userName}" id="order_userName"/>
                    <span style="color: red">&nbsp;&nbsp;请输入订购人姓名或电话号码用于查询</span>
                </td>
                <td class="tablebody1">
                    <span id="Label5">订购时间</span></td>
                <td class="tablebody1">
                    从：<m:time name="order.createdDate" value="${order.createdDate }" id="order_createdDate"></m:time><br/>
                    到：<m:time name="order.createdDate2" value="${order.createdDate2 }" id="order_createdDate2"></m:time>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label10">描 述</span>：</td>
                <td class="tablebody1" colspan="3">
                    <textarea rows="" cols="" id="order_comment" style="width: 85%;height: 20px" name="order.comment" >${order.comment }</textarea>
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
			<th scope="col">订单编号</th>
            <th scope="col">订单总价</th><th scope="col">使用优惠劵</th>
            <th scope="col">状态</th><th scope="col">描 述</th>
            <th scope="col">订购人</th><th scope="col">订购时间</th>
            <th scope="col">可用</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${list}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td><span title="${n.orderNo }">${n.orderNo } </span></td>
            <td>${mf:fmtMoney(n.summary)} </td><td>${mf:fmtMoney(n.couponSummary)} </td>
            <td>${mf:dict('ORDER_STATUS',n.status)} </td>
            <td><span title="${n.comment }">${mf:substring(n.comment,40) }</span></td>
            <td>${n.user.userName} (${n.user.mobilePhone}) </td><td>${mf:fmtDateTime(n.createdDate)} </td>
            <td align="center">${(n.active ne "N")?"是":"否" }</td>
            <td>
                <input type="submit" value="查看" onclick="javascript:OnView(${n.orderNo});" class="Gridbutton" />
                <c:if test="${n.active eq 'N' and n.status ne 'closed'}">
                <input type="submit" value="激活" onclick="javascript:OnUpdate(this.form,${n.orderNo},'active');" class="Gridbutton" />
                </c:if><c:if test="${n.active ne 'N' and n.status ne 'closed'}">
                <input type="submit" value="暂停" onclick="javascript:OnUpdate(this.form,${n.orderNo},'unactivated');" class="Gridbutton" />
                </c:if>
            </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
	<m:pagination></m:pagination>
</div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1。通过所有查询条件的集合查询相应的订单，查询结果以表格形式显示<br /></span>
        <span id="Label14" style="color:Red;">2。对订单进行修改、删除操作<br /></span>
        <span id="Label15" style="color:Red;">3。点击订单标题查看订单内容<br /></span>
        
</form>

</body></html>
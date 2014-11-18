<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单详情</title>
<meta http-equiv="order-Type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<script type="text/javascript">
function OnSubmit(form) {
	ow.location = "${ROOT}/admin/order/list.do";
}
// -->
</script>
</head>
<body style="text-align: left;">
<form name="form1" method="post" action="#" onsubmit="javascript:return OnSubmit(this);" id="form1">
<input name="order.orderNo" type="hidden" value="${order.orderNo}" />
<div id="Panel1" style="width:100%;">
	        
        <table cellpadding="3" cellspacing="1" class="tableborder1">           
            <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">订单详情</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label7">订单编号</span></td>
                <td class="tablebody1" colspan="3" width="85%">
                    <input type="text" value="${order.orderNo }" style="width: 250px" disabled  maxlength="50" name="order.orderNo" id="order_orderNo" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label17">是否可用</span></td>
                <td class="tablebody1">
                    <input type="radio" value="Y"${order.active ne 'N'?' checked="checked"':'' } disabled name="order.active" />是
                    <input type="radio" value="N"${order.active eq 'N'?' checked="checked"':'' } disabled name="order.active" />否
                </td>
                <td class="tablebody1">
                    <span id="Label2">订单状态</span></td>
                <td class="tablebody1">
                    <m:dictlist name="order.status" id="order_status" dict="ORDER_STATUS" value="${order.status}" disabled="true" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">总金额</span></td>
                <td class="tablebody1">
                    <input name="order.summary" type="text" value="${order.summary }" disabled style="width: 50px"  maxlength="10" id="order_summary" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label1">使用优惠劵</span></td>
                <td class="tablebody1">
                    <input name="order.couponSummary" type="text" value="${order.couponSummary }" disabled style="width: 50px"  maxlength="10" id="couponSummary" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label4">订购人</span></td>
                <td class="tablebody1">
                    <input type="text" value="${order.user.userName} (${order.user.mobilePhone})" disabled="disabled"/>
                    <input type="hidden" value="${order.user.userNo }" name="order.user.userNo" id="order_user_userNo"/>
                </td>
                <td class="tablebody1">
                    <span id="Label5">订购时间</span></td>
                <td class="tablebody1">
                    <input type="text" value="${empty order.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(order.createdDate) }" disabled="disabled"/>
                    <input type="hidden" value="${empty order.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(order.createdDate) }" name="order.createdDate" id="createdDate"/>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label10">订单描述</span>：</td>
                <td class="tablebody1" colspan="3">
                    <textarea rows="" cols="" id="order_comment" disabled style="width: 85%;height: 20px" name="order.comment" >${order.comment }</textarea>
                </td>
            </tr>
        </table>   
        
</div>
<br />
<div>
	<table class="blueGrid" cellspacing="1" cellpadding="3" border="0" id="GridView1">
        <tr>
            <td colspan="5" class="titlebg"><span>订购明细</span></td>
        </tr>
		<tr class="Gridhead" style="font-weight:normal;font-style:normal;">
			<th scope="col">序号</th><th scope="col">活 动</th><th scope="col">票 价</th>
            <th scope="col">数量</th><th scope="col">描 述</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${order.details}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td>${n.lineNo } </td><td><a href="${ROOT}/admin/mfCase/view.do?cids=${n.mfCase.caseNo}">${n.mfCase.title } </a></td><td>${mf:fmtMoney(n.price)} </td>
            <td>${n.qty} </td><td>${mf:substring(n.comment, 50)}  </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
    <table class="blueGrid" cellspacing="1" cellpadding="3" border="0" id="GridView1">
        <tr>
            <td colspan="5" class="titlebg"><span>优惠卷</span></td>
        </tr>
        <tr class="Gridhead" style="font-weight:normal;font-style:normal;">
            <th scope="col">序号</th><th scope="col">优惠劵号</th><th scope="col">优惠金额</th><th scope="col">状态</th><th scope="col">描 述</th>
        </tr>
        <c:forEach var="n" varStatus="status" items="${order.coupons}">
            <tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
                <td>${status.index + 1 } </td><td>${mf:fmtCouponNo(n.couponNo) } </td><td>${mf:fmtMoney(n.deduction)} </td>
                <td>${mf:dict('COUPON_STATUS',n.status) } </td><td>${mf:substring(n.description, 50)}  </td>
            </tr>
        </c:forEach>
    </table>
</div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1. 查看订单详情<br /></span>
        
</form>

</body></html>
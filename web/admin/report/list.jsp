<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>月报列表</title>
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
function OnOrderSearch(form){
    form.target="";
    var now = new Date();
    form.action = "${ROOT }/admin/order/search.do?order.createdDate=${mf:fmtDateTime(mf:setToFirstDateOfMonth(mf:setToFirstTime(mf:now())))}"+
            "&order.createdDate=${mf:fmtDateTime(mf:setToLastDateOfMonth(mf:setToLastTime(mf:now())))}";
}
function OnSearch(form){
    form.target="";
	form.action = "${ROOT }/admin/report/search.do";
}
function OnBackupHistoryOrders(form){
    form.target="_blank";
    form.action = "${ROOT }/admin/report/backupHistoryOrders.do";
}
function OnCleanHistoryOrders(form){
    if(confirm("只会删除备份过的数据，删除前请先备份历史订单数据。\n删除后将无法恢复，您确定要删除历史订单信息吗？")){
        form.target="_blank";
        form.action = "${ROOT }/admin/report/cleanHistoryOrders.do";
    }
}
function OnMonthlyTradingDetail(form){
    var year = $('#year').val();
    if(!year){
        if(confirm('你没有选择年份，需要导出当前年份的“月交易目录”吗？')){
            year = new Date().getYear() + 1900;
            $('#year').val(year);
        }else{
            return;
        }
    }
    var month = $('#month').val();
    if(!month){
        if(confirm('你没有选择月份，需要导出当前月的“月交易目录”吗？')){
            month = new Date().getMonth() + 1;
            $('#month').val(month);
        }else{
            return;
        }
    }
    form.target="_blank";
    form.action = "${ROOT }/admin/report/monthlyTradingDetail.do";
}
function OnMonthlyPerformance(form){
    form.target="_blank";
    form.action = "${ROOT }/admin/report/monthlyPerformance.do";
}
function OnMonthlTradingStatistics(form){
    form.target="_blank";
    form.action = "${ROOT }/admin/report/monthlyTradingStatistics.do";
}
function OnYearlyPerformance(form){
    form.target="_blank";
    form.action = "${ROOT }/admin/report/yearlyPerformance.do";
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
                <td colspan="5" class="titlebg">
                    <span id="Label5">报表生成条件</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="5%">
                    <span id="Label1">年份</span>：</td>
                <td class="tablebody1" width="15%">
                    <select id="year" name="report.year">
                        <option value="">All</option>
                        <c:forEach var="year" begin="2014" end="${mf:now().year + 1900}">
                        <option value="${year}">${year}</option>
                        </c:forEach>
                    </select> 年
                </td>
                <td class="tablebody1" width="5%">
                    <span id="Label6">月份</span>：</td>
                <td class="tablebody1" width="15%">
                    <select id="month" name="report.month">
                        <option value="">All</option>
                        <c:forEach var="month" begin="1" end="12">
                            <option value="${month}">${month}</option>
                        </c:forEach>
                    </select> 月
                </td>
                <td class="tablebody1" width="60%">
                </td>
            </tr>
            <tr>
                <td class="tablebody1" colspan="5" style="text-align: right;">
                    <input type="submit" name="Button1" value="查询当前月交易明细" onclick="javascript:OnOrderSearch(this.form)" id="Button" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="submit" name="Button1" value="查询月业绩报表" onclick="javascript:OnSearch(this.form)" id="Button0" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="submit" name="Button1" value="备份历史订单" onclick="javascript:OnBackupHistoryOrders(this.form)" id="Button01" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="submit" name="Button1" value="删除历史订单" onclick="javascript:OnCleanHistoryOrders(this.form)" id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="submit" name="Button1" value="每月交易目录" onclick="javascript:OnMonthlyTradingDetail(this.form)" id="Button2" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="submit" name="Button1" value="月绩效报表" onclick="javascript:OnMonthlyPerformance(this.form)" id="Button4" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="submit" name="Button1" value="每月交易统计" onclick="javascript:OnMonthlTradingStatistics(this.form)" id="Button3" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="submit" name="Button1" value="年绩效报表" onclick="javascript:OnYearlyPerformance(this.form)" id="Button5" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                    <input type="button" name="Button2" value="重置内容" onclick="javascript:OnReset({year:'',month:''})" id="Button6" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                </td>
            </tr>
        </table>   
        
</div>
        <span id="lberr" style="color:Red;"></span>
        <br />
        <div>
	<table class="blueGrid" cellspacing="1" cellpadding="3" border="0" id="GridView1">
		<tr class="Gridhead" style="font-weight:normal;font-style:normal;">
			<th scope="col">年份</th><th scope="col">月份</th>
            <th scope="col">预算活动数</th><th scope="col">预算订单数</th><th scope="col">预算售票数</th><th scope="col">预算总金额</th><th scope="col">预算优惠总金额</th>
            <th scope="col">实际活动数</th><th scope="col">实际订单数</th><th scope="col">实际售票数</th><th scope="col">实际总金额</th><th scope="col">实际优惠总金额</th>
            <th scope="col">备注</th><th scope="col">创建人</th><th scope="col">创建时间</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${list}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td>${n.year } </td><td>${n.month } </td>
            <td>${n.caseTotal } </td><td>${n.orderTotal } </td><td>${n.ticketTotal } </td><td>${mf:fmtMoney(n.priceTotal) } </td><td>${mf:fmtMoney(n.couponTotal) } </td>
            <td>${n.realCaseTotal } </td><td>${n.realOrderTotal } </td><td>${n.realTicketTotal } </td><td>${mf:fmtMoney(n.realPriceTotal) } </td><td>${mf:fmtMoney(n.realCouponTotal) } </td>
            <td>${ n.comment} </td><td>${ mf:getAuthUserById(n.createdId).userRealname}</td><td>${mf:fmtDateTime(n.createdDate)} </td>
            <td>
                <%--<input type="submit" value="下载" onclick="javascript:OnDownload(this.form,${n.id});" class="Gridbutton" />--%>
            </td>
		</tr>
		</c:forEach>
	</tr>
    </table><br/>
	<m:pagination></m:pagination>
</div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1。选择年份和月份<br /></span>
        <span id="Label14" style="color:Red;">2。点击“查询”可以查询指定年份和月份的“月绩效报表”；如果没有选择月份，将返回指定年份的月报，如果也没指定年份，将查询所有。<br /></span>
        <span id="Label15" style="color:Red;">3。点击“备份&清除历史订单”将备份已经生成报表的历史订单，并在备份成功后删除历史订单。<br /></span>
        <span id="Label15" style="color:Red;">4。点击“每月交易目录”将下载当前月份的交易明细（该报表只显示当前月的即时交易记录，为保证报表的运行速度，每月底人工备份，只保留历史2个月的纪录）。<br /></span>
        <span id="Label15" style="color:Red;">5。点击“月绩效报表”将生成当前月及历史未生成月份的“月绩效报表”，并提供指定年份&月份“月绩效报表”的下载。成功生成报表的月份的订单记录将保存为“历史订单”。<br /></span>
        <span id="Label15" style="color:Red;">5。点击“每月交易统计”将生成指定年份的“每月交易统计”，并提供下载。<br /></span>
        <span id="Label15" style="color:Red;">5。点击“年绩效报表”将生成指定年份的“年绩效报表”，并提供下载。<br /></span>

</form>

</body></html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>优惠劵列表</title>
<meta http-equiv="coupon-Type" content="text/html; charset=UTF-8" />
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
	form.action = "${ROOT }/admin/coupon/search.do";
}
function OnView(id) {
	var page = "${ROOT }/admin/coupon/view.do?cids="+id;
	window.open(page,"_blank");
}
function OnUpdate(form,id,action) {
    var statusName = '';
    switch(action) {
        case 'released':
            statusName='发布';
            break;
        case 'active':
            statusName='激活';
            break;
        case 'unactivated':
            statusName='暂停';
            break;
        case 'locked':
            statusName='解锁';
            break;
        case 'expired':
            statusName='作废';
            break;
    }
    if(!statusName){
        return false;
    }
    if(window.confirm("确定要修改该优惠劵的状态为【"+statusName+"】吗？")) {
        form.action = "${ROOT }/admin/coupon/edit.do?action=" + action + "&cids=" + id;
    }
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该优惠劵吗？")){
		form.action = "${ROOT }/admin/coupon/remove.do?cids="+id;
	}
}
function OnSubmit(form) {
	
}
// -->
</script>
</head>
<body style="text-align: left;">
<form name="form1" method="post" action="#" onsubmit="javascript:return OnSubmit(this);" id="form1">
<input name="coupon.contentType" type="hidden" value="" />
<div id="Panel1" style="width:100%;">
	        
        <table cellpadding="3" cellspacing="1" class="tableborder1">           
            <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">优惠劵列表</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label7">优惠劵编号</span></td>
                <td class="tablebody1">
                    <input type="text" value="${mf:fmtCouponNo(coupon.couponNo) }" style="width: 250px"  maxlength="50" name="coupon.couponNo" id="coupon_couponNo" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label17">是否可用</span></td>
                <td class="tablebody1">
                    <input type="radio" value="Y"${coupon.active ne 'N'?' checked="checked"':'' } name="coupon.active" />是
                    <input type="radio" value="N"${coupon.active eq 'N'?' checked="checked"':'' } name="coupon.active" />否
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">优惠金额</span></td>
                <td class="tablebody1">
                    从：<input name="coupon.deduction" type="text" value="${coupon.deduction }" style="width: 50px"  maxlength="10" id="coupon_deduction" />
                    &nbsp;&nbsp;到：<input name="coupon.deduction2" type="text" value="${coupon.deduction2 }" style="width: 50px"  maxlength="10" id="coupon_deduction2" />
                </td>
                <td class="tablebody1">
                     <span id="Label2">折扣率</span></td>
                 <td class="tablebody1">
                     从：<input name="coupon.discount" type="text" value="${coupon.discount }" style="width: 50px"  maxlength="10" id="coupon_discount" />
                     &nbsp;&nbsp;到：<input name="coupon.discount2" type="text" value="${coupon.discount2 }" style="width: 50px"  maxlength="10" id="coupon_discount2" />
                 </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label3">生成数量</span></td>
                <td class="tablebody1" width="35%">
                    <m:dictlist name="coupon.total" id="coupon_total" dict="COUPON_NUM_RANG" showAllText="-=所有=-" showAllValue="" value="${coupon.total}" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label4">优惠劵类型</span></td>
                <td class="tablebody1" width="35%">
                    <m:dictlist name="coupon.couponType" id="coupon_couponType" dict="COUPON_TYPE" showAllText="-=所有=-" showAllValue="" value="${coupon.couponType}" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label5">状 态</span></td>
                <td class="tablebody1" width="35%">
                    <m:dictlist name="coupon.status" id="coupon_status" dict="COUPON_STATUS" showAllText="-=所有=-" showAllValue="" value="${coupon.status}" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label6">版 本</span></td>
                <td class="tablebody1" width="35%">
                    <input name="coupon.version" type="text" value="${coupon.version }" style="width: 150px"  maxlength="50" id="coupon_version" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label8">到期时间</span>：</td>
                <td class="tablebody1" style="width: 326px">
                    从：<m:time name="coupon.deadline" value="${coupon.deadline }" id="deadline"></m:time><br/>
                    到：<m:time name="coupon.deadline2" value="${coupon.deadline2 }" id="deadline2"></m:time>
                </td>
                <td class="tablebody1">
                    <span id="Label9">发布时间</span>：</td>
                <td class="tablebody1" style="width: 326px">
                    从：<m:time name="coupon.createdDate" value="${coupon.createdDate }" id="createdDate"></m:time><br/>
                    到：<m:time name="coupon.createdDate2" value="${coupon.createdDate2 }" id="createdDate2"></m:time>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label10">描 述</span>：</td>
                <td class="tablebody1" colspan="3">
                    <textarea rows="" cols="" id="coupon_description" style="width: 85%;height: 20px" name="coupon.description" >${coupon.description }</textarea>
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
			<th scope="col"><input type="checkbox" onchange="checkIds()" id="allIds"/></th><th scope="col">编号</th>
            <th scope="col">到期日期</th><th scope="col">版本</th>
            <th scope="col">优惠金额</th><th scope="col">优惠折扣</th>
            <th scope="col">类 别</th><th scope="col">产生数量</th>
            <th scope="col">状 态</th><th scope="col">描 述</th>
            <th scope="col">发布时间</th><th scope="col">发布人</th>
            <th scope="col">可用</th><th scope="col">功能</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${list}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td><input type="checkbox" name="cids" value="${n.couponId}"/></td><td>
            <%--<a href="${ROOT }/admin/coupon/view.do?cids=${n.couponNo}" > --%>
                <span title="${n.couponNo }">${n.couponNo } </span>
            <%--</a>--%>
            </td>
            <td>${mf:fmtDateTime(n.deadline)} </td><td>${n.version} </td>
            <td>${mf:fmtMoney(n.deduction)} </td><td>${mf:fmtMoney(n.discount)}  </td>
            <td>${mf:dict('COUPON_TYPE',n.couponType)} </td><td>${n.total}  </td><td>${mf:dict('COUPON_STATUS',n.status)} </td>
            <td><span title="${n.description }">${mf:substring(n.description,6) }</span></td>
            <td>${mf:fmtDateTime(n.createdDate)} </td><td>${ mf:getAuthUserById(n.createdId).userRealname}</td>
            <td align="center">${(n.active ne "N")?"是":"否" }</td>
            <td>
                <c:if test="${n.status eq 'created' and n.active eq 'Y' and n.deadline gt mf:now()}">
                <input type="submit" value="发布" onclick="javascript:OnUpdate(this.form,${n.couponId},'released');" class="Gridbutton" />
                </c:if><c:if test="${n.active ne 'Y'and n.deadline gt mf:now()}">
                <input type="submit" value="激活" onclick="javascript:OnUpdate(this.form,${n.couponId},'active');" class="Gridbutton" />
                </c:if><c:if test="${n.active eq 'Y'and n.deadline gt mf:now()}">
                <input type="submit" value="暂停" onclick="javascript:OnUpdate(this.form,${n.couponId},'unactivated');" class="Gridbutton" />
                </c:if><c:if test="${n.active ne 'Y' and n.active eq 'Y'and n.deadline gt mf:now()}">
                <input type="submit" value="解锁" onclick="javascript:OnUpdate(this.form,${n.couponId},'locked');" class="Gridbutton" />
                </c:if>
                <%--
                <c:if test="${n.active ne 'Y' and n.active eq 'Y'}}">
                <input type="submit" value="作废" onclick="javascript:OnUpdate(this.form,${n.id},'expired');" class="Gridbutton" />
                </c:if>--%>
            </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
	<m:pagination></m:pagination>
</div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1。通过所有查询条件的集合查询相应的优惠劵，查询结果以表格形式显示<br /></span>
        <span id="Label14" style="color:Red;">2。对优惠劵进行修改、删除操作<br /></span>
        <span id="Label15" style="color:Red;">3。点击优惠劵标题查看优惠劵内容<br /></span>
        
</form>

</body></html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
	添加优惠劵
</title><meta http-equiv="coupon-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
<script type="text/javascript"> 
<!--
jQuery(function() {
//    jQuery("#content_title").validate({
//        expression: "if (val) return true; else return false;",
//        message: "请输入优惠劵标题。"
//    });
});
// -->
</script>
</head>
<body>
<m:message></m:message>
<form name="form1" method="post" action="${ROOT}/admin/coupon/create.do?random=${mf:random()}" id="form1">
    <input name="coupon.couponId" type="hidden" value="${coupon.couponId }" id="id" />
    <%--<input name="coupon.couponType" type="hidden" value="1" />--%>
        <table cellpadding="3" cellspacing="1" class="tableborder1">
             <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">生成优惠劵</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label3">优惠金额</span></td>
                <td class="tablebody1" width="35%">
                    <m:dictradio name="coupon.deduction" id="coupon_deduction" dict="COUPON_PRICE" value="${coupon.deduction}" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label13">到期时间</span></td>
                <td class="tablebody1" width="35%">
                    <m:time name="coupon.deadline" id="coupon_deadline" value="${coupon.deadline}" />
                </td>
                <%--  <td class="tablebody1">
                     <span id="Label3">折扣率</span></td>
                 <td class="tablebody1">
                     <m:dictlist name="coupon.discount" id="coupon_discount" dict="COUPON_TYPE" value="${coupon.discount}" />
                 </td> --%>
            </tr>
            <%--<tr>--%>
                <%--<td class="tablebody1" width="15%">--%>
                    <%--<span id="Label3">是否可用</span></td>--%>
                <%--<td class="tablebody1" colspan="3" width="85%">--%>
                <%--<input type="radio" value="Y"${coupon.active ne 'N'?' checked="checked"':'' } name="coupon.active" />是--%>
                <%--<input type="radio" value="N"${coupon.active eq 'N'?' checked="checked"':'' } name="coupon.active" />否--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label3">生成数量</span></td>
                <td class="tablebody1" width="35%">
                    <m:dictlist name="coupon.total" id="coupon_total" dict="COUPON_NUM_RANG" value="${coupon.total}" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label3">优惠劵类型</span></td>
                <td class="tablebody1" width="35%">
                    <m:dictlist name="coupon.couponType" id="coupon_couponType" dict="COUPON_TYPE" value="${coupon.couponType}" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label4">发布人</span></td>
                <td class="tablebody1">
                <input type="text" value="${empty content.createdId?currentUser.userRealname: mf:getAuthUserById(content.createdId).userRealname}" disabled="disabled"/>
                <input type="hidden" value="${empty content.createdId?currentUser.userName:content.createdId }" name="coupon.createdId" id="createdId"/>
                </td>
                <td class="tablebody1">
                    <span id="Label5">发布时间</span></td>
                <td class="tablebody1">
                <input type="text" value="${empty content.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(content.createdDate) }" disabled="disabled"/>
                <input type="hidden" value="${empty content.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(content.createdDate) }" name="coupon.createdDate" id="createdDate"/>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label10">描 述</span>：</td>
                <td class="tablebody1" colspan="3">
                    <textarea rows="" cols="" id="coupon_description" style="width: 85%;height: 20px" name="coupon.description" >${coupon.description }</textarea>
                </td>
            </tr>
        </table>
                  
        <br />
        
        <input type="submit" name="btnUpdata" value="生成新优惠劵" onmouseout="this.className='button_mouseout'" /><br />
        <span id="lblDisplay"></span></div>
        <br />
        <span id="Label9" style="color:Red;">操作说明：</span>&nbsp;<br />
        
        <span id="Label11" style="color:Red;">1、添加优惠劵</span>
        </form>
</body>
</html>

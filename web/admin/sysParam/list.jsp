<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>系统参数设置</title>
    <meta http-equiv="coupon-Type" content="text/html; charset=UTF-8" />
    <%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
    <script type="text/javascript">
        <!--
        function OnSave(form){
            form.action = "${ROOT }/admin/sysParam/save.do";
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
            <c:set var="i" value="0" />
            <c:forEach var="p" items="${siteParameterList}">
            <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">${p.paramName}</span></td>
            </tr>
            <c:forEach var="item" items="${p.siteParameterItems}">
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label7">${item.paramItemComment}</span></td>
                <td class="tablebody1" colspan="3" width="85%">
                    <input type="hidden" name="siteParameterItemList[${i}].paramItemId" value="${item.paramItemId}" />
                    <input type="hidden" name="siteParameterItemList[${i}].paramKey" value="${item.paramKey}" />
                    <input type="hidden" name="siteParameterItemList[${i}].paramItemName" value="${item.paramItemName}" />
                    <input type="hidden" name="siteParameterItemList[${i}].paramItemName" value="${item.paramItemValue3}" />
                    <input type="hidden" name="siteParameterItemList[${i}].paramItemComment" value="${item.paramItemComment}" />
                    <textarea rows="" cols="" style="width: 85%;height: 20px" name="siteParameterItemList[${i}].paramItemValue" >${item.paramItemValue }</textarea>
                    <br/><span style="color: red">${item.paramItemValue3} </span>
                    <%--http://utf8.sms.webchinese.cn/?Uid=colormefun&Key=68a2eb316e847a8c669e&smsMob=13228181890&smsText=%E6%B5%8B%E8%AF%95%E5%86%85%E5%AE%B9abcd12345--%>
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />
            </c:forEach>
                <tr>
                    <td colspan="4" class="tablebody1"> </td>
                </tr>
            </c:forEach>
            <tr>
                <td class="tablebody1" colspan="4" style="text-align: right;">
                    <input type="submit" name="Button1" value="保存系统参数" onclick="javascript:OnSave(this.form)" id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />
                </td>
            </tr>
        </table>

    </div>
    <br />
    <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
    <span id="Label13" style="color:Red;">1。填写相关参数<br /></span>
    <span id="Label14" style="color:Red;">2。点击“保存”按钮保存<br /></span>

</form>

</body></html>
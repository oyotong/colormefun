<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
    重置客户密码
</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
</head>
<body>
<m:message></m:message>
<form name="form1" method="post" enctype="multipart/form-data" action="${ROOT}/admin/user/list.do?random=${mf:random()}" id="form1">

    <table cellpadding="3" cellspacing="1" class="tableborder1">
        <tr>
            <td colspan="4" class="titlebg">
                <span id="Label0">客户密码已经重置，请客户尽快修改该密码！！</span></td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label1" >电话号码</span>：</td>
            <td class="tablebody1">
                ${user.mobilePhone }
            </td>
            <td class="tablebody1" width="15%">
                <span id="Label2">姓名</span>：</td>
            <td class="tablebody1" width="35%">
                ${user.userName }
            </td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label5">临时密码</span>：</td>
            <td class="tablebody1" colspan="3">
                <span><strong>${user.password2}</strong></span>
            </td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label7">电子邮件</span>：</td>
            <td class="tablebody1">
                ${user.email }
            </td>
            <td class="tablebody1">
                <span id="Label8">注册时间</span>：</td>
            <td class="tablebody1">
                ${mf:fmtDate(n.registeredDate)}
            </td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label9">兴趣爱好</span>：</td>
            <td class="tablebody1" colspan="3">
                ${user.favorite }
            </td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label10">喜欢的颜色</span>：</td>
            <td class="tablebody1" colspan="3">${user.myColor }
            </td>
        </tr>

    </table>

    <br />

    <input type="submit" name="btnUpdata" value="返回" onmouseout="this.className='button_mouseout'" /><br />
    <span id="lblDisplay"></span></div>
    <br />
    <span id="Label20" style="color:Red;">操作说明：</span>&nbsp;<br />

    <span id="Label21" style="color:Red;">1、重置客户密码</span>
</form>
</body>
</html>

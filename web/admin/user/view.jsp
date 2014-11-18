<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
    查看客户信息
</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
    <script type="text/javascript">
        <!--
        jQuery(function() {
            jQuery("#user_userName").validate({
                expression: "if (val) return true; else return false;",
                message: "请输入客户姓名。"
            });
            jQuery("#user_mobilePhone").validate({
                expression: "if (val) return true; else return false;",
                message: "请输入客户电话号码（登录名）。"
            });
            jQuery("#user_email").validate({
                expression: "if (val) return true; else return false;",
                message: "请输入客户电子邮件地址。"
            });
        });
        // -->
    </script>
</head>
<body>
<m:message></m:message>
<form name="form1" method="post" enctype="multipart/form-data" action="${ROOT}/admin/user/list.do?random=${mf:random()}" id="form1">

    <table cellpadding="3" cellspacing="1" class="tableborder1">
        <tr>
            <td colspan="4" class="titlebg">
                <span id="Label0">查看客户信息</span></td>
        </tr>
        <tr>
            <td class="tablebody1" title="该号码将作为客户唯一的登录名。">
                <span id="Label1" >电话号码</span>：</td>
            <td class="tablebody1" title="该号码将作为客户唯一的登录名。">
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
                <span id="Label5">性别</span>：</td>
            <td class="tablebody1">
                <span>${'F' eq user.sex?'女':('M' eq user.sex?'男':'保密') }</span>
            </td>
            <td class="tablebody1">
                <span id="Label6">生日</span>：</td>
            <td class="tablebody1">
                ${mf:fmtDate(user.birthday)}
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
                ${mf:fmtDateTime(user.registeredDate)}
            </td>
        </tr>
        <tr>
            <td class="tablebody1"><span>客户照片</span>：</td>
            <td class="tablebody1" colspan="3">
                <img src="${ROOT}${user.pic1 }" height="100px" width="100px" />
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

    <input type="button" onclick="window.close();" name="btnUpdata" value="关闭" onmouseout="this.className='button_mouseout'" /><br />
    <span id="lblDisplay"></span></div>
    <br />
    <span id="Label20" style="color:Red;">操作说明：</span>&nbsp;<br />

    <span id="Label21" style="color:Red;">1、查看客户</span>
</form>
</body>
</html>

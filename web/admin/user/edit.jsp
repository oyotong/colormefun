<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
    更新客户
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
<form name="form1" method="post" enctype="multipart/form-data" action="${ROOT}/admin/user/save.do?random=${mf:random()}" id="form1">
    <input name="user.userNo" type="hidden" value="${user.userNo }" id="user_userNo" />
    <table cellpadding="3" cellspacing="1" class="tableborder1">
        <tr>
            <td colspan="4" class="titlebg">
                <span id="Label0">更新客户</span></td>
        </tr>
        <tr>
            <td class="tablebody1" title="请保证唯一，该号码将作为客户唯一的登录名。">
                <span id="Label1" >电话号码</span>：</td>
            <td class="tablebody1" title="请保证唯一，该号码将作为客户唯一的登录名。">
                <input type="text" value="${user.mobilePhone }" name="user.mobilePhone" style="width: 100px"  maxlength="20" id="user_mobilePhone" />
            </td>
            <td class="tablebody1" width="15%">
                <span id="Label2">姓名</span>：</td>
            <td class="tablebody1" width="35%">
                <input name="user.userName" type="text" value="${user.userName }" style="width: 100px"  maxlength="50" id="user_userName" />
            </td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label5">性别</span>：</td>
            <td class="tablebody1">
                <input type="radio" value="M"${user.sex eq 'M'?' checked="checked"':'' } name="user.sex" />男
                <input type="radio" value="F"${user.sex eq 'F'?' checked="checked"':'' } name="user.sex" />女
            </td>
            <td class="tablebody1">
                <span id="Label6">生日</span>：</td>
            <td class="tablebody1">
                <m:date name="user.birthday" value="${user.birthday }" id="user_birthday"></m:date>
            </td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label7">电子邮件</span>：</td>
            <td class="tablebody1">
                <input name="user.email" type="text" value="${user.email }" style="width: 200px"  maxlength="200" id="user_email" />
            </td>
            <td class="tablebody1">
                <span id="Label8">注册时间</span>：</td>
            <td class="tablebody1">
                <m:time name="user.registeredDate" value="${user.registeredDate }" id="user_registeredDate"></m:time>
            </td>
        </tr>
        <tr>
            <td class="tablebody1"><span>客户照片</span>：</td>
            <td class="tablebody1" colspan="3"><span id="lblpic"
                                                     style="color: Red;">图片最佳大小为100×100</span><br/>
                <m:imgUp name="user.pic1" maxHeight="50" maxWidth="100" value="${user.pic1 }" id="user_pic1" upname="image1"/></td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label9">兴趣爱好</span>：</td>
            <td class="tablebody1" colspan="3">
                <textarea rows="" cols="" style="width: 85%;height: 30px" name="user.favorite" >${user.favorite }</textarea>
            </td>
        </tr>
        <tr>
            <td class="tablebody1">
                <span id="Label10">喜欢的颜色</span>：</td>
            <td class="tablebody1" colspan="3">
                <textarea rows="" cols="" style="width: 85%;height: 20px" name="user.myColor" >${user.myColor }</textarea>
            </td>
        </tr>

    </table>

    <br />

    <input type="submit" name="btnUpdata" value="更新客户" onmouseout="this.className='button_mouseout'" /><br />
    <span id="lblDisplay"></span></div>
    <br />
    <span id="Label20" style="color:Red;">操作说明：</span>&nbsp;<br />

    <span id="Label21" style="color:Red;">1、更新客户</span>
</form>
</body>
</html>

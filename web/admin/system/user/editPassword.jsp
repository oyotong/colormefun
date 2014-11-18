<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改密码</title>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
<script type="text/javascript"> 
<!--
jQuery(function(){
    jQuery("#oldUserPassword").validate({
        expression: "if (!val || (val && val.length >= 4 && val.length <= 8)) return true; else return false;",
        message: "请填写旧密码,密码长度为4-8位英文或数字。"
    });
    jQuery("#newUserPassword").validate({
        expression: "if (!val || (val && val.length >= 4 && val.length <= 8)) return true; else return false;",
        message: "请填写新密码,密码长度为4-8位英文或数字。"
    });
    jQuery("#newUserPassword2").validate({
        expression: "if (val == $('#newUserPassword').val()) return true; else return false;",
        message: "请再次输入新密码,并与第一次输入的新密码保持一致。"
    });
    jQuery("#old2UserPassword").validate({
        expression: "if (!val || (val && val.length >= 4 && val.length <= 8)) return true; else return false;",
        message: "请填写旧密码,密码长度为4-8位英文或数字。"
    });
    jQuery("#new2UserPassword").validate({
        expression: "if (!val || (val && val.length >= 4 && val.length <= 8)) return true; else return false;",
        message: "请填写新密码,密码长度为4-8位英文或数字。"
    });
    jQuery("#new2UserPassword2").validate({
        expression: "if (val == $('#new2UserPassword').val()) return true; else return false;",
        message: "请再次输入新密码,并与第一次输入的新密码保持一致。"
    });
});
// -->
</script>
</head>
<body>
<div>
<m:message></m:message>
<form method="post" action="${ROOT }/admin/system/user/editPassword.do">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td width="10%" class="left_title_1"><label class="left-title">请输入旧密码：</label></td>
      <td><input type="password" name="oldUserPassword" id="oldUserPassword" value="${oldUserPassword }" /></td>
    </tr>
    <tr>
      <td class="left_title_2"><label class="left-title">请输入新密码：</label></td>
      <td><input type="password" name="newUserPassword" id="newUserPassword" value="${newUserPassword}" />(不填写，将不修改)</td>
    </tr>
    <tr>
      <td class="left_title_1"><label class="left-title">再次输入新密码：</label></td>
      <td><input type="password" name="newUserPassword2" id="newUserPassword2" value="${newUserPassword2}" /></td>
    </tr>
    <tr>
      <td width="10%" class="left_title_1"><label class="left-title">请输入旧财务密码：</label></td>
      <td><input type="password" name="old2UserPassword" id="oldUserPassword" value="${old2UserPassword }" /></td>
    </tr>
    <tr>
      <td class="left_title_2"><label class="left-title">请输入新财务密码：</label></td>
      <td><input type="password" name="new2UserPassword" id="newUserPassword" value="${new2UserPassword}" />(不填写，将不修改)</td>
    </tr>
    <tr>
      <td class="left_title_1"><label class="left-title">再次输入财务新密码：</label></td>
      <td><input type="password" name="new2UserPassword2" id="newUserPassword2" value="${new2UserPassword2}" /></td>
    </tr>
    <tr>
      <td class="left_title_2" colspan=2"><input type="submit" value="修改密码" /></td>
    </tr> 
  </table>
</form>
</div>
</body>
</html>

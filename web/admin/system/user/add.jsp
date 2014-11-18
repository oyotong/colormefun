<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
	编辑管理员
</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
<script type="text/javascript"> 
<!--
jQuery(function(){
    jQuery("#userName").validate({
        expression: "if (val) return true; else return false;",
        message: "请输入管理员编号。"
    });
    jQuery("#userRealname").validate({
        expression: "if (val) return true; else return false;",
        message: "请输入管理员姓名。"
    });
    jQuery("#userPassword").validate({
        expression: "if (val || (val.length >= 4 && val.length <= 8 )) return true; else return false;",
        message: "请输入密码,密码长度为4-8位英文或数字。"
    });
    jQuery("#newUserPassword").validate({
        expression: "if (val == $('#userPassword').val()) return true; else return false;",
        message: "再次输入密码必须与上一次输入的新密码保持一致。"
    }); 
    jQuery("#userPassword2").validate({
        expression: "if (val || (val.length >= 4 && val.length <= 8 )) return true; else return false;",
        message: "请输入财务密码,密码长度为4-8位英文或数字。"
    });
    jQuery("#newUserPassword2").validate({
        expression: "if (val == $('#userPassword2').val()) return true; else return false;",
        message: "再次输入财务密码必须与上一次输入的新密码保持一致。"
    }); 
});
// -->
</script>
</head>
<body>
<m:message></m:message>
    <form name="form1" method="post" action="${ROOT}/admin/system/user/save.do" id="form1">
    <div>
        <table cellpadding="3" cellspacing="1" class="tableborder1">
            <tr>
                <td colspan="4" class="titlebg">
                    <span>修改管理员信息</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="25%">
                    <span>管理员编号</span>：</td>
                <td class="tablebody1">
                    <input name="authUser.userName" type="text" value="${authUser.userName }" maxlength="20" id="userName" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="25%">
                    <span>管理员姓名</span>：</td>
                <td class="tablebody1">
                    <input name="authUser.userRealname" type="text" value="${authUser.userRealname }" maxlength="20" id="userRealname" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label2">角色</span>：</td>
                <td class="tablebody1">
                	<select name="authUser.authRoles.makeNew[0].roleId" id="roleId" class="select">
						<option value="">-- 请选择 --</option>
						<c:forEach var="r" items="${authUser.authRoles }">
							<c:set var="role" value="${r}"></c:set>
						</c:forEach>
						<c:forEach var="r" items="${authRoleList}">
						<option ${role.roleId eq r.roleId?'selected="selected"':'' } value="${r.roleId }">${r.roleName }</option>
						</c:forEach>
					</select>
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="25%">
                    <span>管理员密码</span>：</td>
                <td class="tablebody1">
                    <input name="authUser.userPassword" type="password" maxlength="20" id="userPassword" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="25%">
                    <span>重复密码</span>：</td>
                <td class="tablebody1">
                    <input name="newUserPassword" type="password" value="" maxlength="20" id="newUserPassword" />
                </td>
            </tr>
             <tr>
                <td class="tablebody1" width="25%">
                    <span>财务密码</span>：</td>
                <td class="tablebody1">
                    <input name="authUser.userPassword2" type="password" maxlength="20" id="userPassword2" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="25%">
                    <span>重复财务密码</span>：</td>
                <td class="tablebody1">
                    <input name="newUserPassword2" type="password" value="" maxlength="20" id="newUserPassword2" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label2">职位描述</span>：</td>
                <td class="tablebody1">
                	<textarea name="authUser.userComment" id="userComment" rows="4" cols="15">${authUser.userComment }</textarea>
                </td>
            </tr>
        </table>
        <div id="ValidationSummary" style="color:Red;display:none;">
 
		</div>            
        <br />
        
        <input type="submit" name="btnUpdata" value="添加管理员" onmouseout="this.className='button_mouseout'" /><br />
        <span id="lblDisplay"></span></div>
        <br />
        <span id="Label9" style="color:Red;">操作说明：</span>&nbsp;<br />
        
        <span id="Label11" style="color:Red;">1、添加管理员信息</span>
        </form>
</body>
</html>

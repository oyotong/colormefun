<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<fmt:setBundle var="MessageBundle" scope="session" basename="config/Message"/>
<fmt:message scope="session" var="LONG_DATE" bundle="${MessageBundle}" key="system.format.longdate"></fmt:message>
<fmt:message scope="session" var="SHORT_DATE" bundle="${MessageBundle}" key="system.format.shortdate"></fmt:message>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${ROOT}/admin/css/StyleSheet.css" type="text/css" rel="stylesheet"/>
    <title>
	管理员登录页面
</title>
<script language="javascript" type="text/javascript">
if(window != top.window){
	top.window.document.location.href = document.location.href;
}
if(!String.prototype.trim){
	String.prototype.trim = function(){
		return this.replace("\s*(.?)\s*","$1");
	}
}
function setFocus() {
	document.loginForm['user.userName'].select();
	document.loginForm['user.userName'].focus();
}
function checkForm(form){
	var username = new String(form['user.userName'].value);
	if(username.trim() == ""){
		alert("用户名不可为空。");
		form['user.userName'].focus();
		return false;
	}

	var password = new String(form['user.userPassword'].value);
	var length = password.length;
	if(length < 4 || length > 20){
		alert("密码长度应该在4-20之间。");
		form['user.userPassword'].focus();
		return false;
	}
	return true;
}
</script>
</head>
<body onload="javascript:setFocus()">
<form action="${ROOT}/admin/user/login.do" method="post" target="_top"
					onsubmit="return checkForm(this)" id="loginForm" name="loginForm">
    <div>
			<table id="Table2" border="0" cellpadding="0" cellspacing="0" height="500px" width="100%">
				<tbody><tr>
					<td align="center" valign="middle">
						<table id="Table3" border="0" cellpadding="0" cellspacing="0" height="258px" width="628">						    
							<tbody><tr>
								<td style="height: 250px;" background="${ROOT}/admin/images/adminilogin.jpg" valign="bottom">
									<table id="Table4" border="0" cellpadding="0" cellspacing="0" width="100%">
										<tbody><tr>
											<td style="width: 102px;" height="70"><font face="宋体"></font></td>
											<td height="70" valign="top">
												<table id="Table5" border="0" cellpadding="0" cellspacing="0" width="100%">
													<tbody><tr>
														<td style="height: 16px; width: 109px;" align="left">
                                                            &nbsp;<span id="Label1">管理员</span>:</td>
														<td style="height: 16px; width: 107px;" align="left">
                                                            &nbsp;<span id="Label2">密码</span>:</td>
														<td style="height: 16px; width: 107px;" align="left">
                                                            &nbsp;<span id="Label3">验证码</span>:</td>
														<td style="height: 16px; width: 80px;" align="left"></td>
													</tr>
													<tr>
														<td style="width: 109px;" valign="top"><input name="user.userName" value="${user.userName }" maxlength="20" id="userName" style="height: 18px; width: 80px;" type="text"/></td>
														<td style="width: 107px;" valign="top"><input name="user.userPassword" maxlength="20" id="userPassword" style="height: 18px; width: 80px;" type="password"/></td>
														<td style="width: 107px;" valign="top"><input name="validateCode" maxlength="20" id="validateCode" style="height: 18px; width: 80px; border-color: rgb(135, 135, 135);" type="text"/></td>
														<td style="text-align: left; width: 80px;"><img title="please refresh" src="${ROOT}/images/captcha.jpg?r=${mf:now().time}" height="22" width="70"/></td>
														<td style="text-align: left;" valign="top">
															<input name="btnOK" value="登录" id="btnOK" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" style="font-weight: bold; height: 22px; width: 70px;" type="submit"></td>
													</tr>
													<tr>
													<td colspan="4"><m:message></m:message></td>
													</tr>
							
												</tbody></table>                                                
                                                
                                                </td>
										</tr>
										<tr>
											<td colspan="2" height="20">
											
                                            </td>
										</tr>
									</tbody></table>
								</td>
							</tr>
						</tbody></table>
						</td>
				</tr>
				
			</tbody></table>
    </div>
 
        </form>
</body></html>
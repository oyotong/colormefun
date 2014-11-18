<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>无标题页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp" %>
<LINK rel=stylesheet type=text/css href="${ROOT }/admin/css/StyleSheet.css"/>
<script type="text/javascript" src="${ROOT }/admin/js/Main.js"></script>
<script type="text/javascript">
if(window != top.window){
	top.window.document.location.href = document.location.href;
}
</script>
</HEAD>
<BODY>
<BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
<TABLE width=355 background=${ROOT }/images/waitting.gif align=center 
  height=182><TBODY>
  <TR>
    <TD style="TEXT-ALIGN: center; HEIGHT: 100px"><BR><SPAN 
      style="COLOR: green" id=Label1>您好，系统正在维护中，请您稍后再登录</SPAN>！</TD></TR>
  <TR>
    <TD style="TEXT-ALIGN: center"><SPAN style="COLOR: red" 
      id=remark>${mf:param('MEMBER_LOGIN_TIME','message2') }</SPAN></TD></TR>
  <TR>
    <TD style="TEXT-ALIGN: center">&nbsp;</TD></TR>
</TBODY></TABLE></DIV></BODY></HTML>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>备份数据库</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp" %>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp" %></HEAD>
<BODY>
<FORM id=form1 method=post name=form1 action=#>
<TABLE class=tableborder1 cellSpacing=1 cellPadding=3>
  <TBODY>
  <TR>
    <TD class=titlebg1 colSpan=2><SPAN id=Label1>备份数据库</SPAN> </TD></TR>
  <TR>
    <TD class=tablebody1><SPAN id=Label2>备份名称</SPAN>： </TD>
    <TD class=tablebody1><INPUT id=txtName maxLength=200 type=text 
      name=txtName></TD></TR>
  <TR>
    <TD class=tablebody1 colSpan=2><INPUT id=Button1 class=button value=执行备份 type=submit name=Button1> 
      <SPAN style="COLOR: red" id=lberr></SPAN></TD></TR></TBODY></TABLE><BR>
<DIV>
<TABLE id=GridView1 class=blueGrid border=0 cellSpacing=1 cellPadding=3>
  <TBODY>
  <TR style="FONT-STYLE: normal; FONT-WEIGHT: normal" class=Gridhead>
    <TH scope=col><A 
      href="javascript:__doPostBack('GridView1','Sort$happentime')">备份时间</A></TH>
    <TH scope=col>文件名</TH>
    <TH scope=col>功能</TH></TR>
  <TR style="CURSOR: hand" class=Griditem 
  onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" 
  onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'">
    <TD>2010-6-9 10:07:58</TD>
    <TD>0529</TD>
    <TD><INPUT id=GridView1_ctl02_Button2 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl02$Button2", "", true, "", "", false, false))' value=下载 type=submit name=GridView1$ctl02$Button2> 
<INPUT id=GridView1_ctl02_Button4 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl02$Button4", "", true, "", "", false, false))' value=删除 type=submit name=GridView1$ctl02$Button4> 
    </TD></TR>
  <TR style="CURSOR: hand" class=Griditem 
  onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" 
  onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'">
    <TD>2010-5-24 12:38:30</TD>
    <TD>after-set-quan</TD>
    <TD><INPUT id=GridView1_ctl03_Button2 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl03$Button2", "", true, "", "", false, false))' value=下载 type=submit name=GridView1$ctl03$Button2> 
<INPUT id=GridView1_ctl03_Button4 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl03$Button4", "", true, "", "", false, false))' value=删除 type=submit name=GridView1$ctl03$Button4> 
    </TD></TR>
  <TR style="CURSOR: hand" class=Griditem 
  onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" 
  onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'">
    <TD>2010-5-16 13:54:36</TD>
    <TD>baderte</TD>
    <TD><INPUT id=GridView1_ctl04_Button2 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl04$Button2", "", true, "", "", false, false))' value=下载 type=submit name=GridView1$ctl04$Button2> 
<INPUT id=GridView1_ctl04_Button4 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl04$Button4", "", true, "", "", false, false))' value=删除 type=submit name=GridView1$ctl04$Button4> 
    </TD></TR>
  <TR style="CURSOR: hand" class=Griditem 
  onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" 
  onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'">
    <TD>2010-5-24 12:10:33</TD>
    <TD>before-set-quan</TD>
    <TD><INPUT id=GridView1_ctl05_Button2 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl05$Button2", "", true, "", "", false, false))' value=下载 type=submit name=GridView1$ctl05$Button2> 
<INPUT id=GridView1_ctl05_Button4 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl05$Button4", "", true, "", "", false, false))' value=删除 type=submit name=GridView1$ctl05$Button4> 
    </TD></TR>
  <TR style="CURSOR: hand" class=Griditem 
  onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" 
  onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'">
    <TD>2010-6-2 8:59:47</TD>
    <TD>testtest</TD>
    <TD><INPUT id=GridView1_ctl06_Button2 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl06$Button2", "", true, "", "", false, false))' value=下载 type=submit name=GridView1$ctl06$Button2> 
<INPUT id=GridView1_ctl06_Button4 class=Gridbutton onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("GridView1$ctl06$Button4", "", true, "", "", false, false))' value=删除 type=submit name=GridView1$ctl06$Button4> 
    </TD></TR></TBODY></TABLE></DIV><BR><SPAN style="COLOR: red" 
id=Label4>操作说明：</SPAN> <SPAN style="COLOR: red" id=Label5>数据库的备份、下载及删除</SPAN>

</FORM></BODY></HTML>

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>管理员日志</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp" %>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp" %>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp" %>
</HEAD>
<BODY>
<FORM id=form1 method=post name=form1 action=# >
<DIV>
<TABLE class=tableborder1 cellSpacing=1 cellPadding=3>
  <TBODY>
  <TR>
    <TD class=titlebg1 colSpan=4><SPAN id=Label1>管理日志搜索</SPAN> </TD></TR>
  <TR>
    <TD class=tablebody1 width="15%"><SPAN id=Label2>管理员</SPAN>：</TD>
    <TD class=tablebody1 colSpan=3><INPUT id="systemOperationLog_userId" maxLength=200 type=text 
      name="systemOperationLog.userId" value="${systemOperationLo.userId }"></TD></TR>
  <TR>
    <TD class=tablebody1><SPAN id=Label3>起始时间</SPAN>：</TD>
    <TD class=tablebody1>
    <m:time name="systemOperationLog.startTime" value="${systemOperationLog.startTime }" id="systemOperationLog_startTime"></m:time>
	</TD>
    <TD style="WIDTH: 84px" class=tablebody1><SPAN id=Label4>截至时间</SPAN>：</TD>
    <TD class=tablebody1>
    <m:time name="systemOperationLog.startTime2" value="${systemOperationLog.startTime2 }" id="systemOperationLog_startTime2"></m:time>  
	</TD></TR>
  <TR>
    <TD class=tablebody1><SPAN id=Label5>操作类型</SPAN>：</TD>
    <TD class=tablebody1 colSpan=3>
<table>
<tr>
<c:forEach var="i" varStatus="idx" items="${mf:paramList('USER_LOG_TYPE') }">
<td>
<input id="systemOperationLog_operation_${idx.index}" type="checkbox" name="systemOperationLog.operation" value="${i.paramItemValue }"  ${fn:contains(systemOperationLog.operation , i.paramItemValue)?'checked="checked"':'' } ><label for="systemOperationLog_operation_${idx.index}">${i.paramItemValue }</label>
</td>
<c:if test="${idx.index % 6 eq 5}">
</tr><tr>
</c:if>
</c:forEach>
</tr>
</table>
    </TD></TR>
  <TR>
    <TD class=tablebody1 colSpan=4 align=right><INPUT id=Button1 class=button onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("Button1", "", true, "", "", false, false))' value=开始查询 type=submit name=Button1></TD></TR></TBODY></TABLE></DIV><BR>
<DIV>
<TABLE id=GridView1 class=blueGrid border=0 cellSpacing=1 cellPadding=3>
  <TBODY>
  <TR style="FONT-STYLE: normal; FONT-WEIGHT: normal" class=Gridhead>
    <TH scope=col>开始时间</TH>
    <TH scope=col>结束时间</TH>
    <TH scope=col>管理员</TH>
    <TH scope=col>操作类型</TH>
    <TH scope=col>描述</TH></TR>
<c:forEach var="o" items="${systemOperationLogList }"> 
  <TR style="CURSOR: hand" class=Griditem 
  onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" 
  onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'">
    <TD>${mf:fmtDateTime(o.startTime) }</TD>
    <TD>${mf:fmtDateTime(o.endTime) }</TD>
    <TD>${o.userId }</TD>
    <TD>${o.operation }</TD>
    <TD>${o.opComment }</TD></TR>
</c:forEach>
  <TR class=Gridfoot>
    <TD colSpan=5>
    <m:pagination></m:pagination>
    </TD></TR></TBODY></TABLE></DIV>
</FORM></BODY></HTML>

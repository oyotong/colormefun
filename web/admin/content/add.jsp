<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
	添加常见问题
</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
<script type="text/javascript"> 
<!--
jQuery(function() {
    jQuery("#content_title").validate({
        expression: "if (val) return true; else return false;",
        message: "请输入常见问题标题。"
    });
});
// -->
</script>
</head>
<body>
<m:message></m:message>
<form name="form1" method="post" action="${ROOT}/admin/content/save.do?random=${mf:random()}" id="form1">
    <input name="content.id" type="hidden" value="${content.id }" id="id" />
    <input name="content.contentType" type="hidden" value="question" />
        <table cellpadding="3" cellspacing="1" class="tableborder1">
             <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">发布常见问题</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">标 题</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                	<input name="content.title" type="text" value="${content.title }" style="width: 300px"  maxlength="100" id="content_title" />
                </td> 
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label3">是否显示</span></td>
                <td class="tablebody1">
                <input type="radio" value="Y"${content.active ne 'N'?' checked="checked"':'' } name="content.active" />是
                <input type="radio" value="N"${content.active eq 'N'?' checked="checked"':'' } name="content.active" />否
                </td>
                <td class="tablebody1">
                    <span id="Label31">显示顺序</span></td>
                <td class="tablebody1">
                    <input type="text" style="width: 50px" maxlength="4" value="${empty content.seqNo?1:content.seqNo }" name="content.seqNo" />
                    <span style="color: red">数字，按从小到大的顺序显示。</span>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label4">发布人</span></td>
                <td class="tablebody1">
                <input type="text" value="${empty content.createdId?currentUser.userRealname: mf:getAuthUserById(content.createdId).userRealname}" disabled="disabled"/>
                <input type="hidden" value="${empty content.createdId?currentUser.userName:content.createdId }" name="content.createdId" id="createdId"/>
                </td>
                <td class="tablebody1">
                    <span id="Label5">发布时间</span></td>
                <td class="tablebody1">
                <input type="text" value="${empty content.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(content.createdDate) }" disabled="disabled"/>
                <input type="hidden" value="${empty content.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(content.createdDate) }" name="content.createdDate" id="createdDate"/>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label6">发布内容</span>：</td>
                <td class="tablebody1" colspan="3">
                <fck:editor instanceName="content.content" basePath="/admin/js/Fckeditor" height="500">
						<jsp:attribute name="value">${content.content }</jsp:attribute>   
				</fck:editor> 
                </td>
            </tr>
        </table>
                  
        <br />
        
        <input type="submit" name="btnUpdata" value="发布新常见问题" onmouseout="this.className='button_mouseout'" /><br />
        <span id="lblDisplay"></span></div>
        <br />
        <span id="Label9" style="color:Red;">操作说明：</span>&nbsp;<br />
        
        <span id="Label11" style="color:Red;">1、添加常见问题</span>
        </form>
</body>
</html>

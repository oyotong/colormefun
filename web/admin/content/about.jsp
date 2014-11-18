<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
   关于快乐秘方（Color Me Fun）
</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
    <%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
    <script type="text/javascript">
        <!--
//        jQuery(function() {
//            jQuery("#content_title").validate({
//                expression: "if (val) return true; else return false;",
//                message: "请输入常见问题标题。"
//            });
//        });
        // -->
    </script>
</head>
<body>
<m:message></m:message>
<form name="form1" method="post" action="${ROOT}/admin/content/saveAbout.do?random=${mf:random()}" id="form1">
    <input name="content.id" type="hidden" value="${content.id }" id="id" />
    <input name="content.contentType" type="hidden" value="about" />
    <input name="content.title" type="hidden" value="关于快乐秘方（Color Me Fun）" style="width: 300px"  maxlength="100" id="content_title" />
    <input type="hidden" value="Y" name="content.active" />
    <table cellpadding="3" cellspacing="1" class="tableborder1">
        <tr>
            <td colspan="4" class="titlebg">
                <span id="Label0">关于快乐秘方（Color Me Fun）</span></td>
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

    <input type="submit" name="btnUpdata" value="发布" onmouseout="this.className='button_mouseout'" /><br />
    <span id="lblDisplay"></span></div>
    <br />
    <span id="Label9" style="color:Red;">操作说明：</span>&nbsp;<br />

    <span id="Label11" style="color:Red;">1、发布“关于快乐秘方（Color Me Fun）”</span>
</form>
</body>
</html>

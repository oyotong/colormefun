<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
	菜单编辑
</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
<script type="text/javascript"> 
<!--
jQuery(function(){
    jQuery("#purviewName").validate({
        expression: "if (val) return true; else return false;",
        message: "菜单名称是必填项。"
    });
    jQuery("#purviewUrl").validate({
        expression: "if (val) return true; else return false;",
        message: "菜单URL是必填项。"
    });
});
// -->
</script>
</head>
<body>
<m:message></m:message>
    <form name="form1" method="post" action="${ROOT}/admin/system/menu/update.do" id="form1">
    <div>
    <input type="hidden" name="authPurview.purviewMask" id="purviewMask" value="${authPurview.purviewMask }" />
        <table cellpadding="3" cellspacing="1" class="tableborder1">
            <tr>
                <td colspan="4" class="titlebg">
                    <span>修改菜单</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="25%">
                    <span>菜单编号</span>：</td>
                <td class="tablebody1">
                	${authPurview.purviewId }
                    <input name="authPurview.purviewId" type="hidden" value="${authPurview.purviewId }" maxlength="20" id="purviewId" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="25%">
                    <span>菜单名称</span>：</td>
                <td class="tablebody1">
                    <input name="authPurview.purviewName" type="text" value="${authPurview.purviewName }" maxlength="20" id="purviewName" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label2">菜单类型</span>：</td>
                <td class="tablebody1">
                	${authPurview.purviewType eq 0 ? '一级' : '二级' }
                    <input name="authPurview.purviewType" type="hidden" value="${authPurview.purviewType }" maxlength="50" id="purviewType" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label2">上级菜单</span>：</td>
                <td class="tablebody1">
                	${authPurview.authPurview.purviewName }
                    <input name="authPurview.authPurview.purviewId" id="authPurview.purviewId" type="hidden" value="${authPurview.authPurview.purviewId }" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label3">菜单URL</span>：</td>
                <td class="tablebody1">
                	<c:if test="${sessionScope['SessionUser'].userName eq 'admin' }">
                		<input name="authPurview.purviewUrl" type="text" style="width: 250px" value="${authPurview.purviewUrl }" maxlength="250" id="purviewUrl" />
                	</c:if>
                	<c:if test="${sessionScope['SessionUser'].userName ne 'admin' }">
                		${authPurview.purviewUrl }
                	</c:if>
                	
                </td>
            </tr>
            
        </table>
        <div id="ValidationSummary" style="color:Red;display:none;">
 
		</div>            
        <br />
        
        <input type="submit" name="btnUpdata" value="修改菜单" onmouseout="this.className='button_mouseout'" /><br />
        <span id="lblDisplay"></span></div>
        <br />
        <span id="Label9" style="color:Red;">操作说明：</span>&nbsp;<br />
        
        <span id="Label11" style="color:Red;">1。修改菜单名称</span>
        </form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
	发布${caseType eq 'ent_vip'? '企业活动':'私人派对'}
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
        message: "请输入标题。"
    });
});
// -->
</script>
</head>
<body>
<m:message></m:message>
<form name="form1" method="post" enctype="multipart/form-data" action="${ROOT}/admin/mfCase/save.do?random=${mf:random()}" id="form1">
    <input name="mfCase.caseNo" type="hidden" value="${mfCase.caseNo }" id="caseNo" />
        <table cellpadding="3" cellspacing="1" class="tableborder1">
             <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">发布${caseType eq 'ent_vip'? '企业活动':'私人派对'}</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">${caseType eq 'ent_vip'? '企业活动':'私人派对'}主题</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.title" type="text" value="${mfCase.title }" style="width: 300px"  maxlength="100" id="mfCase_title" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label1">${caseType eq 'ent_vip'? '企业活动':'私人派对'}场所</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.location" type="text" value="${mfCase.location }" style="width: 300px"  maxlength="100" id="mfCase_location" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">${caseType eq 'ent_vip'? '企业活动':'私人派对'}地点</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <textarea rows="" cols="" style="width: 85%;height: 20px" name="mfCase.address" >${mfCase.address }</textarea>
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">票 价</span>：</td>
                <td class="tablebody1" width="35%">
                    ￥<input name="mfCase.ticketPrice" type="text" value="${mfCase.ticketPrice }" style="width: 50px"  maxlength="10" id="ticketPrice" />/位
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label1">票 数</span>：</td>
                <td class="tablebody1" width="35%">
                    总共<input name="mfCase.ticketNumber" onchange="updateRamainingTicket" type="text" value="${mfCase.ticketNumber }" style="width: 50px"  maxlength="10" id="mfCase_ticketNumber" />张票
                    ，已销售<input type="text" value="${empty mfCase.salesVolume?0:mfCase.salesVolume }" readonly style="width: 50px" disabled  />张票。
                    <input name="mfCase.salesVolume" type="hidden" value="${empty mfCase.salesVolume?0:mfCase.salesVolume }" id="mfCase_salesVolume" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label3">是否显示</span></td>
                <td class="tablebody1">
                <input type="radio" value="Y"${mfCase.active ne 'N'?' checked="checked"':'' } name="mfCase.active" />是
                <input type="radio" value="N"${mfCase.active eq 'N'?' checked="checked"':'' } name="mfCase.active" />否
                </td>
                <td class="tablebody1">
                    <span id="Label3">难度级别</span></td>
                <td class="tablebody1">
                    <m:dictlist name="mfCase.level" id="mfCase_level" dict="CASE_LEVEL" value="${mfCase.level}"></m:dictlist>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label4">${caseType eq 'ent_vip'? '企业活动':'私人派对'}日期</span></td>
                <td class="tablebody1">
                    <m:date id="start_date" name="mfCase.startDate" value="${mfCase.startDate}" />
                </td>
                <td class="tablebody1">
                    <span id="Label5">时间范围</span></td>
                <td class="tablebody1">
                    <input name="mfCase.timeRange" type="text" value="${mfCase.timeRange }" style="width: 100px"  maxlength="50" id="mfCase_timeRange" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label4">发布人</span></td>
                <td class="tablebody1">
                <input type="text" value="${empty mfCase.createdId?currentUser.userRealname: mf:getAuthUserById(mfCase.createdId).userRealname}" disabled="disabled"/>
                <input type="hidden" value="${empty mfCase.createdId?currentUser.userName:mfCase.createdId }" name="mfCase.createdId" id="createdId"/>
                </td>
                <td class="tablebody1">
                    <span id="Label5">发布时间</span></td>
                <td class="tablebody1">
                <input type="text" value="${empty mfCase.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(mfCase.createdDate) }" disabled="disabled"/>
                <input type="hidden" value="${empty mfCase.createdDate?mf:fmtDateTime(mf:now()):mf:fmtDateTime(mfCase.createdDate) }" name="mfCase.createdDate" id="createdDate"/>
                </td>
            </tr>
            <tr>
                <td class="tablebody1"><span>图片</span>：</td>
                <td class="tablebody1" colspan="3"><span id="lblpic"
                                                         style="color: Red;">图片最佳大小为100×100</span><br/>
                    <m:imgUp name="mfCase.picture" maxHeight="50" maxWidth="100" value="${mfCase.picture }" id="mfCase_picture" upname="image1"/></td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label6">${caseType eq 'ent_vip'? '企业活动':'私人派对'}详情</span>：</td>
                <td class="tablebody1" colspan="3">
                <fck:editor instanceName="mfCase.description" basePath="/admin/js/Fckeditor" height="200">
						<jsp:attribute name="value">${mfCase.description }</jsp:attribute>
				</fck:editor> 
                </td>
            </tr>
        </table>
                  
        <br />
        
        <input type="submit" name="btnUpdata" value="发 布" onmouseout="this.className='button_mouseout'" /><br />
        <span id="lblDisplay"></span></div>
        <br />
        <span id="Label9" style="color:Red;">操作说明：</span>&nbsp;<br />
        
        <span id="Label11" style="color:Red;">1、发布快乐秘方${caseType eq 'ent_vip'? '企业活动':'私人派对'}</span>
        </form>
</body>
</html>

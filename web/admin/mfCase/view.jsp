<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
	活动
</title><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-fckeditor.jsp"%>
</head>
<body>
<m:message></m:message>
<form name="form1" method="post" enctype="multipart/form-data" action="#" id="form1">
    <input name="mfCase.caseNo" type="hidden" value="${mfCase.caseNo }" id="caseNo" />
        <table cellpadding="3" cellspacing="1" class="tableborder1">
             <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">发布活动</span></td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">活动主题</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.title" disabled type="text" value="${mfCase.title }" style="width: 300px"  maxlength="100" id="mfCase_title" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label1">活动场所</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.location" disabled type="text" value="${mfCase.location }" style="width: 300px"  maxlength="100" id="mfCase_location" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">活动地点</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <textarea rows="" cols="" disabled style="width: 85%;height: 20px" name="mfCase.address" >${mfCase.address }</textarea>
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">票 价</span>：</td>
                <td class="tablebody1" width="35%">
                    ￥<input name="mfCase.ticketPrice" disabled type="text" value="${mfCase.ticketPrice }" style="width: 50px"  maxlength="10" id="ticketPrice" />/位
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label1">票 数</span>：</td>
                <td class="tablebody1" width="35%">
                    总共<input name="mfCase.ticketNumber" disabled onchange="updateRamainingTicket" type="text" value="${mfCase.ticketNumber }" style="width: 50px"  maxlength="10" id="mfCase_ticketNumber" />张票
                    ，已销售<input type="text" value="${empty mfCase.salesVolume?0:mfCase.salesVolume }" readonly style="width: 50px" disabled  />张票。
                    <input name="mfCase.salesVolume" disabled type="hidden" value="${empty mfCase.salesVolume?0:mfCase.salesVolume }" id="mfCase_salesVolume" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label3">是否显示</span></td>
                <td class="tablebody1">
                <input type="radio" disabled value="Y"${mfCase.active ne 'N'?' checked="checked"':'' } name="mfCase.active" />是
                <input type="radio" disabled value="N"${mfCase.active eq 'N'?' checked="checked"':'' } name="mfCase.active" />否
                </td>
                <td class="tablebody1">
                    <span id="Label3">难度级别</span></td>
                <td class="tablebody1">
                    <m:dictlist disabled="true" name="mfCase.level" id="mfCase_level" dict="CASE_LEVEL" value="${mfCase.level}"></m:dictlist>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label4">活动日期</span></td>
                <td class="tablebody1">
                    <m:date id="start_date" disabled="true" name="mfCase.startDate" value="${mfCase.startDate}" />
                </td>
                <td class="tablebody1">
                    <span id="Label5">时间范围</span></td>
                <td class="tablebody1">
                    <input name="mfCase.timeRange" disabled="true" type="text" value="${mfCase.timeRange }" style="width: 100px"  maxlength="50" id="mfCase_timeRange" />
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
                <td class="tablebody1" colspan="3">
                <%--<m:imgUp name="mfCase.picture" maxHeight="50" maxWidth="100" value="${mfCase.picture }" id="mfCase_picture" upname="image1"/>--%>
                <img title="图片最佳大小为100×100" src="${ROOT}${mfCase.picture}" height="100px" width="100px" alt="图片">
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label6">活动详情</span>：</td>
                <td class="tablebody1" colspan="3">
                <div>${mfCase.description } </div>
                </td>
            </tr>
        </table>
                  
        <br />
        
        <input type="button" onclick="window.close();" name="btnUpdata" value="关 闭" onmouseout="this.className='button_mouseout'" /><br />

        </form>
</body>
</html>

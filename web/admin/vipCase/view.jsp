<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head><title>
    ${caseType eq 'ent_vip'? '企业活动':'私人派对'}
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
                    <span id="Label0">${caseType eq 'ent_vip'? '企业活动':'私人派对'}详情</span></td>
            </tr>
            <c:if test="${caseType eq 'ent_vip'}">
                <tr>
                    <td class="tablebody1" width="15%">
                        <span id="Label22">企业/机构</span>：</td>
                    <td class="tablebody1" colspan="3" width="85%">
                        <input name="mfCase.companyName" type="text" disabled value="${mfCase.companyName }" style="width: 500px"  maxlength="50" id="mfCase_companyName" />
                    </td>
                </tr>
            </c:if>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label111">${caseType eq 'ent_vip'? '联系人':'姓名'}</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.contactName" disabled type="text" value="${mfCase.contactName }" style="width: 300px"  maxlength="50" id="mfCase_contactName" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label2">${caseType eq 'ent_vip'? '联系电话':'电话'}</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.contactPhone" disabled type="text" value="${mfCase.contactPhone }" style="width: 300px"  maxlength="50" id="mfCase_contactPhone" />
                </td>
            </tr>
            <c:if test="${caseType eq 'vip'}">
                <tr>
                    <td class="tablebody1" width="15%">
                        <span id="Label1">您所在的城区</span>：</td>
                    <td class="tablebody1" width="35%">
                        <m:dictlist name="mfCase.region" disabled="true" showAllValue="" showAllText="-=所有=-" id="mfCase_region" dict="CD_REGION" value="${mfCase.region}"/>

                    </td>
                    <td class="tablebody1" width="15%">
                        <span id="Label1">您所在的商圈</span>：</td>
                    <td class="tablebody1" width="35%">
                        <input name="mfCase.area" type="text" disabled value="${mfCase.area }" style="width: 200px"  maxlength="50" id="mfCase_area" />
                    </td>
                </tr>
            </c:if>
            <tr>
                <td class="tablebody1" width="15%">
                    <c:if test="${caseType ne 'ent_vip'}"><span id="Label3">儿童人数</span>：</td></c:if>&nbsp;
                <td class="tablebody1" width="35%">
                    <c:if test="${caseType ne 'ent_vip'}">
                        <input name="mfCase.kidsNum" disabled type="text" value="${mfCase.kidsNum }" style="width: 50px"  maxlength="10" id="mfCase_kidsNum" />
                    </c:if>&nbsp;
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label3">电子邮箱</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.email" disabled type="text" value="${mfCase.email }" style="width: 300px"  maxlength="50" id="mfCase_email" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label3">参加人数</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.memberNum" disabled type="text" value="${mfCase.memberNum }" style="width: 50px"  maxlength="10" id="mfCase_memberNum" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label4">是否需要我们<br/>推荐活动场所</span>：</td>
                <td class="tablebody1" width="35%">
                    <input type="radio" disabled value=""${empty mfCase.needLocation?' checked="checked"':'' } name="mfCase.needLocation" />未知
                    <input type="radio" disabled value="Y"${mfCase.needLocation eq 'Y'?' checked="checked"':'' } name="mfCase.needLocation" />是
                    <input type="radio" disabled value="N"${mfCase.needLocation eq 'N'?' checked="checked"':'' } name="mfCase.needLocation" />否
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label5">您的活动场所</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <textarea rows="" cols="" disabled style="width: 85%;height: 20px" name="mfCase.location" >${mfCase.location }</textarea>
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label5">给我们的留言</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <textarea rows="" cols="" disabled style="width: 85%;height: 20px" name="mfCase.description" >${mfCase.description }</textarea>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label7">提交时间</span>：</td>
                <td class="tablebody1" colspan="3">${mf:fmtDateTime(mfCase.createdDate)}
                </td>
            </tr>
        </table>
                  
        <br />
        
        <input type="button" onclick="window.close();" name="btnUpdata" value="关 闭" onmouseout="this.className='button_mouseout'" /><br />

        </form>
</body>
</html>

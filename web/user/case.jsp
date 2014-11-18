<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="caseType" value="${empty param.caseType?caseType:param.caseType}" />
<form name="partyForm_${caseType}" id="partyForm_${caseType}" action="${ROOT}/user/vipCase/saveMyCase.do" onsubmit="OnVipCaseSubmit(this);return false;" >
    <input type="hidden" name="mfCase.caseType" value="${caseType}" />
    <table class="field-table">
        <tbody>
        <c:if test="${caseType eq 'ent_vip'}">
            <tr>
                <td class="title"><span for="mfCase_companyName" class="ui-icon icon-required"></span> 企业/机构</td>
                <td>
                    <input name="mfCase.companyName" type="text" value="${mfCase.companyName }" id="mfCase_companyName" size="25" />
                    <span for="mfCase_companyName" class="tips">请填写您的企业/机构名称</span>
                </td>
            </tr>
        </c:if>
        <tr>
            <td class="title"><span for="mfCase_contactName" class="ui-icon icon-required"></span> ${caseType eq 'ent_vip'? '联系人':'姓名'}</td>
            <td>
                <input name="mfCase.contactName" type="text" value="${mfCase.contactName }" id="mfCase_contactName" size="25" />
                <span for="mfCase_contactName" class="tips">请填写您的真实姓名</span>
            </td>
        </tr>
        <tr>
            <td class="title"><span for="mfCase_contactPhone" class="ui-icon icon-required"></span> ${caseType eq 'ent_vip'? '联系电话':'电话'}</td>
            <td>
                <input name="mfCase.contactPhone" type="text" value="${mfCase.contactPhone }" id="mfCase_contactPhone" size="25" />
                <span for="mfCase_contactPhone" class="tips">请填写您的手机号码</span>
            </td>
        </tr>
        <tr>
            <td class="title"><span for="mfCase_email" class="ui-icon icon-required"></span> 电子邮箱</td>
            <td>
                <input name="mfCase.email" type="text" value="${mfCase.email }" id="mfCase_email" size="25" />
                <span for="mfCase_email" class="tips">请填写您的电子邮箱地址</span>
            </td>
        </tr>
        <c:if test="${caseType eq 'vip'}">
            <tr>
                <td class="title">您所在城区</td>
                <td>
                    <m:dictlist name="mfCase.region" showAllValue="" showAllText="" id="mfCase_region" dict="CD_REGION" value="${mfCase.region}"/>
                    <span class="tips">请选择城区</span>
                </td>
            </tr>
            <tr>
                <td class="title">所在商区</td>
                <td>
                    <input name="mfCase.area" type="text" size="25" value="${mfCase.area }" id="mfCase_area" />
                    <span class="tips">例：玉林，双楠，宽窄巷子</span>
                </td>
            </tr>
        </c:if>
        <tr>
            <td class="title"><span for="mfCase_memberNum" class="ui-icon icon-required"></span> 参加人数</td>
            <td>
                <input name="mfCase.memberNum" type="text" value="${mfCase.memberNum }" size="25" id="mfCase_memberNum" />
                <span for="mfCase_memberNum" class="tips">请填写参加活动总人数（包括孩童）</span>
            </td>
        </tr>
        <c:if test="${caseType ne 'ent_vip'}">
            <tr>
                <td class="title">儿童人数</td>
                <td><input name="mfCase.kidsNum" type="text" value="${mfCase.kidsNum }" size="25" id="mfCase_kidsNum" /></td>
            </tr>
        </c:if>
        <tr>
            <td colspan="2"><span for="mfCase.needLocation" class="ui-icon icon-required"></span> 是否需要我们推荐活动场所？</td>
        </tr>
        <tr>
            <td></td>
            <td>
                <p>
                    <label><input type="radio" value="Y"${mfCase.needLocation eq 'Y'?' checked="checked"':'' } name="mfCase.needLocation" /> 是，需要推荐活动场所</label></p>
                <p>
                    <label><input type="radio" value="N"${mfCase.needLocation ne 'N'?' checked="checked"':'' } name="mfCase.needLocation" /> 否，我们自己安排活动场所</label></p>
                <textarea placeholder="请输入您的活动场所地址" rows="3" cols="80" name="mfCase.location" >${mfCase.location }</textarea>
            </td>
        </tr>
        <tr>
            <td class="title"><span for="mfCase_description" class="ui-icon icon-required"></span> 给我们留言</td>
            <td>
                <textarea placeholder="例：12月2号下午生日派对，有无特殊要求？" rows="3" cols="80" id="mfCase_description" name="mfCase.description" >${mfCase.description }</textarea></td>
            <span style="display: none" for="mfCase_description" class="tips">请给我们留言 </span>
        </tr>
        <tr>
            <td class="title"><span for="validateCode" class="ui-icon icon-required">*</span> 验证码</td>
            <td>
                <input name="validateCode" maxlength="10" id="validateCode" size="10" type="text" />
                <img title="please refresh" id="code_img_${caseType}" style="vertical-align: middle" src="${ROOT}/images/captcha.jpg?r=${mf:now().time}" onclick="getPassCode('code_img')" height="22" width="70"/>
                <span class="tips">看不清楚？ <a href="javascript:void(0);" onclick="getPassCode('code_img')">换一张</a></span>
                <span style="display: none" for="validateCode" class="tips">请填写验证码 </span>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="command-group">
        <button type="submit" class="button-highlight button-big"><span>发送信息</span></button>
        <a href="javascript:void(0);" class="reset-link color-text" onclick="document.partyForm.reset();">重新填写</a>
    </div>
</form>
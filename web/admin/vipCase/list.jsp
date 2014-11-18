<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${caseType eq 'ent_vip'? '企业活动':'私人派对'}列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-common.jsp"%>
<%@include file="/WEB-INF/inc/admin/import-js-date.jsp"%>
<script type="text/javascript">
<!--
function OnReset(map){
	for(var k in map){
		$("#"+k).val(map[k]);
	}
}
function OnSearch(form){
	form.action = "${ROOT }/admin/vipCase/search.do";
}
function OnUpdate(form,id) {
	form.action = "${ROOT }/admin/vipCase/edit.do?cids="+id;
}
function OnView(id) {
    var page = "${ROOT }/admin/vipCase/view.do?cids="+id;
    window.open(page,"_blank");
}
function OnDelete(form,id) {
	if(window.confirm("确定要删除该${caseType eq 'ent_vip'? '企业活动':'私人派对'}吗？")){
		form.action = "${ROOT }/admin/vipCase/remove.do?cids="+id;
	}
}
function OnSubmit(form) {
	
}
// -->
</script>
</head>
<body style="text-align: left;">
<form name="form1" method="post" action="#" onsubmit="javascript:return OnSubmit(this);" id="form1">
<input type="hidden" value="${caseType}" name="mfCase.caseType" />
<div id="Panel1" style="width:100%;">
	        
        <table cellpadding="3" cellspacing="1" class="tableborder1">           
            <tr>
                <td colspan="4" class="titlebg">
                    <span id="Label0">${caseType eq 'ent_vip'? '企业活动':'私人派对'}列表</span></td>
            </tr>
            <c:if test="${caseType eq 'ent_vip'}">
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">企业/机构</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <input name="mfCase.companyName" type="text" value="${mfCase.companyName }" style="width: 500px"  maxlength="50" id="mfCase_companyName" />
                </td>
            </tr>
            </c:if>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label1">${caseType eq 'ent_vip'? '联系人':'姓名'}</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.contactName" type="text" value="${mfCase.contactName }" style="width: 300px"  maxlength="50" id="mfCase_contactName" />
                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label2">${caseType eq 'ent_vip'? '联系电话':'电话'}</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.contactPhone" type="text" value="${mfCase.contactPhone }" style="width: 300px"  maxlength="50" id="mfCase_contactPhone" />
                </td>
            </tr>
            <c:if test="${caseType eq 'vip'}">
                <tr>
                    <td class="tablebody1" width="15%">
                        <span id="Label1">您所在的城区</span>：</td>
                    <td class="tablebody1" width="35%">
                        <m:dictlist name="mfCase.region" showAllValue="" showAllText="-=所有=-" id="mfCase_region" dict="CD_REGION" value="${mfCase.region}"/>
                    </td>
                    <td class="tablebody1" width="15%">
                        <span id="Label1">您所在的商圈</span>：</td>
                    <td class="tablebody1" width="35%">
                        <input name="mfCase.area" type="text" value="${mfCase.area }" style="width: 200px"  maxlength="50" id="mfCase_area" />
                    </td>
                </tr>
            </c:if>
            <tr>
                <td class="tablebody1" width="15%">
                    <c:if test="${caseType ne 'ent_vip'}"><span id="Label3">儿童人数</span>：</td></c:if>&nbsp;
                <td class="tablebody1" width="35%">
                    <c:if test="${caseType ne 'ent_vip'}">
                        从：<input name="mfCase.kidsNum" type="text" value="${mfCase.kidsNum }" style="width: 50px"  maxlength="10" id="mfCase_kidsNum" />
                        &nbsp;&nbsp;到：<input name="mfCase.kidsNum2" type="text" value="${mfCase.kidsNum2 }" style="width: 50px"  maxlength="10" id="mfCase_kidsNum2" />
                    </c:if>&nbsp;
                </td>
                <td class="tablebody1" width="15%">
                <span id="Label3">电子邮箱</span>：</td>
                <td class="tablebody1" width="35%">
                    <input name="mfCase.email" type="text" value="${mfCase.email }" style="width: 300px"  maxlength="50" id="mfCase_email" />
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label3">参加人数</span>：</td>
                <td class="tablebody1" width="35%">
                        从：<input name="mfCase.memberNum" type="text" value="${mfCase.memberNum }" style="width: 50px"  maxlength="10" id="mfCase_memberNum" />
                        &nbsp;&nbsp;到：<input name="mfCase.memberNum2" type="text" value="${mfCase.memberNum2 }" style="width: 50px"  maxlength="10" id="mfCase_memberNum2" />

                </td>
                <td class="tablebody1" width="15%">
                    <span id="Label4">是否需要我们<br/>推荐活动场所</span>：</td>
                <td class="tablebody1" width="35%">
                    <input type="radio" value=""${empty mfCase.needLocation?' checked="checked"':'' } name="mfCase.needLocation" />未知
                    <input type="radio" value="Y"${mfCase.needLocation eq 'Y'?' checked="checked"':'' } name="mfCase.needLocation" />是
                    <input type="radio" value="N"${mfCase.needLocation eq 'N'?' checked="checked"':'' } name="mfCase.needLocation" />否
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label5">您的活动场所</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <textarea rows="" cols="" style="width: 85%;height: 20px" name="mfCase.location" >${mfCase.location }</textarea>
                </td>
            </tr>
            <tr>
                <td class="tablebody1" width="15%">
                    <span id="Label5">给我们的留言</span>：</td>
                <td class="tablebody1" colspan="3" width="85%">
                    <textarea rows="" cols="" style="width: 85%;height: 20px" name="mfCase.description" >${mfCase.description }</textarea>
                </td>
            </tr>
            <tr>
                <td class="tablebody1">
                    <span id="Label7">提交时间</span>：</td>
                <td class="tablebody1" colspan="3">
                    从：<m:time name="mfCase.createdDate" value="${mfCase.createdDate }" id="createdDate"></m:time>
                    &nbsp;&nbsp;到：<m:time name="mfCase.createdDate2" value="${mfCase.createdDate2 }" id="createdDate2"></m:time>
                </td>
            </tr>

            <tr>
                <td class="tablebody1" colspan="4" style="text-align: right;">                
                    <input type="submit" name="Button1" value="开始查询" onclick="javascript:OnSearch(this.form)" id="Button1" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />
                    <input type="button" name="Button2" value="重置内容" onclick="javascript:OnReset({title:'',createdDate:'',createdDate2:''})" id="Button2" class="button" onmouseover="this.className='button_mouseover'" onmouseout="this.className='button_mouseout'" />&nbsp;
                </td>
            </tr>
        </table>   
        
</div>        
        
        <span id="lberr" style="color:Red;"></span>
        <br />
        <div>
	<table class="blueGrid" cellspacing="1" cellpadding="3" border="0" id="GridView1">
		<tr class="Gridhead" style="font-weight:normal;font-style:normal;">
			<th scope="col">序号</th>
            <c:if test="${caseType eq 'ent_vip'}"><th scope="col">企业/机构</th></c:if>
            <th scope="col">${caseType eq 'ent_vip'? '联系人':'姓名'}</th>
            <th scope="col">${caseType eq 'ent_vip'? '联系电话':'电话'}</th>
            <th scope="col">电子邮件</th>
            <c:if test="${caseType eq 'vip'}">
            <th scope="col">所在城区</th><th scope="col">所在商圈</th>
            </c:if>
            <th scope="col">参加人数</th>
            <c:if test="${caseType eq 'vip'}"><th scope="col">儿童人数</th></c:if>
            <th scope="col">推荐场所？</th><th scope="col">活动场所</th><th scope="col">留言</th>
            <th scope="col">提交时间</th>
            <th scope="col">功能</th>
		</tr>
		<c:forEach var="n" varStatus="status" items="${list}">
		<tr class="Griditem" onmouseout="this.style.backgroundColor='White';this.style.color='RoyalBlue'" onmouseover="this.style.backgroundColor='#fffdd7';this.style.color='#008fc3'" style="Cursor:hand">
			<td>${status.index + 1 }</td>
            <c:if test="${caseType eq 'ent_vip'}"><td>${n.companyName} </td></c:if>
            <td>${n.contactName} </td>
            <td>${n.contactPhone} </td><td>${n.email} </td>
            <c:if test="${caseType eq 'vip'}">
                <td>${mf:dict('CD_REGION',n.region )} </td><td>${n.area}</td>
            </c:if>
            <td>${n.memberNum} </td>
            <c:if test="${caseType eq 'vip'}"><td>${n.kidsNum} </td></c:if>
            <td align="center">${(n.needLocation ne "N")?"是":"否" }</td>
            <td title="${n.location}">${mf:substring(n.location, 20)} </td>
            <td title="${n.description}">${mf:substring(n.description, 20)} </td>
            <td>${mf:fmtDateTime(n.createdDate)} </td>

            <td>
                <input type="submit" value="查看" onclick="javascript:OnView(${n.caseNo});" class="Gridbutton" />
            </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
	<m:pagination></m:pagination>
</div>
        <br />
        <span id="Label12" style="color:Red;">操作说明：</span>&nbsp;<br />
        <span id="Label13" style="color:Red;">1。通过所有查询条件的集合查询相应的${caseType eq 'ent_vip'? '企业活动':'私人派对'}，查询结果以表格形式显示<br /></span>

        
</form>

</body></html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="${caseType eq 'ent_vip'? '企业活动':'私人派对'}" />
<%@include file ="../inc/header.jsp" %>
<!-- #include file ="inc/header.htm" -->
<script type="application/javascript">
    $(document).ready(function(){
        $('#partyForm_vip').children().find('input').blur(function(){
            validateFormField(this);
        });
        $('#partyForm_vip').children().find('textarea').blur(function(){
            validateFormField(this);
        });
        $('#partyForm_ent_vip').children().find('input').blur(function(){
            validateFormField(this);
        });
        $('#partyForm_ent_vip').children().find('textarea').blur(function(){
            validateFormField(this);
        });
    });
//    $(document).ready

    var OnVipCaseSubmit = function(form){
        var form = $(form);
        if(!validateFormFields(form)){
            return false;
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: form.attr('action'),
            data: form.serialize(),
            complete :function(){
            },
            success: function(msg) {
                if(msg.isError){
                    alert(msg.message);
                    return;
                }
                alert(msg.message);
                form[0].reset();
            }
        });
    }
</script>
<div class="page-content">
    <div class="main-content">
        <%--<h1>${caseType eq 'ent_vip'? '企业活动':'私人派对'}</h1>--%>
        <div id="accountTabs">
        <ul>
            <li><a id="vipTab" href="#vip-tab" onclick="getPassCode('code_img_vip')">私人派对</a></li>
            <li><a id="entVipTab" href="#ent-vip-tab" onclick="getPassCode('code_img_ent_vip')">企业活动</a></li>
        </ul>
        <div id="vip-tab">
            <jsp:include page="case.jsp">
                <jsp:param name="caseType" value="vip"/>
            </jsp:include>
        </div>
        <div id="ent-vip-tab">
            <jsp:include page="case.jsp">
                <jsp:param name="caseType" value="ent_vip"/>
            </jsp:include>
        </div>
    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<%@include file ="../inc/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="关于快乐秘方" />
<%@include file ="inc/header.jsp" %>
<!-- #include file ="inc/header.htm" -->
<style>
    #uploadPreview {
        width: 168px;
        height: 168px;
        background-position: center center;
        background-size: cover;
        border: 4px solid #fff;
        -webkit-box-shadow: 0 0 1px 1px rgba(0, 0, 0, .3);
        display: inline-block;}
</style>
<script type="application/javascript">
    $(document).ready(function () {
    });
</script>
<div class="page-content">
    <div class="main-content">
        <h1>关于快乐秘方</h1>
        <form id="userInfoForm" action="#" onsubmit="return false;" class="field-form">
            <c:set var="about" value="${mf:invokeMethod('mfContentService', 'getAbout')}"/>
            ${about.content}
        </form>
    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<%@include file ="inc/footer.jsp" %>
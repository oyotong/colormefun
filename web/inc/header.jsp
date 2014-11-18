<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie10 lt-ie9 lt-ie8 lt-ie7" lang="en"><![endif]-->
<!--[if IE 7]>
<html class="no-js ie7 lt-ie10 lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if IE 8]>
<html class="no-js ie8 lt-ie10 lt-ie9" lang="en"><![endif]-->
<!--[if IE 9]>
<html class="no-js ie9 lt-ie10" lang="en"><![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>${_title}${empty _title?'':' - '}快乐秘方 Color Me Fun</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="content-language" content="en-us"/>
    <link href="${ROOT}/resources/css/default.css" rel="stylesheet" type="text/css"/>
    <!--[if lte IE 9]>
    <script src="${ROOT}resources/scripts/html5shiv.js" type="text/javascript"></script>
    <![endif]-->
    <script src="${ROOT}/resources/scripts/common.jsp" type="text/javascript"></script>
    <script src="${ROOT}/resources/scripts/json2.js" type="text/javascript"></script>
    <script src="${ROOT}/resources/scripts/modernizr.custom.js" type="text/javascript"></script>
    <script src="${ROOT}/resources/scripts/es5-shim.js" type="text/javascript"></script>
    <script src="${ROOT}/resources/scripts/jquery.js" type="text/javascript"></script>
    <script src="${ROOT}/resources/scripts/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
    <script src="${ROOT}/resources/scripts/command.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            ui.init();
        });
    </script>
</head>
<body>
<%@include file="login.jsp" %>
<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div></div>
<div class="page-wrapper">
    <header class="page-content">
        <h1 class="page-logo"><img src="${ROOT}/resources/images/logo.png" alt="快乐秘方 Color Me Fun" /></h1>
        <div class="select-area">
            <span>成都市</span>
            <a href="javascript:void(0);"></a>
            <ul>
                <li><a href="#">成都市</a></li>
            </ul>
        </div>
        <div class="page-toolbar">
            <span id="loginStatusDiv">&nbsp;</span><span id="shopCartStatusDiv"></span>
            <div id="myCmfList" class="bubble">
                <s class="ui-icon icon-bubble-arrow-top"></s>
                <ul class="account-list">
                    <li><a href="${ROOT}/user/order/myOrder.do">我的订单</a></li>
                    <li><a href="${ROOT}/user/myFavorites.do">我的收藏夹</a></li>
                    <li><a href="${ROOT}/user/myInfo.do">我的信息</a></li>
                </ul>
            </div>
        </div>
        <nav class="page-nav">
            <a href="/index.jsp">活动日历</a>
            <a href="${mf:param('FUN_URL', 'URL')}" target="_blank">FUN相册</a>
            <a href="${ROOT}/user/vipCase/addVip.do">私人订制</a>
            <a href="${ROOT}/about.jsp">关于快乐秘方</a>
        </nav>
    </header>
    <script type="text/javascript">
        var selectArea = ui.Popup($('.select-area > a'), $('.select-area > ul'), {
            clickToShow: true,
            position: {
                my: 'right top',
                at: 'left bottom'
            }
        });
    </script>
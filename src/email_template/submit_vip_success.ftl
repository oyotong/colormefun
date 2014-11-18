<#setting number_format="#">
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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>快乐秘方 Color Me Fun</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="content-language" content="en-us"/>
    <style>
    /*! normalize.css v3.0.1 | MIT License | git.io/normalize */
    /** 1. Set default font family to sans-serif. 2. Prevent iOS text size adjust after orientation change, without disabling user zoom. */
    html { font-family: sans-serif; /* 1 */ -ms-text-size-adjust: 100%; /* 2 */ -webkit-text-size-adjust: 100%; /* 2 */ }

    /** Remove default margin. */
    body { margin: 0; }

    /* HTML5 display definitions ========================================================================== */
    /** Correct `block` display not defined for any HTML5 element in IE 8/9. Correct `block` display not defined for `details` or `summary` in IE 10/11 and Firefox. Correct `block` display not defined for `main` in IE 11. */
    article, aside, details, figcaption, figure, footer, header, hgroup, main, nav, section, summary { display: block; }

    /** 1. Correct `inline-block` display not defined in IE 8/9. 2. Normalize vertical alignment of `progress` in Chrome, Firefox, and Opera. */
    audio, canvas, progress, video { display: inline-block; /* 1 */ vertical-align: baseline; /* 2 */ }

    /** Prevent modern browsers from displaying `audio` without controls. Remove excess height in iOS 5 devices. */
    audio:not([controls]) { display: none; height: 0; }

    /** Address `[hidden]` styling not present in IE 8/9/10. Hide the `template` element in IE 8/9/11, Safari, and Firefox < 22. */
    [hidden], template { display: none; }

    /* Links ========================================================================== */
    /** Remove the gray background color from active links in IE 10. */
    a { background: transparent; }

    /** Improve readability when focused and also mouse hovered in all browsers. */
    a:active, a:hover { outline: 0; }

    /* Text-level semantics ========================================================================== */
    /** Address styling not present in IE 8/9/10/11, Safari, and Chrome. */
    abbr[title] { border-bottom: 1px dotted; }

    /** Address style set to `bolder` in Firefox 4+, Safari, and Chrome. */
    b, strong { font-weight: bold; }

    /** Address styling not present in Safari and Chrome. */
    dfn { font-style: italic; }

    /** Address variable `h1` font-size and margin within `section` and `article` contexts in Firefox 4+, Safari, and Chrome. */
    h1 { font-size: 2em; margin: 0.67em 0; }

    /** Address styling not present in IE 8/9. */
    mark { background: #FF0; color: #000; }

    /** Address inconsistent and variable font size in all browsers. */
    small { font-size: 80%; }

    /** Prevent `sub` and `sup` affecting `line-height` in all browsers. */
    sub, sup { font-size: 75%; line-height: 0; position: relative; vertical-align: baseline; }

    sup { top: -0.5em; }

    sub { bottom: -0.25em; }

    /* Embedded content ========================================================================== */
    /** Remove border when inside `a` element in IE 8/9/10. */
    img { border: 0; }

    /** Correct overflow not hidden in IE 9/10/11. */
    svg:not(:root) { overflow: hidden; }

    /* Grouping content ========================================================================== */
    /** Address margin not present in IE 8/9 and Safari. */
    figure { margin: 1em 40px; }

    /** Address differences between Firefox and other browsers. */
    hr { -moz-box-sizing: content-box; box-sizing: content-box; height: 0; }

    /** Contain overflow in all browsers. */
    pre { overflow: auto; }

    /** Address odd `em`-unit font size rendering in all browsers. */
    code, kbd, pre, samp { font-family: monospace, monospace; font-size: 1em; }

    /* Forms ========================================================================== */
    /** Known limitation: by default, Chrome and Safari on OS X allow very limited styling of `select`, unless a `border` property is set. */
    /** 1. Correct color not being inherited. Known issue: affects color of disabled elements. 2. Correct font properties not being inherited. 3. Address margins set differently in Firefox 4+, Safari, and Chrome. */
    button, input, optgroup, select, textarea { color: inherit; /* 1 */ font: inherit; /* 2 */ margin: 0; /* 3 */ }

    /** Address `overflow` set to `hidden` in IE 8/9/10/11. */
    button { overflow: visible; }

    /** Address inconsistent `text-transform` inheritance for `button` and `select`. All other form control elements do not inherit `text-transform` values. Correct `button` style inheritance in Firefox, IE 8/9/10/11, and Opera. Correct `select` style inheritance in Firefox. */
    button, select { text-transform: none; }

    /** 1. Avoid the WebKit bug in Android 4.0.* where (2) destroys native `audio` and `video` controls. 2. Correct inability to style clickable `input` types in iOS. 3. Improve usability and consistency of cursor style between image-type `input` and others. */
    button, html input[type="button"], input[type="reset"], input[type="submit"] { -webkit-appearance: button; /* 2 */ cursor: pointer; /* 3 */ }

    /** Re-set default cursor for disabled elements. */
    button[disabled], html input[disabled] { cursor: default; }

    /** Remove inner padding and border in Firefox 4+. */
    button::-moz-focus-inner, input::-moz-focus-inner { border: 0; padding: 0; }

    /** Address Firefox 4+ setting `line-height` on `input` using `!important` in the UA stylesheet. */
    input { line-height: normal; }

    /** It's recommended that you don't attempt to style these elements. Firefox's implementation doesn't respect box-sizing, padding, or width.  1. Address box sizing set to `content-box` in IE 8/9/10. 2. Remove excess padding in IE 8/9/10. */
    input[type="checkbox"], input[type="radio"] { box-sizing: border-box; /* 1 */ padding: 0; /* 2 */ }

    /** Fix the cursor style for Chrome's increment/decrement buttons. For certain `font-size` values of the `input`, it causes the cursor style of the decrement button to change from `default` to `text`. */
    input[type="number"]::-webkit-inner-spin-button, input[type="number"]::-webkit-outer-spin-button { height: auto; }

    /** 1. Address `appearance` set to `searchfield` in Safari and Chrome. 2. Address `box-sizing` set to `border-box` in Safari and Chrome (include `-moz` to future-proof). */
    input[type="search"] { -webkit-appearance: textfield; /* 1 */ -moz-box-sizing: content-box; -webkit-box-sizing: content-box; /* 2 */ box-sizing: content-box; }

    /** Remove inner padding and search cancel button in Safari and Chrome on OS X. Safari (but not Chrome) clips the cancel button when the search input has padding (and `textfield` appearance). */
    input[type="search"]::-webkit-search-cancel-button, input[type="search"]::-webkit-search-decoration { -webkit-appearance: none; }

    /** Define consistent border, margin, and padding. */
    fieldset { border: 1px solid #C0C0C0; margin: 0 2px; padding: 0.35em 0.625em 0.75em; }

    /** 1. Correct `color` not being inherited in IE 8/9/10/11. 2. Remove padding so people aren't caught out if they zero out fieldsets. */
    legend { border: 0; /* 1 */ padding: 0; /* 2 */ }

    /** Remove default vertical scrollbar in IE 8/9/10/11. */
    textarea { overflow: auto; }

    /** Don't inherit the `font-weight` (applied by a rule above). NOTE: the default cannot safely be changed in Chrome and Safari on OS X. */
    optgroup { font-weight: bold; }

    /* Tables ========================================================================== */
    /** Remove most spacing between table cells. */
    table { border-collapse: collapse; border-spacing: 0; }

    td, th { padding: 0; color: #464646; font-size: 10px; }

    /* fix ie6/ie7 default browser vertical scrollbar */
    * html, * + html { overflow: auto; }

    /* set form element font inherit, padding, and margin
    * input[type="text"] and textarea border are set to outer, others are set to inner
    */
    input[type="text"], button, input[type="button"], input[type="submit"], textarea, select { font: inherit; margin: 0; padding: 0; }

    textarea::-ms-clear, input[type="text"]::-ms-clear { display: none; }

    /* fixed table */
    .table-fixed { table-layout: fixed; }

    /* add word break style for all browsers */
    .word-break { white-space: normal; word-wrap: break-word; }

    .word-break-all { white-space: normal; word-break: break-all; }

    .no-word-break { white-space: nowrap; word-break: keep-all; text-overflow: ellipsis; overflow: hidden; min-width: 2em; /* width is required */ }

    /* add lowercase/uppercase */
    .uppercase { text-transform: uppercase; }

    .lowercase { text-transform: lowercase; }

    /* fix ie6/7 inline-block issue */
    .inline-block { zoom: 1; display: inline; }

    /* Text truncation */
    .text-ellipsis { display: inline-block; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; }

    /* remove input text inline clear button of ie9+ */
    .ui-icon { display: inline-block; width: 16px; height: 16px; vertical-align: middle; }
    .ui-icon + span { margin-left: 0.25em; vertical-align: middle; }
    .ui-icon.icon-account { width: 20px; height: 20px; background: url("${ROOT}/images/icon/account.png") no-repeat center center; }
    a:hover .ui-icon.icon-account, a:focus .ui-icon.icon-account, a.active .ui-icon.icon-account { background-image: url("${ROOT}/images/icon/account-active.png"); }
    .ui-icon.icon-arrow { width: 20px; height: 20px; background: url("${ROOT}/images/icon/arrow.png") no-repeat center center; }
    a:hover .ui-icon.icon-arrow, a:focus .ui-icon.icon-arrow, a.active .ui-icon.icon-arrow { background-image: url("${ROOT}/images/icon/arrow-active.png"); }
    .ui-icon.icon-cart { width: 20px; height: 20px; background: url("${ROOT}/images/icon/cart.png") no-repeat center center; }
    a:hover .ui-icon.icon-cart, a:focus .ui-icon.icon-cart { background-image: url("${ROOT}/images/icon/cart-active.png"); }
    .ui-icon.icon-bubble-arrow-top { width: 14px; height: 7px; background: url("${ROOT}/images/icon/bubble-arrow-top.png") no-repeat center center; }
    .ui-icon.icon-calendar-prev { width: 20px; height: 20px; background: url("${ROOT}/images/icon/calendar-prev.png") no-repeat center center; }
    .ui-icon.icon-calendar-next { width: 20px; height: 20px; background: url("${ROOT}/images/icon/calendar-next.png") no-repeat center center; }
    .ui-icon.icon-info { width: 19px; height: 19px; background: url("${ROOT}/images/icon/info-msg.png") no-repeat center center; }
    .ui-icon.icon-confirm-info-msg { width: 24px; height: 24px; background: url("${ROOT}/images/icon/confirm-info-msg.png") no-repeat center center; }
    .ui-icon.icon-required { background: url("${ROOT}/images/icon/required.png") no-repeat center center; }

    .page-content { margin-left: auto; margin-right: auto; width: 1020px; }

    html, body { height: 100%; }

    body .page-wrapper > header { overflow: hidden; *zoom: 1; margin-bottom: 15px; padding-top: 30px; }
    body .page-wrapper > header .page-logo { margin: 0; margin-left: 40px; margin-right: 20px; color: white; font-weight: 700; }
    body > footer { height: 70px; }
    body > footer > div { padding: 20px 40px 0; }
    body .page-wrapper { min-height: 100%; height: auto !important; height: 100%; margin: 0 auto -90px; }
    body .page-footer-placeholder { height: 90px; }


    h1 { margin: 1em 0; font-size: 18px; font-weight: 700; }
    h1 .sub { font-size: 14px; }

    h2 { margin: 0; font-size: 14px; font-weight: 700; }

    h3 { margin: 0; font-size: 12px; font-weight: 700; }

    a { color: #2f9acc; text-decoration: none; outline: 0 none; -webkit-transition-property: color; -moz-transition-property: color; -o-transition-property: color; transition-property: color; -webkit-transition-duration: 0.25s; -moz-transition-duration: 0.25s; -o-transition-duration: 0.25s; transition-duration: 0.25s; }
    a:focus, a:hover { text-decoration: underline; }
    a.action-link { color: #636363; }
    a.action-link:active { color: #2f9acc; }

    .link-button { display: inline-block; text-decoration: none; }
    .link-button:focus, .link-button:hover { text-decoration: none; }

    .no-text-decoration { text-decoration: none; }

    strong { font-weight: 700; }

    img { border: 0 none; }

    hr.thin { border: none; margin: 0; height: 0; }

    p { margin: 0; margin-bottom: 1em; }
    p.strong { font-size: 14px; font-weight: 700; }

    ul.no-list-style, ol.no-list-style { list-style: none; }

    .icon-only span { display: inline-block; width: 0; overflow: hidden; text-indent: -1000px; }



    button.icon-only { min-width: 0; padding: 0 0.5em; }

    input[type="text"], input[type="password"], textarea, select { padding: 0 0.5em; border: 1px solid #b6b6b6; line-height: 28px; vertical-align: middle; -webkit-transition-property: border-color; -moz-transition-property: border-color; -o-transition-property: border-color; transition-property: border-color; -webkit-transition-duration: 0.25s; -moz-transition-duration: 0.25s; -o-transition-duration: 0.25s; transition-duration: 0.25s; }
    input[type="text"].big, input[type="password"].big, textarea.big, select.big { height: 30px; line-height: 30px; font-size: 18px; }
    input[type="text"]:hover, input[type="text"]:focus, input[type="password"]:hover, input[type="password"]:focus, textarea:hover, textarea:focus, select:hover, select:focus { border-color: #b7d4ea; }

    input[type="checkbox"], input[type="radio"] { vertical-align: middle; }
    input[type="checkbox"] + span, input[type="checkbox"] + label, input[type="radio"] + span, input[type="radio"] + label { vertical-align: middle; }

    form > .command-group { padding: 1em 0; border-top: 1px solid #dde0e8; background-color: #f7fafb; text-align: center; }
    form > .command-group button + button { margin-left: 4em; }
    form > .command-group .reset-link { margin-left: 4em; }
    form.field-form { border: 1px solid #c0d3da; -webkit-border-radius: 10px; -moz-border-radius: 10px; -ms-border-radius: 10px; -o-border-radius: 10px; border-radius: 10px; overflow: hidden; }

    .ui-tabs .ui-tabs-nav { height: 31px; margin: 0; padding: 0; }
    .ui-tabs .ui-tabs-nav li { float: left; list-style: none; }
    .ui-tabs .ui-tabs-nav li .ui-tabs-anchor { display: block; min-width: 8em; padding: 0 1em; line-height: 30px; border: 1px solid #2f9acc; background-color: white; text-decoration: none; text-align: center; }
    .ui-tabs .ui-tabs-nav li + li { margin-left: -1px; }
    .ui-tabs .ui-tabs-nav li.ui-tabs-active .ui-tabs-anchor { background-color: #2f9acc; color: white; }
    .ui-tabs .ui-tabs-panel { border: 1px solid #2f9acc; background-color: white; }

    .ui-widget-overlay { position: fixed; top: 0; width: 100%; height: 100%; }
    .rgba .ui-widget-overlay { background-color: rgba(0, 0, 0, 0.55); }
    .no-rgba .ui-widget-overlay { background-color: Black; filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=55); opacity: 0.55; }

    .bubble { display: none; position: absolute; background-color: white; color: #464646; z-index: 100; }
    .bubble .icon-bubble-arrow-top { position: absolute; top: -7px; left: 50%; margin-left: -7px; }

    .small-pic { display: block; max-width: 115px; max-height: 120px; }

    .price { font-size: 16px; }

    .color-main { color: #2f9acc !important; }

    .color-text { color: #464646 !important; }

    .info-msg { display: inline-block; line-height: 1.5em; color: #636363; vertical-align: middle; }
    .info-msg span { display: inline-block; }
    .info-msg a { color: #2f9acc; text-decoration: underline; }

    body { background-color: #2f9acc; color: #464646; font-family: sans-serif; font-size: 10px; line-height: 1.5em; }

    .main-content { background-color: white; color: #464646; -webkit-box-shadow: 2px 3px 13px rgba(4, 4, 4, 0.38); -moz-box-shadow: 2px 3px 13px rgba(4, 4, 4, 0.38); box-shadow: 2px 3px 13px rgba(4, 4, 4, 0.38); }
    .main-content > h1 { margin-top: -1em; color: #2f9acc; }

    .page-logo { float: left; }

    .select-area { overflow: hidden; *zoom: 1; float: left; margin-top: 43px; background-color: white; color: #464646; vertical-align: bottom; }
    .select-area > span { float: left; display: block; width: 4em; height: 27px; line-height: 27px; padding: 0 1em; }
    .select-area > a { float: left; display: block; width: 27px; height: 27px; background: #fff568 url("${ROOT}/images/select-area-arrow.png") no-repeat center center; }
    .select-area > a:hover, .select-area > a:focus { background-color: #ffe32b; }
    .select-area ul { display: none; position: absolute; margin: 0; padding: 0; background-color: white; z-index: 10; }
    .select-area ul li { list-style: none; }
    .select-area ul li a { display: block; width: 4em; height: 27px; line-height: 27px; padding: 0 1em; color: #464646; text-decoration: none; }
    .select-area ul li a:hover, .select-area ul li a:focus { background-color: #ffe32b; }

    .page-toolbar { float: right; margin-right: 40px; }
    .page-toolbar > span { margin-left: 2em; }
    .page-toolbar a { color: white; text-decoration: none; vertical-align: middle; }
    .page-toolbar a:hover, .page-toolbar a:focus, .page-toolbar a.active { color: #fff568; }

    .page-nav { float: right; width: 576px; height: 36px; line-height: 36px; margin-top: 13px; margin-right: 40px; background-color: #fff568; font-size: 16px; }
    .page-nav a { float: left; padding: 0 2em; color: #464646; text-align: center; text-decoration: none; -webkit-transition-property: background; -moz-transition-property: background; -o-transition-property: background; transition-property: background; -webkit-transition-duration: 0.25s; -moz-transition-duration: 0.25s; -o-transition-duration: 0.25s; transition-duration: 0.25s; }
    .page-nav a:hover, .page-nav a:focus, .page-nav a.active { background-color: #ffe32b; }

    .account-list { margin: 0; padding: 0; }
    .account-list li { list-style: none; }
    .account-list li a { display: block; height: 36px; line-height: 36px; padding: 0 1em; color: #464646; }
    .account-list li a:hover, .account-list li a:focus { color: #2f9acc; }
    .account-list li + li { border-top: 1px solid #d0d0d0; }

    .footer-copyright { float: right; }

    .footer-logo { margin-right: 50px; vertical-align: top; }

    .contact-button { display: inline-block; width: 50px; height: 40px; color: white; text-decoration: none !important; vertical-align: top; white-space: nowrap; }
    .contact-button .ui-icon { display: block; width: 40px; height: 40px; margin: 0 auto; background-image: url("${ROOT}/images/contacts-button-icon.png"); background-repeat: no-repeat; }
    .contact-button .ui-icon.icon-weibo { background-position: 0 0; }
    .contact-button .ui-icon.icon-wechat { background-position: -40px 0; }
    .contact-button .ui-icon.icon-linkedin { background-position: -80px 0; }
    .contact-button .ui-icon.icon-qzone { background-position: -120px 0; }
    .contact-button span { display: block; margin: 0; visibility: hidden; line-height: 2.5em; text-align: center; }
    .contact-button:hover .ui-icon.icon-weibo { background-position: 0 -40px; }
    .contact-button:hover .ui-icon.icon-wechat { background-position: -40px -40px; }
    .contact-button:hover .ui-icon.icon-linkedin { background-position: -80px -40px; }
    .contact-button:hover .ui-icon.icon-qzone { background-position: -120px -40px; }
    .contact-button:hover span { visibility: visible; }
    </style>
</head>
<body>
<div class="page-wrapper">
    <header class="page-content">
        <h1 class="page-logo"><img src="${ROOT}/resources/images/logo.png" alt="快乐秘方 Color Me Fun" /></h1>
    </header>
    <div class="page-content">
        <div class="main-content">
            <h1><#if caseType == 'ent_vip'>企业活动<#else>私人派对</#if></h1>
            <form name="partyForm" id="partyForm" onsubmit="return false;" class="field-form">
                <table class="field-table">
                    <tbody>
                    <#if caseType == 'ent_vip'>
                        <tr>
                            <td class="title"><span for="mfCase_companyName" class="ui-icon icon-required"></span> 企业/机构</td>
                            <td>
                                <input name="mfCase.companyName" disabled type="text" value="${mfCase.companyName }" id="mfCase_companyName" size="25" />
                                <span for="mfCase_companyName" class="tips">请填写您的企业/机构名称</span>
                            </td>
                        </tr>
                    </#if>
                    <tr>
                        <td class="title"><span for="mfCase_contactName" class="ui-icon icon-required"></span> <#if caseType == 'ent_vip'> 联系人<#else> 姓名</#if></td>
                        <td>
                            <input name="mfCase.contactName" disabled type="text" value="${mfCase.contactName }" id="mfCase_contactName" size="25" />
                            <span for="mfCase_contactName" class="tips">请填写您的真实姓名</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title"><span for="mfCase_contactPhone" class="ui-icon icon-required"></span> <#if caseType == 'ent_vip'>联系电话<#else> 电话</#if></td>
                        <td>
                            <input name="mfCase.contactPhone" disabled type="text" value="${mfCase.contactPhone }" id="mfCase_contactPhone" size="25" />
                            <span for="mfCase_contactPhone" class="tips">请填写您的手机号码</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title"><span for="mfCase_email" class="ui-icon icon-required"></span> 电子邮箱</td>
                        <td>
                            <input name="mfCase.email" disabled type="text" value="${mfCase.email }" id="mfCase_email" size="25" />
                            <span for="mfCase_email" class="tips">请填写您的电子邮箱地址</span>
                        </td>
                    </tr>
                    <#if caseType == 'vip'>
                        <tr>
                            <td class="title">您所在城区</td>
                            <td>
                                <input name="mfCase.region" disabled type="text" size="25" value="${mfCase.region}" id="mfCase_region" />

                            </td>
                        </tr>
                        <tr>
                            <td class="title">所在商区</td>
                            <td>
                                <input name="mfCase.area" disabled type="text" size="25" value="${mfCase.area }" id="mfCase_area" />
                            </td>
                        </tr>
                    </#if>
                    <tr>
                        <td class="title"><span for="mfCase_memberNum" class="ui-icon icon-required"></span> 参加人数</td>
                        <td>
                            <input name="mfCase.memberNum" disabled type="text" value="${mfCase.memberNum }" size="25" id="mfCase_memberNum" />
                            <span for="mfCase_memberNum" class="tips">请填写参加活动总人数（包括孩童）</span>
                        </td>
                    </tr>
                    <#if caseType == 'vip'>
                        <tr>
                            <td class="title">儿童人数</td>
                            <td><input disabled name="mfCase.kidsNum" type="text" value="${mfCase.kidsNum }" size="25" id="mfCase_kidsNum" /></td>
                        </tr>
                    </#if>
                    <tr>
                        <td colspan="2"><span for="mfCase.needLocation" class="ui-icon icon-required"></span> 是否需要我们推荐活动场所？</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <p>
                                <label><input disabled type="radio" value="Y"<#if mfCase.needLocation == 'Y'> checked="checked"</#if> name="mfCase.needLocation" /> 是，需要推荐活动场所</label></p>
                            <p>
                                <label><input disabled type="radio" value="N"<#if mfCase.needLocation == 'N'> checked="checked"</#if> name="mfCase.needLocation" /> 否，我们自己安排活动场所</label></p>
                            <textarea disabled placeholder="请输入您的活动场所地址" rows="3" cols="80" name="mfCase.location" >${mfCase.location }</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="title"><span for="mfCase_description" class="ui-icon icon-required"></span> 给我们留言</td>
                        <td>
                            <textarea disabled placeholder="例：12月2号下午生日派对，有无特殊要求？" rows="3" cols="80" id="mfCase_description" name="mfCase.description" >${mfCase.description }</textarea></td>
                        <span style="display: none" for="mfCase_description" class="tips">请给我们留言 </span>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    <div class="page-footer-placeholder"></div>
</div>
<footer class="page-content">
    <div>
        <div class="footer-copyright">
            @ 2014 Color Me Fun, All Rights Reserved.
        </div>
        <img class="footer-logo" src="${ROOT}/resources/images/logo-small.png" alt="快乐秘方 Color Me Fun" />
        <a href="#" class="contact-button">
            <s class="ui-icon icon-weibo"></s>
            <span>微博</span>
        </a>
        <a href="#" class="contact-button">
            <s class="ui-icon icon-wechat"></s>
            <span>微信</span>
        </a>
        <a href="#" class="contact-button">
            <s class="ui-icon icon-linkedin"></s>
            <span>Linked in</span>
        </a>
        <a href="#" class="contact-button">
            <s class="ui-icon icon-qzone"></s>
            <span>QQ空间</span>
        </a>
    </div>
</footer>
</body>
</html>



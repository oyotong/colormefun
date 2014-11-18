<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="我的信息" />
<%@include file ="../inc/header.jsp" %>
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
        var children = $('#userInfoForm').children();
        children.find("#bithday").attr("readonly", "true").datepicker();
        children.find("#uploadImage").on("change", function(){
            // Get a reference to the fileList
            var files = !!this.files ? this.files : [];

            // If no files were selected, or no FileReader support, return
            if (!files.length || !window.FileReader) return;

            // Only proceed if the selected file is an image
            if (/^image/.test( files[0].type)){

                // Create a new instance of the FileReader
                var reader = new FileReader();

                // Read the local file as a DataURL
                reader.readAsDataURL(files[0]);

                // When loaded, set image data as background of div
                reader.onloadend = function(){

                    $("#uploadPreview").css("background-image", "url("+this.result+")");

                }

            }

        });
        children.find('input').blur(function(){
            validateFormField(this);
        });
        children.find('textarea').blur(function(){
            validateFormField(this);
        });
    });

    var saveUserInfo = function(form){
        var form = $(form);
        if(!validateFormFields(form)){
            return false;
        }
        return true;
    }
</script>
<div class="page-content">
    <div class="main-content">
        <h1>我的信息</h1>
        <form id="userInfoForm" action="${ROOT}/user/saveUserInfo.do" cmethod="post" enctype="multipart/form-data" onsubmit="return saveUserInfo(this)" class="field-form">
            <input type="hidden" name="user.userNo" value="${user.userNo}" />
            <table class="field-table">
                <tr>
                    <td class="title"> 我的头像</td>
                    <td><div id="uploadPreview" style="background-image: url('${ROOT}${user.pic1}');"></div>
                        <input id="pic1" type="file" name="image1" class="fimg1" onchange="PreviewImage();" />
                    </td>
                </tr>
                <tr>
                    <td class="title"> <span for="mobilePhone" class="ui-icon icon-required"></span> 用户名</td>
                    <td><input type="text" size="25" id="mobilePhone" value="${user.mobilePhone}" name="user.mobilePhone" placeholder="请输入您的手机号" />
                        <span class="tips">手机号作为登录账号，方便您接收订单通知，<br />找回密码</span>
                    </td>
                </tr>
                <tr>
                    <td class="title"> <span for="userName" class="ui-icon icon-required"></span> 姓名</td>
                    <td><input type="text" size="25" id="userName" value="${user.userName}" name="user.userName" placeholder="请输入您的姓名" />
                        <span class="tips">方便我们联系您</span>
                    </td>
                </tr>
                <tr>
                    <td class="title"> <span for="password" class="ui-icon icon-required"></span> 请设置密码</td>
                    <td>
                        <input type="password" id="password" name="user.password" value="" size="25" placeholder="如果不修改密码，请不要填写" />
                                <span class="tips">6-20位字符，可使用字母，数字或符号的<br />组合，
                                    不建议使用纯数字，纯字母，纯符号</span>
                    </td>
                </tr>
                <tr>
                    <td class="title"> <span for="password2" class="ui-icon"></span> 请确认密码</td>
                    <td>
                        <input type="password" id="password2" value="" name="user.password2" size="25" placeholder="如果不修改密码，请不要填写" />
                        <span class="tips">请再次输入密码</span>
                    </td>
                </tr>
                <tr>
                    <td class="title"> <span for="email" class="ui-icon icon-required"></span> 电子邮箱</td>
                    <td>
                        <input type="text" id="email" value="${user.email}" name="user.email" size="25" />
                        <span class="tips">请输入电子邮箱地址</span>
                    </td>
                </tr>
                <tr>
                    <td class="title"> 生日</td>
                    <td><input type="text" size="25" id="birthday" value="${user.birthday}" name="user.birthday" placeholder="yyyy/MM/dd" />
                        <span class="tips">请输入您的生日（yyyy/MM/dd）</span>
                    </td>
                </tr>
                <tr>
                    <td class="title"> 性别</td>
                    <td><m:dictlist name="user.sex" showAllValue="" showAllText="" id="sex" dict="SEX" value="${user.sex}"/>
                        <span class="tips">请选择性别</span>
                    </td>
                </tr>
                <tr>
                    <td class="title"> 兴趣爱好</td>
                    <td>
                        <textarea placeholder="请输入您的兴趣爱好，比如：羽毛球，跑步，看电影..." id="favorite" rows="3" cols="80" name="user.favorite" >${user.favorite }</textarea>
                    </td>
                </tr>
                <tr>
                    <td class="title"> 喜欢的颜色</td>
                    <td>
                        <textarea placeholder="请输入您喜欢的颜色，比如：蓝色，桔黄色..." id="myColor" rows="3" cols="80" name="user.myColor" >${user.myColor }</textarea>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><label><input id="letMeKnow" name="user.letMeKnow" type="checkbox" /> <span>如果有新的活动信息， 通知我吧！</span></label></td>
                </tr>
                <tr>
                    <td class="title"> <span for="mfCase_companyName" class="ui-icon icon-required"></span> 验证码</td>
                    <td>
                        <input name="validateCode" maxlength="10" id="validateCode" size="10" type="text"/>
                        <img title="please refresh" id="code_img" style="vertical-align: middle" src="${ROOT}/images/captcha.jpg?r=${mf:now().time}" onclick="getPassCode('code_img')" height="22" width="70"/>
                        <span class="tips">看不清楚？ <a href="javascript:void(0);" onclick="getPassCode('code_img')">换一张</a></span>
                </tr>
            </table>
            <div class="command-group">
                <button type="submit" class="button-highlight button-big"><span>提交我的信息</span></button>
            </div>
        </form>
    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<%@include file ="../inc/footer.jsp" %>
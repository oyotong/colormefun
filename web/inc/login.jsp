<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${ROOT}/resources/css/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ROOT}/resources/scripts/login.js"></script>
<div id="accountDialog" style="display: none">
    <div id="accountTabs">
        <ul>
            <li><a id="loginTab" href="#login-tab">登录</a></li>
            <li><a id="registerTab" href="#register-tab" onclick="getPassCode('code_img')">新用户注册</a></li>
            <li><a id="getPasswordTab" href="#get-password-tab" onclick="getPassCode('code_img2');">找回密码</a></li>
        </ul>
        <div id="login-tab">
            <form id="loginForm" action="#" onsubmit="login(this);return false;">
                <input type="hidden" id="_afterUrl" />
                <table class="field-table">
                    <tr>
                        <td class="title">用户名</td>
                        <td><input type="text" id="loginMobilePhone" name="user.mobilePhone" size="25" placeholder="请输入注册手机号" />
                            <span class="errorMessage" id="loginMobilePhoneError" style="color: red"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">密码</td>
                        <td>
                            <input type="password" id="loginPassword" name="user.password" size="25" />
                            <a href="#get-password-tab" onclick="getPassCode('code_img2')" class="forgot-pwd">忘记密码？</a>
                            &nbsp;<span class="errorMessage" style="color: red" id="loginPasswordError"></span>
                        </td>
                    <tr>
                        <td></td>
                        <td><label><input type="checkbox"  name="rememberMe" /> <span>记住我</span></label></td>
                    </tr>
                    <tr>
                        <td colspan="2"><span class="errorMessage" style="color: red;text-align: center" id="loginError"></span></td>
                    </tr>
                </table>
                <div class="command-group">
                    <button type="submit" class="button-highlight button-big"><span>快乐登录</span></button>
                </div>
            </form>
        </div>
        <div id="register-tab">
            <form id="registerForm" action="#" onsubmit="register(this);return false;">
                <table class="field-table">
                    <tr>
                        <td class="title">用户名</td>
                        <td><input type="text" size="25" id="mobilePhone" name="user.mobilePhone" placeholder="请输入您的手机号" />
                            <span class="tips">手机号作为登录账号，方便您接收订单通知，<br />找回密码</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">请设置密码</td>
                        <td>
                            <input type="password" id="password" name="user.password" size="25" />
                                <span class="tips">6-20位字符，可使用字母，数字或符号的<br />组合，
                                    不建议使用纯数字，纯字母，纯符号</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">请确认密码</td>
                        <td>
                            <input type="password" id="password2" name="user.password2" size="25" />
                            <span class="tips">请再次输入密码</span>
                            &nbsp;<span class="errorMessage" style="color: red" id="password2Error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">电子邮箱</td>
                        <td>
                            <input type="text" id="email" name="user.email" size="25" />
                            <span class="tips">请输入电子邮箱地址</span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label><input id="letMeKnow" name="user.letMeKnow" type="checkbox" /> <span>如果有新的活动信息， 通知我吧！</span></label></td>
                    </tr>
                    <tr>
                        <td class="title">验证码</td>
                        <td>
                            <input name="validateCode" maxlength="10" id="validateCode" size="10" type="text"/>
                            <img title="please refresh" id="code_img" style="vertical-align: middle" src="${ROOT}/images/captcha.jpg?r=${mf:now().time}" onclick="getPassCode('code_img')" height="22" width="70"/>
                            <span class="tips">看不清楚？ <a href="javascript:void(0);" onclick="getPassCode('code_img')">换一张</a></span>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label><input id="agree" name="agree" type="checkbox" /> <span>我已阅读并同意</span></label> <a href="#">《快乐秘方用户注册协议》</a>
                            <br/>
                            <span class="errorMessage" id="agreeErr" style="color: red"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><span class="errorMessage" style="color: red;text-align: center" id="registerError"></span></td>
                    </tr>
                </table>
                <div class="command-group">
                    <button type="submit" class="button-highlight button-big"><span>立即注册</span></button>
                </div>
            </form>
        </div>
        <div id="get-password-tab">
            <form id="getPasswordForm" action="#" onsubmit="changePassword(this);return false;">
                <table class="field-table">
                    <tr>
                        <td class="title">用户名</td>
                        <td><input type="text" id="getPasswordMobilePhone" name="user.mobilePhone" size="25" placeholder="请输入注册手机号" />
                            <span class="errorMessage" id="getPasswordMobilePhoneError" style="color: red"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">验证码</td>
                        <td>
                            <input name="imgValidateCode" maxlength="10" id="getPasswordValidateCode" size="10" type="text"/>
                            <img title="please refresh" id="code_img2" style="vertical-align: middle" src="" onclick="getPassCode('code_img2')" height="22" width="70"/>
                            <span class="tips">看不清楚？ <a href="javascript:void(0);" onclick="getPassCode('code_img2')">换一张</a></span>
                    </tr>
                    <tr>
                        <td class="title">手机验证码</td>
                        <td>
                            <input name="validateCode" maxlength="10" id="mobilePhoneValidateCode" size="10" type="text"/>
                            <button class="button-highlight button-big" id="validateBtn" onsubmit="return false;" onclick="sendValidateCode(); return false;"><span id="validateBtnMsg">发送验证码到手机</span></button>
                            <br/><span class="errorMessage" style="color: red" id="mobilePhoneValidateCodeError"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">请设置密码</td>
                        <td>
                            <input type="password" id="getPasswordPassword" name="user.password" size="25" />
                                <span class="tips">6-20位字符，可使用字母，数字或符号的<br />组合，
                                    不建议使用纯数字，纯字母，纯符号</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">请确认密码</td>
                        <td>
                            <input type="password" id="getPasswordPassword2" name="user.password2" size="25" />
                            <span class="tips">请再次输入密码</span>
                            &nbsp;<span class="errorMessage" style="color: red" id="getPassworPassword2Error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><span class="errorMessage" style="color: red;text-align: center" id="getPasswordError"></span></td>
                    </tr>
                </table>
                <div class="command-group">
                    <button type="submit" class="button-highlight button-big"><span>修改密码</span></button>
                </div>
            </form>
        </div>
    </div>
</div>
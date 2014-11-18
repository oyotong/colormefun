var accountDialog = null;
$(document).ready(function () {
    accountDialog = ui.Dialog(null, $('#accountDialog'), {
        width: 690,
        closeText: '关闭窗口',
        autoOpen: false,
        modal: true
    });
    $('#accountTabs').tabs();

    // validation
    $('#loginMobilePhone').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }
        if(!(/^[0-9]{11}$/.test(val))){
            showErr(this);
            return false;
        }
//                if(!checkMobilePhone(val)){
//                    showErr(this);
//                    return false;
//                }
        showOk(this);
        return true;
    });

    $('#getPasswordMobilePhone').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^[0-9]{11}$/.test(val))){
            showErr(this);
            return false;
        }

        if(checkMobilePhone(val)){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });
    $('#mobilePhone').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^[0-9]{11}$/.test(val))){
            showErr(this);
            return false;
        }

        if(!checkMobilePhone(val)){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });

    $('#loginPassword').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^.{6,20}$/.test(val))){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });

    $('#password').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^.{6,20}$/.test(val))){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });
    $('#password2').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);

        var val2 = $('#password').val();
        val2 = $.trim(val2);
        $('#password').val(val2);

        if(val != val2){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });

    $('#getPasswordPassword').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^.{6,20}$/.test(val))){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });
    $('#getPasswordPassword2').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);

        var val2 = $('#getPasswordPassword').val();
        val2 = $.trim(val2);
        $('#getPasswordPassword').val(val2);

        if(val != val2){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });

    $('#email').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^([a-zA-Z0-9_\.\-])+@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(val))){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });

    $('#getPasswordValidateCode').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^[0-9]{4}$/.test(val))){
            showErr(this);
            return false;
        }
        if(!validateImgCode($(this))){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });
    $('#validateCode').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^[0-9]{4}$/.test(val))){
            showErr(this);
            return false;
        }
        if(!validateImgCode($(this))){
            showErr(this);
            return false;
        }

        showOk(this);
        return true;
    });

    $('#mobilePhoneValidateCode').blur(function(){
        var val = $(this).val();
        val = $.trim(val);
        $(this).val(val);
        if(!val){
            showErr(this);
            return false;
        }

        if(!(/^[0-9]{6}$/.test(val))){
            showErr(this);
            return false;
        }
        if(!validateMobileCode($(this))){
            showErr(this);
            return false;
        }
        showOk(this);
        return true;
    });

    $('#agree').click(function(){
        checkAgree();
    });

    // Update Status
    updateLoginStatus();
    updateCartStatus();
});

var validateImgCode = function(obj){
    var success = false;
    var validateCode = $(obj).val();
    $.ajax({
        type: "get",
        async:false,
        dataType: "json",
        url: _root_ + "/user/validateImgCode.do?rand="+ new Date().getTime()+Math.random(),
        data: "validateCode="+validateCode,
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                alert(msg.message);
                return;
            }
            success = true;
        }
    });
    return success;
}

var validateMobileCode = function(obj){
    var mobilePhone = $('#getPasswordMobilePhone').val();
    var validateCode = $(obj).val();
    var success = false;
    $.ajax({
        type: "get",
        async:false,
        dataType: "json",
        url: _root_ + "/user/validateMobileCode.do?rand="+ new Date().getTime()+Math.random(),
        data: "validateCode="+validateCode+"&user.mobilePhone="+mobilePhone,
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                alert(msg.message);
                return;
            }
            success = true;
        }
    });
    return success;
}

var checkMobilePhone = function(mobilePhone){
    var success = false;
    $.ajax({
        type: "get",
        async:false,
        dataType: "json",
        url: _root_ + "/user/checkMobilePhone.do?rand="+ new Date().getTime()+Math.random(),
        data: "user.mobilePhone="+mobilePhone,
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                $('#loginMobilePhoneError').html(msg.message);
                return;
            }
            $('#loginMobilePhoneError').html('');
            success = true;
        }
    });
    return success;
}

var getErrIcon = function(obj){
    var next = $(obj).next('.errIcon');
    if(next.length == 0){
        $(obj).after('<span class="errIcon" style="vertical-align: middle;width:16px;height: 16px;font-size: 16px">&nbsp;&nbsp;</span>');
        next = $(obj).next('.errIcon');
    }
    return next;
}

var showErr = function(obj){
    var next = getErrIcon(obj);
    next.addClass('inputErr');
    next.removeClass('inputOk');
}

var showOk = function(obj){
    var next = getErrIcon(obj);
    next.addClass('inputOk');
    next.removeClass('inputErr');
}

var isLogin = function(){
    var userName = false;
    $.ajax({
        type: "get",
        async:false,
        dataType: "json",
        url: _root_ + "/user/isLogin.do?rand="+ new Date().getTime()+Math.random(),
        data: "",
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                alert(msg.message);
                return;
            }
            userName = (msg.data.userName);
        }
    });
    return userName;
}
var toLogin = function(url){
    $('#loginTab').click();
    if(url){
        $('#_afterUrl').val(url);
    }
    accountDialog.Open();
}
var toCmf = function(){
    window.location = _root_+'/user/index.do';
}
var toRegister = function(){
    $('#registerTab').click();
    accountDialog.Open();
}
var login = function(form){
    if(!validateForm($('#loginForm'))){
        return false;
    }
    $.ajax({
        type: "post",
        dataType: "json",
        url: _root_ + "/user/login.do?rand="+ new Date().getTime()+Math.random(),
        data: $('#loginForm').serialize(),
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                $('#loginError').html(msg.message);
                return;
            }
            var afterUrl = $('#_afterUrl').val();
            resetForm(form);
            accountDialog.Close();
            if(afterUrl){
                window.location = afterUrl;
                return;
            }
            updateLoginStatus();
        }
    });
}
var checkAgree = function(){
    var noAgreeErr = '你还没有接受《快乐秘方用户注册协议》';
    var isAgreeChecked = $('#agree')[0].checked;
    if(!isAgreeChecked){
        $('#agreeErr').html(noAgreeErr);
        return false;
    }else{
        $('#agreeErr').html('');
        return true;
    }
}
var register = function(form){

    if(!checkAgree()){
        return false;
    }
    if(!validateForm($('#registerForm'))){
        return false;
    }
    $.ajax({
        type: "post",
        dataType: "json",
        url: +_root_ + "/user/register.do?rand="+ new Date().getTime()+Math.random(),
        data: $('#registerForm').serialize(),
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                $('#registerError').html(msg.message);
                return;
            }
            resetForm(form);
            accountDialog.Close();
            updateLoginStatus();
        }
    });
}
var logoff = function(){
    $.ajax({
        type: "post",
        dataType: "json",
        url: _root_ + "/user/logoff.do?rand="+ new Date().getTime()+Math.random(),
        data: "",
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                alert(msg.message);
                return;
            }
            $('#loginError').html("");
            accountDialog.Close();
            updateLoginStatus();
        }
    });
}

var updateLoginStatus = function(){
    var html = "";
    var userName = isLogin();
    if(!userName){
        html = "<span><a href=\"javascript:void(0);\" onclick=\"toLogin()\">[请登录]</a>&nbsp;"+
            "<a href=\"javascript:void(0);\" onclick=\"toRegister()\">[新用户注册]</a></span>";
        $('#loginStatusDiv').html(html);
    }else{
        html = "<span><a id=\"myCmf\" href=\"javascript:void(0);\"><s class=\"ui-icon icon-account\"></s>" +
            "<span onclick='toCmf()'>我的快乐秘方</span><s class=\"ui-icon icon-arrow\"></s></a>"+
            "%{userName} <a href=\"javascript:void(0);\" onclick=\"logoff()\">[退出]</a></span>".replace("%{userName}",userName);
        $('#loginStatusDiv').html(html);
        accountBubble();
    }
}

var validateForm = function(form){
    $(form).children().find('input').blur();
    return $(form).children().find('.inputErr').length == 0
}

var interval;
var count = 120;
var disableSendValidateBtn = function(){
    $('#validateBtn').attr('disabled',true);
    setTimeout(function(){
        if(--count < 0){
            setTimeout(interval);
            $('#validateBtnMsg').html("发送验证码到手机");
            $('#validateBtn').attr('disabled',false);
            count = 120;
            return;
        }
        $('#validateBtnMsg').html("等待("+count+")秒以后，才可以再次发送");
        setTimeout(arguments.callee,1000);
    },1000);
}

var sendValidateCode = function(){
    $('#getPasswordMobilePhone').blur();
    $('#getPasswordValidateCode').blur();

    var next1 = $('#getPasswordMobilePhone').next('.inputErr');
    var next2 = $('#getPasswordValidateCode').next('.inputErr');
    if(next1.length > 0 || next2.length > 0){
        return false;
    }

    disableSendValidateBtn();
    var mobilePhone = $('#getPasswordMobilePhone').val();
    var validateCode = $('#getPasswordValidateCode').val();// /user/validateMobileCode.do,/user/validateImgCode.do
    var success = false;
    $.ajax({
        type: "post",
        async:false,
        dataType: "json",
        url: _root_ + "/user/sendValidateCode.do?rand="+ new Date().getTime()+Math.random(),
        data: "user.mobilePhone="+mobilePhone+"&validateCode="+validateCode,
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                $('#mobilePhoneValidateCodeError').html(msg.message);
                return false;
            }
            success = true;
            $('#mobilePhoneValidateCodeError').html("验证码已经发送到您的手机");
            alert("验证码已经发送到您的手机");
        }
    });
    return success;
}

var changePassword = function(form){
    if(!validateForm($(form))){
        return false;
    }
    var success = false;
    $.ajax({
        type: "post",
        async:false,
        dataType: "json",
        url: _root_ + "/user/changePassword.do?rand="+ new Date().getTime()+Math.random(),
        data: $(form).serialize(),
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                $('#getPasswordError').html(msg.message);
                return;
            }
            $('#getPasswordError').html("密码已成功修改！");
            alert("密码已成功修改！");
            resetForm(form);
            success = true;
        }
    });
    return success;
}

var resetForm = function(form){
    $(form).children().find('input').val('');
    $(form).children().find('.errorMessage').html('');
    $(form).children().find('.errIcon').removeClass('inputErr inputOk');
}

//获得验证码
var getPassCode = function(imageId){
    document.getElementById(imageId).src = '' ;
    document.getElementById(imageId).src = _root_ + '/images/captcha.jpg?r='+new Date().getTime() ;
}

// ///////////////////////////////
var accountBubble = function() {
    ui.Popup($('#myCmf'), $('#myCmfList'), {
        clickToShow: true,
        activeClass: true,
        position: {
            my: 'center top+14',
            at: 'center bttom'
        }
    });
}

// ///////////////////////////////

var getCartCount = function(obj){
    var count = 0;
    $.ajax({
        type: "get",
        async:false,
        dataType: "json",
        url: _root_ + "/user/cart/getCartCount.do?rand="+ new Date().getTime()+Math.random(),
        data: "",
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                alert(msg.message);
                return;
            }
            count = msg.data;
        }
    });
    return count;
}

var updateCartStatus = function(){
    var html = "";
    var count = getCartCount();
    if(!count){
        html = " ";
    }else{
        html = "<a href=\"javascript:showCart();\"><s class=\"ui-icon icon-cart\"></s><span>购物车 (%{count})</span></a>".replace("%{count}",count);
    }
    $('#shopCartStatusDiv').html(html);
}

var showCart = function(){
    var url = _root_ +"/user/cart/showCart.do";
    if(!isLogin()){
        toLogin(url);
    }else{
        window.location = url;
    }
}

var addToCart = function(caseNo, num){
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/user/cart/addToCart.do",
        data: "mfCase.caseNo="+caseNo+"&mfCase.ticketNumber="+num,
        complete :function(){},
        success: function(msg) {
            if(msg.isError){
                alert(msg.message);
                return;
            }
            updateCartStatus();
        }
    });
}

var validateFormField = function(obj){
    var val = $(obj).val();
    var id = $(obj).attr('id');
    var name = $(obj).attr('name');
    var type = $(obj).attr('type');

    if(type == 'radio'){
        val = $('input [type="radio"][name="'+name+'"][checked="checked"]').val();
    }

    if(!val){
        val = "";
    }

    val = $.trim(val);
    if(type != 'radio') {
        $(obj).val(val);
    }
    if(!val && formFieldIsRequire(obj)){
        showErr(obj);
        return false;
    }

    if(!val){
        //showOk(obj);
        return true;
    }

    if(/^.*email$/ig.test(name) && !(/^([a-zA-Z0-9_\.\-])+@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(val))){
        showErr(obj);
        return false;
    }

    if(/^.*phone/ig.test(name) && !(/^[0-9]{11}$/.test(val))){
        showErr(obj);
        return false;
    }

    if(/^.*Num/ig.test(name) && !(/^[0-9]+$/.test(val))){
        showErr(obj);
        return false;
    }
    showOk(obj);
    return true;
}
var validateFormFields = function(form){
    $(form).children().find('input').blur();
    $(form).children().find('textarea').blur();
    return $(form).children().find('.inputErr').length == 0
}

var formFieldIsRequire = function(obj){
    var id = $(obj).attr('id');
    if(!id){
        return false;
    }
    return $('span[for="'+id+'"][class="ui-icon icon-required"]').length > 0;
}

/**
 *   Usage:  CurrencyFormatted(12345.678);
 *   result: 12345.68
 **/
function CurrencyFormatted(amount) {
    var i = parseFloat(amount);
    if(isNaN(i)) { i = 0.00; }
    var minus = '';
    if(i < 0) { minus = '-'; }
    i = Math.abs(i);
    i = parseInt((i + .005) * 100);
    i = i / 100;
    s = new String(i);
    if(s.indexOf('.') < 0) { s += '.00'; }
    if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
    s = minus + s;
    return s;
}

/**
 *   Usage:  CommaFormatted(12345678);
 *   result: 12,345,678
 **/
function CommaFormatted(amount) {
    var delimiter = ","; // replace comma if desired
    amount = new String(amount);
    var a = amount.split('.',2)
    var d = a[1];
    var i = parseInt(a[0]);
    if(isNaN(i)) { return ''; }
    var minus = '';
    if(i < 0) { minus = '-'; }
    i = Math.abs(i);
    var n = new String(i);
    var a = [];
    while(n.length > 3)
    {
        var nn = n.substr(n.length-3);
        a.unshift(nn);
        n = n.substr(0,n.length-3);
    }
    if(n.length > 0) { a.unshift(n); }
    n = a.join(delimiter);
    if(d.length < 1) { amount = n; }
    else { amount = n + '.' + d; }
    amount = minus + amount;
    return amount;
}






///////////////////////

//放大图片
function imgShow(outerdiv, innerdiv, bigimg, src, url){
    //var src = _this.attr("src");
    $(bigimg).attr("src", src);

    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();
        var windowH = $(window).height();
        var realWidth = this.width;
        var realHeight = this.height;
        var imgWidth, imgHeight;
        var scale = 0.8;

        if(realHeight>windowH*scale) {
            imgHeight = windowH*scale;
            imgWidth = imgHeight/realHeight*realWidth;
            if(imgWidth>windowW*scale) {
                imgWidth = windowW*scale;
            }
        } else if(realWidth>windowW*scale) {
            imgWidth = windowW*scale;
            imgHeight = imgWidth/realWidth*realHeight;
        } else {
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width",imgWidth);

        var w = (windowW-imgWidth)/2;
        var h = (windowH-imgHeight)/2;
        $(innerdiv).css({"top":h, "left":w});
        $(outerdiv).fadeIn("fast");
    });

    $(outerdiv).click(function(){
        $(this).fadeOut("fast");
        if(url){
            window.open(url);
        }
    });
}

/**
 * AD Float Object(飞出广告)
 *
 * @desc:Create a float ad with image: ad.jpg
 * @example:FloatAd.newFloatAd("image_path/ad.jpg");
 * @desc:Create a float ad with image and url: ad.jpg
 * @example:FloatAd.newFloatAd("image_path/ad.jpg", "http://blog.csdn.net/xxd851116/");
 *
 * @author: Xing,XiuDong :Last Editer
 * @date: 2009-09-17
 * @refers:	http://www.ahbofcom.gov.cn/js/FloatAd.js
 */
FloatAd = {
    "ads" : new Object,
    /**
     * @param imgUrl : image path
     * @param strLink : image link address
     * @param top : image top at starting..
     * @param left : image left at starting..
     */
    "newFloatAd" : function(imgUrl, strLink, top, left) {
        var ad = document.createElement("a");
        ad.DirV = true;
        ad.DirH = true;
        ad.AutoMove = true;
        ad.Image = new Image;
        ad.Seed = Math.random();
        ad.Timer = setInterval("FloatAd.float(" + ad.Seed + ")", 20);
        this.ads[ad.Seed] = ad;
        ad.Image.Parent = ad;
        ad.style.position = "absolute";
        ad.style.left = left || 0;
        ad.style.top = top || 0;
        ad.Image.src = imgUrl;
        ad.Image.onmouseover = function(){this.Parent.AutoMove = false;};
        ad.Image.onmouseout = function(){this.Parent.AutoMove = true;};
        if(strLink) {
            ad.href = strLink;
            ad.Image.border = 0;
            ad.target = "_blank";
        }
        ad.appendChild(ad.Image);
        document.body.appendChild(ad);
        return ad;
    },
    "float" : function(floatId){
        var ad = this.ads[floatId];
        if(ad.AutoMove){
            var curLeft = parseInt(ad.style.left);
            var curTop = parseInt(ad.style.top);
            if(ad.offsetWidth + curLeft > document.documentElement.clientWidth + document.documentElement.scrollLeft - 1){
                curLeft = document.documentElement.scrollLeft + document.documentElement.clientWidth - ad.offsetWidth;
                ad.DirH = false;
            }
            if(ad.offsetHeight + curTop > document.documentElement.clientHeight + document.documentElement.scrollTop - 1){
                curTop = document.documentElement.scrollTop + document.documentElement.clientHeight - ad.offsetHeight;
                ad.DirV = false;
            }
            if(curLeft < document.documentElement.scrollLeft) {
                curLeft = document.documentElement.scrollLeft;
                ad.DirH = true;
            }
            if(curTop < document.documentElement.scrollTop) {
                curTop = document.documentElement.scrollTop;
                ad.DirV = true;
            }
            ad.style.left = curLeft + (ad.DirH ? 1 : -1) + "px";
            ad.style.top = curTop + (ad.DirV ? 1 : -1) + "px";
        }
    }
};
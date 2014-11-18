<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="mfCase" value="${mf:invokeMethod1('mfCaseService','getActivedMfCaseById', param['caseNo'] )}" />
<c:if test="${empty mfCase.remainingTicket}">
    <jsp:setProperty name="mfCase" property="remainingTicket" value="${mfCase.ticketNumber}"></jsp:setProperty>
</c:if>
<c:if test="${mfCase.remainingTicket < 0}">
    <jsp:setProperty name="mfCase" property="remainingTicket" value="0"></jsp:setProperty>
</c:if>
<c:set var="maxNum" value="${mfCase.remainingTicket eq 0?mfCase.ticketNumber:mfCase.remainingTicket}"></c:set>
<c:set var="_title" value="活动详情《${mfCase.title}》" />
<%@include file ="inc/header.jsp" %>
<style>
    #imglist {list-style:none; width:500px; margin:50px auto;}
    #imglist li {float:left; margin-top:10px;}
</style>
<script type="text/javascript" src="${ROOT}/resources/scripts/preview.js"></script>
<script type="text/javascript">
    var ticketPrice = ${mfCase.ticketPrice};
    var caseNo = ${mfCase.caseNo};
    var addToBufferDialog = null;

    $(document).ready(function () {
        $('#qty').spinner({
            min: 1,
            max: ${maxNum},
            change: function (event, ui) {
                $('#qty').change();
            }
        });
        $('#mfTicketBuffer_qty').spinner({
            min: 1,
            max: ${maxNum}
        });
        $('.ui-spinner-button').click(function(){
            $('.input-qty').blur();
        });
        $('#qty').change(function(){
            var val = $(this).val();
            if(!val || !/^[0-9]+$/.test(val)){
                $(this).val('1');
                val = 1;
            }
            if(val < 1){
                $(this).val('1');
                val = 1;
            }
            if(val > ${maxNum}){
                $(this).val('${maxNum}');
                val = ${maxNum};
            }
            var total = val * ticketPrice;
            $('#totalPrice').text('￥'+CommaFormatted(CurrencyFormatted(total)));
        });
        $('.activity-tabs').tabs();

        // BShare
        $('#bshareBtn').mouseover(function(){mouseOverShare()});
        $('#bshareBtn').mouseout(function(){mouseOutShare()});

        // 放大图片
        $('#zoomBtn').click(function(){
            var imgSrc = $('#picImg').attr('src');
            imgShow("#outerdiv", "#innerdiv", "#bigimg", imgSrc);
        });

        // 收藏夹
        $('#addFavoriteBtn').click(function(){
            if(!isLogin()){
                toLogin();
                return;
            }
            addFavorite();
        });

        // 加入等待队列
        addToBufferDialog = ui.Dialog(null, $('#addToBufferDialog'), {
            width: 720,
            closeText: '关闭窗口',
            autoOpen: false,
            modal: true
        });
        $('#addToBufferTabs').tabs();

        // Add valication
        var children = $('#addToBufferForm').children();
        children.find('input').blur(function(){
            validateFormField(this);
        });
        children.find('textarea').blur(function(){
            validateFormField(this);
        });

        ui.init();
    });

    // BShare
    var onBsPanel = false;
    function mouseOverShare(){
        //$('#bsPanel').show();
        var btnOffset = $('#bshareBtn').offset();
        var panelOffset = $('#bsPanel').offset();
        var top = btnOffset.top + 20;
        var left = btnOffset.left;
        $('#bsPanel').attr('style','top:'+top+'px;left:'+left+'px;display:block');
    }
    function mouseOutShare(){
        $('#bsPanel').mouseover(function(){
            onBsPanel = true;
        });
        setTimeout('hideBsPanel()',500);
    }
    function hideBsPanel(){
        if(onBsPanel){
            onBsPanel = false;
            return;
        }
        $('#bsPanel').hide();
        onBsPanel = false;
    }


    //收藏夹
    function addFavorite(){
        $.ajax({
            type: "get",
            async:false,
            dataType: "json",
            url: _root_ + "/user/addFavorite.do?myFavorite.mfCase.caseNo="+caseNo+"&rand="+ new Date().getTime()+Math.random(),
            data: "",
            complete :function(){},
            success: function(msg) {
                if(msg.isError){
                    alert(msg.message);
                    return;
                }
                alert("已经成功收藏活动");
            }
        });
    }

    //加入队列
    var addToBuffer = function(caseNo, qty){
        $('#mfTicketBuffer_qty').val(qty);
        $('#mfTicketBuffer_caseNo').val(caseNo);
        if(isLogin()){
            _addToBuffer($('#addToBufferForm'),true);
            return;
        }
        getPassCode('mfTicketBuffer_code_img');
        addToBufferDialog.Open();
    }
    var _addToBuffer = function(form, isLogin){
        var form = $(form);
        if(!isLogin && !validateFormFields(form)){
            return false;
        }
        $.ajax({
            type: "get",
            async:false,
            dataType: "json",
            url: _root_ + "/user/addToBuffer.do?rand="+ new Date().getTime()+Math.random(),
            data: $(form).serialize(),
            complete :function(){},
            success: function(msg) {
                if(msg.isError){
                    alert(msg.message);
                    return;
                }
                alert("已经成功加入等候名单");
                addToBufferDialog.Close();
            }
        });
    }

</script>
<style>
    .l-left-detail img{
        max-width:220px;
        width:220px;
        width:expression(document.body.clientWidth>220?'220px':'auto');
        overflow:hidden;
    }
</style>
<!-- #include file ="inc/header.htm" -->
<div class="page-content">
    <div class="main-content">
        <h1>活动详情</h1>
        <form id="caseDetailForm" action="#" onsubmit="return false;">

            <div class="l-g l-left-detail">
            <aside class="l-u">
                <img id="picImg" src="${ROOT}${mfCase.picture}" alt="" />
                <div class="activity-function">
                    <a href="javascript:void(0);" id="zoomBtn"><s class="ui-icon icon-zoom"></s><span>放大图片</span></a>
                    <a href="javascript:void(0);" id="addFavoriteBtn"><s class="ui-icon icon-add-to-favorite"></s><span>收藏</span></a>
                    <a href="javascript:void(0);" onclick="bShare.more(event);" id="bshareBtn"><s class="ui-icon icon-share"></s><span>分享</span></a>
                    <span style="display: none"><a class="bshareDiv" id="bshareLink" href="http://www.bshare.cn/share">分享</a></span>
                    <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#uuid=&amp;style=10&amp;bgcolor=Orange&amp;ssc=false&amp;pophcol=1"></script>
                    <p class="BSHARE_TEXT" style="display: none">和我一起参加${mf:fmtMyDate(mfCase.startDate,"MM月dd日（E）")}在${mfCase.location}的快乐秘方派对吧！Let's have some fun!</p>
                </div>
            </aside>
            <div class="l-u">
                <div class="activity-title">
                    <span class="title">${mfCase.title}</span>
                    <c:if test="${mfCase.expirated}">
                        <span class="alert-tip">已经过期啦!</span>
                    </c:if>
                    <c:if test="${not mfCase.expirated and mfCase.remainingTicket le 0}">
                        <span class="alert-tip">票卖光啦!</span>
                    </c:if>
                </div>
                <p class="rank">
                    <a href="javascript:void(0);" class="icon-only rank-0" title="移除评分"><s class="ui-icon"></s><span>0星</span></a>
                    <c:forEach var="idx" begin="1" end="5">
                    <a href="javascript:void(0);" class="icon-only rank-${idx}">
                        <s class="ui-icon icon-rank${idx le mfCase.level?'-active':''}"></s><span>${idx}星</span></a>
                    </c:forEach>
                    <a href="javascript:void(0);">${mf:param('CASE_LEVEL',mfCase.level )}</a>
                    <a href="#" class="icon-only"><s class="ui-icon icon-help"></s><span>帮助</span></a>
                </p>
                <dl class="activity-dl">
                    <dt>时间：</dt>
                    <dd>
                        ${mf:fmtMyDate(mfCase.startDate,"MM月dd日（E）")}<%--7月4日（星期五）--%><br />
                        ${mfCase.timeRange}<%--7:00 － 9:00 p.m.--%></dd>
                    <dt>地点：</dt>
                    <dd>
                        ${mfCase.location}<br />
                        ${mfCase.address}
                    </dd>
                    <dt>票价：</dt>
                    <dd>${mf:fmtMoney(mfCase.ticketPrice)} / 位</dd>
                    <c:if test="${not mfCase.expirated}">
                    <dt>票数：</dt>
                    <dd>
                        <input type="text" id="qty" value="1" size="3" class="input-qty" style="vertical-align: top;" />
                        <a href="#">还剩${mfCase.remainingTicket}个座位！</a></dd>
                    </c:if>
                    <dt>总金额：</dt>
                    <dd>
                        <span class="price color-main" id="totalPrice">${mf:fmtMoney(mfCase.ticketPrice)}</span>
                    </dd>
                </dl>
                <div class="activity-add"><%--onclick="addToCart(${c.caseNo},1)--%>
                    <c:if test="${not mfCase.expirated and mfCase.remainingTicket le 0}">
                    <button type="button" id="addToBufferBtn" onclick="addToBuffer(${mfCase.caseNo},$('#qty').val())" class="button-highlight button-big"><span>加入等候名单</span></button>
                    <span class="tips">
                        选择所需票数后，点击加入等候名单；<br />
                        如有空位腾出，快乐秘方将通过短信或电话及时通知您！</span>
                    </c:if>
                    <c:if test="${not mfCase.expirated and mfCase.remainingTicket > 0}">
                        <button type="button" id="addToCartBtn" onclick="addToCart(${mfCase.caseNo},$('#qty').val());" class="button-highlight button-big"><span>加入购物车</span></button>
                    </c:if>
                </div>
                <div class="activity-tabs">
                    <ul>
                        <li><a href="#activity-intro">活动介绍</a></li>
                        <li><a href="#activity-team">秘方团队</a></li>
                        <li><a href="#activity-faq">常见问题</a></li>
                    </ul>
                    <div id="activity-intro">
                        ${mfCase.description}
                        <%--
                        <p>
                            <div>快乐秘方派对是什么样的活动？</div>
                            －
                        </p>
                        <p>
                            <div>活动票价包含了哪些方面？</div>
                            －
                        </p>
                        --%>
                    </div>
                    <div id="activity-team">

                    </div>
                    <div id="activity-faq">

                    </div>
                </div>
            </div>
        </div>
        </form>
    </div>
</div>

<div id="addToBufferDialog" style="display: none">
    <div id="addToBufferTabs">
        <ul>
            <li><a id="loginTab" href="#login-tab">加入等候名单</a></li>
        </ul>
        <div id="login-tab">
            <form id="addToBufferForm" action="#" onsubmit="_addToBuffer(this);return false;">
                <input type="hidden" id="mfTicketBuffer_caseNo" name="mfTicketBuffer.caseNo" />
                <table class="field-table">
                    <tr>
                        <td class="title"><span for="mfTicketBuffer_mobilePhone" class="ui-icon icon-required"></span>手机号</td>
                        <td><input type="text" size="25" id="mfTicketBuffer_mobilePhone" name="mfTicketBuffer.mobilePhone" placeholder="请输入您的手机号" />
                            <span class="tips">为了方便我们联系您，请填写你的手机号</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title"><span for="mfTicketBuffer_email" class="ui-icon icon-required"></span>电子邮件</td>
                        <td><input type="text" size="50" id="mfTicketBuffer_email" name="mfTicketBuffer.email" placeholder="请输入您的电子邮件" />
                            <span class="tips">请输入电子邮箱地址</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">订购数量</td>
                        <td><input type="text" id="mfTicketBuffer_qty" name="mfTicketBuffer.qty" value="1" size="3" class="input-qty" style="vertical-align: top;width: 30px" />
                        </td>
                    </tr>
                    <tr>
                        <td class="title"><span for="mfTicketBuffer_validateCode" class="ui-icon icon-required"></span>验证码</td>
                        <td>
                            <input name="validateCode" maxlength="10" id="mfTicketBuffer_validateCode" size="10" type="text"/>
                            <img title="please refresh" id="mfTicketBuffer_code_img" style="vertical-align: middle" src="${ROOT}/images/captcha.jpg?r=${mf:now().time}" onclick="getPassCode('mfTicketBuffer_code_img')" height="22" width="70"/>
                            <span class="tips">看不清楚？ <a href="javascript:void(0);" onclick="getPassCode('mfTicketBuffer_code_img')">换一张</a></span>
                    </tr>
                    <tr>
                        <td colspan="2"><span class="errorMessage" style="color: red;text-align: center" id="addToBufferError"></span></td>
                    </tr>
                </table>
                <div class="command-group">
                    <button type="submit" class="button-highlight button-big"><span>加入等候名单</span></button>
                </div>
            </form>
        </div>
        </div>
    </div>
<!-- #include file ="inc/footer.htm" -->
<%@include file ="inc/footer.jsp" %>
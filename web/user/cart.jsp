<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="购物车" />
<%@include file ="../inc/header.jsp" %>
<!-- #include file ="inc/header.htm" -->
<script type="text/javascript">
    $(document).ready(function () {
        initSpinner();

        $('#addCouponBtn').click(function(){
            if($.trim($('#coupon').val()) == ''){
                $('#coupon').val('');
                return;
            }
            $.ajax({
                type: "get",
                dataType: "json",
                url: "${ROOT}/user/coupon/add.do",///user/cart/getCart.do
                data: "coupon.couponNo=" + $('#coupon').val(),
                complete :function(){
                    $('#coupon').val("");
                },
                success: function(msg) {
                    if(msg.isError){
                        //alert(msg.message);
                        $('#couponErrMsg').show();
                        return;
                    }

                    $('#couponErrMsg').hide();
                    getCart();

                }
            });
        });

        $('#removeCouponBtn').click(function(){
            $.ajax({
                type: "get",
                dataType: "json",
                url: "${ROOT}/user/coupon/remove.do?rand="+ new Date().getTime()+Math.random(),
                complete :function(){},
                success: function(msg) {
                    if(msg.isError){
                        alert(msg.message);
                        return;
                    }
                    $('#couponErrMsg').hide();
                    $('#removeCouponSpan').hide();
                    getCart();
                }
            });
        });

        ui.init();
    });

    var initSpinner = function() {
        try{
            $('.input-qty').spinner( "destroy" );
        }catch (e){}
        $('.input-qty').spinner({
            min: 1,
            change: function (event, ui) {
                var it =  $(event.currentTarget);
                updateCartQty(it.attr('caseNo'), it.val());
            }
        });
        $('.ui-spinner-button').click(function(){
            $('.input-qty').blur();
        });
        $('.remove').unbind('click').removeAttr('onclick').click(function(){
            $.ajax({
                type: "get",
                dataType: "json",
                url: "${ROOT}/user/cart/removeFormCart.do?rand="+ new Date().getTime()+Math.random(),
                data: "mfCart.mfCase.caseNo=" + $(this).attr('caseNo'),
                complete :function(){},
                success: function(msg) {
                    if(msg.isError){
                        alert(msg.message);
                        return;
                    }
                    renderCartList(msg.data);
                }
            });
        });
    };
//removeCouponBtn
    var getCart = function(){
        $.ajax({
            type: "get",
            dataType: "json",
            url: "${ROOT}/user/cart/getCart.do?rand="+ new Date().getTime()+Math.random(),
            data: '',
            complete :function(){},
            success: function(msg) {
                if(msg.isError){
                    alert(msg.message);
                    return;
                }
                renderCartList(msg.data, true);
            }
        });
    }

    var updateCartQty = function(caseNo, qty){
        $.ajax({
            type: "get",
            dataType: "json",
            url: "${ROOT}/user/cart/updateQty.do?rand="+ new Date().getTime()+Math.random(),
            data: "mfCart.mfCase.caseNo=" + caseNo+"&mfCart.qty="+qty,
            complete :function(){},
            success: function(msg) {
                if(msg.isError){
                    alert(msg.message);
                    return;
                }
                renderCartList(msg.data);
            }
        });
    }

    var confirmOrder = function() {
        window.location='/user/cart/confirmOrder.do';
    }

    var renderCartList = function(data, isOnlyUpdatePrice){

        if(data.couponTotal == ('￥0.00')){
            data.couponTotal = "- "+data.couponTotal;
            $('#removeCouponSpan').hide();
        }else{
            $('#removeCouponSpan').show();
        }

        $("#totalPrice").html(data.balance);
        $("#couponTotalPrice").html(data.couponTotal);


        if(isOnlyUpdatePrice){
            return;
        }

        $("#cartLines").html("");

        $.each(data.list,function(i, val){
            var html = $("#cartLinesTemplate").html();
            html = html.replace('%{cart.mfCase.picture}', val.mfCase.picture);
            html = html.replace('%{cart.mfCase.title}', val.mfCase.title);
            html = html.replace('%{cart.mfCase.title}', val.mfCase.title);
            html = html.replace('%{cart.mfCase.startDate}', val.mfCase.startDateStr);
            html = html.replace('%{cart.mfCase.timeRange}', val.mfCase.timeRange);
            html = html.replace('%{cart.mfCase.location}', val.mfCase.location);
            html = html.replace('%{cart.mfCase.address}', val.mfCase.address);
            html = html.replace('%{cart.mfCase.ticketPrice}', val.mfCase.ticketPriceStr);
            html = html.replace('%{cart.mfCase.ticketPrice2}', val.mfCase.ticketPrice2Str);
            html = html.replace('%{cart.qty}', val.qty);
            html = html.replace('%{cart.mfCase.caseNo}', val.mfCase.caseNo);
            html = html.replace('%{cart.mfCase.caseNo}', val.mfCase.caseNo);
            html = html.replace('%{input-qty}', 'input-qty');
            html = html.replace('%{remove}', 'remove');
            $("#cartLines").append(html);
        });
        initSpinner();
    }
</script>

<div class="page-content">
    <div class="main-content">
        <div class="cart-header">
            <button type="button" class="button-highlight" onclick="confirmOrder();"><span>结算</span></button>
            <nav class="cart-steps">
                <a href="/index.jsp" class="active"><s>1</s><span>选择活动</span></a>
                <a href="#" class="active"><s>2</s><span>添加购物车</span></a>
                <a href="#"><s>3</s><span>确认订单</span></a>
                <a href="#"><s>4</s><span>支付订单</span></a>
            </nav>
        </div>
        <div style="visibility: hidden; height: 0px">
            <table>
                <tbody id="cartLinesTemplate">
                <tr>
                    <td>
                        <a href="#"><img src="${ROOT}%{cart.mfCase.picture}" alt="%{cart.mfCase.title}" class="small-pic" /></a>
                    </td>
                    <td>
                        <dl class="dl-tablication-3em">
                            <dt>主题：</dt>
                            <dd> %{cart.mfCase.title} </dd>
                            <dt>时间：</dt>
                            <dd>
                                %{cart.mfCase.startDate}<br />
                                %{cart.mfCase.timeRange}</dd>
                            <dt>场所：</dt>
                            <dd> %{cart.mfCase.location}</dd>
                            <dt>地址：</dt>
                            <dd>
                                %{cart.mfCase.address}
                            </dd>
                        </dl>
                    </td>
                    <td class="align-right"><span class="price">%{cart.mfCase.ticketPrice} </span></td>
                    <td class="align-center"><input type="text" value="%{cart.qty}" caseNo="%{cart.mfCase.caseNo}" size="2" class="%{input-qty}" /></td>
                    <td class="align-right"><strong class="price color-main">%{cart.mfCase.ticketPrice2}</strong></td>
                    <td class="align-center">
                        <p><a href="javascript:void(0);" caseNo="%{cart.mfCase.caseNo}" class="action-link %{remove}">删除</a></p>
                        <div><a href="javascript:void(0);" class="action-link">加入收藏夹</a></div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <table class="datagrid colored">
            <colgroup>
                <col />
                <col />
                <col />
                <col />
                <col />
                <col style="width: 100px;" />
            </colgroup>
            <thead>
                <tr class="title">
                    <td colspan="6"><h1>我的购物车</h1></td>
                </tr>
                <tr class="thead">
                    <td></td>
                    <td>活动信息</td>
                    <td>单价（元）</td>
                    <td>数量</td>
                    <td>金额</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody id="cartLines">
                <c:forEach varStatus="idx" var="cart" items="${mfCartList}">
                <tr>
                    <td>
                        <a href="#"><img src="${ROOT}${cart.mfCase.picture}" alt="${cart.mfCase.title}" class="small-pic" /></a>
                    </td>
                    <td>
                        <dl class="dl-tablication-3em">
                            <dt>主题：</dt>
                            <dd> ${cart.mfCase.title} </dd>
                            <dt>时间：</dt>
                            <dd>
                                ${mf:fmtMyDate(cart.mfCase.startDate,"MM月dd日（E）")}<%--7月4日（星期五）--%><br />
                                ${cart.mfCase.timeRange}<%--7:00 － 9:00 p.m.--%></dd>
                            <dt>场所：</dt>
                            <dd> ${cart.mfCase.location}</dd>
                            <dt>地址：</dt>
                            <dd>
                                ${cart.mfCase.address}
                            </dd>
                        </dl>
                    </td>
                    <td class="align-right"><span class="price">${mf:fmtMoney(cart.mfCase.ticketPrice)} </span></td>
                    <td class="align-center"><input type="text" value="${cart.qty}" caseNo="${cart.mfCase.caseNo}" size="2" class="input-qty" /></td>
                    <td class="align-right"><strong class="price color-main">${mf:fmtMoney(cart.mfCase.ticketPrice2)}</strong></td>
                    <td class="align-center">
                        <p><a href="javascript:void(0);" caseNo="${cart.mfCase.caseNo}" class="action-link remove">删除</a></p>
                        <div><a href="javascript:void(0);" class="action-link">加入收藏夹</a></div>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="cart-coupon">
            <strong class="price color-main" id="couponTotalPrice">- ${mf:fmtMoney(couponPrice)}</strong>
            <label for="coupon">优惠券代码：</label>
            <input id="coupon" type="text" class="big" />
            <button type="button" id="addCouponBtn"><span>确认</span></button>
            <span class="info-msg" id="couponErrMsg" style="display: none">
                <s class="ui-icon icon-info"></s>
                <span>
                    代码输入无效<br />
                    若有问题，请<a href="#">联系客服</a>
                </span>
            </span>
            <span id="removeCouponSpan" style="${couponPrice gt 0?'':'display: none'}">
            <a href="javascript:void(0);" id="removeCouponBtn" caseNo="%{cart.mfCase.caseNo}" class="action-link">删除</a>
            </span>
        </div>
        <div class="cart-summary">
            应付总金额：
            <strong id="totalPrice" class="price color-main">${mf:fmtMoney(balance)}</strong>
            <button type="button" class="button-highlight" onclick="confirmOrder();"><span>结算</span></button>
        </div>
        <div class="cart-go-on">
            <a href="${ROOT}">继续订位</a>
        </div>
    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<%@include file ="../inc/footer.jsp" %>
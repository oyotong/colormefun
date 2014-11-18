<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="确认订单" />
<%@include file ="../inc/header.jsp" %>
<!-- #include file ="inc/header.htm" -->
<div class="page-content">
    <div class="main-content">
        <div class="cart-header">
            <button type="button" class="button-highlight"><span>支付订单</span></button>
            <nav class="cart-steps">
                <a href="/index.jsp" class="active"><s>1</s><span>选择活动</span></a>
                <a href="/user/cart/showCart.do" class="active"><s>2</s><span>添加购物车</span></a>
                <a href="#" class="active"><s>3</s><span>确认订单</span></a>
                <a href="#"><s>4</s><span>支付订单</span></a>
            </nav>
        </div>
        <table class="datagrid">
            <tbody>
                <tr class="title">
                    <td colspan="5"><h1>确认订单信息</h1></td>
                </tr>
                <tr class="thead">
                    <td></td>
                    <td>活动信息</td>
                    <td>单价（元）</td>
                    <td>数量</td>
                    <td>金额</td>
                </tr>
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
                    </tr>
                </c:forEach>
                <%--
                <tr>
                    <td>
                        <a href="#"><img src="/resources/images/pic/pic-01-big.jpg" alt="" class="small-pic" /></a>
                    </td>
                    <td>
                        <dl class="dl-tablication-3em">
                            <dt>主题：</dt>
                            <dd> 鲤鱼池 （Koi Pond）</dd>
                            <dt>时间：</dt>
                            <dd>
                                7月4日（星期五）<br />
                                7:00 － 9:00 p.m.</dd>
                            <dt>场所：</dt>
                            <dd>Zoo Coffee 咖啡厅 (金牛万达店)</dd>
                            <dt>地址：</dt>
                            <dd>
                                金牛区一环路北三段1号金牛<br />
                                万达百货2楼
                            </dd>
                        </dl>
                    </td>
                    <td class="align-right"><span class="price">¥199.00</span></td>
                    <td class="align-center">1</td>
                    <td class="align-right"><span class="price">¥199.00</span></td>
                </tr>
                --%>
            </tbody>
        </table>
        <div class="order-summary">
            <dl>
                <dt>总金额：</dt>
                <dd><span class="price">${mf:fmtMoney(totalPrice)}</span></dd>
                <dt>优惠金额：</dt>
                <dd><span class="price">- ${mf:fmtMoney(couponPrice)}</span></dd>
                <dt>应付总金额：</dt>
                <dd><span class="price color-main">${mf:fmtMoney(balance)}</span></dd>
            </dl>
            <div class="command-group">
                <button class="button-highlight" type="button"><span>支付订单</span></button>
            </div>
        </div>
    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<%@include file ="../inc/footer.jsp" %>
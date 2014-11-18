<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="我的订单"/>
<%@include file="../inc/header.jsp" %>
<!-- #include file ="inc/header.htm" -->
<script type="application/javascript">

    var removeOrderConfirmDialog = null;

    $(function () {
        removeOrderConfirmDialog = ui.Dialog(null, $('#DeleteConfirmDialog'), {
            closeText: '关闭窗口',
            minHeight: 0,
            dialogClass: 'confirm-dialog',
            modal: true
        });
    });

    function removeOrder() {
        var orderNo = $('#removeOrderNo').val();
        $.ajax({
            type: "get",
            dataType: "json",
            url: "${ROOT}/user/order/removeOrder.do",
            data: "order.orderNo=" + orderNo,
            complete: function () {
                closeConfirmDialog();
            },
            success: function (msg) {
                if (msg.isError) {
                    alert(msg.message);
                    return;
                }
                window.location = '${ROOT}/user/order/myOrder.do';
            }
        });
    }

    function toPay(orderNo) {
        $.ajax({
            type: "get",
            dataType: "json",
            url: "${ROOT}/user/order/payOrder.do",
            data: "order.orderNo=" + orderNo,
            complete: function () {
            },
            success: function (msg) {
                if (msg.isError) {
                    alert(msg.message);
                    return;
                }
                alert('已成功支付订单【' + orderNo + '】。');
                window.location = '${ROOT}/user/order/myOrder.do';
            }
        });
    }

    function openConfirmDialog(orderNo) {
        $('#removeOrderNo').val(orderNo);
        removeOrderConfirmDialog.Open();
    }
    function closeConfirmDialog() {
        $('#removeOrderNo').val('');
        removeOrderConfirmDialog.Close();
    }
</script>
<div class="page-content">
    <div class="main-content">
        <c:if test="${empty list}">
            <table class="datagrid colored">
                <colgroup>
                    <col style="width: 410px;"/>
                    <col style="width: 120px;"/>
                    <col style="width: 60px;"/>
                    <col style="width: 120px;"/>
                    <col style="width: 130px;"/>
                </colgroup>
                <tbody>
                <tr class="title">
                    <td colspan="5"><h1>我的订单</h1></td>
                </tr>
                <tr>
                    <td>活动信息</td>
                    <td class="align-right">单价（元）</td>
                    <td class="align-center">数量</td>
                    <td class="align-right">小计（元）</td>
                    <td class="align-center">交易操作</td>
                </tr>
                <tr>
                    <td colspan="5" class="align-center">没有您的订单记录</td>
                </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty list}">
            <c:forEach var="order" items="${list}" varStatus="idx">
                <c:if test="${(order.status eq 'paid') or (order.status eq 'created') or (order.status eq 'closed')}">
                    <table class="datagrid colored">
                        <colgroup>
                            <col style="width: 130px;"/>
                            <col style="width: 280px;"/>
                            <col style="width: 120px;"/>
                            <col style="width: 60px;"/>
                            <col style="width: 120px;"/>
                            <col style="width: 130px;"/>
                        </colgroup>
                        <tbody>
                        <c:if test="${idx.index eq 0}">
                            <tr class="title">
                                <td colspan="6"><h1>我的订单</h1></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>活动信息</td>
                                <td class="align-right">单价（元）</td>
                                <td class="align-center">数量</td>
                                <td class="align-right">小计（元）</td>
                                <td class="align-center">交易操作</td>
                            </tr>
                        </c:if>
                        <tr class="thead">
                            <td>${mf:fmtDate(order.createdDate) }</td>
                            <td colspan="5" class="align-left">订单号：#${order.orderNo }</td>
                        </tr>
                        <c:forEach var="detail" items="${order.details}" varStatus="didx">
                            <tr>
                                <td>
                                    <a href="#"><img src="${ROOT}${detail.mfCase.picture}" alt=""
                                                     class="small-pic"/></a>
                                </td>
                                <td>
                                    <dl class="dl-tablication-3em">
                                        <dt>主题：</dt>
                                        <dd> ${detail.mfCase.title} </dd>
                                        <dt>时间：</dt>
                                        <dd>
                                                ${mf:fmtMyDate(detail.mfCase.startDate,"MM月dd日（E）")}<%--7月4日（星期五）--%>
                                            <br/>
                                                ${detail.mfCase.timeRange}<%--7:00 － 9:00 p.m.--%> </dd>
                                        <dt>场所：</dt>
                                        <dd>${detail.mfCase.location}</dd>
                                        <dt>地址：</dt>
                                        <dd>
                                                ${detail.mfCase.address}
                                        </dd>
                                    </dl>
                                </td>
                                <td class="align-right"><span class="price">${mf:fmtMoney(detail.price)} </span></td>
                                <td class="align-center">${detail.qty}</td>
                                <td class="align-right"><span
                                        class="price">${mf:fmtMoney(detail.price * detail.qty)} </span></td>
                                <c:if test="${didx.index eq 0}">
                                    <td class="align-center border-left"
                                        rowspan="${(order.couponSummary ne 0?2:0)+fn:length(order.details)}">
                                        <c:if test="${order.status eq 'created'}">
                                            <p>
                                                <button type="button" class="button-highlight"
                                                        onclick="toPay('${order.orderNo}')"><span>立即付款</span></button>
                                            </p>
                                        </c:if>
                                        <c:if test="${order.status eq 'paid'}">
                                            <p><a href="javascript:viod(0);" class="action-link cart-delete">付款成功</a>
                                            </p>
                                        </c:if>
                                        <c:if test="${order.status eq 'closed'}">
                                            <p><a href="javascript:viod(0);" class="action-link cart-delete">交易成功</a>
                                            </p>
                                        </c:if>
                                            <%--<p><a href="javascript:viod(0);" class="action-link cart-delete">订单详情</a></p>--%>
                                        <c:if test="${order.status eq 'created'}">
                                            <div><a href="javascript:viod(0);"
                                                    onclick="openConfirmDialog('${order.orderNo}')"
                                                    class="action-link cart-delete">删除</a></div>
                                        </c:if>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <c:if test="${order.couponSummary ne 0}">
                            <tr class="tfoot">
                                <td colspan="5" class="align-right">使用优惠券：${mf:fmtMoney(0 - order.couponSummary) }</td>
                            </tr>
                            <tr class="tfoot">
                                <td colspan="5" class="align-right">
                                    实付款：${mf:fmtMoney(order.summary - order.couponSummary) }</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </c:if>
            </c:forEach>
        </c:if>

    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<div id="DeleteConfirmDialog">
    <input type="hidden" id="removeOrderNo" />
    <div class="align-center">
        <p><span class="ui-icon icon-confirm-info-msg"></span></p>
        <p>是否确认删除此项？</p>
    </div>
    <div class="command-group">
        <button type="button" class="dialog-ok button-highlight" onclick="removeOrder();"><span>删除</span></button>
        <button type="button" class="dialog-cancel button-lesser" onclick="closeConfirmDialog();"><span>不删除</span>
        </button>
    </div>
</div>
<%@include file="../inc/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="我的快乐秘方" />
<%@include file ="../inc/header.jsp" %>
<!-- #include file ="inc/header.htm" -->
<div class="page-content">
    <div class="main-content">
        <h1>我的快乐秘方</h1>
        <div class="l-g l-left-my">
            <aside class="l-u">
                <section class="box my-profile">
                    <h2 class="title">我的信息</h2>
                    <div class="content">
                        <p class="my-portrait">
                            <c:if test="${empty user.pic1}">
                            <img src="${ROOT}/resources/images/pic/portrait.jpg" alt="" />
                            </c:if>
                            <c:if test="${not empty user.pic1}">
                                <img src="${ROOT}${user.pic1}" alt="" />
                            </c:if>
                            <a href="${ROOT}}/user/myInfo.do"><s class="ui-icon icon-setting"></s><span>更改</span></a>
                        </p>
                        <p>姓名：${user.userName}</p>
                        <p>用户名：${user.mobilePhone}</p>
                        <p>密码：<a href="${ROOT}}/user/myInfo.do">修改密码</a></p>
                        <p>电子邮箱: ${user.email}</p>
                        <p>生日：${empty user.birthday?'<a href="${ROOT}}/user/myInfo.do">填写</a>':mf:fmtDate(user.birthday)}</p>
                        <p>性别：${empty user.sex?'<a href="${ROOT}}/user/myInfo.do">填写</a>':mf:dict('SEX',user.sex)}</p>
                        <p>兴趣爱好：${empty user.favorite?'<a href="${ROOT}}/user/myInfo.do">填写</a>':'<span title="'+user.favorite+'">'+mf:substring(user.favorite,20)+'</span>'}</p>
                        <p>喜欢的颜色：${empty user.myColor?'<a href="${ROOT}}/user/myInfo.do">填写</a>':'<span title="'+user.myColor+'">'+mf:substring(user.myColor,20)+'</span>'}</p>
                    </div>
                </section>
            </aside>
            <div class="l-u">
                <section class="box my-favorite">
                    <h2 class="title">我的收藏 [<a href="${ROOT}}/user/myFavorites.do">更多</a>]</h2>
                    <ul class="content">
                        <c:if test="${empty myFavorites}">
                            <span>没有您的收藏记录</span>
                        </c:if>
                        <c:if test="${not empty myFavorites}">
                        <c:forEach items="${myFavorites}" varStatus="idx" var="myfav">
                        <c:if test="${idx lt 3}">
                        <li>
                            <a href="javascript:void(0);" class="delete icon-only"><s class="ui-icon icon-delete-favorite"></s><span>删除</span></a>
                            <img src="${ROOT}${myfav.myCase.picture}" alt="${myfav.myCase.title}" />
                            <div>${myfav.title}</div>
                            <div> ${mf:fmtMyDate(myfav.mfCase.startDate,"MM月dd日（E）")}<%--7月4日（星期五）--%> <br />
                                    ${myfav.mfCase.timeRange}<%--7:00 － 9:00 p.m.--%>  </div>
                            <div>${myfav.mfCase.location}</div>
                        </li>
                        </c:if>
                        </c:forEach>
                        </c:if>
                    </ul>
                </section>
                <section class="box my-order">
                    <h2 class="title">订单记录 [<a href="${ROOT}}/user/myOrder.do">更多</a>]</h2>
                    <ul>
                        <c:if test="${empty myOrders}">
                            <li>没有您的订单记录</li>
                        </c:if>
                        <c:if test="${not empty myOrders}">
                        <c:forEach var="order" items="${myOrders}">
                            <c:if test="${(order.status eq 'paid') or (order.status eq 'created') or (order.status eq 'closed')}">
                                <c:forEach var="detail" items="${order.details}">
                        <li>
                            <table>
                                <tr>
                                    <td>
                                        <a href="#"><img src="${ROOT}${detail.mfCase.picture}" alt="" class="small-pic" /></a>
                                    </td>
                                    <td>
                                        <dl class="dl-tablication-3em">
                                            <dt>主题：</dt>
                                            <dd> ${detail.mfCase.title} </dd>
                                            <dt>时间：</dt>
                                            <dd>
                                                    ${mf:fmtMyDate(detail.mfCase.startDate,"MM月dd日（E）")}<%--7月4日（星期五）--%> <br />
                                                    ${detail.mfCase.timeRange}<%--7:00 － 9:00 p.m.--%> </dd>
                                            <dt>场所：</dt>
                                            <dd>${detail.mfCase.location}</dd>
                                            <dt>地址：</dt>
                                            <dd>
                                                    ${detail.mfCase.address}
                                            </dd>
                                        </dl>
                                    </td>
                                    <td class="align-right">${mf:fmtMoney(detail.price)}</td>
                                    <td class="align-center">${detail.qty}</td>
                                    <td class="align-right">${mf:fmtMoney(detail.price * detail.qty)}</td>
                                    <td class="align-center">
                                        <c:if test="${order.status eq 'created'}">
                                            <p><a class="action-link" href="javascript:viod(0);" onclick="toPay('${order.orderNo}')"><span>立即付款</span></a></p>
                                        </c:if>
                                        <c:if test="${order.status eq 'paid'}">
                                            <p><a href="javascript:viod(0);" class="action-link">付款成功</a></p>
                                        </c:if>
                                        <c:if test="${order.status eq 'closed'}">
                                            <p><a href="javascript:viod(0);" class="action-link">交易成功</a></p>
                                        </c:if>
                                            <%--<p><a href="javascript:viod(0);" class="action-link cart-delete">订单详情</a></p>--%>
                                        <c:if test="${order.status eq 'created'}">
                                            <div><a href="javascript:viod(0);" onclick="openConfirmDialog('${order.orderNo}')" class="action-link">删除</a></div>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </li>
                                </c:forEach>
                            </c:if>
                            </c:forEach>
                        </c:if>
                    </ul>
                </section>
            </div>
        </div>
    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<%@include file ="../inc/footer.jsp" %>
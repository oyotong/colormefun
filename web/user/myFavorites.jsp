<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="我的收藏夹"/>
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

    function removeFavorite() {
        var favId = $('#removeFavoriteId').val();
        $.ajax({
            type: "get",
            dataType: "json",
            url: "${ROOT}/user/removeFavorite.do",
            data: "cids=" + favId,
            complete: function () {
                closeConfirmDialog();
            },
            success: function (msg) {
                if (msg.isError) {
                    alert(msg.message);
                    return;
                }
                window.location = '${ROOT}/user/myFavorites.do';
            }
        });
    }

    function openConfirmDialog(favId) {
        $('#removeFavoriteId').val(favId);
        removeOrderConfirmDialog.Open();
    }
    function closeConfirmDialog() {
        $('#removeFavoriteId').val('');
        removeOrderConfirmDialog.Close();
    }
</script>
<div class="page-content">
    <div class="main-content">
        <c:if test="${empty myFavorites}">
            <table class="datagrid colored">
                <colgroup>
                    <col style="width: 130px;"/>
                    <col style="width: 460px;"/>
                    <col style="width: 120px;"/>
                    <col style="width: 130px;"/>
                </colgroup>
                <tbody>
                <tr class="title">
                    <td colspan="4"><h1>我的收藏夹</h1></td>
                </tr>
                <tr>
                    <td>收藏时间</td>
                    <td>活动信息</td>
                    <td>活动单价</td>
                    <td class="align-center">操作</td>
                </tr>
                <tr>
                    <td colspan="4" class="align-center">没有您的收藏记录</td>
                </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty myFavorites}">
            <c:forEach var="fav" items="${myFavorites}" varStatus="idx">
                    <table class="datagrid colored">
                        <colgroup>
                            <col style="width: 130px;"/>
                            <col style="width: 460px;"/>
                            <col style="width: 120px;"/>
                            <col style="width: 130px;"/>
                        </colgroup>
                        <tbody>
                        <c:if test="${idx.index eq 0}">
                            <tr class="title">
                                <td colspan="4"><h1>我的收藏夹</h1></td>
                            </tr>
                            <tr>
                                <td>收藏时间</td>
                                <td>活动信息</td>
                                <td>活动单价</td>
                                <td class="align-center">操作</td>
                            </tr>
                        </c:if>
                        <tr class="thead">
                            <td>${mf:fmtDate(fav.createdDate) }</td>
                            <td colspan="3" class="align-left">收藏编号：#${fav.favoriteId }</td>
                        </tr>
                            <tr>
                                <td>
                                    <a href="#"><img src="${ROOT}${fav.mfCase.picture}" alt=""
                                                     class="small-pic"/></a>
                                </td>
                                <td>
                                    <dl class="dl-tablication-3em">
                                        <dt>主题：</dt>
                                        <dd> ${fav.mfCase.title} </dd>
                                        <dt>时间：</dt>
                                        <dd>
                                                ${mf:fmtMyDate(fav.mfCase.startDate,"MM月dd日（E）")}  ${fav.mfCase.timeRange}</dd>
                                        <dt>场所：</dt>
                                        <dd>${fav.mfCase.location}</dd>
                                        <dt>地址：</dt>
                                        <dd>
                                                ${fav.mfCase.address}
                                        </dd>
                                    </dl>
                                </td>
                                <td class="align-right"><span class="price">${mf:fmtMoney(fav.mfCase.ticketPrice)} </span></td>
                                <td class="align-center border-left">
                                        <div><a href="javascript:viod(0);"
                                                onclick="openConfirmDialog('${fav.favoriteId}')"
                                                class="action-link cart-delete">删除</a></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
            </c:forEach>
        </c:if>

    </div>
</div>
<!-- #include file ="inc/footer.htm" -->
<div id="DeleteConfirmDialog">
    <input type="hidden" id="removeFavoriteId" />
    <div class="align-center">
        <p><span class="ui-icon icon-confirm-info-msg"></span></p>
        <p>是否确认删除此项？</p>
    </div>
    <div class="command-group">
        <button type="button" class="dialog-ok button-highlight" onclick="removeFavorite();"><span>删除</span></button>
        <button type="button" class="dialog-cancel button-lesser" onclick="closeConfirmDialog();"><span>不删除</span>
        </button>
    </div>
</div>
<%@include file="../inc/footer.jsp" %>
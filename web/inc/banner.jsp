<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $('.page-banner .banner-nav a').click(function() {
            if (!$(this).is('.active')) {
                $('.page-banner ul a:visible').fadeOut();
                $('.page-banner ul a:eq(' + $(this).index() + ')').fadeIn();
                $('.page-banner .banner-nav a.active').removeClass('active');
                $(this).addClass('active');
            }
        });

        //Auto play banner
        var isMouseOverBanner = false,
                autoPlayBannerTimer = setInterval(function () {
                    if (!isMouseOverBanner) {
                        if ($('.page-banner .banner-nav a.active').index() < $('.page-banner .banner-nav a').length - 1) {
                            $('.page-banner .banner-nav a.active').next().click();
                        }
                        else {
                            $('.page-banner .banner-nav a:eq(0)').click();
                        }
                    }
                }, 5000);
        $('.page-banner').hover(function () {
                    isMouseOverBanner = true;
                },
                function () {
                    isMouseOverBanner = false;
                });

        ui.init();
    });
</script>
<c:set var="marqueeList" value="${mf:invokeMethod1('mfContentService','findTopNMarqueeOrderBySeqNo',mf:int2obj(5)) }"> </c:set>
<div class="page-banner">
    <ul>
        <c:forEach var="b" items="${marqueeList}" varStatus="idx">
        <li><a href="${b.subTitle}"${idx.index eq 0?' style="display: block;"':''}><img src="${ROOT}${b.pic1}" alt="" /></a></li>
        </c:forEach>
    </ul>
    <div class="banner-nav">
        <c:forEach var="b" items="${marqueeList}" varStatus="idx">
        <a href="javascript:void(0);"${idx.index eq 0?' class="active"':''}>${b.content}</a>
        </c:forEach>
    </div>
</div>
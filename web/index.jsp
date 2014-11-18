<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<c:set var="_title" value="活动日历" />
<%@include file ="inc/header.jsp" %>
<c:set var="list" value="${mf:invokeMethod1('mfCaseService','findCaseByCurrentMonthByStatus',param.status) }"> </c:set>
<c:set var="previousMonth" value="${mf:invokeMethod1('mfCaseService','getPreviousMonthByStatus',param.status) }"> </c:set>
<c:set var="nextMonth" value="${mf:invokeMethod1('mfCaseService','getNextMonthByStatus',param.status) }"> </c:set>
<c:set var="currentDate" value="${mf:invokeMethod1('mfCaseService','getCurrentDateByStatus',param.status) }"> </c:set>

<c:set var="firstDay" value="${mf:invokeMethod1('mfCaseService','getFirstDayByStatus',param.status) }"> </c:set>
<c:set var="firstDate" value="${mf:invokeMethod1('mfCaseService','getFirstDateByStatus',param.status) }"> </c:set>
<c:set var="maxDayOfMonth" value="${mf:invokeMethod1('mfCaseService','getMaxDayOfMonthByStatus',param.status) }"> </c:set>
<c:set var="derta" value="${firstDay - firstDay * 2 + 2 }"> </c:set>

<div class="page-content">
    <%@include file="inc/banner.jsp"%>
    <div class="main-content">
        <h1>活动日历<span class="sub"> － 请点击图片以了解更多活动信息</span></h1>
        <table class="action-calendar">
            <thead>
                <tr class="calendar-title">
                    <th colspan="7">
                        <span class="tips">活动日历将以每月20号公布</span>
                        <c:if test="${not empty previousMonth}">
                        <a href="/index.jsp${empty param.status?'?status=previous':''}" class="icon-only" title="${previousMonth}月"><s class="ui-icon icon-calendar-prev"></s><span>${previousMonth}月</span></a>
                        </c:if>
                        <span>${mf:fmtMyDate(currentDate,"yyyy年M月")}</span>
                        <c:if test="${not empty nextMonth}">
                        <a href="/index.jsp${empty param.status?'?status=next':''}" class="icon-only" title="${nextMonth}月"><s class="ui-icon icon-calendar-next"></s><span>${nextMonth}月</span></a>
                        </c:if>
                    </th>
                </tr>
                <tr class="week-title">
                    <th>星期一</th>
                    <th>星期二</th>
                    <th>星期三</th>
                    <th>星期四</th>
                    <th>星期五</th>
                    <th>星期六</th>
                    <th>星期日</th>
                </tr>
            </thead>
            <tbody>
            <c:set var="days" value="${maxDayOfMonth - derta}"></c:set>
            <c:forEach var="i" begin="1" end="${days mod 7 eq 0?days:days + (7 - days mod 7)}" step="1">
                <c:set var="date" value="${mf:invokeMethod2('mfCaseService','addDate', firstDate,mf:int2obj(derta)) }"> </c:set>
                <c:set var="derta" value="${derta + 1 }"> </c:set>
                <c:if test="${i mod 7 eq 1}"><tr></c:if>
                    <c:set var="isCurrentMonth" value="${mf:fmtMyDate(date.time,'M') eq mf:fmtMyDate(currentDate,'M') }" />
                    <td><span class="day${isCurrentMonth?'':' no-current-month' }">${mf:fmtMyDate(date.time,"d")}</span>
                        <c:if test="${isCurrentMonth}">
                        <c:forEach var="c" items="${list}">
                        <c:if test="${mf:fmtMyDate(date.time,'MM-dd') eq mf:fmtMyDate(c.startDate,'MM-dd')}">
                        <a href="${ROOT}/detail.jsp?caseNo=${c.caseNo}" target="_blank">
                            <img src="${ROOT}${c.picture}" alt="${c.title}" />
                            <div>${c.title}</div>
                            <div>${mf:substring(c.location,20)}</div>
                            <div>${mf:substring(c.address,20)}</div>
                            <div>${c.timeRange}</div>
                        </a>
                        </c:if>
                        </c:forEach>
                        </c:if>
                    </td>
                <c:if test="${i mod 7 eq 0}"></tr></c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file ="inc/footer.jsp" %>

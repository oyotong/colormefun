<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<m:dictlist name="${param.name }" showAllText="${param.allText }" showAllValue="${param.allValue }"
    	value="${param.value }" dict="${param.dict }" 
    	id="${param.id }"></m:dictlist>
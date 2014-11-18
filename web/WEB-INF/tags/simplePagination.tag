<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false" body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="pagination" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="url" type="java.lang.Object" required="false"
	rtexprvalue="true"%>
<%@attribute name="jsfunction" type="java.lang.String" required="false"
	rtexprvalue="true"%>
<!-- Pagination -->
<c:if test="${empty pagination}">
	<c:set var="pagination" value="${requestScope.pagination}" />
</c:if>
<%--<c:if test="${pagination.totalPageCount > 1 }"> --%>
<script type="text/javascript">
<c:if test="${not empty jsfunction}">
function _go(page){
	var rowCount = document.getElementById('_rowCount').value;
	if(page){
		document.getElementById("_page").value = page;
	}
	var temp = "${jsfunction}";
	var fun = "";
	if(temp.indexOf("()") != -1){
		fun = temp.replace(/\)/, 'page,rowCount)');
	}else{
		fun = "${jsfunction}".replace(/\)/, ',page,rowCount)');
	}
	eval(fun);
}
</c:if>
<c:if test="${empty jsfunction}">
function _go(page){
	var _url = "${url}";
	if(!_url){
		_url = window.location.href;
	}
	if(page){
		document.getElementById("_page").value = page;
	}
	var _form = document.forms[0];
	_form.action = _url;
	_form.submit();
}
</c:if>
</script>
共有<span> ${pagination.totalRowCount } </span>条 |
共有<span> ${pagination.totalPageCount } </span>页 |
当前是第<span > ${pagination.page } </span>页 | 
<c:if test="${pagination.page eq 1}">
<span>首　页</span>
</c:if>
<c:if test="${pagination.page ne 1}">
<a href="javascript:_go(1);">
首　页
</a>
</c:if> | 
<c:if test="${pagination.page eq 1}">
<span>
上一页
</span>
</c:if>
<c:if test="${pagination.page ne 1}">
<a href="javascript:_go(${pagination.page - 1  });">
上一页
</a>
</c:if> | 
<c:if test="${pagination.page ne pagination.totalPageCount and pagination.totalPageCount ne 0}">
<a href="javascript:_go(${pagination.page + 1  });" title="下一页">
下一页
</a>
</c:if>
<c:if test="${pagination.page eq pagination.totalPageCount or pagination.totalPageCount eq 0 }" >
<span>
下一页
</span>
</c:if> | 
<c:if test="${pagination.page ne pagination.totalPageCount and pagination.totalPageCount ne 0}">
<a href="javascript:_go(${pagination.totalPageCount });" title="末页">
末　页
</a>
</c:if>
<c:if test="${pagination.page eq pagination.totalPageCount or pagination.totalPageCount eq 0 }">
<span>
末　页
</span>
</c:if>
<%--</c:if> --%>

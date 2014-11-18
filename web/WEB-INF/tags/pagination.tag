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

<del class="container">
<div class="pagination">
<div class="limit">
每页显示数#
<select name="_rowCount" id="_rowCount" class="inputbox" size="1"
	onchange="_go();">
<option value="5">
5
</option>
<option value="10">
10
</option>
<option value="15">
15
</option>
<option value="20">
20
</option>
<option value="25">
25
</option>
<option value="30">
30
</option>
<option value="50">
50
</option>
<option value="100">
100
</option>
<option value="0">
所有
</option>
</select>
<script type="text/javascript">
document.getElementById('_rowCount').value = '${pagination.rowCount}';
</script>
</div>
<div class="button2-right ${pagination.page eq 1 ? 'off':'' }">
<div class="start">
<c:if test="${pagination.page eq 1}">
	<span>
	首页
	</span>
</c:if>
<c:if test="${pagination.page ne 1}">
	<a href="javascript:_go(1);">
	首页
	</a>
</c:if>
</div>
</div>
<div class="button2-right ${pagination.page eq 1 ? 'off':'' }">
<div class="prev">
<c:if test="${pagination.page eq 1}">
	<span>
	上一页
	</span>
</c:if>
<c:if test="${pagination.page ne 1}">
	<a href="javascript:_go(${pagination.page - 1  });">
	上一页
	</a>
</c:if>
</div>
</div>
<div class="button2-left">
<div class="page">
<span>
${pagination.page }
</span>
</div>
</div>
<div class="button2-left">
<div class="next ${pagination.page eq pagination.totalPageCount or pagination.totalPageCount eq 0 ? 'off':'' }">
<c:if test="${pagination.page ne pagination.totalPageCount and pagination.totalPageCount ne 0}">
<a href="javascript:_go(${pagination.page + 1  });" title="下一页">
下一页
</a>
</c:if>
<c:if test="${pagination.page eq pagination.totalPageCount or pagination.totalPageCount eq 0 }" >
<span>
下一页
</span>
</c:if>
</div>
</div>
<div class="button2-left">
<div class="end ${pagination.page eq pagination.totalPageCount or pagination.totalPageCount eq 0  ? 'off':'' }">
<c:if test="${pagination.page ne pagination.totalPageCount and pagination.totalPageCount ne 0}">
<a href="javascript:_go(${pagination.totalPageCount });" title="末页">
末页
</a>
</c:if>
<c:if test="${pagination.page eq pagination.totalPageCount or pagination.totalPageCount eq 0 }">
<span>
末页
</span>
</c:if>
</div>
</div>
<div class="limit">
共 ${pagination.totalPageCount } 页
</div>
<input type="hidden" name="_page" value="${empty pagination.page ? 1:pagination.page }" id="_page" />
</div>
</del>
<%--</c:if> --%>

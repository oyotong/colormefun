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
	var _form = document.getElementById("_page").form;
	_form.action = _url;
	_form.submit();
	
}
</c:if>
</script>
<%--<ul>
<li>显示#</li><li>
<select name="_rowCount" id="_rowCount" class="inputbox" size="1"
	onchange="window.location.href='${pageContext.request.contextPath}/sort2/${sort.sortId}_%5FrowCount_'+this.value;">
<option value="24">24</option>
<option value="48">48</option>
<option value="96">96</option>
</select></li></ul> --%>
<DIV class="globalPage  salePage ">
<ul>
	<c:if test="${pagination.page gt 1 }">
	<li><a href='javascript:_go(${pagination.page-1 });' class="previous"></a></li></c:if>
	<li><a href='javascript:_go(1);'${pagination.page eq 1?' class="pageChoose"':'' }>1</a></li>
	<c:if test="${pagination.page gt 3 }"><li><a href=''>..</a></li></c:if>
	<c:if test="${pagination.page-1 gt 1 }">
	<li><a href='javascript:_go(${pagination.page-1 });'>${pagination.page-1 }</a></li>
	</c:if>
	<c:if test="${pagination.page gt 1 }">
	<li><a href='javascript:_go(${pagination.page });' class="pageChoose">${pagination.page }</a></li>
	</c:if>
	<c:if test="${pagination.page+1 lt pagination.totalPageCount }">
	<li><a href='javascript:_go(${pagination.page+1 });'>${pagination.page+1 }</a></li>
	</c:if>
	<c:if test="${pagination.page lt pagination.totalPageCount - 3 }"><li>..</li></c:if>
	<c:if test="${pagination.totalPageCount gt 1 and pagination.totalPageCount ne pagination.page }">
	<li><a href="javascript:_go(${pagination.totalPageCount });">${pagination.totalPageCount }</a></li>
	</c:if>
	<c:if test="${pagination.page lt pagination.totalPageCount }">
	<li><a	href='javascript:_go(${pagination.page+1 });' class="next"></a></li></c:if>
</ul>
</DIV>
<input type="hidden" name="_page" value="${empty pagination.page ? 1:pagination.page }" id="_page" />

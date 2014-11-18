<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/inc/admin/import-jsp-all.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
<noscript>
<iframe src=""></iframe>
</noscript>
<style>
@IMPORT url("css/menu.css");
@IMPORT url("css/index.css");</style>
<script>
var status = 1;
function switchSysBar(){
     if (1 == window.status){
		  window.status = 0;
          switchPoint.innerHTML = '打开左侧菜单';
          document.all("frmTitle").style.display="none"
     }
     else{
		  window.status = 1;
          switchPoint.innerHTML = '关闭左侧菜单';
          document.all("frmTitle").style.display=""
     }
}
</script>
<script type="text/javascript" src="${ROOT}/admin/js/menu1.js"></script>
</head>
<body style="margin: 0px;">
    <span id="lblJS">
    
    <script language="javascript">
    function LoadNavMenu(CataID, MouseOnID, title) {
    	var str = "<div style=\"font-weight:bold;color:#565656;font-size:14px\"><img src='${ROOT}/admin/images/title.gif' />"
    			+ title + "&nbsp;</div>";
    	var objShow = window.parent.frames['frmLeft'].document
    			.getElementById('NavMenu');
    	try {
    		for (i = 0; i < NavMenu.mTitles[CataID].length; i++) {
        		if(!NavMenu.mTitles[CataID][i]){continue;}
    			str += "<a href=\"" + NavMenu.mTitles[CataID][i].href
    					+ "\" target=\"frmright\" alt=\""
    					+ NavMenu.mTitles[CataID][i].title
    					+ "\" onclick=\"ChgStyle(this);\" "
    					+ ((MouseOnID == i) ? "id=\"Menu_On\"" : "") + ">"
    					+ NavMenu.mTitles[CataID][i].title + "</a>" + "\n";
    		}
    	} catch (e) {
    		alert("加载左侧子菜单时发生错误:\n" + e.description);
    	}
    	objShow.innerHTML = str;
    	var dest_obj = window.parent.frames['frmLeft'].document
    			.getElementById('Menu_On');
    	window.parent.frames[dest_obj.target].location.href = dest_obj.href;
    	window.parent.document.frmLeft.document.getElementById('Menu_On').click();
    	return false;
    }
    var NavMenu = {
    	mTitles : [],
    	set : function(ID) {
    	},
    	out : function() {
    	}
    };

    <c:set var="i" value="${1}" ></c:set>
    <c:forEach items="${menu}" var="m">
    	<c:if test="${fn:length(m.purviewMask) == 3 and m.active ne 'N'}">
    		NavMenu.mTitles[${i }] = [
			<c:set var="ci" value="${0}" ></c:set>
    		<c:forEach var="cm" items="${menu}">
	  	  	<c:if test="${fn:length(cm.purviewMask) == 6 and fn:startsWith(cm.purviewMask,m.purviewMask)  and m.active ne 'N'}">
	  	  	   <c:if test="${ci != 0}">,</c:if>
	  	      {"title":"${cm.purviewName }" ,"href":"${ROOT}${cm.purviewUrl }"}
	  	      <c:set var="ci" value="${ci + 1}" ></c:set>
	  	    </c:if>
		  	</c:forEach>
    		<c:set var="i" value="${i+1}" ></c:set>
    		];
    	</c:if>
    </c:forEach>
		
    </script></span>
    <table style="background: none repeat scroll 0% 0% rgb(195, 218, 249);" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
        <tbody>
            <tr>
                <td colspan="3" height="81">
                    <div style="background-image: url('images/top_bg1.gif'); width: 100%; height: 52px;">
                        <div style="float: left; padding-left: 10px;">
                            <img src="images/logo1.gif"></div>
                        <div style="float: right; padding-right: 5px; padding-top: 10px;">
                            <span id="lblTop" style="color: White;">[${sessionScope.SessionAuthUser.userRealname }],欢迎您登录后台管理系统！</span>
                        </div>
                    </div>
                    <div class="top_table_leftbg">
                        <div class="top_table">
                            <span id="lblmenu"><div class="menu"><ul>
                            <c:set var="i" value="${1}" ></c:set>
                            <c:forEach var="m" items="${menu}">
                            <c:if test="${fn:length(m.purviewMask) == 3}">
                            <li onmouseover="Menus.Show(this,0)">
                            	  <a href="#" id="Nav${i }" onclick="LoadNavMenu(${i},0,'${m.purviewName }');" class="menu_sub">${m.purviewName }</a>
								  <div class="menu_childs" onmouseout="Menus.Hide(0);"><ul>
								      <c:set var="ci" value="${0}" ></c:set>
								  	  <c:forEach var="cm" items="${menu}">
									  	  <c:if test="${fn:length(cm.purviewMask) == 6 and fn:startsWith(cm.purviewMask,m.purviewMask)}">
									  	  <li><a href="${ROOT}${cm.purviewUrl }" alt="" target="frmright" onclick="LoadNavMenu(${i},${ci},'${m.purviewName }');">· ${cm.purviewName }</a></li>
									  	  <c:set var="ci" value="${ci+1}" ></c:set>
									  	  </c:if>
								  	  </c:forEach>
								  </ul>
								  </div>
							</li>
							<c:set var="i" value="${i+1}" ></c:set>
							</c:if>
							</c:forEach>
                          </ul></div></span></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td id="frmTitle" name="fmtitle" style="background: none repeat scroll 0% 0% rgb(201, 222, 250);" align="middle" valign="top">
                    <iframe id="frmLeft" name="frmLeft" src="${ROOT}/admin/inc/leftmenu.jsp" style="height: 100%; visibility: inherit; width: 185px; background: url(&quot;images/leftop.gif&quot;) no-repeat scroll 0% 0% transparent;" allowtransparency="true" frameborder="0"></iframe>
                    </td><td style="width: 0px; background: none repeat scroll 0% 0% rgb(255, 255, 255);">
                        <div onclick="switchSysBar()">
                            <span class="navpoint" id="switchPoint" title="关闭/打开左栏">关闭左侧菜单</span>
                        </div>
                    </td>
                <td style="width: 100%;" valign="top">
                    <span id="lblmain"><iframe id="frmright" name="frmright" src="" style="height: 100%; visibility: inherit; width: 100%; z-index: 1;" frameborder="0" scrolling="yes"></iframe></span>
                </td>
            </tr>
            <tr>
                <td colspan="3" height="30">
                    <table style="background: url('images/bot_bg.gif') repeat scroll 0% 0% transparent;" border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tbody><tr height="32">
                            <td style="padding-left: 30px; font-family: arial; font-size: 11px;">
                            <fmt:message bundle="${MessageBundle}" key="system.site.copyright"></fmt:message>
                            </td>
                            <td style="text-align: right; color: rgb(145, 177, 198);">
                            </td><td style="text-align: right; color: rgb(19, 82, 148); padding-right: 20px;">
                                <span id="lbljishu"><a href="#" target="_blank">技术支持中心</a> | </span>
                                
                                <a href="${ROOT}/admin/index.jsp" target="_self">常用快捷菜单</a> | <a href="${ROOT}/admin/user/logout.do">
                                    安全退出</a>
                            </td>
                        </tr>
                    </tbody></table>
                </td>
            </tr>
        </tbody>
    </table>
</body></html>
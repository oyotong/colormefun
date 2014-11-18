<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/portal/import-jsp-all.jsp" %>
<div class="page-footer-placeholder"></div>
</div>
<footer class="page-content">
    <div>
        <div class="footer-copyright">
            @ 2014 Color Me Fun, All Rights Reserved.
        </div>
        <img class="footer-logo" src="${ROOT}/resources/images/logo-small.png" alt="快乐秘方 Color Me Fun" />
        <a href="javascript:void(0);" onclick="openURL('${mf:param('WEIBO', 'CODE_URL')}', '${mf:param('WEIBO', 'URL')}');" class="contact-button">
            <s class="ui-icon icon-weibo"></s>
            <span>微博</span>
        </a>
        <a href="javascript:void(0);" onclick="openURL('${mf:param('WEIXIN', 'CODE_URL')}', '${mf:param('WEIXIN', 'URL')}');" class="contact-button">
            <s class="ui-icon icon-wechat"></s>
            <span>微信</span>
        </a>
        <a href="javascript:void(0);" onclick="openURL('${mf:param('LINKED_IN', 'CODE_URL')}', '${mf:param('LINKED_IN', 'URL')}');" class="contact-button">
            <s class="ui-icon icon-linkedin"></s>
            <span>Linked in</span>
        </a>
        <a href="javascript:void(0);" onclick="openURL('${mf:param('QZONE', 'CODE_URL')}', '${mf:param('QZONE', 'URL')}');" class="contact-button">
            <s class="ui-icon icon-qzone"></s>
            <span>QQ空间</span>
        </a>
    </div>
</footer>
<script type="application/javascript">
    var openURLDialog = null;
    //openURLDialog.Open();
    //openURLDialog.Close();
    $(function () {
        openURLDialog = ui.Dialog(null, $('#OpenURLDialog'), {
            closeText: '关闭窗口',
            minHeight: 0,
            dialogClass: 'confirm-dialog',
            modal: true
        });
    });
    function openURL(imageSrc,url){
        if(!imageSrc && !url){
            return;
        }
        if(!imageSrc){
            window.open(url);
            return;
        }
        if(url) {
            $('#hasURL').show();
            $('#gotoURL').attr('href', url);
        }else{
            $('#hasURL').hide();
        }
        $('#grCode').attr('src',imageSrc);
        openURLDialog.Open();
    }
</script>
<div id="OpenURLDialog">
    <div class="align-center">
        <p><img id="grCode" src="#" style="max-width: 150px; max-height: 150px" /></p>
        <p>扫描上面的二维码，关注我吧！
            <span id="hasURL"><br>或者猛戳<a href="#" id="gotoURL" target="_blank">这里</a>，进去看看！</span></p>
    </div>
    <div class="command-group">
        <button type="button" class="dialog-ok button-highlight" onclick="openURLDialog.Close();"><span>关闭窗口</span></button>
    </div>
</div>
</body>
</html>
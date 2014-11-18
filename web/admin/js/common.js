/**
 * 浏览器判断
 */
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
if (window.ActiveXObject)
    Sys.ie = ua.match(/msie ([\d.]+)/)[1]
else if (document.getBoxObjectFor)
    Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1]
else if (window.MessageEvent && !document.getBoxObjectFor)
    Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1]
else if (window.opera)
    Sys.opera = ua.match(/opera.([\d.]+)/)[1]
else if (window.openDatabase)
    Sys.safari = ua.match(/version\/([\d.]+)/)[1];


/* *********** Common Utility Scripts ***********
 * create: 2007-12-26 by 小点
 * lastupdate: 2007-12-26 by 小点
 * all right reserved 2007 dvbbs.net
/* *********** Common Utility Scripts ***********/


// 根据鼠标或焦点事件，设置文本框和按钮样式,此函数会与样式相关
function IEvent(sType,oInput)
{
	var onStyle,offStyle,eleType;
	eleType = oInput.type;
	// 设置关联样式
	if("button" == eleType || "submit" == eleType || "reset" == eleType || "textarea" == oInput.tagName)
	{
		onStyle = "button_on";
		offStyle = "button_off";
	}
	else
	{
		onStyle = "input_on";
		offStyle = "input_off";
	}
	// 根据事件选择样式
	switch (sType)
	{
		case "focus" :
			oInput.isfocus = true;
		case "mouseover" :
			oInput.className = onStyle;
			break;
		case "blur" :
			oInput.isfocus = false;
		case "mouseout" :
			if(!oInput.isfocus)
			{
				oInput.className = offStyle;
			}
			break;
	}
}

function Reset(form){
	var iArray = document.getElementsByTagName("input");
	
	if(iArray.length)
	for(var i=0;i<iArray.length;i++){
		var obj = iArray[i];
		if(obj.type == 'text' || obj.type == 'password'){
			obj.value = "";
		}
		if(obj.type == 'radio'){
			obj.selected = false;
		}
		if(obj.type == 'checkbox'){
			obj.checked = false;
		}
	}
	
	var oArray = document.getElementsByTagName("option");
	for(var i=0;i<oArray.length;i++){
		var obj = oArray[i];
		if(obj.value == ''){
			obj.selected = "selected";
		}
	}
}

function NumberKeyHandler(event){
	if(Sys.ie)
		return event.keyCode >= 48 && event.keyCode <= 57;
	return true;
}

function trim(str)
{
	return str.replace(/(^[\s　]*)|([\s　]*$)/g, '');
}
String.prototype.trim = function(){
	return this.replace(/(^[\s　]*)|([\s　]*$)/g, '');
};

function imageResize(image,maxWidth,maxHeight){
	if(image.width > maxWidth && image.height*(maxWidth/image.width) <= maxHeight ){image.width = maxWidth;}else if(image.height > maxHeight){image.height = maxHeight;}
}

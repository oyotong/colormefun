// JScript 文件
//document.charset = "gb2312";
//定义textbox鼠标效果
var xmlhttp = HttpObj();    
function HttpObj(){
	var xmlhttp = null;
	try{
		xmlhttp= new ActiveXObject('Msxml2.XMLHTTP');
	}catch(e){
		try{
			xmlhttp= new ActiveXObject('Microsoft.XMLHTTP');
		}catch(e){
			try{
				xmlhttp= new XMLHttpRequest();
			}catch(e){}
		}
	}
	if (xmlhttp) return xmlhttp;
}

//url参数分割
function parseQuery ( query ) {
   var Params = {};
   if ( ! query ) {return Params;}// return empty object
   var Pairs = query.split(/[;&]/);
   for ( var i = 0; i < Pairs.length; i++ ) {
      var KeyVal = Pairs[i].split('=');
      if ( ! KeyVal || KeyVal.length != 2 ) {continue;}
      var key = unescape( KeyVal[0] );
      var val = unescape( KeyVal[1] );
      val = val.replace(/\+/g, ' ');
      Params[key] = val;
   }
   return Params;
}

function inputStyle(fEvent,oInput){
	if (!oInput.style) return;
	switch (fEvent){
		case "focus" :
			oInput.isfocus = true;
		case "mouseover" :
			oInput.style.borderColor = "#339900";
			oInput.style.backgroundColor = "#FFFFF0";
			break;
		case "blur" :
			oInput.isfocus = false;
		case "mouseout" :
			if(!oInput.isfocus){
				oInput.style.borderColor='#878787';
				oInput.style.backgroundColor = "";
			}
			break;
	}
}

window.onload = function(){
	var oInput = document.getElementsByTagName("input");
	//alert(oInput.length);
	for (var i=0; i<oInput.length; i++)
	{
		if (oInput[i].getAttribute("type").toLowerCase()=="text" || oInput[i].getAttribute("type").toLowerCase()=="password"){
			if (document.all)
			{
				oInput[i].attachEvent("onmouseover",oInput[i].onmouseover=function(){inputStyle("mouseover",this);});
				oInput[i].attachEvent("onmouseout",oInput[i].onmouseout=function(){inputStyle("mouseout",this);});
				oInput[i].attachEvent("onfocus",oInput[i].onfocus=function(){inputStyle("focus",this);});
				if (oInput[i].id=="stylewidth")
					oInput[i].attachEvent("onblur",oInput[i].onblur=function(){inputStyle("blur",this);reSize(this.value);});
				else
					oInput[i].attachEvent("onblur",oInput[i].onblur=function(){inputStyle("blur",this);});
			}
			else{
				oInput[i].addEventListener("onmouseover",oInput[i].onmouseover=function(){inputStyle("mouseover",this);},false);
				oInput[i].addEventListener("onmouseout",oInput[i].onmouseout=function(){inputStyle("mouseout",this);},false);
				oInput[i].addEventListener("onfocus",oInput[i].onfocus=function(){inputStyle("focus",this);},false);
				if (oInput[i].id=="stylewidth")
					oInput[i].addEventListener("onblur",oInput[i].onblur=function(){inputStyle("blur",this);reSize(this.value);},false);
				else
					oInput[i].addEventListener("onblur",oInput[i].onblur=function(){inputStyle("blur",this);},false);
			}
		}
	}
}



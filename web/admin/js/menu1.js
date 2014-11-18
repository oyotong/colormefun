// JScript 文件

var Menus = new DvMenuCls;

function DvMenuCls(){
	var MenuHides = new Array();
	this.Show = function(obj,depth){
		var childNode = this.GetChildNode(obj);
		if (!childNode){return ;}
		if (typeof(MenuHides[depth])=="object"){
			this.closediv(MenuHides[depth]);
			MenuHides[depth] = '';
		};
		if (depth>0){
			if (childNode.parentNode.offsetWidth>0){
				childNode.style.left= childNode.parentNode.offsetWidth+'px';
				
			}else{
				childNode.style.left='100px';
			};
			
			childNode.style.top = '-2px';
		};
		childNode.style.display ='block';
		MenuHides[depth]=childNode;
	
	};
	this.closediv = function(obj){
		if (typeof(obj)=="object"){
			if (obj.style.display!='none'){
			obj.style.display='none';
			}
		}
	}
	this.Hide = function(depth){
		var i=0;
		if (depth>0){
			i = depth
		};
		while(MenuHides[i]!=null && MenuHides[i]!=''){
			this.closediv(MenuHides[i]);
			MenuHides[i]='';
			i++;
		};
	
	};
	this.Clear = function(){
		for(var i=0;i<MenuHides.length;i++){
			if (MenuHides[i]!=null && MenuHides[i]!=''){
				MenuHides[i].style.display='none';
				MenuHides[i]='';
			}
		}
	}
	this.GetChildNode = function(submenu){
		for(var i=0;i<submenu.childNodes.length;i++)
		{
			if(submenu.childNodes[i].nodeName.toLowerCase()=="div")
			{
				var obj=submenu.childNodes[i];
				break;
			}
		}
		return obj;
	}

}
document.onclick=Menus.Clear;

function ShowLeft(theItem){ 
	var _panel = theItem.parentNode.firstChild;
	var _left = this.window.left.document.getElementById("leftchilemenu");
	var _leftbar = document.getElementById("_leftbar");
	var _frmright = document.getElementById("frmright");
	//var reg = /<A(.*?)href="(.*?)"(.*?)>(.*?)<\/A>/g
	var _str = "";
	if(arguments.length == 1){
		_leftbar.innerHTML=_panel.innerHTML;
		_panel = _panel.nextSibling.nextSibling.firstChild.firstChild;
	}else{
		_panel = _panel.parentNode.parentNode.firstChild;
		_leftbar.innerHTML=_panel.parentNode.parentNode.parentNode.firstChild.innerHTML;
	}
	var i=0;
	while(_panel = _panel.nextSibling){
		if(i==0) _panel=_panel.parentNode.firstChild;
		var _t = _panel.innerHTML;
		var _reg = /<A(.*?)href="(.*?)"(.*?)>(.*?)<\/A>/ig;

		if(_reg.test(_t)){
			if(i == 0){
				_frmright.src = RegExp.$2;
			}
			i++;
			_str += "<" + "a " + "href=\"" + RegExp.$2 + "\" target=\"frmright\" class=\"left_color\" onfocus=\"" + "blur()\"> " + RegExp.$4 + "<" + "\/a>";
		}
		
	}
	_left.innerHTML = _str;
	return;
}
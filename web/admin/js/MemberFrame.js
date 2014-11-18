// JScript 文件

var isIe=(document.all)?true:false; 
//设置select的可见状态 
function setSelectState(state) 
{ 
var objl=document.getElementsByTagName('select'); 
for(var i=0;i<objl.length;i++) 
{ 
objl[i].style.visibility=state; 
} 
} 
function mousePosition(ev) 
{ 
if(ev.pageX || ev.pageY) 
{ 
return {x:ev.pageX, y:ev.pageY}; 
} 
return { 
x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,y:ev.clientY + document.body.scrollTop - document.body.clientTop 
}; 
} 
//弹出方法 
function showMessageBox(wTitle,content,pos,wWidth) 
{ 
closeWindow(); 
var bWidth=parseInt(document.documentElement.scrollWidth); 
var bHeight=parseInt(document.documentElement.scrollHeight); 
if(isIe){ 
setSelectState('hidden');} 
var back=document.createElement("div"); 
back.id="back"; 
var styleStr="top:0px;left:0px;position:absolute;width:"+bWidth+"px;height:"+bHeight+"px;"; 
styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;"; 
back.style.cssText=styleStr; 
document.body.appendChild(back); 
showBackground(back,50); 
var mesW=document.createElement("div"); 
mesW.id="mesWindow"; 
mesW.className="mesWindow"; 
mesW.innerHTML="<div class='mesWindowTop'><table width='100%' height='100%'><tr><td>"+wTitle+"</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='关闭窗口' class='close' value='关闭' /></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>"; 
if (pos.y<400)
{
pos.y = 500;
}
styleStr="left:"+(((pos.x-wWidth)>0)?(pos.x-wWidth):pos.x)+"px;top:"+(pos.y-150)+"px;position:absolute;width:"+wWidth+"px;"; 
mesW.style.cssText=styleStr; 
document.body.appendChild(mesW); 
} 
//让背景渐渐变暗 
function showBackground(obj,endInt) 
{ 
if(isIe) 
{ 
obj.filters.alpha.opacity+=1; 
if(obj.filters.alpha.opacity<endInt) 
{ 
setTimeout(function(){showBackground(obj,endInt)},5); 
} 
}else{ 
var al=parseFloat(obj.style.opacity);al+=0.01; 
obj.style.opacity=al; 
if(al<(endInt/100)) 
{setTimeout(function(){showBackground(obj,endInt)},5);} 
} 
} 
//关闭窗口 
function closeWindow() 
{ 
if(document.getElementById('back')!=null) 
{ 
document.getElementById('back').parentNode.removeChild(document.getElementById('back')); 
} 
if(document.getElementById('mesWindow')!=null) 
{ 
document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow')); 
} 

if(isIe){ 
setSelectState('');} 
} 
//测试弹出 
function testMessageBox(ev,n,id) 
{ 
var objPos = mousePosition(ev); 
if(n==1)
{
messContent="<div id=div1 style='text-align:center'><iframe src=setpwd.do?cids="+id+"&section=admin frameborder=0 width=100% height=300></iframe></div>"; 
showMessageBox('修改密码',messContent,objPos,350); 
}
if(n==2)
{
messContent="<div id=div2 style='text-align:center'><iframe src=setmeminfo.do?cids="+id+" frameborder=0 width=100% height=400></iframe></div>"; 
showMessageBox('修改资料',messContent,objPos,600); 
}
if (n==3)
{
messContent="<div id=div3 style='text-align:center'><iframe src=chongzhi.do?cids="+id+" frameborder=0 width=100% height=280></iframe></div>"; 
showMessageBox('账户充值',messContent,objPos,400); 
}
if (n==4)
{
messContent="<div id=div4 style='text-align:center'><iframe src=huifu.aspx?id="+id+" frameborder=0 width=100% height=200></iframe></div>"; 
showMessageBox('修改/回复留言',messContent,objPos,450); 
}

} 
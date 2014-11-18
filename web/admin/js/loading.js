// JScript 文件
document.charset = "utf-8";
//页面加载数据前的loading效果
//div的样式名称为[loading_div], 在公共的样式表 [public.css]
document.write("<div id='loading_div' style='display:none'><table width='100%' border='0' cellpadding='3' cellspacing='1' class='loading_tb'><tr><td class='input_text'>&nbsp;</td></tr><tr><td align='center'><img src='../../images/loading.gif' /></td></tr><tr><td align='center'>正在加载数据，请等待...</td></tr></table></div>");
//显示loading层 
function show_loading(){
 document.getElementById("loading_div").style.display="block";
 loadWindow();
}
//隐藏loading层 
function hide_loading(){
 document.getElementById("loading_div").style.display="none";
}


function loadWindow()  
{  
   if(document.readyState == "complete")
   {           
       document.getElementById("loading_div").style.display="none";
   }
   else
   {   
       setTimeout("loadWindow()", 100);
   }
} 


setTimeout("show_loading()", 100);






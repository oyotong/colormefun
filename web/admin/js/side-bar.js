var isExtended = 0;
function slideSideBar(){

	new Effect.toggle('sideBarContents', 'blind', {scaleX: 'true', scaleY: 'true;', scaleContent: false});
	
	if(isExtended==0){
		abc('sideBarTab').childNodes[0].src = abc('sideBarTab').childNodes[0].src.replace('.gif', '-active.gif');
		
		new Effect.Fade('sideBarContents',
   	{ duration:1.0, from:0.0, to:1.0 });
		
		isExtended++;
	}
	else{
		abc('sideBarTab').childNodes[0].src = abc('sideBarTab').childNodes[0].src.replace('-active.gif', '.gif');
		new Effect.Fade('sideBarContents',
   	{ duration:1.0, from:1.0, to:0.0 });
		
		isExtended=0;
	}
	
}

function init(){
	Event.observe('sideBarTab', 'click', slideSideBar, true);
}

Event.observe(window, 'load', init, true);
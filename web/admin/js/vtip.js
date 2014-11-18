/**
Vertigo Tip by www.vertigo-project.com
Requires jQuery
*/

this.vtip = function(selector) {    
    this.xOffset = -10; // x distance from mouse
    this.yOffset = 10; // y distance from mouse       
    
    $(selector).unbind().hover(    
        function(e) {
            this.t = this.title;
            if(!this.t){
            	if($(this).attr('alt')){
            		this.t = $(this).attr('alt');
                }else{
                	return false;
                }
            }
            if(!(this.t.indexOf('<') != -1 || this.t.indexOf('&lt;')!=-1)){
            	return false;
            }
            this.title = ''; 
            this.top = (e.pageY + yOffset); this.left = (e.pageX + xOffset);
            
            $('body').append( '<p id="vtip"><img id="vtipArrow" />' + this.t + '</p>' );
                        
            $('p#vtip #vtipArrow').attr("src", _root_+'/images/vtip_arrow.png');
            $('p#vtip').css("top", this.top+"px").css("left", this.left+"px").fadeIn("slow");
            
        },
        function() {
        	if(this.t){
        		this.title = this.t;
        		$("p#vtip").fadeOut("slow").remove();
        	}
        }
    ).mousemove(
        function(e) {
            this.top = (e.pageY + yOffset);
            this.left = (e.pageX + xOffset);
                         
            $("p#vtip").css("top", this.top+"px").css("left", this.left+"px");
        }
    );            
    
};

//jQuery(document).ready(function($){vtip();}) 
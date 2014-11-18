/**
 * @author GeekTantra
 * @date 20 September 2009
 */
var ValidationState = "valid";
var ValidationCall=function(){};
(function(jQuery){
    jQuery.fn.validate = function(options){
        options = jQuery.extend({
            expression: "return true;",
            message: "",
            error_class: "ValidationErrors",
            error_field_class: "ErrorField",
            live: true
        }, options);
        var SelfID = jQuery(this).attr("id");
        if (options['live']) {
            jQuery(this).bind('blur', function(){
                validate_field(this);
            });
            jQuery(this).bind('focus keypress', function(){
                jQuery(this).next('.' + options['error_class']).fadeOut("fast", function(){
                    jQuery(this).remove();
                });
                jQuery(this).removeClass(options['error_field_class']);
            });
        }
        jQuery(this).parents("form").submit(function(){
            validate_field('#' + SelfID);
            if (ValidationState == "valid")
                return true;
            else 
                return false;
        });
        function validate_field(id){
            var self = jQuery(id).attr("id");
            var validation_state = false;
            if(jQuery.isFunction(options['expression'])){
            	ValidationCall = function(validation_state,message){
            		showMessage(id,validation_state,message);
            	};
            	validation_state = options['expression'](jQuery(id));            	
            }else{
	            var expression = 'function Validate(){' + options['expression'].replace(new  RegExp('val(?=[^\\(\\w])',"g"), '$(\'#' + self + '\').val()') + '} Validate()';
	            validation_state = eval(expression);
	            return showMessage(id,validation_state);
	        }
            
            
        }
        
        function showMessage(id,validation_state,message){
        	
        	if (!validation_state) {
        		ValidationState = "invalid";
        		if(!message){
            		message=options['message'];
            	}
                if (jQuery(id).next('.' + options['error_class']).length == 0) {
                    jQuery(id).after('<span class="' + options['error_class'] + '">' + message + '</span>');
                    jQuery(id).addClass(options['error_field_class']);
                }
                
                return false;
            }
            else {
            	ValidationState = "valid";
            	 if (jQuery(id).next('.' + options['error_class']).length == 0 && message) {
                     jQuery(id).after('<span class="' + options['error_class'] + '">' + message + '</span>');
                 }
                return true;
            }
        }
    };
})(jQuery);


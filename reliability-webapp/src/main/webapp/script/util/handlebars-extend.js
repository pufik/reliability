Handlebars.registerHelper('html', function(text){	
	if(text == null){
		return "";
	}
	return new Handlebars.SafeString(text); 
});
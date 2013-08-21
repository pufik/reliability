function TemplateProvider(context){
	var templates = {};
	var appContext = context;

	function compileSource(path, source, context, handler){
		var template = Handlebars.compile(source);
		templates[path] = template;
		var html = template(context);
		
		handler(html);
	}
	
	function getTemplateByAjax(path, context, handler){
		var prefix = appContext.getTemplateContext().prefix;
		var sufix = appContext.getTemplateContext().sufix;
		var date = new Date();
		var noCache = "?not_cache=" + date.getTime();

		
		jQuery.ajax({
			url : prefix + path + sufix + noCache, 
			type : "GET",
			dataType : 'html',
			success : function(source) {
				 compileSource(path, source, context, handler);
			},
			error : function(data) {
				alert(data);
			}
		});
	}
	
	this.getContent = function(path, context, handler){
		var template = templates[path];
		
		if(template){
			handler(template(context));
		}else{
			getTemplateByAjax(path, context, handler);
		}
	}	
}
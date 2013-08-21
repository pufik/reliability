function getDataByAjax(context) {
	var resultData = "";
	jQuery.ajax({
		url : context.pageUrl,
		type : context.reqType,
		cache : false,
		data : context.reqData,
		dataType : 'json',
		beforeSend : context.beforeSend || showDefaultLoadingScreen,
		complete : context.onComplete || hideDefaultLoadingScreen,
		success : function(data) {
			if (context.handler) {
				context.handler(data);
			}
		}
	});
}

function getDataByAjaxJSON(context) {
	jQuery.ajax({
		url : context.pageUrl,
		type : context.reqType,
		cache : false,
		data : JSON.stringify(context.dataObject),
		contentType : 'application/json',
		dataType : 'json',
		beforeSend : context.beforeSend || showDefaultLoadingScreen,
		complete : context.onComplete || hideDefaultLoadingScreen,
		success : function(data) {
			if (context.handler) {
				context.handler(data);
			}
		}
	});
}

// ################# Session expired ####################
$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
	//TODO: Change to MAP
	if (jqXHR.status == 401) {
		hideDefaultLoadingScreen();
		handleSessionExpired();
	}else{
		var errorMessage = jQuery.parseJSON(jqXHR.responseText);
		errorMessage.title = jqXHR.statusText;
		handleServerSideException(errorMessage);
	}
});

function handleSessionExpired() {
	$('#sessionExpiredDialog')
			.dialog({
				zIndex : 0,
				closeOnEscape : false,
				autoOpen : true,
				modal : true,
				width : 300,
				draggable : false,
				resizable : false,
				buttons : {
					'OK' : function() {
						$(this).dialog("close");
					}
				},
				close : function(event, ui) {
					window.location.href = getContext().contextPath;
				}
			});
}

function handleServerSideException(message) {
	$('<div title="' + message.title+'">' + message.message + '</div>')
			.dialog({
				zIndex : 0,
				closeOnEscape : false,
				autoOpen : true,
				modal : true,
				width : 500,
				draggable : false,
				resizable : false,
				buttons : {
					'OK' : function() {
						$(this).dialog("close");
					}
				},
				close : function(event, ui) {
					$(this).remove();
				}
			});
}

function showDefaultLoadingScreen() {
	$.blockUI({
		message : $('<img src="'+ getContext().contextPath +'/style/css/images/ajax-loader.gif" style="width: 128px; height: 128px"/>'),
		css : {
			border : 0,
			top : ($(window).height() - 128) / 2 + 'px',
			left : ($(window).width() - 128) / 2 + 'px',
			background: "none",
			width: 0,
			showOverlay : false
		}
	});
}

function  hideDefaultLoadingScreen(){
	$.unblockUI();
}
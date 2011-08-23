var VDB = {
	loadHtml: function(url){
		$.ajax({
			url: url,
			context: document,
			dataType: "html",
			beforeSend: function(){
				$("#content-view").addClass("loading-information");
			},
			success: function(html){
				$("#content-view").removeClass("loading-information");
				$("#content-show").html(html);
				var title = $(html).filter("title").text();
				$("#content-selected").text(title);
			}
		});
	},
	redirectPage: function(){			
		var url = $.getParameter("url");
		if(url != ''){
			$.history.load(url);
		}else{
			$.history.load("pages/acerca-de.html");
		}
	},
	initConsole: function(){
		$("li#exec").click(function(){
			var source = $("#bash").val();
			window.console.log(eval(source));
		});
		$("li#hide").click(function(){
			$("#console").fadeOut("slow");
		});
		$("a#abre-consola").click(function(){
			$("#console").show("slow");
		});		
		//$("#console").draggable();
	},
	defineLinkAjax: function(){
		$(".open-link, .detalles-aqui").live('click', function(element){
			var url = $(this).attr("href");
			$.history.load(url);
			return false;
		});
	}, 
	loadTwitterReader: function(){
		$("#content-twitter").twitterReader({twitterUser:'valledelbit', timeRefresh:60000, numberOfTwits:5});
	}
};
$().ready(function(){	
	/* Rediect page*/
	VDB.redirectPage();	
	
	/* Init console jQuery*/		
	VDB.initConsole();
	
	/* Define Links */
	VDB.defineLinkAjax();		
	
	/* Load Twitter Rader for ValleDelBit */
	VDB.loadTwitterReader();

	$.history.init(function(hash){
		if(hash != "") {
			VDB.loadHtml(hash);
		}
	},
	{ unescape: ",/" });
});

function addJavascript(jsname) {
	var th = document.getElementsByTagName('head')[0];
	var s = document.createElement('script');
	s.setAttribute('type','text/javascript');
	s.setAttribute('src',jsname);
	th.appendChild(s);
}
addJavascript("https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js");

$(document).ready(function(){
	$("#logo").hover(function(){
		$("#logo img").attr("src","../resources/images/logoin.png")
	},function(){
		$("#logo img").attr("src","../resources/images/logoout.png")
	})
})

function globalSearch(){
	
}

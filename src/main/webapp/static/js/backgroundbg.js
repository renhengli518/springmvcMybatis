$(function(){
	$('.containerLogin').css("background-image", "url()");
	$('body').append('<div id="main_bg" style="position: absolute;left:0;top:0;z-index:-100;">');
	$('#main_bg').append('<img id="bigpic" src="../../public/img/admin/loginBg.jpg"/>');
	cover();
	$(window).resize(function(){
		cover();
	});
	function cover() {
		var win_width = $(window).width();
		var win_height = $(window).height();
		$("#bigpic").attr({width: win_width, height: win_height});
	}
});
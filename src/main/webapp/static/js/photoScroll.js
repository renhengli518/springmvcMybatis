var scrollHori={
	init : function(){
		scrollHori.locked = 0;
		scrollHori.vector = 0; //方向
		scrollHori.feet = 1; //滚动步长
		scrollHori.length = $tag('li',$id('rollList')).length;
		scrollHori.size = $tag('li',$id('rollList'))[0].offsetWidth;
		scrollHori.bind();
	},
	bind : function(){
		events.addEvent($id("rollLeft"),"click",scrollHori.turnLeft);
		events.addEvent($id("rollRight"),"click",scrollHori.turnRight);
	},
	turnLeft:function(){
		if (scrollHori.locked == 0){
			if($id('rollBox').scrollLeft!=0){
				scrollHori.vector = -1;
				scrollHori.scroll();
			}else{
				scrollHori.vector = 1;
				scrollHori.scroll('back');
			}
		}
	},
	turnRight : function(){
		if (scrollHori.locked == 0){
			if ($id('rollBox').scrollLeft!=($id('rollList').offsetWidth-$id('rollBox').offsetWidth)){
				scrollHori.vector = 1;
				scrollHori.scroll();
			}else{
				scrollHori.vector = -1;
				scrollHori.scroll('back');
			}
		}
	},
	scroll:function(type){
		var distance = 0,
			step = (type == "back") ? scrollHori.length : 1;
			scrollHori.feet = (type == "back") ? 100 : 8;
		play = setInterval(function(){
			if(scrollHori.size * step > distance){
				$id('rollBox').scrollLeft += (scrollHori.vector * scrollHori.feet);
				distance += Math.abs(scrollHori.vector * scrollHori.feet);
				scrollHori.locked = 1;
			}else{
				scrollHori.stop();
			}
		},5);
	},
	stop:function(){
		if (window.play) {
			clearInterval(window.play);
			scrollHori.locked = 0;
		}
	}
};


var autoPlay = {
	init : function(){
		autoPlay.liList = $tag('li',$id('rollList'));
		autoPlay._timer = null;
		autoPlay.length = autoPlay.liList.length;
		autoPlay.size = 4;
		autoPlay.index = 0;
		autoPlay.bind();
		autoPlay.play();
		addClass(autoPlay.liList[0],"selected");
	},
	bind : function(){
		var showBox = $class("information-top")[0];
		if(showBox) {
			events.addEvent(showBox,"mouseenter",autoPlay.stop);
			events.addEvent(showBox,"mouseleave",autoPlay.play);
		}
	},
	play : function(){
		autoPlay._timer = setInterval(function(){
			autoPlay.index ++;
			if(autoPlay.index >= autoPlay.length) autoPlay.index = 0;
			if(autoPlay.index <= autoPlay.length - autoPlay.size) scrollHori.turnRight();
			removeClass(autoPlay.liList,"selected");
			addClass(autoPlay.liList[autoPlay.index],"selected");
			
			if(typeof showPhotoScrollDetail == 'function') {//显示相对应的滚动窗,如果有需要
				showPhotoScrollDetail(autoPlay.index);
			}
		},3*1000);
	},
	stop : function(){
		clearInterval(autoPlay._timer);
	} 
};

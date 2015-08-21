function check_FileType(fileName){
	var type = fileName.substr(fileName.lastIndexOf(".")+1,fileName.length);
	type = type.toLowerCase();
	if(type !='jpg' && type != 'jpeg' && type !='png'&& type !='gif'&& type !='bmp' && type !='svg' && type !='psd'){
		return true;
	}
	return false;
}

function imgShow(outerdiv, innerdiv, bigimg, _this){
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    var fileName = _this.attr("filename");
    if(check_FileType(fileName)){
    	alert("非图片格式无法察看预览");
    	return true;
    }
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性
 
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();
        var windowH = $(window).height();
        var realWidth = this.width;
        var realHeight = this.height;
        var imgWidth, imgHeight;
        var scale = 0.8;
         
        if(realHeight>windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;
            imgWidth = imgHeight/realHeight*realWidth;
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {
            imgWidth = windowW*scale;
                        imgHeight = imgWidth/realWidth*realHeight;
        } else {
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
                $(bigimg).css("width",imgWidth);
         
        var w = (windowW-imgWidth)/2;
        var h = (windowH-imgHeight)/2;
        $(innerdiv).css({"top":h, "left":w});
        $(outerdiv).fadeIn("fast");
    });
     
    $(outerdiv).click(function(){
        $(this).fadeOut("fast");
    });
}
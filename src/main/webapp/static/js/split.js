function splitString(value, size) {
	
	if((value)  && (value.length > size)) {
		
		return "<a href='javascript:void(0);' title='" + value + "'>" + value.substring(0,size) + "..." + "</a>" ;
	}
	
	return value ;
} ;

function splitString2(str,size){
	var text = $('<div>').html(str).text();
	if((text)  && (text.length > size)) {
		return "<a href='javascript:void(0);' title='" + text + "'>" + text.substring(0,size) + "..." + "</a>" ;
	}
	return text ;
} ;

function splitStringHtml(value, size) {
	var value = htmlDecode(value);
	if((value)  && (value.length > size)) {
		return "<a href='javascript:void(0);' title='" + value + "'>" + value.substring(0,size).replace(/\</g,"&lt;").replace(/\>/g,"&gt;") + "..." + "</a>" ;
	}
	return value.replace(/\</g,"&lt;").replace(/\>/g,"&gt;");
} ;

function htmlDecode(textVal){
 	 var $elem = $("<div></div>");
 	 $elem.html(textVal);
 	 var retHtml = $elem.text();
 	 $elem.remove();
 	 return retHtml;
}

function splitNormalString(value, size) {
	
	if((value)  && (value.length > size)) {
		
		return value.substring(0,size) + "..." ;
	}else if(value == null || value == 'undefined'){
		return "-";
	}else{
		return value;
	}
	
	//return value ;
} ;
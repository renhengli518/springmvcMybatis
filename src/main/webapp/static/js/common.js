//上传类
function UploadFile(wrap, namespace, url, params, call){
	var self = this;
	var myParams = extendCopy(params || {}, {
		nameSpace : namespace,
		debug : 1,
		fileType : "*.pdf;*.doc;*.ppt;*.docx;*.pptx;*.xls;*.xlsx;*.PDF;*.DOC;*.PPT;*.DOCX;*.PPTX;*.XLS;*.XLSX;*.jpg;*.gif;*.png;*.jpeg;*.bmp;*.swf;",
		typeDesc : "office文档、图片、pdf文档",
		//multiSelect : true,
		//sizeLimit : 20*1024*1024,
		server: "/me.php?r=/codyy/uploadFiles"
	}); 
	if(call){
		this.noticeTypeError = call.noticeTypeError; 
		this.noticeSizeError = call.noticeSizeError; 
		this.onCancel = call.onCancel; 
		this.onOpen = call.onOpen; 
		this.onProgress = call.onProgress; 
		this.onComplete = call.onComplete; 
	}
	this.init = function(){
		var paramVars = "";
		for(var i in myParams){
			paramVars += i+"="+myParams[i]+"&";
		};
		this.obj = FlashPlayer(wrap, url+"?"+paramVars, namespace);
	};
	this.cancelUpload = function(){
		this.obj.cancelUpload();
	};
	this.init();
};

 
var smile ={
	//表情
	emt : {
		"微笑":"weixiao",
		"呲牙":"ciya",
		"再见":"zaijian",
		"偷笑":"touxiao",
		"调皮":"tiaopi",
		"大哭":"daku",
		"擦汗":"cahan",
		"猪头":"zhutou",
		"得意":"deyi",
		"傲慢":"aoman",
		"发怒":"fanu",
		"害羞":"haixiu",
		"憨笑":"hanxiao",
		"汗":"han",
		"尴尬":"ganga",
		"抓狂":"zhuakuang",
		"花":"hua",
		"惊恐":"jingkong",
		"惊讶":"jingya",
		"可爱":"keai",
		"抠鼻":"koubi",
		"酷":"ku",
		"流泪":"liulei",
		"便便":"bianbian",
		"难过":"nanguo",
		"撇嘴":"piezui",
		"敲打":"qiaoda",
		"亲亲":"qinqin",
		"色":"se",
		"胜利":"shengli",
		"示爱":"shiai",
		"白眼":"baiyan",
		"耍酷":"shuaku",
		"衰":"shuai",
		"睡":"shui",
		"鄙视":"bishi",
		"吐":"tu",
		"嘘":"xu",
		"委屈":"weiqu",
		"炸弹":"zhadan",
		"心":"xin",
		"心裂":"xinlie",
		"菜刀":"caidao",
		"疑问":"yiwen",
		"阴险":"yinxian"
	},
	init : function(cdRoot) {
		smile.box =  document.createElement("div");
		var litag ='<ul>';
			for(var k in smile.emt){
				litag+='<li><img src="' + cdRoot + '/public/img/face/'+smile.emt[k]+'.gif" title="'+ k +'" /></li>';
			};
			litag += '</ul>';
		if (UA.isIE6){
			litag += '<iframe id="msgFace_ie6" frameborder="0" scrolling="no"></iframe>';
		};
		smile.box.id = "smileBox";
	    smile.box.innerHTML = litag;
	    smile.box.style.display = "none";
		document.body.appendChild(smile.box);
		events.delegate(smile.box, "li", "click", smile.add);
		events.addEvent(document, 'click', function(){
			if (smile.box)
				smile.box.style.display = "none"; 
			return false;
		});
	},
	add : function(){
		var event = arguments[0] || window.event,
		target = event.srcElement || event.target,
		imgVal = target.getAttribute("title");
		//smile.textBox.insertAtCaret(smile.encodePubContent(imgVal));
		//smile.textBox.insertAdjacentHTML('beforeEnd',smile.encodePubContent(imgVal));
		smile.textBox.value += smile.encodePubContent(imgVal);
		smile.box.style.display = "none";
		smile.cb && smile.cb(smile.textBox);
	},
	show : function(e,t,cdRoot, cb){
		if(!smile.box) smile.init(cdRoot);
		if(cb) smile.cb = cb;
		smile.textBox =  t;
		var e = e || window.event,
			o = e.srcElement || e.target;
		var pos = o.getBoundingClientRect(),
			mH = document.body.scrollTop || document.documentElement.scrollTop;
		smile.box.style.cssText = "display:block;top:"+(pos.top + mH - 198)+"px;left:"+(pos.left)+"px;";
		try{
			e.stopPropagation(); //阻止冒泡
		}catch(e){
			e.cancelBubble = true; //针对ie
		}
	},
	decodePubContent : function(content,cdRoot)
	{	
		 content = content || '';
		 var imgReg = /\[(\W[^\[]*)\]/gim;  //匹配[酷][耶][大笑]这种格式
		 content =  content.replace(imgReg, function(all,$1){
		 	if(smile.emt[$1] == undefined){
		 		return all;
		 	};
		 	return "<img src='"+cdRoot+"/public/img/face/"+smile.emt[$1]+".gif' title='"+ $1 +"' />";
		 });
		 return content;
	},
	encodePubContent : function(content)
	{
		// var imgReg =   /<img.*title=\"?([^\s]*)(?=(\s|\")).*?>/gim;
		// content = content.replace(imgReg,"[$1]");
		// return content;
		return "["+content+"]";
	}
};

String.prototype.r = function(f, r)
{
	var c = this + "";
	r=r||"";
	if (f instanceof Array)
	{
		for (var i = 0; i < f.length; i++)
		{
			c = c.replace(f[i], r[i]);
		}
	}
	else
	{
		c = c.replace(f, r);
	}
	return  c;
};
function now(f,t)
{
    var D=t?new Date(t):new Date();
    var y=D.getFullYear();
    var m=D.getMonth()+1;
	var w=D.getDay();
    m=m<10?"0"+m:m;
    var d=D.getDate();
    d=d<10?"0"+d:d;
    var h=D.getHours();
    h=h<10?"0"+h:h;
    var i=D.getMinutes();
    i=i<10?"0"+i:i;
    var s=D.getSeconds();
    s=s<10?"0"+s:s;
    var ms=D.getMilliseconds();
    if(!f)
    {
    	return  y + "-" + m + "-" + d + " " + h + ":" + i + ":" + s;
    }
    var c=":";
    var p=" ";
    var l="-";
    var r="";
    if(f!=null)
    {
        try
        {
            l=f.match(/^\w(\W?)\w/)[1];
        }catch (e)
        {
            l="-";
        }
    }
    r=f.r(["ms","m","y","d","h","i","s","w"],[ms,m,y,d,h,i,s,w]);
    return r;
};
/*
function LightBox(){
	this.showWrap = "";
	this.curr = 0;
	this.bind();
};
LightBox.prototype = {
	bind : function(){
		var that = this;
		events.delegate(document.body,".lightPic","click",function(){
			var e = arguments[0] || event,
				target = e.srcElement || e.target;
			that.pics = $class("lightPic",target.parentNode);
			var idx = target.getAttribute("idx");
			if(idx == null) idx = 0; 
			that.show(idx);
		});
	},
	create : function(){
		var that = this;
		var wrap = document.createElement("DIV");
		wrap.id = "lightWrap";
		wrap.innerHTML = "<div class='mask'></div><div class='cont'><a class='close' href='javascript:;'>X</a><a class='prev' href='javascript:;'>&lt;</a><img src='' /><a class='next' href='javascript:;'>&gt;</a><p class='info'><span id='currNum'>0</span>/<span id='totalNum'>1</span><p></div>";
		wrap.style.top = (document.body.scrollTop || document.documentElement.scrollTop) + "px";
		document.body.appendChild(wrap);
		this.showWrap = wrap;
		events.addEvent($class("close",wrap),"click",function(){
			that.hide();
		});
		events.addEvent($class("prev",wrap),"click",function(){
			that.show(that.curr - 1);
		});
		events.addEvent($class("next",wrap),"click",function(){
			that.show(that.curr - 0 + 1);
		});
	},
	show : function(i){
		addClass(document.body,"lightFull");
		var totalNum = this.pics.length;
		if(!this.showWrap) this.create();
		this.showWrap.style.display = "block";
		this.showWrap.style.top = (document.body.scrollTop || document.documentElement.scrollTop) + "px";
		$tag("img",this.showWrap)[0].src = this.pics[i].src;
		$class("prev",this.showWrap)[0].style.visibility = "visible";
		$class("next",this.showWrap)[0].style.visibility = "visible";
		if(i == 0)  $class("prev",this.showWrap)[0].style.visibility = "hidden";
		if(i == totalNum-1)  $class("next",this.showWrap)[0].style.visibility = "hidden";
		this.curr = i;
		$id("currNum").innerHTML = this.curr - 0 + 1;
		$id("totalNum").innerHTML = totalNum;
		$class("info")[0].style.display = (totalNum > 1) ? "block" : "none";
		var cont = $class("cont",this.showWrap)[0],
			_h = document.documentElement.clientHeight - $tag("img",this.showWrap)[0].offsetHeight - 40;
		cont.style.paddingTop = _h/2 + "px";
		cont.style.paddingBottom = _h/2 + "px";
	},
	hide : function(){
		this.showWrap.style.display = "none";	
		removeClass(document.body,"lightFull");
	}
};

events.addEvent(window,"load",function(){
	new LightBox();
});
*/

function i_encodeHTML(source){
	source = source.replace(/&/g,'&amp;')
					.replace(/</g,'&lt;')
					.replace(/>/g,'&gt;')
					.replace(/\\/g,'&#92;')
					.replace(/"/g,'&quot;')
					.replace(/'/g,'&#39;');
	return source;
}

var util = (function() {
	var $win = $(window);
	var $doc = $(document);

	//cookie
	(function ($) {
		var pluses = /\+/g;
		function encode(s) {
			return config.raw ? s : encodeURIComponent(s);
		}
		function decode(s) {
			return config.raw ? s : decodeURIComponent(s);
		}
		function stringifyCookieValue(value) {
			return encode(config.json ? JSON.stringify(value) : String(value));
		}
		function parseCookieValue(s) {
			if (s.indexOf('"') === 0) {
				// This is a quoted cookie as according to RFC2068, unescape...
				s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
			}
			try {
				// Replace server-side written pluses with spaces.
				// If we can't decode the cookie, ignore it, it's unusable.
				s = decodeURIComponent(s.replace(pluses, ' '));
			} catch(e) {
				return;
			}
			try {
				// If we can't parse the cookie, ignore it, it's unusable.
				return config.json ? JSON.parse(s) : s;
			} catch(e) {}
		}
		function read(s, converter) {
			var value = config.raw ? s : parseCookieValue(s);
			return $.isFunction(converter) ? converter(value) : value;
		}
		var config = $.cookie = function (key, value, options) {
			if (value !== undefined && !$.isFunction(value)) {
				options = $.extend({}, config.defaults, options);
				if (typeof options.expires === 'number') {
					var days = options.expires, t = options.expires = new Date();
					t.setDate(t.getDate() + days);
				}
				return (document.cookie = [
					encode(key), '=', stringifyCookieValue(value),
					options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
					options.path    ? '; path=' + options.path : '',
					options.domain  ? '; domain=' + options.domain : '',
					options.secure  ? '; secure' : ''
				].join(''));
			}
			var result = key ? undefined : {};
			var cookies = document.cookie ? document.cookie.split('; ') : [];
			for (var i = 0, l = cookies.length; i < l; i++) {
				var parts = cookies[i].split('=');
				var name = decode(parts.shift());
				var cookie = parts.join('=');
				if (key && key === name) {
					result = read(cookie, value);
					break;
				}
				if (!key && (cookie = read(cookie)) !== undefined) {
					result[name] = cookie;
				}
			}
			return result;
		};
		config.defaults = {};
		$.removeCookie = function (key, options) {
			if ($.cookie(key) !== undefined) {
				$.cookie(key, '', $.extend({}, options, { expires: -1 }));
				return true;
			}
			return false;
		};
	})($);
	
	var template = (function () {
		var noMatch = /(.)^/;
		var escapes = {
			"'" : "'",
			'\\' : '\\',
			'\r' : 'r',
			'\n' : 'n',
			'\t' : 't',
			'\u2028' : 'u2028',
			'\u2029' : 'u2029'
		};
		var escaper = /\\|'|\r|\n|\t|\u2028|\u2029/g;
		var settings = {
			evaluate : /<@([\s\S]+?)@>/g,
			interpolate : /<@=([\s\S]+?)@>/g,
			escape : /<@-([\s\S]+?)@>/g
		};
		
		var matcher = new RegExp([(settings.escape || noMatch).source, (settings.interpolate || noMatch).source, (settings.evaluate || noMatch).source].join('|') + '|$', 'g');
		
		return function(text, data) {
			var render;
			var index = 0;
			var source = "__p+='";
			text.replace(matcher, function(match, escape, interpolate, evaluate, offset) {
				source += text.slice(index, offset).replace(escaper, function(match) {
					return '\\' + escapes[match];
				});
				if(escape) {
					source += "'+\n((__t=(" + escape + "))==null?'':_.escape(__t))+\n'";
				}
				if(interpolate) {
					source += "'+\n((__t=(" + interpolate + "))==null?'':__t)+\n'";
				}
				if(evaluate) {
					source += "';\n" + evaluate + "\n__p+='";
				}
				index = offset + match.length;
				return match;
			});
			source += "';\n";
			if (!settings.variable)
				source = 'with(obj||{}){\n' + source + '}\n';
			source = "var __t,__p='',__j=Array.prototype.join," + "print=function(){__p+=__j.call(arguments,'');};\n" + source + "return __p;\n";
			
			try {
				render = new Function(settings.variable || 'obj', source);
			} catch (e) {
				e.source = source;
				throw e;
			}
			
			if(data)
				return render(data);
			var template = function(data) {
				return render.call(this, data);
			};
			template.source = 'function(' + (settings.variable || 'obj') + '){\n' + source + '}';
			return template;
		};
	})();
	
	var getParam = function (search) {
		search = search.replace(/#.+$/,'');
		var re = {};
		if (search == "" || typeof search == "undefined") {
			return {};
		} else {
			search = search.substr(1).split('&');
			for (var i = 0, len = search.length; i < len; i++) {
				var tmp = search[i].split('=');
				if(i == 0 && tmp.length == 1) {//?132141
					return {
						'__search__' : tmp[0]
					};
				}
				re[tmp.shift()] = tmp.join('=');
			}
			return re;
		}
	};
	var _param = getParam(window.location.search);
	
	
	var getCursor = function(elm) {
		var _this = this;
		var start = 0,
			end = 0;
		var rangeData = {
			start: 0,
			end: 0,
			text: ""
		};
		if (typeof(elm.selectionStart) == "number") { //W3C
			rangeData.start = elm.selectionStart; //光标起始位置
			rangeData.end = elm.selectionEnd; //光标末尾位置
			rangeData.text = elm.value.substring(0, elm.selectionStart); //获取文本框value
		} else if (document.selection) { //IE
			/*
			var sRange = document.selection.createRange();
			var oRange = document.body.createTextRange();
			oRange.moveToElementText(this.elem);
			rangeData.text = sRange.text;
			rangeData.bookmark = sRange.getBookmark();
			for (var i = 0; oRange.compareEndPoints("StartToStart", sRange) < 0 && sRange.moveStart("character", -1) !== 0; i++) {
				if (this.elem.value.charAt(i) == '\r' || this.elem.value.charAt(i) == '\n') {
					i++; //IE的特殊处理，遇到enter键需要加1
				}
			}
			rangeData.start = i;
			rangeData.end = rangeData.text.length + rangeData.start;
			rangeData.text = this.elem.value.substring(0, i);
			*/
			var range = document.selection.createRange();
			if (range.parentElement().id == elm.id) {
				var range_all = document.body.createTextRange();
				range_all.moveToElementText(elm);
				for (start = 0; range_all.compareEndPoints("StartToStart", range) < 0; start++)
					range_all.moveStart('character', 1);
				for (var i = 0; i <= start; i++) {
					if (elm.value.charAt(i) == '\n') start++;
				}
				var range_all = document.body.createTextRange();
				range_all.moveToElementText(elm);
				for (end = 0; range_all.compareEndPoints('StartToEnd', range) < 0; end++)
					range_all.moveStart('character', 1);
				for (var i = 0; i <= end; i++) {
					if (elm.value.charAt(i) == '\n' || this.elem.value.charAt(i) == '\r') end++;
				}
			}
			rangeData.start = start;
			rangeData.end = rangeData.text.length + rangeData.start;
			rangeData.text = elm.value.substring(0, end);
		}
		return rangeData;
	};
	var setCursor = function(elm, start, end) { //设置光标
		if (elm.setSelectionRange) { //W3C
			elm.setSelectionRange(start, end);
		} else if (elm.createRange) { //IE
			var range = elm.createRange();
			if (elm.value.length == rangeData.start) {
				range.collapse(false);
				range.select();
			} else {
				range.moveToBookmark(rangeData.bookmark);
				range.select();
			}
		}
	};
	var moveCursor = function (elm, start) {
		start = start || 0;
		if (elm.setSelectionRange) {
			setTimeout(function() {
				elm.setSelectionRange(start, start);
				elm.focus();
			}, 0);
		}else if (elm.createTextRange) {
			var range = elm.createTextRange();
			range.collapse(true);
			range.moveEnd('character', start);
			range.moveStart('character', start);
			range.select();
		}
	};
	
	var addText = function (elm, str, nStart, nLen) {
		nLen = nLen || 0;
		nStart = nStart || getCursor(elm).start;
		elm.focus();
		if (elm.setSelectionRange) { //W3C
			_tValue = elm.value; //获取文本框内容
			var _start = nStart - nLen, //设置光标起点  光标的位置-离@的文本长度
				_end = _start + str.length, //设置光标末尾，start+数据文字长度
				_value = _tValue.substring(0, _start) + str + _tValue.substring(nStart, elm.value.length);
			elm.value = _value;
			setCursor(elm, _end, _end);
		} else if (elm.createTextRange) {
			var range = document.selection.createRange();
			range.moveStart("character", -nLen); //移动光标
			range.text = str;
			range.select();
		}
	};
	
	var restCount = (function () {
		var enterRegIE = /\r\n/gim;
		var enterRegFF = /\n/gim;
		var maxWordCount = 150;
		var _html = function ($elm, rest, max) {
			$elm.html((max - rest) + '/' + max);
		}
		var getCount = function (elm, max) {
			max = max || maxWordCount;
			var val;
			if (UA.isIE) {
				val = elm.value.replace(enterRegIE, "*");
			} else {
				val = elm.value.replace(enterRegFF, "*");
			}
			var rest = max - val.length;
			if (rest < 0) {
				elm.value = elm.value.substring(0, max);
				rest = 0;
			}
			return rest;
		};
		return function ($textarea, $elm, max, html) {
			var rest = getCount($textarea.get(0), max);
			html = html || _html;
			html($elm, rest, maxWordCount);
		}
	})();
	
	var smile = (function () {
		var _map = {
			"微笑":"weixiao",
			"呲牙":"ciya",
			"再见":"zaijian",
			"偷笑":"touxiao",
			"调皮":"tiaopi",
			"大哭":"daku",
			"擦汗":"cahan",
			"猪头":"zhutou",
			"得意":"deyi",
			"傲慢":"aoman",
			"发怒":"fanu",
			"害羞":"haixiu",
			"憨笑":"hanxiao",
			"汗":"han",
			"尴尬":"ganga",
			"抓狂":"zhuakuang",
			"花":"hua",
			"惊恐":"jingkong",
			"惊讶":"jingya",
			"可爱":"keai",
			"抠鼻":"koubi",
			"酷":"ku",
			"流泪":"liulei",
			"便便":"bianbian",
			"难过":"nanguo",
			"撇嘴":"piezui",
			"敲打":"qiaoda",
			"亲亲":"qinqin",
			"色":"se",
			"胜利":"shengli",
			"示爱":"shiai",
			"白眼":"baiyan",
			"耍酷":"shuaku",
			"衰":"shuai",
			"睡":"shui",
			"鄙视":"bishi",
			"吐":"tu",
			"嘘":"xu",
			"委屈":"weiqu",
			"炸弹":"zhadan",
			"心":"xin",
			"心裂":"xinlie",
			"菜刀":"caidao",
			"疑问":"yiwen",
			"阴险":"yinxian"
		};
		var $smileBox, $smileBtn;
		
		var imgReg = /\[(\W[^\[]*)\]/gim;
		var returnReg = /\n/g;
		var unicodeReg = /&#(\d+);/g
		var opt = {
			root: KD_RRT.root,
			decode: function (str) {
				 return str.replace(imgReg, function(all, k){
					k = k.replace(unicodeReg, function (all, code) {
						return String.fromCharCode(code);
					})
					if (_map[k]) {
						return '<img src="' + opt.root + '/public/img/face/' + _map[k] + '.gif" title="'+ k +'"/>';
					}
					return all;
				 }).replace(returnReg, "<br/>");
				 return content;
			},
			encode: function(key)
			{
				// var imgReg =   /<img.*title=\"?([^\s]*)(?=(\s|\")).*?>/gim;
				// content = content.replace(imgReg,"[$1]");
				// return content;
				return "[" + key + "]";
			},
			restCount: function ($target, $text) {
				restCount($target, $text);
			}
		};
		
		return {
			text2HTML: function (text) {
				text=text||'';
				return opt.decode(text);
			},
			initBox: function () {
				if (!$smileBox) {
					var self = this;
					var html = ["<div style='display:none;' id='smileBox'><ul>"];
					for (var k in _map) {
						html.push('<li data-smileMsg="'+ k +'"><img src="' + opt.root + '/public/img/face/' + _map[k] + '.gif" title="'+ k +'"/></li>');
					};
					html.push('</ul>');
					if (UA.isIE6) {
						html.push('<iframe id="msgFace_ie6" frameborder="0" scrolling="no"></iframe>');
					};
					html.push('</div>');
					$smileBox = $(html.join('')).appendTo(document.body);
					$smileBox.on('click', 'li', function (event) {
						self.add(this);
						event.stopPropagation();
					});
					$doc.on('click', this.hide);
				} 
			},
			init: function () {
				$doc.on('click', '.kd_smile_btn', function (event) {
					smile.show($(this));
					event.stopPropagation();
				});
				smile.init = function () {}
			},
			add: function (li) {
				var target = $smileBtn.attr('data-target');
				var countText = $smileBtn.attr('data-count');
				var $target = target? $(target): $smileBtn.data('smile-target');
				if ($target.length > 0) {
					addText($target[0], opt.encode($(li).attr('data-smileMsg')));
					if (countText) {
						opt.restCount($target, $(countText));
					}
					//add(that.elem, shortUrl, cursor.start, 0).getCount();
				}
				this.hide();
			},
			show: function ($elm, _opt) {
				$.extend(opt, _opt||{});
				$smileBtn = $elm;
				this.initBox();
				var offset =  $elm.offset();
				$smileBox.css({
					display: "block",
					top: offset.top - 198,
					left: offset.left
				})
			},
			hide: function () {
				$smileBox.hide();
			}
		}
	})();
	
	
	var runLoad = function (opts) {
		var callList = [];
		var state = 'init';
		return {
			load: function (callback) {
				if (state == 'load') {
					callback && callback(this.data);
				} else if (state == 'init') {
					state = 'loading';
					callback && callList.push(callback);
					opts.fun.call(this);
				} else if (state == 'loading') {
					callback && callList.push(callback);
				}
			},
			init: function () {
				state = 'init';
			},
			done: function (data) {
				this.data = data;
				state = 'load';
				if (callList.length > 0) {
					for (var i = 0, len = callList.length; i < len ; i++) {
						callList[i](this.data);
					}
					callList = [];
				}
			}
		}
	};
		
	var loadScript = function (url, callback) {
		var doc = document;
		var head = doc.head || doc.getElementsByTagName("head")[0] || doc.documentElement;
		var node  = document.createElement("script"); 

		var supportOnload = "onload" in node;
		if (supportOnload) {
			node.onload = onload;
			node.onerror = function() {
				onload()
			};
		} else {
			node.onreadystatechange = function() {
				if (/loaded|complete/.test(node.readyState)) {
					onload();
				}
			}
		}
		function onload() {
			node.onload = node.onerror = node.onreadystatechange = null
			head.removeChild(node)
			node = null;
			if (callback) callback()
		};
		node.async = true;
		node.src = url; 
		head.appendChild(node);
	};
	//URL特殊字符替换
	var specialCharactersHandle = function (str){
		str = str.replace(/\%/g,"%25");
		str = str.replace(/\#/g,"%23");
		str = str.replace(/\&/g,"%26");
		return str;
	}
	
	return {
		specialCharactersHandle: specialCharactersHandle,
		moveCursor: moveCursor,
		restCount: restCount,
		smile: smile,
		template: template,
		getParam: getParam,
		_param: _param,
		runLoad: runLoad,
		loadScript: loadScript
	}
})();
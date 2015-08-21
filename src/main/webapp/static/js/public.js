/**
 * 下拉联动
 * @param $
 */
(function($){
	$.getValue = function(jsonValue){	
		jsonValue.target.empty();	
		$.post(jsonValue.url,{parentId:jsonValue.parentId},function(data){
			var $option1 = $("<option/>") ;
			$option1.attr("value","");
			$option1.text("-- 请选择 --");
			jsonValue.target.append($option1);
			var data = eval("("+data+")") ;
			for(var i=0;i<data.length;i++){
				var $option = $("<option/>") ;
				$option.attr("value",data[i].id);
				$option.text(data[i].name);
				jsonValue.target.append($option);
			}
		});		
	};
})($) ;
/**
 * 对json数据排序
 * @param filed
 * @param rev
 * @param primer
 * @returns
 */
var sortBy = function (filed, rev, primer) {
	rev = (rev) ? -1 : 1;
	return function (a, b) {
		a = a[filed];
		b = b[filed];
		if (typeof (primer) != 'undefined') {
			a = primer(a);
			b = primer(b);
		}
		if (a < b) { return rev * -1; }
		if (a > b) { return rev * 1; }
		return 1;
	}
};

//datagrid查询无结果处理
function noRecordsView(target){
	//var opts = $(target).datagrid('options');
	var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
	//vc.children('div.datagrid-empty').remove();
	if (!$(target).datagrid('getRows').length){
		var d = $('<div class="datagrid-empty"></div>').html("未搜索到相关内容！").appendTo(vc);
		d.css({
			position:'absolute',
			left:0,
			top:50,
			width:'100%',
			textAlign:'center'
		});
	} 
}

// resize easy ui
$(window).resize(function() {  
	var $list = $("#list");
	if ($list.length != 0 && !!$list.datagrid) {
		$list.datagrid("resize");
	}
});

//转换成html标签
function encodeHTML(source){
	source = source.replace('&lt;','<').replace('&gt;','>').replace('&quot;','"').replace('&#39;','\'').replace('&amps;','&');
	return source;
}

// 弹出窗口
function openwin(wid,url,title,width,height,modal,left,top){
	var windiv = document.createElement('div');
	windiv.id = wid;
	document.body.appendChild(windiv);
	windiv.innerHTML = '<iframe src="'+url+'" frameborder="0"  style="overflow: no; overflow-x:hidden;padding: 0px; margin: 0px; valign: top;width:100%;height:100%" id="'+wid+'_frame" name="'+wid+'_frame" scolling="no" noresize="noresize"></iframe>';
 	$('#'+wid).window({
		title: title,
		left:left,
		top:top,
		width: width,
		height: height,
		modal: modal,
		draggable:true,
		shadow: true,//阴影
		closed: false,
		closable:true,
		collapsible:false,
		minimizable:false,
		maximizable:true,
		inline:false,
		onClose:function(){
			var temp = windiv.parentNode;
			document.body.removeChild(temp);
		}
	});
}

//关闭窗口
function closewin(wid) {
	$('#'+wid).window('close',true);
	parent.window.$("#list").datagrid('reload');
}

var initComboboxData='[{"id":"","districtName":"--请选择--","selected":true}]';
var initComboboxDataJson=eval("("+initComboboxData+")");

function bindProvinceCombobox(path,provinceTarget,cityTarget,xianTarget){
	$("#"+provinceTarget).combobox({
		url:path+"/admin/district/queryBaseDistrictByParentId.do?id=-1",
		valueField:'id',
		textField:'districtName',
		editable:false,
		onChange:function(newValue,oldValue){//联动城市
			reloadCity(path,cityTarget,newValue);
			reloadXian(path,xianTarget,"");
		}
	}); 
	
}
 
function bindCityCombobox(path,cityTarget,xianTarget){
	 $("#"+cityTarget).combobox({
			valueField:'id',
			textField:'districtName',
			data:initComboboxDataJson,
			editable:false,
			onChange:function(newValue,oldValue){//联动县
				reloadXian(path,xianTarget,newValue);
			}
		});
 }
function bindXianCombobox(target){
	$("#"+target).combobox({
		valueField:'id',
		textField:'districtName',
		data:initComboboxDataJson,
		editable:false
	});
}

/**
 * reload city
 */
function reloadCity(path,target,provinceId){
	var url = path+"/admin/common/getDistrictByParentId.do?parentId="+provinceId;
	$("#"+target).combobox("clear");
	if(provinceId==""){
		$("#"+target).combobox("loadData",initComboboxDataJson);
		}else{
			$("#"+target).combobox("reload",url);
			}
}
/**
 * reload xian
 */
function reloadXian(path,target,cityId){
	var url = path+"/admin/common/getDistrictByParentId.do?parentId="+cityId;
	$("#"+target).combobox("clear");
	if(cityId==""){
		$("#"+target).combobox("loadData",initComboboxDataJson);
		}else{
			$("#"+target).combobox("reload",url);
			}
}
/**
 * 根据level初始化
 */
function initDistrict(path,target,level){
	var url = path+"/admin/common/getBaseDistrictByLevel.do?level="+level;
	$("#"+target).combobox("reload",url);
}


// 转换成html标签2
function encodeHTML2(str) {
	str = str.replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace('&quot;', '"').replace('&#39;', '\'').replace(/&amp;/g, "&");
	return str;
}
function deEncodeHTML2(str){
	str = str.replace(/&/g, "&amp;").replace('\'', '&#39;').replace('"', '&quot;').replace(/</g, "&lt;").replace(/>/g, "&gt;");
	return str;
}
// URL特殊字符替换
function specialCharactersHandle(str) {
	str = str.replace(/\&/g, "&amp;").replace(/\</g, "&lt;").replace(/\>/g, "&gt;").replace('/\'/g','&#39').replace('/\"/g','&quot;');
	return str;
}
//根据val推出前后Math.floor(val/2)范围的入学年份
function getJoinedYear(val){
	var jsonObject = {
			data: [] 
		};
	var date = new Date();
	var year = date.getFullYear();
	for(var i=0;i<=val;i++){
		var joinedYear = year-Math.floor(val/2)+i+"级";
		jsonObject.data.push({id:joinedYear,text:joinedYear});
	}
	jsonObject.data.unshift({
		"id":"",
		"text":"--请选择--",
		"selected":true
	});
	var str = JSON.stringify(jsonObject.data);
	return str;
}
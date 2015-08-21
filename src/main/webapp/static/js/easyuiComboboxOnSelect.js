//add by wangqiqi easyui下拉框处理特殊字符显示
$(function(){
	$(".easyui-combobox").each(function(){
		$(this).combobox({
			onSelect:function(record){
				var opts = $(this).combobox('options');
				var fieldName = opts.textField;
				var newVal = record[fieldName];
				newVal = encodeHTML2(newVal);
				$(this).combobox("setText",newVal);
			}
		});
	});
});
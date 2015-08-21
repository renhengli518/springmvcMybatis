/**关联分类*/
function onChangeSemester(newValue, oldValue){
	if (newValue != ""){
		$.get(ctx + "/skeleton/getClasslevels.do?semesterId=" + newValue, function(result){
			result.unshift({"id":"", "classLevelName":"请选择"});
			$("#classlevelList").combobox("loadData", result);
		}, "json");
	} else {
		$("#classlevelList").combobox("loadData", [{"id":"", "classLevelName":"请选择"}]);
	}
	$("#classlevelList").combobox("setValue", "");
}

function onChangeSemesterEdit(newValue, callback){
	if (newValue != ""){
		$.get(ctx + "/skeleton/getClasslevels.do?semesterId=" + newValue, function(result){
			result.unshift({"id":"", "classLevelName":"请选择"});
			$("#classlevelList").combobox("loadData", result);
			if(callback) callback();
		}, "json");
	} else {
		$("#classlevelList").combobox("loadData", [{"id":"", "classLevelName":"请选择"}]);
	}
}

function onChangeClassLevel(newValue, oldValue){
	if (newValue != ""){
		$.get(ctx + "/skeleton/getDisciplines.do?classlevelId=" + newValue, function(result){
			result.unshift({"id":"", "disciplineName":"请选择"});
			$("#disciplineList").combobox("loadData", result);
		}, "json");
	} else {
		$("#disciplineList").combobox("loadData", [{"id":"", "disciplineName":"请选择"}]);
	}
	$("#disciplineList").combobox("setValue", "");
}

function onChangeClassLevelEdit(newValue, callback){
	if (newValue != ""){
		$.get(ctx + "/skeleton/getDisciplines.do?classlevelId=" + newValue, function(result){
			result.unshift({"id":"", "disciplineName":"请选择"});
			$("#disciplineList").combobox("loadData", result);
			if(callback) callback();
		}, "json");
	} else {
		$("#disciplineList").combobox("loadData", [{"id":"", "disciplineName":"请选择"}]);
	}
}

function onChangeDiscipline(newValue, oldValue){
	if (newValue != ""){
		var classlevelId = $("#classlevelList").combobox("getValue");
		$.get(ctx + "/skeleton/getVersions.do?classlevelId=" + classlevelId + "&disciplineId=" + newValue, function(result){
			result.unshift({"id":"", "name":"请选择"});
			$("#versionList").combobox("loadData", result);
		}, "json");
	} else {
		$("#versionList").combobox("loadData", [{"id":"", "name":"请选择"}]);
	}
	$("#versionList").combobox("setValue", "");
}

function onChangeDisciplineEdit(newValue, callback){
	if (newValue != ""){
		var classlevelId = $("#classlevelList").combobox("getValue");
		$.get(ctx + "/skeleton/getVersions.do?classlevelId=" + classlevelId + "&disciplineId=" + newValue, function(result){
			result.unshift({"id":"", "name":"请选择"});
			$("#versionList").combobox("loadData", result);
			if(callback) callback();
		}, "json");
	} else {
		$("#versionList").combobox("loadData", [{"id":"", "name":"请选择"}]);
	}
}

function onChangeVersion(newValue, oldValue) {
	if (newValue != ""){
		var classlevelId = $("#classlevelList").combobox("getValue");
		var disciplineId = $("#disciplineList").combobox("getValue");
		$.get(ctx + "/skeleton/getFascicles.do?classlevelId=" + classlevelId + "&disciplineId=" + disciplineId + "&versionId=" + newValue, function(result){
			result.unshift({"id":"", "catalogName":"请选择"});
			$("#fascicleList").combobox("loadData", result);
		}, "json");
	} else {
		$("#fascicleList").combobox("loadData", [{"id":"", "catalogName":"请选择"}]);
	}
	$("#fascicleList").combobox("setValue", "");
}

function onChangeVersionEdit(newValue, callback) {
	if (newValue != ""){
		var classlevelId = $("#classlevelList").combobox("getValue");
		var disciplineId = $("#disciplineList").combobox("getValue");
		$.get(ctx + "/skeleton/getFascicles.do?classlevelId=" + classlevelId + "&disciplineId=" + disciplineId + "&versionId=" + newValue, function(result){
			result.unshift({"id":"", "catalogName":"请选择"});
			$("#fascicleList").combobox("loadData", result);
			if(callback) callback();
		}, "json");
	} else {
		$("#fascicleList").combobox("loadData", [{"id":"", "catalogName":"请选择"}]);
	}
}

function onChangeFascicle(newValue, oldValue) {
	if (newValue != ""){
		$.get(ctx + "/skeleton/getCatalogs.do?parentId=" + newValue, function(result){
			result.unshift({"id":"", "catalogName":"请选择"});
			$("#chapterList").combobox("loadData", result);
		}, "json");
	} else {
		$("#chapterList").combobox("loadData", [{"id":"", "catalogName":"请选择"}]);
	}
	$("#chapterList").combobox("setValue", "");
}

function onChangeFascicleEdit(newValue, callback) {
	if (newValue != ""){
		$.get(ctx + "/skeleton/getCatalogs.do?parentId=" + newValue, function(result){
			result.unshift({"id":"", "catalogName":"请选择"});
			$("#chapterList").combobox("loadData", result);
			if(callback) callback();
		}, "json");
	} else {
		$("#chapterList").combobox("loadData", [{"id":"", "catalogName":"请选择"}]);
	}
}

function onChangeChapter(newValue, oldValue) {
	if (newValue != ""){
		$.get(ctx + "/skeleton/getCatalogs.do?parentId=" + newValue, function(result){
			result.unshift({"id":"", "catalogName":"请选择"});
			$("#partList").combobox("loadData", result);
		}, "json");
	} else {
		$("#partList").combobox("loadData", [{"id":"", "catalogName":"请选择"}]);
	}
	$("#partList").combobox("setValue", "");
}

function onChangeChapterEdit(newValue, callback) {
	if (newValue != ""){
		$.get(ctx + "/skeleton/getCatalogs.do?parentId=" + newValue, function(result){
			result.unshift({"id":"", "catalogName":"请选择"});
			$("#partList").combobox("loadData", result);
			if(callback) callback();
		}, "json");
	} else {
		$("#partList").combobox("loadData", [{"id":"", "catalogName":"请选择"}]);
	}
}

function onResourceCatalogChange(newValue, oldValue){
	if (oldValue != "")
		removeResourceCatalogCombobox(oldValue);
	if (newValue != ""){
		$.get(ctx + "/skeleton/getResourceCatalogs.do?parentId=" + newValue, function(result){
			if (result.length>0){
				if($("#resourceCatalog" + newValue).length == 0){
					$("#resourceCatalogContainer").append("<select id='resourceCatalog" + newValue + "' class='resourceCatalogSecond' style='width:100px;' data-options='editable:false, valueField:\"id\", textField:\"catalogName\"'/> ");
					$("#resourceCatalog" + newValue).combobox({
						onChange:function(newValue, oldValue){
							// 资源分类只有两级
							//onResourceCatalogChange(newValue, oldValue);
						},
						onSelect:function(record){
							var opts = $(this).combobox('options');
							var fieldName = opts.textField;
							var newVal = record[fieldName];
							newVal = encodeHTML2(newVal);
							$(this).combobox("setText",newVal);
						}
					});
				}
				result.unshift({"id":"", "catalogName":"请选择"});
				$("#resourceCatalog" + newValue).combobox("loadData", result);
				var initResourceCatalogSecondId = $("#initResourceCatalogSecondId").val();
				if (initResourceCatalogSecondId == '1') {
					$("#resourceCatalog" + newValue).combobox('setValue', $("#resourceCatalogSecondId").val());
					$("#initResourceCatalogSecondId").val("0");
				}
			}
		}, "json");
	}
}

function onResourceCatalogChangeEdit(newValue,oldValue,callback){
	if (oldValue != "")
		removeResourceCatalogCombobox(oldValue);
	if (newValue != ""){
		$.get(ctx + "/skeleton/getResourceCatalogs.do?parentId=" + newValue, function(result){
			if (result.length>0){
				if($("#resourceCatalog" + newValue).length == 0){
					$("#resourceCatalogContainer").append("<select id='resourceCatalog" + newValue + "' class='resourceCatalogSecond' style='width:100px;' data-options='editable:false, valueField:\"id\", textField:\"catalogName\"'/> ");
					$("#resourceCatalog" + newValue).combobox({
						onChange:function(newValue, oldValue){
							// 资源分类只有两级
							//onResourceCatalogChange(newValue, oldValue);
						},
						onSelect:function(record){
							var opts = $(this).combobox('options');
							var fieldName = opts.textField;
							var newVal = record[fieldName];
							newVal = encodeHTML2(newVal);
							console.log("test");
							$(this).combobox("setText",newVal);
						}
					});
				}
				result.unshift({"id":"", "catalogName":"请选择"});
				$("#resourceCatalog" + newValue).combobox("loadData", result);
				var initResourceCatalogSecondId = $("#initResourceCatalogSecondId").val();
				if (initResourceCatalogSecondId == '1') {
					$("#resourceCatalog" + newValue).combobox('setValue', $("#resourceCatalogSecondId").val());
					$("#initResourceCatalogSecondId").val("0");
				}
				if(callback)callback();
			}
		}, "json");
	}
}

function removeResourceCatalogCombobox(id){
	if($("#resourceCatalog" + id).length > 0){
		var value = $("#resourceCatalog" + id).combobox("getValue");
		removeResourceCatalogCombobox(value);
		$("#resourceCatalog" + id).combobox("destroy");
	}
}

function formatResourceCatalogId(id, val){
	if($("#" + id).length > 0){
		var value = $("#" + id).combobox("getValue");
		if (value != ""){
			return formatResourceCatalogId("resourceCatalog" + value, value);
		}else{
			return val;
		}
	}else{
		return val;
	}
}



/**BaseCatalog分类*/
function onCatalogChange(newValue, oldValue){
	if (oldValue != "")
		removeCatalogCombobox(oldValue);
	if (newValue != ""){
		$.get(ctx + "/skeleton/getCatalogs.do?parentId=" + newValue + "&type=" + catalogType, function(result){
			if (result.length>0){
				if($("#catalog" + newValue).length == 0){
					$("#catalogContainer").append("<select id='catalog" + newValue + "' style='width:100px;' data-options='editable:false, valueField:\"id\", textField:\"catalogName\"'/> ");
					$("#catalog" + newValue).combobox({
						onChange:function(newValue, oldValue){
							onCatalogChange(newValue, oldValue);
						}
					});
				}
				result.unshift({"id":"", "catalogName":"请选择"});
				$("#catalog" + newValue).combobox("loadData", result);
			}
		}, "json");
	}
}

function removeCatalogCombobox(id){
	if($("#catalog" + id).length > 0){
		var value = $("#catalog" + id).combobox("getValue");
		removeCatalogCombobox(value);
		$("#catalog" + id).combobox("destroy");
	}
}

function formatCatalogId(id, val){
	if($("#" + id).length > 0){
		var value = $("#" + id).combobox("getValue");
		if (value != ""){
			return formatCatalogId("catalog" + value, value);
		}else{
			return val;
		}
	}else{
		return val;
	}
}



/**知识点选择*/
function removeKnowledgeCombobox(id){
	if($("#knowledge" + id).length > 0){
		var value = $("#knowledge" + id).combobox("getValue");
		removeKnowledgeCombobox(value);
		$("#knowledge" + id).combobox("destroy");
	}
}

function onKnowledgeChange(newValue, oldValue){
	if (oldValue != "")
		removeKnowledgeCombobox(oldValue);
	if (newValue != ""){
		$.get(ctx + "/skeleton/getKnowledges.do?parentId=" + newValue, function(result){
			if (result.length>0){
				if($("#knowledge" + newValue).length == 0){
					$("#knowledgeContainer").append("<select id='knowledge" + newValue + "' style='width:100px;' data-options='editable:false, valueField:\"id\", textField:\"knowledgeName\"'/> ");
					$("#knowledge" + newValue).combobox({
						onChange:function(newValue, oldValue){
							onKnowledgeChange(newValue, oldValue);
						},
						onSelect:function(record){
							var opts = $(this).combobox('options');
							var fieldName = opts.textField;
							var newVal = record[fieldName];
							newVal = encodeHTML2(newVal);
							$(this).combobox("setText",newVal);
						}
					});
				}
				result.unshift({"id":"", "knowledgeName":"请选择"});
				$("#knowledge" + newValue).combobox("loadData", result);
			}
		}, "json");
	}
}

function onChangeSemesterKnowledge(){
	var semesterId = $("#semesterAllList").combobox("getValue");
	var disciplineId = $("#disciplineAllList").combobox("getValue");
	
	if (semesterId != null && semesterId != '' && disciplineId!= null && disciplineId != ''){
		$.get(ctx + "/skeleton/getRootKnowledges.do?semesterId=" + semesterId + "&disciplineId=" + disciplineId, function(result){
			result.unshift({"id":"", "knowledgeName":"请选择"});
			$("#knowledgeRoot").combobox("loadData", result);
		}, "json");
	} else {
		$("#knowledgeRoot").combobox("loadData", [{"id":"", "knowledgeName":"请选择"}]);
	}
	$("#knowledgeRoot").combobox("setValue", "");
}

function onChangeDisciplineKnowledge(){
	var semesterId = $("#semesterAllList").combobox("getValue");
	var disciplineId = $("#disciplineAllList").combobox("getValue");
	
	if (semesterId != null && semesterId != '' && disciplineId!= null && disciplineId != ''){
		$.get(ctx + "/skeleton/getRootKnowledges.do?semesterId=" + semesterId + "&disciplineId=" + disciplineId, function(result){
			result.unshift({"id":"", "knowledgeName":"请选择"});
			$("#knowledgeRoot").combobox("loadData", result);
		}, "json");
	} else {
		$("#knowledgeRoot").combobox("loadData", [{"id":"", "knowledgeName":"请选择"}]);
	}
	$("#knowledgeRoot").combobox("setValue", "");
}

function addKnowledge(){
	if($("#knowledgeSelected .smallBlock").length >=3){
		Win.alert('最多只能添加三个关联知识点！');
		return ;
	}
	if ($("#semesterAllList").combobox("getValue") == "" ||
		$("#disciplineAllList").combobox("getValue") == "" ||
		$("#knowledgeRoot").combobox("getValue") == ""){
		Win.alert('请先选择知识点!');
	} else {
		var text = deEncodeHTML2($("#semesterAllList").combobox("getText"));
		text = text + "--&gt;" + deEncodeHTML2($("#disciplineAllList").combobox("getText"));
		text = formatKnowledgeName("knowledgeRoot", text);
		var id = formatKnowledgeId("knowledgeRoot", -1);
		
		if($("#knowledgeTag" + id).length > 0){
			Win.alert('该知识点已经被添加!');
			return;
		}
		$("#knowledgeSelected").append("<span class='smallBlock chapBlock' id='knowledgeTag" + id + "' knowledgeId='" + id + "'>" 
				+ text + "<a href='javascript:deleteKnowledge(\"" + id + "\")'></a></span>");
	}
}

function deleteKnowledge(id){
	if($("#knowledgeTag" + id).length > 0){
		$("#knowledgeTag" + id).remove();
	}
}

function formatKnowledgeId(id, val){
	if($("#" + id).length > 0){
		var value = $("#" + id).combobox("getValue");
		if (value != ""){
			return formatKnowledgeId("knowledge" + value, value);
		}else{
			return val;
		}
	}else{
		return val;
	}
}

function formatKnowledgeName(id, name){
	var text = $("#" + id).combobox("getText");
	var value = $("#" + id).combobox("getValue");
	if (value != ""){
		name = name + "--&gt;" + deEncodeHTML2(text);
		if($("#knowledge" + value).length > 0){
			return formatKnowledgeName("knowledge" + value, name);
		}else{
			return name;
		}
	} else {
		return name;
	}
}




/**文件上传*/
var printScreenTimer = null;
var printScreen = false;
var printScreenCanceled = false;
var selectedThumbSequence = undefined;
var pictureCount = 0;//截图总数
var from = 1;
function checkMediaType(extend){
	if (extend == 'mp4' || extend == 'avi' || extend == 'rm' 
		|| extend == 'rmvb' || extend == 'wmv' || extend == 'mkv'
		|| extend == 'mov' || extend == 'mpg' || extend == '3gp' || extend == 'flv')
		return true;
	return false;
}

function checkFileType(sequence, extend){
	if ((sequence == 'thumb' || sequence == 'image') 
			&& (extend == "" || (extend !="jpg" && extend != "jpeg" && extend!="gif" && extend!="png" && extend!="bmp"))){
		Win.alert("只允许上传jpg, jpeg, gif, png, bmp类型的文件!");
		return false;
	}
	if ((sequence == 'highdefine' || sequence == 'middledefine') && !checkMediaType(extend)){
		Win.alert("只允许上传视频文件, 包括mp4, avi, rm, rmvb, wmv, mkv, mov, mpg, 3gp, flv");
		return false;
	}
	if (sequence == 'studyresource' && (extend == "" || (extend !="doc" && extend != "docx" && extend!="xls" && extend!="xlsx" && extend!="ppt" && extend!="pptx"&& extend!="pdf"))){
		Win.alert("只允许上传doc, docx, xls, xlsx, ppt, pptx, pdf类型的文件!");
		return false;
	}
	return true;
}

function getPrintScreenProcess(){
	$.get(ctx + "/uploadHandle/printScreenProgress.do", function(result){
		if (!printScreenCanceled){
			if (result){
				if (result.status == 1){
					$("#thumbFile").hide();
					$("#thumbSelected").hide();
					$("#thumbWait").hide();
					$("#thumbPrintScreen").show();
					pictureCount = result.pictureCount;
					from = 1;
					showPrintScreenGroup();
					window.clearInterval(printScreenTimer);
					printScreenTimer = null;
				} else if (result.status == -1){
					$("#thumbFile").show();
					$("#thumbSelected").hide();
					$("#thumbWait").hide();
					$("#thumbPrintScreen").hide();
					printScreen = true;
					window.clearInterval(printScreenTimer);
					printScreenTimer = null;
					if(window.Win)
						Win.alert("获取视频的截图失败, 请手动上传缩略图, 或重新上传视频");
					else
						$.messager.alert("错误", "获取视频的截图失败, 请手动上传缩略图, 或重新上传视频");
				}
			}
		}
	}, "json");
}

function getProgressy(sequence) {
	$.ajax({type: "post", dataType: "json", url: ctx + "/uploadHandle/progress.do", data: {"sequence":sequence},
		success: function(data) {
			if (data.result){
				if (data.status == 0){
					$("#progress_percent_" + sequence).text(data.percent);
					$("#progress_bar_" + sequence).width(data.percent);
					$("#progress_info_" + sequence).html("已上传：" + data.read + "MB&nbsp;&nbsp;&nbsp;速度：" + data.speed + "KB/s");
				}
			}
		}, error: function(err) {
			window.clearInterval(oTimer[sequence]);
			$("#progress_info_" + sequence).text("文件上传失败");
		}
	});
}

function nextGroup(){
	if (from <= pictureCount - 6)
		from = from + 6;
	showPrintScreenGroup();
}

function prevGroup(){
	if (from > 6)
		from = from - 6;
	showPrintScreenGroup();
}

function waitPrintScreen(){
	$("#thumbFile").hide();
	$("#thumbPrintScreen").hide();
	$("#thumbSelected").hide();
	$("#thumbWait").show();
}

function cancelSelect(){
	printScreenCanceled = true;
	window.clearInterval(printScreenTimer);
	printScreenTimer = null;
	$("#thumbFile").show();
	$("#thumbPrintScreen").hide();
	$("#thumbSelected").hide();
	$("#thumbWait").hide();
}

function onSelectPrintScreen(id){
	selectedThumbSequence = id;
	$("#thumbSequence").val(id);
	$("#thumbFile").hide();
	$("#thumbPrintScreen").hide();
	$("#thumbWait").hide();
	$("#thumbSelected").show();
	$("#selectedPrintScreenContainer").html("");
	$("#selectedPrintScreenContainer").html("<img src='" + ctx + "/uploadHandle/displayPrintScreen.html?id=" + id + "&tag=" + new Date().getTime() + "' height='260' style='margin-left:5px;'>");
}

function reSelect(){
	selectedThumbSequence = undefined;
	$("#thumbSequence").val("");
	$("#thumbPrintScreen").show();
	$("#thumbSelected").hide();
	$("#thumbWait").hide();
	$("#thumbFile").hide();
}

function showPrintScreenGroup(){
	$("#printScreenContainer").html("");
	var str = "";
	for (var x=from;x<=pictureCount&&x<from+6;x++){
		var id = "printScreen" + new Date().getTime();
		str = str + "<a href='javascript:onSelectPrintScreen(" + x + ")'><img id='printScreen" + id + "' src='" + ctx + "/uploadHandle/displayPrintScreen.html?id=" + x + "&tag=" + id + "' width='220' height='125' style='margin-left:5px;'></a>";
	}
	$("#printScreenContainer").html(str);
	if (from > 6){
		$("#printScreenButtonPrev")[0].disabled = false;
		$("#printScreenButtonPrev").removeClass("bgGray");
	}else{
		$("#printScreenButtonPrev")[0].disabled = true;
		$("#printScreenButtonPrev").addClass("bgGray");
	}
	if (from <= pictureCount - 6){
		$("#printScreenButtonNext")[0].disabled = false;
		$("#printScreenButtonNext").removeClass("bgGray");
	}else{
		$("#printScreenButtonNext")[0].disabled = true;
		$("#printScreenButtonNext").addClass("bgGray");
	}
}

// 上传视频
function uploadVideo(uploadDomId, url, fileSize, bPrintScreen) {
	var fileType = 'mp4,avi,rm,rmvb,wmv,mkv,mov,mpg,3gp,flv';
	uploadFile(uploadDomId, url, fileSize, fileType, bPrintScreen);
}

//上传图片
function uploadImage(uploadDomId, url, fileSize) {
	var fileType = 'jpg,jpeg,gif,png,bmp';
	uploadFile(uploadDomId, url, fileSize, fileType);
}

//上传文档
function uploadDocument(uploadDomId, url, fileSize) {
	var fileType = 'doc,docx,xls,xlsx,ppt,pptx,pdf';
	uploadFile(uploadDomId, url, fileSize, fileType);
}

//上传附件
function uploadFile(uploadDomId, url, fileSize, fileType, bPrintScreen) {
	var $box = $('#' + uploadDomId);
	var uploader = WebUploader.create({
		auto: true,
		swf: KD_RRT.root + "/public/flash/Uploader.swf",
		server: url,
		fileSingleSizeLimit: fileSize * 1024 * 1024,
		pick: {
			id:$box.find('.uploadFileBtn')[0],
			multiple:false
		},
		bPrintScreen: bPrintScreen,
		accept: {
			title: '选择上传文件',
			extensions: '' + fileType
		}
	});
	uploader.on('error', function( file ) {
		if (file == "F_EXCEED_SIZE") {
			Win.alert({
				id : "dialogAlert",
				html : '<span class="dialog_Inner">上传文件过大，最大支持'+fileSize+'MB</span>',
				width: 300
			});
		}
		if (file == "Q_TYPE_DENIED") {
			Win.alert({
				id : "dialogAlert",
				html : '<span class="dialog_Inner">文件类型错误，只支持以下文件类型：'+fileType+'</span>',
				width: 500
			});
		}
	});
	uploader.on('beforeFileQueued',function(file){
		var uploading = $("#uploading").val();
		if($.trim(uploading) == '1'){
			Win.alert({
				id : "dialogAlert",
				html : '<span class="dialog_Inner">上传进程进行中，请稍候</span>',
				width: 300
			});
			return false;
		}
		if(printScreenTimer !=null){
			Win.alert({
				id : "dialogAlert",
				html : '<span class="dialog_Inner">上传进程错误，有视频正在进行截屏处理</span>',
				width: 300
			});
			return false;
		}
	});
	uploader.on( 'fileQueued', function( file ) {
		var showTitle = file.name;
		var showHtml = showTitle;
		if(showTitle.length>20){
			showHtml = showTitle.substring(0,20)+"...";
		}
		$box.find('.uploadFileName').html('<span title="'+showTitle+'">'+showHtml+'</span>');
		// 正在上传
		$("#uploading").val("1");
		$box.find('.process').show();
		$box.find('.oldAttachment').remove();
		$box.find('.info').html('');
		//$box.find('.uploadFileBtn').hide();
		$box.find('.successFileName').val('');
	});
	uploader.on( 'uploadStart', function( file ) {
		
		if (uploader.options.bPrintScreen && uploader.options.bPrintScreen == true) {
			// 如果已经有缩略图，则不自动截取
			var thumbName = $(".successFileName","#thumbFile").val();
			var thumbSequence = $("#thumbSequence").val();
			if (thumbName == '' && thumbSequence == '') {
				waitPrintScreen();	
				printScreenCanceled = false;
			}
		}
		$box.find('.progress-bar').addClass('none').css('width', 0);
		$box.find('.progress_percent').html(0);
		setTimeout(function () {
			$box.find('.progress-bar').removeClass('none');
		}, 50);
	});
	uploader.on( 'uploadProgress', function( file, percentage  ) {
		percentage = ((percentage * 100)>>0 ) + '%';
		$box.find('.progress-bar').css('width', percentage);
		$box.find('.progress_percent').html(percentage);
	});
	uploader.on( 'uploadSuccess', function(file, json) {
		// 上传结束
		$("#uploading").val("0");
		$box.find('.uploadFileBtn').show();
		if (json != undefined && json.result != null && json.result == true) {
			var showTitle = file.name;
			var showHtml = showTitle;
			if(showTitle.length>20){
				showHtml = showTitle.substring(0,20)+"...";
			}
			$box.find('.info').html('<a href="javascript:;" style="cursor: initial;" title="'+showTitle+'">'+showHtml + '上传成功</a>');
			$box.find('.successFileName').val(file.name);
			// 如果是缩略图
			if ($box.find('.imageName').length > 0) {
				$box.find('.imageName').val(json.name);	
			}
			if ($box.find('#resourceImages').length > 0) {
				var resourceImages = $box.find('#resourceImages').val();
				if (resourceImages == "") {
					$box.find('.progress_info_image').show();
					$box.find('#resourceImages').val(json.name);
				} else {
					$box.find('#resourceImages').val(resourceImages + ";" + json.name);	
				}
				var showTitle = file.name;
				var showHtml = showTitle;
				if(showTitle.length>20){
					showHtml = showTitle.substring(0,20)+"...";
				}
				$box.find('#progress_info_image').append('<li title="'+showTitle+'" id="'+json.name+'">' +showHtml + '上传成功' + '<a class="close-uploadImg" href="javascript:;"></a></li>');
			}
			if (uploader.options.bPrintScreen && uploader.options.bPrintScreen == true && !printScreenCanceled) {
				// 如果已经有缩略图，则不自动截取
				var thumbName = $(".successFileName","#thumbFile").val();
				var thumbSequence = $("#thumbSequence").val();
				if (thumbName == '' && thumbSequence == '') {
					printScreenTimer = setInterval("getPrintScreenProcess()", 1000);	
				}
			}
			return ;
		}
		var percentage = '0%';
		$box.find('.progress-bar').css('width', percentage);
		$box.find('.progress_percent').html(percentage);
		cancelSelect();
		$box.find('.info').html('<b style="color: red;">上传失败:' + json.message + '</b>');
	});
	uploader.on( 'uploadError', function( file, json) {
		var percentage = '0%';
		$box.find('.progress-bar').css('width', percentage);
		$box.find('.progress_percent').html(percentage);
		cancelSelect();
		// 上传结束
		$("#uploading").val("0");
		$box.find('.uploadFileBtn').show();
		$box.find('.info').html('<b style="color: red;">上传失败</b>');
	});
	uploader.on( 'uploadFinished', function( file, json) {
		// 上传结束
		//$("#uploading").val("0");
		$box.find('.uploadFileBtn').show();
		uploader.reset();
	});
	$box.data("WebUploader", uploader);
}

function getValue(value) {
	if (value == null || !value) {
		return "";
	} else {
		return value;
	}
}
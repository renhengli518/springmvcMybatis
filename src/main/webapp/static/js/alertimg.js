var index = 1;
var uploadNum = 0;
var overNum = 0;
var file_src;
var file_name;


function addFiles(ctx){
	var length = $("#ul_file").children().length;
	if(length >=7){
		Win.alert("文件最多为6个！");
	}else{
		var html = '<li id="file_'+index+'">';
		html = html + '<input name="uploadFile" id="file_'+index+'_inputfile" type="file" title="请选择要上传的文件"/>';
		html = html + '<div id="file_'+index+'_div" style="display: none">';
		html = html + '<a id="file_'+index+'_alert" href="javascript:;" class="file" src="filesrc" filename="filename" onclick="clickimg(this)">';
		html = html + '<b id="file_'+index+'_name" class="blue"></b>';
		html = html + '</a>';
		html = html + '<span id="file_'+index+'_size" class="size"></span>';
		html = html + '<span id="file_'+index+'_percent" class="uploadBar uploadIng blu	e"></span>';
		html = html + '<span id="file_'+index+'_delete" class="deleteUpload"><a href="javascript:;" onclick="deleteProgres(\''+index+'\',\''+ctx+'\')">删除</a></span>';
		html = html + '</div></li>';
		
		$("#ul_file").append(html);
		(function(i){
			$("#file_"+index+"_inputfile").bind("change", function(){ 
  			 	uploadSubmite(i, ctx); 
  			});
		})(index);
		index = index + 1;
	}
}

function clickimg(obj){
//	var _this = $(this);//将当前元素作为_this传入函数
    imgShow("#outerdiv", "#innerdiv", "#bigimg", $(obj));
}

function checkFileType(fileName){
	var type = fileName.substr(fileName.lastIndexOf(".")+1,fileName.length);
	type = type.toLowerCase();
	if(type !='doc' && type !='docx' && type !='xls'&& type !='xlsx'&& type !='ppt'&& type !='pptx'&& type !='pdf'&& type !='jpg' && type !='png'&& type !='gif'&& type !='bmp'){
		return false;
	}
	return true;
}
function uploadSubmite(sequence, ctx) {
	var filename = $("#file_" + sequence+"_inputfile").val();
	file_name = filename;
	var filenames = filename.split("\\");
	filename = filenames[filenames.length-1];
	if(!checkFileType(filename)){
		$("#file_" + sequence+"_inputfile").val('');
		Win.alert("只支持office、pdf和图片文件！");
		return false;
	}
	uploadNum = uploadNum+1;
	var filenamemin = filename;
	if(filenamemin.length>20){
		filenamemin = filenamemin.substr(0,20);
	}
	$("#file_" + sequence+"_div").show();
	$("#file_" + sequence+"_inputfile").hide();
    $("#file_" + sequence+"_percent").text("已上传0%");
    $("#file_" + sequence+"_name").text(filenamemin);
    $("#file_" + sequence+"_name").attr("title",filename);
    oTimer = setInterval("getProgressy('" + sequence + "', '" + ctx +"')", 1000);/**一秒钟调用一次上传文件的进度*/
    ajaxFileUpload(sequence,ctx);
}

function getProgressy(sequence, ctx) {
    $.ajax({
        type: "post",
        dataType: "json",
        url: ctx + "/uploadHandle/progress.do",
        data: {"sequence":sequence},
        success: function(data) {
        	if (data.result){
        		$("#file_" + sequence+"_percent").attr("class","uploadBar uploadIng blue");
        		$("#file_" + sequence+"_percent").text("已上传"+data.percent);
        		$("#file_" + sequence+"_size").text(data.size+"M");
            }else{
            	window.clearInterval(oTimer);
            	$("#file_" + sequence+"_percent").attr("class","uploadBar uploadFail red");
            	$("#file_" + sequence+"_percent").text("上传失败");
            	uploadNum = uploadNum -1;
            }
        },
        error: function(err) {
        	$("#file_" + sequence+"_percent").attr("class","uploadBar uploadFail red");
        	$("#file_" + sequence+"_percent").text("上传失败");
        	uploadNum = uploadNum -1;
        }
    });
}

function ajaxFileUpload(sequence,ctx) {
    $.ajaxFileUpload({
        url: ctx+'/uploadHandle/upload.do?sequence=' + sequence+'&maxSize='+5*1024*1024,
        secureuri: false,
        fileElementId: "file_"+sequence+"_inputfile",
        dataType: 'json',
        success: function(data, status) {
        	file_src = ctx+"/ImageServlet/"+data.name;
        	window.clearInterval(oTimer);
            if (data.result) {
            	$("#file_" + sequence+"_size").text(data.size+"M");
            	$("#file_" + sequence+"_percent").attr("class","uploadBar uploadDone green");
               	$("#file_" + sequence+"_percent").text("已完成");
               	$("#file_" + sequence+"_alert").attr("src",file_src);
               $("#file_" + sequence+"_alert").attr("filename",file_name);
               	overNum = overNum +1;
            }else if(!data.result && data.message == "文件过大"){
            	$("#file_" + sequence+"_percent").attr("class","uploadBar uploadFail red");
        		$("#file_" + sequence+"_percent").text("上传失败,文件不能大于5M");
        		deleteProgresError(sequence,ctx);
        		uploadNum = uploadNum -1;
            }else{
               	$("#file_" + sequence+"_percent").attr("class","uploadBar uploadFail red");
        		$("#file_" + sequence+"_percent").text("上传失败");
        		uploadNum = uploadNum -1;
            }
        },
        error: function(data, status, e) {
        	window.clearInterval(oTimer);
        	$("#file_" + sequence+"_percent").attr("class","uploadBar uploadFail red");
        	$("#file_" + sequence+"_percent").text("上传失败");
        	uploadNum = uploadNum -1;
        }
    });
    return false;
}

function deleteProgresError(id,ctx){
	$.ajax({
        type: "post",
        dataType: "json",
        url: ctx+"/uploadHandle/deleteProgress.do",
        data: {"sequence":id},
        success: function(data) {
        },
        error: function(err) {
        }
    });
}

function deleteProgres(id,ctx){
	Win.confirm("确定删除文件吗？",function(){
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: ctx+"/uploadHandle/deleteProgress.do",
	        data: {"sequence":id},
	        success: function(data) {
	        	if (data.result){
	        		$("#file_"+id).remove();
	        		if(uploadNum > 0){
	        			uploadNum = uploadNum -1;
		        		overNum = overNum -1;
	        		}
	            }else{
	            	Win.alert("删除失败！");
	            }
	        },
	        error: function(err) {
	        	Win.alert("删除错误！");
	        }
	    });
	},function(){});
	
}

function deleteFile(id,ctx){
	Win.confirm("确定删除文件吗？",function(){
		$.post(ctx+"/front/networkJob/deleteNetworkJobFile.do",{'id':id},function(data){
			if(data && data.result){
				$("#file_"+id).remove();
			}else if(data && !data.result){
				Win.alert(data.message);
			}else{
				Win.alert("删除失败！");
			}
		});
	},function(){});
	
}
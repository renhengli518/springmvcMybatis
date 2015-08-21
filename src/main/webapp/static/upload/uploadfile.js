//上传类
function UploadFile(wrap, namespace, url, params){
    var self = this;
    var myParams = extendCopy(params || {}, {
        nameSpace : namespace,
        debug : 0,
        fileType : "*.jpg;*.gif;*.png;*.jpeg;*.bmp;",
        typeDesc : "图片",
        multiSelect : 0,
        sizeLimit : 20*1024*1024,
        autoStart : true,
        server: "/"
    });
    this.uploading = 0;
    this.init = function(){
        var paramVars = "";
        for(var i in myParams){
            paramVars += i+"="+myParams[i]+"&";
        };
        wrap.innerHTML ="";
        this.obj = FlashPlayer(wrap, url+"?"+paramVars,{wmode:"Opaque"});
    };
    this.uploadEvent = new CustomEvent();
    this.init();
};

UploadFile.prototype = {
	startUpload : function(server,i){
        this.obj.upload(server,i);
    },
    cancelUpload : function(i){
        this.obj.cancel(i);
    },
    getState : function(){
    	return this.obj.getState();
    },
	noticeTypeError : function(d){
		this.uploadEvent.fire({type:"noticeTypeError",message:d});
	},
	noticeSizeError : function(d){
		this.uploadEvent.fire({type:"noticeSizeError",message:d});
	},
	onCancel : function(d){
		this.uploadEvent.fire({type:"onCancel",message:d});
	},
	onSelect : function(d){
		this.uploadEvent.fire({type:"onSelect",message:d});
	},
	onOpen : function(d){
		this.uploading++;
		this.uploadEvent.fire({type:"onOpen",message:d});
	},
	onProgress : function(d){
		this.uploadEvent.fire({type:"onProgress",message:d});
	},
	onStop : function(d){
		//由取消上传cancel操作触发
		this.uploading--;
		this.uploadEvent.fire({type:"onStop",message:d});
	},
	onComplete : function(d){
		this.uploading--;
		this.uploadEvent.fire({type:"onComplete",message:d});
	},
	onFail : function(d){
		this.uploading--;
		this.uploadEvent.fire({type:"onFail",message:d});
	}
};

function Html5Upload(wrap,namespace, params){
	var self = this;
    var myParams = extendCopy(params || {}, {
    	nameSpace : namespace,
        debug : 0,
        fileType : "*.jpg;*.gif;*.png;*.jpeg;*.bmp;",
        accept : "*",
        typeDesc : "图片",
        multiSelect : 0,
        sizeLimit : 20*1024*1024,
        autoStart : true,
        server: "/"
    });
    this.myParams = myParams;
    this.fileRefJson = {};
    this.xhrJson = {};
	this.uploading = 0;
    this.init = function(){
        var fileInput = document.createElement("INPUT");
        	fileInput.type = "file";
        	fileInput.accept = myParams.accept;
        	if(myParams.multiSelect) fileInput.multiple = true;
        wrap.appendChild(fileInput);
        events.addEvent(fileInput,"change",self.handleFiles);
    };
    this.handleFiles = function(obj){
    	var files = obj.target.files, n;
       	for (var i = 0; i < files.length; i++) {
       		n = myParams.nameSpace +"_"+ random(),
       		file = files[i];
       		if(myParams.fileType !== "*.*" && myParams.fileType.indexOf((file.type).toLowerCase())==-1){
       			self.noticeTypeError([n,myParams.fileType,file]);
				continue;
			}
			if(file.size > myParams.sizeLimit){
				self.noticeSizeError([n,myParams.sizeLimit,file]);
				continue;
			}
       		self.fileRefJson[n] = file;
       		self.uploading ++ ;
		    self.onSelect([n,file]);
		    if(myParams.autoStart)  self.startUpload(myParams.server,n);
		}
    };
    this.uploadEvent = new CustomEvent();
    this.init();
}
Html5Upload.prototype = {
	startUpload : function(server,i){
		var self = this,
			url = server || this.myParams.server;
        var xhr = new XMLHttpRequest();
        	this.xhrJson[i] = xhr;
	        xhr.open("POST", decodeURIComponent(url), true);
	        //xhr.overrideMimeType('text/plain; charset=x-user-defined-binary');
	        xhr.addEventListener("loadstart", function(e){
	        	self.onOpen([i,self.fileRefJson[i]]);
	        }, false);
	        xhr.upload.addEventListener("progress", function(e){
	        	if (e.lengthComputable) {
				    self.onProgress([i, e.loaded , e.total]);
				}
	        }, false);
	        xhr.addEventListener("load", function(e){
	        	delete self.fileRefJson[i];
				self.uploading --;
	        }, false);
			xhr.addEventListener("loadend", function(e){
	        	self.onComplete([i,e.target.response]);
	        }, false);
			xhr.addEventListener("error", function(e){
				self.onFail([i,e]);
				self.uploading --;
			}, false);
			xhr.addEventListener("abort", this.onCancel, false);
			xhr.addEventListener("readystatechange", function(e){
				if (xhr.readyState == 4) {
					if(xhr.status !== 200) self.onFail([i,e.target]);
				}
			}, false);
 		var fd = new FormData();
	        fd.append('myFile', this.fileRefJson[i]);
	        xhr.send(fd);
    },
    cancelUpload : function(i){
        this.xhrJson[i].abort();
    },
    getState : function(){
    	
    },
    noticeTypeError : function(d){
		this.uploadEvent.fire({type:"noticeTypeError",message:d});
	},
	noticeSizeError : function(d){
		this.uploadEvent.fire({type:"noticeSizeError",message:d});
	},
	onSelect : function(d){
		this.uploadEvent.fire({type:"onSelect",message:d});
	},
	onOpen : function(d){
		this.uploadEvent.fire({type:"onOpen",message:d});
	},
	onProgress : function(d){
		this.uploadEvent.fire({type:"onProgress",message:d});
	},
	onComplete : function(d){
		this.uploading = false;
		this.uploadEvent.fire({type:"onComplete",message:d});
	},
	onFail : function(d){
		this.uploading = false;
		this.uploadEvent.fire({type:"onFail",message:d});
	},
	onCancel:function(e) {
		console.log(e);
	}
};


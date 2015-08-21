var feed = (function () {
	var $win = $(window);

	var fullScreenApi = (function () {
		var api = {
			supportsFullScreen: false,
			isFullScreen: function() { return false; }, 
			requestFullScreen: function() {}, 
			cancelFullScreen: function() {},
			fullScreenEventName: '',
			prefix: ''
		};
		var browserPrefixes = 'webkit moz o ms khtml'.split(' ');
		if (typeof document.cancelFullScreen != 'undefined') {
			api.supportsFullScreen = true;
		} else {	 
			for (var i = 0, il = browserPrefixes.length; i < il; i++ ) {
				api.prefix = browserPrefixes[i];				
				if (typeof document[api.prefix + 'CancelFullScreen' ] != 'undefined' ) {
					api.supportsFullScreen = true;
					break;
				}
			}
		}
		if (api.supportsFullScreen) {
			api.fullScreenEventName = api.prefix + 'fullscreenchange';
			api.isFullScreen = function() {
				switch (this.prefix) {	
					case '':
						return document.fullScreen;
					case 'webkit':
						return document.webkitIsFullScreen;
					default:
						return document[this.prefix + 'FullScreen'];
				}
			}
			api.requestFullScreen = function(el) {
				return (this.prefix === '') ? el.requestFullScreen() : el[this.prefix + 'RequestFullScreen']();
			}
			api.cancelFullScreen = function(el) {
				return (this.prefix === '') ? document.cancelFullScreen() : document[this.prefix + 'CancelFullScreen']();
			}		
		}
		return api;
	})();
		
	var picDetail = (function () {
		var html = ['<div class="fbPhotoSnowlift fullScreenAvailable">',
						'<div class="fbPhotoSnowliftOuter"></div>',
						'<div class="fbPhotoSnowliftInner">',
							'<div class="fbPhotoSnowliftContainer">',
								'<div class="fbPhotoSnowliftPopup" >',
									'<div class="fbPhotoSnowliftWrapper" style="width: 860px; line-height: 43.3333em;">',
										'<div class="fbPhotoSnowliftImgWrapper g-ifc-wrap">',
											'<img class="fbPhotoSnowliftImg g-ifc-item" />',
											(UA.isIE6||UA.isIE7)?'<span class="g-ifc-wrap-after"></span>':'',
										'</div>',
										'<a id="fbPhotoSnowliftPhotoPre" class="snowliftPager prev hilightPager" ><i></i></a>',
										'<a id="fbPhotoSnowliftPhotoNext" class="snowliftPager next hilightPager" ><i></i></a>',
										fullScreenApi.supportsFullScreen? ['<div id="fullScreenSwitch" class="fbPhotoSnowliftFullScreen fullScreenSwitch">',
											'<a id="fbPhotoSnowliftFullScreenSwitch" href="javascript:;" ></a>',
											'<a id="fbPhotoSnowliftFullScreenClose" href="javascript:;" ></a>',
										'</div>'].join(''):'',
										'<a href="javascript:;" id="fbPhotoSnowliftClose" >X</a>',
									'</div>',
								'</div>',
							'</div>',
						'</div>',
					'</div>'].join('');
		
		var _imgInfos = {}
		var _showIndex = 0;
		var _photeList = [];
		
		
		var timeId = 0;
		var $win = $(window);
		var $body = $("body");
		var maxWinWith = 580, maxWinHeight = 480;
		

		var $photoDetail, $detailBox, $photoBox, $image, $preBtn, $nextBtn;
		
		//窗口改变函数
		var _resize = function () {
			var width, height, css = [{},{}];
			
			if (fullScreenApi.supportsFullScreen) {
				if (fullScreenApi.isFullScreen() == true) {
					$photoDetail.addClass('fbPhotoSnowliftIsFullScreen');
				} else {
					$photoDetail.removeClass('fbPhotoSnowliftIsFullScreen');
				}
			}
			
			var winWidth = $win.width();
			var winHeight = $win.height();
			
			if (UA.isIE6) {
				$photoDetail.css({
					width: winWidth,
					height: winHeight
				});
			}
			
			if (winWidth > maxWinWith) {
				var margin = (winWidth - maxWinWith)/4;
				width = maxWinWith - 20 + margin * 2;
				css[0].width = width + 20 + 'px';
				css[1].width = width + 'px';
			} else {
				width = maxWinWith - 20;
				css[0].width = width + 20 + 'px';
				css[1].width = width + 'px';
			}
			
			winHeight = winHeight > maxWinHeight ? winHeight: maxWinHeight;
			height = winHeight - 80;
			css[0].height = winHeight - 60 + 'px';
			css[1].height = css[1]['line-height'] = height + 'px';
			
			$detailBox.css(css[0]);
			$photoBox.css(css[1]);
			
			$preBtn.css('width', (width/2-10)+'px');
			$nextBtn.css('width', (width/2-10)+'px');
			
			height = Math.floor(height);
			width = Math.floor(width);
			
			_resizeImg($image, width, height);
			
		};
		
		var _winResize = function (time) {
			clearTimeout(timeId);
			time = time || 500;
			timeId = setTimeout(_resize, time);
		};
		
		var _resizeImg = function ($image, width, height) {
			var url = _photeList[_showIndex];
			var originalWidth = _imgInfos[url].width;
			var originalHeight = _imgInfos[url].height;
			if (width > originalWidth && height > originalHeight) {
				$image.css({
					'width': originalWidth + 'px',
					'height': originalHeight + 'px'
				})
			} else {
				var originalWeight = originalWidth/originalHeight;
				var weight = width/height;
				if (weight > originalWeight) {	//按height缩放
					$image.css({
						width: Math.floor(originalWeight * height),
						height: height
					});
				} else { //按width缩放
					$image.css({
						width: width,
						height: Math.floor(width/originalWeight)
					})
				}
			}
		};
		
		var _loaldPic = function (index) {
			index = index || 0;
			_showIndex = index * 1;
			
			$preBtn.show();
			$nextBtn.show();
			if (_showIndex == 0) {
				$preBtn.hide();
			} 
			if (_showIndex == _photeList.length -1) {
				$nextBtn.hide();
			}
			var url = _photeList[index];
			if (_imgInfos[url] && _imgInfos[url].state == "load") {
				$image.attr('src', url);
				_winResize(10);
			} else {
				_imgInfos[url] = {};
				var image = new Image();
				image.onload = function () {
					_imgInfos[url].width = image.width;
					_imgInfos[url].height = image.height;
					_imgInfos[url].state = "load";
					$image.attr('src', url);
					_winResize(10);
				}
				image.onerror = function () {
					_imgInfos[url].width = _imgInfos[url].height = 600;
					_imgInfos[url].state = "error";
					$image.attr('src', url);
					_winResize(10);
				}
				image.src = url;
			}
		};
		
		return {
			init: function () {
				if (!$photoDetail) {
					var self = this;
					$photoDetail = $(html).appendTo($body);
					$detailBox = $photoDetail.find('.fbPhotoSnowliftPopup');
					$photoBox = $photoDetail.find('.fbPhotoSnowliftWrapper');
					$image = $photoDetail.find('.fbPhotoSnowliftImg');
					$preBtn = $('#fbPhotoSnowliftPhotoPre');
					$nextBtn = $('#fbPhotoSnowliftPhotoNext');
					this.initEvents();
				}
			},
			initEvents: function () {
				//图标显示
				$photoBox.mouseenter(function () {
					$photoDetail.addClass('pagingActivated');
				}).mouseleave(function () {
					$photoDetail.removeClass('pagingActivated');
				});
				
				//fullScreen
				if (fullScreenApi.supportsFullScreen) {
					$('#fullScreenSwitch').show();
					$('#fbPhotoSnowliftFullScreenSwitch').show().click(function () {
						fullScreenApi.requestFullScreen($photoBox[0]);
						_winResize(10);
					});;
					$('#fbPhotoSnowliftFullScreenClose').click(function () {
						fullScreenApi.cancelFullScreen($photoBox[0]);
					});;
				}
				
				$preBtn.click(function () {
					var _index = _showIndex - 1;
					if (_index >= 0)
						_loaldPic(_index);
					return false;
				});
				$nextBtn.click(function () {
					var _index = _showIndex + 1;
					if (_index <  _photeList.length)
						_loaldPic(_showIndex + 1);
					return false;
				});
				
				$('.fbPhotoSnowliftContainer').click(function (event) {
					if (event.target.className == "fbPhotoSnowliftContainer")
						picDetail.close();
				})
				$('#fbPhotoSnowliftClose').click(function (event) {
					picDetail.close();
					return false;
				})
			},
			show: function (list, index) {
				this.init();
				
				if (UA.isIE6) {
					$photoDetail.css({
						position: 'absolute',
						top: (document.body.scrollTop | document.documentElement.scrollTop),
						left: 0
					});
				}
				
				_photeList = list;
				_loaldPic(index);
			
				$body.css({overflow: "hidden"});
				$win.bind('resize', _winResize);
				$photoDetail.show();
			},
			close: function () {
				$body.css({overflow: "auto"});
				$win.unbind('resize');
				
				$image.attr('src', '');
				$photoDetail.hide();
			}
		}
	})();
	
	//picDetail.show(["./exp1.jpg","./exp2.jpg","./exp3.jpg","./exp4.jpg"]);
	
	var scrollLoading = function () {
		var $moreBox;
		var state = "loaded";
		var _opt = {
			minSHight: 40,
			check: function () {
				return $win.scrollTop() + $win.height() - $moreBox.offset().top  >= this.minSHight;
			},
			onStatusChange: function ($moreBox, state) {
				if (state == 'loaded') {
					$moreBox.html('正在加载中，请稍候...');
				} else if (state == 'loading') {
					$moreBox.html('正在加载，请稍候...');
				} else if (state == 'end') {
				}
			}
		}
		return {
			init: function ($elm, opt) {
				state = 'loaded';
				$moreBox = $elm.addClass('evenMore');
				_opt.onStatusChange($moreBox, state);
				$.extend(_opt, opt||{});
				var self = this;
				$win.on('scroll', function () {
					if (!self.isLoading()) {
						if (_opt.check()) {
							_opt.onScroll();
						}
					}
				});
				return this;
			},
			loadinit: function () {
				state = 'loaded';
				_opt.onStatusChange($moreBox, state);
				$moreBox.show();
				return true;
			},
			loadon: function() {
				if (state == 'loaded') {
					state = 'loading';
					_opt.onStatusChange($moreBox, state);
					return true;
				} else {
					return false;
				}
			},
			loadoff: function() {
				if (state != 'end') {
					state = 'loaded';
					_opt.onStatusChange($moreBox, state);
					return true;
				}
				return false;
			},
			loadover: function() {
				state = 'end';
				_opt.onStatusChange($moreBox, state);
				$moreBox.hide();
				return true;
			},
			isLoading: function() {
				return state == 'loading' || state == "end";
			}
		}
	};
	
	 
	
	var feedList = function ($list) {
		var $feedList = $list;
		var feedListTemp; 
		var replyListTmp;
		var feedScrollLoading;
		
		
		var toggleShow = function ($elm, onShow) {
			if ($elm.css('display') == 'none') {
				$elm.show();
				onShow && onShow();
			} else {
				$elm.hide();
			}
		};
		
		var defaultRow = 4;
		
		var getCommentNums = function (feedId, onSuccess){
			$.post(KD_RRT.root + "/front/dynamicComment/getCommentNumber.do",  {
				dynamicId: feedId,
				forignType: $feedList.attr('data-forignType'),
				forignId: $feedList.attr('data-forignId'),
				dynamicType: $feedList.attr('data-dynamicType'),
				dynamicVisitorId: $feedList.attr('data-dynamicVisitorId')
			}, function (data) {
				onSuccess(data);
			}, 'json');
		};
		
		var loadCommentList = function ($feedItem, page) {
			var $commentList = $feedItem.find('.commentList');
			var feedId = $feedItem.attr('data-feedId');
			var nowPage;
			if (page == -1) {
				nowPage = 0;
			} else {
				nowPage = $commentList.attr('data-page')*1 || 0;
			}
			var perPgaeNum = $commentList.attr('data-rows') || defaultRow;
			$.post(KD_RRT.root + "/front/dynamicComment/getDynamicComment.do", {
				forignType: $feedList.attr('data-forignType'),
				forignId: $feedList.attr('data-forignId'),
				dynamicType: $feedList.attr('data-dynamicType'),
				dynamicVisitorId: $feedList.attr('data-dynamicVisitorId'),
				dynamicId: feedId, 
				page: nowPage + 1, 
				rows: perPgaeNum
			}, function (data) {
				var hasMoreReply = false;
				var hasFirstReply = false;
				
				if (nowPage * perPgaeNum + data.rows.length < data.total) {
					hasMoreReply = true;	
				} else if (nowPage >= 1) {
					hasFirstReply = true;
				}
				
				nowPage++;
				if(data.total == 0){
					if('org' == $feedList.attr('data-orgType')){
						$commentList.html('<li>无评论信息<li>');
					}else{
						$commentList.html('');
					}
					nowPage--;
				}else{
					$commentList.html(replyListTmp({data: data, text2HTML: util.smile.text2HTML, hasMoreReply: hasMoreReply, hasFirstReply: hasFirstReply}));
				}
				$commentList.attr('data-page', nowPage);
				$feedItem.find('.showCommentList').html('评论(<span>' + data.total + '</span>)');
			}, 'json');
		}
		
		
		return {
			loadFeedList: function (page, rows) {
				if (feedScrollLoading.loadon()) {
					//feedScrollLoading.loadon();
					var loadPage = page || ($feedList.attr('data-page') *1 + 1);
					var rows = rows || 20;
					if (loadPage == 0) $feedList.html("");
					$.post(KD_RRT.root + "/front/dynamic/getDynamic.do", {
						forignType: $feedList.attr('data-forignType'), 
						forignId: $feedList.attr('data-forignId'), 
						dynamicType: $feedList.attr('data-dynamicType'),
						visitedUserId: $feedList.attr('data-visitedUserId'),
						pageNow: loadPage, 
						pageSize: rows
					}, function (data) {
						if (data.total == 0 && loadPage == 1) {
							$("#platsetList").show();
							$("#latsetPubName").html("暂无动态");
							feedScrollLoading.loadoff();
						} else {
							$("#platsetList").hide();
							feedScrollLoading.loadoff();
						}
						var html = feedListTemp({data: data.rows, text2HTML: util.smile.text2HTML});
						if (loadPage == 1) {
							$feedList.html(html);
						} else {
							$feedList.append(html);
						}
						$feedList.attr('data-page', loadPage);
						if ((loadPage-1) * rows +  data.rows.length >= data.total ) {
							feedScrollLoading.loadover();
						}
					}, 'json');
				}
			},
			reloadList: function (forignType, forignId) {
				feedScrollLoading.loadinit();
				$feedList.attr('data-forignType', forignType);
				$feedList.attr('data-forignId', forignId);
				this.loadFeedList(1);
			},
			init: function (flag) {
				var self = this;
				
				feedScrollLoading = scrollLoading().init($('#moreFeedLink'), {
					onScroll: function () {
						self.loadFeedList();
					}
				});
				feedListTemp = util.template($('#feedListTmp').html());
				replyListTmp = util.template($('#replyListTmp').html());
				//$.post()
				
				$feedList.on('click', '.feed_action', function () {
					var $elm = $(this), 
						action = $elm.attr('data-feedAction');
					if (self[action]) {
						self[action].call(self, $elm);
					}
				});
				if (!flag) {
					this.loadFeedList();
				}
				//微博发布
				$('#pubSendBtn').click(this.pubSendFeed);
				
				return this;
			},
			showCommentList: function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				var feedId = $feedItem.attr('data-feedId');
				var $comment = $feedItem.find('.comment');
				
				toggleShow($comment, function () {
					$comment.find('.sendComment').focus();
					loadCommentList($feedItem, -1);
				});
			},
			getMoreComment: function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				//$elm.parents('.moreComm').remove();
				loadCommentList($feedItem);
			},
			getFirstComment: function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				//$elm.parents('.moreComm').remove();
				loadCommentList($feedItem, -1);
			},
			showReplyBox: function ($elm) {
				var $replyBox = $elm.parents('.replyItem').find('.replyBox');
				toggleShow($replyBox, function () {
					var $replyComment = $replyBox.find('.replyComment');
					$replyComment.focus();
					util.moveCursor($replyComment[0], $replyComment.val().length);
				});
			},
			feedLike: function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				var feedId = $feedItem.attr('data-feedId');
				$.post(KD_RRT.root + "/front/dynamic/dynamicGreateSaveOrCancel.do", {dynamicId: feedId}, function (data) {
					if (data.result == true) {
						var html = (data.code == 0?'赞' : '取消赞') + '(<span>' + data.message + '</span>)';
						$elm.html(html);
					}
				}, 'json');
			},
			feedDelete: function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				var feedId = $feedItem.attr('data-feedId');
				Win.confirm({
					id: "feed_confirm_win",
					title : "确认消息",
					html : '<span class="dialog_Inner">确认删除？</span>',
					mask: true
				}, function () {
					$.post(KD_RRT.root + "/front/dynamic/deleteDynamicById.do", {dynamicId: feedId}, function (data) {
						if (data.result == true) {
							$feedItem.remove();
						}else{
							Win.alert("删除失败");
						}
					}, 'json');
				}, true);
			},
			feedComment: function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				var feedId = $feedItem.attr('data-feedId');
				var $feedCommentTextarea = $('#feedCommentTextarea_'+feedId);
				var content = $.trim($feedCommentTextarea.val());
				if (content === "") {
					Win.alert("评论内容不能为空！");
					return;
				}
				if (content.length > 150) {
					Win.alert({
						id : "dialogAlert",
						html : '<span class="dialog_Inner">评论内容不能超过150个字！</span>',
						width: 300
					});
					return;
				}
				$.post(KD_RRT.root + "/front/dynamicComment/sendDynamicComment.do", {"content":content,"dynamicId": feedId}, function (data) {
					//$feedItem.find('.commentList').prepend(replyListTmp({data: data, text2HTML: util.smile.text2HTML, hasMoreReply: false, hasFirstReply: false}));
					//$comment.find('.sendComment').focus();
					if(data){
						if(!data.result){
							Win.alert(data.message);
							return ;
						}
					}else{
						Win.alert("评论失败！");
						return ;
					}
					loadCommentList($feedItem, -1);
					$feedCommentTextarea.val('');
					getCommentNums(feedId, function (num) {
						$feedItem.find('.showCommentList').html('评论(<span>' + num + '</span>)');
					});
				}, 'json');
			},
			feedReply: function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				var feedId = $feedItem.attr('data-feedId');
				
				var $replyItem = $elm.parents('.replyItem');
				var replyId = $replyItem.attr('data-replyId');
				
				var atName = $replyItem.attr('data-replyUserName');
				var $feedCommentTextarea = $('#replyCommentTextarea_' + replyId);
				var content = $.trim($feedCommentTextarea.val());
				if (content === "") {
					Win.alert("回复内容不能为空！");
					return;
				}
				
				var atStr = '@' + atName + ':';
				if (content == atStr) {
					Win.alert("回复内容不能为空！");
					return;
				} 
				var basedUserId="";
				var childUserId = $replyItem.attr('data-childUserId')
				var atIndex=content.indexOf(atStr);
				if (atIndex==0){
					basedUserId=$replyItem.attr('data-replyUserId');
					content=content.substring(atStr.length);
				}
				if (content.length > 150) {
					Win.alert({
						id : "dialogAlert",
						html : '<span class="dialog_Inner">回复内容不能超过150个字！</span>',
						width: 300
					});
					return;
				}
				$.post(KD_RRT.root + "/front/dynamicComment/sendDynamicComment.do", {"content":content,"dynamicId": feedId,"basedUserId":basedUserId,"atName":atName,"childUserId":childUserId}, function (data) {
					if(data){
						if(!data.result){
							Win.alert(data.message);
							return ;
						}
					}else{
						Win.alert("评论失败！");
						return ;
					}
					$feedItem.find('.commentList').prepend(replyListTmp({data: data, text2HTML: util.smile.text2HTML, hasMoreReply: false, hasFirstReply: false}));
					$feedCommentTextarea.val(atStr);
					getCommentNums(feedId, function (num) {
						$feedItem.find('.showCommentList').html('评论(<span>' + num + '</span>)');
					});
					$elm.parents('.replyBox').hide();
				}, 'json');
			},
			delReply : function ($elm) {
				var $feedItem = $elm.parents('.feedItem');
				var feedId = $feedItem.attr('data-feedId');
				var $replyItem = $elm.parents('.replyItem');
				var replyId = $replyItem.attr('data-replyId');
				
				Win.confirm({
					id: "feed_confirm_win",
					title: "确认消息",
					html: '<span class="dialog_Inner">确认删除？</span>',
					mask: true
				}, function () {
					$.post(KD_RRT.root + "/front/dynamicComment/deleteDynamicComment.do", { "dynamicCommentId": replyId}, function (data) {
						/*if ($replyItem.siblings(".replyItem").length == 4) {
							$feedItem.find('.getFirstComment').hide();
						}*/
						//$replyItem.remove();
						loadCommentList($feedItem, -1);
						/*getCommentNums(feedId, function (num) {
							$feedItem.find('.showCommentList').html('评论(<span>' + num + '</span>)');
						});*/
					}, 'json');
				}, true);
				
			},
			showPicDetail: function ($elm) {
				var picUrls = $elm.parents('.lightbox').attr("data-picUrls").split(",");
				picDetail.show(picUrls, $elm.attr("data-picIndex"));
			},
			pubSendFeed: function () {
				if (uploadFeedPic.state == 'progress') {
					Win.alert("图片上传中，不能发表！");
					return;
				}
				
				var $pubContent = $("#pubContent");
				var content = $.trim($pubContent.val());
				if (content == "") {
					Win.alert("互动内容不能为空！");
					return;
				}
				var photoUrls = [];
				$(".publishPic li").each(function(_idx, _item) {
					photoUrls.push($(this).attr("data-picUrl"));
				});

				$.ajax({
					type: "POST",
					url : KD_RRT.root + "/front/dynamic/sendDynamic.do",
					cache : false,
					dataType:'json',
					data : {
						"forignType": $feedList.attr('data-forignType'),
						"forignId": $feedList.attr('data-forignId'),
						"content": content,
						"images" : photoUrls.join(',')
					},
					success : function(data) {
						var count = 0;
						uploadFeedPic.count = 0;
						$(".publishPic").html('');
						$pubContent.val('');
						util.restCount($pubContent, $('#pubRestCountText'));
						$('#feedList').prepend(feedListTemp({data: data.rows, text2HTML: util.smile.text2HTML, isSchool: $feedList.attr('data-isSchool')}));
						var $latsetPubName = $("#latsetPubName");
						if ($latsetPubName.html() == "暂无动态") {
							$('#platsetList').hide();
							$latsetPubName.html('');
						}
					},
					error : function(data) {
						Win.alert("发布动态失败！");
					}
				});
			}
		}
	};
	
	var uploadFeedPic = (function () {
		var uploader;
		return {
			count:0,
			state: 'complete',
			reset: function () {
				uploader.reset();
			},
			init: function () {
				var self = this;
				var $uploadImg = $("#uploadImg");
				if ($uploadImg.length > 0) {
					var $list;
					uploader = WebUploader.create({
						auto: true,
						swf: KD_RRT.root + "/public/flash/Uploader.swf",
						//server: KD_RRT.root + "/uploadHandle/upload.do?sequence=1",
						server: KD_RRT.root + "/upload/image.do?thumb=true",
						pick: '#uploadImg',
						accept: {
							title: '图片',
							extensions: 'gif,jpg,jpeg,bmp,png'
						},
						fileSingleSizeLimit: 4 * 1024 * 1024
					});
					uploader.on('error', function( file ) {
						if (file == "Q_TYPE_DENIED") {
							Win.alert({
								id : "dialogAlert",
								html : '<span class="dialog_Inner">文件类型错误，只支持以下文件类型：gif,jpg,jpeg,bmp,png</span>',
								width: 300
							});
						}else{
							self.count--;
						}
						if (file == "F_EXCEED_SIZE") {
							Win.alert({
								id : "dialogAlert",
								html : '<span class="dialog_Inner">上传文件过大，最大支持4MB</span>',
								width: 300
							});
						}
					});
					var msg_max_file = false;
					uploader.on('uploadProgress', function( file, percentage ) {
						var $elm = $("#pubPicList .publishPic-" + file.id);
						if ($elm.length == 0) {
							var $pics = $("#pubPicList li");
							if ($pics.length >= 8) {
								msg_max_file = true;
								return;
							}
							$("#pubPicList").append('<li class="publishPic-' + file.id + '">' + ((percentage*100)>>0) + '%</li>');
						} else {
							$elm.html(((percentage*100)>>0) + '%');
						}				
					});
					uploader.on('beforeFileQueued', function( file ) {
						self.count++;
						if(self.count>8){
							self.count--;
							Win.alert('只能上传8张图片!');
							msg_max_file = false;
							return false;
						}
						var $pics = $("#pubPicList li");
						if ($pics.length >= 8) {
							msg_max_file = true;
							return false;
						}
					})
					uploader.on( 'fileQueued', function( file ) {
						self.state = "progress";
					});
					uploader.on( 'uploadSuccess', function(file, json) {
						//console.log('uploadSuccess', file, json);
						var $elm = $("#pubPicList .publishPic-" + file.id);
						if (json != undefined && json.status == true) {
							if ($elm.length == 0) {
								//$("#pubPicList").append('<li data-picUrl="' + json.name + '" class="publishPic-' + file.id + '" ><img src="' + KD_RRT.root + '/ImageServlet/THUMB' + json.name + '"/><a href="javascript:;" class="close">&nbsp;</a></li>');
							} else {
								$elm.attr('data-picUrl', json.name).html('<img src="' + KD_RRT.root + '/ImageServlet/THUMB_' + json.name + '"/><a href="javascript:;" class="close">&nbsp;</a>')
							}
						}else{
							self.count--;
							$elm.remove();
							json = json || "";
							if(json.name){
								Win.alert('上传失败，请上传标准格式的图片！');
							}else{
								top.location = KD_APPREDIRECT;
							}
						}
					});
					uploader.on( 'uploadError', function( file, json) {
						//console.log('uploadError', file, json);
					});
					uploader.on('uploadFinished', function( file, json) {
						self.state = 'complete';
						if (msg_max_file) {
							Win.alert('只能上传8张图片!');
							msg_max_file = false;
						}
						uploader.reset();
					});
					$(".publishPic").on('click', '.close', function () {
						self.count--;
						$(this).parent().remove();
					})
				}
			}
		}
	})();


	
	return {
		uploadFeedPic: uploadFeedPic,
		feedList: feedList,
		picDetail: picDetail
	}
})();
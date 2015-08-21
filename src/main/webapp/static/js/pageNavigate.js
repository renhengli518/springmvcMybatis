function createPageNumbers(pageCount, currPage, ulid, callback) {
	var $pageNavi = $("#" + ulid);
	$pageNavi.addClass('pageNavi');
	currPage = parseInt(currPage);
	if (pageCount <= 1) {
		$pageNavi.empty();
		return;
	}
	if (currPage == 1) {
		$pageNavi.empty();
		$pageNavi.html( "<a href='javascript:;' class='page_prev nolink' >上一页</a>");
	} else {
		var prev = currPage-1;
		$pageNavi.html( "<a href='javascript:;' class='page_prev' pageNo='" + prev + "' >上一页</a>");
	}
	
	if (pageCount <= 7) {
		for (var i=1; i<=pageCount; i++) {
			if (i==currPage) {
				$pageNavi.append("<a href='javascript:;' class='curr' pageNo='" + i + "' >" +i+ "</a>");
			} else {
				$pageNavi.append("<a href='javascript:;' pageNo='" + i + "' >" +i+ "</a>");
			}
		}
	} else {
		if(currPage<5) {
			for (var i=1; i<=6; i++) {
				if (i==currPage) {
					$pageNavi.append("<a href='javascript:;' class='curr' pageNo='" + i + "' >" +i+ "</a>");
				} else {
					$pageNavi.append("<a href='javascript:;' pageNo='" + i + "' >" +i+ "</a>");
				}
			}
			$pageNavi.append("<a href='javascript:;' class='nolink' >...</a>");
			$pageNavi.append("<a href='javascript:;' pageNo='" + pageCount + "' >" + pageCount + "</a>");
		} else if (currPage>pageCount-4) {
			$pageNavi.append("<a href='javascript:;' pageNo='1' >" + 1 + "</a>");
			$pageNavi.append("<a href='javascript:;' class='nolink' >...</a>");
			for (var i=pageCount-5; i<=pageCount; i++) {
				if (i==currPage) {
					$pageNavi.append("<a href='javascript:;' class='curr' pageNo='" + i + "' >" +i+ "</a>");
				} else {
					$pageNavi.append("<a href='javascript:;' pageNo='" + i + "' >" +i+ "</a>");
				}
			}
		} else {
			$pageNavi.append("<a href='javascript:;' pageNo='1' >" + 1 + "</a>");
			$pageNavi.append("<a href='javascript:;' class='nolink' >...</a>");
			for (var i=currPage-2; i<=currPage+2; i++) {
				if (i==currPage) {
					$pageNavi.append("<a href='javascript:;' class='curr' pageNo='" + i + "' >" +i+ "</a>");
				} else {
					$pageNavi.append("<a href='javascript:;' pageNo='" + i + "' >" +i+ "</a>");
				}
			}
			$pageNavi.append("<a href='javascript:;' class='nolink' >...</a>");
			$pageNavi.append("<a href='javascript:;' pageNo='" + pageCount + "' >" + pageCount + "</a>");
		}
		
	}
	
	if (currPage != pageCount) {
		var next = currPage + 1;
		$pageNavi.append("<a href='javascript:;' class='page_next' pageNo='" + next + "' >下一页</a>") ;
	}else{
		$pageNavi.append("<a href='javascript:;' class='page_next nolink'  >下一页</a>"); 
	}
	
	$pageNavi.append( "去第 <div class='page_go_cont'><input type='input' min='1' class='pageIndex' /><a href='javascript:;' class='page_go'>确定</a></div> 页");
	
	$pageNavi.find("a[pageNo]").click(function(){
		var pageNo = $(this).attr("pageNo");
		if(pageNo) callback(parseInt(pageNo));
	});
	$pageNavi.find(".page_go").click(function(){
		var pageNo = $pageNavi.find(".pageIndex").val() * 1;

		if (typeof pageNo == 'number' && pageNo <= pageCount && pageNo > 0) {
			callback(parseInt(pageNo));
		}
	});
	$pageNavi.on('focus', '.pageIndex', function(e){
          $(this).parent().addClass('showDom');
      });
      $('html').on('click', function(e){
          var pageGoBtn = $pageNavi.find('.page_go')[0];
          var pageGoInput = $pageNavi.find('.pageIndex')[0];
          if (e.target != pageGoInput) {
              if (e.target != pageGoBtn) {
                  $(pageGoBtn).parent().removeClass('showDom');
              } else {
                  return false;
              }
          } else {
              return false;
          }
      });
}
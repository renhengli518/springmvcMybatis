// +----------------------------------------------------------------------+
// | SplitPage.js   分页控件
// +----------------------------------------------------------------------+
// | Author: Neoxone
// +----------------------------------------------------------------------+
// | Site: www.cssass.com
// +----------------------------------------------------------------------+
function SplitPage(config){
    this.pageWrap = config.node;  //容器
    this.pageWrap.className = "pageNavi";
    this.showLimit = config.showLimit || 1; //显示页码数（左1右1）
    this.showGoPage = config.showGoPage ||"YES";//显示去第几页
    this.showTotalPage = config.showTotalPage ||"YES";//显示总页数
    this.count = config.count || 10; //每页显示数
    this.url = config.url;  //请求地址(可带参数)
    this.req = {
        start : 0,
        end : this.count - 1
    };
    extendCopy(config.data || {}, this.req); //请求参数
    this.callback = config.callback;  //请求回调
    this.type = config.type; //支持jsonp
    this.disabled = false;
    this.init();
    this.loadimg = config.loadimg;
}
SplitPage.prototype = {
    init : function(){
        var that = this;
        this.myPageSpan = document.createElement("span");
        this.pageWrap.appendChild(this.myPageSpan);
        this.ajaxGet(function(){
            that.setLinks();
            that.bindLinks();
        });
    },
    search : function(url,data){  //按条件重设分页
        this.pageWrap.innerHTML = "";
        this.url = url;
        this.req = {
            start : 0,
            end : this.count - 1
        };
        extendCopy(data || {}, this.req);
        this.init();
    },
    reload : function(){    //重加载，用于刷新当前分页
        this.ajaxGet(this.setLinks);
    },
    prev : function(){
        this.req.start -= this.count;
        this.req.end -= this.count;
        this.ajaxGet(this.setLinks);
    },
    next : function(){
        this.req.start += this.count;
        this.req.end += this.count;
        this.ajaxGet(this.setLinks);
    },
    getByNum : function(n){
        this.req.start = (n - 1) * this.count;
        this.req.end = n * this.count - 1 ;
        this.ajaxGet(this.setLinks);
    },
    ajaxGet : function(func){
        var that = this;
        if(this.disabled) return;
        this.disabled = true;
        var _apply = function(msg){
            that.msg = msg;
            if(that.callback) that.callback(msg.data,msg.total);
            func.call(that);
            that.disabled = false;
        };
        this.req["t"] = random();
        switch(this.type){
            case "jsonp": //jsonp必然不会是post
                $jsonp(this.url,this.req,_apply);
                break;
            case "post":
                $.post(this.url,this.req, _apply,'json');
                break;
            default :
                $.get(this.url,this.req, _apply,'json');
        };
        if(this.loadimg){
            this.imgShow();
        }
    },
    setLinks : function(){
        this.myPageSpan.innerHTML = "";
        var data = this.msg.data;
        this.totalNum = Math.ceil(this.msg.total/this.count);
        if(this.totalNum < 2) return false;
        var links ="",
            currNum = Math.floor(this.req.end/this.count) + 1,
            startNum = Math.max(2, currNum - this.showLimit),
            endNum = Math.min(this.totalNum-1, currNum + this.showLimit);
        links += "<a href='javascript:;' class='page_prev'>上一页</a>";
        links += "<a href='javascript:;' pid='1' class='page_home'>1</a>";
        //if(this.totalNum && startNum > 2) links += "<a href='javascript:;' class='nolink' >...</a>";
        if(this.totalNum && startNum > 2) links += "...";
        for(var i = startNum; i<= endNum; i++){
            if(i == currNum){
                links += "<a href='javascript:;' class='curr' pid='"+ i +"'>"+ i +"</a>";
            }else{
                links += "<a href='javascript:;' pid='"+ i +"'>"+ i +"</a>";
            }
        };
        //if(endNum < this.totalNum-1) links += "<a href='javascript:;' class='nolink' >...</a>";
        if(endNum < this.totalNum-1) links += "...";
        links += "<a href='javascript:;' pid="+this.totalNum+" class='page_end'>"+this.totalNum+"</a>";
        links += "<a href='javascript:;' class='page_next'>下一页</a>&nbsp;&nbsp;";
        if(this.showTotalPage=="YES"){
        	links +="<span style='display:inline-block;'>共"+this.totalNum+"页&nbsp;&nbsp;</span>";
        }
        if(this.showGoPage=="YES") {
            links += "<span style='display:inline-block;'>去第</span> <div class='page_go_cont'><input type='input' min='1' max="+this.totalNum+" class='pageIndex' /><a href='javascript:;' class='page_go'>确定</a></div><span style='display:inline-block;' > 页</span>";
        }
        this.myPageSpan.innerHTML = links;

        this.midAnchor = $tag("a[pid]", this.myPageSpan);
        this.prevAnchor = $class("page_prev", this.myPageSpan)[0];
        this.nextAnchor = $class("page_next", this.myPageSpan)[0];
        this.homeAnchor = $class("page_home", this.myPageSpan)[0];
        this.endAnchor = $class("page_end", this.myPageSpan)[0];
        this.goAnchor = $class("page_go", this.myPageSpan)[0];
        removeClass($class("nolink"),'nolink');
        if(this.req.start < 1){
            addClass(this.prevAnchor,"nolink");
            addClass(this.homeAnchor,"curr");
        };
        if(this.req.end + 1 >= this.msg.total){
            addClass(this.nextAnchor,"nolink");
            addClass(this.endAnchor,"curr");
        };
        if(this.loadimg){
            this.imgHide();
        }
        window.scrollTo(0,0);  //每次点击返回顶部
    },
    bindLinks : function(){
        var that = this;
        events.delegate(that.myPageSpan, '.page_prev', 'click',function(){
            if(!hasClass(that.prevAnchor,'nolink')) that.prev();
        });
        events.delegate(that.myPageSpan, '.page_next', 'click', function(){
            if(!hasClass(that.nextAnchor,'nolink')) that.next();
        });
        events.delegate(that.myPageSpan, '.page_go', 'click', function(e){
            var n = that.goAnchor.previousSibling.value;
            if(n-0 > 0 && n-0 <= that.totalNum) that.getByNum(n);
            $(this).parent().removeClass('showDom');
        });
        events.delegate(that.myPageSpan,'a', 'click',function(){
            var e = arguments[0] || window.event,
                target = e.srcElement || e.target,
                n = target.getAttribute("pid");
            if(n && !hasClass(target,'curr')) that.getByNum(n);
        });
        $(that.myPageSpan).on('focus', '.pageIndex', function(e){
            $(this).parent().addClass('showDom');
        });
        $('html').on('click', function(e){
            var pageGoBtn = $(that.myPageSpan).find('.page_go')[0];
            var pageGoInput = $(that.myPageSpan).find('.pageIndex')[0];
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
    },
    imgShow : function(){
        this.time = setTimeout(function(){
            this.loadimg.style.display = "block";
        }, 10)
    },
    imgHide : function(){
        var that = this;
        clearTimeout(that.time);
        this.loadimg.style.display = "none";
    }
};

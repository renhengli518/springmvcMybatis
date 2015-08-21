//javascript document

jQuery.extend({
    /**
     * 清除当前选择内容
     */
    unselectContents: function(){
        if(window.getSelection)
            window.getSelection().removeAllRanges();
        else if(document.selection)
            document.selection.empty();
    }
});
jQuery.fn.extend({
    /**
     * 选中内容
     */
    selectContents: function(){
        $(this).each(function(i){
            var node = this;
            var selection, range, doc, win;
            if ((doc = node.ownerDocument) &&
                (win = doc.defaultView) &&
                typeof win.getSelection != 'undefined' &&
                typeof doc.createRange != 'undefined' &&
                (selection = window.getSelection()) &&
                typeof selection.removeAllRanges != 'undefined')
            {
                range = doc.createRange();
                range.selectNode(node);
                if(i == 0){
                    selection.removeAllRanges();
                }
                selection.addRange(range);
            }
            else if (document.body &&
                     typeof document.body.createTextRange != 'undefined' &&
                     (range = document.body.createTextRange()))
            {
                range.moveToElementText(node);
                range.select();
            }
        });
    },
    /**
     * 初始化对象以支持光标处插入内容
     */
    setCaret: function(){
        if(!UA.isIE) return;
        var initSetCaret = function(){
            var textObj = $(this).get(0);
            textObj.caretPos = document.selection.createRange().duplicate();
        };
        $(this)
        .click(initSetCaret)
        .select(initSetCaret)
        .keyup(initSetCaret);
    },
    /**
     * 在当前对象光标处插入指定的内容
     */
    insertAtCaret: function(myValue) {
		return this.each(function() {
			var me = this;
			if (document.selection) { // IE
			me.focus();
			sel = document.selection.createRange();
			sel.text = myValue;
			me.focus();
			} else if (me.selectionStart || me.selectionStart == '0') { // Real browsers
			var startPos = me.selectionStart, endPos = me.selectionEnd, scrollTop = me.scrollTop;
			me.value = me.value.substring(0, startPos) + myValue + me.value.substring(endPos, me.value.length);
			me.focus();
			me.selectionStart = startPos + myValue.length;
			me.selectionEnd = startPos + myValue.length;
			me.scrollTop = scrollTop;
			} else {
			me.value += myValue;
			me.focus();
			}
		});
	}
});
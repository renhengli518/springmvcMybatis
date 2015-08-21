function UE_UI_BLANK (editor,uiName) {
	
	var s4 =  function () {
		return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
	};
	var getUUID = function () {
		return s4() + s4() + s4() + s4()  + s4()  + s4() + s4() + s4();
	};
	
	var hasClass = function (node, name) {
		return node.attrs && node.attrs["class"] && node.attrs["class"].indexOf(name) != -1;
	}
	var me = this;
	me.addInputRule(function (root) {
		 $.each(root.getNodesByTagName('img'), function (i, node) {
           if (hasClass(node, 'question-blank-space')) {
				node.setAttr('onclick', 'return false;');
				node.setAttr('ondragstart', 'return false;');
            }
        });
    });
	me.addOutputRule(function (root) {
		var flag = false;
		var tab = [];
		$(me.body).find('img.question-blank-space').each(function (index) {
			this.src = UEDITOR_CONFIG.UEDITOR_IMAGE_VISIT_URL + "public/js/ueditor/third-party/blank/" + ( index + 1 ) + ".png";
			this.setAttribute('alt', index + 1);
			if (!this.getAttribute('uuid')) {
				this.setAttribute('uuid', getUUID());
			}
			tab[index] = this.getAttribute('uuid');
			if (index > 9) {
				$(this).remove();
				flag = true;
			}
		})	
		if (flag) {
			Win.alert('最多插入10个填空题');
		}
		var id = -1;
		$.each(root.getNodesByTagName('img'), function (i, node) {
           if (hasClass(node, 'question-blank-space')) {
				id++;
				node.setAttr('uuid', tab[id]);
				node.setAttr('src', UEDITOR_CONFIG.UEDITOR_IMAGE_VISIT_URL + "public/js/ueditor/third-party/blank/" + ( id + 1 ) + ".png");
				node.setAttr('ondragstart', '');
				node.setAttr('onclick', '');
				node.setAttr('onfocus', '');			
            }
        });
    });
    var btn = new UE.ui.Button({
        name: uiName,
        title: '插入填空项',
        cssRules: 'border-bottom: 1px solid black;background: none repeat scroll 0 0 #fafafa !important;',
        onclick: function () {
           editor.execCommand(uiName);
        }
    });
     me.commands[uiName] = {
        execCommand: function (cmd, latex) {
			if ($(me.body).find('.question-blank-space').length > 9) {
				Win.alert('最多支持插入10个空');
				return;
			}
			me.execCommand('inserthtml', '<img class="question-blank-space edui-faked-music" onfocus="return false;" ondragstart="return false;" onclick="return false;"/>');
        },
		queryCommandState: function (cmd) {
            return 0;
        },
        queryCommandValue: function (cmd) {
            return false;
        }
    }
    return btn;
};
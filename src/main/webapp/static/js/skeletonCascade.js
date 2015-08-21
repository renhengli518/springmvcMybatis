var skeleton = {};
skeleton.option={
	root:"",
	se : { id : "", def : "" , data:"", change: ""},//学段
	l  : { id : "", def : "" , data:"", change: ""},//年级
	d  : { id : "", def : "" , data:"", change: "", clear:""},//学科
	v  : { id : "", def : "" , data:"", change: "", clear:""},//分册
	ch : { id : "", def : "" , data:"", change: "", clear:""},//章节
	kn : { parent:"" , id:"", def:"" , data:"", change:"", clear:""},//知识点
	tc : { parent:"" , id:"", def:"" , data:"", change:"", clear:"", type:""}//目录
};

skeleton.load = function() {
	if (skeleton.option.se.id == "" || skeleton.option.d.id == "") return;
	if (skeleton.option.se.id != "") {
		$("#" + skeleton.option.se.id).bind("change", skeleton.onSemesterChange); //学段更新绑定
	}
	
	if (skeleton.option.l.id != "") {
		$("#" + skeleton.option.l.id).bind("change", skeleton.onClasslevelChange); //年级更新绑定
	}
	
	if (skeleton.option.d.id != "") {
		$("#" + skeleton.option.d.id).bind("change", skeleton.onDisciplineChange); //学科更新绑定
	}
	
	if (skeleton.option.v.id != "") {
		$("#" + skeleton.option.v.id).bind("change", skeleton.onVolumeChange); //分册更新绑定
	}
	
	if (skeleton.option.ch.id != "" && skeleton.option.ch.change != "") {
		$("#" + skeleton.option.ch.id).bind("change", skeleton.option.ch.change);
	}
	
	if (skeleton.option.kn.id != "") {
		$("#" + skeleton.option.kn.id).bind("change", skeleton.onKnowledgeChange);
	}
	
	if (skeleton.option.tc.id != "") {
		$("#" + skeleton.option.tc.id).bind("change", skeleton.onTeachCatalogChange);
	}
	
//	if (skeleton.option.et.id != "") {
//		$("#" + skeleton.option.et.id).bind("change", skeleton.onExamTypeChange);
//	}
	
	$.post(skeleton.option.root + "/skeleton/getSkeletonInfos.do",
			{'semesterId': skeleton.option.se.def,
			'classlevelId': skeleton.option.l.def,
			'disciplineId': skeleton.option.d.def,
			'volumeId': skeleton.option.v.def},
			function(data) {
				var semesters = data.semesters;
				if (skeleton.option.se.id != "" && semesters != undefined && semesters.length > 0) {
					skeleton.appendOptions(skeleton.option.se.id, semesters, "semesterName", skeleton.option.se.def);
				}
				
				var classlevels = data.classlevels;
				if (skeleton.option.l.id != "") {
					if (classlevels != undefined && classlevels.length > 0) {
						skeleton.appendOptions(skeleton.option.l.id, classlevels, "classLevelName", skeleton.option.l.def);
					} else {
						$("#" + skeleton.option.l.id).val("");
					}
				}
				
				var disciplines = data.disciplines;
				if (skeleton.option.d.id !="") {
					if (disciplines != undefined && disciplines.length > 0) {
						skeleton.appendOptions(skeleton.option.d.id, disciplines, "disciplineName", skeleton.option.d.def);
					} else {
						$("#" + skeleton.option.d.id).val("");
					}
				}
				
				var volumes = data.volumes;
				if (skeleton.option.v.id != "") {
					if (volumes != undefined && volumes.length > 0) {
						skeleton.appendOptions(skeleton.option.v.id, volumes, "volumeName", skeleton.option.v.def);
					} else {
						$("#" + skeleton.option.v.id).val("");
					}
				}
				
				var chapters = data.chapters;
				if (skeleton.option.ch.id != "") {
					if (chapters != undefined && chapters.length > 0) {
						skeleton.appendOptions(skeleton.option.ch.id, chapters, "chapterName", skeleton.option.ch.def);
					} else {
						$("#" + skeleton.option.ch.id).val("");
					}
				}
				
				var knowledges = data.knowledges;
				if (skeleton.option.kn.id != "" && knowledges != undefined && knowledges.length > 0) {
					skeleton.appendOptions(skeleton.option.kn.id, knowledges, "knowledgeName", skeleton.option.kn.def);
				}
				
				var teachCatalogs = data.teachCatalogs;
				if (skeleton.option.tc.id != "" && teachCatalogs != undefined && teachCatalogs.length > 0) {
					skeleton.appendOptions(skeleton.option.tc.id, teachCatalogs, skeleton.option.tc.def, "");
				}
			}
	);
};

skeleton.onSemesterChange = function() {
	skeleton.clearClasslevels();
	skeleton.obtainClasslevels();
	if (skeleton.option.se.change != "") {
		skeleton.option.se.change();
	}
};

skeleton.onClasslevelChange = function() {
	skeleton.clearDisciplines();
	skeleton.obtainDisciplines();
	if (skeleton.option.l.change != "") {
		skeleton.option.l.change();
	}
};

skeleton.onDisciplineChange = function() {
	skeleton.clearVolumes();
	skeleton.clearKnowledges();
	skeleton.clearTeacherCatalogs();
	skeleton.obtainInfosByDiscipline();
	if (skeleton.option.d.change != "") {
		skeleton.option.d.change();
	}
};

skeleton.onVolumeChange = function() {
	skeleton.clearChapters();
	skeleton.obtainChapters();
	if (skeleton.option.v.change != "") {
		skeleton.option.v.change();
	}
};

skeleton.onKnowledgeChange = function() {
	var knowledgeId = $(this).val();
	
	var $currItem = {};
	if (this.id == skeleton.option.kn.id) {
		$currItem = $(this);
	} else {
		$currItem = $(this).parent();
	}
	
	//清除子知识点select
	var knCount = $(".kns").length;
	if (knCount > 0) {
		var currIdStr = this.id;
		if (currIdStr == skeleton.option.kn.id) {
			$(".kns:eq(0) select option").remove();
			$(".kns:eq(0) select").append("<option value=\"\">请选择</option>");
			$(".kns:gt(0)").remove();
		} else {
			$currItem.next(".kns").find("select option:gt(0)").remove();
			$currItem.nextAll(".kns").filter(":gt(0)").remove();
		}
	}
	
	if (knowledgeId == 0 || knowledgeId == "") {
		$currItem.next(".kns").remove();
		return;
	}
	
	$.post(skeleton.option.root + "/skeleton/getKnowledges.do", {"parentId": knowledgeId}, function(data) {
		if (data.length == 0) {
			$currItem.next(".kns").remove();
			return;
		}
		if ($currItem.next(".kns").length == 0) {
			$("#" + skeleton.option.kn.parent).append("<span class='kns'><label>子知识点： </label><select>"
					+ "<option value=\"\">请选择</option></select></span>");
		}
		
		var nameProperty = "knowledgeName";
		var $select = $currItem.next(".kns").children("select");
		$select.children("option:gt(0)").remove();
		for (var i=0; i < data.length; i++) {
			var item = data[i];
			$select.append("<option value=\"" + item.id + "\">" + item[ nameProperty] + "</option>");
		}
		$select.bind("change", skeleton.onKnowledgeChange);
	});
};

skeleton.onTeachCatalogChange = function() {
	var teachCatalogId = $(this).val();
	
	var $currItem = {};
	if (this.id == skeleton.option.tc.id) {
		$currItem = $(this);
	} else {
		$currItem = $(this).parent();
	}
	
	//清除子知识点select
	var tcCount = $(".tcs").length;
	if (tcCount > 0) {
		var currIdStr = this.id;
		if (currIdStr == skeleton.option.tc.id) {
			$(".tcs:eq(0) select option").remove();
			$(".tcs:eq(0) select").append("<option value=\"\">请选择</option>");
			$(".tcs:gt(0)").remove();
		} else {
			$currItem.next(".tcs").find("select option:gt(0)").remove();
			$currItem.nextAll(".tcs").filter(":gt(0)").remove();
		}
	}
	
	if (teachCatalogId == 0 || teachCatalogId == "") {
		$currItem.next(".tcs").remove();
		return;
	}
	
	$.post(skeleton.option.root + "/skeleton/getTeachCatalogs.do", {"parentId": teachCatalogId}, function(data) {
		if (data.length == 0) {
			$currItem.next(".tcs").remove();
			return;
		}
		if ($currItem.next(".tcs").length == 0) {
			$("#" + skeleton.option.tc.parent).append("<span class='tcs'><label>子类型： </label><select>"
					+ "<option value=\"\">请选择</option></select></span>");
		}
		
		var nameProperty = "catalogName";
		var $select = $currItem.next(".tcs").children("select");
		$select.children("option:gt(0)").remove();
		for (var i=0; i < data.length; i++) {
			var item = data[i];
			$select.append("<option value=\"" + item.id + "\">" + item[ nameProperty] + "</option>");
		}
		$select.bind("change", skeleton.onTeachCatalogChange);
	});
};

skeleton.onExamTypeChange = function() {
	var examTypeId = $(this).val();
	
	var $currItem = {};
	if (this.id == skeleton.option.et.id) {
		$currItem = $(this);
	} else {
		$currItem = $(this).parent();
	}
	
	//清除子试卷类型
	var knCount = $(".ets").length;
	if (knCount > 0) {
		var currIdStr = this.id;
		if (currIdStr == skeleton.option.kn.id) {
			$(".ets:eq(0) select option").remove();
			$(".ets:eq(0) select").append("<option value=\"\">请选择</option>");
			$(".ets:gt(0)").remove();
		} else {
			$currItem.next(".ets").find("select option:gt(0)").remove();
			$currItem.nextAll(".ets").filter(":gt(0)").remove();
		}
	}
	
	if (examTypeId == 0 || examTypeId == "") {
		$currItem.next(".ets").remove();
		return;
	}
	
	$.post(skeleton.option.root + "/skeleton/getExamTypes.do", {"parentId": examTypeId}, function(data) {
		if (data.length == 0) {
			$currItem.next(".ets").remove();
			return;
		}
		if ($currItem.next(".ets").length == 0) {
			$("#" + skeleton.option.et.parent).append("<span class='ets'><label>子考试类型： </label><select>"
					+ "<option value=\"0\">请选择</option></select></span>");
		}
		
		var $select = $currItem.next(".ets").children("select");
		$select.children("option:gt(0)").remove();
		for (var i=0; i < data.length; i++) {
			var item = data[i];
			$select.append("<option value=\"" + item.id + "\">" + item.name + "</option>");
		}
		$select.bind("change", skeleton.onExamTypeChange);
	});
};

skeleton.clearClasslevels = function() {
	skeleton.clearDisciplines();
	skeleton.clearOption( skeleton.option.l.id);
};

skeleton.clearDisciplines = function() {
	skeleton.clearVolumes(); // 清除分册
	skeleton.clearKnowledges(); // 清除知识点
	skeleton.clearTeacherCatalogs();
	skeleton.clearOption( skeleton.option.d.id);
	if (skeleton.option.d.clear != "") {
		skeleton.option.d.clear();
	}
};

skeleton.clearVolumes = function() {
	skeleton.clearChapters();
	skeleton.clearOption( skeleton.option.v.id);
};

skeleton.clearChapters = function() {
	skeleton.clearOption( skeleton.option.ch.id);
};

skeleton.clearKnowledges = function() {
	skeleton.clearChildKnSelects();//清除子知识点select
	skeleton.clearOption( skeleton.option.kn.id);
};

skeleton.clearTeacherCatalogs = function() {
	skeleton.clearChildTcSelects();
	skeleton.clearOption( skeleton.option.tc.id);
};

skeleton.obtainClasslevels = function() {
	var semesterId = $("#" + skeleton.option.se.id).val();
	if (semesterId == 0) return;
	$.post(skeleton.option.root + "/skeleton/getClasslevels.do", {"semesterId": semesterId}, function(data) {
		skeleton.appendOptions(skeleton.option.l.id, data, "classLevelName");
	});
};

skeleton.obtainDisciplines = function() {
	var classlevelId = $("#" + skeleton.option.l.id).val();
	if (classlevelId == 0) return;
	$.post(skeleton.option.root + "/skeleton/getDisciplines.do", {"classlevelId": classlevelId}, function(data) {
		skeleton.appendOptions(skeleton.option.d.id, data, "disciplineName");
	});
};

//获取分册
skeleton.obtainVolumes = function() {
	var classlevelId = $("#" + skeleton.option.l.id).val();
	var disciplineId = $("#" + skeleton.option.d.id).val();
	if (classlevelId == 0 || disciplineId == 0) return;
	$.post(skeleton.option.root + "/skeleton/getVolumes.do", {"classlevelId": classlevelId, "disciplineId": disciplineId}, function(data) {
		skeleton.appendOptions(skeleton.option.v.id, data, "volumeName");
	});
};

//获取章节
skeleton.obtainChapters = function() {
	var volumeId = $("#" + skeleton.option.v.id).val();
	if (volumeId == 0 || volumeId == "") return;
	$.post(skeleton.option.root + "/skeleton/getChapters.do", {"volumeId": volumeId}, function(data) {
		skeleton.appendOptions(skeleton.option.ch.id, data, "chapterName");
	});
};

skeleton.obtainKnowledges = function() {
	var semesterId = $("#" + skeleton.option.se.id).val();
	var disciplineId = $("#" + skeleton.option.d.id).val();
	if (semesterId == 0 || disciplineId == 0 || semesterId == "" || disciplineId == "") return;
	$.post(skeleton.option.root + "/skeleton/getRootKnowledges.do", 
		{"semesterId": semesterId, "disciplineId": disciplineId},
		function(data) {
			skeleton.appendOptions(skeleton.option.kn.id, data, "knowledgeName");
		}
	);
};

skeleton.obtainTeachCatalogs = function() {
	var semesterId = $("#" + skeleton.option.se.id).val();
	var disciplineId = $("#" + skeleton.option.d.id).val();
	if (semesterId == 0 || disciplineId == 0 || semesterId == "" || disciplineId == "") return;
	$.post(skeleton.option.root + "/skeleton/getRootTeachCatalogs.do", 
		{"semesterId": semesterId, "disciplineId": disciplineId},
		function(data) {
			skeleton.appendOptions(skeleton.option.tc.id, data, "catalogName");
		}
	);
};

skeleton.obtainInfosByDiscipline = function() {
	var semesterId = $("#" + skeleton.option.se.id).val();
	var classlevelId = $("#" + skeleton.option.l.id).val();
	var disciplineId = $("#" + skeleton.option.d.id).val();
	if (semesterId == 0 || semesterId == "" 
			|| classlevelId == 0 || classlevelId == ""
			|| disciplineId == 0 || disciplineId == "") return;
	var needVolume = false;
	var needKnowledge = false;
	var needTeachCatalog = false;
	if (skeleton.option.v.id != "") needVolume = true;
	if (skeleton.option.kn.id != "") needKnowledge = true;
	if (skeleton.option.tc.id != "") needTeachCatalog = true;
	var teachCatalogType = skeleton.option.tc.type;
	$.post(skeleton.option.root + "/skeleton/getInfosByDiscipline.do", 
		{"semesterId": semesterId, "classlevelId": classlevelId, "disciplineId": disciplineId, 
		"needVolume" : needVolume, "needKnowledge" : needKnowledge,
		"needTeachCatalog": needTeachCatalog, "type": teachCatalogType},
		function(data) {
			var volumes = data.volumes;
			if (skeleton.option.v.id != "" && volumes != undefined && volumes.length > 0) {
				skeleton.appendOptions(skeleton.option.v.id, volumes, "volumeName");
			}
			
			var knowledges = data.knowledges;
			if (skeleton.option.kn.id != "" && knowledges != undefined && knowledges.length > 0) {
				skeleton.appendOptions(skeleton.option.kn.id, knowledges, "knowledgeName");
			}
			
			var teachCatalogs = data.teachCatalogs;
			if (skeleton.option.tc.id != "" && teachCatalogs != undefined && teachCatalogs.length > 0) {
				skeleton.appendOptions(skeleton.option.tc.id, teachCatalogs, "catalogName");
			}
		}
	);
};

skeleton.obtainChildKnowledges = function(knowledgeId) {
	$.post(skeleton.option.root + "/skeleton/getKnowledges.do", {"parentId": knowledgeId}, function(data) {
		if (data.length == 0) return;
		var appendId = "knowledge" + $(".kns").length;
		$("#" + skeleton.option.kn.parent).append("<label>子知识点： </label><select id=\""
				+ appendId
				+ "\" class='kns'><option value=\"\">请选择</option></select>");
		skeleton.appendOptions( appendId, data);
		$("#" + appendId).bind("change", skeleton.onKnowledgeChange);
	});
};

skeleton.obtainChildTeachCatalogs = function(teachCatalogId) {
	$.post(skeleton.option.root + "/skeleton/getTeachCatalogs.do", {"parentId": teachCatalogId}, function(data) {
		if (data.length == 0) return;
		var appendId = "teachCatalog" + $(".tcs").length;
		$("#" + skeleton.option.tc.parent).append("<label>子类型： </label><select id=\""
				+ appendId
				+ "\" class='tcs'><option value=\"\">请选择</option></select>");
		skeleton.appendOptions( appendId, data);
		$("#" + appendId).bind("change", skeleton.onTeachCatalogChange);
	});
};

skeleton.clearChildKnSelects = function() {
	$(".kns").remove();
};

skeleton.clearChildTcSelects = function() {
	$(".tcs").remove();
};

skeleton.appendOptions = function(_selectId, _items, proname, _def) {
	if (proname == undefined) proname = "name";
	for (var i=0; i < _items.length; i++) {
		var item = _items[i];
		$("#" + _selectId).append("<option value=\"" + item.id + "\" " + (item.id==_def ? "selected":"") + ">" + item[proname] + "</option>");
	}
};

skeleton.clearOption = function(_id) {
	if (_id == undefined || _id == "") return;
	
	$("#" + _id + " option:gt(0)").remove();//删除除了“请选择”的其它所有项
};

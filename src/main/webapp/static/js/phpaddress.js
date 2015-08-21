$(document).ready(function(){
	if (/Android|IPad/i.test(UA.ua)) {
		$(".normalTable .phpaddress").attr("href", "myCodyyAir://10.1.201.62:80?mid=4");
		$(".normalTable .phpaddress2").attr("href", "myCodyyAir://10.1.201.62:80?mid=4");
	} else {
		$(".normalTable .phpaddress").attr("href", "http://10.1.201.62:80/index.php?a=api&type=adminJoinClassRoom&mid=4");
		$(".normalTable .phpaddress2").attr("href", "http://10.1.201.62:80/index.php?a=api&type=guestJoinClassRoom&code=8304&mid=4");
	}
});
//phpaddress是电教馆、教育局和学校
//phpaddress2是老师，学生，家长
// 电教馆、教育局和学校
// <a href="http://10.1.100.222/index.php?a=api&type=adminJoinClassRoom&mid=1280" target="_blank">超级管理员进入课堂观摩</a>
// 老师，学生，家长
// <a href="http://10.1.100.222/index.php?a=api&type=guestJoinClassRoom&code=9907&mid=1280" target="_blank">来宾进入课堂观摩</a>

//公司，主课堂
//电教馆、教育局和学校
//http://117.79.148.117:8081/index.php?a=api&type=adminJoinClassRoom&mid=338
//老师，学生，家长
//http://117.79.148.117:8081/index.php?a=api&type=guestJoinClassRoom&code=8135&mid=338

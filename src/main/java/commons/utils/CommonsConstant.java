package commons.utils;

import org.apache.commons.lang.StringUtils;

public class CommonsConstant {
	public static final String FLAG_YES = "Y";
	// DELETE_FLAG已删除
	// LOCKED已经锁定无法再登录
	// BASE_USER.ADMIN_FLAG区县管理员或学校管理员
	// BASE_AREA.EDIT_SCHEDULE区域用户可以编辑课表
	// BASE_AREA.SOFTWARE_UPGRADE区域教室自动更新软件
	// BASE_AREA.CREATE_SCHOOL区域下可以挂学校
	// CLS_CLASSROOM.WHITE_PAD_SWITCH, CLS_SCHEDULE_DETAIL.WHITE_PAD_SWITCH白板打开
	// CLS_CLASSROOM.RECORD_SWITCH, CLS_SCHEDULE_DETAIL.RECORD_SWITCH录制打开
	// CLS_CLASSROOM.REMOTE_SWITCH远程导播打开
	// CLS_SCHEDUEL_DETAIL.SELF_DEFINE自主开课
	// EVA_STANDARD.SYSTEM_PROVIDED系统默认评课标准

	public static final String FLAG_NO = "N";
	// DELETE_FLAG未删除
	// LOCKED未锁定可以登录
	// BASE_USER.ADMIN_FLAG区县普通用户或学校普通用户
	// BASE_AREA.EDIT_SCHEDULE区域用户不能编辑课表
	// BASE_AREA.SOFTWARE_UPGRADE区域教室不更新软件
	// BASE_AREA.CREATE_SCHOOL区域下不能挂学校
	// CLS_CLASSROOM.WHITE_PAD_SWITCH, CLS_SCHEDULE_DETAIL.WHITE_PAD_SWITCH白板关闭
	// CLS_CLASSROOM.RECORD_SWITCH, CLS_SCHEDULE_DETAIL.RECORD_SWITCH录制关闭
	// CLS_CLASSROOM.REMOTE_SWITCH远程导播关闭
	// CLS_SCHEDUEL_DETAIL.SELF_DEFINE课表开课
	// EVA_STANDARD.SYSTEM_PROVIDED非系统默认评课标准

	// BASE_USER的用户类型
	public static final String USER_TYPE_AREA_USER = "AREA_USR";// 行政机构用户
	public static final String USER_TYPE_SCHOOL_USER = "SCHOOL_USR";// 学校用户
	public static final String USER_TYPE_TEACHER = "TEACHER";// 教师
	public static final String USER_TYPE_STUDENT = "STUDENT";// 学生
	public static final String USER_TYPE_PARENT = "PARENT";// 家长

	// BASE_USER.CREATE_USER_TYPE用户的创建人类型
	public static final String CREATE_USER_TYPE_BASE_ADMIN = "ADMIN_USER";// 管理员用户创建
	public static final String CREATE_USER_TYPE_BASE_USER = "BASE_USER";// BaseUser创建

	// CLS_CLASSROMM.ROOM_TYPE教室类型
	public static final String CLASSROOM_TYPE_MASTER = "MASTER";// 主讲教室
	public static final String CLASSROOM_TYPE_RECEIVE = "RECEIVE";// 接收教室

	// CLS_SCHEDULE_DETAIL.STATUS课堂状态
	// CLS_SCHEDULE_DETAIL_RECEIVE.STATUS课堂状态
	public static final String SCHE_DETAIL_INIT = "INIT";// 未开始,
	public static final String SCHE_DETAIL_PROGRESS = "PROGRESS";// 进行中
	public static final String SCHE_DETAIL_END = "END";// 已结束
	public static final String SCHE_DETAIL_CANCEL = "CANCEL";// 取消

	// LIVE_APPOINTMENT.STATUS课堂状态
	// LIVE_APPOINTMENT_RECEIVE.STATUS课堂状态
	public static final String LIVE_INIT = "INIT";
	public static final String LIVE_PROGRESS = "PROGRESS";// 进行中
	public static final String LIVE_END = "END";// 已结束
	public static final String LIVE_CANCEL = "CANCEL";// 取消

	// DOWNLOAD_FILE_INVOKE_TYPE
	// BASE_CLASS_WORK
	public static final String INVOKE_TYPE_ONLINE_CLASS = "ONLINE_CLASS";// 在线课堂
	public static final String INVOKE_TYPE_LIVE = "LIVE";// 直录播

	// CLS_SCHEDULE_DETAIL.VIDEO_UPLOAD状态
	public static final String VIDEO_UPLOAD_N = "N";// 初始状态 ：未上传
	public static final String VIDEO_UPLOAD_F = "F";// 上传失败
	public static final String VIDEO_UPLOAD_Y = "Y";// 已经上传
	public static final String VIDEO_UPLOAD_S = "S";// 转换成功

	// CLS_SCHEDULE_DETAIL_GUEST.GUEST_TYPE课堂来宾类型
	public static final String GUEST_TYPE_GUEST = "GUEST";// 课堂来宾
	public static final String GUEST_TYPE_WATCH = "WATCH";// 观摩用户

	// MAL_DETAIL.STATUS报修状态
	public static final String MAL_DETAIL_NEW = "NEW";// 待处理
	public static final String MAL_DETAIL_PROGRESS = "PROGRESS";// 处理中
	public static final String MAL_DETAIL_DONE = "DONE";// 已处理
	public static final String MAL_DETAIL_VERFIED = "VERIFIED";// 已验收

	// EVA_EVALUATION.STATUS评课状态
	public static final String EVALUATION_STATUS_INIT = "INIT";// 未开始
	public static final String EVALUATION_STATUS_PROGRESS = "PROGRESS";// 进行中
	public static final String EVALUATION_STATUS_END = "END";// 已结束

	// EVA_EVALUATION.EVA_TYPE评课类型
	public static final String EVALUATION_TYPE_LIVE = "LIVE";// 直播
	public static final String EVALUATION_TYPE_VIDEO = "VIDEO";// 录播

	// EVA_EVALUATION.SCORE_TYPE评课的评分类型
	public static final String EVALUATION_SCORE_TYPE_ADD_ALL = "ADD_ALL";// 所有相加取平均数
	public static final String EVALUATION_SCORE_TYPE_REMOVE_SIDE = "REMOVE_SIDE";// 多于五个评分去掉最高和最低再取平均数

	// EVA_RECEIVE_SCHOOL.STATUS评课接收学校的处理状态
	public static final String EVALUATION_RECEIVE_SCHOOL_STATUS_WAIT = "WAIT";// 待处理
	public static final String EVALUATION_RECEIVE_SCHOOL_STATUS_REJECT = "REJECT";// 已拒绝
	public static final String EVALUATION_RECEIVE_SCHOOL_STATUS_ACCEPT = "ACCEPT";// 已接受

	// EVA_STANDARD.AVAILABLE_SCOPE评分标准可用范围
	public static final String EVA_STANDARD_SCOPE_PUBLIC = "PUBLIC";// 公开
	public static final String EVA_STANDARD_SCOPE_PRIVATE = "PRIVATE";// 私有

	// EVA_RECOMMEND_TYPE评课议课推荐类型
	public static final String EVA_RECOMMEND_TYPE_AREA = "AREA";// 区域
	public static final String EVA_RECOMMEND_TYPE_SCHOOL = "SCHOOL";// 学校

	public static final String SCHOOL_DIRECTLY = "DIRECTLY"; // 直属学校
	public static final String SCHOOL_UNDIRECTLY = "UNDIRECTLY";// 非直属学校

	// 默认头像
	public static final String HEAD_PIC_DEFAULT = "headPicDefault.jpg";
	// 默认缩略图
	public static final String THUMB_PIC_DEFAULT = "thumbPicDefault.jpg";
	// 批阅过后的课堂作业名字包含字符
	public static final String CLASS_WORK_REVIEW_PIC = "review";
	// 授课平台配置_redis缓存路径
	public static final String PLATFORM_CONFIG_REDIS = "platConfig:";
	public static final String DEFAULT_PLATFORM_CONFIG = "defaultConfig:platform:";
	// 前台页面配置_区域_redis缓存路径
	public static final String FRONT_CONFIG_AREA_REDIS = "frontConfig:area:";
	// 前台页面配置_信息_redis缓存路径
	public static final String FRONT_CONFIG_SCHOOL_REDIS = "frontConfig:school:";
	public static final String DEFAULT_FRONT_CONFIG = "defaultConfig:front:";

	public static final String ERROR_PAGE_404 = "common/error/404";

	// 默认密码
	public static final String CLASSROOM_PASSWORD_DEFAULT = "666666";

	// 资源转换标记
	public static final String TRANS_PENDDING = "TRANS_PENDDING";// 【视频：等待获取基本信息，文档：等待转换】
	public static final String TRANS_CAPTURING = "TRANS_CAPTURING";// 视频基本信息获取中
	public static final String TRANS_CAPTURED = "TRANS_CAPTURED";// 获取基本信息成功，等待转换。只针对视频
	public static final String TRANS_TRANSING = "TRANS_TRANSING";// 转换中
	public static final String TRANS_SUCCESS = "TRANS_SUCCESS";// 转换成功
	public static final String TRANS_FAILED = "TRANS_FAILED";// 转换失败[包含获取基本信息失败的数据]

	// 下载标记
	public static final String DOWNLOADING = "DOWNLOADING";// 资源下载中
	public static final String DOWNLOAD_FAILED = "FAILED";// 失败
	public static final String DOWNLOAD_SUCCESS = "SUCCESS";// 成功

	// 下载类型
	public static final String DOWNLOAD_TYPE_ONLINECLASS = "onlineClass";// 在线课堂
	public static final String DOWNLOAD_TYPE_LIVE = "Live";// 直录播
	public static final String DOWNLOAD_TYPE_MEET = "meeting";// 视频会议

	// 资源来源
	public static final String RES_SOURCE_TYPE_UPLOAD = "UPLOAD";
	public static final String RES_SOURCE_TYPE_TRANSFER_CLASS = "TRANS_CLASS";
	public static final String RES_SOURCE_TYPE_TRANSFER_APPOINTMENT = "TRANS_APP";

	// 资源推荐审核状态
	public static final String RES_REVIEW_STATUS_PENDING = "PENDING";
	public static final String RES_REVIEW_STATUS_PASSED = "PASSED";
	public static final String RES_REVIEW_STATUS_FAILED = "FAILED";

	// 资源推荐类型：
	public static final String TEA_TO_SCH_UP = "TEA_TO_SCH_UP"; // 1.老师推荐到上级学校
	public static final String SCH_TO_SCH_HOME = "SCH_TO_SCH_HOME"; // 2.学校推荐到学校主页
	public static final String SCH_TO_AREA_UP = "SCH_TO_AREA_UP"; // 3.学校推荐到上级区域
	public static final String AREA_TO_AREA_UP = "AREA_TO_AREA_UP";// 4.区域推荐到上级区域
	public static final String AREA_TO_AREA_HOME = "AREA_TO_AREA_HOME";// 5.区域推荐到区域主页
	public static final String STU_TO_TEA_UP = "STU_TO_TEA_UP";// 6.学生推荐资源到班主任
	public static final String SCH_TO_SCH_CHANNEL = "SCH_TO_SCH_CHANNEL";// 7.学校推送资源到频道
	public static final String AREA_TO_AREA_CHANNEL = "AREA_TO_AREA_CHANNEL";// 8.区域推送资源到频道

	// 约课推荐类型：
	public static final String APPOINTMENT_RECOMMEND_TYPE_AREA = "AREA_CHANNEL"; // 1.区域推荐到区域频道页
	public static final String APPOINTMENT_RECOMMEND_TYPE_SCHOOL = "SCHOOL_CHANNEL"; // 2.学校推荐到学校频道页

	// 平台类型
	public static final String PLATFORM_HOME = "PLATFORM_HOME";// 1.平台主页
	public static final String PLATFORM_CHANNEL = "PLATFORM_CHANNEL";// 2.平台频道

	// 系统参数定义
	public static final String CONFIG_SYS_TITLE = "sys.title"; // 系统标题
	public static final String CONFIG_SYS_FOOTER = "sys.footer"; // 底部版权声明定义
	// 是否开启首页主讲课堂数等数值的自定义,不开启则从数据库计算,开启则从配置文件中读取, Y=开启, N=关闭
	public static final String CONFIG_SYS_CLASS_NUMBER_DEFINE = "sys.classNumber";
	// public static final String CONFIG_DCLASS_VALID_DURATION =
	// "1200";//统计课堂时,超过多长时间的算有效课堂,单位秒

	// session名称定义
	public static final String SESSION_USER = "SESSION_USER"; // SessionUser对象,
																// 用于用户中心,资源,评课,在线课堂模块
	public static final String SESSION_STATE = "SESSION_STATE"; // String对象,
																// 单点登录时的state值,用于单点登录中的重放攻击
	public static final String SESSION_IMAGES = "SESSION_IMAGES";// List<String>对象,存储用户已经上传的图片文件地址,保存后应该从List中清除已经保存的图片文件
	public static final String SESSION_RES_FILE_ITEM = "SESSION_RES_FILE_ITEM";// 资源项目中上传的文件回话key
	public static final String SESSION_ANDROID_FILE = "SESSION_ANDROID_FILE";// List<String>对象,存储用户已经上传的文件地址,保存后应该从List中清除已经保存的文件
	public static final String COOKIE_USERNAME = "COOKIE_USERNAME"; // 自动登录时的cookie
																	// 用户名
	public static final String COOKIE_REMEMBER_TAG = "COOKIE_REMEMBER_TAG"; // 自动登录时的cookie
																			// 标识
	public static final String COOKIE_HOME = "COOKIE_HOME"; 	// 在UserCenter模块记录用户访问的门户地址
	public static final String COOKIE_HOME_LOGOUT = "COOKIE_HOME_LOGOUT"; // 在UserCenter模块记录Home的退出地址

	// 其余分割符
	public static final String AREA_SPLIT = "-";

	// 直录播统计数据类型
	public static final String CLASS_STA_AREA = "AREA"; // 地区
	public static final String CLASS_STA_D_SCH = "DSCH";// 直属学校
	public static final String CLASS_STA_SCH = "SCH";// 学校

	// 统计课程状态
	public static final String CLASS_STA_STATUS_VALID = "VALID";// 有效授课：正常已结束的课程
	public static final String CLASS_STA_STATUS_INVALID = "INVALID";// 无效授课：小于30分钟的课程
	public static final String CLASS_STA_STATUS_REASONABLE_MISSED = "REASONABLE_MISSED";// 因故停课：已取消的课程
	public static final String CLASS_STA_STATUS_UNREASONABLE_MISSED = "UNREASONABLE_MISSED";// 无故停课：当前时间前的未开始的课程
	public static final String CLASS_STA_STATUS_UNJUDGED = "UNJUDGED";//未判定

	// 移动端常量
	public static final String RESULT_SUCCESS = "success";
	public static final String RESULT_ERROR = "error";
	
	public static final String MOBILE_MEETING_DMC_CODE = "meeting";

	// 系统配置参数
	public static final String SYS_CONFIG_SYS_CLASSNUMBER = "sys.classNumber";
	public static final String SYS_CONFIG_CLASS_VALIDDURATION = "class.validDuration";
	public static final String SYS_CONFIG_CLASS_REFRESHTIME = "class.refreshTime";
	public static final String SYS_CONFIG_SYS_TITLE = "sys.title";
	public static final String SYS_CONFIG_SYS_FOOTER = "sys.footer";

	public static final String SYS_CONFIG_SYS_MAINCLASSNUMBER = "sys.mainClassNumber";
	public static final String SYS_CONFIG_SYS_RECEIVECLASSNUMBER = "sys.receiveClassNumber";
	public static final String SYS_CONFIG_SYS_SEMESTER_TOTALCLASSNUMBER = "sys.semeterTotalClassNumber";
	public static final String SYS_CONFIG_SYS_SEMESTER_STUDENTNUMBER = "sys.semeterStudentNumber";
	public static final String SYS_CONFIG_SYS_SEMESTER_TEACHERNUMBER = "sys.semeterTeacherNumber";
	public static final String SYS_CONFIG_SYS_WEEK_CLASSNUMBER = "sys.weekClassNumber";
	public static final String SYS_CONFIG_SYS_WEEK_PLANCLASSNUMBER = "sys.weekPlanClassNumber";
	public static final String SYS_CONFIG_SYS_WEEK_TAUGHTCLASSNUMBER = "sys.weekTaughtClassNumber";

	public static final String MEET_CONFIG = "MEET-CONFIG:";
	public static final String SYS_CONFIG = "SYS-CONFIG:";
	public static final String MEET_CONFIG_MEET = "meetConfig";
	public static final String MEET_CONFIG_LICENSE = "license";

	// 资讯类型
	public static final String INFO_TYPE_NEWS = "NEWS"; // 新闻
	public static final String INFO_TYPE_ANNOUNCEMENT = "ANNOUNCEMENT"; // 公告
	public static final String INFO_TYPE_NOTICE = "NOTICE"; // 通知

	// 资讯创建者所属机构
	public static final String CREATE_TYPE_AREA = "AREA"; // 区域
	public static final String CREATE_TYPE_SCHOOL = "SCHOOL"; // 学校

	// 作业类型
	public static final String CLASS_TYPE_ONLINE_CLASS = "ONLINE_CLASS"; // 在线课堂
	public static final String CLASS_TYPE_LIVE = "LIVE"; // 直录播

	// 会议类型(视频会议,集体备课,互动听课)
	public static final String MEETING_TYPE_VIDEO_MEETING = "VIDEO_MEETING"; // 视频会议
	public static final String MEETING_TYPE_INTERACT_LESSON = "INTERACT_LESSON"; // 互动听课
	public static final String MEETING_TYPE_PREPARE_LESSON = "PREPARE_LESSON"; // 集体备课
	// 发起会议权限(视频会议,集体备课,互动听课)
	public static final String MEETING_INTERACTIVE_LISTEN_FLAG = "INTERACTIVE_LISTEN_FLAG"; // 互动听课
	public static final String MEETING_GROUP_PREPARATION_FLAG = "GROUP_PREPARATION_FLAG"; // 集体备课
	public static final String MEETING_VIDEO_CONFERENCE_FLAG = "VIDEO_CONFERENCE_FLAG"; // 视频会议
	// 会议状态(视频会议,集体备课,互动听课)
	public static final String MEETING_STATUS_INIT = "INIT"; // 未开始
	public static final String MEETING_STATUS_PROGRESS = "PROGRESS"; // 进行中
	public static final String MEETING_STATUS_END = "END"; // 已结束
	// 会议登录地点(视频会议,集体备课,互动听课)
	public static final String MEETING_LOGIN_PLACE_PC = "PC"; // pc端登录
	public static final String MEETING_LOGIN_PLACE_CLASSROOM = "CLASSROOM"; // 教室登录
	// 会议参会者类型(视频会议,集体备课,互动听课)
	public static final String MEETING_MEMBER_TYPE_MAIN = "MAIN"; // 主讲
	public static final String MEETING_MEMBER_TYPE_RECEIVE = "RECEIVE"; // 辅助
	public static final String MEETING_MEMBER_TYPE_JOIN = "JOIN"; // 参会者
	public static final String MEETING_MEMBER_TYPE_GUEST = "GUEST"; // 来宾
	// 会议参会者登录信息
	public static final String MEET_SESSION_USER = "MEET_SESSION_USER";
	// 会议录制视频归档状态(视频会议,集体备课,互动听课)
	public static final String MEETING_VIDEO_STATUS_INIT = "INIT"; // 未归档
	public static final String MEETING_VIDEO_STATUS_PROGRESS = "PROGRESS"; // 归档中
	public static final String MEETING_VIDEO_STATUS_FILED = "FILED"; // 已归档
	// 会议异常跳转页面
	public static final String MEETING_REDIRECT_PAGE = "meeting/redirect"; // 会议异常跳转页面

	// 视频录制角色
	public static final String MEET_REC_TYPE_MEMBER = "MEMBER"; // 参会者
	public static final String MEET_REC_TYPE_CLASSROOM = "CLASSROOM"; // 教室

	// 会议文档类型
	public static final String MEET_DOCUMENT_TYPE_RELATED = "RELATED"; // 相关文档
	public static final String MEET_DOCUMENT_TYPE_RESULT = "RESULT"; // 成果文档

	/********************* 录制视频相关常量定义开始 *********************/
	public static final String RECORD_KEY_SUFFIX = "suffix";// 录制格式
	public static final String SUFFIX_FLV = "flv";//
	public static final String SUFFIX_MP4 = "mp4";//

	public static final String RECORD_KEY_DEFINITION = "definition";// 视频清晰度
	public static final String DEFINITION_HIGH = "high";// 高
	public static final String DEFINITION_MIDDLE = "middle";// 中
	public static final String DEFINITION_LOW = "low";// 低

	public static final String RECORD_KEY_MODE = "mode";// 录制模式
	public static final String MODE_MOVIE = "movie";// 电影模式
	public static final String MODE_RESOURCE = "resource";// 资源模式
	public static final String MODE_FIX = "fix";// 电影模式+资源模式

	public static final String RECORD_KEY_RESOLUTION = "resolution";// 分辨率
	public static final String RESOLUTION_1920_1080 = "1920*1080";
	public static final String RESOLUTION_1280_720 = "1280*720";

	public static final String HOME_COOKIE_AREA_ID = "homeCookieAreaId";

	public static final String HOME_COOKIE_SCHOOL_ID = "homeCookieShoolId";

	/********************* 录制视频相关常量定义结束 *********************/

	public enum ResourceColumnEnum {
		video("video", "视频类"), doc("doc", "文档类");

		private ResourceColumnEnum(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static ResourceColumnEnum getDes(String value) {
			if (StringUtils.isNotBlank(value)) {
				for (ResourceColumnEnum p : ResourceColumnEnum.values()) {
					if (p.getValue().equals(value)) {
						return p;
					}
				}
			}
			return null;
		}
	}

	public enum ResourceDeleteFlagEnum {
		DELETE_NOT_DELETE("DELETE_NOT_DELETE", "未删除"), DELETE_BY_MANAGER("DELETE_BY_MANAGER", "管理员删除"), DELETE_BY_USER(
				"DELETE_BY_USER", "用户删除");

		private ResourceDeleteFlagEnum(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static ResourceDeleteFlagEnum getDes(String value) {
			if (StringUtils.isNotBlank(value)) {
				for (ResourceDeleteFlagEnum p : ResourceDeleteFlagEnum.values()) {
					if (p.getValue().equals(value)) {
						return p;
					}
				}
			}
			return null;
		}
	}

	public enum EvaStandardAvailableScopeEnum {
		PUBLIC("PUBLIC", "公开 "), PRIVATE("PRIVATE", "私有");

		private EvaStandardAvailableScopeEnum(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static EvaStandardAvailableScopeEnum getDes(String value) {
			if (StringUtils.isNotBlank(value)) {
				for (EvaStandardAvailableScopeEnum p : EvaStandardAvailableScopeEnum.values()) {
					if (p.getValue().equals(value)) {
						return p;
					}
				}
			}
			return null;
		}
	}

	public enum ResPermissionEnum {
		PUBLIC("PUBLIC", "公开 "), PRIVATE("PRIVATE", "私有");

		private ResPermissionEnum(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static ResPermissionEnum getDes(String value) {
			if (StringUtils.isNotBlank(value)) {
				for (ResPermissionEnum p : ResPermissionEnum.values()) {
					if (p.getValue().equals(value)) {
						return p;
					}
				}
			}
			return null;
		}
	}

	/**
	 * 基本数据类型
	 * 
	 * @author yaodaqing
	 *
	 */
	public enum BaseCategoryType {

		/** 学段 */
		SEMESTER("semester", 1),
		/** 年级 */
		CLASSLEVEL("classlevel", 2),
		/** 学科 */
		CLASSLEVELSUBJECT("classlevelSubject", 3),
		/** 版本 */
		CLASSLEVELSUBJECTVERSION("classlevelSubjectVersion", 4),
		/** 分册 */
		VOLUME("volume", 5),
		/** 章 */
		CHAPTER("chapter", 6),
		/** 节 */
		SECTION("section", 7);

		// 成员变量
		private String name;
		private Integer index;

		// 构造方法
		private BaseCategoryType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public Integer getIndex() {
			return this.index;
		}

		public static BaseCategoryType getType(String name) {
			if (StringUtils.isNotBlank(name)) {
				for (BaseCategoryType bt : BaseCategoryType.values()) {
					if (bt.getName().equalsIgnoreCase(name)) {
						return bt;
					}
				}
			}
			return null;
		}
	}

	public static String getMeetingType(String type) {
		if (StringUtils.isNotBlank(type)) {
			if (type.equals(MEETING_TYPE_VIDEO_MEETING)) {
				return "视频会议";
			} else if (type.equals(MEETING_TYPE_INTERACT_LESSON)) {
				return "互动听课";
			} else if (type.equals(MEETING_TYPE_PREPARE_LESSON)) {
				return "集体备课";
			}
		}
		return null;
	}
	
	public static String getMeetingTypeTitle(String type) {
		if (StringUtils.isNotBlank(type)) {
			if (type.equals(MEETING_TYPE_VIDEO_MEETING)) {
				return "teach.platform.meettitle";
			} else if (type.equals(MEETING_TYPE_INTERACT_LESSON)) {
				return "teach.platform.irtitle";
			} else if (type.equals(MEETING_TYPE_PREPARE_LESSON)) {
				return "teach.platform.aptitle";
			}
		}
		return "teach.platform.oltitle";
	}

}

package commons.utils;
import com.alibaba.fastjson.JSONObject;

public class ResultJson {

	private boolean result;
	private Integer code;
	private String message;
	
	public static String trueResult(){
		return new ResultJson(true).toString();
	}
	
	public static String falseResult(){
		return new ResultJson(false).toString();
	}
	
	public ResultJson(boolean result){
		this.result = result;
	}
	
	public ResultJson(boolean result, Integer code){
		this.result = result;
		this.code = code;
	}
	
	public ResultJson(boolean result, String message){
		this.result = result;
		this.message = message;
	}
	
	public ResultJson(boolean result, Integer code, String message){
		this.result = result;
		this.message = message;
		this.code = code;
	}
	
	public String toString(){
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("code", code);
		json.put("message", message);
		return json.toString();
	}
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

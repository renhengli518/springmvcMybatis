package commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StreamUtils;

/**
 * http断点工具<br/>
 * 文件断点下载<br/>
 * 视频边下边播<br/>
 * 
 * @author yaodaqing
 *
 */
public class HttpBreakpointUtils {

	private static final Log logger = LogFactory.getLog(HttpBreakpointUtils.class);

	/**
	 * HTTP断点下载文件
	 * @param request
	 * @param response
	 * @param filePath
	 * @param requestType 扩展的文件类型可以为空
	 * @throws IOException
	 */
	public static void breakpointDownloadFile(HttpServletRequest request, HttpServletResponse response, String filePath, String requestType) throws IOException {
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			response.setHeader("Accept-Ranges", "bytes");
			long fileLength = file.length();
			String range = request.getHeader("Range");
			if (StringUtils.isNotBlank(range)) {// 分段下载处理
				response.setStatus(206);
				logger.debug("ranges:" + range);
				range = range.replaceAll("bytes=", "");
				if (StringUtils.isNotBlank(range)) {
					String[] x = range.split("-");
					int start = Integer.parseInt(x[0]);// 开始位置
					logger.debug("start:" + start);
					Integer end = null;// 结束位置, 可能为空
					if (x.length > 1) {
						end = Integer.valueOf(x[1]);
					} else {
						if (fileLength - start > 1024 * 1024) {// 默认读取流大小
							end = start + 1024 * 1024;
						} else {
							end = (int) fileLength;
						}
					}
					int len1 = end - start + 1;
					response.setHeader("Content-Length", String.valueOf(len1));
					logger.debug("end:" + end);
					// 检查位置是否超出范围
					if (start < fileLength && (end == null || (end != null && end.longValue() <= fileLength && end > start))) {
						logger.debug("position verify ok.");
						try {
							InputStream input = new FileInputStream(file);
							int limit = -1;// 限制读取的长度
							if (end != null)
								limit = end - start + 1;
							input.skip(start);
							logger.debug("skip " + start);
							int readLength = 10240;
							byte[] buffer = new byte[readLength];
							if (limit != -1 && limit < readLength)
								readLength = limit;
							logger.debug("set status and header");
							String contentRange = new StringBuffer("bytes ").append(start).append("-").append(end == null ? "" : end).append("/").append(String.valueOf(file.length())).toString();
							response.setHeader("Content-Range", contentRange);
							OutputStream out = response.getOutputStream();
							logger.debug("start send bytes");
							while (true) {
								int len = input.read(buffer, 0, readLength);
								if (len == -1)
									break;
								out.write(buffer, 0, len);
								if (limit != -1) {
									limit -= len;
									if (limit <= 0)
										break;
								}
							}
							input.close();
						} catch (IOException e) {
							// response.sendError(404);
							logger.debug("client abort:" + e.getMessage());
						}
					}
				}
			} else {// 非分段下载
				logger.debug("no bytes tag");
				response.setStatus(200);
				try {
					response.setHeader("Content-Length", String.valueOf(fileLength));
					InputStream input = new FileInputStream(file);
					StreamUtils.copy(input, response.getOutputStream());
				} catch (IOException e) {
					logger.debug("client abort:" + e.getMessage());
				}
				/*
				 * if("android".equals(requestType)){ }else
				 * if("ios".equals(requestType)){ try {
				 * response.setHeader("Content-Length",
				 * String.valueOf(fileLength)); InputStream input = new
				 * FileInputStream(file); StreamUtils.copy(input,
				 * response.getOutputStream()); } catch (IOException e) {
				 * logger.debug("client abort:" + e.getMessage()); } }else{
				 * logger.debug("requestType not support:" + requestType);
				 * response.sendError(404); }
				 */
			}
		} else {
			// 未找到文件，返回404
			logger.debug("file not found:" + filePath);
			response.sendError(404);
		}
	}
	
	/**
	 * HTTP断点上传文件
	 * @param request
	 * @param response
	 * @param filePath
	 * @param requestType
	 * @throws IOException
	 */
	public static void breakpointUploadFile(HttpServletRequest request, HttpServletResponse response, String filePath, String requestType) throws IOException {
		
	}
}

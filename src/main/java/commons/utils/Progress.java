package commons.utils;

/**
 * 功能描述： 文件上传进度<br>
 */
public class Progress {

	
	private long bytesRead = 0L;		//已读字节
	private long contentLength = 0L;	//总长度
	private int items;					//读取第N个文件
	private String physicalFilename;	//本地文件地址
	private String fullFilename;
	private String originalFilename;	//原始文件名
	private Long fileSize;				//文件大小
	private int status = 0;					//文件状态
				//0=上传中
				//1=文件存储中，文件上传结束后转存到文件服务器
				//2=上传结束
	
	private long startTime = System.currentTimeMillis(); //开始读取的时间 

	public long getBytesRead() {
		return bytesRead;
	}

	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startReatTime) {
		this.startTime = startReatTime;
	}

	public String getPhysicalFilename() {
		return physicalFilename;
	}

	public void setPhysicalFilename(String physicalFilename) {
		this.physicalFilename = physicalFilename;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFullFilename() {
		return fullFilename;
	}

	public void setFullFilename(String fullFilename) {
		this.fullFilename = fullFilename;
	}

}
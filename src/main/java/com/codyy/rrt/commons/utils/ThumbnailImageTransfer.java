package com.codyy.rrt.commons.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.codyy.rrt.commons.utils.Scalr.Mode;


public class ThumbnailImageTransfer {
	@SuppressWarnings("unused")
	private String srcFile;
	private String destFile;
	private int width;
	private int height;
	private Image img;
	private double resizePercent;
	private BufferedImage bufferedImage;
	
	public ThumbnailImageTransfer() {
		resizePercent = 0.5;
	}

	/**
	 * 
	 * @param filePath
	 *            文件路径
	 * @param savePath
	 *            保存路径
	 * @param resizePercent
	 *            缩放比例
	 * @throws IOException
	 */
	public ThumbnailImageTransfer(String filePath, String savePath)
			throws IOException {
		File file = new File(filePath); // 读入文件
		this.srcFile = file.getName();
		this.destFile = savePath;
		img = javax.imageio.ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	public ThumbnailImageTransfer(InputStream in) throws IOException {
		img = javax.imageio.ImageIO.read(in); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}
	
	public void initFromInputStream(InputStream in) throws IOException {
		img = javax.imageio.ImageIO.read(in); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            新宽度
	 * @param h
	 *            新高度
	 * @param save
	 *            是否保存
	 * @throws IOException
	 */
	public void resize(int w, int h, boolean save) throws IOException {
		bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		if (save) {
			saveImage();
		}
	}

	/**
	 * 按照固定的比例缩放图片
	 * 
	 * @param t
	 *            比例
	 * @throws IOException
	 */
	public void resize(double t, boolean save) throws IOException {
		int w = (int) (width * t);
		int h = (int) (height * t);
		resize(w, h, save);
	}

	public void resize(boolean save) throws IOException {
		int w = (int) (width * resizePercent);
		int h = (int) (height * resizePercent);
		resize(w, h, save);
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            新宽度
	 * @throws IOException
	 */
	public void resizeByWidth(int w, boolean save) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h, save);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            新高度
	 * @throws IOException
	 */
	public void resizeByHeight(int h, boolean save) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h, save);
	}

	/**
	 * 按照最大高度限制，生成最大的等比例缩略图
	 * 
	 * @param w
	 *            最大宽度
	 * @param h
	 *            最大高度
	 * @throws IOException
	 */
	public void resizeFix(int w, int h, boolean save) throws IOException {
		
		if (width / height > w / h) {
			resizeByWidth(w, save);
		} else {
			resizeByHeight(h, save);
		}
	}

	/**
	 * 设置目标文件名 setDestFile
	 * 
	 * @param fileName
	 *            文件名字符串
	 */
	public void setDestFile(String fileName) throws Exception {
		if (!fileName.endsWith(".jpg")) {
			throw new Exception("Dest File Must end with \".jpg\".");
		}
		destFile = fileName;
	}

	/**
	 * 获取目标文件名 getDestFile
	 */
	public String getDestFile() {
		return destFile;
	}

	/**
	 * 获取图片原始宽度 getSrcWidth
	 */
	public int getSrcWidth() {
		return width;
	}

	/**
	 * 获取图片原始高度 getSrcHeight
	 */
	public int getSrcHeight() {
		return height;
	}

	public double getResizePercent() {
		return resizePercent;
	}

	public void setResizePercent(double resizePercent) {
		this.resizePercent = resizePercent;
	}


	/*
	 * 调用测试
	 */
	public void init(File file) throws Exception {
		this.srcFile = file.getName();
		img = javax.imageio.ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	public void saveImage() {
		try {
			File file = new File(destFile);
			File parentPath = file.getParentFile();
			if (!parentPath.exists()) {
				parentPath.mkdirs();
			}
			ImageIO.write(bufferedImage, "JPEG", new FileOutputStream(destFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		long s = System.currentTimeMillis();
		ThumbnailImageTransfer thumbnailImageTransfer = new ThumbnailImageTransfer(
				"E:/temp/IMG_20140731_113757.jpg", "E:/temp/123.jpg");
		thumbnailImageTransfer.setResizePercent(0.2);
		thumbnailImageTransfer.resize(true);
		long e = System.currentTimeMillis();
		System.out.println("时间：" + (e - s));
	}

	public BufferedImage getThumbnailImage(File file, int width, int height)
			throws Exception {
		init(file);
		resize(width, height, false);
		return bufferedImage;
	}

	/**
	 * 获取图片输入流
	 * 
	 * @return
	 * @throws Exception 
	 */
	public InputStream getImageInputStreamByResizeFix(int saveWidth,int saveHeight,InputStream in, String suffix) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		if(".gif".equals(suffix)){
			GifDecoder gd = new GifDecoder();
			int status = gd.read(in);
			if (status != GifDecoder.STATUS_OK) {
				throw new Exception("文件格式错误");
			}else{
				AnimatedGifEncoder ge = new AnimatedGifEncoder();
				ge.start(bos);
				//ge.start(new FileOutputStream(new File("D:/desc_e.gif")));
				ge.setRepeat(0);
				for (int i = 0; i < gd.getFrameCount(); i++) {
					BufferedImage frame = gd.getFrame(i);
					double orignWidth = frame.getWidth();
					double orignHeight = frame.getHeight();
					if (orignWidth / orignHeight > saveWidth / saveHeight) {
						orignHeight = orignHeight * saveWidth / orignWidth;
						orignWidth = saveWidth;
					} else {
						orignWidth = saveWidth * saveHeight / orignHeight;
						orignHeight=saveHeight;
					}
					BufferedImage rescaled = Scalr.resize(frame, Mode.FIT_EXACT, (int)orignWidth, (int)orignHeight);
					int delay = gd.getDelay(i);
					ge.setDelay(delay);
					ge.addFrame(rescaled);
				}
				ge.finish();
			}
		}else{
			initFromInputStream(in);
			resizeFix(saveWidth, saveHeight, false);
			if (bufferedImage == null) {
				throw new Exception("文件不存在");
			}
			bufferedImage.flush();
			ImageOutputStream imOut;
			imOut = ImageIO.createImageOutputStream(bos);
			ImageIO.write(bufferedImage, "JPEG", imOut);
		
		}
		InputStream is = new ByteArrayInputStream(bos.toByteArray());
		return is;
	}

	public BufferedImage getThumbnailImageWithPercent(File file, int width,
			int height) throws Exception {
		init(file);
		resizeFix(width, height, false);
		return bufferedImage;
	}
}

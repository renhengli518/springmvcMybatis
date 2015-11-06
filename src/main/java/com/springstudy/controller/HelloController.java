package com.springstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springstudy.entity.BaseUser;
import com.springstudy.service.BaseUserService;
import commons.page.Page;
import commons.utils.ResultJson;

@Controller
public class HelloController {
	
	private Logger logger = Logger.getLogger(HelloController.class);
	
	@Autowired
	private BaseUserService baseUserService;
	
	@RequestMapping("/hello")
	public ModelAndView hello() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("spring", "spring mvc");

		mv.setViewName("hello");

		return mv;

	}
	
	@RequestMapping("getUserList")
	public String getUserList(HttpServletRequest request){
		List<BaseUser> list = baseUserService.getAllUsers();
		request.setAttribute("userList", list);
		return "userList";
	}
	
	/**
	 * springmvc 上传文件（并利用poi读取 word table内容）
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@RequestMapping("readUploadDocFile")
	public String readUploadDocFile(HttpServletRequest request , HttpServletResponse response,@RequestParam("file") MultipartFile file) throws IOException{
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("UTF-8");
//		resolver.setMaxUploadSize(1024*1024*1024);
//		resolver.setMaxInMemorySize(1024*1024);
//		MultipartHttpServletRequest multiRequest = null;
//		multiRequest = resolver.resolveMultipart(request);
		//MultipartFile file = multiRequest.getFile("file");
		String original = file.getOriginalFilename();
		original = (original == null) ? "" : original;
		file.transferTo(new File("d:/"+file.getOriginalFilename()));// 写入目标文件
		FileInputStream in = new FileInputStream("d:/"+file.getOriginalFilename());
		POIFSFileSystem pfs = new POIFSFileSystem(in);
		HWPFDocument hwfp = new HWPFDocument(pfs);
		Range range = hwfp.getRange();
		TableIterator it = new TableIterator(range);
		try {
			int index = 0;
			while(it.hasNext()){
				Table table = (Table)it.next();
				for (int i = 0;i<table.numRows();i++) {
					TableRow tr = table.getRow(i);
					for(int j = 0;j<tr.numCells(); j++){
						TableCell td = tr.getCell(j);
						String text = td.text();
						System.out.println("---------word text :-------------"+text);
					}
				}
				index++;
				in.close();
			}
		} catch (Exception e) {
			in.close();
			e.printStackTrace();
		}
		ResultJson json = new ResultJson(true, 0, file.getName());
		response.getWriter().write(json.toString());
		logger.debug("File upload transferTo over");
		return "userList";
	}
	
	@RequestMapping("pageListDemo")
	@ResponseBody
	public Page pageListDemo(HttpServletRequest request, String createUserId, String resourceColumn,
			Page page) {
		return baseUserService.getUserInfoPageList(page);
	} 
	
	@RequestMapping("getUeditorForm")
	public String getUeditorForm(HttpServletRequest request, String ueditor){
		System.out.println("----------editor---------------:"+ueditor);
		return "userList";
	}
	
}
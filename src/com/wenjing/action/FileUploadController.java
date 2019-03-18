package com.wenjing.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {

	@RequestMapping("/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		String fileName = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
			multipartFile = set.getValue();// 文件名
			System.out.println(multipartFile);
		}
		fileName = this.storeIOc(multipartRequest, multipartFile);

		out.print(fileName);
	}

	// 接受图片，返回图片地址
	private String storeIOc(HttpServletRequest request, MultipartFile file)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String savepath = request.getParameter("path");
		String fileName = "";
		String _fileName = "";
		String logImageName = "";
		String realPath = request.getSession().getServletContext().getRealPath("resources/" + savepath + "");
		
		File f = new File(realPath);
		if (!f.exists() && !f.isDirectory()) {  //是文件夹，且文件夹不存在则创建文件夹 
			f.mkdir();
		}
		
		if (file == null) {
			return "dream_ioc" + File.separator + "headpic.jpg";
		}

		if (file.isEmpty()) {
			System.out.println("文件未上传");
		} else {
			_fileName = file.getOriginalFilename();
			String suffix = _fileName.substring(_fileName.lastIndexOf("."));
			// /**使用UUID生成文件名称**/
			logImageName = UUID.randomUUID().toString() + suffix;
			fileName = realPath + File.separator + logImageName;
			File restore = new File(fileName);
			try {
				file.transferTo(restore);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		// 返回默认的图片地址
		return "/resources/" + savepath + "/" + logImageName;
	}

	/**
	 * 保存上传文件
	 * 
	 * @param picaddress
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String savepictoria(@RequestParam("picaddress") String picaddress) {
		System.out.println("=========================" + picaddress);

		return null;
	}
}
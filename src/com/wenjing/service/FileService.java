/**
 * 
 */
package com.wenjing.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类说明
 * @author xiejin
 * @date 2016-2-4 
 * @date 2016-2-4 上午10:50:38
 */
@Service
public class FileService {
	
	/**
	 * 删除指定路径文件
	 */
	@Transactional
	public boolean deleteAttachment(String filePath, HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("" + filePath + "");
		File file = new File(realPath);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}

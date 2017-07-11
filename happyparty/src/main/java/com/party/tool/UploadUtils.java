package com.party.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.party.dto.Response;

/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午2:49:58
 * @version v.0.1
 * @email 1115054416@qq.com
 *
 *        <p>
 * 		Description: 本项目专用的上传
 *        </p>
 *
 */
public class UploadUtils {
	private static final int BUFFER_SIZE = 16 * 1024;

	public static void copy(InputStream in, File dst) {
		OutputStream out = null;
		try {
			in = new BufferedInputStream(in, BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//把获取到的文件直接存储到本地服务器上
	public static String MvcUpload(MultipartFile file, String fileSavePath) throws Exception {
		//文件存储的全名
		String realPath = "";
			if(file.isEmpty())
				throw new Exception("图片为空");
			// 如果post过来的文件为空
			// 获取文件名
			String fileName = file.getOriginalFilename();
			// logger.info("上传的图片名称为"+fileName);
			// 获取文件后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			// logger.info("文件后缀名称：" + suffixName);
			// 文件重命名，解决中文问题，liunx下中文路径，图片显示问题,解决图片重名问题
			fileName = UUID.randomUUID() + suffixName;
			
			// 本地存储全路径
			realPath = fileSavePath + fileName;
			File dest = new File(realPath);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			// 直接转换存储
			copy(file.getInputStream(), dest);
		return realPath;
	}
}

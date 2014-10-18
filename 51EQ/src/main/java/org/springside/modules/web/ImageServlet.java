package org.springside.modules.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.globals.SysConfig;

public class ImageServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(ImageServlet.class);

	private static final long serialVersionUID = 2943971882819896835L;

	/**
	 * doget
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws IOException 文件读写异常
	 * @throws ServletException ServletException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		this.loadData(request, response);

	}

	/**
	 * dopost
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws IOException 文件读写异常
	 * @throws ServletException ServletException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	public void loadData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		String uploadFilePath = sysConfig.getUploadShareFilePath();

		String filePath = uploadFilePath + request.getParameter("FILE_PATH");

		String suffix = null;
		int index = filePath.lastIndexOf(".");
		if (index > 0) {
			suffix = filePath.substring(index);
		} else {
			suffix = "unknown";
		}

		response.setContentType("image/" + suffix);
		response.setHeader("Content-Disposition", "inline");

		InputStream in = null;

		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			in = new FileInputStream(file);
		} else {
			in = new FileInputStream(uploadFilePath + "blank.jpg");
			logger.error("File is not exists! show blank.jpg" + filePath);
		}

		int bufferSize = 1024;

		ServletOutputStream outfile = response.getOutputStream();

		byte ioBuf[] = new byte[bufferSize];
		do {
			int chunkLen = in.read(ioBuf);
			if (chunkLen <= 0) {
				break;
			}
			outfile.write(ioBuf, 0, chunkLen);
		} while (true);
		in.close();
		outfile.close();
	}

}

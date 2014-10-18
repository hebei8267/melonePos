package com.tjhx.common.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtils {
	/**
	 * 从request中取得上传文件流
	 * 
	 * @param request
	 * @return
	 */
	public static MultipartFile getMultipartFile(HttpServletRequest request) {
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件
		MultipartFile _file = multipartRequest.getFile("file");

		return _file;
	}

	/**
	 * 保存文件至磁盘
	 * 
	 * @param file
	 * @param filePath
	 * @param fileStoreName
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static void saveUploadFile(MultipartFile file, String filePath, String fileStoreName) throws IllegalStateException,
			IOException {
		if (0 == file.getSize() || null == file.getOriginalFilename()) {
			return;
		}
		
		deleteFile(filePath + fileStoreName);
		
		File _file = new File(filePath + fileStoreName);
		file.transferTo(_file);
	}

	/**
	 * 自动建立文件夹
	 * 
	 * @param filePath 立文件夹路径
	 */
	public static void mkdir(String filePath) {
		File _filePath = new File(filePath);
		if (!_filePath.exists()) {
			_filePath.mkdir();
		}
	}

	/**
	 * 得到文件后缀(如sample.jpg->.jpg)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		if (pos < 0) {
			return fileName.substring(fileName.length() - 3).toLowerCase();
		} else {
			return fileName.substring(pos).toLowerCase();
		}
	}

	/**
	 * 得到文件名称无后缀(如sample.jpg->sample)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameNoSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(0, pos);
	}

	public static List<String> readFileContent(String filePath) {
		List<String> fileContent = new ArrayList<String>();
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(filePath);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				fileContent.add(strLine);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return fileContent;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileFullPath 被删除文件的文件路径
	 * @return
	 */
	public static File deleteFile(String fileFullPath) {
		File _file = new File(fileFullPath);
		if (_file.isFile() && _file.exists()) {
			_file.delete();
		}

		return _file;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 */
	public static boolean deleteDirectory(String fullPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!fullPath.endsWith(File.separator)) {
			fullPath = fullPath + File.separator;
		}
		File dirFile = new File(fullPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				deleteFile(files[i].getAbsolutePath());

			} // 删除子目录
			else {
				deleteDirectory(files[i].getAbsolutePath());
			}
		}

		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}

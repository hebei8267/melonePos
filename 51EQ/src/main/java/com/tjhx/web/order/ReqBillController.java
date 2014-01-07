package com.tjhx.web.order;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.common.utils.FileUtils;
import com.tjhx.globals.SysConfig;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/reqBill")
public class ReqBillController extends BaseController {

	@RequestMapping(value = { "init", "" })
	public String initUploadPage_Action() {
		return "order/reqBill";
	}

	/**
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping("/deleteDirectory")
	// ajax请求,json数据返回
	@ResponseBody
	public String deleteDirectory_Action(HttpServletRequest request) throws ServletRequestBindingException {
		String batchId = ServletRequestUtils.getStringParameter(request, "batchId");
		batchId = DateUtils.transDateFormat(batchId, "yyyy-MM-dd", "yyyyMMdd");

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		String filePath = sysConfig.getReqBillSupplierInputPath() + batchId + File.separator;
		// 清理文件夹(指定的文件名如果存在,则删除)
		FileUtils.deleteDirectory(filePath);

		JsonMapper mapper = new JsonMapper();
		return mapper.toJson("清理完成!");
	}

	/**
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping("/uploadFile")
	public String uploadFile_Action(HttpServletRequest request) throws IllegalStateException, IOException,
			ServletRequestBindingException {
		String batchId = ServletRequestUtils.getStringParameter(request, "batchId");
		batchId = DateUtils.transDateFormat(batchId, "yyyy-MM-dd", "yyyyMMdd");

		MultipartFile imgFile = getMultipartFile(request);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		String filePath = sysConfig.getReqBillSupplierInputPath() + batchId + File.separator;

		// 自动建立文件夹
		FileUtils.mkdir(filePath);

		saveUploadFile(imgFile, filePath, imgFile.getOriginalFilename());

		return "order/reqBill";
	}

	/**
	 * @param request
	 * @return
	 */
	private MultipartFile getMultipartFile(HttpServletRequest request) {
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件
		MultipartFile imgFile = multipartRequest.getFile("file");

		return imgFile;
	}

	/**
	 * @param imgFile
	 * @param filePath
	 * @param fileName
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private void saveUploadFile(MultipartFile imgFile, String filePath, String fileName) throws IllegalStateException,
			IOException {
		if (0 == imgFile.getSize() || null == imgFile.getOriginalFilename()) {
			return;
		}

		File _file = new File(filePath + fileName);
		imgFile.transferTo(_file);
	}
}

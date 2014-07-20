/**
 * 
 */
package com.tjhx.web.affair;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.entity.affair.ShareFile;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.ShareFileManager;
import com.tjhx.web.BaseController;

/**
 * 共享文件
 */
@Controller
@RequestMapping(value = "/shareFile")
public class ShareFileController extends BaseController {

	@Resource
	private ShareFileManager shareFileManager;

	/**
	 * 共享文件信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list_Action(Model model, HttpServletRequest request) {

		initStatusList(model);

		return "affair/shareFileList";
	}

	/**
	 * 取得门店巡查报告(运营)信息列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String search_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		String status = ServletRequestUtils.getStringParameter(request, "status");
		model.addAttribute("status", status);

		List<ShareFile> _list = shareFileManager.getShareFileList(status);
		model.addAttribute("shareFileList", _list);

		initStatusList(model);

		return "affair/shareFileList";
	}

	/**
	 * 初始化文件状态列表
	 * 
	 * @param model
	 */
	private void initStatusList(Model model) {
		Map<String, String> statusList = new LinkedHashMap<String, String>();
		statusList.put("", "");
		statusList.put("00", "在用");
		statusList.put("01", "停用");
		statusList.put("99", "废除");

		model.addAttribute("statusList", statusList);
	}

	/**
	 * 新增共享文件初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initShareFile_Action(Model model) {
		ShareFile shareFile = new ShareFile();
		model.addAttribute("shareFile", shareFile);

		initStatusList(model);

		return "affair/shareFileForm";
	}

	/**
	 * 删除共享文件
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delShareFile_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			shareFileManager.delShareFileByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/shareFile/list";
	}

	/**
	 * 保存共享文件
	 * 
	 * @param shareFile
	 * @param model
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save")
	public String saveShareFile_Action(@ModelAttribute("shareFile") ShareFile shareFile, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {
		if (null == shareFile.getUuid()) {// 新增操作
			try {
				MultipartFile _file = FileUtils.getMultipartFile(request);
				shareFile.setShareFile(_file);

				shareFileManager.addNewShareFile(shareFile);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		} else {// 修改操作
			try {
				 shareFileManager.updateShareFile(shareFile);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/shareFile/list";
	}

	/**
	 * 编辑共享文件
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editShareFile_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		ShareFile shareFile = shareFileManager.getShareFileByUuid(id);
		if (null == shareFile) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/shareFile/list";
		} else {
			initStatusList(model);
			model.addAttribute("shareFile", shareFile);

			return "affair/shareFileForm";
		}

	}
}

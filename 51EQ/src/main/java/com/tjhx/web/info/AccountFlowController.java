package com.tjhx.web.info;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.AccountFlow;
import com.tjhx.entity.info.AccountFlowSplit;
import com.tjhx.entity.info.AccountSubject;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.AccountFlowManager;
import com.tjhx.service.info.AccountSubjectManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/accountFlow")
public class AccountFlowController extends BaseController {
	@Resource
	private AccountFlowManager accountFlowManager;
	@Resource
	private AccountSubjectManager accountSubjectManager;

	/**
	 * 记账页面初始化
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "list")
	public String list_Action(HttpServletRequest request, Model model) throws ServletRequestBindingException {

		return "info/accountFlowList";
	}

	/**
	 * 记账页面查询
	 * 
	 * @param model
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "search")
	public String search_Action(HttpServletRequest request, Model model) throws ServletRequestBindingException {
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");

		List<AccountFlow> _list = accountFlowManager.getAccountFlowList(optDateShow_start, optDateShow_end);

		model.addAttribute("accountFlowList", _list);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		return "info/accountFlowList";
	}

	/**
	 * 记账数据导出
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	@RequestMapping(value = "export")
	public void export_Action(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, IOException, ParsePropertyException, InvalidFormatException {
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		// =========================================
		// 生成下载文件
		// =========================================
		String downLoadFileName = accountFlowManager.createDownLoadFile(optDateShow_start, optDateShow_end);

		if (null == downLoadFileName) {
			return;
		}
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String downLoadPath = sysConfig.getReportTmpPath() + downLoadFileName;

		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(downLoadFileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {

		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	/**
	 * 删除记账信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delAccountFlow_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			accountFlowManager.delAccountFlowByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
	}

	/**
	 * 记账初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initAccountFlow_Action(Model model) {

		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setInAmt(null);
		accountFlow.setOutAmt(null);
		model.addAttribute("accountFlow", accountFlow);

		return "info/accountFlowForm";
	}

	/**
	 * 编辑记账信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{uuid}")
	public String editAccountFlow_Action(@PathVariable("uuid") Integer id, Model model) {

		AccountFlow accountFlow = accountFlowManager.getAccountFlowByUuid(id);
		if (null == accountFlow) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
		} else {
			accountFlow.setOptDate(DateUtils.transDateFormat(accountFlow.getOptDate(), "yyyyMMdd", "yyyy-MM-dd"));
			model.addAttribute("accountFlow", accountFlow);

			return "info/accountFlowForm";
		}

	}

	/**
	 * 新增/修改 记账信息
	 * 
	 * @param role
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveAccountFlow_Action(HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		AccountFlow accountFlow = new AccountFlow();
		BeanUtils.populate(accountFlow, request.getParameterMap());

		if (null != accountFlow.getUuid() && 0 == accountFlow.getUuid()) {
			accountFlow.setUuid(null);
		}
		// 保存操作
		accountFlowManager.saveAccountFlow(accountFlow);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
	}

	// =================================================================================
	/**
	 * 会计记账编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "split/{uuid}")
	public String splitAccountFlow_Action(@PathVariable("uuid") Integer id, Model model) {
		AccountFlow accountFlow = accountFlowManager.getAccountFlowByUuid(id);
		if (null == accountFlow) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
		} else {
			model.addAttribute("accountFlow", accountFlow);

			List<AccountFlowSplit> splitList = accountFlowManager.getAccountFlowSplitByFlowUuid(id);
			model.addAttribute("splitList", splitList);

			List<AccountSubject> subList = accountSubjectManager.getAccountSubjectList();
			model.addAttribute("subList", subList);

			return "info/accountFlowSplit";
		}
	}

	@RequestMapping(value = "splitSave")
	public String splitSave_Action(HttpServletRequest request) throws ServletRequestBindingException {
		int accountFlowUuid = ServletRequestUtils.getIntParameter(request, "accountFlowUuid");
		String[] subId = new String[5];
		String[] amt = new String[5];
		for (int i = 0; i < 5; i++) {
			subId[i] = ServletRequestUtils.getStringParameter(request, "subId" + i);
			amt[i] = ServletRequestUtils.getStringParameter(request, "amt" + i);
		}

		List<AccountFlowSplit> _list = Lists.newArrayList();

		for (int i = 0; i < amt.length; i++) {
			if (StringUtils.isNotBlank(subId[i]) && StringUtils.isNotBlank(amt[i])) {
				_list.add(new AccountFlowSplit(subId[i], new BigDecimal(amt[i])));
			}

		}

		accountFlowManager.saveAccountFlowSplit(accountFlowUuid, _list);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/accountFlow/list";
	}
}

package com.tjhx.web.affair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.PunchClock_List_Show;
import com.tjhx.entity.member.Employee;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.affair.PunchClockManager;
import com.tjhx.service.member.EmployeeManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/punchClock")
public class PunchClockController extends BaseController {

	@Resource
	private PunchClockManager punchClockManager;
	@Resource
	private EmployeeManager empManager;
	@Resource
	private OrganizationManager orgManager;

	@RequestMapping(value = { "list" })
	public String punchClockList_Action(Model model, HttpSession session) throws ParseException {

		_punchClockListAction(getUserInfo(session).getOrganization().getId(), DateUtils.getCurFormatDate("yyyy"),
				DateUtils.getCurFormatDate("MM"), model);

		return "affair/punchClockList";
	}

	@RequestMapping(value = { "list/{date}" })
	public String punchClockList_Param_Action(@PathVariable("date") String optDate, Model model, HttpSession session)
			throws ParseException {
		String optDateY = DateUtils.transDateFormat(optDate, "yyyy-MM", "yyyy");
		String optDateM = DateUtils.transDateFormat(optDate, "yyyy-MM", "MM");

		_punchClockListAction(getUserInfo(session).getOrganization().getId(), optDateY, optDateM, model);

		return "affair/punchClockList";
	}

	private void _punchClockListAction(String orgId, String optDateY, String optDateM, Model model)
			throws ParseException {
		List<Employee> _empList = empManager.getEmployeeListByOrgId(orgId);
		model.addAttribute("empList", _empList);

		List<PunchClock_List_Show> _clockList = punchClockManager
				.getPunchClockList(orgId, optDateY, optDateM, _empList);
		model.addAttribute("punchClockList", _clockList);
	}

	// ######################################################################################################
	@RequestMapping(value = { "manage" })
	public String punchClockManageList_Action(Model model) {

		ReportUtils.initOrgList_NonNull_Root(orgManager, model);

		return "affair/punchClockManageList";
	}

	@RequestMapping(value = { "manage/search" })
	public String punchClockManageSearchList_Action(HttpServletRequest request, Model model)
			throws ServletRequestBindingException, ParseException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow", optDateShow);

		String optDateY = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "yyyy");
		String optDateM = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "MM");

		_punchClockListAction(orgId, optDateY, optDateM, model);

		ReportUtils.initOrgList_NonNull_Root(orgManager, model);

		return "affair/punchClockManageList";
	}

	@RequestMapping(value = "manage/export")
	public void punchClockExport_Action(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException, ParsePropertyException, InvalidFormatException, IOException,
			ParseException {

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow", optDateShow);

		String optDateY = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "yyyy");
		String optDateM = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "MM");

		String downLoadFileName = punchClockManager.createPunchClockFile(orgId, optDateY, optDateM);

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
		return;
	}

}

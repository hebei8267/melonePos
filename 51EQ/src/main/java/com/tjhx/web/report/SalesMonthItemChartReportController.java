package com.tjhx.web.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.SalesMonthTotalItem;
import com.tjhx.entity.info.SalesMonthTotal_Show;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.SalesMonthTotalItemManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesMonthItemChartReport")
public class SalesMonthItemChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesMonthTotalItemManager salesMonthTotalItemManager;

	/**
	 * 全部门店近4年销售数据
	 * 
	 * @param optDateYList
	 * @param _orgSumSalesJsonList
	 */
	private void getSalesTotalList_ByYear(List<String> optDateYList, List<String> _orgSumSalesJsonList) {
		JsonMapper mapper = new JsonMapper();
		// 全部门店近4年销售数据
		List<SalesMonthTotal_Show> _salesTotalShowList = new ArrayList<SalesMonthTotal_Show>();
		initSalesTotalShowList(Constants.ROOT_ORG_ID, _salesTotalShowList, optDateYList);
		for (String optDateY : optDateYList) {
			SalesMonthTotalItem _param = new SalesMonthTotalItem();
			_param.setOptDateY(optDateY);
			List<SalesMonthTotalItem> _salesYearTotal = salesMonthTotalItemManager.getSalesTotalList_ByYear(_param);
			// 取得门店数量
			int orgCnt = salesMonthTotalItemManager.getOrgCnt(optDateY);
			copyDate2SalesTotalShowList(_salesTotalShowList, _salesYearTotal, orgCnt);
		}
		_orgSumSalesJsonList.add(mapper.toJson(_salesTotalShowList));
	}

	/**
	 * 各店近4年销售数据
	 * 
	 * @param _orgList
	 * @param optDateYList
	 * @param _orgSumSalesJsonList
	 * @param _orgNameList
	 */
	private void getSalesTotalList_ByOrgAndYear(List<Organization> _orgList, List<String> optDateYList, List<String> _orgSumSalesJsonList,
			List<String> _orgNameList) {
		JsonMapper mapper = new JsonMapper();
		// 各店近4年销售数据
		for (Organization org : _orgList) {
			List<SalesMonthTotal_Show> _salesTotalShowList = new ArrayList<SalesMonthTotal_Show>();
			initSalesTotalShowList(org.getId(), _salesTotalShowList, optDateYList);

			for (String optDateY : optDateYList) {
				SalesMonthTotalItem _param = new SalesMonthTotalItem();
				_param.setOptDateY(optDateY);
				_param.setOrgId(org.getId());
				List<SalesMonthTotalItem> _salesYearTotal = salesMonthTotalItemManager.getSalesTotalList_ByOrgAndYear(_param);

				copyDate2SalesTotalShowList(_salesTotalShowList, _salesYearTotal, 1);
			}

			_orgSumSalesJsonList.add(mapper.toJson(_salesTotalShowList));
			_orgNameList.add(org.getName());
		}

	}

	@RequestMapping(value = "bar_init")
	public String salesDayChartReport1_Action(Model model) throws ParseException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int _yearNum = sysConfig.getSalesMonthTotalShowYearNum();

		List<Organization> _orgList = orgManager.getSubOrganization();
		List<String> optDateYList = calOptDateY(_yearNum);

		List<String> _orgSumSalesJsonList = new ArrayList<String>();
		List<String> _orgNameList = new ArrayList<String>();
		_orgNameList.add("合计");
		// 全部门店近4年销售数据
		getSalesTotalList_ByYear(optDateYList, _orgSumSalesJsonList);
		// 各店近4年销售数据
		getSalesTotalList_ByOrgAndYear(_orgList, optDateYList, _orgSumSalesJsonList, _orgNameList);

		for (int i = 0; i < _yearNum; i++) {
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
			model.addAttribute("optDateYM" + (i + 1), optDateYList.get(i));
		}

		model.addAttribute("orgSumSalesJsonList", _orgSumSalesJsonList);
		model.addAttribute("orgNameList", _orgNameList);

		return "report/salesMonthItemChartReport_bar";
	}

	private void copyDate2SalesTotalShowList(List<SalesMonthTotal_Show> _salesTotalShowList, List<SalesMonthTotalItem> _salesYearTotal,
			int orgCnt) {

		for (SalesMonthTotalItem _salesMonthTotalItem : _salesYearTotal) {
			for (SalesMonthTotal_Show _salesMonthTotalShow : _salesTotalShowList) {
				int equalsRes = _salesMonthTotalShow.myEquals(_salesMonthTotalItem);
				if (0 != equalsRes) {
					_salesMonthTotalShow.copyData(_salesMonthTotalItem, equalsRes, orgCnt);
				}
			}
		}
	}

	private void initSalesTotalShowList(String orgId, List<SalesMonthTotal_Show> _salesTotalShowList, List<String> optDateYList) {

		for (int i = 1; i <= 12; i++) {
			SalesMonthTotal_Show _salesTotalShow = new SalesMonthTotal_Show();
			// 机构编号
			_salesTotalShow.setOrgId(orgId);
			// 月份
			_salesTotalShow.setOptDateM(String.format("%02d", i));
			// 年月1
			_salesTotalShow.setOptDateYM1(optDateYList.get(0) + String.format("%02d", i));
			// 年月2
			_salesTotalShow.setOptDateYM2(optDateYList.get(1) + String.format("%02d", i));
			// 年月3
			_salesTotalShow.setOptDateYM3(optDateYList.get(2) + String.format("%02d", i));
			// 年月4
			_salesTotalShow.setOptDateYM4(optDateYList.get(3) + String.format("%02d", i));
			_salesTotalShowList.add(_salesTotalShow);
		}

	}

	/**
	 * 取得月销售信息对比(图形)页面年份显示个数
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDateY(int _yearNum) throws ParseException {
		List<String> _optDateYList = new ArrayList<String>();

		String _now = DateUtils.getCurrentYear();
		_optDateYList.add(_now);

		for (int i = 1; i < _yearNum; i++) {
			_optDateYList.add(DateUtils.getNextYearFormatDate(_now, -i, "yyyy"));
		}

		return _optDateYList;
	}

	// ==============================================================================
	// 数据导出
	// ==============================================================================
	@RequestMapping(value = "export")
	public void export_Action(HttpServletResponse response) throws ParseException, ParsePropertyException, InvalidFormatException,
			IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int _yearNum = sysConfig.getSalesMonthTotalShowYearNum();

		List<Organization> _orgList = orgManager.getSubOrganization();
		List<String> optDateYList = calOptDateY(_yearNum);

		String downLoadFileName = salesMonthTotalItemManager.createReportFile(optDateYList, _orgList);

		if (null == downLoadFileName) {
			return;
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String downLoadPath = sysConfig.getReportTmpPath() + downLoadFileName;

		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(downLoadFileName.getBytes("utf-8"), "ISO8859-1"));
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

	@RequestMapping(value = "brand_init")
	public String brand_init_Action(Model model) {

		List<SalesMonthTotalItem> _list = salesMonthTotalItemManager.getSalesTotalMonthListByBrand();

		List<Map<String, Object>> _dataList = Lists.newArrayList();
		for (SalesMonthTotalItem item : _list) {

			if (StringUtils.isBlank(item.getOrgBrand())) {
				continue;
			}

			Map<String, Object> _data = Maps.newHashMap();

			if ("Infancy".equals(item.getOrgBrand())) {
				_data.put("optDateYM", item.getOptDateYM());
				_data.put("saleRamt_IF", item.getSaleRamt());
			} else if ("EQ+".equals(item.getOrgBrand())) {
				_data.put("optDateYM", item.getOptDateYM());
				_data.put("saleRamt_EQ", item.getSaleRamt());
			}

			_dataList.add(_data);

		}

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("dataList", mapper.toJson(mergeDataList(_dataList)));

		return "report/salesMonthItemChartReport_brand";
	}

	@RequestMapping(value = "mngUser_init")
	public String mngUserInit_Action(Model model) {
		List<SalesMonthTotalItem> _list = salesMonthTotalItemManager.getSalesTotalMonthListByMngUser();

		List<Map<String, Object>> _dataList = Lists.newArrayList();

		String _tmpMngUser = "";
		int _index = 0;
		for (SalesMonthTotalItem item : _list) {
			if (!_tmpMngUser.equals(item.getMngUser())) {
				_tmpMngUser = item.getMngUser();
				_index++;

				model.addAttribute("mngUser" + _index, item.getMngUser());
			}
			Map<String, Object> _data = Maps.newHashMap();

			_data.put("mngUser" + _index, item.getMngUser());
			_data.put("optDateYM", item.getOptDateYM());
			_data.put("saleRamt" + _index, item.getSaleRamt());

			_dataList.add(_data);

		}

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("dataList", mapper.toJson(mergeDataList(_dataList)));
		model.addAttribute("mngUserNum", _index);

		return "report/salesMonthItemChartReport_mngUser";
	}

	private List<Map<String, Object>> mergeDataList(List<Map<String, Object>> _dataList) {
		Map<String, Map<String, Object>> _tmpMap = Maps.newHashMap();

		for (Map<String, Object> _data : _dataList) {
			if (_tmpMap.containsKey(_data.get("optDateYM"))) {
				Map<String, Object> _tData = _tmpMap.get(_data.get("optDateYM"));

				for (Entry<String, Object> _tMap : _data.entrySet()) {
					_tData.put(_tMap.getKey(), _tMap.getValue());
				}
			} else {
				_tmpMap.put((String) _data.get("optDateYM"), _data);
			}
		}

		List<Map<String, Object>> _reDataList = Lists.newArrayList();
		for (Map<String, Object> value : _tmpMap.values()) {

			_reDataList.add(value);

		}
		Collections.sort(_reDataList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return ((String) o1.get("optDateYM")).compareTo((String) o2.get("optDateYM"));
			}

		});
		return _reDataList;
	}
}

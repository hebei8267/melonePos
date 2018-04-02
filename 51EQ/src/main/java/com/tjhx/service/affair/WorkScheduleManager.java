package com.tjhx.service.affair;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.WorkScheduleJpaDao;
import com.tjhx.dao.affair.WorkScheduleMyBatisDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.affair.WorkSchedule;
import com.tjhx.entity.affair.WorkSchedule_List_Show;
import com.tjhx.entity.affair.WorkSchedule_Show;
import com.tjhx.entity.affair.WorkType;
import com.tjhx.entity.member.Employee;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class WorkScheduleManager {
	@Resource
	private WorkScheduleJpaDao wsJpaDao;
	@Resource
	private OrganizationJpaDao orgJpaDao;
	@Resource
	private WorkScheduleMyBatisDao wsMyBatisDao;
	@Resource
	private WorkTypeManager workTypeManager;

	/**
	 * 取得指定日期开始的所有排班信息
	 * 
	 * @param orgId
	 * @param scheduleDate(yyyyMMdd)
	 * @return
	 */
	public List<WorkSchedule> getWorkScheduleListByDate(String orgId, String scheduleDate) {
		// 数据库中已保存的排班表对象列表
		WorkSchedule _parmWs = new WorkSchedule();
		_parmWs.setOrgId(orgId);
		_parmWs.setScheduleDate(scheduleDate);
		List<WorkSchedule> _dbWsList = wsMyBatisDao.getWorkScheduleListByDate(_parmWs);
		return _dbWsList;
	}

	/**
	 * 取得排班信息列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<WorkSchedule_List_Show> getWorkScheduleList(String orgId, List<Employee> empList) throws ParseException {

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int scheduleOverDays = sysConfig.getWorkScheduleOverDays();
		int scheduleDays = sysConfig.getWorkScheduleDays();
		// 取得计算可排班天数（含明天，列表第一个对象）
		List<String> optDateList = calOptDate(scheduleOverDays, scheduleDays);

		// 页面显示排班表对象列表
		List<WorkSchedule_List_Show> wsList = new ArrayList<WorkSchedule_List_Show>();

		// 数据库中已保存的排班表对象列表
		List<WorkSchedule> _dbWsList = getWorkScheduleListByDate(orgId,
				DateUtils.transDateFormat(optDateList.get(0), "yyyy-MM-dd", "yyyyMMdd"));

		// 取得指定机构的上班类型信息Map(开启状态)
		Map<Integer, WorkType> _dbWtMap = workTypeManager.getValidWorkTypeMapByOrgId(orgId);

		for (int i = 1; i < optDateList.size(); i++) {
			WorkSchedule_List_Show ws = new WorkSchedule_List_Show();
			ws.setScheduleDate(optDateList.get(i - 1));
			ws.setWeek(DateUtils.getWeekOfDate(optDateList.get(i - 1), "yyyy-MM-dd"));
			// 开始可排版日期(以当前时间为基准-可编辑3天后的日期)
			 ws.setEditFlg(i - 3 > scheduleOverDays);
			// 开始可排版日期
			//ws.setEditFlg(i > scheduleOverDays);
			
			List<WorkSchedule_Show> scheduleList = new ArrayList<WorkSchedule_Show>();
			for (Employee emp : empList) {
				WorkSchedule_Show subWs = new WorkSchedule_Show();
				subWs.setEmpCode(emp.getCode());
				subWs.setScheduleDate(DateUtils.transDateFormat(optDateList.get(i - 1), "yyyy-MM-dd", "yyyyMMdd"));

				workSchedule_Show_ObjCopy(subWs, _dbWsList, _dbWtMap);

				scheduleList.add(subWs);
			}
			ws.setScheduleList(scheduleList);

			wsList.add(ws);
		}

		return wsList;
	}

	private void workSchedule_Show_ObjCopy(WorkSchedule_Show subWs, List<WorkSchedule> dbWsList,
			Map<Integer, WorkType> dbWtMap) {
		for (WorkSchedule ws : dbWsList) {
			if (subWs.getEmpCode().equals(ws.getEmpCode()) && subWs.getScheduleDate().equals(ws.getScheduleDate())) {
				subWs.setWorkTime(ws.getWorkTime());
				subWs.setWorkTypeUuid(ws.getWorkTypeUuid());
				WorkType _tmpWt = dbWtMap.get(ws.getWorkTypeUuid());
				if (null != _tmpWt) {
					subWs.setWorkTypeName(_tmpWt.getName());
				}

			}
		}
	}

	/**
	 * 计算可排班天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate(int scheduleOverDays, int scheduleDays) throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurFormatDate("yyyy-MM-dd");
		for (int i = scheduleOverDays; i > 0; i--) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyy-MM-dd"));
		}

		_optDateList.add(_now);

		for (int i = 1; i < scheduleDays + 1; i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, i, "yyyy-MM-dd"));
		}
		return _optDateList;
	}

	/**
	 * 保存/更新 排班信息
	 * 
	 * @param scheduleDate
	 * @param empCode
	 * @param scheduleTimeSelect
	 */
	@Transactional(readOnly = false)
	public void updateWorkScheduleList(String[] scheduleDate, String[] empCode, String[] scheduleTimeSelect,
			Map<String, String> wtDataMap, String orgBwId) {
		if (null == wtDataMap) {
			return;
		}

		Organization org = orgJpaDao.findByBwId(orgBwId);

		for (int i = 0; i < scheduleDate.length; i++) {
			String _tmpScDate = DateUtils.transDateFormat(scheduleDate[i], "yyyy-MM-dd", "yyyyMMdd");

			WorkSchedule _dbws = wsJpaDao.findByEmpCodeAndscheduleDate(empCode[i], _tmpScDate);

			if (null == _dbws) {// 未选择排班时间
				if (StringUtils.isBlank(scheduleTimeSelect[i])) {
					continue;
				}
				// 新增排班信息
				addWorkSchedule(org, empCode[i], scheduleDate[i], scheduleTimeSelect[i], wtDataMap);
			} else {

				if (StringUtils.isBlank(scheduleTimeSelect[i])) {
					// 删除排班信息
					wsJpaDao.delete(_dbws);
				} else {
					// 更新排班信息
					updateWorkSchedule(_dbws, org, scheduleTimeSelect[i], wtDataMap);
				}

			}
		}
	}

	/**
	 * 更新排班信息
	 * 
	 * @param _dbws
	 * @param org
	 * @param scheduleTimeSelect
	 * @param wtDataMap
	 */
	@Transactional(readOnly = false)
	private void updateWorkSchedule(WorkSchedule _dbws, Organization org, String scheduleTimeSelect,
			Map<String, String> wtDataMap) {
		// 员工编号-自定义
		// 排班日期
		// 排班日期

		// HH:mm格式
		String[] time1 = getTime1(wtDataMap, scheduleTimeSelect);
		// 上班时间 HH:mm
		_dbws.setStartTime(time1[0]);
		// 下班时间 HH:mm
		_dbws.setEndTime(time1[1]);
		// 工作时间 HH:mm - HH:mm
		_dbws.setWorkTime(getDate(wtDataMap, scheduleTimeSelect));
		// 用户关联机构
		_dbws.setOrganization(org);
		// 上班类型Uuid
		_dbws.setWorkTypeUuid(Integer.parseInt(scheduleTimeSelect));

		wsJpaDao.save(_dbws);
	}

	/**
	 * 新增排班信息
	 * 
	 * @param org
	 * @param empCode
	 * @param scheduleDate
	 * @param scheduleTimeSelect
	 * @param wtDataMap
	 */
	@Transactional(readOnly = false)
	private void addWorkSchedule(Organization org, String empCode, String scheduleDate, String scheduleTimeSelect,
			Map<String, String> wtDataMap) {
		WorkSchedule ws = new WorkSchedule();

		// 员工编号-自定义
		ws.setEmpCode(empCode);
		// 排班日期
		ws.setScheduleDate(DateUtils.transDateFormat(scheduleDate, "yyyy-MM-dd", "yyyyMMdd"));
		// 排班日期
		ws.setScheduleShow(scheduleDate);

		// 排班日期YYYY */
		ws.setScheduleDateY(DateUtils.transDateFormat(scheduleDate, "yyyy-MM-dd", "yyyy"));
		// 排班日期MM */
		ws.setScheduleDateM(DateUtils.transDateFormat(scheduleDate, "yyyy-MM-dd", "MM"));
		// 排班日期YYYYMM */
		ws.setScheduleDateYM(DateUtils.transDateFormat(scheduleDate, "yyyy-MM-dd", "yyyyMM"));

		// HH:mm格式
		String[] time1 = getTime1(wtDataMap, scheduleTimeSelect);
		// 上班时间 HH:mm
		ws.setStartTime(time1[0]);
		// 下班时间 HH:mm
		ws.setEndTime(time1[1]);
		// 工作时间 HH:mm - HH:mm
		ws.setWorkTime(getDate(wtDataMap, scheduleTimeSelect));

		// 用户关联机构
		ws.setOrganization(org);
		// 上班类型Uuid
		ws.setWorkTypeUuid(Integer.parseInt(scheduleTimeSelect));

		wsJpaDao.save(ws);
	}

	/**
	 * 返回 开始/结束时间 HH:mm - HH:mm
	 * 
	 * @param wtDataMap
	 * @param scheduleTimeSelectKey
	 * @return
	 */
	private String getDate(Map<String, String> wtDataMap, String scheduleTimeSelectKey) {
		return wtDataMap.get(scheduleTimeSelectKey);
	}

	/**
	 * 返回 开始/结束时间 HH:mm
	 * 
	 * @param wtDataMap
	 * @param scheduleTimeSelectKey
	 * @return
	 */
	private String[] getTime1(Map<String, String> wtDataMap, String scheduleTimeSelectKey) {
		String date = wtDataMap.get(scheduleTimeSelectKey);
		String[] result = new String[2];
		result[0] = date.substring(0, 5);
		result[1] = date.substring(8, 13);

		return result;
	}

	/**
	 * 取得指定月份的排班信息
	 * 
	 * @param orgId
	 * @param scheduleDateYM
	 * @return
	 */
	public List<WorkSchedule> getWorkScheduleListByDateYM(String orgId, String scheduleDateYM) {
		WorkSchedule _parmWs = new WorkSchedule();
		_parmWs.setOrgId(orgId);
		_parmWs.setScheduleDateYM(scheduleDateYM);
		List<WorkSchedule> _dbWsList = wsMyBatisDao.getWorkScheduleListByDateYM(_parmWs);

		return _dbWsList;
	}

	/**
	 * 取得排班信息列表(指定日期 yyyy-MM)
	 * 
	 * @param orgId
	 * @param empList
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public List<WorkSchedule_List_Show> getWorkScheduleList(String orgId, List<Employee> empList, String date)
			throws ParseException {
		String dateY = DateUtils.transDateFormat(date, "yyyy-MM", "yyyy");
		String dateM = DateUtils.transDateFormat(date, "yyyy-MM", "MM");
		List<String> optDateList = calOptDate(dateY, dateM);

		// 页面显示排班表对象列表
		List<WorkSchedule_List_Show> wsList = new ArrayList<WorkSchedule_List_Show>();

		// 数据库中已保存的排班表对象列表
		List<WorkSchedule> _dbWsList = getWorkScheduleListByDateYM(orgId, dateY + dateM);

		// 取得指定机构的上班类型信息Map(开启状态)
		Map<Integer, WorkType> _dbWtMap = workTypeManager.getValidWorkTypeMapByOrgId(orgId);

		for (int i = 1; i <= optDateList.size(); i++) {
			WorkSchedule_List_Show ws = new WorkSchedule_List_Show();
			ws.setScheduleDate(DateUtils.transDateFormat(optDateList.get(i - 1), "yyyyMMdd", "yyyy-MM-dd"));
			ws.setWeek(DateUtils.getWeekOfDate(optDateList.get(i - 1), "yyyyMMdd"));
			ws.setEditFlg(false);

			List<WorkSchedule_Show> scheduleList = new ArrayList<WorkSchedule_Show>();
			for (Employee emp : empList) {
				WorkSchedule_Show subWs = new WorkSchedule_Show();
				subWs.setEmpCode(emp.getCode());
				subWs.setScheduleDate(optDateList.get(i - 1));

				workSchedule_Show_ObjCopy(subWs, _dbWsList, _dbWtMap);

				scheduleList.add(subWs);
			}
			ws.setScheduleList(scheduleList);

			wsList.add(ws);
		}
		return wsList;
	}

	private List<String> calOptDate(String year, String month) {
		int days = DateUtils.getMonthDays(Integer.valueOf(year), Integer.valueOf(month));

		List<String> _optDateList = new ArrayList<String>();

		for (int i = 1; i <= days; i++) {
			_optDateList.add(year + month + String.format("%02d", i));
		}
		return _optDateList;
	}
}

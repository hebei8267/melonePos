package com.tjhx.service.affair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.affair.WorkTypeJpaDao;
import com.tjhx.dao.struct.OrganizationJpaDao;
import com.tjhx.entity.affair.WorkType;
import com.tjhx.entity.struct.Organization;

@Service
@Transactional(readOnly = true)
public class WorkTypeManager {

	@Resource
	private WorkTypeJpaDao workTypeJpaDao;
	@Resource
	private OrganizationJpaDao orgJpaDao;

	/**
	 * 初始化新机构的关联上班类型信息
	 * 
	 * @param orgUuid
	 */
	public void initNewOrgWorkType(Integer orgUuid) {
		Organization org = orgJpaDao.findOne(orgUuid);
		if (null == org) {
			return;
		}

		WorkType w1 = new WorkType();

		w1.setName("早班");
		w1.setUseFlg("0");
		w1.setOrganization(org);

		workTypeJpaDao.save(w1);
		// ----------------------------------------------
		WorkType w2 = new WorkType();

		w2.setName("晚班");
		w2.setUseFlg("0");
		w2.setOrganization(org);

		workTypeJpaDao.save(w2);
		// ----------------------------------------------
		WorkType w3 = new WorkType();

		w3.setName("全天班");
		w3.setUseFlg("0");
		w3.setOrganization(org);

		workTypeJpaDao.save(w3);
		// ----------------------------------------------
		WorkType w4 = new WorkType();

		w4.setName("高峰班1");
		w4.setUseFlg("0");
		w4.setOrganization(org);
		w4.setEditFlg(true);

		workTypeJpaDao.save(w4);
		// ----------------------------------------------
		WorkType w5 = new WorkType();

		w5.setName("高峰班2");
		w5.setUseFlg("0");
		w5.setOrganization(org);
		w5.setEditFlg(true);

		workTypeJpaDao.save(w5);
		// ----------------------------------------------
		WorkType w6 = new WorkType();

		w6.setName("高峰班3");
		w6.setUseFlg("0");
		w6.setOrganization(org);
		w6.setEditFlg(true);

		workTypeJpaDao.save(w6);
		// ----------------------------------------------
		WorkType w7 = new WorkType();

		w7.setName("高峰班4");
		w7.setUseFlg("0");
		w7.setOrganization(org);
		w7.setEditFlg(true);

		workTypeJpaDao.save(w7);
		// ----------------------------------------------
		WorkType w8 = new WorkType();

		w8.setName("高峰班5");
		w8.setUseFlg("0");
		w8.setOrganization(org);
		w8.setEditFlg(true);

		workTypeJpaDao.save(w8);
	}

	/**
	 * 清除机构的关联上班类型信息
	 * 
	 * @param orgUuid
	 */
	public void cleanOrgWorkType(Integer orgUuid) {
		Organization org = orgJpaDao.findOne(orgUuid);
		if (null == org) {
			return;
		}
		// 删除原有机构的关联上班类型信息
		List<WorkType> workTypeList = workTypeJpaDao.getWorkTypeListByOrgId(org.getId());
		if (null != workTypeList && workTypeList.size() > 0) {
			workTypeJpaDao.delete(workTypeList);
		}
	}

	/**
	 * 取得指定机构的上班类型信息列表(开启状态)
	 * 
	 * @param orgId
	 * @return
	 */
	public List<WorkType> getValidWorkTypeByOrgId(String orgId) {
		List<WorkType> workTypeList = workTypeJpaDao.getValidWorkTypeByOrgId(orgId);

		return workTypeList;
	}

	/**
	 * 取得指定机构的上班类型信息Map(开启状态)
	 * 
	 * @param orgId
	 * @return
	 */
	public Map<Integer, WorkType> getValidWorkTypeMapByOrgId(String orgId) {
		List<WorkType> workTypeList = workTypeJpaDao.getValidWorkTypeByOrgId(orgId);

		Map<Integer, WorkType> wtMap = new HashMap<Integer, WorkType>();
		for (WorkType workType : workTypeList) {
			wtMap.put(workType.getUuid(), workType);
		}
		return wtMap;
	}

	/**
	 * 取得指定机构的上班类型信息列表
	 * 
	 * @param orgId
	 * @return
	 */
	public List<WorkType> getWorkTypeByOrgId(String orgId) {
		List<WorkType> workTypeList = workTypeJpaDao.getWorkTypeListByOrgId(orgId);

		return workTypeList;
	}

	/**
	 * 更新上班类型信息
	 * 
	 * @param orgId
	 * @param workTypeList
	 */
	@Transactional(readOnly = false)
	public void updateWorkTypeList(String orgId, List<WorkType> workTypeList) {
		List<WorkType> _dbWorkTypeList = getWorkTypeByOrgId(orgId);

		for (WorkType dbWorkType : _dbWorkTypeList) {
			WorkType workType = myEquals(dbWorkType, workTypeList);
			if (null != workType) {
				if ("1".equals(workType.getUseFlg())) {// 启用

					dbWorkType.setName(workType.getName());
					dbWorkType.setStartDate(workType.getStartDate());
					dbWorkType.setEndDate(workType.getEndDate());

					dbWorkType.set_startDate(workType.get_startDate());
					dbWorkType.set_endDate(workType.get_endDate());
					dbWorkType.setWorkDate(workType.getWorkDate());

				} else {

					dbWorkType.setStartDate(null);
					dbWorkType.setEndDate(null);

					dbWorkType.set_startDate(null);
					dbWorkType.set_endDate(null);
					dbWorkType.setWorkDate(null);

				}
				dbWorkType.setUseFlg(workType.getUseFlg());

				workTypeJpaDao.save(dbWorkType);
			}
		}
	}

	private WorkType myEquals(WorkType dbWorkType, List<WorkType> workTypeList) {
		for (WorkType workType : workTypeList) {
			if (workType.getUuid().equals(dbWorkType.getUuid())) {
				return workType;
			}
		}
		return null;
	}

}

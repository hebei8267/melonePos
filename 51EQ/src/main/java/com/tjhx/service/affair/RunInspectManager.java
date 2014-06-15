package com.tjhx.service.affair;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.RunInspectDetailJpaDao;
import com.tjhx.dao.affair.RunInspectDetailMyBatisDao;
import com.tjhx.dao.affair.RunInspectJpaDao;
import com.tjhx.entity.affair.RunInspect;
import com.tjhx.entity.affair.RunInspectDetail;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class RunInspectManager {
	@Resource
	private RunInspectJpaDao runInspectJpaDao;
	@Resource
	private RunInspectDetailJpaDao runInspectDetailJpaDao;
	@Resource
	private RunInspectDetailMyBatisDao runInspectDetailMyBatisDao;

	/**
	 * 删除门店巡查报告(运营)信息
	 * 
	 * @param uuid RunInspect编号
	 */
	@Transactional(readOnly = false)
	public void delRunInspectByUuid(Integer uuid) {
		RunInspect run = getRunInspectByUuid(uuid);

		if (null != run) {
			runInspectDetailMyBatisDao.delRunInspectInfo(run.getTrsId());
		}

		runInspectJpaDao.delete(run);
	}

	/**
	 * 取得门店巡查报告(运营)信息
	 * 
	 * @param id
	 * @return
	 */
	public RunInspect getRunInspectByUuid(Integer uuid) {
		return runInspectJpaDao.findOne(uuid);
	}

	/**
	 * 添加新门店巡查报告(运营)信息
	 * 
	 * @param orgId
	 * @param dutyPerson
	 * @param optDate
	 * @param assessors
	 * @param comments
	 * @param feedback
	 * @param goodsIssue
	 * @param penaltyCase
	 * @param trainingStatistics
	 * @param inventoryStatistics
	 * @param typeNos
	 * @param ids
	 * @param itemSelect
	 * @param score1
	 * @param score2
	 */
	@Transactional(readOnly = false)
	public void addNewRunInspect(String orgId, String dutyPerson, String optDate, String assessors, String comments,
			String feedback, String goodsIssue, String penaltyCase, String trainingStatistics, String inventoryStatistics,
			String[] typeNos, String[] ids, boolean[] itemSelect, int score1, int score2) {

		String trsId = optDate + orgId;

		RunInspect _dbrun = runInspectJpaDao.findByTrsId(trsId);

		if (null != _dbrun) {
			throw new ServiceException("ERR_MSG_RUN_INSPECT_001");
		}

		RunInspect run = new RunInspect();

		// 门店巡查报告(运营)编号
		run.setTrsId(trsId);
		// 店铺名称
		run.setOrgId(orgId);
		// 当班负责人
		run.setDutyPerson(dutyPerson);
		// 评核日期
		run.setOptDateY(DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy"));
		// 评核日期
		run.setOptDateM(DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM"));
		// 评核日期
		run.setOptDate(optDate);
		// 评核员
		run.setAssessors(assessors);
		// 收银台礼仪-得分
		run.setScore1(score1);
		// 卖场巡检-得分
		run.setScore2(score2);
		// 意见或建议
		run.setComments(comments);
		// 店铺反馈问题及跟进
		run.setFeedback(feedback);
		// 货品问题的发现及跟进
		run.setGoodsIssue(goodsIssue);
		// 现场违纪违规及处罚情况
		run.setPenaltyCase(penaltyCase);
		// 培训统计
		run.setTrainingStatistics(trainingStatistics);
		// 库存统计
		run.setInventoryStatistics(inventoryStatistics);

		runInspectJpaDao.save(run);

		for (int i = 0; i < typeNos.length; i++) {
			addNewRunInspectDetail(trsId, typeNos[i], ids[i], itemSelect[i]);
		}

	}

	/**
	 * 取得门店巡查报告(运营)-明细信息
	 * 
	 * @param trsId
	 * @return
	 */
	public List<RunInspectDetail> getRunInspectDetailList(String trsId) {
		return runInspectDetailJpaDao.getRunInspectDetailList(trsId);
	}

	/**
	 * 添加门店巡查报告(运营)-明细信息
	 * 
	 * @param trsId
	 * @param typeNo
	 * @param id
	 * @param selectFlg
	 */
	@Transactional(readOnly = false)
	public void addNewRunInspectDetail(String trsId, String typeNo, String id, boolean selectFlg) {
		RunInspectDetail run = new RunInspectDetail();

		// 门店巡查报告(运营)编号
		run.setTrsId(trsId);
		// 类型编号
		run.setTypeNo(typeNo);
		// 编号
		run.setId(id);
		// 选择标记 false-未选 true-已选
		run.setSelectFlg(selectFlg);

		runInspectDetailJpaDao.save(run);
	}

	/**
	 * 取得门店巡查报告(运营)信息列表
	 * 
	 * @param orgId
	 * @param optDateStart
	 * @param optDateEnd
	 */
	public List<RunInspect> getRunInspectList(String orgId, String optDateStart, String optDateEnd) {
		if (StringUtils.isBlank(orgId)) {
			return runInspectJpaDao.getRunInspectList(optDateStart, optDateEnd);
		} else {
			return runInspectJpaDao.getRunInspectList(orgId, optDateStart, optDateEnd);
		}
	}

	/**
	 * 更新门店巡查报告(运营)信息
	 * 
	 * @param orgId
	 * @param dutyPerson
	 * @param optDate
	 * @param assessors
	 * @param comments
	 * @param feedback
	 * @param goodsIssue
	 * @param penaltyCase
	 * @param trainingStatistics
	 * @param inventoryStatistics
	 * @param typeNos
	 * @param ids
	 * @param itemSelect
	 */
	@Transactional(readOnly = false)
	public void updateRunInspect(String orgId, String dutyPerson, String optDate, String assessors, String comments,
			String feedback, String goodsIssue, String penaltyCase, String trainingStatistics, String inventoryStatistics,
			String[] typeNos, String[] ids, boolean[] itemSelect, int score1, int score2) {
		String trsId = optDate + orgId;

		RunInspect _dbrun = runInspectJpaDao.findByTrsId(trsId);

		if (null == _dbrun) {
			throw new ServiceException("ERR_MSG_RUN_INSPECT_002");
		}

		// 门店巡查报告(运营)编号
		_dbrun.setTrsId(trsId);
		// 店铺名称
		_dbrun.setOrgId(orgId);
		// 当班负责人
		_dbrun.setDutyPerson(dutyPerson);
		// 评核日期
		_dbrun.setOptDateY(DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy"));
		// 评核日期
		_dbrun.setOptDateM(DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM"));
		// 评核日期
		_dbrun.setOptDate(optDate);
		// 评核员
		_dbrun.setAssessors(assessors);
		// 收银台礼仪-得分
		_dbrun.setScore1(score1);
		// 卖场巡检-得分
		_dbrun.setScore2(score2);
		// 意见或建议
		_dbrun.setComments(comments);
		// 店铺反馈问题及跟进
		_dbrun.setFeedback(feedback);
		// 货品问题的发现及跟进
		_dbrun.setGoodsIssue(goodsIssue);
		// 现场违纪违规及处罚情况
		_dbrun.setPenaltyCase(penaltyCase);
		// 培训统计
		_dbrun.setTrainingStatistics(trainingStatistics);
		// 库存统计
		_dbrun.setInventoryStatistics(inventoryStatistics);

		runInspectJpaDao.save(_dbrun);

		runInspectDetailMyBatisDao.delRunInspectInfo(trsId);
		for (int i = 0; i < typeNos.length; i++) {
			addNewRunInspectDetail(trsId, typeNos[i], ids[i], itemSelect[i]);
		}

	}

}
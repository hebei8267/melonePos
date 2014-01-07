package com.tjhx.service.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.InspectJpaDao;
import com.tjhx.dao.affair.InspectMyBatisDao;
import com.tjhx.entity.affair.Inspect;
import com.tjhx.entity.member.User;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class InspectManager {

	@Resource
	private InspectJpaDao inspectJpaDao;

	@Resource
	private InspectMyBatisDao inspectMyBatisDao;

	/**
	 * 根据编号取得门店巡查报告信息
	 * 
	 * @param uuid 门店巡查报告编号
	 * @return 门店巡查报告信息
	 */
	public Inspect getInspectByUuid(Integer uuid) {
		return inspectJpaDao.findOne(uuid);
	}

	/**
	 * 删除门店巡查报告信息
	 * 
	 * @param uuid 门店巡查报告编号
	 */
	@Transactional(readOnly = false)
	public void delInspectByUuid(Integer uuid) {
		inspectJpaDao.delete(uuid);
	}

	/**
	 * 添加新门店巡查报告信息
	 * 
	 * @param cashRun 门店巡查报告信息
	 */
	@Transactional(readOnly = false)
	public void addNewInspect(Inspect inspect, User user) {
		Inspect _dbInspect = inspectJpaDao.findByTrsId(inspect.getTrsId());
		// 该门店巡查报告信息已存在
		if (null != _dbInspect) {
			throw new ServiceException("ERR_MSG_INSPECT_001");
		}

		String _date = DateUtils.transDateFormat(inspect.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");

		// 日期
		inspect.setOptDate(_date);
		// 日期-年
		inspect.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 日期-月
		inspect.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));

		inspectJpaDao.save(inspect);
	}

	/**
	 * 更新门店巡查报告信息
	 * 
	 * @param inspect 门店巡查报告信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateInspect(Inspect inspect, User user) throws IllegalAccessException, InvocationTargetException {

		Inspect _dbInspect = inspectJpaDao.findOne(inspect.getUuid());
		if (null == _dbInspect) {
			// 门店巡查报告不存在!
			throw new ServiceException("ERR_MSG_INSPECT_002");
		}

		// 巡查机构编号
		_dbInspect.setOrgId(inspect.getOrgId());

		// 巡查日期-显示
		_dbInspect.setOptDateShow(inspect.getOptDateShow());

		String _date = DateUtils.transDateFormat(inspect.getOptDateShow(), "yyyy-MM-dd", "yyyyMMdd");
		// 巡查日期
		_dbInspect.setOptDate(_date);
		// 巡查日期-年
		_dbInspect.setOptDateY(DateUtils.transDateFormat(_date, "yyyyMMdd", "yyyy"));
		// 巡查日期-月
		_dbInspect.setOptDateM(DateUtils.transDateFormat(_date, "yyyyMMdd", "MM"));

		// 巡查时间
		_dbInspect.setOptTime(inspect.getOptTime());
		// 班次(1-A早班、2-B晚班)
		_dbInspect.setJobType(inspect.getJobType());
		// 当班负责人
		_dbInspect.setDutyPer(inspect.getDutyPer());

		// 班前余额-A
		_dbInspect.setInitAmt(inspect.getInitAmt());
		// 实际销售现金-B
		_dbInspect.setCashAmt(inspect.getCashAmt());
		// 存款金额-C
		_dbInspect.setDepositAmt(inspect.getDepositAmt());
		// 销售现金-D
		_dbInspect.setSaleCashAmt(inspect.getSaleCashAmt());
		// 现金小计(A+B+C)
		_dbInspect.setCashSubtotal(inspect.getCashSubtotal());
		// 现金小计盈亏(A+B+C-D)
		_dbInspect.setAdjustAmt(inspect.getAdjustAmt());
		// 结论 1-完全相等、2基本相等、4不符，调查原因
		_dbInspect.setCashConclusion(inspect.getCashConclusion());
		// 结论 1备注
		_dbInspect.setCashConclusionTxt(inspect.getCashConclusionTxt());

		// 备用金日记账余额-E
		_dbInspect.setImprestCalance(inspect.getImprestCalance());
		// 已作报销支出尚未记账的支出金额-F
		_dbInspect.setExpImprestAmt(inspect.getExpImprestAmt());
		// 实际清点的备用金额-G
		_dbInspect.setClearImprestAmt(inspect.getClearImprestAmt());
		// 备用金小计1(E-F)
		_dbInspect.setImprestSubtotal1(inspect.getImprestSubtotal1());
		// 备用金小计2(E-F-G)
		_dbInspect.setImprestSubtotal2(inspect.getImprestSubtotal2());
		// 结论 1-完全相等、2基本相等、4不符，调查原因
		_dbInspect.setImprestConclusion(inspect.getImprestConclusion());
		// 结论 2备注
		_dbInspect.setImprestConclusionTxt(inspect.getImprestConclusionTxt());

		// 巡查人
		_dbInspect.setInspectPer(inspect.getInspectPer());

		inspectJpaDao.save(_dbInspect);
	}

	public List<Inspect> searchInspectList(Inspect inspect) {
		return inspectMyBatisDao.getInspectList(inspect);
	}
}

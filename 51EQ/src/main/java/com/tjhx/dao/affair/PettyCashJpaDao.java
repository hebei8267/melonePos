package com.tjhx.dao.affair;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.PettyCash;

public interface PettyCashJpaDao extends CrudRepository<PettyCash, Integer> {
	@Query("select p from PettyCash p where p.optUid = :optUid")
	public PettyCash findByOptUid(@Param("optUid") String optUid);

	/**
	 * 查询指定机构/更新时间之后的备用金信息
	 * 
	 * @param orgId 机构编号
	 * @param createDate 创建时间
	 * @param sort
	 * @return
	 */
	@Query("select p from PettyCash p where p.orgId = :orgId and p.optDate >= :optDate")
	public List<PettyCash> findByOrgId(@Param("orgId") String orgId, @Param("optDate") String optDate, Sort sort);

	/**
	 * 根据查询条件 取得指定门店备用金余额（指定时间段内）
	 * 
	 * @param orgId 机构编号
	 * @param optDate_start
	 * @param optDate_end
	 * @return
	 */
	@Query("select p from PettyCash p where p.orgId = :orgId and p.optDate >= :optDateStart and p.optDate <= :optDateEnd")
	public List<PettyCash> findByOrgIdAndOptDateInterval(@Param("orgId") String orgId,
			@Param("optDateStart") String optDateStart, @Param("optDateEnd") String optDateEnd, Sort sort);

	/**
	 * 根据查询条件 取得指定门店备用金余额（指定时间段内）
	 * 
	 * @param orgId 机构编号
	 * @param optDateStart
	 * @param optDateEnd
	 * @param expType
	 * @return
	 */
	@Query("select p from PettyCash p where p.orgId = :orgId and p.optDate >= :optDateStart and p.optDate <= :optDateEnd and p.expType = :expType")
	public List<PettyCash> findByOrgIdAndOptDateInterval(@Param("orgId") String orgId,
			@Param("optDateStart") String optDateStart, @Param("optDateEnd") String optDateEnd, @Param("expType") String expType,
			Sort sort);

	/**
	 * 取得指定门店未结转的备用金信息
	 * 
	 * @param orgId 机构编号
	 * @param sort
	 * @return
	 */
	@Query("select p from PettyCash p where p.orgId = :orgId and p.carryOverFlg = 'false' ")
	public List<PettyCash> findByOrgIdAndCarryOverFlg(@Param("orgId") String orgId, Sort sort);

	/**
	 * @param orgId
	 * @param optDateStart
	 * @param optDateEnd
	 * @param sort
	 * @return
	 */
	@Query("select p from PettyCash p where p.orgId = :orgId and CONCAT(p.optDateY, p.optDateM) >= :optDateStart and CONCAT(p.optDateY, p.optDateM) <= :optDateEnd and p.expType is not null ")
	public List<PettyCash> findByOrgIdAndOptDateInterval_AllExpType(@Param("orgId") String orgId,
			@Param("optDateStart") String optDateStart, @Param("optDateEnd") String optDateEnd, Sort sort);

}

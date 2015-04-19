/**
 * 
 */
package com.tjhx.dao.info;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.info.BudgetSubject;

/**
 *
 */
public interface BudgetSubjectJpaDao extends CrudRepository<BudgetSubject, Integer> {

	/**
	 * 取得一级科目（机构信息）
	 * 
	 * @return
	 */
	@Query("select bs from BudgetSubject bs where bs.level = 1 and bs.delFlg = '0' order by sortIndex")
	List<BudgetSubject> findLevelOneBudgetSubjectList();

}

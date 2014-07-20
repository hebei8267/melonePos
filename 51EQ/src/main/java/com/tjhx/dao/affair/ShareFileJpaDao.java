package com.tjhx.dao.affair;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.affair.ShareFile;

public interface ShareFileJpaDao extends CrudRepository<ShareFile, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 根据文件编号取得共享文件
	 * 
	 * @param fileNo
	 * @return
	 */
	@Query("select f from ShareFile f where f.fileNo = :fileNo")
	public ShareFile findByNo(@Param("fileNo") String fileNo);
}

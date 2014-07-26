package com.tjhx.service.affair;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.affair.ShareFileJpaDao;
import com.tjhx.dao.affair.ShareFileMyBatisDao;
import com.tjhx.entity.affair.ShareFile;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class ShareFileManager {
	@Resource
	private ShareFileJpaDao shareFileJpaDao;
	@Resource
	private ShareFileMyBatisDao shareFileMyBatisDao;

	/**
	 * 取得共享文件（在用）信息
	 * 
	 * @return
	 */
	public List<ShareFile> getValidShareFileList() {
		ShareFile param = new ShareFile();
		param.setStatus("00");
		return shareFileMyBatisDao.getShareFileList(param);
	}

	/**
	 * 取得共享文件信息
	 * 
	 * @param status 共享文件状态
	 * @return 共享文件信息列表
	 */
	public List<ShareFile> getShareFileList(String status) {
		ShareFile param = new ShareFile();
		param.setStatus(status);
		return shareFileMyBatisDao.getShareFileList(param);
	}

	/**
	 * 根据编号取得共享文件信息
	 * 
	 * @param uuid 共享文件编号
	 * @return 共享文件信息
	 */
	public ShareFile getShareFileByUuid(Integer uuid) {
		return shareFileJpaDao.findOne(uuid);
	}

	/**
	 * 删除共享文件信息
	 * 
	 * @param uuid 共享文件编号
	 */
	@Transactional(readOnly = false)
	public void delShareFileByUuid(Integer uuid) {
		shareFileJpaDao.delete(uuid);
	}

	/**
	 * 添加共享文件
	 * 
	 * @param shareFile
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void addNewShareFile(ShareFile shareFile) throws IllegalStateException, IOException {
		ShareFile _dbShareFile = shareFileJpaDao.findByNo(shareFile.getFileNo());
		// 该共享文件已存在!
		if (null != _dbShareFile) {
			throw new ServiceException("ERR_MSG_SHARE_FILE_001");
		}

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		String storePath = DateUtils.getCurrentDateShortStr() + File.separator;
		String storeName = UUID.randomUUID().toString() + FileUtils.getSuffix(shareFile.getShareFile().getOriginalFilename());
		String uploadFilePath = sysConfig.getUploadShareFilePath() + storePath;
		// 设置文件存储名称(磁盘中)
		shareFile.setStoreName(storeName);
		// 设置文件存储全路径(磁盘中)
		shareFile.setStorePath(storePath + storeName);

		// 自动建立文件夹
		FileUtils.mkdir(uploadFilePath);
		// 保存文件至磁盘
		FileUtils.saveUploadFile(shareFile.getShareFile(), uploadFilePath, storeName);

		shareFileJpaDao.save(shareFile);
	}

	/**
	 * 更新共享文件
	 * 
	 * @param shareFile
	 */
	@Transactional(readOnly = false)
	public void updateShareFile(ShareFile shareFile) {

		ShareFile _dbShareFile = shareFileJpaDao.findOne(shareFile.getUuid());
		if (null == _dbShareFile) {
			// 共享文件不存在!
			throw new ServiceException("ERR_MSG_SHARE_FILE_002");
		}
		_dbShareFile.setFileShortName(shareFile.getFileShortName());
		_dbShareFile.setFileName(shareFile.getFileName());
		_dbShareFile.setStatus(shareFile.getStatus());

		shareFileJpaDao.save(_dbShareFile);

	}

}
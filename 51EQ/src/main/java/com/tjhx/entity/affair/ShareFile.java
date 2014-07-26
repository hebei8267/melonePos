/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.entity.IdEntity;

/**
 * 共享文件
 */
@Entity
@Table(name = "T_SHARE_FILE")
public class ShareFile extends IdEntity {

	private static final long serialVersionUID = 306142609016481658L;
	/** 文件编号 */
	private String fileNo;
	/** 文件名称 */
	private String fileName;
	/** 文件名称-简称 */
	private String fileShortName;
	/** 文件存储名称(磁盘中) */
	private String storeName;
	/** 文件存储全路径(磁盘中) */
	private String storePath;
	/** 文件状态 00-在用 01-停用 99-废除 */
	private String status;
	/** 共享文件 */
	private MultipartFile shareFile;

	/**
	 * 获取文件编号
	 * 
	 * @return fileNo 文件编号
	 */
	@NaturalId
	@Column(length = 8)
	public String getFileNo() {
		return fileNo;
	}

	/**
	 * 设置文件编号
	 * 
	 * @param fileNo 文件编号
	 */
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	/**
	 * 获取文件名称
	 * 
	 * @return fileName 文件名称
	 */
	@Column(length = 64)
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件名称
	 * 
	 * @param fileName 文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取文件名称-简称
	 * 
	 * @return fileShortName 文件名称-简称
	 */
	@Column(length = 32)
	public String getFileShortName() {
		return fileShortName;
	}

	/**
	 * 设置文件名称-简称
	 * 
	 * @param fileShortName 文件名称-简称
	 */
	public void setFileShortName(String fileShortName) {
		this.fileShortName = fileShortName;
	}

	/**
	 * 获取文件存储名称(磁盘中)
	 * 
	 * @return storeName 文件存储名称(磁盘中)
	 */
	@Column(length = 64)
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 设置文件存储名称(磁盘中)
	 * 
	 * @param storeName 文件存储名称(磁盘中)
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 获取文件存储全路径(磁盘中)
	 * 
	 * @return storePath 文件存储全路径(磁盘中)
	 */
	@Column(length = 128)
	public String getStorePath() {
		return storePath;
	}

	/**
	 * 设置文件存储全路径(磁盘中)
	 * 
	 * @param storePath 文件存储全路径(磁盘中)
	 */
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	/**
	 * 获取文件状态00-在用01-停用99-废除
	 * 
	 * @return status 文件状态00-在用01-停用99-废除
	 */
	@Column(length = 2)
	public String getStatus() {
		return status;
	}

	/**
	 * 设置文件状态00-在用01-停用99-废除
	 * 
	 * @param status 文件状态00-在用01-停用99-废除
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取共享文件
	 * 
	 * @return shareFile 共享文件
	 */
	@Transient
	public MultipartFile getShareFile() {
		return shareFile;
	}

	/**
	 * 设置共享文件
	 * 
	 * @param shareFile 共享文件
	 */
	public void setShareFile(MultipartFile shareFile) {
		this.shareFile = shareFile;
	}

}

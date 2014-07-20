package com.tjhx.dao.affair;

import java.util.List;

import com.tjhx.entity.affair.ShareFile;

public interface ShareFileMyBatisDao {

	public List<ShareFile> getShareFileList(ShareFile shareFile);

}

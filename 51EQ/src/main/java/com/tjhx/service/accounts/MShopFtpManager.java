package com.tjhx.service.accounts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.daobw.MShopDailySaleMyBatisDao;
import com.tjhx.entity.bw.MShopDailySale;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class MShopFtpManager {
	@Resource
	private MShopDailySaleMyBatisDao mShopDailySaleMyBatisDao;
	// M+商场提供的参数
	private final String ORG_ID = "WHTS2FL2036";
	private final String POS_ID = "9013";
	private final String GOOD_ID = "100178";
	// M+商场内部编号
	private final String EQ_ORG_ID = "0801";

	/**
	 * M+商场销售数据同步FTP
	 * 
	 * @throws ParseException
	 */
	public void synFtpFile() throws ParseException {
		String endDate = DateUtils.getCurFormatDate("yyyy-MM-dd");
		String beginDate = DateUtils.getNextDateFormatDate(endDate, -1, "yyyy-MM-dd");

		Map<String, String> param = Maps.newHashMap();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("bwBranchNo", EQ_ORG_ID);// M+商场内部编号

		List<MShopDailySale> _saleDataList = mShopDailySaleMyBatisDao.getDailySaleList(param);

		String dateTime = DateUtils.getCurFormatDate("yyyyMMddhhmmss");
		String fileName = ORG_ID + "_" + dateTime + "_LIST.txt";

		createFtpFile(fileName, _saleDataList);

		_uploadFtpFile(fileName);
	}

	private void _uploadFtpFile(String fileName) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		String _fileName = sysConfig.getReportTmpPath() + "/M+/" + fileName;

		FTPSClient ftpClient = new FTPSClient();
		try {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			// ftpClient.setAuthValue("SSL");
			ftpClient.connect("121.15.128.18");
			ftpClient.login("WHTS2FL2036", "m7Mre187");
			ftpClient.setDefaultPort(21);

			int reply = ftpClient.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
			ftpClient.enterLocalPassiveMode();

			// 设置上传目录
			ftpClient.changeWorkingDirectory("/");
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			boolean res_mainFileName = ftpClient.storeFile(fileName, new FileInputStream(new File(_fileName)));
			System.out.println("upload file:" + res_mainFileName);

			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
					// do nothing
				}
			}
		}
	}

	/**
	 * 销售小票主体文件MAIN
	 */
	private void createFtpFile(String fileName, List<MShopDailySale> saleDataList) {
		// 商铺号_20170414235959_LIST.txt
		String tmpFlowNo = null;
		MShopDailySale tmpMShopDailySale = null;

		List<MShopDailySale> fileList = Lists.newArrayList();
		for (MShopDailySale saleData : saleDataList) {
			if (null == tmpFlowNo || !tmpFlowNo.equals(saleData.getFlowNo())) {
				tmpFlowNo = saleData.getFlowNo();
				tmpMShopDailySale = new MShopDailySale();

				fileList.add(tmpMShopDailySale);
			}

			// 1-收银机号
			tmpMShopDailySale.setPosNo(POS_ID);
			// 2-交易流水号
			tmpMShopDailySale.setFlowNo(saleData.getFlowNo());
			// 3-交易时间
			tmpMShopDailySale.setOperDate(DateUtils.transDateFormat(saleData.getOperDate(), "yyyy-MM-dd HH:mm:ss.SSS",
					"yyyy-MM-dd HH:mm:ss"));
			// 4-整单实收金额
			tmpMShopDailySale.setSalePrice(tmpMShopDailySale.getSalePrice().add(saleData.getSalePrice()));
			// 5-货品编码 NULL
			// 6-付款方式
			tmpMShopDailySale.setOperType("");
			// 7-备注 NULL

		}

		_writeFtpFile(fileName, fileList);
	}

	/**
	 * 销售小票主体文件MAIN
	 */
	private void _writeFtpFile(String fileName, List<MShopDailySale> fileList) {
		FileWriter fw;
		try {
			SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
			fw = new FileWriter(sysConfig.getReportTmpPath() + "/M+/" + fileName);

			for (MShopDailySale saleData : fileList) {

				// 1-收银机号
				fw.write(saleData.getPosNo() + ",");
				// 2-交易流水号
				fw.write(saleData.getFlowNo() + ",");
				// 3-交易时间
				fw.write(saleData.getOperDate() + ",");
				// 4-整单实收金额
				fw.write(saleData.getSalePrice() + ",");
				// 5-货品编码
				fw.write(GOOD_ID + ",");
				// 6-付款方式
				fw.write(saleData.getOperType() + ",");
				// 7-备注
				fw.write("" + ",");
				fw.write("\r\n");

			}

			fw.flush();

			fw.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}

	}
}

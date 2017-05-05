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
	private final String EQ_ORG_ID = "1301";

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

		createMainFile(dateTime, _saleDataList);
		createDetailFile(dateTime, _saleDataList);

		_synFtpFile(dateTime);
	}

	/**
	 * 同步FTP文件
	 */
	private void _synFtpFile(String dateTime) {

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		String mainFileName = sysConfig.getReportTmpPath() + "/M+/" + ORG_ID + "_" + dateTime + "_MAIN.txt";
		String detailFileName = sysConfig.getReportTmpPath() + "/M+/" + ORG_ID + "_" + dateTime + "_DETAIL.txt";
		String payFileName = sysConfig.getReportTmpPath() + "/M+/" + ORG_ID + "_" + dateTime + "_PAY.txt";

		_synFtpFile(mainFileName, detailFileName, payFileName);

	}

	private void _synFtpFile(String mainFileName, String detailFileName, String payFileName) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		String _mainFileName = sysConfig.getReportTmpPath() + "/M+/" + mainFileName;
		String _detailFileName = sysConfig.getReportTmpPath() + "/M+/" + detailFileName;
		String _payFileName = sysConfig.getReportTmpPath() + "/M+/" + payFileName;

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

			boolean res_mainFileName = ftpClient.storeFile(mainFileName, new FileInputStream(new File(_mainFileName)));
			System.out.println("mainFileName" + res_mainFileName);

			boolean res_detailFileName = ftpClient.storeFile(detailFileName, new FileInputStream(new File(_detailFileName)));
			System.out.println("detailFileName" + res_detailFileName);

			boolean res_payFileName = ftpClient.storeFile(payFileName, new FileInputStream(new File(_payFileName)));
			System.out.println("payFileName" + res_payFileName);

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
	 * 销售小票商品明细DETAIL
	 * 
	 * @param fileName
	 * @param _saleDataList
	 */
	private void createDetailFile(String fileName, List<MShopDailySale> _saleDataList) {
		FileWriter fw;
		try {
			SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
			fw = new FileWriter(sysConfig.getReportTmpPath() + "/M+/" + ORG_ID + "_" + fileName + "_DETAIL.txt");

			for (MShopDailySale saleData : _saleDataList) {
				// 店铺ID
				fw.write(ORG_ID + ",");
				// POS机编号
				fw.write(POS_ID + ",");
				// 销售单号
				fw.write(saleData.getFlowNo() + ",");
				// 货品明细ID
				fw.write("00001" + ",");
				// 货品编号（商铺自己销售的货品条码，由M+商场提供。）
				fw.write(GOOD_ID + ",");
				// 商品销售金额
				fw.write(saleData.getSalePrice() + ",");
				// 商品销售数量
				fw.write(saleData.getSaleQnty() + ",");
				// 明细实收金额
				fw.write(saleData.getSalePrice() + ",");
				// 明细优惠金额
				fw.write("0.0" + ",");
				// 备注-默认为空
				fw.write(",");
				fw.write("\r\n");
			}

			fw.flush();

			fw.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}

	}

	/**
	 * 销售小票付款明细PAY
	 */
	private void _writePayFile(String dateTime, List<MShopDailySale> fileList) {
		FileWriter fw;
		try {
			SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
			fw = new FileWriter(sysConfig.getReportTmpPath() + "/M+/" + ORG_ID + "_" + dateTime + "_PAY.txt");

			for (MShopDailySale saleData : fileList) {
				// 店铺ID
				fw.write(saleData.getOrgId() + ",");
				// POS机编号
				fw.write(saleData.getPosNo() + ",");
				// 销售单号
				fw.write(saleData.getFlowNo() + ",");
				// 付款明细ID
				fw.write("01" + ",");
				// 付款方式
				fw.write("01" + ",");
				// 商品销售金额
				fw.write(saleData.getSalePrice() + ",");
				// 备注-默认为空
				fw.write(",");
				fw.write("\r\n");

			}

			fw.flush();

			fw.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}

	}

	/**
	 * 销售小票主体文件MAIN
	 */
	private void createMainFile(String dateTime, List<MShopDailySale> saleDataList) {
		// 商铺号_20170414235959_MAIN.txt
		String tmpFlowNo = null;
		MShopDailySale tmpMShopDailySale = null;
		int tmpRowNum = 0;

		List<MShopDailySale> fileList = Lists.newArrayList();
		for (MShopDailySale saleData : saleDataList) {
			if (null == tmpFlowNo || !tmpFlowNo.equals(saleData.getFlowNo())) {
				tmpFlowNo = saleData.getFlowNo();
				tmpMShopDailySale = new MShopDailySale();
				tmpRowNum = 0;

				fileList.add(tmpMShopDailySale);
			}

			// 店铺ID
			tmpMShopDailySale.setOrgId(ORG_ID);
			// POS机编号
			tmpMShopDailySale.setPosNo(POS_ID);
			// 销售单号
			tmpMShopDailySale.setFlowNo(saleData.getFlowNo());
			// 交易类型 -1销售2红冲销售3退货4红冲退货
			tmpMShopDailySale.setOperType("1");
			// 日期
			tmpMShopDailySale.setOperDate(DateUtils.transDateFormat(saleData.getOperDate(), "yyyy-MM-dd HH:mm:ss.SSS",
					"yyyy-MM-dd HH:mm:ss"));
			// 商品销售金额
			tmpMShopDailySale.setSalePrice(tmpMShopDailySale.getSalePrice().add(saleData.getSalePrice()));
			// 交易明细行数
			tmpMShopDailySale.setRowNum(++tmpRowNum);

		}

		_writeMainFile(dateTime, fileList);
		_writePayFile(dateTime, fileList);
	}

	/**
	 * 销售小票主体文件MAIN
	 */
	private void _writeMainFile(String dateTime, List<MShopDailySale> fileList) {
		FileWriter fw;
		try {
			SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
			fw = new FileWriter(sysConfig.getReportTmpPath() + "/M+/" + ORG_ID + "_" + dateTime + "_MAIN.txt");

			for (MShopDailySale saleData : fileList) {
				// 店铺ID
				fw.write(saleData.getOrgId() + ",");
				// POS机编号
				fw.write(saleData.getPosNo() + ",");
				// 销售单号
				fw.write(saleData.getFlowNo() + ",");
				// 交易类型 -1销售2红冲销售3退货4红冲退货
				fw.write(saleData.getOperType() + ",");
				// 日期
				fw.write(saleData.getOperDate() + ",");
				// 商品销售金额
				fw.write(saleData.getSalePrice() + ",");
				// 优惠金额
				fw.write("0.0" + ",");
				// 原交易流水号
				fw.write(",");
				// 交易明细行数
				fw.write(saleData.getRowNum() + ",");
				// 付款明细行数-如此单只有现金付款则为1种付款方式，录入1，如此单有刷卡和现金2种付款方式，录入2，以此类推
				fw.write("2" + ",");
				// 已读标识一律写0
				fw.write("0" + ",");
				// 备注-默认为空
				fw.write(",");
				fw.write("\r\n");

			}

			fw.flush();

			fw.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}

	}
}

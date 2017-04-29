package com.tjhx.service.accounts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
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
	private final String POS_ID = "0000";
	private final String GOOD_ID = "100178";
	// M+商场内部编号
	private final String EQ_ORG_ID = "1301";
	private FTPClient ftp;

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
		// TODO Auto-generated method stub
		try {
			SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
			String mainFileName = ORG_ID + "_" + dateTime + "_MAIN.txt";
			String detailFileName = ORG_ID + "_" + dateTime + "_DETAIL.txt";
			String payFileName = ORG_ID + "_" + dateTime + "_PAY.txt";

			FileInputStream in = new FileInputStream(new File(sysConfig.getReportTmpPath() + "/" + mainFileName));
			boolean flag = uploadFile("121.15.128.18", 21, "WHTS2FL2036", "m7Mre187", "test.txt", in);
			System.out.println("MainFileName Upload File Result : " + flag);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param url FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param path FTP服务器保存目录
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 成功返回true，否则返回false
	 */
	private boolean uploadFile(String url, int port, String username, String password, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}

			// Enter local passive mode
			ftp.enterLocalPassiveMode();

			ftp.storeFile(filename, input);

			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
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
			fw = new FileWriter(sysConfig.getReportTmpPath() + "/" + ORG_ID + "_" + fileName + "_DETAIL.txt");

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
			fw = new FileWriter(sysConfig.getReportTmpPath() + "/" + ORG_ID + "_" + dateTime + "_PAY.txt");

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
			if (null == tmpFlowNo || !tmpFlowNo.equals(saleData.getBranchNo())) {
				tmpFlowNo = saleData.getBranchNo();
				tmpMShopDailySale = new MShopDailySale();
				tmpRowNum = 0;
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
			tmpMShopDailySale.setOperDate(saleData.getOperDate());
			// 商品销售金额
			tmpMShopDailySale.setSalePrice(tmpMShopDailySale.getSalePrice().add(saleData.getSalePrice()));
			// 交易明细行数
			tmpMShopDailySale.setRowNum(++tmpRowNum);

			fileList.add(tmpMShopDailySale);

			_writeMainFile(dateTime, fileList);
			_writePayFile(dateTime, fileList);
		}
	}

	/**
	 * 销售小票主体文件MAIN
	 */
	private void _writeMainFile(String dateTime, List<MShopDailySale> fileList) {
		FileWriter fw;
		try {
			SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
			fw = new FileWriter(sysConfig.getReportTmpPath() + "/" + ORG_ID + "_" + dateTime + "_MAIN.txt");

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

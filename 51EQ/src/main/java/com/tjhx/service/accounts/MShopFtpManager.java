package com.tjhx.service.accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tjhx.common.utils.DateUtils;
import com.tjhx.daobw.MShopDailySaleMyBatisDao;
import com.tjhx.entity.bw.MShopDailySale;

@Service
@Transactional(readOnly = true)
public class MShopFtpManager {
	@Resource
	private MShopDailySaleMyBatisDao mShopDailySaleMyBatisDao;

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
		param.put("bwBranchNo", "0801");// M+商场内部编号

		List<MShopDailySale> _saleDataList = mShopDailySaleMyBatisDao.getDailySaleList(param);

		String fileName = DateUtils.getCurFormatDate("yyyyMMddhhmmss");

		createMainFile(fileName, _saleDataList);
		createDetailFile(fileName, _saleDataList);

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
			// TODO
			fw = new FileWriter("d:/L2036_" + fileName + "_DETAIL.txt");

			for (MShopDailySale saleData : _saleDataList) {
				// 店铺ID
				fw.write("L2036" + ",");
				// POS机编号
				fw.write("8888" + ",");
				// 销售单号
				fw.write(saleData.getFlowNo() + ",");
				// 货品明细ID
				fw.write("00001" + ",");
				// 货品编号（商铺自己销售的货品条码，由M+商场提供。）
				fw.write("00101012" + ",");
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
	private void _writePayFile(String fileName, List<MShopDailySale> fileList) {
		FileWriter fw;
		try {
			// TODO
			fw = new FileWriter("d:/L2036_" + fileName + "_PAY.txt");

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
	private void createMainFile(String fileName, List<MShopDailySale> saleDataList) {
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
			tmpMShopDailySale.setOrgId("L2036");
			// POS机编号
			tmpMShopDailySale.setPosNo("8888");
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

			_writeMainFile(fileName, fileList);
			_writePayFile(fileName, fileList);
		}
	}

	/**
	 * 销售小票主体文件MAIN
	 */
	private void _writeMainFile(String fileName, List<MShopDailySale> fileList) {
		FileWriter fw;
		try {
			// TODO
			fw = new FileWriter("d:/L2036_" + fileName + "_MAIN.txt");

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

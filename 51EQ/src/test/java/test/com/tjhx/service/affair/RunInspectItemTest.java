/**
 * 
 */
package test.com.tjhx.service.affair;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.affair.RunInspectItemJpaDao;
import com.tjhx.entity.affair.RunInspectItem;

public class RunInspectItemTest extends SpringTransactionalTestCase {
	@Resource
	private RunInspectItemJpaDao runInspectItemJpaDao;

	@Test
	@Rollback(false)
	public void add101() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("01");
		// 编号
		r.setId("00000001");
		// 分数
		r.setScore(10);
		// 描述
		r.setDescTxt("面带微笑 眼神交流");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add102() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("01");
		// 编号
		r.setId("00000002");
		// 分数
		r.setScore(10);
		// 描述
		r.setDescTxt("迎声 您好欢迎光临EQ+/infancy");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add103() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("01");
		// 编号
		r.setId("00000003");
		// 分数
		r.setScore(20);
		// 描述
		r.setDescTxt("是否双手接物");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add104() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("01");
		// 编号
		r.setId("00000004");
		// 分数
		r.setScore(20);
		// 描述
		r.setDescTxt("跟顾客核对商品数量");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add105() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("01");
		// 编号
		r.setId("00000005");
		// 分数
		r.setScore(20);
		// 描述
		r.setDescTxt("唱收唱付：现金/刷卡<br>双手接现金，现金唱收唱付，双手递过找零<br>核对POS机刷卡金额，勾出账单金额请顾客核对签字<br>双手递还所有单据及银行卡");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add106() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("01");
		// 编号
		r.setId("00000006");
		// 分数
		r.setScore(10);
		// 描述
		r.setDescTxt("帮顾客打包核对商品，双手递出");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add107() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("01");
		// 编号
		r.setId("00000007");
		// 分数
		r.setScore(10);
		// 描述
		r.setDescTxt("送声 欢迎您下次光临 微笑目送");

		runInspectItemJpaDao.save(r);
	}

	// ================================================================================
	@Test
	@Rollback(false)
	public void add201() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000001");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("头发束起");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add202() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000002");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("工作服是否整洁干净");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add203() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000003");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("精神面貌是否良好");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add204() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000004");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("进店迎声 您好欢迎光临EQ+/infancy");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add205() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000005");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("离店送声 慢走欢迎下次光临");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add206() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000006");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("店内灯光是否柔和适度");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add207() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000007");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("店内音乐是否让人听觉舒服");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add208() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000008");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("店内温度让人体感觉舒适");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add209() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000009");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("店铺整体感觉是否良好，有无货品，纸箱大量占道");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2010() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000010");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("收银台是否整洁");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2011() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000011");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("货架清洁");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2012() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000012");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("堆码清洁");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2013() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000013");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("商品清洁");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2014() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000014");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("橱窗清洁");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2015() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000015");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("地面清洁");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2016() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000016");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("货架周围有无手写POP、过档物料及POP");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2017() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000017");
		// 分数
		r.setScore(2);
		// 描述
		r.setDescTxt("货品标签及宣传标签张贴和摆放是否正确");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2018() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000018");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("货品摆放位置是否合理");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2019() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000019");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("货品摆放是否整齐");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2020() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000020");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("员工是否清楚店内各种商品的库存量");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2021() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000021");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("店内商品的定价是否存在问题");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2022() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000022");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("店铺2个月内是否有最少一次的盘点");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2023() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000023");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("服务用语");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2024() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000024");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("服务主动性");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2025() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000025");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("服务进度");

		runInspectItemJpaDao.save(r);
	}

	@Test
	@Rollback(false)
	public void add2026() {
		RunInspectItem r = new RunInspectItem();

		// 类型编号
		r.setTypeNo("02");
		// 编号
		r.setId("00000026");
		// 分数
		r.setScore(5);
		// 描述
		r.setDescTxt("是否一站式服务");

		runInspectItemJpaDao.save(r);
	}

}

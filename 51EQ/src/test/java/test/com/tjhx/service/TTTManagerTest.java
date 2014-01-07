package test.com.tjhx.service;

import java.text.ParseException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.affair.PunchClockJpaDao;
import com.tjhx.daozk.CheckInOutMyBatisDao;
import com.tjhx.service.affair.PunchClockManager;

public class TTTManagerTest extends SpringTransactionalTestCase {
	@Resource
	private PunchClockJpaDao punchClockJpaDao;
	@Resource
	private CheckInOutMyBatisDao checkInOutMyBatisDao;
	@Resource
	private PunchClockManager punchClockManager;
	@Test
	@Rollback(false)
	public void aaa() throws ParseException{
		punchClockManager.recalPunchClock();
	}
	
//	@Test
//	@Rollback(false)
//	public void syn_PunchClock() {
//
//		List<List<CheckInOut>> _tmpList = new ArrayList<List<CheckInOut>>();
//
//		for (int i = 1; i < 32; i++) {
//			CheckInOut checkInOut = new CheckInOut();
//			checkInOut.setOptDateStart("2013-07-" + String.format("%02d", i) + " 07:00:00");
//			checkInOut.setOptDateEnd("2013-07-" + String.format("%02d", i + 1) + " 06:59:59");
//
//			List<CheckInOut> cList = checkInOutMyBatisDao.getCheckInOutList(checkInOut);
//
//			int _userId = -1;
//
//			List<CheckInOut> _tmpSubList = null;
//
//			for (CheckInOut c : cList) {
//
//				if (!c.getUserid().equals(_userId)) {
//					_userId = c.getUserid();
//
//					_tmpSubList = new ArrayList<CheckInOut>();
//					_tmpList.add(_tmpSubList);
//
//				}
//				_tmpSubList.add(c);
//			}
//		}
//
//		savePunchClock(_tmpList);
//
//	}
//
//	/**
//	 * 保存首尾打卡记录
//	 * 
//	 * @param checkInOutpList
//	 */
//	private void savePunchClock(List<List<CheckInOut>> checkInOutpList) {
//
//		for (List<CheckInOut> _cList : checkInOutpList) {
//			if (null == _cList || _cList.size() == 0) {
//				continue;
//			}
//
//			String _y = null;
//			String _m = null;
//			String _d = null;
//
//			if (_cList.size() > 0) {// 保存首条打卡记录
//				CheckInOut _checkInOut = _cList.get(0);
//
//				_y = DateUtils.transDateFormat(_checkInOut.getChecktime(), "yyyy");
//				_m = DateUtils.transDateFormat(_checkInOut.getChecktime(), "MM");
//				_d = DateUtils.transDateFormat(_checkInOut.getChecktime(), "dd");
//
//				PunchClock p = new PunchClock();
//
//				p.setZkid(_checkInOut.getUserid());
//				p.setClockTime(_checkInOut.getChecktime());
//				p.setClockTimeY(_y);
//				p.setClockTimeM(_m);
//				p.setClockTimeD(_d);
//				p.setSn(_checkInOut.getSn());
//
//				punchClockJpaDao.save(p);
//			}
//
//			if (_cList.size() > 1) {// 有两条以上的记录，保存最后的打卡记录
//				CheckInOut _checkInOut = _cList.get(_cList.size() - 1);
//
//				PunchClock p = new PunchClock();
//
//				p.setZkid(_checkInOut.getUserid());
//				p.setClockTime(_checkInOut.getChecktime());
//				p.setClockTimeY(_y);
//				p.setClockTimeM(_m);
//				p.setClockTimeD(_d);
//				p.setSn(_checkInOut.getSn());
//
//				punchClockJpaDao.save(p);
//			}
//
//		}
//
//	}

}

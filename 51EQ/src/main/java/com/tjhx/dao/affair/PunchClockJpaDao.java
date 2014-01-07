package com.tjhx.dao.affair;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.affair.PunchClock;

public interface PunchClockJpaDao extends CrudRepository<PunchClock, Integer> {

}

/**
 * 
 */
package com.tjhx.entity.affair;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 门店巡查报告(运营)
 */
@Entity
@Table(name = "T_RUN_INSPECT")
public class RunInspect extends IdEntity{

	private static final long serialVersionUID = -8034259767801686310L;

}

package com.tjhx.entity.member;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.tjhx.entity.IdEntity;

/**
 * 资源访问权限
 */
@Entity
@Table(name = "T_ROLE_FUN_PERM")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends IdEntity {

	private static final long serialVersionUID = -4090640196673810332L;

	/** 功能资源 */
	private Function function;
	/** 角色 */
	private Role role;

	/**
	 * 取得功能资源
	 * 
	 * @return 功能资源
	 */
	@ManyToOne
	// @JoinColumn表示外键的列
	@JoinColumn(name = "FUN_UUID")
	public Function getFunction() {
		return function;
	}

	/**
	 * 设置功能资源
	 * 
	 * @param function 功能资源
	 */
	public void setFunction(Function function) {
		this.function = function;
	}

	/**
	 * 取得角色
	 * 
	 * @return 角色
	 */
	@ManyToOne
	// @JoinColumn表示外键的列
	@JoinColumn(name = "ROLE_UUID")
	public Role getRole() {
		return role;
	}

	/**
	 * 设置角色
	 * 
	 * @param role 角色
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Permission)) {
			return false;
		}
		Permission rhs = (Permission) obj;
		return new EqualsBuilder().append(this.function.getUuid(), rhs.function.getUuid())
				.append(this.role.getUuid(), rhs.role.getUuid()).isEquals();
	}

}

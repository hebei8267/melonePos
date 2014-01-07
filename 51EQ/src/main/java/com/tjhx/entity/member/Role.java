package com.tjhx.entity.member;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tjhx.entity.IdEntity;

/**
 * 角色
 */
@Entity
@Table(name = "T_ROLE")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity {

	private static final long serialVersionUID = 7216626205866676484L;
	/** 角色名称 */
	private String name;
	/** 角色详细描述 */
	private String descTxt;
	/** 拥有权限信息集合 */
	private Set<Permission> permissionSet = Sets.newHashSet();

	// ##########################################################
	/** 拥有资源菜单ID信息集合 */
	private List<String> permIdList = Lists.newArrayList();
	/** 非拥有资源菜单信息集合 */
	private List<Function> noPermList;

	/**
	 * 取得角色名称
	 * 
	 * @return 角色名称
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置角色名称
	 * 
	 * @param name 角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得角色详细描述
	 * 
	 * @return 角色详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置角色详细描述
	 * 
	 * @param descTxt 角色详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得权限信息集合
	 * 
	 * @return 权限信息集合
	 */
	// cascade表示级联操作
	// CascadeType.PERSIST级联保存
	// CascadeType.MERGE级联更新
	// CascadeType.REMOVE级联删除
	// CascadeType.REFRESH级联刷新
	// FetchType.LAZY表示懒加载。对于xxxtoMany时即获得多的一方fetch的默认值是FetchType.LAZY懒加载。对于xxxtoOne时即获得一的一方fetch的默认值是FetchType.EAGER立即加载
	// mappedBy表示关系统被维护端，它的值是关系维护端维护关系的属性
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Permission> getPermissionSet() {
		return permissionSet;
	}

	/**
	 * 设置权限信息集合
	 * 
	 * @param permissionSet 权限信息集合
	 */
	public void setPermissionSet(Set<Permission> permissionSet) {
		this.permissionSet = permissionSet;
	}

	/**
	 * 添加权限
	 * 
	 * @param permission 权限信息
	 */
	public void addPermission(Permission permission) {
		if (!permissionSet.contains(permission)) {
			permissionSet.add(permission);
		}

	}

	/**
	 * 取得拥有权限集合名称(##,##,##)
	 * 
	 * @return 拥有权限集合名称
	 */
	@Transient
	public String getPermissionNames() {
		List<String> permissionNameList = Lists.newArrayList();
		for (Permission permission : getPermissionSet()) {
			permissionNameList.add(permission.getFunction().getDisplayName());
		}
		return StringUtils.join(permissionNameList, ",");
	}

	@SuppressWarnings("unchecked")
	public void initPermIdList(List<Function> allFunList) {
		List<Function> _funList = Lists.newArrayList();
		for (Permission permission : getPermissionSet()) {
			permIdList.add(permission.getFunction().getUuid().toString());
			_funList.add(permission.getFunction());
		}

		noPermList = (List<Function>) CollectionUtils.subtract(allFunList, _funList);

	}

	/**
	 * 取得拥有资源菜单ID信息集合
	 * 
	 * @return permIdList 拥有资源菜单ID信息集合
	 */
	@Transient
	public List<String> getPermIdList() {
		return permIdList;
	}

	/**
	 * 设置拥有资源菜单ID信息集合
	 * 
	 * @param permIdList 拥有资源菜单ID信息集合
	 */
	public void setPermIdList(List<String> permIdList) {
		this.permIdList = permIdList;
	}

	/**
	 * 取得非拥有资源菜单信息集合
	 * 
	 * @return noPermList 非拥有资源菜单信息集合
	 */
	@Transient
	public List<Function> getNoPermList() {
		return noPermList;
	}

	/**
	 * 设置非拥有资源菜单信息集合
	 * 
	 * @param noPermList 非拥有资源菜单信息集合
	 */
	public void setNoPermList(List<Function> noPermList) {
		this.noPermList = noPermList;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String[] arrayA = new String[] { "1", "2", "3", "3", "4", "5" };
		String[] arrayB = new String[] { "3", "4", "5", "6", "7" };
		List<String> a = Arrays.asList(arrayA);
		List<String> b = Arrays.asList(arrayB);
		// 并集
		Collection<String> union = CollectionUtils.union(a, b);
		// 交集
		Collection<String> intersection = CollectionUtils.intersection(a, b);
		// 交集的补集
		Collection<String> disjunction = CollectionUtils.disjunction(a, b);
		// 集合相减
		Collection<String> subtract = CollectionUtils.subtract(a, b);
		Collections.sort((List<String>) union);
		Collections.sort((List<String>) intersection);
		Collections.sort((List<String>) disjunction);
		Collections.sort((List<String>) subtract);
		System.out.println("A: " + ArrayUtils.toString(a.toArray()));
		System.out.println("B: " + ArrayUtils.toString(b.toArray()));
		System.out.println("--------------------------------------------");
		System.out.println("Union(A, B): " + ArrayUtils.toString(union.toArray()));
		System.out.println("Intersection(A, B): " + ArrayUtils.toString(intersection.toArray()));
		System.out.println("Disjunction(A, B): " + ArrayUtils.toString(disjunction.toArray()));
		System.out.println("Subtract(A, B): " + ArrayUtils.toString(subtract.toArray()));
	}
}

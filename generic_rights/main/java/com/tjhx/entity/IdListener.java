package com.tjhx.entity;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tjhx.entity.member.User;
import com.tjhx.globals.Constants;

/**
 * 在自动为entity添加审计信息的Hibernate EventListener.
 * 
 * 在hibernate执行saveOrUpdate()时,自动为IdEntity的子类添加审计信息.
 * 
 */
public class IdListener {

	private static Logger logger = LoggerFactory.getLogger(IdListener.class);

	@PrePersist
	@PreUpdate
	public void onSaveOrUpdate(IdEntity entity) throws HibernateException {

		// 如果对象是IdEntity子类,添加审计信息.

		String loginName = null;
		if (null == loginName || StringUtils.isBlank(loginName)) {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			if (null != servletRequestAttributes) {
				HttpServletRequest request = servletRequestAttributes.getRequest();
				User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_INFO);
				loginName = user.getUuid().toString();
			} else {
				loginName = "sys";
			}

		}
		if (entity.getUuid() == null) {
			// 创建新对象
			entity.setCreateDate(new Date());
			entity.setCreateUserId(loginName);

			entity.setUpdateDate(new Date());
			entity.setUpdateUserId(loginName);
		} else {
			// 修改旧对象
			entity.setUpdateDate(new Date());
			entity.setUpdateUserId(loginName);

			logger.info("{} 对象(ID:{}) 被 {} 在 {} 修改", new Object[] { entity.getClass().getSimpleName(),
					entity.getUuid(), loginName, new Date() });
		}

	}
}

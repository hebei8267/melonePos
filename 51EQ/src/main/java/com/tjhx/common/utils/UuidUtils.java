package com.tjhx.common.utils;

import java.util.UUID;

public class UuidUtils {

	/**
	 * 返回一个36位的UUID
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();

	}
}

/**
 * 
 */
package com.tjhx.vo;

/**
 * @author he_bei
 * 
 */
public class Select2Vo {
	private String id;
	private String text;

	public Select2Vo() {

	}

	public Select2Vo(String id, String text) {
		this.id = id;
		this.text = text;
	}

	/**
	 * 获取id
	 * 
	 * @return id id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id
	 * 
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取text
	 * 
	 * @return text text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置text
	 * 
	 * @param text text
	 */
	public void setText(String text) {
		this.text = text;
	}
}

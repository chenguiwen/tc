package com.tc.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 * */
	private Integer id;
	
	/**
	 * 搜索值
	 * */
	private String searchValue;
	
	/**
	 * 创建人
	 * */
	private String creator;
	
	/**
	 * 创建时间
	 * */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/**
	 * 修改人
	 * */
	private String modifier;
	
	/**
	 * 修改时间
	 * */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	/**
	 * 删除标记
	 * */
	private Integer dr;
	
	/**
	 * 备注
	 * */
	private String note;
	
	/**
	 * 请求参数
	 * */
	private Map<String,Object> params;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Map<String,Object> getParams() {
		return params;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
}

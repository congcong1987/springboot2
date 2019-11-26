package com.gzzl.domain.manifest.vo;

import java.io.Serializable;

public class ManifestResult <T> implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5659180528295835868L;
	/**
	 * 成功时标志为1，其它情况都为失败
	 */
	public static int SUCCESS = 1;
	/**
	 * 默认失败标志为0
	 */
	public static int ERROR = 0;
	
	
	/**
	 * 状态 1-成功，0--失败
	 */
	private Integer status;
	
	/**
	 * 消息描述
	 */
	private String msg;
	
	/**
	 * 数据
	 */
	private T data;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ManifestResult(Integer status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public ManifestResult() {
		/*默认成功*/
		this.status = SUCCESS;
	}
	  

}

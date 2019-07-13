/**
 * 
 */
package com.tc.common.model.tips;

/**
 * @author chengw
 *
 * @Date 2019年7月13日
 * @Time 下午2:45:48
 */
public class BaseTip {

	protected int code;
	protected String massage;
	
	public BaseTip() {
		
	}
	
	public BaseTip(int code,String massage) {
		this.setCode(code);
		this.massage = massage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}


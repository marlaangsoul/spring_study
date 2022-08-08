package com.springstudy.book.vo;

public class MemberVo {

	private String memId;
	private String memPW;
	private String memSex;
	private int memPhone;

	private String memName;
	private String memRegDate;

	public MemberVo() {
	}

	public MemberVo(String memId, String memPW, int memPhone, String memSex, String memName,
			String memRegDate) {
		super();
		this.memId = memId;
		this.memPW = memPW;
		this.memSex = memSex;
		this.memPhone = memPhone;
		
		this.memName = memName;
		this.memRegDate = memRegDate;
	}


	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPW() {
		return memPW;
	}

	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}

	public String getMemSex() {
		return memSex;
	}

	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}

	public int getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(int memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemRegDate() {
		return memRegDate;
	}

	public void setMemRegDate(String memRegDate) {
		this.memRegDate = memRegDate;
	}


	@Override
	public String toString() {
		return "MemberVo [memId=" + memId + ", memPW=" + memPW + ", memSex=" + memSex + ", memPhone=" + memPhone
				+ ", memName=" + memName + ", memRegDate=" + memRegDate + "]";
	}


}

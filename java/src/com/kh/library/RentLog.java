package com.kh.library;

public class RentLog {
	private int logNo;
	private int hm_rentKey;
	private String hm_name;
	private int bk_rentCode;
	private String BK_TITLE;
	private String rentInOut;
	private String enrollDate;
	private int fee;
	
	
	
	public int getLogNo() {
		return logNo;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
	public int getHm_rentKey() {
		return hm_rentKey;
	}
	public void setHm_rentKey(int hm_rentKey) {
		this.hm_rentKey = hm_rentKey;
	}
	public String getHm_name() {
		return hm_name;
	}
	public void setHm_name(String hm_name) {
		this.hm_name = hm_name;
	}
	public String getBK_TITLE() {
		return BK_TITLE;
	}
	public void setBK_TITLE(String bK_TITLE) {
		BK_TITLE = bK_TITLE;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getBk_rentCode() {
		return bk_rentCode;
	}
	public void setBk_rentCode(int bk_rentCode) {
		this.bk_rentCode = bk_rentCode;
	}
	public String getRentInOut() {
		return rentInOut;
	}
	public void setRentInOut(String rentInOut) {
		this.rentInOut = rentInOut;
	}
	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	@Override
	public String toString() {
		return logNo + "\t" + hm_rentKey +"\t" + this.hm_name + "\t" + bk_rentCode
				+"\t" +this.BK_TITLE + "\t" +rentInOut + "\t" +enrollDate +"\t" + fee+"원" ;
	}
}

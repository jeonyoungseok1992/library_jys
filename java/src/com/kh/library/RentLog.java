package com.kh.library;

public class RentLog {
	private int logNo;
	private int hm_rentKey;
	private int bk_rentCode;
	private String rentInOut;
	private String enrollDate;
	
	
	
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
		return logNo + "\t" + hm_rentKey + "\t" + bk_rentCode
				+ "\t" +rentInOut + "\t" +enrollDate ;
	}
}

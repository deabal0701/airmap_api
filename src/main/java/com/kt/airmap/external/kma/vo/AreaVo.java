package com.kt.airmap.external.kma.vo;

import java.util.Date;

public class AreaVo {
	
	/** 지정코드 */
	private String bstorCd;

	/** 1단계 시도*/
	private String cityNm;

	/** 2단계(군/구) */
	private String guNm;
	
	/** 3단계(읍면동) */
	private String dongNm;
	
	/** 격자 X */
	private Integer xcrdVal;
	
	/** 격자 Y */
	private Integer ycrdVal;
	
	/** 사용 여부 */
	private String useYn;
	
	/** 생성 일시 */
	private Date cretDt;

	/** 생성 사용자 ID */
	private String cretUserId;
	
	/** 수정 일시 */
	private Date amdDt;

	/** 수정 사용자 ID */
	private String amdUserId;

	public String getBstorCd() {
		return bstorCd;
	}

	public void setBstorCd(String bstorCd) {
		this.bstorCd = bstorCd;
	}

	public String getCityNm() {
		return cityNm;
	}

	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}

	public String getGuNm() {
		return guNm;
	}

	public void setGuNm(String guNm) {
		this.guNm = guNm;
	}

	public String getDongNm() {
		return dongNm;
	}

	public void setDongNm(String dongNm) {
		this.dongNm = dongNm;
	}

	public Integer getXcrdVal() {
		return xcrdVal;
	}

	public void setXcrdVal(Integer xcrdVal) {
		this.xcrdVal = xcrdVal;
	}

	public Integer getYcrdVal() {
		return ycrdVal;
	}

	public void setYcrdVal(Integer ycrdVal) {
		this.ycrdVal = ycrdVal;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Date getCretDt() {
		return cretDt;
	}

	public void setCretDt(Date cretDt) {
		this.cretDt = cretDt;
	}

	public String getCretUserId() {
		return cretUserId;
	}

	public void setCretUserId(String cretUserId) {
		this.cretUserId = cretUserId;
	}

	public Date getAmdDt() {
		return amdDt;
	}

	public void setAmdDt(Date amdDt) {
		this.amdDt = amdDt;
	}

	public String getAmdUserId() {
		return amdUserId;
	}

	public void setAmdUserId(String amdUserId) {
		this.amdUserId = amdUserId;
	}
	
	
}

package com.kt.airmap.external.kma.vo.forcast;

import java.util.Date;

public class ForecastSpaceDataVo {
	
	/**발표 일자*/
	private String ptDate;
	
	/**발표 시간*/
	private String ptTime;
	
	/**예상 일자*/
	private String forecDate;
	
	/**예상 시간*/
	private String forecTime;
	
	/**X좌표 값*/
	private Integer xcrdVal;
	
	/**Y좌표 값*/
	private Integer ycrdVal;
	
	/**강수 확률*/
	private Double prctPrbb;
	
	/**강수 형태 코드*/
	private String prctShapCd;
	
	/**강수 량 6 시간*/
	private Double prctQnt6Time;
	
	/**습도*/
	private Double humdt;
	
	/**신적설 6 시간*/
	private Double fsnowc6Time;
	
	/**하늘 상태 코드*/
	private String skySttusCd;
	
	/**온도 3 시간*/
	private Double tempr3Time;
	
	/**아침 최저 기온*/
	private Double lowstTempr;
	
	/**낮 최고 기온*/
	private Double higstTempr;
	
	/**동서풍속성분*/
	private Double ewWsCmpnt;
	
	/**남북풍속성분*/
	private Double snWsCmpnt;
	
	/**파고*/
	private Double wvhgt;
	
	/**바람 방향*/
	private Integer wd;
	
	/**바람 속도*/
	private Integer ws;
	
	/**생성 일시*/
	private Date cretDt;
	
	/**생성 사용자 ID*/
	private String cretUserId;

	public String getPtDate() {
		return ptDate;
	}

	public void setPtDate(String ptDate) {
		this.ptDate = ptDate;
	}

	public String getPtTime() {
		return ptTime;
	}

	public void setPtTime(String ptTime) {
		this.ptTime = ptTime;
	}

	public String getForecDate() {
		return forecDate;
	}

	public void setForecDate(String forecDate) {
		this.forecDate = forecDate;
	}

	public String getForecTime() {
		return forecTime;
	}

	public void setForecTime(String forecTime) {
		this.forecTime = forecTime;
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

	public Double getPrctPrbb() {
		return prctPrbb;
	}

	public void setPrctPrbb(Double prctPrbb) {
		this.prctPrbb = prctPrbb;
	}

	public String getPrctShapCd() {
		return prctShapCd;
	}

	public void setPrctShapCd(String prctShapCd) {
		this.prctShapCd = prctShapCd;
	}

	public Double getPrctQnt6Time() {
		return prctQnt6Time;
	}

	public void setPrctQnt6Time(Double prctQnt6Time) {
		this.prctQnt6Time = prctQnt6Time;
	}

	public Double getHumdt() {
		return humdt;
	}

	public void setHumdt(Double humdt) {
		this.humdt = humdt;
	}

	public Double getFsnowc6Time() {
		return fsnowc6Time;
	}

	public void setFsnowc6Time(Double fsnowc6Time) {
		this.fsnowc6Time = fsnowc6Time;
	}

	public String getSkySttusCd() {
		return skySttusCd;
	}

	public void setSkySttusCd(String skySttusCd) {
		this.skySttusCd = skySttusCd;
	}

	public Double getTempr3Time() {
		return tempr3Time;
	}

	public void setTempr3Time(Double tempr3Time) {
		this.tempr3Time = tempr3Time;
	}

	public Double getLowstTempr() {
		return lowstTempr;
	}

	public void setLowstTempr(Double lowstTempr) {
		this.lowstTempr = lowstTempr;
	}

	public Double getHigstTempr() {
		return higstTempr;
	}

	public void setHigstTempr(Double higstTempr) {
		this.higstTempr = higstTempr;
	}

	public Double getEwWsCmpnt() {
		return ewWsCmpnt;
	}

	public void setEwWsCmpnt(Double ewWsCmpnt) {
		this.ewWsCmpnt = ewWsCmpnt;
	}

	public Double getSnWsCmpnt() {
		return snWsCmpnt;
	}

	public void setSnWsCmpnt(Double snWsCmpnt) {
		this.snWsCmpnt = snWsCmpnt;
	}

	public Double getWvhgt() {
		return wvhgt;
	}

	public void setWvhgt(Double wvhgt) {
		this.wvhgt = wvhgt;
	}

	public Integer getWd() {
		return wd;
	}

	public void setWd(Integer wd) {
		this.wd = wd;
	}

	public Integer getWs() {
		return ws;
	}

	public void setWs(Integer ws) {
		this.ws = ws;
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

	
	
	
}

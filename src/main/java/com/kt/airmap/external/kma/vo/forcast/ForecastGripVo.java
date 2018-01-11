package com.kt.airmap.external.kma.vo.forcast;

import java.util.Date;
/**
 * 초단기 실황 VO
 * @author deabal
 *
 */
public class ForecastGripVo {

	
	/**발표 일자*/
	private String ptDate;
	
	/**발표 시간*/
	private String ptTime;
	
	private Integer xcrdVal;
	private Integer ycrdVal;
	private Double temprVal;
	private Double prcp1TimeQnt;
	private String skySttusCd;
	private Double ewWindCmpntVal;
	private Double snWindCmpntVal;
	private Double humdtRate;
	private String prcpShapCd;
	private String thnbltCd;
	private Double wdVal;
	private Double wsVal;
	private Date cretDt;
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
	public Double getTemprVal() {
		return temprVal;
	}
	public void setTemprVal(Double temprVal) {
		this.temprVal = temprVal;
	}
	public Double getPrcp1TimeQnt() {
		return prcp1TimeQnt;
	}
	public void setPrcp1TimeQnt(Double prcp1TimeQnt) {
		this.prcp1TimeQnt = prcp1TimeQnt;
	}
	public String getSkySttusCd() {
		return skySttusCd;
	}
	public void setSkySttusCd(String skySttusCd) {
		this.skySttusCd = skySttusCd;
	}
	public Double getEwWindCmpntVal() {
		return ewWindCmpntVal;
	}
	public void setEwWindCmpntVal(Double ewWindCmpntVal) {
		this.ewWindCmpntVal = ewWindCmpntVal;
	}
	public Double getSnWindCmpntVal() {
		return snWindCmpntVal;
	}
	public void setSnWindCmpntVal(Double snWindCmpntVal) {
		this.snWindCmpntVal = snWindCmpntVal;
	}
	public Double getHumdtRate() {
		return humdtRate;
	}
	public void setHumdtRate(Double humdtRate) {
		this.humdtRate = humdtRate;
	}
	public String getPrcpShapCd() {
		return prcpShapCd;
	}
	public void setPrcpShapCd(String prcpShapCd) {
		this.prcpShapCd = prcpShapCd;
	}
	public String getThnbltCd() {
		return thnbltCd;
	}
	public void setThnbltCd(String thnbltCd) {
		this.thnbltCd = thnbltCd;
	}
	public Double getWdVal() {
		return wdVal;
	}
	public void setWdVal(Double wdVal) {
		this.wdVal = wdVal;
	}
	public Double getWsVal() {
		return wsVal;
	}
	public void setWsVal(Double wsVal) {
		this.wsVal = wsVal;
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

package com.kt.airmap.external.kma.vo;

public class Area {

	private String code;
	private String name;
	private String parentCode;
	private String parentName;
	private String pParentCode;
	private String pParentName;
	private String gridX;
	private String gridY;
	    
	public Area(String code, String name, String parentCode, String parentName, String gridX, String gridY) {
		super();
		this.code = code;
		this.name = name;
		this.parentCode = parentCode;
		this.parentName = parentName;
		this.gridX = gridX;
		this.gridY = gridY;
	}

	public Area(String code, String name, String parentCode, String parentName) {
		super();
		this.code = code;
		this.name = name;
		this.parentCode = parentCode;
		this.parentName = parentName;
	}

	public Area(String code, String name, String parentCode, String parentName, String pParentCode,
			String pParentName, String gridX, String gridY) {
		super();
		this.code = code;
		this.name = name;
		this.parentCode = parentCode;
		this.parentName = parentName;
		this.pParentCode = pParentCode;
		this.pParentName = pParentName;
		this.gridX = gridX;
		this.gridY = gridY;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getGridX() {
		return gridX;
	}

	public void setGridX(String gridX) {
		this.gridX = gridX;
	}

	public String getGridY() {
		return gridY;
	}

	public void setGridY(String gridY) {
		this.gridY = gridY;
	}

	public String getpParentCode() {
		return pParentCode;
	}

	public void setpParentCode(String pParentCode) {
		this.pParentCode = pParentCode;
	}

	public String getpParentName() {
		return pParentName;
	}

	public void setpParentName(String pParentName) {
		this.pParentName = pParentName;
	}

	@Override
	public String toString() {
		return "TownDTO [code=" + code + ", name=" + name + ", parentCode=" + parentCode + ", parentName=" + parentName
				+ ", pParentCode=" + pParentCode + ", pParentName=" + pParentName + ", gridX=" + gridX + ", gridY="
				+ gridY + "]";
	}

}

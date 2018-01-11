package com.kt.airmap.external.kma.vo.forcast;

import java.util.List;

//@JsonInclude(Include.NON_NULL)
public class ForecastResponse {
	
	private HeaderVo header;
	private BodyVo body;
	
	public HeaderVo getHeader() {
		return header;
	}
	public void setHeader(HeaderVo header) {
		this.header = header;
	}
	public BodyVo getBody() {
		return body;
	}
	public void setBody(BodyVo body) {
		this.body = body;
	}
	
	public static class HeaderVo{

		private String resultCode;
		private String resultMsg;
		
		public String getResultCode() {
			return resultCode;
		}
		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}
		public String getResultMsg() {
			return resultMsg;
		}
		public void setResultMsg(String resultMsg) {
			this.resultMsg = resultMsg;
		}
	}
	
	public static class BodyVo {
	
		private ItemsVo items;
		private Integer numOfRows;
		private Integer pageNo;
		private Integer totalCount;
		
		public ItemsVo getItems() {
			return items;
		}

		public void setItems(ItemsVo items) {
			this.items = items;
		}
		public Integer getNumOfRows() {
			return numOfRows;
		}

		public void setNumOfRows(Integer numOfRows) {
			this.numOfRows = numOfRows;
		}

		public Integer getPageNo() {
			return pageNo;
		}

		public void setPageNo(Integer pageNo) {
			this.pageNo = pageNo;
		}

		public Integer getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(Integer totalCount) {
			this.totalCount = totalCount;
		}

		public static class ItemsVo {
			private List<ItemVo> item;

			public List<ItemVo> getItem() {
				return item;
			}

			public void setItem(List<ItemVo> item) {
				this.item = item;
			}
	
			public static class ItemVo {
		
				private String baseDate;
				private String baseTime;
				private String category;
				private Object obsrValue;
				private Integer nx;
				private Integer ny;
				
				public String getBaseDate() {
					return baseDate;
				}
				public void setBaseDate(String baseDate) {
					this.baseDate = baseDate;
				}
				public String getBaseTime() {
					return baseTime;
				}
				public void setBaseTime(String baseTime) {
					this.baseTime = baseTime;
				}
				public String getCategory() {
					return category;
				}
				public void setCategory(String category) {
					this.category = category;
				}
				public Object getObsrValue() {
					return obsrValue;
				}
				public void setObsrValue(Object obsrValue) {
					this.obsrValue = obsrValue;
				}
				public Integer getNx() {
					return nx;
				}
				public void setNx(Integer nx) {
					this.nx = nx;
				}
				public Integer getNy() {
					return ny;
				}
				public void setNy(Integer ny) {
					this.ny = ny;
				}
			}
		}
	}
}

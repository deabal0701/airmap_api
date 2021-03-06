package com.kt.airmap.external.kma.vo.lifeindex;

public class LifeIndexResponse {
	
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

		private String successYN;
		private String returnCode;
		private String errMsg;
		
		public String getSuccessYN() {
			return successYN;
		}
		public void setSuccessYN(String successYN) {
			this.successYN = successYN;
		}
		public String getReturnCode() {
			return returnCode;
		}
		public void setReturnCode(String returnCode) {
			this.returnCode = returnCode;
		}
		public String getErrMsg() {
			return errMsg;
		}
		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
	}

	public static class BodyVo {

		private IndexModel indexModel;

		public IndexModel getIndexModel() {
			return indexModel;
		}

		public void setIndexModel(IndexModel indexModel) {
			this.indexModel = indexModel;
		}
		
		public static class IndexModel {
			private String code;
			private String areaNo;
			private String date;
			
			/** 식중독, 자외선 지수 06, 18시*/
			private String today;
			
			private String h3;
			private String h6;
			private String h9;
			private String h12;
			private String h15;
			private String h18;
			private String h21;
			private String h24;
			private String h27;
			private String h30;
			private String h33;
			private String h36;
			private String h39;
			private String h42;
			private String h45;
			private String h48;
			private String h51;
			private String h54;
			private String h57;
			private String h60;
			private String h63;
			private String h66;
			
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
			public String getAreaNo() {
				return areaNo;
			}
			public void setAreaNo(String areaNo) {
				this.areaNo = areaNo;
			}
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
			}
					
			public String getToday() {
				return today;
			}
			public void setToday(String today) {
				this.today = today;
			}
			public String getH3() {
				return h3;
			}
			public void setH3(String h3) {
				this.h3 = h3;
			}
			public String getH6() {
				return h6;
			}
			public void setH6(String h6) {
				this.h6 = h6;
			}
			public String getH9() {
				return h9;
			}
			public void setH9(String h9) {
				this.h9 = h9;
			}
			public String getH12() {
				return h12;
			}
			public void setH12(String h12) {
				this.h12 = h12;
			}
			public String getH15() {
				return h15;
			}
			public void setH15(String h15) {
				this.h15 = h15;
			}
			public String getH18() {
				return h18;
			}
			public void setH18(String h18) {
				this.h18 = h18;
			}
			public String getH21() {
				return h21;
			}
			public void setH21(String h21) {
				this.h21 = h21;
			}
			public String getH24() {
				return h24;
			}
			public void setH24(String h24) {
				this.h24 = h24;
			}
			public String getH27() {
				return h27;
			}
			public void setH27(String h27) {
				this.h27 = h27;
			}
			public String getH30() {
				return h30;
			}
			public void setH30(String h30) {
				this.h30 = h30;
			}
			public String getH33() {
				return h33;
			}
			public void setH33(String h33) {
				this.h33 = h33;
			}
			public String getH36() {
				return h36;
			}
			public void setH36(String h36) {
				this.h36 = h36;
			}
			public String getH39() {
				return h39;
			}
			public void setH39(String h39) {
				this.h39 = h39;
			}
			public String getH42() {
				return h42;
			}
			public void setH42(String h42) {
				this.h42 = h42;
			}
			public String getH45() {
				return h45;
			}
			public void setH45(String h45) {
				this.h45 = h45;
			}
			public String getH48() {
				return h48;
			}
			public void setH48(String h48) {
				this.h48 = h48;
			}
			public String getH51() {
				return h51;
			}
			public void setH51(String h51) {
				this.h51 = h51;
			}
			public String getH54() {
				return h54;
			}
			public void setH54(String h54) {
				this.h54 = h54;
			}
			public String getH57() {
				return h57;
			}
			public void setH57(String h57) {
				this.h57 = h57;
			}
			public String getH60() {
				return h60;
			}
			public void setH60(String h60) {
				this.h60 = h60;
			}
			public String getH63() {
				return h63;
			}
			public void setH63(String h63) {
				this.h63 = h63;
			}
			public String getH66() {
				return h66;
			}
			public void setH66(String h66) {
				this.h66 = h66;
			}
		}
	}
	
}

package com.kt.airmap.etc;

import com.kt.airmap.base.message.ResponseCode;

public class ResponseCodeTest {
	
	public static void main(String[] args) {
	  
	   System.out.println(ResponseCode.EXTERNAL_TIMEOUT.getValue());
	   System.out.println(ResponseCode.EXTERNAL_TIMEOUT.getReasonPhrase());
		    
	
	   ResponseCode code = ResponseCode.parseType("0000");
	   System.out.println("code:" + code.getValue());
	   System.out.println("reasonPhrase:" + code.getReasonPhrase());
	   
	   ResponseCode code2 = ResponseCode.parseType("1000");
	   System.out.println("code:" + code2.getValue());
	   System.out.println("reasonPhrase:" + code2.getReasonPhrase());
		
	}
}

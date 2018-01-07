package com.kt.airmap.base.common;

public class NumberUtil {

	public static boolean  isDouble( String str ){
		  try{
			  Double.parseDouble( str );
		    return true;
		  }
		  catch( Exception e ){
		    return false;
	   }
	}
}

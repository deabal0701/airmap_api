package com.kt.airmap.etc;

import com.kt.airmap.Const;

public class TestMainClass4 {
	public static void main(String[] args) {
		
		String inStr = "3";
		String str = String.format(Const.KMA_LOCATION_CODE_LEAF_URI,inStr);
		System.out.println("======> " + str);
		
	}
}

package com.kt.airmap.etc;


public class TestMainClass2 {
	
	public static void main(String[] args) {
//		String s= "-";
//		Double d = Double.valueOf(isDouble(s));
//		
//		System.out.println("d ==> " + d);
		
		 String input = "1.7e-6";
		double myDouble = Double.parseDouble(input);
		System.out.println(myDouble);
		double invalidDouble = Double.parseDouble("INVALID!");
		System.out.println(invalidDouble);
	}
	
	public static String isDouble(String s){
		
		return s;
		
	}
}

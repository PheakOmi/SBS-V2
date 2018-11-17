package com.ModelClasses;

public class IdentifyTypeUser {

	private static String type = "default";
	
	public static void setType(String types ){
		type = types;
	}
	public static String getType(){
		return type;
	}

}

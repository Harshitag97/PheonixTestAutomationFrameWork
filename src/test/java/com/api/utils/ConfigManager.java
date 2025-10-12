package com.api.utils;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class ConfigManager {
	
	private ConfigManager() {}
	
	private static Properties prop = new Properties(); // Create the object of properties class
	private static String path = "config/config.properties";
	private static String env ;
	
	
	static {
		
		
		env = System.getProperty("env","qa");
		env = env.toLowerCase().trim();
		
		switch(env) {
		
		case "qa" ->path = "config/config.qa.properties";
		
		case "dev" -> path ="config/config.dev.properties";
		case "uat" -> path ="config/config.uat.properties";
		
		
		}
		
		//File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		//FileReader filereader;
		InputStream input=Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		if(input == null) {
			throw new RuntimeException("Cannot find file config.properties");
		}
		try {
			
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static  String getProperty(String Key) throws IOException {
		
		return prop.getProperty(Key);
	
	}

}

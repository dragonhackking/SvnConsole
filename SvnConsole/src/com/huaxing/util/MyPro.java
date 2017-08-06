package com.huaxing.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.huaxing.util.LogUtil;




public class MyPro extends Properties {  
	private static MyPro instance;
	
	public static MyPro getInstance(){
		if(instance!=null){
			return instance;
		}else{
			instance=new MyPro();
		}
		return instance;
	}
	
	private MyPro(){
		InputStream iss=null;
		try {
			iss = this.getClass().getResourceAsStream("/info.properties");
			this.load(iss);
		} catch (IOException e) {
			LogUtil.log.error(e.toString());
		}finally{
			if(iss!=null){
			    try {
					iss.close();
				} catch (IOException e) {
					LogUtil.log.error(e.toString());
				}
			}
		}
		
		
		
		
	}
}

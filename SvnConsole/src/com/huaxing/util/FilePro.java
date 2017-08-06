package com.huaxing.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.huaxing.util.LogUtil;




public class FilePro extends Properties {  
	private static FilePro instance;
	private static File file;
	
	public static FilePro getInstance(String path){
		if(instance!=null){
			return instance;
		}else{
			instance=new FilePro(path);
		}
		return instance;
	}
	
	private FilePro(String path){
		File file = new File(path);
		if(file.exists()){
			this.file = file;
		}
	}
}
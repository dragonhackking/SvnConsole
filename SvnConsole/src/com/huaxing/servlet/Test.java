package com.huaxing.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class Test {
    public static void main(String[] args) throws Exception {
//    	File f = new File("\\\\10.1.98.190\\Repositories98190");
//    	System.out.println(f.listFiles().length);
    	String s = "#";
    	String ss[] = s.split("=");
    	
    	List<String> list = new ArrayList<String>();
    	list.add("one");
    	list.add("two");
    	
    	List<String> list2 = new ArrayList<String>();
    	list2.add("three");
    	list2.add("four");
    	
    	List<List> list3 = new ArrayList<List>();
    	list3.add(list);
    	list3.add(list2);
    	
    	for(List temp:list3){
    		System.out.println(temp);
    	}
//    	String user = "svn";
//    	String pass ="Password123";
// 
//    	String sharedFolder="shared";
//    	String path="smb://ip_address/"+sharedFolder+"/test.txt";
//    	NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("",user, pass);
//    	SmbFile smbFile = new SmbFile(path,auth);
//    	SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
//    	smbfos.write("testing.and writing to a file".getBytes());
//    	System.out.println("completed nice !");
    	
    	
//    	File f = new File("\\\\10.1.90.89\\Repositories9089\\htpasswd");
//    	if(f.exists()){
//    		FileReader fr = new FileReader(f);
//    		BufferedReader br = new BufferedReader(fr);
//    		String str = br.readLine();
//    		while(str!=null){
//    			System.out.println(str);
//    			str = br.readLine();
//    		}
//    	}else{
//    		System.out.println("文件不存在");
//    	}
    	
//        String srcPath = "F:\\test\\" + UUID.randomUUID().toString().replaceAll("-", "") + ".txt";
//        File parentDir = new File("\\\\10.1.90.89\\Repositories9089");
//        File [] fs = parentDir.listFiles();
//        System.out.println(fs.length);
//        
//        File targetPath = new File(parentDir, "tt.txt");
//        InputStream in = new FileInputStream(targetPath);
//        OutputStream out = new FileOutputStream(srcPath);
//        try {
//            byte[] bs = new byte[1024];
//            int len = -1;
//            while((len = in.read(bs)) != -1) {
//                out.write(bs, 0, len);
//            }
//        } finally {
//            try {
//                out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                in.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("上传成功！！");
    }
}
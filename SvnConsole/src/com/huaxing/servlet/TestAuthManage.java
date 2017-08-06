package com.huaxing.servlet;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaxing.biz.AuthManage;
import com.huaxing.biz.UserManage;
import com.huaxing.biz.impl.AuthManageImpl;
import com.huaxing.biz.impl.UserManageImpl;
import com.huaxing.util.MyPro;
import com.huaxing.vo.UserAuth;

public class TestAuthManage {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		String json = "{\"username\":\"小民\",\"path\":\"wervc\",\"auth\":\"wsdv\"}";  
//        UserAuth u1 = new UserAuth();
//        u1.setAuth("auth1");
//        u1.setPath("path1");
//        u1.setUsername("username1");
//        
//        UserAuth u2 = new UserAuth();
//        u2.setAuth("auth2");
//        u2.setPath("path2");
//        u2.setUsername("username2");
//        
//        List<UserAuth> uList = new ArrayList<UserAuth>();  
//        uList.add(u1);
//        uList.add(u2);
//        ObjectMapper mapper = new ObjectMapper(); 
//        String jsonlist = mapper.writeValueAsString(uList);
//        //System.out.println(jsonlist);
//        List<UserAuth> uList2 = mapper.readValue(jsonlist, new TypeReference<List<UserAuth>>() {
//		});
//        System.out.println(uList2.toString());
        /** 
         * 测试synchronizeAuth方法
         */  
		AuthManage am = new AuthManageImpl();
        String jsonlist = am.synchronizeAuth();
        File auth = new File(MyPro.getInstance().getProperty("userAndAuth"));
        auth.createNewFile();
        FileWriter fw = new FileWriter(auth);
        fw.write(jsonlist);
        fw.flush();
        fw.close();
        
//        FileReader fr = new FileReader(auth);
//        BufferedReader br = new BufferedReader(fr);
//        String line = br.readLine();
//        while(line!=null){
//        	System.out.println(line);
//        	line = br.readLine();
//        }
//        
//        ObjectMapper om = new ObjectMapper();
//        JavaType javaType = om.getTypeFactory().constructParametricType(List.class, UserAuth.class);
//        List<UserAuth>  uaList = om.readValue(jsonlist, javaType);
//		System.out.println(uaList.size());
		/**
		 * 测试addAuthByUseAuth方法
		 */
//		UserAuth ua = new UserAuth();
//		ua.setAuth("rw");
//		ua.setPath("https://10.1.98.190/svn/BBB/code/DEV");
//		ua.setUsername("tom");
//		new AuthManageImpl().addAuthByUseAuth(ua);
		/**
		 * 测试findAuthByName方法
		 */
//		System.out.println("=================查找权限================");
//		List<UserAuth> list = (new AuthManageImpl()).findAuthByName("tom");
//		int num = 0;
//		for(int i=0; i<list.size(); i++){
//			System.out.println(list.get(i).getUsername() + "=" + list.get(i).getAuth() + "=" + list.get(i).getPath());
//			num = i+1;
//		}
//		System.out.println(num);
		/**
		 * 测试deleteAuthByUserAuth方法
		 */
//		UserAuth ua = new UserAuth();
//		ua.setPath("https://10.1.98.190/svn/BBB/code/DEV");
//		ua.setUsername("tom");
//		new AuthManageImpl().deleteAuthByUserAuth(ua);
		/**
		 * 测试从另一台windows机读取文件
		 */
		//在Test.java中查看
		
		/**
		 * 测试jackson数据转java对象
		 */
//		long startTime = System.currentTimeMillis();
//         
//        UserAuth user = mapper.readValue(json, UserAuth.class);  
//        long endTime = System.currentTimeMillis();
//        System.out.println(user);
//        System.out.println(user.getUsername());
//        System.out.println(user.getPath());
//        System.out.println(user.getAuth());
	}

}

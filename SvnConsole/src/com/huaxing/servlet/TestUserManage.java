package com.huaxing.servlet;

import java.io.*;

import com.huaxing.biz.UserManage;
import com.huaxing.biz.impl.UserManageImpl;

public class TestUserManage {
	private static String username = "jonh";
	private static String pwd = "666";

	public static void main(String[] args) {
//		UserManage um = new UserManageImpl();
//		if(um.findUserByName(username)!=null){
//			System.out.println("此用户存在");
//		}else{
//			um.addUser(username, pwd);
//		}
//		//修改密码
//		String newPwd = "333";
//		if(um.findPwdByName(username).equals(pwd)){
//			um.updateAndResetPwdByName(username, newPwd);
//		}
		
		//重置密码为与用户名一样的
		//um.updateAndResetPwdByName(username, username);
		
	}

}

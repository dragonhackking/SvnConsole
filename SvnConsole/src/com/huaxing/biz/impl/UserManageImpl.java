package com.huaxing.biz.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huaxing.biz.UserManage;
import com.huaxing.util.MyPro;
import com.huaxing.vo.User;
import com.huaxing.vo.UserAuth;

public class UserManageImpl implements UserManage {
	private MyPro singleInstance = MyPro.getInstance();
	private File htpasswd = null;
	private List<String> server = new ArrayList<String>();
	
	public UserManageImpl(){
		
	}

	
	
	/**
	 * 查找指定用户，存在则返回用户名，不存在则返回null
	 */
	@Override
	public String findUserByName(String username, String htpasswd) {
		try {
			FileReader fr = new FileReader(htpasswd);
			BufferedReader br = new BufferedReader(fr);
			String everyLine = br.readLine();
			while(everyLine != null){
				String str[] = everyLine.split(":");
				if(str[0]!=null && str[0].equals(username)){
					return str[0];
				}
				everyLine = br.readLine();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteUser(String username, String htpasswd) {

	}
	/**
	 * 修改或者重置svn用户密码，当需要修改密码时，newPwd为新密码。当需要重置密码时，则把用户名当做密码。
	 * 重置密码后，用户名=密码
	 */
	@Override
	public void updateAndResetPwdByName(String username, String newPwd, String htpasswd) {
		try {
			FileReader fr = new FileReader(htpasswd);
			BufferedReader br = new BufferedReader(fr);
			String everyLine = br.readLine();
			String content = everyLine;
			String temp[] = content.split(":");
			if(content!=null && temp[0].equals(username)){
				content = temp[0] + ":" + newPwd;
			}
			while(everyLine != null){
				everyLine = br.readLine();
				if(everyLine != null){
					String str[] = everyLine.split(":");
					if(str[0]!=null && str[0].equals(username)){
						content += "\n" + str[0] + ":" + newPwd;
					}else{
						content += ("\n" + everyLine);
					}
				}
			}
			FileWriter fw = new FileWriter(htpasswd);
			fw.write(content);
			fw.flush();
			fw.close();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据用户名查找该用户的密码
	 */
	@Override
	public String findPwdByName(String username, String htpasswd) {
		try {
			FileReader fr = new FileReader(htpasswd);
			BufferedReader br = new BufferedReader(fr);
			String everyLine = br.readLine();
			while(everyLine != null){
				String str[] = everyLine.split(":");
				if(str[0]!=null && str[0].equals(username)){
					return str[1];
				}
				everyLine = br.readLine();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, List<String>> findUsersByIp(List<String> server, String htpasswd) throws IOException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();	
		String resPath = "";
		if(server != null){
			for(String s:server){
				resPath = "\\\\"+s.split("-")[1]+"\\"+s.split("-")[2]+"\\"+htpasswd;
				File file = new File(resPath);
				List<String> userList = new ArrayList<String>();
				if(file.exists()){
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String everyLine = "";
					while((everyLine=br.readLine()) != null){
						userList.add(everyLine.split(":")[0]);
					}
				}else{
					continue;
				}
				map.put(s.split("-")[1], userList);
			}
			return map;
		}else{
			return null;
		}
	}

	/**
	 * 添加svn用户
	 */
	@Override
	public String addUser(User user, List<String> server, String htpasswd) {
		String resPath = "";
		if(server != null){
			for(String s:server){  //https://10.1.90.89/svn/FTS/
				if(user.getPath().split("/")[2].split(":")[0].equals(s.split("-")[1])){
					resPath = "\\\\"+s.split("-")[1]+"\\"+s.split("-")[2]+"\\"+htpasswd;
					break;
				}
			}
		}else{
			return "no any server";//还没配置任何的svn服务器（serverConfig是空的）
		}
		if("".equals(resPath)){
			return "no ip:"+user.getPath().split("/")[2].split(":")[0]; //该用户所添加的svn服务器不存在（找不到该IP）
		}
		try {
			File file = new File(resPath);
			if(!file.exists()){
				return "no file:"+htpasswd;
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String everyLine = "";
			String content = "";
			boolean flag = true;
			while((everyLine=br.readLine()) != null){
				String uname = everyLine.split(":")[0];
				if(uname.equals(user.getUsername())){
					flag = false;
					return "user is exist";
				}
				content += everyLine+"\n";
			}
			if(flag){
				content += 
			}
			fw.write(finalLine);
			fw.flush();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

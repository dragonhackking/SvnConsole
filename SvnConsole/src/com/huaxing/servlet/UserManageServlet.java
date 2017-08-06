package com.huaxing.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huaxing.biz.UserManage;
import com.huaxing.biz.impl.UserManageImpl;
import com.huaxing.util.MyPro;
import com.huaxing.vo.UserAuth;


public class UserManageServlet extends HttpServlet {
	private MyPro mp = MyPro.getInstance();
	private UserManage userManage = new UserManageImpl();
	
	public UserManageServlet(){
		super();
	}
	
	public void destory(){
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String op = request.getParameter("userOp");
		String serverFile = mp.getProperty("server");
		String htpasswd = mp.getProperty("userList"); //获取用户管理文件的名字：htpasswd
		String serverConfig = this.getClass().getClassLoader().getResource("/").getPath()+serverFile; 
		List<String> server = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(new File(serverConfig));
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			while((str = br.readLine())!=null){
				if("".equals(str)){
					continue;
				}
				server.add(str);
			}		
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if("toUserManage".equals(op)){
			toUserManage(request, response, session, server, htpasswd);
		}else if("addUser".equals(op)){
			addUser(request, response, session, server, htpasswd);
		}
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, List<String> server, String htpasswd) {
		String username = request.getParameter("username");
		String path = request.getParameter("path");
		UserAuth user = new UserAuth();
		user.setUsername(username);
		user.setPath(path);
		
		
		userManage.addUser(user, htpasswd);
	}

	private void toUserManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, List<String> server, String htpasswd) {
		try {
			Map<String, List<String>> map = userManage.findUsersByIp(server, htpasswd);
			session.setAttribute("allUsers", map);
			response.sendRedirect("userManage.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
	
	
}

package com.huaxing.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huaxing.util.MyPro;


public class AuthManageServlet extends HttpServlet {
	private MyPro mp = null;
	private File htpasswd = null;
	
	public AuthManageServlet(){
		super();
		this.mp = MyPro.getInstance();
		this.htpasswd = new File(this.mp.getProperty("respositoryPath") + "\\" + this.mp.getProperty("userList"));
	}
	
	public void destory(){
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if(!htpasswd.exists()){
			System.out.println("htpasswd不存在");
			return;
		}
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		  
		String op = request.getParameter("op");
		  
		System.out.println(op+":"+username+"-"+pwd);
		if(op.equals("add")){
			  
		}else if(op.equals("del")){
			  
		}
		 
		  //session.setAttribute("account", account);
		   
		  //resp.sendRedirect(login_suc);
		   
		  //String login_fail = "fail.jsp";
		  //resp.sendRedirect(login_fail);
		  return;
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
	
	
}

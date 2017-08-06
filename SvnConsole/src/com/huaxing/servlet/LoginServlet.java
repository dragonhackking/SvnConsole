package com.huaxing.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huaxing.util.MyPro;


public class LoginServlet extends HttpServlet {
	private MyPro mp = MyPro.getInstance();
	private List<String> server = new ArrayList<String>();
	private String authFile = "";
	private String userAndAuth = "";
	
	public LoginServlet(){
		super();
		this.authFile = mp.getProperty("authFile");		
		this.userAndAuth = mp.getProperty("userAndAuth");
		String serverFile = mp.getProperty("server");
		String path = this.getClass().getClassLoader().getResource("/").getPath()+serverFile; 
		try {
			FileReader fr = new FileReader(new File(path));
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			while(str!=null && !str.equals("")){
				server.add(str);
				str = br.readLine();
			}		
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void destory(){
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String op = request.getParameter("loginOp");
		if("login".equals(op)){
			doLogin(request, response, session);
		}else if("toIndex".equals(op)){
			toIndex(request, response, session);
		}
			
		
		return;
	}
	
	private void toIndex(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		if(session.getAttribute("server") != null){
			response.sendRedirect("index.jsp");
		}else{
			List<String> ipList = new ArrayList<String>();
			for(int i=0; i<server.size(); i++){
				ipList.add(server.get(i).split("-")[1]);
			}
			session.setAttribute("server", ipList);
			response.sendRedirect("index.jsp");
		}
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		List<String> ipList = new ArrayList<String>();
		for(int i=0; i<server.size(); i++){
			ipList.add(server.get(i).split("-")[1]);
		}
		session.setAttribute("server", ipList);
		response.sendRedirect("index.jsp");
		
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
	
	
}

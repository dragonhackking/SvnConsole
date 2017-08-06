package com.huaxing.biz.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaxing.biz.AuthManage;
import com.huaxing.util.MyPro;
import com.huaxing.vo.UserAuth;

public class AuthManageImpl implements AuthManage {
	private MyPro singleInstance = MyPro.getInstance();
	private String resPath = "";
	private String authFile = "";
	private String userAndAuth = "";
	private List<String> server = new ArrayList<String>();
	
	public AuthManageImpl(){
//		this.resPath = singleInstance.getProperty("respositoryPath");
		this.authFile = singleInstance.getProperty("authFile");		
		this.userAndAuth = singleInstance.getProperty("userAndAuth");
		try {
			FileReader fr = new FileReader(new File("./src/serverConfig.txt"));
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
	
	@Override
	public String synchronizeAuth() throws JsonProcessingException {
//		File file = new File(resPath);
//		File f[] = file.listFiles();
		String failedDirectory = "";
		File svnAuth = null;
		List<UserAuth> uaList = new ArrayList<UserAuth>();
		for(int n=0; n<server.size(); n++){
			//File f = new File("\\\\10.1.90.89\\Repositories9089\\htpasswd");
			resPath = "\\\\"+server.get(n).split("-")[1]+"\\"+server.get(n).split("-")[2];
			String svnPath = "https://"+server.get(n).split("-")[1]+"/svn/";
			File ff = new File(resPath);
			File f[] = ff.listFiles();
			for(int i=0; i<f.length; i++){
				if(f[i].isDirectory()){
					svnAuth = new File(resPath+"\\"+f[i].getName()+"\\conf"+"\\"+authFile); 
					if(svnAuth.exists()){
						try {
							FileReader fr = new FileReader(svnAuth);
							BufferedReader br = new BufferedReader(fr);
							String everyLine = br.readLine();
							String authPath = "";
							String fullAuthPath = "";
							Boolean flag = false;
							int num = 1;
							while(everyLine != null){
								//System.out.println(num++);
								everyLine = br.readLine();
								if(everyLine == null){
									continue;
								}
								if(everyLine.equals("") || everyLine.substring(0,1).equals("#")){
									continue;
								}
//								if(everyLine!= null && !everyLine.equals("") && everyLine.substring(0,1).equals("[") && !flag){
								if(everyLine.substring(0,1).equals("[") && !flag){
									flag = true;//标识已读取到权限区域（因该文件最前面有一部分自带的无效语句）
									//获取权限路径，fullAuthPath作为用户的权限数据存入json数据中
									authPath = everyLine.substring(1, everyLine.length()-1);
									fullAuthPath = authPath.equals("/")?svnPath+f[i].getName():svnPath+f[i].getName()+authPath;
								}else{
									flag = false;
									
									if(everyLine.split("=").length > 1){
										UserAuth ua = new UserAuth();
										ua.setUsername(everyLine.split("=")[0]);
										ua.setPath(fullAuthPath);
										ua.setAuth(everyLine.split("=")[1]);
										uaList.add(ua);
										System.out.println(ua.getUsername()+"="+ua.getAuth()+"="+ua.getPath());
									}
								}
							}
							br.close();
							fr.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
						failedDirectory += f[i].getName()+",";
					}
				}
			}
		}
		ObjectMapper om = new ObjectMapper();
		String jsonlist = om.writeValueAsString(uaList);
		return jsonlist;
	}
	/**
	 * 查找指定用户的权限
	 */
	@Override
	public List<UserAuth> findAuthByName(String username) throws IOException {
		List<UserAuth> uaList = new ArrayList<UserAuth>();
		FileReader fr = new FileReader(userAndAuth);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		ObjectMapper om = new ObjectMapper();
		JavaType javaType = om.getTypeFactory().constructParametricType(List.class, UserAuth.class);
		uaList = om.readValue(line, javaType);
		String uname = "";
		for(int i=0; i<uaList.size(); i++){
			uname = uaList.get(i).getUsername();
			if(!uname.equals(username)){
				uaList.remove(i);
				i -= 1;//list集合remove一个元素后下标会往前移动一位，所以要比较下一个元素时，要把i减一
			}
		}
		return uaList;
	}

	/**
	 * 添加指定用户的权限。
	 */
	@Override
	public void addAuthByUserAuth(UserAuth ua) throws IOException {
		String username = ua.getUsername();
		String path = ua.getPath();
		String auth = ua.getAuth();
		String ip = path.split("/")[2].split(":")[0];
		String pathSplit[] = path.split("/");//待添加的权限地址：https://10.1.90.89/svn/BDPS/code/UAT
		String remoteFilePath = ""; 
		for(int i=0; i<server.size(); i++){
			String s[] = server.get(i).split("-");
			if(ip.equals(s[1])){
				remoteFilePath = "\\\\"+s[1]+"\\"+s[2]+"\\"+pathSplit[4]+"\\conf\\"+authFile;
			}
		}
		System.out.println(remoteFilePath);
		File remoteFile = new File(remoteFilePath);
		FileReader frr = new FileReader(remoteFile);
		BufferedReader brr = new BufferedReader(frr);
		String everyLine = brr.readLine();
		String newAuth = "";
		boolean isOk = false;
		boolean userFlag = false;
		String isAuth = "";
		while(everyLine!=null){
			if(!isOk){
				if(everyLine.equals("") || everyLine.substring(0,1).equals("#")){
					newAuth += everyLine+"\n";
				}else if(everyLine.equals("[/]") && pathSplit.length==5){
					newAuth += everyLine+"\n"+username+"="+auth+"\n";
					isOk = true;
				}else if(everyLine.substring(0,1).equals("[") && everyLine.length()>3){
					//先判断是否路径包含，包含则userFlag=true，后续需要对username做比较，检查该权限路径下是否已存在该用户
					if(path.indexOf(everyLine.substring(1,everyLine.length()-1))!=-1){
						newAuth += everyLine+"\n";
						userFlag = true;
						isAuth = "true";
					}else{
						newAuth += everyLine+"\n";
						isAuth = "false";
					}
				}else if(username.equals(everyLine.split("=")[0]) && isAuth.equals("true")){
					if(path.substring(path.indexOf(pathSplit[5])-1,path.length()).equals(everyLine.substring(1,everyLine.length()-1))){
						newAuth += username+"="+auth+"\n";
						isOk = true;
					}else{
						if(everyLine.split("=").length==2 && everyLine.split("=")[1].equals("rw")){
							newAuth += everyLine+"\n";
							isOk = true;
						}else if(everyLine.split("=").length==1){
							newAuth += everyLine+auth+"\n";
							isOk = true;
						}else if(everyLine.split("=").length==2 && everyLine.split("=")[1].equals("r") && auth.equals("r")){
							newAuth += everyLine+"\n";
							isOk = true;
						}else{
							newAuth += everyLine+"\n";
						}
					}
				}else{
					newAuth += everyLine+"\n";
				}
			}else{
				newAuth += everyLine+"\n";
			}
			everyLine = brr.readLine();
		}
		if(!isOk){
			newAuth += pathSplit.length==5?"[/]\n"+username+"="+auth:"["+path.substring(path.indexOf(pathSplit[5])-1,path.length())+"]\n"+username+"="+auth;
		}
		System.out.println(newAuth);
		FileWriter fww = new FileWriter(remoteFile);
		BufferedWriter bww = new BufferedWriter(fww);
		bww.write(newAuth);
		bww.flush();
		bww.close();
		brr.close();
		synchronizeAuth();
	}
	@Override
	public void deleteAuthByUserAuth(UserAuth ua) throws IOException {
			String username = ua.getUsername();
			String path = ua.getPath();//https://10.1.98.190/svn/CCC/code/DEV
			String ip = path.split("/")[2].split(":")[0];
			String pathSplit[] = path.split("/");//待添加的权限地址：https://10.1.90.89/svn/BDPS/code/UAT
			String auth = path.substring(path.indexOf(pathSplit[5])-1,path.length());
			String remoteFilePath = ""; 
			for(int i=0; i<server.size(); i++){
				String s[] = server.get(i).split("-");
				if(ip.equals(s[1])){
					remoteFilePath = "\\\\"+s[1]+"\\"+s[2]+"\\"+pathSplit[4]+"\\conf\\"+authFile;
				}
			}
			System.out.println(remoteFilePath);
			File remoteFile = new File(remoteFilePath);
			FileReader frr = new FileReader(remoteFile);
			BufferedReader brr = new BufferedReader(frr);
			String everyLine = "";
			String newAuth = "";
			int num = 0;
			boolean flag = false;
			List<String> lineList = new ArrayList<String>();
			while((everyLine=brr.readLine())!=null){
				if(everyLine.equals("")){
					continue;
				}else if(everyLine.substring(0,1).equals("[") && flag){
					lineList.add(everyLine);
					int temp = lineList.indexOf(everyLine);
					if(temp-num > 2){
						for(int i=num+1; i<temp; i++){
							if(lineList.get(i).split("=")[0].equals(username)){
								lineList.remove(i);
								break;
							}
						}
					}else{
						lineList.remove(num);
						lineList.remove(num);
					}
					flag = false;
				}else if(everyLine.length()>1 && everyLine.substring(1,everyLine.length()-1).equals(auth)){
					lineList.add(everyLine);
					flag = true;
					num = lineList.indexOf(everyLine);
				}else{
					lineList.add(everyLine);
				}
			}		
			if(flag){
				int start = num;
				int end = lineList.size();
				if(end-start > 2){
					for(int i=start+1; i<end; i++){
						if(lineList.get(i).split("=")[0].equals(username)){
							lineList.remove(i);
							break;
						}
					}
				}else{
					lineList.remove(start);
					lineList.remove(start);
				}
			}
			for(String s:lineList){
				newAuth += s+"\n";
			}
			System.out.println(newAuth);
			FileWriter fww = new FileWriter(remoteFile);
			BufferedWriter bww = new BufferedWriter(fww);
			bww.write(newAuth);
			bww.flush();
			bww.close();
			brr.close();
			synchronizeAuth();
	}

}

package com.huaxing.biz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.huaxing.vo.*;

public interface UserManage {
	public String addUser(User user, List<String> server, String htpasswd);
	public String findUserByName(String username, String htpasswd);
	public Map<String,List<String>> findUsersByIp(List<String> server, String htpasswd) throws IOException;
	public void deleteUser(String username, String htpasswd);
	public void updateAndResetPwdByName(String username, String newPwd, String htpasswd);
	public String findPwdByName(String username, String htpasswd);
}

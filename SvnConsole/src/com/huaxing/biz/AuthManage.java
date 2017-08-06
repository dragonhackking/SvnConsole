package com.huaxing.biz;

import java.io.*;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huaxing.vo.UserAuth;

public interface AuthManage {
	public String synchronizeAuth() throws JsonProcessingException;
	
	public List<UserAuth> findAuthByName(String username) throws IOException;
	
	public void addAuthByUserAuth(UserAuth ua) throws IOException;
	
	public void deleteAuthByUserAuth(UserAuth ua) throws IOException;
}

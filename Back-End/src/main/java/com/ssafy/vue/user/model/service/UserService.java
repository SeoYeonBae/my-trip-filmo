package com.ssafy.vue.user.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.user.model.UserDto;

public interface UserService {

	int checkID(String userId) throws Exception;
	
	Map<String, String> findPass(Map<String, Object> map) throws Exception;

	int joinUser(UserDto userDto) throws Exception;

	UserDto loginUser(UserDto userDto) throws Exception;

	public UserDto userInfo(String userid) throws Exception;
	
	int modifyUser(UserDto userDto) throws Exception;

	int deleteUser(String userId) throws Exception;
	
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	
	public Object getRefreshToken(String userid) throws Exception;
	
	public void deleRefreshToken(String userid) throws Exception;

	public void offFk() throws Exception;
	
	public void onFk() throws Exception;

}

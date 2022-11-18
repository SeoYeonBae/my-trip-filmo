package com.ssafy.vue.user.model.service;

import java.util.Map;

import com.ssafy.vue.user.model.UserDto;

public interface UserService {

	int checkID(String userId) throws Exception;
	
	String findPass(Map<String, Object> map) throws Exception;

	int joinUser(UserDto userDto) throws Exception;

	UserDto loginUser(Map<String, Object> map) throws Exception;

	int modifyUser(UserDto userDto) throws Exception;

	int deleteUser(String userId) throws Exception;

}

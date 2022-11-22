package com.ssafy.vue.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.user.model.UserDto;

@Mapper
public interface UserMapper {
	
	int checkID(String userId) throws SQLException;

	Map<String, String> findPass(Map<String, Object> map) throws SQLException;

	int joinUser(UserDto userDto) throws SQLException;

	UserDto loginUser(UserDto userDto) throws SQLException;
	
	public UserDto userInfo(String userid) throws SQLException;

	int modifyUser(UserDto userDto) throws SQLException;

	int deleteUser(String userId) throws SQLException;
	
	public void saveRefreshToken(Map<String, String> map) throws SQLException;

	public Object getRefreshToken(String userid) throws SQLException;
	
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	public void offFk() throws SQLException;
	
	public void onFk() throws Exception;

}

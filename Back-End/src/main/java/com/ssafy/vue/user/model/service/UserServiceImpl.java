package com.ssafy.vue.user.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.user.model.UserDto;
import com.ssafy.vue.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {


	private UserMapper userMapper;

	@Autowired
	private UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDto loginUser(UserDto userDto) throws Exception {
		return userMapper.loginUser(userDto);
	}
	
	@Override
	public UserDto userInfo(String userid) throws Exception {
		return userMapper.userInfo(userid);
	}
	
	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return userMapper.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}

	@Override
	public int checkID(String userId) throws Exception {
		return userMapper.checkID(userId);
	}

	@Override
	public Map<String, String> findPass(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.findPass(map);
	}

	@Override
	public int joinUser(UserDto userDto) throws Exception {
		return userMapper.joinUser(userDto);
	}


	@Override
	public int modifyUser(UserDto userDto) throws Exception {
		return userMapper.modifyUser(userDto);
	}

	@Override
	public void deleteUser(UserDto userDto, String path) throws Exception {
		userMapper.deleteUser(userDto.getId());
		if(userDto.getSaveFile() != "" || userDto.getSaveFile() != null) {
			File file = new File(path + File.separator + userDto.getSaveFile());
			file.delete();
			File sfile = new File(path + File.separator + "s_" + userDto.getSaveFile());
			sfile.delete();
		}
	}

	@Override
	public void offFk() throws Exception {
		userMapper.offFk();
	}

	@Override
	public void onFk() throws Exception {
		userMapper.onFk();
	}

}

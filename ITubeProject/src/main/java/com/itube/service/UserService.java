package com.itube.service;

import com.itube.domain.UserVO;
import com.itube.dto.LoginDTO;

public interface UserService {
	//public boolean login(String id, String pwd);
	public UserVO login(LoginDTO dto) throws Exception;
	public boolean logout() throws Exception;
	public boolean join(UserVO uservo) throws Exception;
	public void adjust(UserVO uservo) throws Exception;
	public boolean delete(String uid) throws Exception;
}

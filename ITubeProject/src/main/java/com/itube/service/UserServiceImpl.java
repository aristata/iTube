package com.itube.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itube.domain.UserVO;
import com.itube.dto.LoginDTO;
import com.itube.persistence.UserDAOImpl;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAOImpl userDAO;

	/*@Override
	public boolean login(String id, String pwd) {
		UserVO uservo = null;
		try {
			uservo = dao.select(id, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (uservo != null){
			
			return true;
		}
		else
			return false;
	}*/
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <- Login start
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		
		return userDAO.select(dto.getId(), dto.getPwd());
		
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <- Login end

	@Override
	public boolean logout() {
		
		return false;
	}

	@Override
	public boolean join(UserVO uservo) {
		try {
			userDAO.insert(uservo);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public void adjust(UserVO uservo) {
		
		try {
			userDAO.update(uservo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(String uid) {
		return false;
	}

}

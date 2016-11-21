package com.itube.persistence;

import java.sql.SQLException;
import java.util.List;

import com.itube.domain.UserVO;

public interface UserDAO {
	public UserVO select(String id,String pwd) throws SQLException;
	public UserVO selectID(String id) throws SQLException;
	public UserVO selectUID(String uid) throws SQLException;
	public UserVO selectNickname(String nickname) throws SQLException;
	public UserVO selectEmail(String email) throws SQLException;
	public List<UserVO> selectGrade(int grade) throws SQLException;
	public void insert(UserVO uservo) throws SQLException;
	public void update(UserVO uservo) throws SQLException;
	public void update(String id, int grade) throws SQLException;
	public void delete(String uid) throws SQLException;
	public void deleteById(String id) throws SQLException;
	public List<UserVO> listAll() throws SQLException;
}

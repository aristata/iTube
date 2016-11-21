package com.itube.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itube.domain.UserVO;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.itube.persistence.UserMapper";
	
	@Override
	public void insert(UserVO uservo) throws SQLException{
		session.insert(namespace+".insert",uservo);
	}

	@Override
	public void update(UserVO uservo) throws SQLException{
		session.update(namespace+".update",uservo);
	}
	
	@Override
	public void update(String id, int grade) throws SQLException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("grade", grade);
		session.update(namespace+".updateById", map);
		
	}

	@Override
	public List<UserVO> listAll() throws SQLException{
		return session.selectList(namespace+".selectAll");
	}

	@Override
	public UserVO select(String id,String pwd) throws SQLException{
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.selectOne(namespace+".selectOne",map);
	}

	@Override
	public void delete(String uid) throws SQLException{
		session.delete(namespace+".delete",uid);
	}
	
	@Override
	public void deleteById(String id) throws SQLException{
		session.delete(namespace+".deleteById",id);
	}

	@Override
	public UserVO selectID(String id) throws SQLException {
		return session.selectOne(namespace+".selectID",id);
	}

	@Override
	public UserVO selectUID(String uid) throws SQLException {
		return session.selectOne(namespace+".selectUID",uid);
	}

	@Override
	public UserVO selectNickname(String nickname) throws SQLException {
		return session.selectOne(namespace+".selectNickname",nickname);
	}

	@Override
	public UserVO selectEmail(String email) throws SQLException {
		return session.selectOne(namespace+".selectEmail",email);
	}

	@Override
	public List<UserVO> selectGrade(int grade) throws SQLException {
		return session.selectList(namespace+".selectGrade",grade);
	}

}

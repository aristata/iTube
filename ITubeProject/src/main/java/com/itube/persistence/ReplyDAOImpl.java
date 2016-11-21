package com.itube.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itube.domain.ReplyVO;

@Repository("ReplyDAO")
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.itube.mappers.ReplyMapper";
		

	@Override
	public void insert(ReplyVO replyvo) throws Exception {
		session.insert(namespace + ".insert", replyvo);

	}

	@Override
	public void update(ReplyVO replyvo) throws Exception {
		session.update(namespace + ".update", replyvo);

	}

	@Override
	public void delete(Integer rid) throws Exception {
		session.delete(namespace + ".delete", rid);

	}

	@Override
	public List<ReplyVO> listAll(Integer pid) throws Exception {
		return session.selectList(namespace + ".listAll", pid);
	}

	@Override
	public void up(Integer rid) throws Exception {
		session.update(namespace +".up", rid);
		
	}

	@Override
	public void down(Integer rid) throws Exception {
		session.update(namespace +".down", rid);
		
	}

	@Override
	public String upDownTypeGet(int rid, String sessionid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("rid", rid);
		map.put("sessionid", sessionid);
		String obj = session.selectOne(namespace+".upDownTypeGet",map);
		if(obj == null)
			return "no";
		return obj;
	}
	@Override
	public void insertUpDown(String sessionid, int rid, String type) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("rid", rid);
		map.put("sessionid", sessionid);
		map.put("type", type);
		session.insert(namespace+".insertUpDown", map);
	}
	
	// 댓글이 삭제될 때 해당 게시물의 번호를 알아내는 기능 구현
	@Override
	public int getPid(Integer rid) throws Exception {
		return session.selectOne(namespace + ".getPid", rid);
	}
}

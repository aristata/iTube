package com.itube.persistence;

import java.util.List;

import com.itube.domain.ReplyVO;

public interface ReplyDAO {

	public void insert(ReplyVO replyvo) throws Exception;
	public void update(ReplyVO replyvo) throws Exception;
	public void delete(Integer rid) throws Exception;
	public List<ReplyVO> listAll(Integer pid) throws Exception;
	public void up(Integer rid) throws Exception;
	public void down(Integer rid) throws Exception;
		
	// 업, 다운과 세션 아이디 중복 방지
	public String upDownTypeGet(int rid, String sessionid) throws Exception;
		
	// 업, 다운 타입넣기
	public void insertUpDown(String sessionid, int rid, String type) throws Exception;
	
	// 댓글이 삭제될 때 해당 게시물의 번호를 알아내는 기능
	public int getPid(Integer rid) throws Exception;
}

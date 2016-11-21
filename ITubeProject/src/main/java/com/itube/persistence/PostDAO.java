package com.itube.persistence;

import java.util.List;

import com.itube.domain.Criteria;
import com.itube.domain.PostVO;
import com.itube.domain.SearchCriteria;

public interface PostDAO {

	public PostVO read(Integer pid) throws Exception;
	public PostVO read(String url) throws Exception;
	public void insert(PostVO postvo) throws Exception;
	public void update(PostVO postvo) throws Exception;
	public void delete(Integer pid) throws Exception;
	public List<PostVO> listAll() throws Exception;
	/* Page */
	public List<PostVO> listPage(int page) throws Exception;
	public List<PostVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri) throws Exception;
	//
	public List<PostVO> listCriteria2(Criteria cri) throws Exception;
	public int countPaging2(Criteria cri) throws Exception;
	public List<PostVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	//AdminController 에서 사용하는 부분
	public void update(Integer postID, String postCategory) throws Exception;
	
	//특정 게시물의 조회수 처리
	public void updateViewCnt(Integer pid) throws Exception;
	public void updateViewCnt(String url) throws Exception;
	
	//특정 게시물의 좋아요 처리
	public void updateLike(Integer pid) throws Exception;
	//특정 게시물의 싫어요 처리
	public void updateDislike(Integer pid) throws Exception;
	
	//베스트 게시물 조회
	public List<PostVO> bestList() throws Exception;
	
	//업, 다운 카운트 조회
	public int likeCountGet(Integer pid) throws Exception;
	public int dislikeCountGet(Integer pid) throws Exception;
	
	// 업, 다운과 세션 아이디 중복 방지
	public String upDownTypeGet(int pid, String sessionid) throws Exception;
	
	// 업, 다운 타입넣기
	public void insertUpDown(String sessionid, int pid, String type) throws Exception;
	
	//댓글 숫자 표시
	public void updateReplyCnt(Integer pid, int cnt) throws Exception;
	
	//즐겨찾기
	public void bookmark(Integer pid, Integer uid) throws Exception;
	public List<PostVO> listBookmark(Integer uid) throws Exception;
}

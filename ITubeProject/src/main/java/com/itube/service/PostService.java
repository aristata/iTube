package com.itube.service;

import java.util.List;

import com.itube.domain.Criteria;
import com.itube.domain.PostVO;
import com.itube.domain.SearchCriteria;

public interface PostService {

	public PostVO readPost(Integer pid) throws Exception;
	public PostVO readPost(String url) throws Exception;
	public void writePost(PostVO postvo) throws Exception;
	public void modifyPost(PostVO postvo) throws Exception;
	public void removePost(Integer pid) throws Exception;
	public List<PostVO> mainPost(Integer viewcnt, Integer up, Integer down) throws Exception;
	/* Page */
	public List<PostVO> listCriteria(Criteria cri) throws Exception;
	public int listCountCriteria(Criteria cri) throws Exception;
	//
	public List<PostVO> listCriteria2(Criteria cri) throws Exception;
	public int listCountCriteria2(Criteria cri) throws Exception;
	public List<PostVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	//좋아요
	public PostVO likeUp(Integer pid) throws Exception;
	//싫어요
	public PostVO dislikeUp(Integer pid) throws Exception;
	//즐겨찾기
	public void addBookmark(Integer pid, Integer uid) throws Exception;
	public List<PostVO> listBookmark(Integer uid)throws Exception;
}

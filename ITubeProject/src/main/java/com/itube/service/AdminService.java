package com.itube.service;

import java.util.List;

import com.itube.domain.PostVO;
import com.itube.domain.UserVO;

public interface AdminService {

	public List<UserVO> showUser() throws Exception;
	public List<PostVO> showPost() throws Exception;
	public void userModify(String id, int grade) throws Exception;
	public void userDelete(String id) throws Exception;
	public void postModify(Integer postID, String postCategory ) throws Exception;
	public void postDelete(Integer postID) throws Exception;
	
	//베스트 게시물 조회
	public List<PostVO> showBestPost() throws Exception;
}

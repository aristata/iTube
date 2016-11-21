package com.itube.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itube.domain.PostVO;
import com.itube.domain.UserVO;
import com.itube.persistence.PostDAOImpl;
import com.itube.persistence.UserDAOImpl;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private UserDAOImpl userDAO;
	
	@Inject
	private PostDAOImpl postDAO;
	
	@Override
	public List<UserVO> showUser() throws Exception {
				
		return userDAO.listAll();
	}

	@Override
	public List<PostVO> showPost() throws Exception {
		return postDAO.listAll();
	}

	//회원 권한 수정
	@Override
	public void userModify(String id, int grade) throws Exception {
		userDAO.update(id, grade);
	}
	
	//회원 강제 삭제
	@Override
	public void userDelete(String id) throws Exception {
		userDAO.deleteById(id);
	}

	//게시글 카테고리 수정
	@Override
	public void postModify(Integer postID, String postCategory) throws Exception {
		/*System.out.println("---------------------------------------");
		System.out.println("AdminServiceImpl - postCategoryModifyTest");
		System.out.println("postID : " + postID);
		System.out.println("postCategory : " + postCategory);
		System.out.println("---------------------------------------");*/
		postDAO.update(postID, postCategory);
	}

	//게시글 강제 삭제
	@Override
	public void postDelete(Integer postID) throws Exception {
		postDAO.delete(postID);	
	}
	
	//베스트 게시물 조회
	@Override
	public List<PostVO> showBestPost() throws Exception {
		return postDAO.bestList();
	}

}

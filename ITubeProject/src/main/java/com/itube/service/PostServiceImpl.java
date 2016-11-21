package com.itube.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itube.domain.Criteria;
import com.itube.domain.PostVO;
import com.itube.domain.SearchCriteria;
import com.itube.persistence.PostDAOImpl;

@Service
public class PostServiceImpl implements PostService {

	@Inject
	private PostDAOImpl dao;
	
	
	@Override
	public PostVO readPost(Integer pid) throws Exception {
		dao.updateViewCnt(pid);
		return dao.read(pid);
	}
	@Override
	public void modifyPost(PostVO postvo) throws Exception {
		dao.update(postvo);
	}
	@Override
	public void removePost(Integer pid) throws Exception {
		dao.delete(pid);
	}
	@Override
	public void writePost(PostVO postvo) throws Exception {
		dao.insert(postvo);
	}
	@Override
	public List<PostVO> mainPost(Integer viewcnt, Integer up, Integer down) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/* Page */
	@Override
	public List<PostVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}
	//
	@Override
	public List<PostVO> listCriteria2(Criteria cri) throws Exception {
		return dao.listCriteria2(cri);
	}
	@Override
	public int listCountCriteria2(Criteria cri) throws Exception {
		return dao.countPaging2(cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}
	@Override
	public List<PostVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}
	@Override
	public PostVO readPost(String url) throws Exception {
		dao.updateViewCnt(url);
		return dao.read(url);
	}
	
	//좋아요
	@Override
	public PostVO likeUp(Integer pid) throws Exception {
		dao.updateLike(pid);
		return dao.read(pid);
	}
	//싫어요
	@Override
	public PostVO dislikeUp(Integer pid) throws Exception {
		dao.updateDislike(pid);
		return dao.read(pid);
	}
	//즐겨찾기
@Override
	public void addBookmark(Integer pid, Integer uid) throws Exception {
		dao.bookmark(pid, uid);
	}
	@Override
	public List<PostVO> listBookmark(Integer uid) throws Exception {
		return dao.listBookmark(uid);
	}
}

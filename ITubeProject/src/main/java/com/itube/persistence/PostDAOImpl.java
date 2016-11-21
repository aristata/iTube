package com.itube.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itube.domain.Criteria;
import com.itube.domain.PostVO;
import com.itube.domain.SearchCriteria;

@Repository("PostDAO")
public class PostDAOImpl implements PostDAO{

	@Inject
	private SqlSession session;
	
	public static final String namespace = "com.itube.mappers.PostMapper";
	
	@Override
	public PostVO read(Integer pid) throws Exception {
		return session.selectOne(namespace+".read", pid);
	}
	@Override
	public void insert(PostVO postvo) throws Exception {
		session.insert(namespace+".insert", postvo);
		
	}
	@Override
	public void update(PostVO postvo) throws Exception {
		session.update(namespace+".update", postvo);
		
	}
	@Override
	public void delete(Integer pid) throws Exception {
		session.delete(namespace+".delete", pid);
		
	}
	@Override
	public List<PostVO> listAll() throws Exception {
		System.out.println(session.selectList(namespace+".listAll"));
		return session.selectList(namespace+".listAll");
	}
	/* Page */
	@Override
	public List<PostVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}

		page = (page - 1) * 10;

		return session.selectList(namespace + ".listPage", page);
	}
	@Override
	public List<PostVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCriteria", cri);
	}
	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging", cri);
	}
	//
	@Override
	public List<PostVO> listCriteria2(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCriteria2", cri);
	}
	@Override
	public int countPaging2(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging2", cri);
	}
	@Override
	public List<PostVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public PostVO read(String url) throws Exception {
		return session.selectOne(namespace+".readByUrl", url);
	}
	
	//AdminController 에서 사용하는 부분
	@Override
	public void update(Integer postID, String postCategory) throws Exception {
		System.out.println("---------------------------------------");
		System.out.println("PostDAOImpl - postCategoryModifyTest");
		System.out.println("postID : " + postID);
		System.out.println("postCategory : " + postCategory);
		System.out.println("---------------------------------------");
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("postID", postID);
		map.put("postCategory", postCategory);
		
		session.update(namespace+".updateCategory", map);
		
	}
	
	//특정 게시물의 조회수 처리
	@Override
	public void updateViewCnt(Integer pid) throws Exception {
		session.update(namespace + ".updateViewCnt", pid);
	}
	
	@Override
	public void updateViewCnt(String url) throws Exception {
		session.update(namespace + ".updateViewCntByURL", url);
	}
	
	//특정 게시물의 좋아요 처리
	@Override
	public void updateLike(Integer pid) throws Exception {
		session.update(namespace + ".updateLike", pid);
	}
	//특정 게시물의 싫어요 처리
	@Override
	public void updateDislike(Integer pid) throws Exception {
		session.update(namespace + ".updateDislike", pid);		
	}
	
	//베스트 게시물 조회
	@Override
	public List<PostVO> bestList() throws Exception {
		return session.selectList(namespace + ".bestList");
	}
	@Override
	public int likeCountGet(Integer pid) throws Exception {
		return session.selectOne(namespace+".likeCountByPid",pid);
	}
	@Override
	public int dislikeCountGet(Integer pid) throws Exception {
		return session.selectOne(namespace+".dislikeCountByPid",pid);
	}
	@Override
	public String upDownTypeGet(int pid, String sessionid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("sessionid", sessionid);
		String obj =  session.selectOne(namespace+".upDownTypeGet",map);
		if(obj == null)
			return "no";
		return obj;
	}
	@Override
	public void insertUpDown(String sessionid, int pid, String type) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("sessionid", sessionid);
		map.put("type", type);
		session.insert(namespace+".insertUpDown", map);
	}

	//댓글 숫자 표시
	@Override
	public void updateReplyCnt(Integer pid, int cnt) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("cnt", cnt);
		
		session.update(namespace + ".updateReplyCnt", map);
		
	}
	//즐겨찾기
	@Override
	public void bookmark(Integer pid, Integer uid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("uid", uid);
		session.update(namespace + ".bookmark", map);
	}
	@Override
	public List<PostVO> listBookmark(Integer uid) throws Exception {
		
		return session.selectList(namespace  + ".viewBookmark", uid);
	}
}

package com.itube.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itube.domain.Criteria;
import com.itube.domain.NoticeVO;
import com.itube.persistence.NoticeDAOImpl;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Inject
	private NoticeDAOImpl dao;
	
	@Override
	public NoticeVO readPost(Integer nid) throws Exception {
		return dao.read(nid);
	}

	@Override
	public void writePost(NoticeVO nvo) throws Exception {
		dao.insert(nvo);
	}

	@Override
	public void modifyPost(NoticeVO nvo) throws Exception {
		dao.update(nvo);
	}

	@Override
	public void removePost(Integer nid) throws Exception {
		dao.delete(nid);
	}

	@Override
	public List<NoticeVO> listCriteria(Criteria cri) throws Exception {
		System.out.println("NoticeServiceImpl 호출 성공");
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

}

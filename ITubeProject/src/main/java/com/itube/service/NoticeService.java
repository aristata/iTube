package com.itube.service;

import java.util.List;

import com.itube.domain.Criteria;
import com.itube.domain.NoticeVO;

public interface NoticeService {

	public NoticeVO readPost(Integer nid) throws Exception;
	public void writePost(NoticeVO nvo) throws Exception;
	public void modifyPost(NoticeVO nvo) throws Exception;
	public void removePost(Integer nid) throws Exception;
	public List<NoticeVO> listCriteria(Criteria cri) throws Exception;
	public int listCountCriteria(Criteria cri) throws Exception;
}

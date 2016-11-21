package com.itube.persistence;

import java.util.List;

import com.itube.domain.Criteria;
import com.itube.domain.NoticeVO;

public interface NoticeDAO {

	public NoticeVO read(Integer nid) throws Exception;
	public void insert(NoticeVO nvo) throws Exception;
	public void update(NoticeVO nvo) throws Exception;
	public void delete(Integer nid) throws Exception;
	public List<NoticeVO> listAll() throws Exception;
	public List<NoticeVO> listPage(int page) throws Exception;
	public List<NoticeVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri) throws Exception;
}

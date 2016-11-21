package com.itube.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itube.domain.ReplyVO;
import com.itube.persistence.PostDAOImpl;
import com.itube.persistence.ReplyDAOImpl;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAOImpl dao;
	
	@Inject
	private PostDAOImpl pdao;
		
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.insert(vo);
		pdao.updateReplyCnt(vo.getPid(), 1);
	}

	@Override
	public List<ReplyVO> listReply(Integer pid) throws Exception {
		return dao.listAll(pid);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);

	}

	@Override
	public void removeReply(Integer rid) throws Exception {
		int pid = dao.getPid(rid);
		dao.delete(rid);
		pdao.updateReplyCnt(pid, -1);

	}

	@Override
	public void upReply(Integer rid) throws Exception {
		dao.up(rid);
		
	}

	@Override
	public void downReply(Integer rid) throws Exception {
		dao.down(rid);
		
	}
	
}

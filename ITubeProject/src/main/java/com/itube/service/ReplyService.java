package com.itube.service;

import java.util.List;

import com.itube.domain.ReplyVO;


public interface ReplyService {
	
	public void addReply(ReplyVO vo) throws Exception;

	public List<ReplyVO> listReply(Integer pid) throws Exception;

	public void modifyReply(ReplyVO vo) throws Exception;

	public void removeReply(Integer rid) throws Exception;
	
	public void upReply(Integer rid) throws Exception;
	
	public void downReply(Integer rid) throws Exception;
	
	
}
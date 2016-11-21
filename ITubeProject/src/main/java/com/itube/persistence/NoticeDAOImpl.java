package com.itube.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itube.domain.Criteria;
import com.itube.domain.NoticeVO;

@Repository("NoticeDAO")
public class NoticeDAOImpl implements NoticeDAO {

	@Inject
	private SqlSession session;
	
	public static final String namespace = "com.itube.mappers.NoticeMapper";
	
	
	@Override
	public NoticeVO read(Integer nid) throws Exception {
		return session.selectOne(namespace+".read", nid);
	}

	@Override
	public void insert(NoticeVO nvo) throws Exception {
		session.insert(namespace+".insert", nvo);
	}

	@Override
	public void update(NoticeVO nvo) throws Exception {
		session.update(namespace+".update", nvo);
	}

	@Override
	public void delete(Integer nid) throws Exception {
		session.delete(namespace+".delete", nid);

	}
	@Override
	public List<NoticeVO> listAll() throws Exception {
		return session.selectList(namespace + "listAll");
	}

	@Override
	public List<NoticeVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}

		page = (page - 1) * 10;

		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<NoticeVO> listCriteria(Criteria cri) throws Exception {
		System.out.println("NoticeDAOImpl 호출 성공");
		return session.selectList(namespace + ".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging", cri);
	}

}

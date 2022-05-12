package kr.or.blackpink.service;

import org.eclipse.jdt.internal.compiler.ast.TrueLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.blackpink.mapper.TX1Mapper;
import kr.or.blackpink.mapper.TX2Mapper;

@Service
public class GandangServiceImpl implements GandangService {
	
	@Autowired
	private TX1Mapper tx1mapper;
	
	@Autowired
	private TX2Mapper tx2mapper;
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT,
					readOnly = true, propagation = Propagation.REQUIRED)
	public void ins2TB() {
		
		tx2mapper.insTx2("나 오늘 에너지 완전 넘침 100자 넘나?");
		tx1mapper.insTx1("나 오늘 에너지 하나도 안 넘침"); // 실패시킬 트랜잭션 

	}

}

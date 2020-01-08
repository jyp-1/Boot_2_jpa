package com.iu.b1.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public void NoticeList()throws Exception {
		noticeRepository.findAllByOrderByNumDesc();
	}
	
	
	
	
}

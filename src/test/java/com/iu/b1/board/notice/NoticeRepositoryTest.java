package com.iu.b1.board.notice;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysql.cj.protocol.x.Notice;

@SpringBootTest
@Transactional
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;

	//@Test
	void SelectTest() {

		// List<NoticeVO> ar = noticeRepository.findAll();
		// for(NoticeVO noticeVO:ar) {
		// System.out.println(noticeVO.getWriter());
		// System.out.println(noticeVO.getTitle());
		// }

		//NoticeVO noticeVO = noticeRepository.findById(6).get();
		//System.out.println(noticeVO.getWriter());
		//for (NoticeFilesVO noticeFilesVO : noticeVO.getNoticeFilesVOs()) {
		//	System.out.println(noticeFilesVO.getFname());
		//	System.out.println(noticeFilesVO.getOname());
		//}

	}

	@Test
	void ListTest() throws Exception {
		
		/*List<NoticeVO> ar = noticeRepository.findAllByOrderByNumDesc();
		for(NoticeVO noticeVO:ar) {
			System.out.println(noticeVO.getTitle());
		}*/
		
		
		List<NoticeVO> ar = noticeRepository.findByNumGreaterThanOrderByNumDesc(0);
		for(NoticeVO noticeVO:ar) {
			System.out.println(noticeVO.getTitle());
		}
		
		
		
		
		
	}
}




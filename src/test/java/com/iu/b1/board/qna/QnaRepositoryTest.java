package com.iu.b1.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
//@Transactional
class QnaRepositoryTest {

	@Resource
	private QnaRepository qnaRepository;
	@Autowired
	private QnaService qnaService;

	
	
	@Test
	void colTest()throws Exception{
	List<Object[]> obj = qnaRepository.qnaSelect(2);
		for(Object[] o:obj) {
			for(Object o2:o)
			System.out.println(o2);
		}
	}
	
	
	
	
	
	//@Test
	void updateTest()throws Exception{
		qnaService.qnaUpdate();
	}
	
	
	
	//@Test
	public void SelectOneTest()throws Exception{
	QnaVO qnaVO = qnaRepository.findQnaVO(2);
	System.out.println(qnaVO.getTitle());
	System.out.println(qnaVO.getQnaFilesVOs().get(0).getFname());
	}
	
	//@Test
	void QnaWriteTest() throws Exception {
		QnaVO qnaVO = new QnaVO();

		qnaVO.setTitle("choa2");
		qnaVO.setWriter("choa2");
		qnaVO.setContents("choa2");

		List<QnaFilesVO> ar = new ArrayList<QnaFilesVO>();

		QnaFilesVO qnaFilesVO = new QnaFilesVO();
		qnaFilesVO.setFname("choa2f.jpg");
		qnaFilesVO.setOname("choa2o.jpg");
		qnaFilesVO.setQnaVO(qnaVO);
		ar.add(qnaFilesVO);

		qnaFilesVO = new QnaFilesVO();
		qnaFilesVO.setFname("choa2f2.jpg");
		qnaFilesVO.setOname("choa2o2.jpg");
		qnaFilesVO.setQnaVO(qnaVO);
		ar.add(qnaFilesVO);

		qnaVO.setQnaFilesVOs(ar);
		qnaVO = qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		qnaRepository.save(qnaVO);
		System.out.println(qnaVO.getNum());
		System.out.println(qnaVO.getTitle());
	}

	// @Test
	public void selectTest() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("ref").descending().and(Sort.by("step").ascending()));
		List<QnaVO> ar = qnaRepository.findByNumGreaterThan(0, pageable);
		for (QnaVO qnaVO : ar) {
			System.out.println(qnaVO.getTitle());
		}
	}

	//@Test
	void SelectTest() throws Exception {
		List<QnaVO> ar = qnaRepository.findQnas();
		for (QnaVO qnaVO : ar) {
			System.out.println(qnaVO.getTitle());
			for (QnaFilesVO qnaFilesVO : qnaVO.getQnaFilesVOs()) {
				System.out.println(qnaFilesVO.getFname());
			}
		}
	}

}

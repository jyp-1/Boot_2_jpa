package com.iu.b1.board.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<NoticeVO, Integer>{
	
	
	//select * from Notice order by num desc
	public List<NoticeVO> findAllByOrderByNumDesc()throws Exception;
	//select * from Notice where num > 0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(int num)throws Exception;

	public Page<NoticeVO> findAll(Pageable pageable);
	
	//public Page<NoticeVO> findByTitleContainingAndGreaterThan(int num, String title,Pageable pageable)throws Exception;
	
	//public Page<NoticeVO> findByContentsContainingAndGreaterThan(int num,String contents, Pageable pageable)throws Exception;
	
	//public Page<NoticeVO> findByWriterContainingAndGreaterThan(int num, String writer,Pageable pageable)throws Exception;
	
	
	
}

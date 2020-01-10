package com.iu.b1.board.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iu.b1.util.FIlePathGenerator;
import com.iu.b1.util.FileSaver;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private FIlePathGenerator fIlePathGenerator;
	@Autowired
	private FileSaver fileSaver;

	public Page<NoticeVO> boardList(Pageable pageable) throws Exception {

	
			return noticeRepository.findAll(pageable);
		

	}

	public NoticeVO boardSelect(Integer num) throws Exception {
		Optional<NoticeVO> opt = noticeRepository.findById(num);
		NoticeVO noticeVO = opt.get();
		return noticeVO;
	}

	/*
	 * public void boardWrite(NoticeVO noticeVO, MultipartFile[] files) throws
	 * Exception {
	 * 
	 * List<NoticeFilesVO> noticeFilesVOs = new ArrayList<NoticeFilesVO>(); if
	 * (files != null) { for (int i = 1; i < files.length; i++) {
	 * 
	 * File file = fIlePathGenerator.getUseClassPathResource("notice"); String
	 * fileName = fileSaver.save(file, files[i]); System.out.println("fileName: " +
	 * fileName); NoticeFilesVO noticeFilesVO = new NoticeFilesVO();
	 * noticeFilesVO.setFname(fileName);
	 * noticeFilesVO.setOname(files[i].getOriginalFilename());
	 * noticeFilesVOs.add(noticeFilesVO); noticeFilesVO.setNoticeVO(noticeVO);
	 * noticeVO.setNoticeFilesVOs(noticeFilesVOs);
	 * 
	 * } } noticeRepository.save(noticeVO); }
	 */

	public void boardWrite(NoticeVO noticeVO, List<MultipartFile> files) throws Exception {

		List<NoticeFilesVO> noticeFilesVOs = null;
		// 파일의 유무를 검증

		boolean check = false;

		if (files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				if (multipartFile.getSize() > 0) {
					check = true;
					break;

				}
			} // for문1 끝
			if (check) {
				noticeFilesVOs = new ArrayList<NoticeFilesVO>();
				for (MultipartFile multipartFile : files) {
					if (multipartFile.getSize() > 0) {
						NoticeFilesVO noticeFilesVO = new NoticeFilesVO();
						File file = fIlePathGenerator.getUseClassPathResource("notice");
						String filename = fileSaver.save(file, multipartFile);
						noticeFilesVO.setFname(filename);
						noticeFilesVO.setOname(multipartFile.getOriginalFilename());
						noticeFilesVOs.add(noticeFilesVO);
						noticeFilesVO.setNoticeVO(noticeVO);
					}
				} // for문2 끝

				noticeVO.setNoticeFilesVOs(noticeFilesVOs);

			}
		}
		noticeRepository.save(noticeVO);
	} // 끝

}

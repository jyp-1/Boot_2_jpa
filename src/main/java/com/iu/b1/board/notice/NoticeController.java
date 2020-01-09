package com.iu.b1.board.notice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	@Resource(name = "noticeService")
	private NoticeService noticeService;


	@ModelAttribute(name = "board") // jsp로 보내주는거 board라는 속성을 notice로
	public String getBoard() {
		return "notice";
	}

	@GetMapping("noticeList")
	public ModelAndView noticeList(NoticeVO noticeVO, Pageable pageable) throws Exception {
		ModelAndView mv = new ModelAndView();

		Page<NoticeVO> ar = noticeService.boardList(noticeVO, pageable);
		
		mv.addObject("list", ar.getContent());
		mv.setViewName("board/boardList");

		System.out.println("aaaaaa"+ar.getTotalElements());
		System.out.println(ar.getTotalPages());
		System.out.println(ar.getSize());
		System.out.println(ar.getNumber());
		System.out.println(ar.getNumberOfElements());
		System.out.println("aaaaaa"+ar.getSort());

		return mv;
	}

	// my Select
	@GetMapping("noticeSelect")
	public ModelAndView noticeSelect(@RequestParam(defaultValue = "0") Integer num) throws Exception {
		ModelAndView mv = new ModelAndView();
		NoticeVO noticeVO = noticeService.boardSelect(num);

		mv.addObject("vo", noticeVO);
		mv.setViewName("board/boardSelect");

		return mv;
	}

	@GetMapping("noticeWrite")
	public ModelAndView noticeWrite(NoticeVO noticeVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", noticeVO);
		mv.setViewName("board/boardWrite");
		return mv;
	}

	/*
	 * @PostMapping("noticeWrite") public ModelAndView noticeWrite(NoticeVO
	 * noticeVO, MultipartFile[] files) throws Exception { ModelAndView mv = new
	 * ModelAndView(); noticeService.boardWrite(noticeVO, files);
	 * 
	 * String message = "Write Success"; String path = "./noticeList";
	 * 
	 * mv.addObject("message", message); mv.addObject("path", path);
	 * mv.setViewName("common/result");
	 * 
	 * return mv; }
	 */

	@PostMapping("noticeWrite")
	public ModelAndView noticeWrite(NoticeVO noticeVO, List<MultipartFile> files) throws Exception {
		ModelAndView mv = new ModelAndView();
		files.remove(0);
		noticeService.boardWrite(noticeVO, files);
		String message = "Write Success";
		String path = "./noticeList";
		mv.addObject("message", message);
		mv.addObject("path", path);
		mv.setViewName("common/result");
		return mv;
	}

}

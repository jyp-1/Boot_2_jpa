package com.iu.b1.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;

	/*
	 * @GetMapping("memberFileDown") public ModelAndView
	 * memberFileDown(MemberFilesVO memberFilesVO) throws Exception { ModelAndView
	 * mv = new ModelAndView(); List<MemberFilesVO> ar =
	 * memberService.memberFilesSelect(memberFilesVO);
	 * 
	 * if (ar.size() != 0) { mv.addObject("memberfiles", ar); mv.addObject("path",
	 * "upload"); mv.setViewName("fileDown"); } else { mv.addObject("message",
	 * "no Image"); mv.addObject("path", "./myPage");
	 * mv.setViewName("common/result"); } return mv; }
	 */

	
	@ModelAttribute
	public MemberVO getMemberVO() throws Exception {
		return new MemberVO();
	}
	
	
	
	
	
	
	
	@PostMapping("memberIdCheck")
	@ResponseBody//이 결과를 json으로 보내줌 
	public Boolean memberIdCheck(String id)throws Exception{
	 return memberService.memberIdCheck(id);
		
	}
	
	
	
	
	@GetMapping("memberUpdate")
	public void memberUpdate(HttpSession session, Model model)throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		
	}

	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO, BindingResult bindingResult, MultipartFile files, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView(); 
		MemberVO loginVO = (MemberVO)session.getAttribute("member");
		memberVO.setMemberFilesVO(loginVO.getMemberFilesVO());
		
		memberVO = memberService.memberUpdate(memberVO,files);
			
		session.setAttribute("member", memberVO);
			
		String message = "Update success";
		String path = "../";
			
		mv.addObject("message", message);
		mv.addObject("path", path);
		mv.setViewName("common/result");
		return mv; 
	}
	
	
	
	@GetMapping("memberJoin")
	public String memberJoin(/* Model model */) throws Exception {
		/*
		 * MemberVO memberVO = new MemberVO model.addAttribute("memberVO", new
		 * MemberVO());
		 */
		return "member/memberJoin";

	}

	// valid 검증 후 그 뒤에 바로 bindingResult 써줘야함(순서 중요)

	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO, BindingResult bindingResult, MultipartFile files)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		if (memberService.memberJoinValidate(memberVO, bindingResult)) {
			mv.setViewName("member/memberJoin");
		} else {
			memberService.memberJoin(memberVO, files);

			String message = "Join Success";
			String path = "../";

			mv.setViewName("common/result");
			mv.addObject("message", message);
			mv.addObject("path", path);
		}
		return mv;

	}

	@GetMapping("memberDelete")
	public ModelAndView memberDelete(HttpSession session) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		ModelAndView mv = new ModelAndView();
		if (memberVO.getId() == null) {
			mv.setViewName("./");
		} else {
			memberService.memberDelete(memberVO);
			session.invalidate();
			String message = "Delete Success";
			String path = "../";

			mv.setViewName("common/result");
			mv.addObject("message", message);
			mv.addObject("path", path);
		}
		return mv;
	}

	@GetMapping("memberLogin")
	public void memberLogin() throws Exception {

	}

	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.memberLogin(memberVO);

		String message = "Login Fail";
		if (memberVO != null) {
			message = "Login Success";
			session.setAttribute("member", memberVO);
		}
		mv.addObject("message", message);
		mv.addObject("path", "../");
		mv.setViewName("common/result");

		return mv;
	}

	@GetMapping("myPage")
	public void myPage() throws Exception {

	}

	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:../";
	}

}

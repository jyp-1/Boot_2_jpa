package com.iu.b1.member;

import java.io.File;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.iu.b1.util.FIlePathGenerator;
import com.iu.b1.util.FileSaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberFilesRepository memberFilesRepository;
	@Autowired
	private FIlePathGenerator fIlePathGenerator;
	@Autowired
	private FileSaver fileSaver;

	public void memberUpdate(MemberVO memberVO) throws Exception {
		memberRepository.save(memberVO);
	}

	public void memberDelete(MemberVO memberVO) throws Exception {
		memberRepository.deleteById(memberVO.getId());
	}

	public Boolean memberIdCheck(String id) throws Exception {
		return memberRepository.existsById(id);
	}

	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());

	}

	/*
	 * public List<MemberFilesVO> memberFilesSelect(MemberFilesVO
	 * memberFilesVO)throws Exception{ List<MemberFilesVO> ar =
	 * memberFilesRepository.findById(memberFilesVO.getFname()); return ar; }
	 */
	public void memberJoin(MemberVO memberVO, MultipartFile files) throws Exception {

		File file = fIlePathGenerator.getUseClassPathResource("member");
		String filename = fileSaver.save(file, files);

		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setFname(filename);
		memberFilesVO.setOname(files.getOriginalFilename());
		memberVO.setMemberFilesVO(memberFilesVO);
		memberFilesVO.setMemberVO(memberVO);
		memberFilesRepository.save(memberFilesVO);

	}

	public MemberVO memberUpdate(MemberVO memberVO, MultipartFile files) throws Exception {

		if (files.getSize() > 0) {
			File file = fIlePathGenerator.getUseClassPathResource("member");
			String filename = fileSaver.save(file, files);

			MemberFilesVO memberFilesVO = memberVO.getMemberFilesVO();
			memberFilesVO.setFname(filename);
			memberFilesVO.setOname(files.getOriginalFilename());

			memberVO.setMemberFilesVO(memberFilesVO);
			memberFilesVO.setMemberVO(memberVO);
		}

		return memberRepository.save(memberVO);
	}

	public boolean memberJoinValidate(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check = false; // true면 에러 , false면 ok
		// annotation 검증결과
		if (bindingResult.hasErrors()) {
			check = true;
		}

		// pw가 일치하는지 검증

		if (!memberVO.getPw().equals(memberVO.getPw2())) {
			check = true; // form의 path명, properties의 키
			bindingResult.rejectValue("pw2", "memberVO.pw.notEqual");
		}

		Boolean ch = memberRepository.existsById(memberVO.getId());

		if (ch) {
			check = true;
			bindingResult.rejectValue("id", "memberVO.id.notnull");
		}

		return check;
	}

	/*
	 * public boolean memberUpdateValidate(MemberVO memberVO, BindingResult
	 * bindingResult) throws Exception { boolean check = false;
	 * 
	 * if (bindingResult.hasErrors()) { check = true; } // pw 검증
	 * 
	 * if (!memberVO.getPw().equals(memberVO.getPw2())) { check = true;
	 * bindingResult.rejectValue("pw2", "memberVO.pw.notEqual"); }
	 * 
	 * return check; }
	 */

}

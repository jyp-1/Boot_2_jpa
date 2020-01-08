package com.iu.b1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberFilesRepository memberFilesRepository;
	
	
	
	@Test
	void UpdateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu1");
		memberVO.setPw("iu1");
		memberVO.setName("iu1Rename");
		memberVO.setEmail("iu1@ReEmail");
		
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setFnum(1);
		memberFilesVO.setFname("iu1ReImage.jsp");
		memberFilesVO.setOname("iu1Reoname.jsp");
		
		memberVO.setMemberFilesVO(memberFilesVO);
		memberFilesVO.setMemberVO(memberVO);
		
		memberRepository.save(memberVO);
		
		
		
	}
	
	
	
	
	//@Test
	void DeleteTest() {
		memberRepository.deleteById("uuu");
		
	}
	
	

	//@Test
	void InsertTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu10");
		memberVO.setPw("iu10");
		memberVO.setName("iu10");
		memberVO.setEmail("iu10@iu10");
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		
		memberFilesVO.setFname("iu10Fname.jpg");
		memberFilesVO.setOname("iu10Oname.jpg");
		
		memberVO.setMemberFilesVO(memberFilesVO);
		memberFilesVO.setMemberVO(memberVO);
		memberRepository.save(memberVO);
		//memberFilesRepository.save(memberFilesVO); Error 나옴
		
	
	}
	
	
	//@Test
	void SelectTest() {
	 Optional<MemberVO> opt = memberRepository.findById("aaa");
	 MemberVO memberVO = opt.get();
	 System.out.println(memberVO.getName());
	 System.out.println(memberVO.getEmail());
	 System.out.println(memberVO.getMemberFilesVO().getFname());
	 System.out.println(memberVO.getMemberFilesVO().getMemberVO().getId());
	}
	
	
	
	//@Test
	void test() throws Exception {
		//long count = memberRepository.count();
		//boolean check = memberRepository.existsById("admin");
		//List<MemberVO> ar = memberRepository.findAll();
		//for(MemberVO memberVO:ar) {
		//	System.out.println(memberVO.getId());
		//}
		
		/*
		 * Optional<MemberVO> op = memberRepository.findById("adminqwe ");
		 * if(op.isPresent()) { MemberVO memberVO = op.get();
		 * System.out.println(memberVO.getEmail()); }else {
		 * System.out.println("NO DATA"); }
		 */
		//save 는 insert update 둘다 함
		//MemberVO memberVO = new MemberVO();
		//memberVO.setId("iu20");
		//memberVO.setPw("iu20");
		//memberVO.setEmail("iu20@iu");
		//memberVO.setName("rename");
		//memberRepository.save(memberVO);
		
		
		//MemberVO memberVO = new MemberVO();
		//memberVO.setId("aaa");
		//memberVO.setPw("aaa");
		//memberVO.setName("aaa");
		
		//MemberVO memberVO = memberRepository.findByIdAndPw("aaa", "aaa");
		//if(memberVO==null) {
		//	System.out.println("sssssss");
		//}else {
		//	System.out.println("aaaaaaa");
		//}
		
		
		
		
	}

}

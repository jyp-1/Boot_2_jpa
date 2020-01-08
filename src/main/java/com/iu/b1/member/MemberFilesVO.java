package com.iu.b1.member;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "memberFiles")
public class MemberFilesVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fnum; 
	//private String id; //FK는 제거 해주기 (OneToOne 관계일때)
	private String fname; 
	private String oname; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private MemberVO memberVO;
	
	
	
	
	
}

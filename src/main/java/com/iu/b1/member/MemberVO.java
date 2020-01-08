package com.iu.b1.member;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "members")
public class MemberVO {

	@Id // primary key

	private String id;
	// @Column(name="password")

	private String pw;
	@Transient
	private String pw2;

	private String name;

	private String email;
	/* @OneToOne(mappedBy = "Join 하는 Entity에 선언된 자기 자신의 Entity 변수명") */
	@OneToOne(mappedBy = "memberVO", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private MemberFilesVO memberFilesVO;

}

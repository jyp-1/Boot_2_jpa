package com.iu.b1.board.qna;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;



@Entity
@Table(name = "qnaFiles")
@Data
public class QnaFilesVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fnum;
	//private int num; FK는 지워주기
	private String fname; 
	private String oname;
	
	@ManyToOne
	@JoinColumn(name = "num")
	private QnaVO qnaVO;

}

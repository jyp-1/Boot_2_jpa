package com.iu.b1.board;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@MappedSuperclass
public class BoardVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
	private int num;
	private String title;
	private String writer;
	private String contents;
	@CreationTimestamp
	private Date regDate;
	private int hit;

}

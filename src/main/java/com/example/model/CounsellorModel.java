package com.example.model;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="counsellortab")
public class CounsellorModel {
	@Id
	@Column(name="cid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;
	@Column(name="cname")
	private String cname;
	@Column(name="cemail")
	private String cemail;
	@Column(name="cpwd")
	private String cpwd;
	@Column(name="cph")
	private Integer cph;
	@CreationTimestamp
	@Column(name="createdDate")
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(name="updatedDate")
	private LocalDateTime updatedDate;
	@OneToMany(mappedBy ="counsellor", cascade=CascadeType.ALL)
	private List<EnquiryModel> students;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCpwd() {
		return cpwd;
	}
	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
	public Integer getCph() {
		return cph;
	}
	public void setCph(Integer cph) {
		this.cph = cph;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public List<EnquiryModel> getStudents() {
		return students;
	}
	public void setStudents(List<EnquiryModel> students) {
		this.students = students;
	}
}

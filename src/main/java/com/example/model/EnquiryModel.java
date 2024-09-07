package com.example.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "studenttab")
public class EnquiryModel {
	@Id
	@Column(name = "sid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;
	@Column(name = "sname") 
	private String sname;
	@Column(name = "sph")
	private Integer sph;
	@Column(name = "courses")
	private String courses;
	@Column(name = "classModes")
	private String classModes;
	@Column(name = "status")
	private String status;
	@CreationTimestamp
	@Column(name="createdDate")
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(name="updatedDate")
	private LocalDateTime updatedDate;
	
	@ManyToOne
	@JoinColumn(name="c_id")
	private CounsellorModel counsellor;

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getClassModes() {
		return classModes;
	}

	public void setClassModes(String classModes) {
		this.classModes = classModes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public CounsellorModel getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(CounsellorModel counsellor) {
		this.counsellor = counsellor;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getSph() {
		return sph;
	}

	public void setSph(Integer sph) {
		this.sph = sph;

	}
}

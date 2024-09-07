package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.EnquiryModel;

//One class cannot communicate with another class directly for loose coupling 
//hence one class will communicate with another class through interface as a mediator.
public interface IEnquiryDao extends JpaRepository<EnquiryModel, Integer> {

	@Query(value = "select count(*) from studenttab where c_id=:cid", nativeQuery = true)
	public Integer getTotalEnquiries(Integer cid);

	@Query(value = "select count(*) from studenttab where c_id=:cid and status=:status", nativeQuery = true)
	public Integer getEnquiriesByStatus(Integer cid, String status);

}

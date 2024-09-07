package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.CounsellorModel;

public interface ICounsellorDao extends JpaRepository<CounsellorModel,Integer> {
	 
	@Query(value="select * from counsellortab where cemail=:cemail and cpwd=:cpwd", nativeQuery=true)
	public CounsellorModel getCounsellorByEmailAndPwd(String cemail, String cpwd);
	
	@Query(value="select * from counsellortab where cemail=:cemail", nativeQuery=true)
	public CounsellorModel getCounsellorByEmail(String cemail);
}

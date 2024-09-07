package com.example.service;

import com.example.model.CounsellorModel;

public interface ICounsellorService {
	
	CounsellorModel getCounsellor(String cemail,String cpwd);
	boolean registerCounsellor(CounsellorModel cmodel);
		
}

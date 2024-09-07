package com.example.service;

import java.util.List;

import com.example.model.EnquiryDashboard;
import com.example.model.EnquiryModel;

public interface IEnquiryService {
	
	List<EnquiryModel> getStudents(EnquiryModel enquiryModel, Integer cid);
	boolean registerStudent(EnquiryModel enquiryModel, Integer cid);
	EnquiryDashboard getDashBoard(Integer cid);
	EnquiryModel getEnquiry(Integer eid);

}

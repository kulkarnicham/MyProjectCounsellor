package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.dao.ICounsellorDao;
import com.example.dao.IEnquiryDao;
import com.example.model.CounsellorModel;
import com.example.model.EnquiryDashboard;
import com.example.model.EnquiryModel;
import com.example.service.IEnquiryService;

@Service
public class EnquiryServiceImpl implements IEnquiryService {

	@Autowired
	private IEnquiryDao enquiryDao;
	@Autowired
	private ICounsellorDao counsellorDao;

	// view all enquires and filtered enquires
	@Override
	public List<EnquiryModel> getStudents(EnquiryModel enquiryModel, Integer cid) {

		CounsellorModel cModel = counsellorDao.findById(cid).orElseThrow();
		enquiryModel.setCounsellor(cModel);// to get all enquires based on logged in counsellor
		
		EnquiryModel filterCriteria = new EnquiryModel();
		filterCriteria.setCounsellor(cModel);
		
		if(!"".equals(enquiryModel.getClassModes())) {
			filterCriteria.setClassModes(enquiryModel.getClassModes());
		}
		
		if(!"".equals(enquiryModel.getCourses())) {
			filterCriteria.setCourses(enquiryModel.getCourses());
		}
		
		if(!"".equals(enquiryModel.getStatus())) {
			filterCriteria.setStatus(enquiryModel.getStatus());
		}
		
		// to generate dynamic query
		// (it will construct the query based on the data availability in the entity
		// object means which fields in object having non null and non empty values
		// those fields only will be added to query condition )
		Example<EnquiryModel> ex = Example.of(filterCriteria);
		return enquiryDao.findAll(ex);
	}

	// save student
	@Override
	public boolean registerStudent(EnquiryModel enquiryModel, Integer cid) {

		CounsellorModel cModel = counsellorDao.findById(cid).orElseThrow();
		enquiryModel.setCounsellor(cModel);// association for FK
		EnquiryModel savedEnquiry = enquiryDao.save(enquiryModel);

		return savedEnquiry.getSid() != null;
	}

	// retrieve dashboard
	@Override
	public EnquiryDashboard getDashBoard(Integer cid) {

		Integer totalEnq = enquiryDao.getTotalEnquiries(cid);
		Integer enrolledEnq = enquiryDao.getEnquiriesByStatus(cid, "enrolled");
		Integer lostEnq = enquiryDao.getEnquiriesByStatus(cid, "lost");
		Integer newEnq = enquiryDao.getEnquiriesByStatus(cid, "new");

		EnquiryDashboard edb = new EnquiryDashboard();
		edb.setEnrolledEnquires(enrolledEnq);
		edb.setLostEnquires(lostEnq);
		edb.setOpenEnquires(newEnq);
		edb.setTotalEnquires(totalEnq);

		return edb;
	}

	// Edit functionality
	@Override
	public EnquiryModel getEnquiry(Integer eid) {
		return enquiryDao.findById(eid).orElseThrow();
	}

}

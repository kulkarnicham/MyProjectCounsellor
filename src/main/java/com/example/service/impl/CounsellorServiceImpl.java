package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ICounsellorDao;
import com.example.model.CounsellorModel;
import com.example.service.ICounsellorService;

@Service
public class CounsellorServiceImpl implements ICounsellorService {

	@Autowired
	private ICounsellorDao counsellorDao;

	@Override
	public CounsellorModel getCounsellor(String cemail, String cpwd) {

		return counsellorDao.getCounsellorByEmailAndPwd(cemail, cpwd);
	}

	@Override
	public boolean registerCounsellor(CounsellorModel cmodel) {

		CounsellorModel model = counsellorDao.getCounsellorByEmail(cmodel.getCemail());

		if (model != null) {
			return false;
		} else {
			CounsellorModel savedCounsellor = counsellorDao.save(cmodel);
			return savedCounsellor.getCid() != null;
		}
	}

}

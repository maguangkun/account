package com.cun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cun.dao.BillEntityDao;
//import com.cun.dao.BillEntityDao;
import com.cun.entity.BillEntity;
import com.cun.service.BillEntityService;
@Service
@Transactional
public class BillEntityServiceImpl extends BaseServiceImpl<BillEntity> implements BillEntityService{
	@Autowired
	private BillEntityDao billEntityDao;
	public BillEntity findById(Integer id){
		BillEntity findById = billEntityDao.findById(id);
		return findById;
	}
}

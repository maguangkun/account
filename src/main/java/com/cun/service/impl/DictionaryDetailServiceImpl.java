package com.cun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cun.dao.DictionaryDetailDao;
import com.cun.entity.DictionaryDetail;
import com.cun.service.DictionaryDetailService;
@Service
@Transactional
public class DictionaryDetailServiceImpl extends BaseServiceImpl<DictionaryDetail> implements DictionaryDetailService{
	@Autowired
	private DictionaryDetailDao dictionaryDetailDao;
	@Override
	public List<DictionaryDetail> findAllListFinalByParentId(String id) {
		// TODO Auto-generated method stub
		return dictionaryDetailDao.findAllListFinalByParentId(id);
	}
	public DictionaryDetailServiceImpl(){
		super.baseRepository =   this.dictionaryDetailDao;
	}
}

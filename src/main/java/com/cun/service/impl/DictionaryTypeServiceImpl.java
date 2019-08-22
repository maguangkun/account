package com.cun.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cun.dao.DictionaryTypeDao;
import com.cun.entity.DictionaryType;
import com.cun.service.DictionaryTypeService;
@Service
@Transactional
public class DictionaryTypeServiceImpl extends BaseServiceImpl<DictionaryType> implements DictionaryTypeService{
	@Autowired
	private DictionaryTypeDao dictionaryTypeDao;
	@Override
	public List<DictionaryType> findAllList() {
		// TODO Auto-generated method stub
		return dictionaryTypeDao.findAllList();
	}

	@Override
	public void updateDelete_Time(Timestamp date, String update_By, Integer id) {
		// TODO Auto-generated method stub
		dictionaryTypeDao.updateDelete_Time(date, update_By, id);
	}

	@Override
	public void updateTypeName(String update_By, Integer id, String typeName) {
		// TODO Auto-generated method stub
		dictionaryTypeDao.updateTypeName(update_By, id, typeName);
	}
}

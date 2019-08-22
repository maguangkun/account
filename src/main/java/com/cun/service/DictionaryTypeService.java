package com.cun.service;

import java.sql.Timestamp;
import java.util.List;

import com.cun.entity.DictionaryDetail;
import com.cun.entity.DictionaryType;

public interface DictionaryTypeService   extends BaseApi<DictionaryType> {
	List<DictionaryType> findAllList();
	void updateDelete_Time(Timestamp date,String update_By,Integer id);
	void updateTypeName(String update_By,Integer id,String typeName);

}

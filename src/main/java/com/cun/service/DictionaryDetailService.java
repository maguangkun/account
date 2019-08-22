package com.cun.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cun.entity.BillEntity;
import com.cun.entity.DictionaryDetail;

public interface DictionaryDetailService  extends BaseApi<DictionaryDetail> {
	List<DictionaryDetail> findAllListFinalByParentId(String id);
}

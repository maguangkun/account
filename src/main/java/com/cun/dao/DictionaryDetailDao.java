package com.cun.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cun.entity.DictionaryDetail;
public interface DictionaryDetailDao extends BaseDao<DictionaryDetail>{
	@Query(nativeQuery=true,value="select p.* from dictionary_detail p where p.delete_time is null and p.dictionary_id = :id ")
	List<DictionaryDetail> findAllListFinalByParentId(@Param(value = "id")String id);
}

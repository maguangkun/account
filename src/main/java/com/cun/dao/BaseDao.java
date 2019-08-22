package com.cun.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.cun.entity.BaseEntity;
import com.cun.entity.BillEntity;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Integer>,JpaSpecificationExecutor {
	public List<T> findByDeleteTimeIsNull();
	List<T> findByDeleteTimeIsNullOrderByCreateTimeDesc();
}

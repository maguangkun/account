package com.cun.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.cun.dao.BaseDao;
import com.cun.entity.BaseEntity;
import com.cun.entity.BillEntity;
import com.cun.service.BaseApi;
import com.cun.util.ResultBean;
import com.cun.util.SqlUtil;
import com.google.common.collect.Lists;

public class BaseServiceImpl<T extends BaseEntity> implements BaseApi<T>{
	@Autowired
	public BaseDao<T> baseRepository;
	public T get(Integer id) {
		// TODO Auto-generated method stub
		return this.baseRepository.findOne(id);
	}

	public ResultBean<T> DeleteTime(T entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			return new ResultBean(new RuntimeException("数据为空,无法删除!"));
		}
		if (!this.exists(entity.getId())) {
			return new ResultBean(new RuntimeException("数据库不存在该数据,无法执行删除"));
		}
		if(entity.getDeleteTime()==null){
			return new ResultBean(new RuntimeException("getDeleteTime数据不可为空,无法执行删除"));
		}
		entity = this.baseRepository.saveAndFlush(entity);
		if (entity == null) {
			return new ResultBean(new RuntimeException("系统故障"));
		}
		return new ResultBean(entity);
	}

	public ResultBean<T> create(T entity) {
		if (entity == null) {
			return new ResultBean(new RuntimeException("数据为空,无法创建!"));
		}
		if (this.exists(entity.getId())) {
			return new ResultBean(new RuntimeException("实体id相同,无法重复创建!"));
		}
		entity = this.baseRepository.saveAndFlush(entity);
		if (entity == null) {
			return new ResultBean(new RuntimeException("系统故障"));
		}
		return new ResultBean(entity);
	}

	public ResultBean<T> update(T entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			return new ResultBean(new RuntimeException("数据为空,无法创建!"));
		}
		if (!this.exists(entity.getId())) {
			return new ResultBean(new RuntimeException("数据库不存在该数据,无法执行更新"));
		}
		entity = this.baseRepository.saveAndFlush(entity);
		if (entity == null) {
			return new ResultBean(new RuntimeException("系统故障"));
		}
		return new ResultBean(entity);
	}

	public Page<T> page(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.baseRepository.findAll(pageable);
	}

	public boolean exists(Integer id) {
		if(id==null){
			return false;
		}
		// TODO Auto-generated method stub
		return this.baseRepository.exists(id);
	}

	@Override
	public List<T> findByDeleteTimeIsNull() {
		// TODO Auto-generated method stub
		return  this.baseRepository.findByDeleteTimeIsNull();
	}

	@Override
	public List<T> findByDeleteTimeIsNullOrderByCreateTimeDesc() {
		// TODO Auto-generated method stub
		return  this.baseRepository.findByDeleteTimeIsNullOrderByCreateTimeDesc();
	}

	@Override
	public Page<T> myBasePage(Object obj, Integer page, Integer pageNum,Date startDate , Date endDate) {
		// TODO Auto-generated method stub
		Specification  querySpeci = new Specification() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = Lists.newArrayList();
				SqlUtil.getSQL(obj,criteriaBuilder,predicates,root);
				if(startDate!=null && endDate!=null){
					predicates.add(criteriaBuilder.between(root.get("createTime").as(String.class), startDate, endDate));
				}
				
				return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		Sort sort = new Sort(Sort.Direction.DESC,"createTime"); 
		Pageable pageable = new PageRequest(page-1,pageNum,sort);
		return this.baseRepository.findAll(querySpeci,pageable);
	}

}

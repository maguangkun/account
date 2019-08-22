package com.cun.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cun.entity.BaseEntity;
import com.cun.entity.BillEntity;
import com.cun.entity.DictionaryDetail;
import com.cun.entity.DictionaryType;
import com.cun.util.ResultBean;

public interface BaseApi <T extends BaseEntity>{
	
	   /**
     * 查询
     * @param id
     * @return
     */
    T get(Integer id);
    /**
     * 删除
     * @param 根据Id修改DeleteTime时间
     * @return
     */
    ResultBean<T> DeleteTime(T entity);
 
    /**
     * 创建
     * @param entity
     * @return
     */
    ResultBean<T> create(T entity);
 
    /**
     * 更新
     * @param entity
     * @return
     */
    ResultBean<T> update(T entity);
 
    /**
     * 读取所有
     * @param pageable
     * @return
     */
    Page<T> page(Pageable pageable);
 
    /**
     * 判断id是否存在
     * @param id
     * @return
     */
    boolean exists(Integer id);
    /**
     * deleteTime = null
     */
    List<T> findByDeleteTimeIsNull();
    /**
     * deleteTime = null orderBy createTime desc
     */
    List<T> findByDeleteTimeIsNullOrderByCreateTimeDesc();
    /**
     * 通用多条件分页
     */
    Page<T> myBasePage(Object obj,Integer page,Integer pageNum,Date startDate , Date endDate);
}

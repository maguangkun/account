package com.cun.dao;

import java.util.List;

import com.cun.entity.BillEntity;

public interface BillEntityDao extends BaseDao<BillEntity>{
	BillEntity findById(Integer id);
}

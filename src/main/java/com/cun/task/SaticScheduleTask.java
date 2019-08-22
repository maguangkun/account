package com.cun.task;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cun.entity.BillEntity;
import com.cun.service.impl.BillEntityServiceImpl;
import com.cun.util.ExcelUtil;
import com.cun.util.SqlUtil;
import com.google.common.collect.Lists;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
	@Autowired
	private BillEntityServiceImpl billEntityServiceImpl;
	
    //3.添加定时任务
    @Scheduled(cron = "0 59 11 1/1 * ?")//0 0 0 1/1 * ? 
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        Specification  querySpeci = new Specification() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = Lists.newArrayList();
				try {
					predicates.add(criteriaBuilder.equal(root.get("createTime").as(String.class), SqlUtil.formatDate(new Date())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		Sort sort = new Sort(Sort.Direction.DESC,"createTime"); 
        List<BillEntity> findAll = billEntityServiceImpl.baseRepository.findAll(querySpeci,sort);
        ExcelUtil.excelDownload("账单",new String[]{"创建人","收货人","货物名称","收款状态","货物重量","货物单价","货物总价","实际总价","手机号","备注","主键","创建时间","删除时间","修改时间","创建账单人","修改人"}, findAll);
    }
}
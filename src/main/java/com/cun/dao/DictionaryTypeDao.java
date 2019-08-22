package com.cun.dao;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cun.entity.DictionaryType;
public interface DictionaryTypeDao extends BaseDao<DictionaryType>{
	@Query(nativeQuery=true,value="select p.* from dictionary_type p where p.delete_time is null ")
	List<DictionaryType> findAllList();
	@Modifying
	@Query(nativeQuery=true,value="update dictionary_type p set p.delete_time = :date , p.update_by = :update_By where p.id = :id  ")
	void updateDelete_Time(@Param(value = "date") Timestamp date,@Param(value = "update_By")String update_By,@Param(value = "id")Integer id);
	@Modifying
	@Query(nativeQuery=true,value="update dictionary_type p set  p.update_by = :update_By,p.type_Name = :typeName  where p.id = :id  ")
	void updateTypeName(@Param(value = "update_By")String update_By,@Param(value = "id")Integer id,@Param(value = "typeName")String typeName);

}

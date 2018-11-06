package com.ajth.dao;

import java.util.List;

import com.ajth.domain.BaseDict;

/*
 * @author xfd
 * 字典Dao层接口
 */
public interface BaseDictDao extends BaseDao<BaseDict> {

	List<BaseDict> findByTypeCode(String dict_type_code);

}

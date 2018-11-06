package com.ajth.service;

import java.util.List;

import com.ajth.domain.BaseDict;

/*
 * @author xfd
 * 字典业务层接口
 */
public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}

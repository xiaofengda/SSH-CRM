package com.ajth.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ajth.dao.BaseDictDao;
import com.ajth.domain.BaseDict;
import com.ajth.service.BaseDictService;

/*
 * @author xfd
 * 字典业务层接口实现类
 */
@Transactional
public class BaseDictServiceImpl implements BaseDictService {
	//注入dao
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}
	

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		//调用dao
		return baseDictDao.findByTypeCode(dict_type_code);
	}

}

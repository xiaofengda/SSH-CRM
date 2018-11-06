package com.ajth.dao.impl;

import java.util.List;
import com.ajth.dao.BaseDictDao;
import com.ajth.domain.BaseDict;
/*
 * @author xfd
 * 字典Dao层接口实现类
 */
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

	/*public BaseDictDaoImpl() {
		super(BaseDict.class);
		
	}
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?",
				dict_type_code);
	}

}

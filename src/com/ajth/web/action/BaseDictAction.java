package com.ajth.web.action;




import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ajth.domain.BaseDict;
import com.ajth.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/*
 * @author xfd
 * 字典Action类
 */
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	private static final long serialVersionUID = 1L;
	
	//模型驱动使用对象
	private BaseDict baseDict = new BaseDict();
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	//注入service
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	/*
	 * 根据类型名称查询字典的方法findByTypeCode
	 */
	@SuppressWarnings("unused")
	public String findByTypeCode() throws IOException{
		//调用业务层查询
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		//将list转成JSON-----------jsonlib  fastjson
		/*
		 * JSONConfig:转JSON的配置对象
		 * JSONArray:将数组和list集合转成JSON
		 * JSONObject:将对象和Map集合转成JSON
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		
		//将JSON打印到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
		
	}
	

}

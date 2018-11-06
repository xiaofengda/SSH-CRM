package com.ajth.utils;

import java.util.UUID;

/*
 * @author xfd
 * 上传文件工具类
 */
public class UploadUitls {

	public static String getUUIDFileName(String fileName) {
		int idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	/**
	 * 目录规则:/yyyy/MM/dd
	 * 按时间生成上传文件的存储路径的思路简单,而且一般的web应用使用这种方式也可以满足需求.
	 * 如果网站访问量很大,上传文件很多时,还可以继续往下分:/yyyy/MM/dd/hh/mm..
	 * @param uuidFileName 
	 * @param uuidFileName 
	 * 
	public static String getPathByTime() {
	    StringBuilder path = new StringBuilder();
	    // 按系统当前时间计算出存储路径
	    Calendar currTime = Calendar.getInstance();
	    int year = currTime.get(Calendar.YEAR);
	    int month = currTime.get(Calendar.MONTH) + 1;
	    int day = currTime.get(Calendar.DAY_OF_MONTH);
	    // 字符串拼接
	    path.append(File.separator + year).append(File.separator + month).append(File.separator + day);
	        
	    return path.toString();
	}
	*/
	
	/*
	 * 目录规则- 两及目录：/10/1 
	 */
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;	//作为一级目录
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf; //作为二级目录
		return "/"+d1+"/"+d2;
		
	}
	

}

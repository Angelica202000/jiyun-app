package com.fire.back.service;

import java.util.List;
import java.util.Map;

import com.fire.back.entity.StoreTb;

/**
 * 
 * @Title: StoreService.java
 * @Description: TODO(描述)
 * @author Dragon
 * @date 2020-02-21 10:40:12
 */
public interface StoreService {
	List<Map<String, Object>> getListByPage(int page,int size,String field,String sort,
			String stime,String etime,String aname);
	int getListByPageCount(String stime,String etime,String name);
	Map<String, Object> getInfoById(Long activityId);
	int insertOrUpdate(StoreTb store);
	void del(String ids);
}

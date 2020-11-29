package com.fire.back.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fire.back.entity.PackTb;

/**
 * 
 * @Title: PackService.java
 * @Description: TODO(描述)
 * @author Dragon
 * @date 2020-02-21 10:40:12
 */
public interface PackService {
	List<Map<String, Object>> getListByPage(int page,int size,String field,String sort,
			String stime,String etime,String aname,int status);
	int getListByPageCount(String stime,String etime,String name,int type);
	List<Map<String, Object>> getCPackListByPage(int page,int size,String field,String sort,
			Long id);
	int getCPackListByPageCount(Long id);
	void updatePack(Long id,int status,Double weight,Double amount,String type,String logistics,String logistics_order);
	Long addPack(Long userId,String realName,String mobile,String position,String clearance,String packType,String packNo);
	int changeState(Long id,int status);
	int deletePack(Long id);
	List<PackTb> getPackListByStatus(Long userId,int status);
	PackTb getPackInfo(Long packId);
	public void exportExcel(List<Map<String,Object>> list,HttpServletResponse response) throws Exception;
	List<Map<String, Object>> getExportList(
			String stime,String etime,String aname,int status);
	
}

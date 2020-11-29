package com.fire.back.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.back.common.TimeTools;
import com.fire.back.dao.extend.ExtendStoreTbMapper;
import com.fire.back.entity.StoreTb;
import com.fire.back.entity.StoreTbExample;
import com.fire.back.service.StoreService;
@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private ExtendStoreTbMapper storeMapper;

	
	/**
	 * 分页获取活动id name
	 * @param page,size,field,sort,type,stime,etime,state
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getListByPage(int page,int size,String field,String sort,
			String stime,String etime,String name){
		Map<String,Object> params = new HashMap<>();
		String param = " where a.is_delete=0 ";
		if(stime.length()>0) {
			stime = TimeTools.getTimeStamp(stime)/1000 +"";
			param += " and a.create_time >="+stime;
		}
		if(etime.length()>0) {
			etime = TimeTools.getCircleStamp(etime)/1000 +"";
			param += " and a.create_time <"+etime;
		}
		if(name.length()>0) {
			param += " and a.store_name like '%"+name+"%'";
		}
		param += " order by "+field+" "+sort+" limit "+(page-1)*size+","+size;
		params.put("param", param);
		List<Map<String, Object>> list = storeMapper.getListByPage(params);
		return list;
	}
	
	/**
	 * count
	 * @param page,size,field,sort,type,stime,etime,state
	 * @return
	 */
	@Override
	public int getListByPageCount(String stime,String etime,String name){
		Map<String,Object> params = new HashMap<>();
		String param = " where a.is_delete=0 ";
		if(stime.length()>0) {
			stime = TimeTools.getTimeStamp(stime)/1000 +"";
			param += " and a.create_time >="+stime;
		}
		if(etime.length()>0) {
			etime = TimeTools.getCircleStamp(etime)/1000 +"";
			param += " and a.create_time <"+etime;
		}
		if(name.length()>0) {
			param += " and a.store_name like '%"+name+"%'";
		}
		params.put("param", param);
		int count = storeMapper.getListByPageCount(params);
		return count;
	}

	/**
	 * 获取活动详情
	 * @param activityId
	 * @return
	 */
	@Override
	public Map<String, Object> getInfoById(Long activityId){
		Map<String,Object> params = new HashMap<>();
		params.put("id", activityId);
		Map<String,Object> info = storeMapper.getBackInfoById(params);
		return info;
	}

	@Override
	public void del(String ids) {
		StoreTbExample example = new StoreTbExample();
		com.fire.back.entity.StoreTbExample.Criteria c = example.createCriteria();
		c.andIdEqualTo(Integer.parseInt(ids));
		StoreTb a = new StoreTb();
		a.setIsDelete(1);
		storeMapper.updateByExampleSelective(a, example);
	}
	
	@Override
	public int insertOrUpdate(StoreTb store){
		Long now = System.currentTimeMillis()/1000;
		int result = 0;if(store.getId()==null) {
			store.setIsDelete(0);
			store.setCreateTime(now);
			result = storeMapper.insertSelective(store);	
		}else {
			store.setUpdateTime(now);
			result = storeMapper.updateByPrimaryKeySelective(store);
		}
		return result;
	}
}

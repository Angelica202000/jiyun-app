package com.fire.back.controller.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fire.back.common.FireResult;
import com.fire.back.entity.StoreTb;
import com.fire.back.service.StoreService;
import com.fire.back.util.ParamUtil;

/**  
 * @Title: BackStoreController.java
 * @Description: 后端仓库管理
 * @author Dragon
 * @date 2020-07-19 10:35:14 
 */
@RequestMapping("back/store/")
@RestController
public class BackStoreController  extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(BackStoreController.class);
	
	@Autowired
	private StoreService service;
	
	@PostMapping("getList")
	@ResponseBody
    @RequiresPermissions("common:store:list")
	public FireResult getList(@RequestBody Map<String, Object> paramMap) {
		try {
			Integer page = ParamUtil.getInteger(paramMap, "page", 1);
			Integer size = ParamUtil.getInteger(paramMap, "size", 10);
			String field = ParamUtil.getString(paramMap, "field", "id");
			String sort = ParamUtil.getString(paramMap, "sort", "asc");
			String stime = ParamUtil.getString(paramMap, "stime", "");
			String etime = ParamUtil.getString(paramMap, "etime", "");
			String sname = ParamUtil.getString(paramMap, "sname", "").trim();
			List<Map<String,Object>> list = service.getListByPage(page, size, field, sort,stime, etime,sname);
			int count = service.getListByPageCount(stime, etime,sname);
			return FireResult.build(1, "数据获取成功", list,count);
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "获取信息失败，请稍后再试");
		}
	}

	@PostMapping("getInfo")
	@ResponseBody
    @RequiresPermissions("common:store:edit")
	public FireResult getInfo(@RequestBody Map<String, Object> paramMap) {
		try {
			Long id = ParamUtil.getLong(paramMap, "id", -1L);
			Map<String, Object> info = service.getInfoById(id);
			return FireResult.build(1, "数据获取成功", info);
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "获取信息失败，请稍后再试");
		}
	}

	@PostMapping("insertOrUpdate")
	@ResponseBody
	@RequiresPermissions(value={"common:store:add","common:store:edit"},logical=Logical.OR)
	public FireResult insertOrUpdate(@RequestBody StoreTb 
			entity,HttpServletRequest request) {
		try {
			service.insertOrUpdate(entity);
			return FireResult.build(1, "操作成功！");
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "操作失败，请稍后再试");
		}
	}

	@PostMapping("del")
	@ResponseBody
    @RequiresPermissions("common:store:del")
	public FireResult del(@RequestBody Map<String, Object> paramMap) {
		try {
			service.del(paramMap.get("ids")+"");
			return FireResult.build(1, "操作成功！");
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "操作失败，请稍后再试");
		}
	}

}

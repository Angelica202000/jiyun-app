package com.fire.back.controller.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fire.back.common.FireResult;
import com.fire.back.service.PackService;
import com.fire.back.util.ParamUtil;

/**  
 * @Title: BackPackController.java
 * @Description: 后端包裹管理
 * @author Dragon
 * @date 2020-07-19 10:35:14 
 */
@RequestMapping("back/pack/")
@RestController
public class BackPackController  extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(BackPackController.class);
	
	@Autowired
	private PackService service;
	
	@PostMapping("getList")
	@ResponseBody
    @RequiresPermissions("common:pack:list")
	public FireResult getList(@RequestBody Map<String, Object> paramMap) {
		try {
			Integer page = ParamUtil.getInteger(paramMap, "page", 1);
			Integer size = ParamUtil.getInteger(paramMap, "size", 10);
			String field = ParamUtil.getString(paramMap, "field", "id");
			String sort = ParamUtil.getString(paramMap, "sort", "asc");
			String stime = ParamUtil.getString(paramMap, "stime", "");
			String etime = ParamUtil.getString(paramMap, "etime", "");
			String pname = ParamUtil.getString(paramMap, "pname", "").trim();
			Integer type = ParamUtil.getInteger(paramMap, "type", -1);
			List<Map<String,Object>> list = service.getListByPage(page, size, field, sort,stime, etime,pname,type);
			int count = service.getListByPageCount(stime, etime,pname,type);
			return FireResult.build(1, "数据获取成功", list,count);
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "获取信息失败，请稍后再试");
		}
	}
	

	@PostMapping("getCPackList")
	@ResponseBody
    @RequiresPermissions("common:pack:list")
	public FireResult getCPackList(@RequestBody Map<String, Object> paramMap) {
		try {
			Integer page = ParamUtil.getInteger(paramMap, "page", 1);
			Integer size = ParamUtil.getInteger(paramMap, "size", 10);
			String field = ParamUtil.getString(paramMap, "field", "id");
			String sort = ParamUtil.getString(paramMap, "sort", "asc");
			Long id = ParamUtil.getLong(paramMap, "id");
			List<Map<String,Object>> list = service.getCPackListByPage(page, size, field, sort,id);
			int count = service.getCPackListByPageCount(id);
			return FireResult.build(1, "数据获取成功", list,count);
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "获取信息失败，请稍后再试");
		}
	}

	@PostMapping("updatePack")
	@ResponseBody
    @RequiresPermissions("common:pack:edit")
	public FireResult updatePack(@RequestBody Map<String, Object> paramMap) {
		try {
			Long id = ParamUtil.getLong(paramMap, "id");
			String type = ParamUtil.getString(paramMap, "type", "-1");
			String logistics = ParamUtil.getString(paramMap, "logistics", "-1");
			String logistics_order = ParamUtil.getString(paramMap, "logisticsOrder", "-1");
			int status = ParamUtil.getInteger(paramMap, "status", -1);
			Double weight = ParamUtil.getDouble(paramMap, "weight", -1.00);
			Double amount = ParamUtil.getDouble(paramMap, "amount", -1.00);
			service.updatePack(id, status, weight, amount,type,logistics,logistics_order);
			return FireResult.build(1, "包裹修改成功");
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "获取信息失败，请稍后再试");
		}
	}

	@PostMapping("delPack")
	@ResponseBody
    @RequiresPermissions("common:pack:del")
	public FireResult delPack(@RequestBody Map<String, Object> paramMap) {
		try {
			Long id = ParamUtil.getLong(paramMap, "id");
			service.deletePack(id);
			return FireResult.build(1, "订单删除成功");
		} catch (Exception e) {
			logger.error("",e);
			return FireResult.build(0, "获取信息失败，请稍后再试");
		}
	}
	
	@GetMapping("exportPack")
    @RequiresPermissions("common:pack:list")
	public void exportPack(HttpServletRequest req,HttpServletResponse response) {
		try {
			String stime = req.getParameter("stime");
			String etime = req.getParameter("etime");
			String pname = req.getParameter("pname").trim();
			String typeStr = req.getParameter("type");
			Integer type = -1;
			if(!"".equals(typeStr)) type = Integer.parseInt(typeStr);
			List<Map<String,Object>> list = service.getExportList(stime, etime,pname,type);
			service.exportExcel(list,response);
		} catch (Exception e) {
			logger.error("导出失败",e);
		}
	}

}

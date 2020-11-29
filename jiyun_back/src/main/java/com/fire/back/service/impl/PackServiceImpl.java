package com.fire.back.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import com.fire.back.common.TimeTools;
import com.fire.back.dao.CpackTbMapper;
import com.fire.back.dao.extend.ExtendPackTbMapper;
import com.fire.back.entity.CpackTb;
import com.fire.back.entity.CpackTbExample;
import com.fire.back.entity.PackTb;
import com.fire.back.entity.PackTbExample;
import com.fire.back.entity.PackTbExample.Criteria;
import com.fire.back.service.PackService;

@Service
public class PackServiceImpl implements PackService {
	@Autowired
	private ExtendPackTbMapper packMapper;
    @Autowired
    CpackTbMapper cm;

	@Override
	public List<Map<String, Object>> getListByPage(int page, int size, String field, String sort, String stime,
			String etime, String pname,int type) {
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
		if(pname.length()>0) {
			param += " and a.real_name like '%"+pname+"%'";
		}
		if(type>-1) {
			param += " and a.status="+type+"";
		}
		param += " order by "+field+" "+sort+" limit "+(page-1)*size+","+size;
		params.put("param", param);
		List<Map<String, Object>> list = packMapper.getListByPage(params);
		return list;
	}

	@Override
	public int getListByPageCount(String stime, String etime, String name,int status) {
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
			param += " and a.real_name like '%"+name+"%'";
		}
		if(status>-1) {
			param += " and a.status="+status+"";
		}
		params.put("param", param);
		int count = packMapper.getListByPageCount(params);
		return count;
	}

	@Override
	public void updatePack(Long id, int status, Double weight, Double amount,String type,String logistics,String logistics_order) {
		// TODO Auto-generated method stub
		PackTbExample e = new PackTbExample();
		com.fire.back.entity.PackTbExample.Criteria c = e.createCriteria();
		c.andIdEqualTo(id);
		PackTb p = new PackTb();
		if(status>-1)
			p.setStatus(status);
		if(weight>-1)
			p.setWeight(weight);
		if(amount>-1)
			p.setAmount(amount);
		if(!"-1".equals(type))
			p.setPackType(type);
		if(!"-1".equals(logistics))
			p.setLogistics(logistics);
		if(!"-1".equals(logistics_order))
			p.setLogisticsOrder(logistics_order);
		packMapper.updateByExampleSelective(p, e);
	}

	@Override
	public List<Map<String, Object>> getCPackListByPage(int page, int size, String field, String sort, Long id) {
		Map<String,Object> params = new HashMap<>();
		String param = " where a.is_delete=0 ";
		param += " and a.pack_id ="+id;
		param += " order by "+field+" "+sort+" limit "+(page-1)*size+","+size;
		params.put("param", param);
		List<Map<String, Object>> list = packMapper.getCPackListByPage(params);
		return list;
	}

	@Override
	public int getCPackListByPageCount(Long id) {
		Map<String,Object> params = new HashMap<>();
		String param = " where a.is_delete=0 ";
		param += " and a.pack_id ="+id;
		params.put("param", param);
		int count = packMapper.getCPackListByPageCount(params);
		return count;
	}

	@Override
	public Long addPack(Long userId, String realName,String mobile, String position, String clearance,String packType,String packNo) {
		// TODO Auto-generated method stub
		PackTb p = new PackTb();
		p.setUserId(userId);
		p.setRealName(realName);
		p.setMobile(mobile);
		p.setPosition(position);
		p.setClearance(clearance);
		p.setStatus(0);
		p.setIsDelete(0);
		p.setPackType(packType);
		p.setPackNo(packNo);
		p.setCreateTime(System.currentTimeMillis()/1000);
		int result = packMapper.insert(p);
		return p.getId();
	}

	@Override
	public int changeState(Long id, int status) {
		PackTbExample e = new PackTbExample();
		Criteria c = e.createCriteria();
		c.andIdEqualTo(id);
		PackTb p = new PackTb();
		p.setStatus(status);
		return packMapper.updateByExampleSelective(p, e);
	}

	@Override
	public int deletePack(Long id) {
		CpackTbExample e = new CpackTbExample();
		com.fire.back.entity.CpackTbExample.Criteria c = e.createCriteria();
		c.andPackIdEqualTo(id);
		List<CpackTb> list = cm.selectByExample(e);
		for(CpackTb cp : list) {
			cm.deleteByPrimaryKey(cp.getId());
		}
		return packMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<PackTb> getPackListByStatus(Long userId, int status) {
		PackTbExample e = new PackTbExample();
		Criteria c = e.createCriteria();
		c.andUserIdEqualTo(userId);
		c.andStatusEqualTo(status);
		e.setOrderByClause("create_time desc");
		return packMapper.selectByExample(e);
	}

	@Override
	public PackTb getPackInfo(Long packId) {
		return packMapper.selectByPrimaryKey(packId);
	}
	
	@Override
	public List<Map<String,Object>> getExportList(
			String stime,String etime,String pname,int type){
		Map<String,Object> params = new HashMap<>();
		String param = " and a.is_delete=0 ";
		if(stime.length()>0) {
			stime = TimeTools.getTimeStamp(stime)/1000 +"";
			param += " and a.create_time >="+stime;
		}
		if(etime.length()>0) {
			etime = TimeTools.getCircleStamp(etime)/1000 +"";
			param += " and a.create_time <"+etime;
		}
		if(pname.length()>0) {
			param += " and a.real_name like '%"+pname+"%'";
		}
		if(type>-1) {
			param += " and a.status="+type+"";
		}
		param += " order by a.id,c.create_time ";
		params.put("param", param);
		List<Map<String, Object>> list = packMapper.getExportList(params);
		return list;
	}

	@Override
	public void exportExcel(List<Map<String,Object>> list,HttpServletResponse response) throws Exception {
		String now = System.currentTimeMillis()+"";
        String fileName = now+".xlsx";
		String tempPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
		File temp = new File(tempPath,"temp.xlsx");
		File target = new File(tempPath,fileName);
		Files.copy(temp.toPath(), target.toPath());
		FileInputStream fileInputStream = new FileInputStream(target);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
	    XSSFFont font = workbook.createFont();
	    font.setFontHeightInPoints((short)9);
	    cellStyle.setFont(font);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int i = 2;
		for(Map<String,Object> map:list) {
		    XSSFRow row = sheet.createRow(i++);
		    XSSFCell cell = row.createCell(0);
		    cell.setCellValue(ifNull(map.get("pack_no")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(1);
		    cell.setCellValue(TimeTools.timeStamp2Second(Long.parseLong(ifNull(map.get("create_time"))+"000")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(2);
		    cell.setCellValue(ifNull(map.get("pack_type")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(3);
		    cell.setCellValue(getStatus(ifNull(map.get("status"))));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(4);
		    cell.setCellValue(ifNull(map.get("logistics")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(5);
		    cell.setCellValue(ifNull(map.get("logistics_order")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(6);
		    cell.setCellValue(ifNull(map.get("mobile")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(7);
		    cell.setCellValue(ifNull(map.get("real_name")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(8);
		    cell.setCellValue(ifNull(map.get("clearance")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(9);
		    cell.setCellValue(ifNull(map.get("position")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(10);
		    cell.setCellValue(ifNull(map.get("weight")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(11);
		    cell.setCellValue(ifNull(map.get("amount")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(12);
		    cell.setCellValue(ifNull(map.get("goos_number")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(13);
		    cell.setCellValue("");
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(14);
		    cell.setCellValue(ifNull(map.get("goods")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(15);
		    cell.setCellValue("B");
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(16);
		    cell.setCellValue(ifNull(map.get("worth")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(17);
		    cell.setCellValue("淘宝");
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(18);
		    cell.setCellValue(ifNull(map.get("pack_no")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(19);
		    cell.setCellValue(ifNull(map.get("deliver")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(20);
		    cell.setCellValue(ifNull(map.get("deliver_number")));
		    cell.setCellStyle(cellStyle);
		    cell = row.createCell(21);
		    cell.setCellValue(ifNull(map.get("buy_url")));
		    cell.setCellStyle(cellStyle);
		}
        fileInputStream.close();
        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);
        workbook.close();
        target.delete();
	}

	  //浏览器下载excel
	  public void buildExcelDocument(String filename,XSSFWorkbook workbook,HttpServletResponse response) throws Exception{
	        response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
	        OutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        outputStream.flush();
	        outputStream.close();
	    }

	  public String getStatus(String s) {
		  int state = Integer.parseInt(s);
		  if(state == 0) {
			  return "待添加";
		  }else if(state == 1) {
			  return "待称重";
		  }else if(state == 2) {
			  return "待付款";
		  }else if(state == 3) {
			  return "已付款";
		  }else if(state == 4) {
			  return "运输中";
		  }else if(state == 5) {
			  return "已完成";
		  }
		  return "";
	  }
	  
	  public String ifNull(Object obj) {
		  if(obj == null) return "";
		  return obj+"";
	  }
}

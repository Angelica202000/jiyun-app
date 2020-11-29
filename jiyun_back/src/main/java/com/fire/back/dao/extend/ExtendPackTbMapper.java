package com.fire.back.dao.extend;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fire.back.dao.PackTbMapper;

@Repository
public interface ExtendPackTbMapper extends PackTbMapper {
    List<Map<String, Object>> getListByPage(Map<String,Object> params);
    int getListByPageCount(Map<String,Object> params);
    List<Map<String, Object>> getCPackListByPage(Map<String,Object> params);
    int getCPackListByPageCount(Map<String,Object> params);
    List<Map<String, Object>> getExportList(Map<String,Object> params);
}
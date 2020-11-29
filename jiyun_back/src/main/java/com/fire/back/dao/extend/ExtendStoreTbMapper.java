package com.fire.back.dao.extend;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fire.back.dao.StoreTbMapper;

@Repository
public interface ExtendStoreTbMapper extends StoreTbMapper {
    void del(Map<String,Object> ids);
    List<Map<String, Object>> getListByPage(Map<String,Object> params);
    int getListByPageCount(Map<String,Object> params);
    Map<String, Object> getBackInfoById(Map<String,Object> params);
}
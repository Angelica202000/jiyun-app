package com.fire.back.dao;

import com.fire.back.entity.CpackTb;
import com.fire.back.entity.CpackTbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpackTbMapper {
    long countByExample(CpackTbExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CpackTb record);

    int insertSelective(CpackTb record);

    List<CpackTb> selectByExample(CpackTbExample example);

    CpackTb selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CpackTb record, @Param("example") CpackTbExample example);

    int updateByExample(@Param("record") CpackTb record, @Param("example") CpackTbExample example);

    int updateByPrimaryKeySelective(CpackTb record);

    int updateByPrimaryKey(CpackTb record);
}
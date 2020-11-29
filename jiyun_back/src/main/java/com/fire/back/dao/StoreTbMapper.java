package com.fire.back.dao;

import com.fire.back.entity.StoreTb;
import com.fire.back.entity.StoreTbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreTbMapper {
    long countByExample(StoreTbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StoreTb record);

    int insertSelective(StoreTb record);

    List<StoreTb> selectByExample(StoreTbExample example);

    StoreTb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StoreTb record, @Param("example") StoreTbExample example);

    int updateByExample(@Param("record") StoreTb record, @Param("example") StoreTbExample example);

    int updateByPrimaryKeySelective(StoreTb record);

    int updateByPrimaryKey(StoreTb record);
}
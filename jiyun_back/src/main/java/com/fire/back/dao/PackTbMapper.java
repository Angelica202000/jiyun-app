package com.fire.back.dao;

import com.fire.back.entity.PackTb;
import com.fire.back.entity.PackTbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PackTbMapper {
    long countByExample(PackTbExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PackTb record);

    int insertSelective(PackTb record);

    List<PackTb> selectByExample(PackTbExample example);

    PackTb selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PackTb record, @Param("example") PackTbExample example);

    int updateByExample(@Param("record") PackTb record, @Param("example") PackTbExample example);

    int updateByPrimaryKeySelective(PackTb record);

    int updateByPrimaryKey(PackTb record);
}
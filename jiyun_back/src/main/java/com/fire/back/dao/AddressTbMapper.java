package com.fire.back.dao;

import com.fire.back.entity.AddressTb;
import com.fire.back.entity.AddressTbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddressTbMapper {
    long countByExample(AddressTbExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddressTb record);

    int insertSelective(AddressTb record);

    List<AddressTb> selectByExample(AddressTbExample example);

    AddressTb selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddressTb record, @Param("example") AddressTbExample example);

    int updateByExample(@Param("record") AddressTb record, @Param("example") AddressTbExample example);

    int updateByPrimaryKeySelective(AddressTb record);

    int updateByPrimaryKey(AddressTb record);
}
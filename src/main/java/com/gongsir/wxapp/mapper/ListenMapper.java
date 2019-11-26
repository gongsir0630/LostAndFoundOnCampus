package com.gongsir.wxapp.mapper;

import com.gongsir.wxapp.model.Listen;
import com.gongsir.wxapp.model.ListenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ListenMapper {
    long countByExample(ListenExample example);

    int deleteByExample(ListenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Listen record);

    int insertSelective(Listen record);

    List<Listen> selectByExample(ListenExample example);

    Listen selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Listen record, @Param("example") ListenExample example);

    int updateByExample(@Param("record") Listen record, @Param("example") ListenExample example);

    int updateByPrimaryKeySelective(Listen record);

    int updateByPrimaryKey(Listen record);
}
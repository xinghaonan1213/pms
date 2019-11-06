package com.xhn.cust.bean.controller.mapper;

import com.xhn.cust.bean.Level;
import com.xhn.cust.bean.LevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LevelMapper {
    int countByExample(LevelExample example);

    int deleteByExample(LevelExample example);

    int deleteByPrimaryKey(Integer jid);

    int insert(Level record);

    int insertSelective(Level record);

    List<Level> selectByExample(LevelExample example);

    Level selectByPrimaryKey(Integer jid);

    int updateByExampleSelective(@Param("record") Level record, @Param("example") LevelExample example);

    int updateByExample(@Param("record") Level record, @Param("example") LevelExample example);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);
}
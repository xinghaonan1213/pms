package com.xhn.sys.service;

import com.xhn.sys.bean.Position;
import com.xhn.sys.bean.PositionExample;
import com.xhn.sys.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    public List<Position> getPositionList() {
      return   positionMapper.selectByExample(new PositionExample());
    }
}

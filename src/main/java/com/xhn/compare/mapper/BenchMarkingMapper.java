package com.xhn.compare.mapper;

import com.xhn.compare.bean.BenchMarking;

import java.util.List;

public interface BenchMarkingMapper {

    void saveInfo(BenchMarking benchMarking);

    List<BenchMarking> getBenchList(Integer year);
}

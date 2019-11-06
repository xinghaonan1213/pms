package com.xhn.compare.service;

import com.xhn.compare.bean.BenchMarking;

import java.util.List;

public interface BenchMarkingService {
    void saveInfo(BenchMarking benchMarking);

    List<BenchMarking> getBenchList(Integer year);
}

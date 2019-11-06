package com.xhn.compare.service;

import com.xhn.compare.bean.BenchMarking;
import com.xhn.compare.mapper.BenchMarkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenchMarkingServiceImpl implements BenchMarkingService {

        @Autowired
        private BenchMarkingMapper benchMarkingMapper;

        public void saveInfo(BenchMarking benchMarking) {
                benchMarkingMapper.saveInfo(benchMarking);
        }

        public List<BenchMarking> getBenchList(Integer year) {
               return benchMarkingMapper.getBenchList(year);
        }
}


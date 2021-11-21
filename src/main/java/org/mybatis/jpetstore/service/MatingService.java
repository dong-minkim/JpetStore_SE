package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.Mating;
import org.mybatis.jpetstore.mapper.MatingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatingService {

    private final MatingMapper matingMapper;

    public MatingService(MatingMapper matingMapper) {
        this.matingMapper=matingMapper;
    }

    public Mating getMating(int matingId) { return matingMapper.getMating(matingId);}

    public List<Mating> getMatingList(String type){
        return matingMapper.getMatingList(type);
    }

    @Transactional
    public void insertMating(Mating mating) {matingMapper.insertMating(mating);}
}

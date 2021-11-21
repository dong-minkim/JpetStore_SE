package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Mating;

import java.util.List;

public interface MatingMapper {

    Mating getMating(int matingId);

    List<Mating> getMatingList(String type);

    void insertMating(Mating mating);
}

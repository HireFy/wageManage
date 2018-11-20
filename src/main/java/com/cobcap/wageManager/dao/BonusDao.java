package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Bonus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BonusDao {
    Bonus getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Bonus bonus);

    Boolean insert(Bonus bonus);

    List<Bonus> getBounses(@Param("offset") int offset, @Param("size") int size);

    Boolean insertBounses(List<Bonus> bonusList);

    int getTotalCount();
}

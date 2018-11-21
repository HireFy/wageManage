package com.cobcap.wageManager.service;

import com.cobcap.wageManager.pojo.Bonus;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface BonusService {
    Bonus getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Bonus bonus);

    Boolean insert(Bonus bonus);

    List<Bonus> getBounses(int pageNum, int pageSize);

    Boolean insertRandomBonus(List<Integer> personIds);

    int getPageCount(int pageSize);

    float getRateByPersonId(Integer id);
}

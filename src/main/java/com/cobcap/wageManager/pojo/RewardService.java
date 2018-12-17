package com.cobcap.wageManager.pojo;

import java.util.List;
import java.util.Map;

public interface RewardService {

    Boolean deleteById(Integer id);

    Boolean insert(Reward reward);

    Boolean update(Reward reward);

    List<Reward> getRewards(int pageNum, int pageSize);

    Boolean insertRewardList(List<Reward> rewards);

    int getTotalCount();

    void generateReward();

    Map<String, Integer> getAbsenceAndOverByYearAndMonth(Integer personId, Integer year, Integer month);
}

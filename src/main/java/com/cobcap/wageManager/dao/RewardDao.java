package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Reward;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date Dec 05 3:34 PM
 **/
public interface RewardDao {
    Reward getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean insert(Reward reward);

    Boolean update(Reward reward);

    List<Reward> getRewards(@Param("offset") int offset, @Param("size") int size);

    Boolean insertRewardList(List<Reward> rewards);

    int getTotalCount();

    /**
     * 获得指定personId的所有reward记录的条数
     * 从而知道该人员从入职一共发了多少月工资
     * @param personId
     * @return
     */
    int getRecordCount(Integer personId);

    Map<String, Integer> getAbsenceAndOver(@Param("personId") Integer personId, @Param("recordDate") String recordDate);
}

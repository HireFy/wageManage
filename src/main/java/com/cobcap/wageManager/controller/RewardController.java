package com.cobcap.wageManager.controller;

import com.cobcap.wageManager.pojo.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 获得缺勤天数和加班天数
 * @Date Dec 17 3:37 PM
 **/

@RequestMapping("/reward")
@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @RequestMapping("/{personId}/{year}/{month}")
    public Map<String, Integer> getReward(@PathVariable("personId") Integer personId,
                                          @PathVariable("year") Integer year,
                                          @PathVariable("month") Integer month) {

        Map<String, Integer> map = rewardService.getAbsenceAndOverByYearAndMonth(personId, year, month);
        return map;
    }
}

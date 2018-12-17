package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.dao.RewardDao;
import com.cobcap.wageManager.pojo.Reward;
import com.cobcap.wageManager.pojo.RewardService;
import com.cobcap.wageManager.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description
 * @Date Dec 05 4:23 PM
 **/
@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardDao rewardDao;

    @Autowired
    private PersonDao personDao;


    @Override
    public Boolean deleteById(Integer id) {
        return rewardDao.deleteById(id);
    }

    @Override
    public Boolean insert(Reward reward) {
        return rewardDao.insert(reward);
    }

    @Override
    public Boolean update(Reward reward) {
        return rewardDao.update(reward);
    }

    @Override
    public List<Reward> getRewards(int pageNum, int pageSize) {
        return rewardDao.getRewards((pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public Boolean insertRewardList(List<Reward> rewards) {
        return rewardDao.insertRewardList(rewards);
    }

    @Override
    public int getTotalCount() {
        return rewardDao.getTotalCount();
    }

    @Override
    public Map<String, Integer> getAbsenceAndOverByYearAndMonth(Integer personId, Integer year, Integer month) {
        return rewardDao.getAbsenceAndOverByYearAndMonth(personId, year, month);
    }

    /**
     * 生成reward信息
     */
    public void generateReward() {
        Reward reward = new Reward();
        List<Integer> personIds = personDao.getAllId();
        /*缺勤天数*/
        int absenceDays = 0;
        /*加班天数*/
        int overTimeDays = 0;

        Random random = new Random();

        Calendar time = Calendar.getInstance();
        for (Integer id : personIds) {

            time.setTime(personDao.getById(id).getEnterTime());

            while (time.compareTo(Calendar.getInstance()) < 1) {

                absenceDays = random.nextInt(5);
                overTimeDays = random.nextInt(7);

                time.set(Calendar.DATE, 1);
                time.roll(Calendar.DATE, -1);

                reward = new Reward(id, absenceDays, overTimeDays, new Timestamp(time.getTime().getTime()));
                this.insert(reward);
//                System.out.println(reward);
                time.add(Calendar.MONTH, 1);
            }
        }
    }
}

package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.BonusDao;
import com.cobcap.wageManager.pojo.Bonus;
import com.cobcap.wageManager.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BonusServiceImpl implements BonusService {

    @Autowired
    private BonusDao bonusDao;

    @Override
    public Bonus getById(Integer id) {
        return bonusDao.getById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return bonusDao.deleteById(id);
    }

    @Override
    public Boolean updateById(Bonus bonus) {
        return bonusDao.updateById(bonus);
    }

    @Override
    public Boolean insert(Bonus bonus) {
        return bonusDao.insert(bonus);
    }

    @Override
    public List<Bonus> getBounses(int pageNum, int pageSize) {
        return bonusDao.getBounses((pageNum - 1) * pageSize, pageSize);
    }

    /*生成随机的奖金增幅*/
    public Boolean insertRandomBonus(List<Integer> personIds) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Bonus bonus;
        List<Bonus> bonusList = new ArrayList<>();
        for (Integer pId : personIds) {
            bonus = new Bonus(pId, Float.valueOf(decimalFormat.format(
                    0 + (float) Math.random() * 1.00)));

            bonusList.add(bonus);
        }

        return bonusDao.insertBounses(bonusList);
    }
}

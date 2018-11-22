package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.BonusDao;
import com.cobcap.wageManager.pojo.Bonus;
import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.BonusService;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BonusServiceImpl implements BonusService {

    @Autowired
    private BonusDao bonusDao;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PersonService personService;

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

    @Override
    public float getRateByPersonId(Integer id) {
        return bonusDao.getRateByPersonId(id);
    }

    @Override
    public int getPageCount(int pageSize) {
        int totalCount = bonusDao.getTotalCount();
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageCount += 1;
        }
        return pageCount;
    }


    /**
     * 删除一个奖金表的时候，这个人也应该级联删除
     * 数据库表设置了级联删除，删除person的时候,
     * 这个person的bonus就会跟着一起删除
     * @param id
     * @return
     */
    @Override
    public Boolean delete(Integer id) {
        Person person = bonusDao.getPersonByBonusId(id);
        return personService.deleteById(person.getId());
    }

    /**
     * 更新bonus时候，当bonus的rate更改了，
     * 当前这个person的薪资也应随着一起修改
     * @param bonus
     * @return
     */
    public Boolean update(Bonus bonus) {
        /*根据bonus id 获取 person*/
        Person person = bonusDao.getPersonByBonusId(bonus.getId());
        person.setSalary(CommonUtils.getSalary(
                placeService.getSalaryByPlaceId(person.getPlaceId()),bonus.getRate()));
        personService.updateById(person);

        return this.updateById(bonus);
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

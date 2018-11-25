package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.BonusService;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private BonusService bonusService;

    @Override
    public Person getById(Integer id) {
        return personDao.getById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return personDao.deleteById(id);
    }

    @Override
    public Boolean updateById(Person person) {
        return personDao.updateById(person);
    }

    @Override
    public Boolean insert(Person person) {
        if (person.getSalary() == null) {
            person.setSalary(placeService.getSalaryByPlaceId(person.getPlaceId()));
        }
        return personDao.insert(person);
    }

    @Override
    public Boolean isNameExist(String name) {
        return personDao.isNameExist(name);
    }

    @Override
    public List<Person> getPersons(int pageNum, int pageSize) {
        return personDao.getPersons((pageNum-1)*pageSize, pageSize);
    }

    public int getPageCount(int pageSize) {
        int totalCount = personDao.getTotalCount();
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    /*生成person*/
    public void generatePerson(int num, int min, int max) {
        Person person;
        for (int i = 0; i < num; i++) {
            String name = CommonUtils.getRandomName();
            int placeId = min + (int) (Math.random() * (max - min + 1));
            person = new Person(name, placeId);
            personDao.insert(person);
        }
    }


    /*更新person，同时根据职位的改变重新计算薪资*/
    @Override
    public Boolean update(Person person) {
        person.setSalary(CommonUtils.getSalary(
                placeService.getSalaryByPlaceId(person.getPlaceId()),
                bonusService.getRateByPersonId(person.getId())));
        return this.updateById(person);
    }

    /*生成person的salary*/
    @Override
    public void generateSalary() {
        List<Person> personList = this.getPersons(1, 2010);

        for (Person person : personList) {
            Integer id = person.getId();
            BigDecimal baseSalary = personDao.getBaseSalary(id);
            Float rate = personDao.getRate(id);
            person.setSalary(CommonUtils.getSalary(baseSalary, rate));
            this.updateById(person);
        }
    }
}

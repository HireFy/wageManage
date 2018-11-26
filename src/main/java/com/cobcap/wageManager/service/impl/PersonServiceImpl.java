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
import java.util.Random;

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
        return personDao.getPersons((pageNum - 1) * pageSize, pageSize);
    }

    public int getPageCount(int pageSize) {
        int totalCount = personDao.getTotalCount();
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageCount += 1;
        }
        return pageCount;
    }


    /**
     * 生成person
     * @param dataSize 生成person的数据量
     */
    public void generatePerson(int dataSize) {
        Person person;
        List<Integer> placeIds = placeService.getAllIds();
        Random random = new Random();
        for (int i = 0; i < dataSize; i++) {
            String name = CommonUtils.getRandomName();
            int placeId = placeIds.get(random.nextInt(placeIds.size()));
            person = new Person(name, placeId);
            personDao.insert(person);
        }
    }


    @Override
    public List<Integer> getPersonIdPageNation(int pageNum, int pageSize) {
        return personDao.getPersonIdPageNation((pageNum - 1) * pageSize, pageSize);
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
    public void generateSalary(int pageNum, int pageSize) {
        List<Person> personList = this.getPersons(pageNum, pageSize);

        for (Person person : personList) {
            Integer id = person.getId();
            BigDecimal baseSalary = personDao.getBaseSalary(id);
            Float rate = personDao.getRate(id);
            try {
                person.setSalary(CommonUtils.getSalary(baseSalary, rate));
            } catch (Exception e) {
                System.out.println("person id: " + id);
                System.out.println("baseSalary: " + baseSalary);
                System.out.println("rate: " + rate);

                e.printStackTrace();
            }
            this.updateById(person);
        }
    }
}

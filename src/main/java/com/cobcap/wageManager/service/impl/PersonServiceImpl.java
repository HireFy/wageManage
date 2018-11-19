package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.pojo.Person;
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

    /*生成person*/
    public void generatePerson() {
        Person person;
        for (int i = 0; i < 100; i++) {
            String name = CommonUtils.getRandomName();
            int placeId = 1 + (int) (Math.random() * 16);
            person = new Person(name, placeId);
            personDao.insert(person);
        }
    }

    /*生成person的salary*/
    @Override
    public void generateSalary() {
        List<Person> personList = this.getPersons(1, 100);

        for (Person person : personList) {
            Integer id = person.getId();
            BigDecimal baseSalary = personDao.getBaseSalary(id);
            Float rate = personDao.getRate(id);
            person.setSalary(BigDecimal.valueOf(baseSalary.floatValue() + (baseSalary.floatValue() * rate)));
            this.updateById(person);
        }
    }
}

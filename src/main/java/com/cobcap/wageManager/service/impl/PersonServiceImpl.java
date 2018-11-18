package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PlaceService placeService;

    @Override
    public Person getById(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return null;
    }

    @Override
    public Boolean updateById(Person person) {
        return null;
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
        return null;
    }

    @Override
    public List<Person> getPersons(int pageNum, int pageSize) {
        return null;
    }
}

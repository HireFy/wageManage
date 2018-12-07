package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.pojo.Salary;
import com.cobcap.wageManager.service.PersonService;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.service.SalaryService;
import com.cobcap.wageManager.util.CommonUtils;
import com.cobcap.wageManager.vo.PersonVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private SalaryService salaryService;

    private static int dataSize = 100;
    /*生成出生时间区间*/
    private static String beginDateBorn = "1980-01-01";
    private static String endDateBorn = "1990-12-31";

    /*生成入职时间区间*/
    private static String beginDataEnter = "2015-01-01";
    private static String endDateEnter = "2015-12-31";

    @Override
    public Person getById(Integer id) {
        return personDao.getById(id);
    }

    /**
     * 删除人的时候，对应工资表的数据清除
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Integer id) {
        salaryService.deleteByPersonId(id);
        return personDao.deleteById(id);
    }

    @Override
    public Boolean updateById(Person person) {
        return personDao.updateById(person);
    }

    /**
     * 新增person
     * @param person
     * @return
     */
    @Override
    public Boolean addUser(Person person) {
        return this.insert(person);
    }

    @Override
    public Boolean insert(Person person) {
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
        for (int i = 0; i < dataSize; i++) {
            /*获得名字和性别*/
            Map<String, Object> nameAndSex = CommonUtils.getRandomNameAndSex();
            /*获得出生日期*/
            Timestamp born = CommonUtils.getRandomDate(beginDateBorn, endDateBorn);
            /*获得年龄*/
            int age = CommonUtils.getAge(born);
            /*获得密码*/
            String pass = CommonUtils.generatePass();
            /*获得入职日期*/
            Timestamp enterTime = CommonUtils.getRandomDate(beginDataEnter, endDateEnter);
            /*获得职位*/
            int placeId = placeService.getRandomPlaceId();

            person = new Person((String)nameAndSex.get("name"), (String)nameAndSex.get("sex"),
                    age, born, pass, enterTime, placeId);

            this.insert(person);
        }
    }


    public void generatePerson() {
        this.generatePerson(dataSize);
    }


    @Override
    public Boolean isPassRight(Integer num, String pass) {
        Person person = this.getById(num);

        if (person == null) {
            return false;
        }

        if (person.getPass().equals(pass)) {
            return true;
        }
        return false;
    }

    @Override
    public String getNameById(Integer id) {
        return personDao.getNameById(id);
    }

    @Override
    public List<PersonVo> getPersonVos(int pageNum, int pageSize) {
        return this.transFormData(this.getPersons(pageNum, pageSize));
    }

    @Override
    public List<Integer> getPersonIdPageNation(int pageNum, int pageSize) {
        return personDao.getPersonIdPageNation((pageNum - 1) * pageSize, pageSize);
    }

    /*更新person,相应薪资会在新的月底刷新*/
    @Override
    public Boolean update(Person person) {
//        BigDecimal baseSalary = placeService.getSalaryByPlaceId(person.getPlaceId());
//        Float onDutyRate = person.getOnDutyRate();
//        Float overTimeRate = person.getOverTimeRate();

//        BigDecimal salary = CommonUtils.computeSalary(baseSalary, onDutyRate, overTimeRate);

//        salaryService.updateSalaryByPersonId(person.getId(), salary);

        return this.updateById(person);
    }

    /*生成person的salary*/
    @Override
    public void generateSalary(int pageNum, int pageSize) {
        List<Person> personList = this.getPersons(pageNum, pageSize);

        for (Person person : personList) {
            Integer id = person.getId();
            BigDecimal baseSalary = personDao.getBaseSalaryById(id);
//            Float rate = personDao.getRateById(id);
            try {
//                person.setSalary(CommonUtils.getSalary(baseSalary, rate));
            } catch (Exception e) {
                System.out.println("person id: " + id);
                System.out.println("baseSalary: " + baseSalary);
//                System.out.println("rate: " + rate);

                e.printStackTrace();
            }
            this.updateById(person);
        }
    }


    @Override
    public List<Person> getPersonBykw(String kw) {
        return personDao.getPersonBykw(kw);
    }

    /**
     * 生成person的Vo对象
     * @param person
     * @return PersonVo
     */
    public PersonVo transFormData(Person person) {
        PersonVo vo = new PersonVo();
        BeanUtils.copyProperties(person, vo);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        vo.setPlaceName(placeService.getPlaceNameById(vo.getPlaceId()));
        vo.setBornTimeStr(df.format(vo.getBorn()));
        vo.setEnterTimeStr(df.format(vo.getEnterTime()));

        return vo;
    }


    public List<PersonVo> transFormData(List<Person> personList) {
        List<PersonVo> personVoList = new ArrayList<>();
        for (Person person : personList) {
            personVoList.add(this.transFormData(person));
        }
        return personVoList;
    }
}

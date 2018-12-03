package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.DeptDao;
import com.cobcap.wageManager.dao.PersonDao;
import com.cobcap.wageManager.dao.PlaceDao;
import com.cobcap.wageManager.dao.SalaryDao;
import com.cobcap.wageManager.pojo.Place;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.service.SalaryService;
import com.cobcap.wageManager.vo.PlaceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.PlatformLoggingMXBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private PersonDao personDao;

    @Override
    public Place getById(Integer id) {
        return placeDao.getById(id);
    }

    /**
     * 删除职位，相关人员删除，相关工资删除
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Integer id) {
        /*工资表删除*/
        salaryDao.deleteByPlaceId(id);
        /*人员删除*/
        personDao.deleteByPlaceId(id);

        return placeDao.deleteById(id);

    }

    /**
     * place中的底薪更新的时候，刷新所有人的工资
     *
     * @param place
     * @return
     */
    @Override
    public Boolean updateById(Place place) {
        placeDao.updateById(place);
        return salaryService.updateSalary(place.getId());
    }

    @Override
    public Boolean insert(Place place) {
        return placeDao.insert(place);
    }

    @Override
    public Boolean isNameExist(String name) {
        return placeDao.isNameExist(name);
    }

    @Override
    public BigDecimal getSalaryByPlaceId(Integer id) {
        return placeDao.getSalaryByPlaceId(id);
    }

    @Override
    public List<Integer> getAllIds() {
        return placeDao.getAllIds();
    }

    @Override
    public int getPageCount(int pageSize) {
        int totalCount = placeDao.getTotalCount();
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeDao.getAllPlaces();
    }

    @Override
    public List<PlaceVo> getPlaceVos(int pageNum, int pageSize) {
        return this.transFormData(this.getPlaces(pageNum, pageSize));
    }

    @Override
    public String getPlaceNameById(Integer id) {
        return placeDao.getPlaceNameById(id);
    }

    @Override
    public List<Place> getPlaces(int pageNum, int pageSize) {
        return placeDao.getPlaces((pageNum - 1) * pageSize, pageSize);
    }


    /*Place转换placeVo*/
    public PlaceVo transFormData(Place place) {
        PlaceVo vo = new PlaceVo();
        BeanUtils.copyProperties(place, vo);

        vo.setDeptName(deptDao.getDeptNameById(vo.getDeptId()));

        return vo;
    }

    public List<PlaceVo> transFormData(List<Place> places) {
        List<PlaceVo> placeVos = new ArrayList<>();
        for (Place place : places) {
            placeVos.add(this.transFormData(place));
        }
        return placeVos;
    }


    /**
     * 获得随机的职位id
     *
     * @return
     */
    public Integer getRandomPlaceId() {
        List<Integer> placeIds = this.getAllIds();
        Random random = new Random();
        int placeId = placeIds.get(random.nextInt(placeIds.size()));
        return placeId;
    }


    public void generate() {
        Place place = new Place("运维工程师", BigDecimal.valueOf(9000), deptDao.getIdByDeptName("技术部门"));
        Place place1 = new Place("设计师", BigDecimal.valueOf(10000), deptDao.getIdByDeptName("产品部门"));
        Place place2 = new Place("编辑", BigDecimal.valueOf(8000), deptDao.getIdByDeptName("运营部门"));
        Place place3 = new Place("测试工程师", BigDecimal.valueOf(9000), deptDao.getIdByDeptName("技术部门"));
        Place place4 = new Place("活动策划", BigDecimal.valueOf(8700), deptDao.getIdByDeptName("运营部门"));
        Place place5 = new Place("架构师", BigDecimal.valueOf(100000), deptDao.getIdByDeptName("技术部门"));
        Place place6 = new Place("数据运营", BigDecimal.valueOf(9000), deptDao.getIdByDeptName("运营部门"));
        Place place7 = new Place("媒体运营", BigDecimal.valueOf(9000), deptDao.getIdByDeptName("运营部门"));
        Place place8 = new Place("前端工程师", BigDecimal.valueOf(10000), deptDao.getIdByDeptName("技术部门"));
        Place place9 = new Place("内容策划", BigDecimal.valueOf(8000), deptDao.getIdByDeptName("运营部门"));
        Place place10 = new Place("会员运营", BigDecimal.valueOf(8000), deptDao.getIdByDeptName("运营部门"));
        Place place11 = new Place("产品运营", BigDecimal.valueOf(8000), deptDao.getIdByDeptName("产品部门"));
        Place place12 = new Place("产品经理", BigDecimal.valueOf(9500), deptDao.getIdByDeptName("产品部门"));
        Place place13 = new Place("产品助理", BigDecimal.valueOf(8000), deptDao.getIdByDeptName("产品部门"));
        Place place14 = new Place("Python工程师", BigDecimal.valueOf(10000), deptDao.getIdByDeptName("技术部门"));
        Place place15 = new Place("Java工程师", BigDecimal.valueOf(12000), deptDao.getIdByDeptName("技术部门"));

        this.insert(place);
        this.insert(place1);
        this.insert(place2);
        this.insert(place3);
        this.insert(place4);
        this.insert(place5);
        this.insert(place6);
        this.insert(place7);
        this.insert(place8);
        this.insert(place9);
        this.insert(place10);
        this.insert(place11);
        this.insert(place12);
        this.insert(place13);
        this.insert(place14);
        this.insert(place15);


    }
}

package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.DeptDao;
import com.cobcap.wageManager.dao.PlaceDao;
import com.cobcap.wageManager.pojo.Place;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.vo.PlaceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.PlatformLoggingMXBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private DeptDao deptDao;

    @Override
    public Place getById(Integer id) {
        return placeDao.getById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return placeDao.deleteById(id);
    }

    @Override
    public Boolean updateById(Place place) {
        return placeDao.updateById(place);
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
}

package com.cobcap.wageManager.service.impl;

import com.cobcap.wageManager.dao.PlaceDao;
import com.cobcap.wageManager.pojo.Place;
import com.cobcap.wageManager.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Override
    public Place getById(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return null;
    }

    @Override
    public Boolean updateById(Place place) {
        return null;
    }

    @Override
    public Boolean insert(Place place) {
        return null;
    }

    @Override
    public Boolean isNameExist(String name) {
        return null;
    }

    @Override
    public BigDecimal getSalaryByPlaceId(Integer id) {
        return placeDao.getSalaryByPlaceId(id);
    }

    @Override
    public List<Place> getPlaces(int pageNum, int pageSize) {
        return null;
    }
}

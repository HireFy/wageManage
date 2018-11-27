package com.cobcap.wageManager.service;

import com.cobcap.wageManager.pojo.Place;
import com.cobcap.wageManager.vo.PlaceVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface PlaceService {
    Place getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Place place);

    Boolean insert(Place place);

    Boolean isNameExist(String name);

    BigDecimal getSalaryByPlaceId(Integer id);

    List<Place> getPlaces(int pageNum, int pageSize);

    int getPageCount(int pageSize);

    List<Integer> getAllIds();

    String getPlaceNameById(Integer id);

    List<PlaceVo> getPlaceVos(int pageNum, int pageSize);
}

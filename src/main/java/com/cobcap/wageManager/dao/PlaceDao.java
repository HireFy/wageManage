package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Person;
import com.cobcap.wageManager.pojo.Place;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PlaceDao {
    Place getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Place place);

    Boolean insert(Place place);

    Boolean isNameExist(String name);

    BigDecimal getSalaryByPlaceId(Integer id);

    List<Place> getPlaces(@Param("offset") int offset, @Param("size") int size);

    int getTotalCount();

    List<Integer> getAllIds();

    String getPlaceNameById(Integer id);

    List<Place> getAllPlaces();

    Boolean deleteByDeptId(Integer deptId);

    List<Place> getPlaceBykw(String kw);
}

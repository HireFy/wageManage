package com.cobcap.wageManager.controller;

import com.cobcap.wageManager.pojo.Place;
import com.cobcap.wageManager.service.PlaceService;
import com.cobcap.wageManager.vo.PlaceVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private static int pageSize = 10;

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<PlaceVo> getPlaces(@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return placeService.getPlaceVos(num, pageSize);
    }

    @RequestMapping("/pageCount")
    public int getPageCount() {
        return placeService.getPageCount(pageSize);
    }

    @RequestMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Integer id) {
        return placeService.deleteById(id);
    }

    @RequestMapping("/update")
    public Boolean updatePlace(@RequestBody Place place) {
        return placeService.updateById(place);
    }
}

package com.cobcap.wageManager.controller;

import com.cobcap.wageManager.pojo.Place;
import com.cobcap.wageManager.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Place> getPlaces(@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return placeService.getPlaces(num, pageSize);
    }

    @RequestMapping("/pageCount")
    public int getPageCount() {
        return placeService.getPageCount(pageSize);
    }
}

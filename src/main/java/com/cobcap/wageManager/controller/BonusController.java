package com.cobcap.wageManager.controller;

import com.cobcap.wageManager.pojo.Bonus;
import com.cobcap.wageManager.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bonus")
public class BonusController {

    private static int pageSize = 10;

    @Autowired
    private BonusService bonusService;

    @RequestMapping(value = {"/page", "/page/{num}"})
    public List<Bonus> getBonuses(@PathVariable(required = false) Integer num) {
        if(num == null)
            num = 1;
        return bonusService.getBounses(num, pageSize);
    }

    @RequestMapping("/pageCount")
    public int getPageCount() {
        return bonusService.getPageCount(pageSize);
    }

    @RequestMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Integer id) {
        return bonusService.delete(id);
    }

    @RequestMapping("/update")
    public Boolean update(@RequestBody Bonus bonus) {
        return bonusService.update(bonus);
    }
}

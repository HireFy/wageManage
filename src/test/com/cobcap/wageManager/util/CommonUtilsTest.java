package com.cobcap.wageManager.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUtilsTest {

    @Test
    public void getName() {
        CommonUtils com = new CommonUtils();
        System.out.println(CommonUtils.getRandomName());
    }

}
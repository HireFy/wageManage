package com.cobcap.wageManager.dao;

import com.cobcap.wageManager.pojo.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttendanceDao {
    Attendance getById(Integer id);

    Boolean deleteById(Integer id);

    Boolean updateById(Attendance attendance);

    Boolean insert(Attendance attendance);

    List<Attendance> getAttendances(@Param("offset") int offset, @Param("size") int size);
}

package com.wh.service;

import com.wh.entity.PageResult;
import com.wh.pojo.Teachers;

import java.util.Map;

public interface TeachersService {

    PageResult findTeacherList( Integer pageSize, Integer num, Map searchMap);


    Integer addTeacher(Teachers teachers);
}

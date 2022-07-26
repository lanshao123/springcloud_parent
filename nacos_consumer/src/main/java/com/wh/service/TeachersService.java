package com.wh.service;

import com.github.pagehelper.PageInfo;
import com.wh.entity.PageResult;
import com.wh.pojo.Teachers;

import java.util.List;
import java.util.Map;

public interface TeachersService {

    PageResult findTeacherList( Integer pageSize, Integer num, Map searchMap);


    Integer addTeacher(Teachers teachers);

    PageInfo selectPageTeacher(Integer pageSize, Integer num, Map searchMap);

    void deleteTeacher(Integer tid);

    Teachers findTeacherById(Integer tid);

    void editTeacher(Teachers teachers);
}

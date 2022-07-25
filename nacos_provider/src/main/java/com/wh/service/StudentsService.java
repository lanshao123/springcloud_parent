package com.wh.service;

import com.wh.pojo.Students;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentsService {
    void addListStudents(List<Students> students,Integer id);
}

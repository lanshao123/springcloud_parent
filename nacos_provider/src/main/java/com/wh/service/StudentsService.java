package com.wh.service;

import com.wh.pojo.Students;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentsService {
    void addListStudents(List<Students> students,Integer id);

    List<Students> getStudentListByTid(Integer tid);

    void addStudentList(List<Students> students,Integer tid);

    void deleteStudent(Integer tid);
}

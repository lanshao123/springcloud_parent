package com.wh.service.impl;

import com.wh.mapper.StudentsMapper;
import com.wh.pojo.Students;
import com.wh.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SpringBootDemo
 * @description: StudentsServiceImpl
 * @author: wangheng
 * @create: 2022-07-20 16:46
 **/
@Service
@SuppressWarnings("All")
public class StudentsServiceImpl implements StudentsService {
    @Autowired
    private StudentsMapper studentsMapper;

    @Override
    public void addListStudents(List<Students> students, Integer id) {
        studentsMapper.addListStudents(students,id);
    }

    @Override
    public List<Students> getStudentListByTid(Integer tid) {
        Students students=new Students();
        students.setTid(tid);

        return studentsMapper.select(students);
    }

    @Override
    public void addStudentList(List<Students> students,Integer tid) {
        studentsMapper.addListStudents(students,tid);
    }

    @Override
    public void deleteStudent(Integer tid) {
        Students students=new Students();
        students.setTid(tid);
        studentsMapper.delete(students);
    }
}

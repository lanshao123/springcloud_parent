package com.wh.controller;

import com.wh.pojo.Students;
import com.wh.service.StudentsService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springcloud_parent
 * @description: StudentController
 * @author: wangheng
 * @create: 2022-07-25 17:07
 **/
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentsService studentsService;
    @PostMapping("/addUser/{id}")
    public Integer getUserById(@PathVariable Integer id, @RequestBody List<Students> students){
        try {
            studentsService.addListStudents(students,id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据教师id 查询学生集合
     * @param tid 教师id教师主键 学生外键
     * @return 返回学生集合
     */
    @GetMapping("/getStudentListByTid/{tid}")
    public List<Students> getStudentListByTid(@PathVariable Integer tid) {
        return studentsService.getStudentListByTid(tid);
    }

    /**
     * 添加学生信息
     * @param tid 教师主键 学生外键
     * @param students 传入的学生对象集合
     * @return 返回 1  成功 0 失败
     */
    @PostMapping("/addStudentList/{tid}")
    public String addStudentList(@PathVariable Integer tid,@RequestBody List<Students> students){
        try {
            studentsService.addStudentList(students,tid);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 删除教师下面的所有学生 通过教师主键删除
     * @param tid  教师id
     * @return 返回 1  成功 0 失败
     */
    @DeleteMapping("/deleteStudent/{tid}")
    public String deleteStudent(@PathVariable Integer tid){
        try {
            studentsService.deleteStudent(tid);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

}

package com.wh.controller;

import com.wh.pojo.Students;
import com.wh.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springcloud_parent
 * @description: StudentController
 * @author: wangheng
 * @create: 2022-07-25 17:07
 **/
@RestController
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
}

package com.wh.controller;

import com.github.pagehelper.PageInfo;
import com.wh.entity.PageResult;
import com.wh.feign.ProviderFeign;
import com.wh.pojo.Students;
import com.wh.pojo.Teachers;
import com.wh.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: TeachersController
 * @author: wangheng
 * @create: 2022-07-20 16:36
 **/
@RestController
@RequestMapping("teachersFeign")
@SuppressWarnings("All")
@CrossOrigin
public class TeachersFeignController {
    @Autowired
    private TeachersService teachersService;

    @Autowired
    private ProviderFeign providerFeign;

    /**
     * 分页查询教师信息
     * @param pageSize 当前页码
     * @param num 每页显示条数
     * @param searchMap 查询参数
     * @return 返回分页数据和总记录数
     */
    @PostMapping("/findTeacherList/{pageSize}/{num}")
    public PageResult findTeacherList(@PathVariable Integer pageSize, @PathVariable Integer num, @RequestBody Map searchMap){
        //先分页条件查询教师
        PageInfo pageInfo = teachersService.selectPageTeacher(pageSize, num, searchMap);
        List<Teachers> list=pageInfo.getList();
        for (Teachers teachers : list) {
            teachers.setStudents(providerFeign.getStudentListByTid(teachers.getId()));
        }
        return new PageResult(pageInfo.getTotal(),list);
    }

    /**
     * 添加老师信息
     * @param teachers 老师对象 包含 学生集合
     * @return 返回 1 or 0
     */
    @PostMapping("/addTeacher")
    public Integer addTeacher(@RequestBody Teachers teachers){
        try {
            //首先添加老师
            teachersService.addTeacher(teachers);
            providerFeign.addStudentList(teachers.getId(),teachers.getStudents());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 根据教师主键来删除教师
     * @param tid 教师主键id
     * @return 返回 1 or 0
     */
    @DeleteMapping("/deleteTeacher/{tid}")
    public Integer deleteTeacher(@PathVariable Integer tid){
        try {
            //先远程调用删除学生
            providerFeign.deleteStudent(tid);
            //在删除教师
            teachersService.deleteTeacher(tid);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *   根据教师id 查询出来学生集合 在添加到 查询出来的 教师对象中
     * @param tid 教师id  主键
     * @return 返回教师对象
     */
    @GetMapping("/findTeacherById/{tid}")
    public Teachers findTeacherById(@PathVariable Integer tid){

        List<Students> studentListByTid = providerFeign.getStudentListByTid(tid);
        //查询教师信息
        Teachers teachers=teachersService.findTeacherById(tid);
        teachers.setStudents(studentListByTid);
        return teachers;
    }
    @PutMapping("/editTeacher/{tid}")
    public Integer editTeacher(@PathVariable Integer tid,@RequestBody Teachers teachers){
        try {
            //先修改教师
            teachersService.editTeacher(teachers);
            //在删除所有学生
            providerFeign.deleteStudent(tid);
            //添加学生
            providerFeign.addStudentList(tid,teachers.getStudents());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

}

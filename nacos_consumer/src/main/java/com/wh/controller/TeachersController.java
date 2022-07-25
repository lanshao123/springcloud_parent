package com.wh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wh.entity.PageResult;
import com.wh.pojo.Students;
import com.wh.pojo.Teachers;
import com.wh.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("teachers")
@SuppressWarnings("All")
@CrossOrigin
public class TeachersController {
    @Autowired
    private TeachersService teachersService;
    //从Nacos注册中心获取服务端的ip、端口、要调用的服务
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    //访问Rest服务的客户端
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 分页查询教师信息
     * @param pageSize 当前页码
     * @param num 每页显示条数
     * @param searchMap 查询参数
     * @return 返回分页数据和总记录数
     */
    @PostMapping("/findTeacherList/{pageSize}/{num}")
    public PageResult findTeacherList(@PathVariable Integer pageSize, @PathVariable Integer num, @RequestBody Map searchMap){
       return teachersService.findTeacherList(pageSize,num,searchMap);
    }

    /**
     * 添加老师信息
     * @param teachers 老师对象 包含 学生集合
     * @return
     */
    @PostMapping("addTeacher")
    public Integer addTeacher(@RequestBody Teachers teachers){
        //先添加教师在添加学生
        Integer integer = teachersService.addTeacher(teachers);
        ServiceInstance si = loadBalancerClient.choose("nacos-provider");
        //指定要调用的服务
        String url = "http://"+si.getHost()+":"+si.getPort()+"/addUser/"+teachers.getId();
        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        //设置请求数据类型
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //构建请求数据
        HttpEntity<List<Students>> entity = new HttpEntity<List<Students>>(teachers.getStudents(), headers);

        Integer integer1 = restTemplate.postForObject(url, entity, Integer.class);
        return integer1;

    }

}

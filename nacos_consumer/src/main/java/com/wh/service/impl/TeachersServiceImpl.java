package com.wh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wh.entity.PageResult;
import com.wh.mapper.TeachersMapper;
import com.wh.pojo.Teachers;
import com.wh.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: TeachersServiceImpl
 * @author: wangheng
 * @create: 2022-07-20 16:37
 **/
@Service
@SuppressWarnings("ALL")
@Transactional
public class TeachersServiceImpl implements TeachersService {
    @Autowired
    private TeachersMapper teachersMapper;

    @Override
    public PageResult findTeacherList(Integer pageSize,Integer num, Map searchMap) {
        PageHelper.startPage(pageSize,num);
        List<Teachers> list=teachersMapper.findTeacherList(searchMap.get("name")+"");
        PageInfo pageInfo=new PageInfo(list);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public Integer addTeacher(Teachers teachers) {
        try {
            teachers.setCreateTime(new Date());
            teachers.setUpdateTime(new Date());
            teachersMapper.insertSelective(teachers);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}

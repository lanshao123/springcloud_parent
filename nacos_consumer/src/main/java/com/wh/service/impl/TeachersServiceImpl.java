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
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

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

    @Override
    public PageInfo selectPageTeacher(Integer pageSize, Integer num, Map searchMap) {
        PageHelper.startPage(pageSize,num);
        Example example=new Example(Teachers.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty((String) searchMap.get("name"))){
            criteria.andLike("name","%"+searchMap.get("name")+"%");
        }
        List<Teachers> list = teachersMapper.selectByExample(example);
        return  new PageInfo(list);

    }

    @Override
    public void deleteTeacher(Integer tid) {
        teachersMapper.deleteByPrimaryKey(tid);
    }

    @Override
    public Teachers findTeacherById(Integer tid) {
        return teachersMapper.selectByPrimaryKey(tid);
    }

    @Override
    public void editTeacher(Teachers teachers) {
        teachersMapper.updateByPrimaryKeySelective(teachers);
    }


}

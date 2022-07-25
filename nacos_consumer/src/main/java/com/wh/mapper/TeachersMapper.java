package com.wh.mapper;

import com.wh.pojo.Teachers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeachersMapper extends tk.mybatis.mapper.common.Mapper<Teachers> {
       List<Teachers> findTeacherList(@Param("name") String name);
    }

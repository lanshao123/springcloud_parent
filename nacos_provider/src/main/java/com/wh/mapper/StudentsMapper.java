package com.wh.mapper;

import com.wh.pojo.Students;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentsMapper extends tk.mybatis.mapper.common.Mapper<Students> {
     void addListStudents(List<Students> students, @Param("id") Integer id);
}

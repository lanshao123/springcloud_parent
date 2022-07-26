package com.wh.feign;

import com.wh.pojo.Students;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springcloud_parent
 * @description: ProviderFeign
 * @author: wangheng
 * @create: 2022-07-26 18:05
 **/
@FeignClient("nacos-provider")
@RequestMapping("/student")
public interface ProviderFeign {
    /**
     * 根据教师id 查询学生集合
     * @param tid 教师id教师主键 学生外键
     * @return 返回学生集合
     */
    @GetMapping("/getStudentListByTid/{tid}")
    public List<Students> getStudentListByTid(@PathVariable Integer tid) ;
    /**
     * 添加学生信息
     * @param tid 教师主键 学生外键
     * @param students 传入的学生对象集合
     * @return 返回 1  成功 0 失败
     */
    @PostMapping("/addStudentList/{tid}")
    public String addStudentList(@PathVariable Integer tid,@RequestBody List<Students> students);

    /**
     * 删除教师下面的所有学生 通过教师主键删除
     * @param tid  教师id
     * @return 返回 1  成功 0 失败
     */
    @DeleteMapping("/deleteStudent/{tid}")
    public String deleteStudent(@PathVariable Integer tid);
}

package com.wh.pojo;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

public class Teachers {
  @Id
  @GeneratedValue(generator = "JDBC")
  private Integer id;
  private String name;
  private Integer age;
  private Date createTime;
  // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+0")
  private Date updateTime;
  @Transient
  private List<Students> students;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public List<Students> getStudents() {
    return students;
  }

  public void setStudents(List<Students> students) {
    this.students = students;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}

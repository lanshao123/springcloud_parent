package com.wh.pojo;


import javax.persistence.Transient;

public class Students {

  private Integer id;
  private String name;
  private Integer age;
  private Integer tid;
  @Transient
  private Teachers teachers;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Students(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public Students() {
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

  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
  }

  public Teachers getTeachers() {
    return teachers;
  }

  public void setTeachers(Teachers teachers) {
    this.teachers = teachers;
  }

  @Override
  public String toString() {
    return "Students{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", tid=" + tid +
            ", teachers=" + teachers +
            '}';
  }
}

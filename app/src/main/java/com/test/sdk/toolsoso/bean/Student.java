package com.test.sdk.toolsoso.bean;

/**
 * @author: S
 * @date: 2019/5/31 9:15
 * @description:
 */
public class Student {

    int num;
    String name;
    String sex;
    int age;

    public Student(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

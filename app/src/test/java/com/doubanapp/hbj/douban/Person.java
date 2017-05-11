package com.doubanapp.hbj.douban;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public class Person extends Person2 {
    public String name;
    public String sex;
    public int age;

    public Person(String res, int n, String sex) {
        super(res, n);
        this.sex = sex;
    }
}

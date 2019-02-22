package com.bob.android.myhttprequestexample.entity;

import java.io.Serializable;

/**
 * @package com.bob.android.myhttprequestexample.entity
 * @fileName StudentEntity
 * @Author Bob on 2019/2/21 10:44.
 * @Describe TODO
 */
public class StudentEntity implements Serializable{

    private String stuIn;
    private String stuName;
    private String stuClass;

    public String getStuIn() {
        return stuIn;
    }

    public StudentEntity setStuIn(String stuIn) {
        this.stuIn = stuIn;
        return this;
    }

    public String getStuName() {
        return stuName;
    }

    public StudentEntity setStuName(String stuName) {
        this.stuName = stuName;
        return this;
    }

    public String getStuClass() {
        return stuClass;
    }

    public StudentEntity setStuClass(String stuClass) {
        this.stuClass = stuClass;
        return this;
    }
}

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

    public void setStuIn(String stuIn) {
        this.stuIn = stuIn;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
}

package com.wp.miaoshaproject.controller.viewobject;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/5/29 20:45
 * @description 返回给前端的User模型
 **/
public class UserVO {

    /*
    供前端视图展示用的数据模型
     */

    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telphone;

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

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

}

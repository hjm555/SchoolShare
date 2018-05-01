package com.hjm.model;

public class User {
    private String id;           //用户id（用户id，由后台自动生成，唯一性）
    private String name;         //用户名（登录需要）
    private String password;     //用户登录密码
    private String phoneNumber;  //用户手机号码
    private String sex;          //用户性别
    private String url;          //用户头像对应的图片URL链接

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

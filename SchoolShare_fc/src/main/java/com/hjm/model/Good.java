package com.hjm.model;

import java.util.Date;
import java.util.List;

public class Good {
    private String id;            //物品的id（由后台自动生成，唯一性）
    private String name;          //物品名称
    private String type;          //物品种类
    private String description;   //物品描述
    private String state;         //物品的共享状态
    private List<String> urls;    //物品图片的URL链接
    private Date releaseTime;     //物品发布时间
    private User owner;           //物品的主人用户
    private User borrower;        //获得物品共享资格的用户

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }
}

package com.hjm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/wenhou")
public class LaLa {
    private String jiyu;
    private Date time;
    private String author;

    public String getJiyu() {
        return jiyu;
    }

    public void setJiyu(String jiyu) {
        this.jiyu = jiyu;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @RequestMapping("/lvduizhang")
    public void lvduizhang(HttpServletRequest request){
        LaLa la=new LaLa();
        Date time1=new Date();
        la.setTime(time1);
        la.setAuthor("咖啡");
        la.setJiyu("绿队长晚上好啊.");
        System.out.println(jiyu);
        System.out.println("来自:"+la.getAuthor());
        System.out.println("时间:"+la.getTime());
    }

    @RequestMapping("/lala")
    public void lala(HttpServletRequest request){
        LaLa la=new LaLa();
        Date time1=new Date();
        la.setTime(time1);
        la.setAuthor("咖啡");
        la.setJiyu("I LOVE YOU.");
        System.out.println(jiyu);
        System.out.println("来自:"+la.getAuthor());
        System.out.println("时间:"+la.getTime());
    }

    @RequestMapping("/lvxi")
    public void lvxi(HttpServletRequest request){
        LaLa la=new LaLa();
        Date time1=new Date();
        la.setTime(time1);
        la.setAuthor("咖啡");
        la.setJiyu("祝绿汐和绿队长白头偕老.");
        System.out.println(jiyu);
        System.out.println("来自:"+la.getAuthor());
        System.out.println("时间:"+la.getTime());
    }

    @RequestMapping("/bozai")
    public void bozai(HttpServletRequest request){
        LaLa la=new LaLa();
        Date time1=new Date();
        la.setTime(time1);
        la.setAuthor("咖啡");
        la.setJiyu("波仔晚上好啊.哈哈.");
        System.out.println(jiyu);
        System.out.println("来自:"+la.getAuthor());
        System.out.println("时间:"+la.getTime());
    }
}

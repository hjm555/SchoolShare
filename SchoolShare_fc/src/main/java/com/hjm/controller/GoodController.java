package com.hjm.controller;


import com.hjm.model.Good;
import com.hjm.model.User;
import com.hjm.service.GoodPictureService;
import com.hjm.service.GoodService;
import com.hjm.utils.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/good")
public class GoodController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private GoodPictureService goodPictureService;

    @RequestMapping("/homePage")
    @ResponseBody
    public Map homePage(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        List<Good> goodList=goodService.homePage();
        return ReturnMap.success(goodList);
    }

    @RequestMapping("/release")
    @ResponseBody
    public Map insert(@RequestBody Good good,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");
        good.setId(UUID.randomUUID().toString());
        goodService.insert(good);
        return ReturnMap.success("成功发布物品.");
    }

    @RequestMapping("/update")
    @ResponseBody
    //该方法负责物品相关信息（如物品名，类型，描述等）的更新
    public Map update(@RequestBody Good good, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");
        goodService.update(good);
        return ReturnMap.success(good);
    }

    //pictureUpload方法负责单张物品图片的上传
    @RequestMapping("/pictureUpload")
    @ResponseBody
    public Map pictureUpload(@RequestParam MultipartFile file,@RequestBody Good good,HttpServletRequest request)throws IOException {

        //用户登录状态检测
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        //获得上传文件的全名，在全名中识别出文件类型，根据文件类型由系统生成一个全新的文件名（防止重复）
        // 把文件名保存在newFileName字符串中
        String fileFullName = file.getOriginalFilename();
        int index = fileFullName.lastIndexOf(".");
        String type = fileFullName.substring(index+1, fileFullName.length());
        String newFileName = UUID.randomUUID().toString()+"."+type;

        //新建文件target，指定其路径（URL）
        File target = new File("../resources/"+"/good/"+good.getId()+"/"+newFileName);
        //如果新文件所需的目录不存在，则创建它
        if (!target.getParentFile().exists())
            target.getParentFile().mkdirs();
        //把上传的文件写入target
        file.transferTo(target);
        //使用字符串url保存新文件的URL
        String url="/resources/good/"+good.getId()+"/"+newFileName;
        //根据前端发送过来的good对象，在物品表中查询它的完整信息，并赋值给good
        //good = goodService.getOne(good);
        //在good对象的urls中添加新的String对象，并把url赋值给它
        //good.getUrls().add(url);
        //更新物品表
        //goodService.update(good);
        //在物品图片表中插入新图片的相关数据
        goodPictureService.insert(url,good);
        //返回物品对象
        return ReturnMap.success(goodService.getOne(good));
    }

    //pictureDelete方法负责单张物品图片的删除
    @RequestMapping("/pictureDelete")
    @ResponseBody
    public Map pictureDelete(@RequestParam String url,@RequestBody Good good, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");
        //先根据url删掉图片文件
        File oldPicture=new File("../"+url);
        if(oldPicture.exists()){oldPicture.delete();}
        //删除物品图片表中对应的一条记录
        goodPictureService.delete(url);
        //调用goodService的getOne方法获得一个含有全新urls的good对象并返回给前端
        return ReturnMap.success(goodService.getOne(good));
    }

    //searchByName方法负责提供所有名称与搜索内容相近的物品，并按发布时间的先后顺序返回给前端
    @RequestMapping("/searchByName")
    @ResponseBody
    public Map searchByName(@RequestParam String goodName,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        List<Good> list=goodService.searchByName(goodName);
        return ReturnMap.success(list);
    }

    //shareDisplay方法负责展示用户发布过的共享物品
    @RequestMapping("/shareDisplay")
    @ResponseBody
    public Map shareDisplay(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");
        Good good=new Good();
        good.setOwner(user);
        return ReturnMap.success(goodService.getList(good));
    }

    //borrowDisplay方法负责展示用户获得共享资格的物品
    @RequestMapping("/borrowDisplay")
    @ResponseBody
    public Map borrowDisplay(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null)
            return ReturnMap.error(12,"用户未登录");

        Good good=new Good();
        good.setBorrower(user);
        return ReturnMap.success(goodService.getList(good));
    }

    //deleteShare方法负责删除用户发布的某个物品
    @RequestMapping("/deleteShare")
    @ResponseBody
    public Map deleteShare(@RequestBody Good good, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        //根据物品对象，删掉所有物品图片，并删除物品图片表和物品表中的相关数据
        good=goodService.getOne(good);
        for(int i=0;i<good.getUrls().size();i++){
            String url=good.getUrls().get(i);
            File oldPicture=new File("../"+url);
            if(oldPicture.exists()){oldPicture.delete();}
        }
        goodPictureService.deleteAll(good);
        goodService.delete(good);
        return ReturnMap.success(good);
    }

    @RequestMapping("/deleteBorrow")
    @ResponseBody
    public Map deleteBorrow(@RequestBody Good good, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");
        good.setBorrower(user);
        goodService.deleteBorrow(good);
        return ReturnMap.success("成功删除");
    }

    //deleteAllShare方法负责删除用户发布过的所有物品
    @RequestMapping("/deleteallshare")
    @ResponseBody
    public Map deleteAllShare(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        Good good=new Good();
        good.setOwner(user);
        List<Good> goodList=goodService.getList(good);
        for(int i=0;i<goodList.size();i++){
            Good oldGood=goodList.get(i);
            for(int j=0;j<oldGood.getUrls().size();j++){
                String url=oldGood.getUrls().get(j);
                File oldPicture=new File("../"+url);
                if(oldPicture.exists()){oldPicture.delete(); }
            }
            goodPictureService.deleteAll(oldGood);
            goodService.delete(oldGood);
        }
        return ReturnMap.success("成功");
    }

    //deleteAllBorrow方法负责删除所有用户申请到共享资格的物品
    @RequestMapping("/deleteallborrow")
    @ResponseBody
    public Map deleteAllBorrow(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        Good good=new Good();
        good.setBorrower(user);
        goodService.deleteAllBorrow(good);
        return ReturnMap.success("成功");
    }
}

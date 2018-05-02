package com.hjm.controller;

import com.alibaba.fastjson.JSON;
import com.hjm.model.User;
import com.hjm.service.UserService;
import com.hjm.utils.FileConfig;
import com.hjm.utils.ReturnMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = Logger.getLogger("com.hjm.controller.UserController");
    @Autowired
    private UserService userService;
    @Resource
    private FileConfig fileConfig;

    @RequestMapping("/check")
    @ResponseBody
    public Map check(@RequestBody User user){
        if (userService.getOne(user)!=null)
            return ReturnMap.error(13, "用户名已存在");
        return ReturnMap.success(null);
    }
    //register方法负责用户的注册
    @RequestMapping("/register")
    @ResponseBody
    public Map register(@RequestBody User user){
        logger.info(JSON.toJSON(user).toString());
        //由后台自动生成新用户的id
        //将新用户的信息插入到用户表中
        User user1 = new User();
        user1.setName(user.getName());
        if(userService.getOne(user1)!=null)
            return ReturnMap.error(13,"用户名已存在");
        user.setId(UUID.randomUUID().toString());
        userService.insert(user);

        //返回用户
        return ReturnMap.success(user);
    }

    //login方法负责用户的登录
    @RequestMapping("/login")
    @ResponseBody
    public Map login(@RequestBody User loginUser, HttpServletRequest request){
        if(loginUser.getName()==null || loginUser.getPassword()==null ||
                "".equals(loginUser.getName()) || "".equals(loginUser.getPassword()))
            return ReturnMap.error(11, "请输入用户名或密码");
        //用户名和密码都不能为空
        if(userService.getOne(loginUser)==null)
            return ReturnMap.error(10, "用户名或密码错误");

        //根据前端发送过来的User对象，从用户表中查找相关用户,并把查找到的用户对象赋值给新建User对象user
        User user=new User();
        user=userService.getOne(loginUser);
        user.setPassword(null);
        //如果查找成功，则HttpServletRequest对象保存user，以便进行其他操作前进行登录检查
        request.getSession().setAttribute("user", user);

        //返回user
        return ReturnMap.success(user);
    }

    //quit方法负责用户的退出
    @RequestMapping("/quit")
    @ResponseBody
    public Map quit(HttpServletRequest request){

        //从HttpServletRequest对象中获得当前登录的用户（如果不存在，则提示用户未登录）
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        //从HttpServletRequest对象中移除当前登录的用户
        request.getSession().removeAttribute("user");
        return ReturnMap.success("logout");
    }

    //getInfo方法负责提供用户数据
    @RequestMapping("/getInfo")
    @ResponseBody
    public Map getInfo(@RequestBody User nUser,HttpServletRequest request){

        //从HttpServletRequest对象中获得当前登录的用户（如果不存在，则提示用户未登录）
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        nUser=userService.getOne(nUser);
        nUser.setPassword(null);
        return ReturnMap.success(nUser);
    }

    //update方法负责用户信息的更新
    @RequestMapping("/update")
    @ResponseBody
    public Map update(@RequestBody User newUser, HttpServletRequest request){

        //从HttpServletRequest对象中获得当前登录的用户（如果不存在，则提示用户未登录）
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        //更新用户信息
        userService.update(newUser);
        newUser=userService.getOne(newUser);
        newUser.setPassword(null);
        request.getSession().setAttribute("user", newUser);
        return ReturnMap.success(newUser);
    }

    //upload方法负责用户头像的上传
    @RequestMapping("/profilePhotoUpload")
    @ResponseBody
    public Map upload(@RequestParam MultipartFile file, HttpServletRequest request)throws IOException {

        //从HttpServletRequest对象中获得当前登录的用户（如果不存在，则提示用户未登录）
        User user = (User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        //获得上传文件的全名，在全名中识别出文件类型，根据文件类型由系统生成一个全新的文件名（防止重复）
        // 把文件名保存在newFileName字符串中
        String fileFullName = file.getOriginalFilename();
        int index = fileFullName.lastIndexOf(".");
        String type = fileFullName.substring(index+1, fileFullName.length());
        String newFileName = UUID.randomUUID().toString()+"."+type;

        //获得旧头像的URL链接，根据URL找到旧头像的图片文件并删除
        String oldUrl=user.getUrl();
        File oldPicture=new File("../"+oldUrl);
        if(oldPicture.exists()){oldPicture.delete();}

        //创立一个新文件，指定其路径（URL）（文件名为上面新生成的文件名）
        //设置新头像的URL链接（指向新文件），更新用户表中相关用户的URL数据
        //通过transferTo方法将上传的文件写入到新文件中
        File newPicture = new File(fileConfig.getPath()+"/user/"+user.getId()+"/"+newFileName);
        user.setUrl("/resources/"+"/user/"+user.getId()+"/"+newFileName);
        userService.update(user);
        request.getSession().setAttribute("user", user);
        if (!newPicture.getParentFile().exists())
            newPicture.getParentFile().mkdirs();
        file.transferTo(newPicture);

        //更新用户的登录状态（只要用户表中有相关数据更新，用户的登录状态就得跟着更新）
        request.getSession().setAttribute("user", user);

        //返回用户
        return ReturnMap.success(user);
    }
}


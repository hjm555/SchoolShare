package com.hjm.controller;

import com.hjm.model.Good;
import com.hjm.model.SharingRequest;
import com.hjm.model.User;
import com.hjm.service.GoodService;
import com.hjm.service.SharingRequestService;
import com.hjm.service.UserService;
import com.hjm.utils.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/sharingRequset")
public class SharingRequestController {
    @Autowired
    private SharingRequestService sharingRequestService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    @ResponseBody
    public Map insert(@RequestBody SharingRequest sharingRequest, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");
        sharingRequest.setId(UUID.randomUUID().toString());
        sharingRequestService.insert(sharingRequest);
        return ReturnMap.success("已成功发送请求");
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map update(@RequestBody SharingRequest sharingRequest, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        Good good=new Good();
        good=sharingRequest.getGood();
        goodService.updateState(good);
        sharingRequestService.update(sharingRequest);
        return ReturnMap.success("恭喜您成功完成此次物品的共享，请及时与对方取得联系，并进行线下共享。");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(@RequestBody SharingRequest sharingRequest,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        sharingRequestService.delete(sharingRequest);
        return ReturnMap.success("请求删除成功");
    }

    @RequestMapping("/getOwnerList")
    @ResponseBody
    public Map getOwnerList(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        user=userService.getOne(user);
        return  ReturnMap.success(sharingRequestService.getOwnerList(user));
    }

    @RequestMapping("/getRequesterList")
    @ResponseBody
    public Map getRequesterList(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录");

        user=userService.getOne(user);
        return  ReturnMap.success(sharingRequestService.getRequesterList(user));
    }


}

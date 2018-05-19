package com.hjm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hjm.model.Good;
import com.hjm.model.Message;
import com.hjm.model.SharingRequest;
import com.hjm.model.User;
import com.hjm.service.GoodService;
import com.hjm.service.MessageService;
import com.hjm.service.SharingRequestService;
import com.hjm.service.UserService;
import com.hjm.utils.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
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
    @Autowired
    private MessageService messageService;

    @RequestMapping("/insert")
    @ResponseBody
    public Map insert(@RequestBody SharingRequest sharingRequest, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",null);
        sharingRequest.setId(UUID.randomUUID().toString());
        sharingRequestService.insert(sharingRequest);
        Message message=new Message();
        message.setContent("您好，我向您共享的物品《"+sharingRequest.getGood().getName()+"》发出了租借申请，希望您有时间可以看看");
        message.setReceiver(sharingRequest.getOwner());
        message.setSender(sharingRequest.getRequester());
        String res = JSON.toJSONStringWithDateFormat(message, "yyyy-MM-dd HH:mm:ss",SerializerFeature.DisableCircularReferenceDetect);
        try{
            Session receiverSession=WebSocketTest.get(sharingRequest.getOwner().getId());
            if(receiverSession!=null){
                receiverSession.getBasicRemote().sendText("["+res+"]");
                message.setState("已读");
            }
            else message.setState("未读");
            Session senderSession=WebSocketTest.get(sharingRequest.getRequester().getId());
            if(senderSession!=null){
                senderSession.getBasicRemote().sendText("["+res+"]");
            }
        }catch(IOException e){
            e.printStackTrace();
            return ReturnMap.error(50,"拒绝失败","未知错误");
        }
        messageService.sendOne(message);
        return ReturnMap.success("已成功发送请求");
    }

    @RequestMapping("/agree")
    @ResponseBody
    public Map agree(@RequestBody SharingRequest sharingRequest, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",null);

        Good good=new Good();
        good=sharingRequest.getGood();
        goodService.updateState(good);
        sharingRequestService.update(sharingRequest);
        good.setBorrower(sharingRequest.getRequester());
        goodService.update(good);
        goodService.updateState(good);
        Message message=new Message();
        message.setContent("您好，我同意了您对物品《"+sharingRequest.getGood().getName()+"》的租借申请，赶紧进行线下交易吧。");
        message.setReceiver(sharingRequest.getRequester());
        message.setSender(sharingRequest.getOwner());
        String res = JSON.toJSONStringWithDateFormat(message, "yyyy-MM-dd HH:mm:ss",SerializerFeature.DisableCircularReferenceDetect);
        try{
            Session receiverSession=WebSocketTest.get(sharingRequest.getOwner().getId());
            if(receiverSession!=null){
                receiverSession.getBasicRemote().sendText("["+res+"]");
                message.setState("已读");
            }
            else message.setState("未读");
            Session senderSession=WebSocketTest.get(sharingRequest.getRequester().getId());
            if(senderSession!=null){
                senderSession.getBasicRemote().sendText("["+res+"]");
            }
        }catch(IOException e){
            e.printStackTrace();
            return ReturnMap.error(50,"拒绝失败","未知错误");
        }
        messageService.sendOne(message);
        return ReturnMap.success("恭喜您成功完成此次物品的共享，请及时与对方取得联系，并进行线下共享。");
    }

    @RequestMapping("/refuse")
    @ResponseBody
    public Map refuse(@RequestBody SharingRequest sharingRequest, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",null);

        Good good=new Good();
        good=sharingRequest.getGood();
        sharingRequestService.update(sharingRequest);
        Message message=new Message();
        message.setContent("您好，很抱歉，因为某些原因，我拒绝了您对物品《"+sharingRequest.getGood().getName()+"》的租借申请。");
        message.setReceiver(sharingRequest.getRequester());
        message.setSender(sharingRequest.getOwner());
        String res = JSON.toJSONStringWithDateFormat(message, "yyyy-MM-dd HH:mm:ss",SerializerFeature.DisableCircularReferenceDetect);
        try{
            Session receiverSession=WebSocketTest.get(sharingRequest.getOwner().getId());
            if(receiverSession!=null){
                receiverSession.getBasicRemote().sendText("["+res+"]");
                message.setState("已读");
            }
            else message.setState("未读");
            Session senderSession=WebSocketTest.get(sharingRequest.getRequester().getId());
            if(senderSession!=null){
                senderSession.getBasicRemote().sendText("["+res+"]");
            }
        }catch(IOException e){
            e.printStackTrace();
            return ReturnMap.error(50,"拒绝失败","未知错误");
        }
        messageService.sendOne(message);
        return ReturnMap.success("您已成功处理这个申请。");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(@RequestBody SharingRequest sharingRequest,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",null);

        sharingRequestService.delete(sharingRequest);
        return ReturnMap.success("请求删除成功");
    }

    @RequestMapping("/getOwnerList")
    @ResponseBody
    public Map getOwnerList(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",new ArrayList<User>());

        user=userService.getOne(user);
        return  ReturnMap.success(sharingRequestService.getOwnerList(user));
    }

    @RequestMapping("/getRequesterList")
    @ResponseBody
    public Map getRequesterList(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",new ArrayList<User>());

        user=userService.getOne(user);
        return  ReturnMap.success(sharingRequestService.getRequesterList(user));
    }


}

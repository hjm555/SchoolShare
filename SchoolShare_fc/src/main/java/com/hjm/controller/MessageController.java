package com.hjm.controller;

import com.hjm.model.Message;
import com.hjm.model.User;
import com.hjm.service.MessageService;
import com.hjm.utils.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/sendOne")
    @ResponseBody
    public Map sendOne(@RequestBody Message message, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",null);

        message.setId(UUID.randomUUID().toString());
        messageService.sendOne(message);
        return ReturnMap.success("消息发送成功.");
    }

    @RequestMapping("/getAllMessage")
    @ResponseBody
    public Map getAllMessage(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null)
            return ReturnMap.error(12, "用户未登录",new ArrayList<Message>());

        List<Message> messageList=messageService.getAllMessage(user);
        messageService.update(user);
        return ReturnMap.success(messageList);
    }
}


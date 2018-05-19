package com.hjm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hjm.model.Message;
import com.hjm.model.User;
import com.hjm.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Controller
@ServerEndpoint(value = "/websocket/{userId}", configurator = SpringConfigurator.class)
public class WebSocketTest {
    private static Map<String, Session> map = new HashMap<>();
    Logger logger = Logger.getLogger(WebSocketTest.class);

    @Resource
    private MessageService messageService;

    private String id;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId")String id)throws IOException{
        this.id=id;
        set(id, session);
        User user=new User();
        user.setId(id);
        List<Message> list = messageService.getAllMessage(user);
        messageService.update(user);
        String res = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss",SerializerFeature.DisableCircularReferenceDetect);
        logger.info(res);
        session.getBasicRemote().sendText(res);
    }


    @OnClose
    public void onClose(){
        remove(id);
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException{
        Message message= JSON.parseObject(msg, Message.class);
        Session toSession = get(message.getReceiver().getId());
        if (toSession!=null){
            toSession.getBasicRemote().sendText("["+msg+"]");
            message.setState("已读");
        }
        else  message.setState("未读");
        messageService.sendOne(message);
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    private static synchronized void set(String id, Session session){
        map.put(id, session);
    }

    static synchronized Session get(String id) {
        return map.get(id);
    }

    private static synchronized void remove(String id) {
        map.remove(id);
    }
}

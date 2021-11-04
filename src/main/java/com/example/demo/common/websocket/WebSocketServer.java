package com.example.demo.common.websocket;

import com.example.demo.DemoApplication;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/imserver/{userId}",encoders = {ServerEncoder.class})
@Component
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    private static final int MaxOnlineCount = 10; //最大连接人数为10人
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(this.getOnlineCount()==MaxOnlineCount){
            try {
//                sendMessage("当前聊天室人数已达上限");
                sendSystemMessage("当前聊天室人数已达上限！");
            }catch (IOException e){
                log.error("用户:"+userId+",网络异常!!!!!!");
            }
        }else {
            if(webSocketMap.containsKey(userId)){
                webSocketMap.remove(userId);
                webSocketMap.put(userId,this);
                //加入set中
            }else{
                webSocketMap.put(userId,this);
                //加入set中
                addOnlineCount();
                //在线数加1
            }
            log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());
            try {
//                sendMessage("userId为"+this.userId+"连接成功");
                sendSystemMessage("userId为"+this.userId+"连接成功");
            } catch (IOException e) {
                log.error("用户:"+userId+",网络异常!!!!!!");
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("用户消息:"+userId+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                sendOnlineMessage(message,this.userId);
//                //解析发送的报文
//                JSONObject jsonObject = JSON.parseObject(message);
//                //追加发送人(防止串改)
//                jsonObject.put("fromUserId",this.userId);
//                String toUserId=jsonObject.getString("toUserId");
//                //传送给对应toUserId用户的websocket
//                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
//                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
//                }else{
//                    log.error("请求的userId:"+toUserId+"不在该服务器上");
//                    //否则不在这个服务器上，发送到mysql或者redis
//                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送系统消息，携带的code为201，前端不需要存进data
     * @param message
     * @throws IOException
     */
    public void sendSystemMessage(String message) throws IOException{
        //再次封装发送系统消息
        sendMessageWithUserId(message,this.userId,"201");
    }
    /**
     * 实现服务器主动推送,携带用户信息
     */
    public void sendMessageWithUserId(String userId,String message,String code) throws IOException {
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("message",message);
        map.put("code",code); //code为200的时候表示接收到的是用户信息,201 是系统返回消息，不同添加进前端的data中
        try {
            this.session.getBasicRemote().sendObject(map);
        }catch (Exception e){e.printStackTrace();}

    }



    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
    //向在线所有人发送消息
    public void sendOnlineMessage(String message,String userId){
        //对线程安全的map进行遍历（在线用户遍历）
        for(String key: webSocketMap.keySet()){
                try {
                    //调用每个用户的sendMessage方法
                   webSocketMap.get(key).sendMessageWithUserId(this.userId,message,"200");
                }catch (IOException e){
                    continue;
                }
        }
    }
}

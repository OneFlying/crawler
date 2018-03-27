package com.ld.crawler.web;

import com.Id.crawler.constant.WechatConstant;
import com.ld.crawler.domain.pojo.Profile;
import com.ld.crawler.dto.WechatMessage;
import com.ld.crawler.services.ProfileService;
import com.ld.crawler.utils.WechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/")
public class WechatController {

    @Resource
    private ProfileService profileService;
    /**
     * 对接服务器
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value =  "/wechat", method = RequestMethod.GET)
    @ResponseBody
    public void wechatDoor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String echostr = request.getParameter("echostr");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String signature = request.getParameter("signature");

        boolean res = WechatUtil.checkSignature(signature, timestamp, nonce);

        PrintWriter out = response.getWriter();
        if (res) {
            // 验证成功
            out.print(echostr);
        } else {
            out.print("hello wechat");
        }
    }

    @RequestMapping(value =  "/wechat", method = RequestMethod.POST)
    @ResponseBody
    public void wechatMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()){
            Map<String, String> messageMap = WechatUtil.xmlToMap(request);

            String msgType = messageMap.get("MsgType");
            if (msgType.equals(WechatConstant.MSG_TYPE_EVENT)) {
                this.handEventMessage(messageMap);
            } else {
                this.handCommonMessage(msgType, messageMap, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 处理事件类型消息
    private void handEventMessage(Map<String, String> messageMap) {
        String eventType = messageMap.get("Event");
        String openId = messageMap.get("FromUserName");
        if (eventType.equals(WechatConstant.EVENT_SUBSCRIBE)) {
            // 如果是订阅事件
            Profile profile = new Profile();
            profile.setOpenId(openId);
            profileService.insertProfile(profile);
        } else if (eventType.equals(WechatConstant.EVENT_UNSUBSCRIBE)) {
            // 如果是取消订阅事件
            profileService.deleteProfileByOpenId(openId);
        }
    }

    // 处理普通对话消息
    private void handCommonMessage(String msgType, Map<String, String> messageMap, PrintWriter out) {
        WechatMessage acceptMesssage = new WechatMessage();
        acceptMesssage.setMsgType(msgType);
        acceptMesssage.setContent(messageMap.get("Content"));
        acceptMesssage.setCreateTime(messageMap.get("CreateTime"));
        acceptMesssage.setFromUserName(messageMap.get("FromUserName"));
        acceptMesssage.setToUserName(messageMap.get("ToUserName"));
        acceptMesssage.setMsgId(messageMap.get("MsgId"));

        if (acceptMesssage.getMsgType().equals(WechatConstant.MSG_TYPE_TEXT)) {
            // 如果接受文本消息
            System.out.println(acceptMesssage.getContent());
            WechatMessage sendMessage = new WechatMessage();
            sendMessage.setMsgId(acceptMesssage.getMsgId());
            sendMessage.setMsgType(acceptMesssage.getMsgType());
            sendMessage.setFromUserName(acceptMesssage.getToUserName());
            sendMessage.setToUserName(acceptMesssage.getFromUserName());
            sendMessage.setCreateTime(new Date().toString());
            sendMessage.setContent("hello:" + acceptMesssage.getFromUserName());

            out.print(WechatUtil.objectToXml(sendMessage));
        }
    }
}

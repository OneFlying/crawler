package com.ld.crawler.utils;

public class WechatAPIHelpher {

    // 获取access_token 接口
    public static String getAccessToken() {
        String getAccessTokenUrl
                = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WechatUtil.appID+"&secret="+ WechatUtil.appsecret;
        return HttpClientUtil.doGet(getAccessTokenUrl);
    }

    // 根据标签推送群消息
    public static String sendMessageToAllByTag(String jsonParam) {
        // todo 这里token  获取将来放入redis缓存中
        String res = getAccessToken();
        String token = (String) JSONUtil.jsonToMap(res).get("access_token");
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+token;

        return HttpClientUtil.doPostForJson(url, jsonParam);
    }

    // 根据openid推送消息
    public static String sendMessageToAllByOpenId(String jsonParam) {
        // todo 这里token  获取将来放入redis缓存中
        String res = getAccessToken();
        String token = (String) JSONUtil.jsonToMap(res).get("access_token");
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+token;

        return HttpClientUtil.doPostForJson(url, jsonParam);
    }

}

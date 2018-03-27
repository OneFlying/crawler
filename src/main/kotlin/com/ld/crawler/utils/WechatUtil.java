package com.ld.crawler.utils;

import com.ld.crawler.dto.WechatMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信工具类
 */
public class WechatUtil {

    public static final String appID = "wx1df63e8633605670";

    public static final String appsecret = "fc4d8f7addc14d3b38b1afafefb61401";

    private static final String token = "weixin";

    // 验证服务器对接时签名
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String []arr = {token, timestamp, nonce};

        Arrays.sort(arr);

        StringBuffer sb = new StringBuffer();

        for (String s : arr) {
            sb.append(s);
        }

        // sha1加密
        String temp = getSha1(sb.toString());
        return temp.equals(signature);
    }

    // sha1加密
    public static String getSha1(String str){
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将微信请求中的xml转化成map
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        try (InputStream is = request.getInputStream()){

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(is);

            Element root =  document.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
            return map;
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将消息对象转化成xml
    public static String objectToXml(Object message){

        XStream xs = new XStream();

        //由于转换后xml根节点默认为class类，需转化为<xml>
        xs.alias("xml", message.getClass());

        return xs.toXML(message);
    }
}

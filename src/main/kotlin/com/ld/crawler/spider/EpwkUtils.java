package com.ld.crawler.spider;

import com.alibaba.fastjson.JSONObject;
import com.ld.crawler.domain.pojo.PrjInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * @author jason
 */
@Slf4j
public class EpwkUtils {
    public static List<PrjInfo> crawPrjinfo() {
        String url = "http://www.epwk.com/kaifa/task/";
        List<PrjInfo> list = new ArrayList<PrjInfo>();
        try{
            Document doc = Jsoup.connect(url).get();
            log.info(doc.title());
            Element prjLines = doc.selectFirst(".task_class_list_li");
            String pageInfo = doc.selectFirst(".page").text();
            String pages = pageInfo.substring(pageInfo.indexOf("/")+1,pageInfo.indexOf("页")).replace(" ","");
            int totalPage = Integer.valueOf(pages);
            for(int i=1;i<totalPage;i++){
                String pageUrl = url + "page"+i+".html";
                List<PrjInfo> info = crawPrjinfoByPage(pageUrl);
                if(info != null){
                    list.addAll(info);
                }
            }
        }catch (IOException e){
            log.error("crawPrjinfo exception:",e);
        }
        return list;
    }

    public static List<PrjInfo> crawPrjinfoByPage(String pageurl){
        List<PrjInfo> list = null;
        try {
            Document doc = Jsoup.connect(pageurl).get();
            log.info(doc.title());
            Element prjLines = doc.selectFirst(".task_class_list_li");
            if (prjLines != null) {
                list = new ArrayList<PrjInfo>();
                Elements dls = prjLines.select(".task_class_list_li_box");
                for (Element dl : dls) {
                    PrjInfo prjInfo = new PrjInfo();
                    Element timeElement = dl.selectFirst(".wrid2");
                    if (timeElement != null) {
                        Element taskType = timeElement.selectFirst("em");
                        if("雇佣任务".equals(taskType.text())){
                            continue;
                        }
                        prjInfo.setFabutime(timeElement.text().substring(5));
                        prjInfo.setContent(timeElement.text());
                    }
                    Element moneyElement = dl.selectFirst(".wrid1 b");
                    if (moneyElement != null) {
                        String price = moneyElement.text().replace(" ","").replace("￥","");
                        prjInfo.setMoney(price);
                    }
                    Element titleElement = dl.selectFirst(".wrid1 a");
                    if (titleElement != null) {
                        prjInfo.setTitle(titleElement.attr("title"));
                        String detailUrl = titleElement.absUrl("href");
                        prjInfo.setUrl(detailUrl);
                        Document details = Jsoup.connect(detailUrl).get();
                        prjInfo.setHtml(details.html());
                        Element content = details.selectFirst(".task-info-content");
                        prjInfo.setContent(content.text());
                    }
                    list.add(prjInfo);
                    log.info(JSONObject.toJSONString(prjInfo));
                    Random random = new Random();
                    int r = (int)random.nextInt(10) + 1;
                    Thread.sleep(r * 1000);
                }
            }
        } catch (IOException e) {
            log.error("epwk open exception:", e);
        } catch (InterruptedException e){
            log.error("epwk thread sleep exception:", e);
        }
        return list;
    }
    public static void main(String[] args) {
        crawPrjinfo();
    }
}

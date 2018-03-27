package com.ld.crawler.spider;

import com.ld.crawler.domain.pojo.PrjInfo;
import com.ld.crawler.utils.CrawlerThreadFactory;
import com.ld.crawler.utils.ThreadCallback;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jason
 */
@Slf4j
@Component
public class EpwkUtils {
    //@Value("${epwk}")
    private static String epwk="EPWK";
    private ThreadCallback threadCallback = new ThreadCallback();

    /**
     *线程池
     */
    private ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,Runtime.getRuntime().availableProcessors()*2,1L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1024),
            new CrawlerThreadFactory("EPWK"),new ThreadPoolExecutor.AbortPolicy());

    public void crawPrjinfo() {
        String url = "http://www.epwk.com/kaifa/task/";
        List<PrjInfo> list = new ArrayList<PrjInfo>();
        try{
            Document doc = Jsoup.connect(url).get();
            log.info(doc.title());
            String pageInfo = doc.selectFirst(".page").text();
            String pages = pageInfo.substring(pageInfo.indexOf("/")+1,pageInfo.indexOf("页")).replace(" ","");
            int totalPage = Integer.valueOf(pages);
            log.info("totalPage：{}",totalPage);
            for(int i=1;i<totalPage;i++){
                String pageUrl = url + "page"+i+".html";
                List<PrjInfo> infoList = new ArrayList<PrjInfo>();
                Runnable runnable = crawPrjinfoByPageRunnable(pageUrl,infoList);
                executorService.execute(runnable);
            }
        }catch (IOException e){
            log.error("crawPrjinfo exception:",e);
        }
    }

    private Runnable crawPrjinfoByPageRunnable(final String pageurl,final List<PrjInfo> list) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("URL{}:抓取数量{}",pageurl,list.size());
                    Document doc = Jsoup.connect(pageurl).get();
                    Element prjLines = doc.selectFirst(".task_class_list_li");
                    if (prjLines != null) {
                        Elements dls = prjLines.select(".task_class_list_li_box");
                        for (Element dl : dls) {
                            PrjInfo prjInfo = new PrjInfo();
                            Element timeElement = dl.selectFirst(".wrid2");
                            if (timeElement != null) {
                                Element taskType = timeElement.selectFirst("em");
                                /*if("雇佣任务".equals(taskType.text())){
                                    continue;
                                }*/
                                //过滤掉正在进行 和 截稿的项目
                                if (taskType != null) {
                                    //项目状态
                                    Element taskStatus = taskType.selectFirst("span");
                                    if (taskStatus != null) {
                                        if (taskStatus.text().equals("圆满完成") || taskStatus.text().equals("选稿中") || taskStatus.text().equals("等待雇主托管赏金")
                                                || taskStatus.text().equals("工作中")) {
                                            continue;
                                        }
                                    }
                                }
                                prjInfo.setFabutime(timeElement.text().substring(5));
                                prjInfo.setContent(timeElement.text());
                            }
                            Element moneyElement = dl.selectFirst(".wrid1 b");
                            if (moneyElement != null) {
                                String price = moneyElement.text().replace(" ", "").replace("￥", "");
                                prjInfo.setMoney(price);
                            }
                            Element titleElement = dl.selectFirst(".wrid1 a");
                            if (titleElement != null) {
                                prjInfo.setTitle(titleElement.attr("title"));
                                String detailUrl = titleElement.absUrl("href");
                                prjInfo.setUrl(detailUrl);
                                /*Document details = Jsoup.connect(detailUrl).get();
                                log.info(detailUrl);
                                prjInfo.setHtml(details.html());
                                Element content = details.selectFirst(".task-info-content");
                                if (content != null) {
                                    prjInfo.setContent(content.text());
                                }*/

                            }
                            prjInfo.setSource(epwk);
                            list.add(prjInfo);
                        }
                        //回调数据
                        threadCallback.epwk(list);
                        log.info("URL{}:抓取数量{}",pageurl,list.size());
                    }
                } catch (IOException e) {
                    log.error("epwk open exception:", e);
                }
            }
        };
        return runnable;
    }
    public static void main(String[] args) {
        EpwkUtils epwkUtils = new EpwkUtils();
        epwkUtils.crawPrjinfo();
    }
}

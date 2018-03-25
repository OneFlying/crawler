package com.ld.crawler.spider;

import com.ld.crawler.constant.DataSource;
import com.ld.crawler.domain.pojo.PrjInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description sxsoft 网站数据
 * @Author dzfking007 (<a href="mailto:dzfking007@163.com">dzfking007@163.com</a>)
 * 2018/3/25 16:08
 */
@Slf4j
public class SxsoftUtils {
    private static Pattern pattern = Pattern.compile("(totalPages : (\\d+),)");
    private static Pattern numberPattern = Pattern.compile("(\\d+)");
    private static String  URL = "https://www.sxsoft.com/page/project/search/0/true";
    /**
     * 获取总页数
     */
    public static int getPageNum(){
        int totalPages = 0;
        Connection con = Jsoup.connect(URL);
        con.data("page","1");
        con.data("xiangmuzhuangtai","jbz");
        try {
            Document doc = con.post();
            Element element = doc.selectFirst("script:eq(1)");
            String html = element.html();
            if(html!=null){
                Matcher matcher = pattern.matcher(html);
                if(matcher.find()){
                   String line = matcher.group(0);
                   log.info(line);
                   if(line!=null){
                       Matcher numMatcher = numberPattern.matcher(line);
                       if(numMatcher.find()){
                           String pageStr = numMatcher.group(0);
                           log.info(pageStr);
                           if(!StringUtils.isEmpty(pageStr)){
                               totalPages = Integer.parseInt(pageStr);
                           }
                       }
                   }


                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalPages;

    }


    public static List<PrjInfo> crawPrjinfoBypage(String page) throws IOException {
        log.info("-------------------第{}页------------",page);
        List<PrjInfo> list = null;
        Connection con = Jsoup.connect(URL);
        con.data("page",page);
        con.data("xiangmuzhuangtai","jbz");
        Document doc = con.post();
        Element prjLines = doc.selectFirst("ul");
        if(prjLines!=null) {
            list = new ArrayList<PrjInfo>();
            Elements lis = prjLines.select("li");
            for(Element li : lis) {
                PrjInfo prjInfo = new PrjInfo();
                Element moneyElement = li.selectFirst("div:eq(1)").selectFirst(".green");
                if(moneyElement!=null) {
                    prjInfo.setMoney(moneyElement.text());
                }
                Element titleElement = li.selectFirst("div:eq(1)").selectFirst("a");
                if(titleElement!=null) {
                    prjInfo.setTitle(titleElement.text());
                    prjInfo.setUrl(titleElement.absUrl("href"));
                }
                Element fabutimeElement = li.selectFirst("div:eq(1)").selectFirst("span:eq(3)").selectFirst("em");
                if(fabutimeElement!=null) {
                    prjInfo.setFabutime(fabutimeElement.text());
                }
                //抓取内容简介
                crawContentHtml(prjInfo.getUrl(),prjInfo);
                prjInfo.setSource(DataSource.SXSOFT);
                list.add(prjInfo);
                log.info(prjInfo.getTitle());
            }
        }
        return list;
    }

    /**
     * 获取内容简介 和保存html内容
     * @param url
     * @return
     * @throws IOException
     */
    public static void crawContentHtml(String url,PrjInfo prjInfo) throws IOException {
        Document doc = Jsoup.connect(url).get();
        if(doc!=null){
            prjInfo.setHtml(doc.html());
            Element contentElement = doc.selectFirst(".content div");
            if(contentElement!=null){
                String content = contentElement.html();
                prjInfo.setContent(content);
            }
        }

    }

    public static List<PrjInfo> crawPrjinfo() throws IOException {
        List<PrjInfo> list = null;
        System.setProperty ("jsse.enableSNIExtension", "false");
        int totalPages = getPageNum();
        if(totalPages>0){
            list = new ArrayList<PrjInfo>();
            for(int page = 1;page<=totalPages;page++){
                list.addAll(crawPrjinfoBypage(String.valueOf(page)));
            }
        }
        return list;
    }


    public static void main(String[] args) throws IOException {
        crawPrjinfo();
    }
}

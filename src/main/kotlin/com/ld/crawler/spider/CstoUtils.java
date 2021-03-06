package com.ld.crawler.spider;

import com.alibaba.fastjson.JSONObject;
import com.ld.crawler.domain.pojo.PrjInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author dzfking007@163.com
 * CSTO 网站抓取类 
 */
@Slf4j
@Component
public class CstoUtils {

	//@Value("${csto}")
	private static String csto = "CSTO";

	public static List<PrjInfo> crawPrjinfo() throws IOException {
		List<PrjInfo> list = null;
		Document doc = Jsoup.connect("http://www.csto.com/project/list").get();
		log.info(doc.title());
		Element prjLines = doc.selectFirst("#list_shwores");
		if(prjLines!=null) {
			list = new ArrayList<PrjInfo>();
			Elements dls = prjLines.select("dl");
			for(Element dl : dls) {
				PrjInfo prjInfo = new PrjInfo();
				Element moneyElement = dl.selectFirst(".head .price");
				if(moneyElement!=null) {
					prjInfo.setMoney(moneyElement.attr("title"));
				}
				Element titleElement = dl.selectFirst(".intro .title a");
				if(titleElement!=null) {
					prjInfo.setTitle(titleElement.text());
					prjInfo.setUrl(titleElement.absUrl("href"));
				}
				Element fabutimeElement = dl.selectFirst(".intro ol li");
				if(fabutimeElement!=null) {
					prjInfo.setFabutime(fabutimeElement.text());
				}
				Element contentElement = dl.selectFirst(".intro .introduce p");
				if(contentElement!=null) {
					prjInfo.setContent(contentElement.text());
				}

				prjInfo.setSource(csto);
				list.add(prjInfo);
				log.info(JSONObject.toJSONString(prjInfo));
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
		if (doc != null) {
			prjInfo.setHtml(doc.html());
			Element contentElement = doc.selectFirst(".content div");
			if (contentElement!=null) {
				prjInfo.setContent(contentElement.html());
			}
		}

	}
	
	
	public static void main(String[] args) throws IOException {
		crawPrjinfo();
		
	}

}

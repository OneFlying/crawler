package com.ld.crawler.spider;

import com.alibaba.fastjson.JSONObject;
import com.ld.crawler.dto.PrjInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author dzfking007@163.com
 * CSTO 网站抓取类 
 */
@Slf4j
public class CstoUtils {
	public static List<PrjInfo> crawPrjinfo() throws IOException{
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
				list.add(prjInfo);
				log.info(JSONObject.toJSONString(prjInfo));
			}
		}
		return list;
	}
	
	
	public static void main(String args[]) throws IOException {
		crawPrjinfo();
		
	}

}

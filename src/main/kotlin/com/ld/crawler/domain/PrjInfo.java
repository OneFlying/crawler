package com.ld.crawler.domain;
/**
 * 项目信息
 * @author dzfking007@163.com
 *
 */
public class PrjInfo {
	private String title;//标题
	private String fabutime;//发布时间
	private String money;//发布金额
	private String content;//简介
	private String url;//项目地址
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFabutime() {
		return fabutime;
	}
	public void setFabutime(String fabutime) {
		this.fabutime = fabutime;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

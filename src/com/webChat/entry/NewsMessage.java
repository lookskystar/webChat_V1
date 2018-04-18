package com.webChat.entry;

import java.util.Map;

/**
* @ClassName: NewsMessage
* @Description: TODO(图文消息实体)
* @author andy
* @version V1.0  
* @date Sep 12, 2016 9:37:29 AM
 */
public class NewsMessage {
	private Map<String,NewsItem> newsMap;

	public Map<String, NewsItem> getNewsMap() {
		return newsMap;
	}
	public void setNewsMap(Map<String, NewsItem> newsMap) {
		this.newsMap = newsMap;
	}
	
	
}

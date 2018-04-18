package com.webChat.entry;

/**
* @ClassName: TextMessage
* @Description: TODO(文本消息实体类)
* @author andy
* @version V1.0  
* @date Sep 12, 2016 9:20:56 AM
 */
public class TextMessage {
	//主标题
	private String mainTitle;
	//副标题
	private String subTitle;
	//内容
	private String contents;
	
	public TextMessage() {
		super();
	}
	public TextMessage(String mainTitle, String subTitle, String contents) {
		super();
		this.mainTitle = mainTitle;
		this.subTitle = subTitle;
		this.contents = contents;
	}
	
	public String getMainTitle() {
		return mainTitle;
	}
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getMainTitle()+"\n"+this.getSubTitle()+"\n"+this.getContents();
	}

	

}

package com.webChat.entry;

/**
 * @ClassName: VideoMessage
 * @Description: TODO(视频消息实体类)
 * @author andy
 * @version V1.0
 * @date Sep 2, 2016 10:22:28 AM
 */
public class VideoMessage {
	private String title;
	private String description;
	private String mediaId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}

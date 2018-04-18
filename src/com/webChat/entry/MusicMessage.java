package com.webChat.entry;

/**
* @ClassName: MusicMessage
* @Description: TODO(音乐消息实体类)
* @author andy
* @version V1.0  
* @date Sep 2, 2016 10:20:26 AM
 */
public class MusicMessage {
		private String title;
		private String description;
		private String musicUrl;
		private String hqMusicUrl;
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
		public String getMusicUrl() {
			return musicUrl;
		}
		public void setMusicUrl(String musicUrl) {
			this.musicUrl = musicUrl;
		}
		public String getHqMusicUrl() {
			return hqMusicUrl;
		}
		public void setHqMusicUrl(String hqMusicUrl) {
			this.hqMusicUrl = hqMusicUrl;
		}	
}

package com.amazingfour.common.wxservice.util.respentity;

/**
 * 
 * <音乐消息>
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * TC 	1.0  		2015年8月9日 	Created
 *
 * </pre>
 * @since 1.
 */
public class MusicMessage extends BaseMessage {  
    // 音乐 
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}  
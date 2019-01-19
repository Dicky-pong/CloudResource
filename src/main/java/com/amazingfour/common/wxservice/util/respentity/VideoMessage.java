package com.amazingfour.common.wxservice.util.respentity;

/**
 * 
 * <视频消息>
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * TC 	1.0  		2015年8月9日 	Created
 *
 * </pre>
 * @since 1.
 */
public class VideoMessage extends BaseMessage {

    //视频
    private Video Video;

    
    public Video getVideo() {
        return Video;
    }

    
    public void setVideo(Video video) {
        Video = video;
    }
    
}

package com.amazingfour.common.wxservice.util.respentity;

/**
 * 
 * <视频Model>
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * TC 	1.0  		2015年8月9日 	Created
 *
 * </pre>
 * @since 1.
 */
public class Video {

    //媒体文件
    private String MediaId;
    
    //缩略图的媒体ID
    private String ThumbMediaId;

    
    public String getMediaId() {
        return MediaId;
    }

    
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    
    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
    
    
}

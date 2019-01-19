package com.amazingfour.common.wxservice.util.respentity;



/**
 * 
 * <图片消息>
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * TC 	1.0  		2015年8月9日 	Created
 *
 * </pre>
 * @since 1.
 */
public class ImageMessage extends BaseMessage {

    //图片
    private Image Image;

    
    public Image getImage() {
        return Image;
    }

    
    public void setImage(Image image) {
        Image = image;
    }
    
}

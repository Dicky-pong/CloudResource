package com.amazingfour.common.wxservice.util.respentity;


/**
 * 
 * <语音消息>
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * TC 	1.0  		2015年8月9日 	Created
 *
 * </pre>
 * @since 1.
 */
public class VoiceMessage extends BaseMessage {

    //语音
    private Voice Voice;

    
    public Voice getVoice() {
        return Voice;
    }

    
    public void setVoice(Voice voice) {
        Voice = voice;
    }
    
    
}

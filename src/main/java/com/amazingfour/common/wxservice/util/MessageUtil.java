package com.amazingfour.common.wxservice.util;


import java.io.InputStream;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import javax.servlet.http.HttpServletRequest;

import com.amazingfour.common.wxservice.util.respentity.Article;
import com.amazingfour.common.wxservice.util.respentity.MusicMessage;
import com.amazingfour.common.wxservice.util.respentity.NewsMessage;
import com.amazingfour.common.wxservice.util.respentity.TextMessage;
import com.amazingfour.common.wxservice.util.respentity.VideoMessage;
import com.amazingfour.common.wxservice.util.respentity.VoiceMessage;


/**
 * 
 * <请求消息工具类>
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * tanchao 	1.0  		2015年8月9日 	Created
 *
 * </pre>
 * @since 1.
 */
public class MessageUtil {

	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	
	/**
	 * 事件类型：群发消息返回
	 */
	public static final String EVENT_TYPE_MASSSENDJOBFINISH = "MASSSENDJOBFINISH";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	
	/**
	 * 事件类型：LOCATION(地理位置推送)
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 领取卡券事件
	 */
	public static final String EVENT_TYPE_USER_GET_CARD="user_get_card";
	
	public static final String EVENT_TYPE_COUPONS_GET = "GetCoupons";
	
	/**
	 * 卡券审核通过
	 */
	public static final String EVENT_TYPE_CARD_PASS_CHECK="card_pass_check";
	
	/**
     * 卡券审核不通过
     */
	public static final String EVENT_TYPE_CARD_NOT_PASS_CHECK="card_not_pass_check";
    
	/**
	 * 微信自定义菜单文字点击事件
	 */
	public static final String EVENT_TYPE_WECHATMENU_TEXT_CHECK="wechat_menu_text";
	
	/**
	 * 微信自定义菜单图文点击事件
	 */
	public static final String EVENT_TYPE_WECHATMENU_NEWS_CHECK="wechat_menu_news";
	
	/**
	 * 
	 */
	public static final String EVENT_TYPE_WIFI_CONNECT ="WifiConnected";
	/**
	 * 微信门店审核通过
	 */
	public static final String EVENT_TYPE_POI_CHECK_NOTIFY="poi_check_notify";
	
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage 文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	/**
     * 文本消息对象转换成xml
     * 
     * @param voiceMessage 语音消息对象
     * @return xml
     */
	public static String messageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }
	
	/**
     * 文本消息对象转换成xml
     * 
     * @param videoMessage 视频消息对象
     * @return xml
     */
	public static String messageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }
	
	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessage 音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage 图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
    /** 
     * 判断是否是QQ表情 
     *  
     * @param content 
     * @return 
     */  
    public static boolean isQqFace(String content) {  
        boolean result = false;  
      
        // 判断QQ表情的正则表达式  
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";  
        Pattern p = Pattern.compile(qqfaceRegex);  
        Matcher m = p.matcher(content);  
        if (m.matches()) {  
            result = true;  
        }  
        return result;  
    }  
    
    /** 
     * 演示Java中常用的获取long类型时间的两种方式 
     */  
    public static void main(String[] args) {  
        long longTime = 1373206143378L;  
        String stdFormatTime = formatTime(longTime);  
        // 输出格式：2013-07-07 22:09:03  
        System.out.println(stdFormatTime);  
    }  
      
    /** 
     * 将long类型的时间转换成标准格式（yyyy-MM-dd HH:mm:ss） 
     *  
     * @param longTime 
     * @return 
     */  
    public static String formatTime(long longTime) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return format.format(new Date(longTime));  
    }  
}


package com.amazingfour.common.wxservice.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.amazingfour.common.wxservice.util.respentity.TextMessage;




/**
 * 核心服务类
 * 
 */
public class WxCoreService {
    
	protected static Logger log = Logger.getLogger(WxCoreService.class);
	

	/**
	 * 处理微信发来的请求 换行符仍然是"\n"
	 * 
	 * @param request
	 * @param userId 公众号在系统中标示的商户ID
	 * @return
	 */
	public static String processRequest(HttpServletRequest request,String userId) {
		String respMessage = null;
        log.info("------------->>返回响应数据为:"+respMessage);
        try {
			// 默认返回的文本消息内容
			String respContent = "模块开发中，敬请期待。。。";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			//map信息记录日志
			log.info("请求数据==>解析后的map数据:"+requestMap);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			// 两种获取整形时间的方法。
			// 获取到的结果表示当时时间距离1970年1月1日0时0分0秒0毫秒的毫秒数。公众平台api中消息创建时间CreateTime，它表示1970年1月1日0时0分0秒至消息创建时所间隔的秒数，注意是间隔的秒数，不是毫秒数！
			// long longTime1 = System.currentTimeMillis();
			// long longTime2 = new java.util.Date().getTime();
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			    respContent = "您发送的是文字消息！/坏笑";
			    textMessage.setContent(respContent);
			    respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！/微笑";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是<a href=\"http://www.24hs.cn/\">链接</a>消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				
				log.info("*************微信推送事件：************----------->>>>"+eventType);
				
				if(eventType.equals(MessageUtil.EVENT_TYPE_USER_GET_CARD)){
					respContent = "您已成功领取一张卡券，请及时使用【智惠联盟】";
				}
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				    
				    
				    /*respContent = "感谢您关注【一点点商户云】";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                    log.info("一点点：respMessage:"+respMessage);*/
                    // 将关注者保存为会员					
//					Users user = userService.findUserById(userId);
					respContent = "感谢您关注【功夫梦集结号】";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
					//封装会员信息
//					Member member = new Member();
//					member.setWxId(fromUserName);
//					member.setMerId(user.getId());
//					MsgJson reuslt = MemberServiceAPI.RegisterMember(member);
//					log.info("注册会员信息状态："+reuslt.toString()+" 会员信息  ："+user.toString());
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
//					String eventKey = requestMap.get("EventKey");
//					if(MessageUtil.EVENT_TYPE_COUPONS_GET.equals(eventKey)){
//					    // 创建图文消息
//	                    NewsMessage newsMessage = new NewsMessage();
//	                    newsMessage.setToUserName(fromUserName);
//	                    newsMessage.setFromUserName(toUserName);
//	                    newsMessage.setCreateTime(new Date().getTime());
//	                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//	                    newsMessage.setFuncFlag(0);
//					    List<Article> articleList = new ArrayList<Article>();
//				        Article article = new Article();
//				        article.setTitle("上岛咖啡50元代金卷");
//				        article.setDescription("一点点给您发优惠券啦,请笑纳");
//				        article.setPicUrl("http://yun.yidiandian.net/images/13510976385/infos/CardId1439292185120.png");
//				        article.setUrl("http://yun.yidiandian.net/zhlt/couponsList/getImgCodeInfo.do?couponsId=ebcccb85-558a-4a92-82c6-0f3324dcdad3&imgId=00000001439292226427");
//				        articleList.add(article);
//				        newsMessage.setArticleCount(articleList.size());
//	                    newsMessage.setArticles(articleList);
//	                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
//					}
				}
				//卡券领取推送消息
				else if(eventType.equals(MessageUtil.EVENT_TYPE_USER_GET_CARD)){
				    
				   
				}
				/**
				 * 会员卡审核通过事件处理
				 */
				else if(eventType.equals(MessageUtil.EVENT_TYPE_CARD_PASS_CHECK)){
//				    //被领取的优惠券ID
//                    String cardId = requestMap.get("CardId");
//                    log.info("*********会员卡审核成功事件推送:当前审核的会员卡套ID**********"+cardId);
//				    //会员卡服务Service
//                    IMemberShipCardService memberShipCardService = (IMemberShipCardService)SpringUtil.getBean("memberShipCardService");
//                    //审核通过的会员状态为1  
//                    memberShipCardService.checkCard(cardId,"1");
				}
				/**
                 * 会员卡审核不通过事件处理
                 */
                else if(eventType.equals(MessageUtil.EVENT_TYPE_CARD_NOT_PASS_CHECK)){
                  //被领取的优惠券ID
//                    String cardId = requestMap.get("CardId");
//                    log.info("*********会员卡审核不成功事件推送:当前审核的会员卡套ID**********"+cardId);
//                    //会员卡服务Service
//                    IMemberShipCardService memberShipCardService = (IMemberShipCardService)SpringUtil.getBean("memberShipCardService");
//                    //审核通过的会员状态为2
//                    memberShipCardService.checkCard(cardId,"2");
                }
				
			}
        } catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
        log.info("------------->>返回响应数据为:"+respMessage);
		return respMessage;
	}

}
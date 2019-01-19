/**   
 * @Project: crms 
 * @Title: testQuartz.java 
 * @Package com.amazingfour.common.quartz 
 * @Description: TODO 
 * @author pengwx 
 * @date 2018年4月28日 下午5:04:04 
 * @Copyright: 2018 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package com.amazingfour.common.quartz;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.amazingfour.common.client.LocalHttpClient;
import com.amazingfour.common.utils.DateUtil;
import com.amazingfour.common.utils.ResponseUtil;
import com.amazingfour.common.utils.mail.Mail;
import com.amazingfour.common.utils.mail.MailUtils;

/** 
 * @ClassName testQuartz  
 * @Description TODO 
 * @author pengwx 
 * @date 2018年4月28日  
 *   
 */
@Component("/stockMonitor")
@RequestMapping("/stockMonitor")
public class StockMonitor {
	
	protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());
	protected static final String BASE_URI = "http://localhost/crms";

 
//	@Scheduled(cron = "0 0/1 * * * ?")
	public void excute(){
		
	}
	
	@RequestMapping("/turnToPage")
	public ModelAndView turnToPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/stockMonitor");
		return mav;
	}
	
	@RequestMapping("/sendStockPriceMessage")
	@ResponseBody
	public String sendStockPriceMessage(String stockName,String currentPrice){
		JSONObject obj = new JSONObject();
		/*
		 * 发邮件
		 */
        //把配置文件内容加载到prop中
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

		// 登录邮件服务器，得到session
        String host = prop.getProperty("host");//服务器主机名
        String name = prop.getProperty("username");//登录名
        String pass = prop.getProperty("password");//登录密码
        Session session = MailUtils.createSession(host, name, pass);

		// 创建Mail对象
        String from = prop.getProperty("from");
        String to = prop.getProperty("to");
        String subject = prop.getProperty("stockWarn");
        // MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
        // 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "去死吧"); 返回“你好张三，你去死吧！”
        String content = MessageFormat.format("{0}的当前价格为：{1}",stockName,currentPrice);
        Mail mail = new Mail(from, to, subject, content);

        // 发送邮件
        try {
            MailUtils.send(session, mail);
            obj.put("mes", "发送成功!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return obj.toString();
	}

}

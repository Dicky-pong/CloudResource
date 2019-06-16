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
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;

import com.amazingfour.common.client.HttpUtils;
import com.amazingfour.common.shiro.cache.JedisManager;
import com.amazingfour.common.shiro.cache.VCache;
import com.amazingfour.common.utils.SpringContextUtil;
import com.amazingfour.common.utils.StringUtils;
import com.amazingfour.crms.domain.StockMonitor;
import com.amazingfour.crms.service.StockMonitorService;
import org.apache.commons.collections.ListUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
public class StockMonitorQuartz {

    @Resource
    private StockMonitorService stockMonitorService;

    private static final String STOCK_KEY = "STOCK_KEY";
    protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());


	//"0 0/1 9-15 ? * MON-FRI" 周一至周五的上午9点至15点每隔一分钟触发
//    @Scheduled(cron = "0/10 * * * * ?")
    @Scheduled(cron = "0 0/1 9-15 ? * MON-FRI")
	public void excute(){

        String url = "http://hq.sinajs.cn/list=";
        //从数据库取要监控的股票代码，以及监控的价格
        List<StockMonitor> stockList = stockMonitorService.findAll();
        for (StockMonitor stockMonitor : stockList) {
            url += stockMonitor.getStockCode();
            //发送请求获取最新的价格
            String s = HttpUtils.sendGet(url,null);
            String s1[] = s.split("=");
            String s2[] = s1[1].split(",");
            String stockName = s2[0].substring(1,s2[0].length());
            BigDecimal stockPrice = new BigDecimal(s2[3]);
            //如果价格满足要求，则发送短信
            if(stockPrice.compareTo(stockMonitor.getLowPrice())<=0 || stockPrice.compareTo(stockMonitor.getHighPrice())>=0){
                //发送邮件
                sendStockPriceMessage(stockName,s2[3],stockMonitor.getEmail());
            }
        }

	}
	
	@RequestMapping("/turnToPage")
	public ModelAndView turnToPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/stock/stock");
		return mav;
	}

    @RequestMapping("/addStockRecord")
    @ResponseBody
    public String addStockRecord(StockMonitor stockMonitor){
        JSONObject obj = new JSONObject();
        stockMonitorService.insertStock(stockMonitor);
        obj.put("mes", "发送成功!");
        return obj.toString();
    }


	
	@RequestMapping("/sendStockPriceMessage")
	@ResponseBody
	public String sendStockPriceMessage(String stockName,String currentPrice,String toEmail){
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
        String to = toEmail;
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

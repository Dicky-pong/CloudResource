package com.amazingfour.common.wxservice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.amazingfour.common.wxservice.util.SignUtil;
import com.amazingfour.common.wxservice.util.WxCoreService;


public class CoreServlet extends HttpServlet {

    protected static Logger log = Logger.getLogger(CoreServlet.class);
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	    //微信加密签名
	    String signature = request.getParameter("signature");
	    //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");
        
        PrintWriter out = response.getWriter();
        //请求效验,若效验成功则原样返回echostr,返回表示成功,否则表示失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)){
            log.info("doGet请求效验成功:"+SignUtil.checkSignature(signature, timestamp, nonce));
            out.print(echostr);
        }
        out.close();
        out = null;
    }
	

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //消息的接受、处理、响应
        
        //将请求、响应的编码均设置为UTF-8(防止中文乱码)
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        //接收参数：微信加密签名、时间戳、随机数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        
        //获取商户配置的UUID
        String USER_UUID = request.getParameter("UNION_ID");
        
        PrintWriter out = response.getWriter();
        log.error("signature:"+signature+"timestamp:"+timestamp+"nonce:"+nonce+"USER_UUID:"+USER_UUID);
        //请求检查
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            log.info("doPost请求效验成功:"+SignUtil.checkSignature(signature, timestamp, nonce));
            //调用核心服务类接受处理请求
            String respXml = WxCoreService.processRequest(request,USER_UUID);
            
            out.print(respXml);
        }
        out.close();
        out = null;
    }


}

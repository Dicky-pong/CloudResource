package com.amazingfour.crms.controller;

import com.alibaba.fastjson.JSONObject;
import com.amazingfour.common.shiro.token.manager.TokenManager;
import com.amazingfour.common.utils.ConstantUtil;
import com.amazingfour.common.utils.Encrypt;
import com.amazingfour.common.utils.LoggerUtils;
import com.amazingfour.common.utils.ResponseUtil;
import com.amazingfour.common.utils.StringUtils;
import com.amazingfour.common.utils.mail.Mail;
import com.amazingfour.common.utils.mail.MailUtils;
import com.amazingfour.common.utils.FIleTranslate;
import com.amazingfour.common.utils.qiniu.ConfigToken;
import com.amazingfour.common.utils.qiniu.MyUploadToken;
import com.amazingfour.crms.domain.Role;
import com.amazingfour.crms.domain.User;
import com.amazingfour.crms.service.MenuService;
import com.amazingfour.crms.service.OperationService;
import com.amazingfour.crms.service.RoleService;
import com.amazingfour.crms.service.UserService;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
/**
 * Created by kennyho on 2016/1/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    @Resource
    private OperationService operationService;
    
    @RequestMapping("/login")
    public String login(User entity,String verifyCode,Boolean rememberMe,HttpServletRequest request,HttpSession session){
    	try {
		 if (!(verifyCode.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
	            request.setAttribute("errorMsg", "验证码不正确");
	            return null;
	        }
		 	entity.setPassword(Encrypt.Encrypt_md5(entity.getPassword()));
		 	entity = TokenManager.login(entity,rememberMe);
			/**
			 * shiro 获取登录之前的地址
			 * 之前0.1版本这个没判断空。
			 */
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			String url = null ;
//			String basePath = request.getContextPath();//获取basePath
//			if(null != savedRequest.getRequestUrl() && savedRequest.getRequestUrl().startsWith(basePath)){
//				url = savedRequest.getRequestUrl().replaceFirst(basePath, "");
//			}else{
//				url = savedRequest.getRequestUrl();
//			}
			/**
			 * 我们平常用的获取上一个请求的方式，在Session不一致的情况下是获取不到的
			 * String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
			 */
//			LoggerUtils.fmtDebug(getClass(), "获取登录之前的URL:[%s]",url);
			//如果登录之前没有地址，那么就跳转到首页。
			//如果登录之前没有地址，那么就跳转到首页。
//			if(StringUtils.isBlank(url)){
				url = "list.htm";
//			}
			//设置用户信息Session
			User token = TokenManager.getToken();
			if(StringUtils.isBlank(token.getUserEmail()) || StringUtils.isBlank(token.getTelPhone())){
				token.setIsComplete(ConstantUtil.G_ZERO);			//用户未填写基本信息
			}else{
				token.setIsComplete(ConstantUtil.G_ONE);
			}
			session.setAttribute("currentUser", token);//登录的token
			//跳转地址
			return "redirect:"+url;
		/**
		 * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
		 */
		} catch (DisabledAccountException e) {
			 request.setAttribute("errorMsg", "帐号已经禁用");
		} catch (Exception e) {
			 request.setAttribute("errorMsg",  "帐号或密码错误");
		}
		return null;
    }

//用户登录
//    @RequestMapping("/login")
//    public String login(User user,String verifyCode,HttpServletRequest request,HttpSession session) {
//        ModelAndView mav = new ModelAndView();
//        if (!(verifyCode.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
//            request.setAttribute("errorMsg", "验证码不正确");
//            return "forward:/login.jsp";
//        }
//        String username = user.getUserName();//获取页面填入的用户名
//        String password = user.getPassword();//获取页面填入的密码
//        String s = Encrypt.Encrypt_md5(password);//将页面密码转化为密文
//        user.setPassword(s);
//        User resultUser = userService.login(user);
//        if(resultUser!=null){//判断是否从数据库里查出了数据
//            byte userState=resultUser.getUserState();
//            if(userState==1){
//                request.setAttribute("user", user);
//                request.setAttribute("blacklist","该用户已被拉黑，请联系管理员！");
//                return "forward:/login.jsp";
//            }else {
//                //HttpSession session = request.getSession();
//                /*
//                    1.将用户存入session
//                 */
//                session.setAttribute("currentUser", resultUser);
//                User u  =userService.findById(resultUser.getUserId());
////                Role role = u.getRole();
//                /*
//                    2.将角色存入session
//                 */
////                session.setAttribute("role",role);
////                List<Menu> menuList = menuService.getMenuById(Long.valueOf(role.getRoleId()));//查询到List<Menu>集合
////                for(Menu m : menu){
////                    System.out.println(m.getName());
////                }
//                /*
//                    3.将菜单存入session
//                 */
////                session.setAttribute("menuList",menuList);
//                /*
//                    4.将角色功能存入session
//                 */
////                List<Operation> operList = operationService.getOperbySubId(Long.valueOf(role.getRoleId()));
////                session.setAttribute("operList",operList);
//
//                return "redirect:/user/comInformation.htm";
//                /*if(resultUser.getUserName().equals(username)&&resultUser.getPassword().equals(s)){//判断数据库获得的数据与页面输入的数据是否相等
//                    HttpSession session = request.getSession();
//                    session.setAttribute("currentUser", resultUser);
//                    return "redirect:/user/list.htm";
//                }
//                else{
//                    return null;
//                }*/
//            }
//        }
//        else {
//            request.setAttribute("user", user);
//            request.setAttribute("errorMsg", "用户名或密码错误");
//            return "forward:/login.jsp";
//        }
//    }

 //判断是否补全信息
//    @RequestMapping(value = "/comInformation", method = RequestMethod.GET)
//    public String comInformation(String redirectURL, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("currentUser");
//        if(user.getUserEmail()==null||"".equals(user.getUserEmail())){
//            request.setAttribute("userMsg","1");    //用户未填写基本信息
//        }else if(user.getUserEmail()!=null){
//            if(user.getActivated()==0)
//            request.setAttribute("userMsg","2");     //用户未激活邮箱
//        }
//        List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
//        return "forward:/"+menuList.get(0).getUrl()+"";
//    }

//    //返回登录界面
//    @RequestMapping(value = "/relogin", method = RequestMethod.GET)
//    public ModelAndView relogin(String redirectURL, HttpServletRequest request) {
//        ModelAndView view = new ModelAndView();
//        //把拦截前路径存下来，以便登入成功可以直接请求到登录前的页面
//        view.addObject("redirectURL", redirectURL);
//        view.setViewName("/login");
//        return view;
//    }
    //从数据库中获取所有用户信息
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "page", required = false) String page,
            User user, HttpServletRequest request) {
    	
        ModelAndView mav = new ModelAndView();
//        HttpSession session = request.getSession();

//        int pageSize = 4; // 页容量
//
//        if (page == null || page == "") {
//            page = "1";
//
//        } /*else {
//
//            user = (User) session.getAttribute("user");
//
//        }*/
//        String userName=user.getUserName();
//
//        Byte userState=user.getUserState();
//        Map<String, Object> map = new HashMap<String, Object>(); // 使用Map传值到mapper处理
//        map.put("start", (Integer.parseInt(page) - 1) * pageSize); // 起始记录
//        map.put("size", pageSize);
//        map.put("userName",userName);
//        map.put("userState",userState);
//
//        List<User> userList = userService.find(map); //查询
//
//        int total = userService.count(user);
//
//        Map<String,Object> params =new HashMap<String,Object>();
//        params.put("userName",userName);//传入搜素字段
//        params.put("userState",userState);
//
//
//        String pageCode = PageUtil.getPagation("/user/list.htm",params, total, Integer.parseInt(page), pageSize);
//        mav.addObject("pageCode", pageCode);
//        mav.addObject("userList", userList);
        mav.setViewName("/index");
        mav.setViewName("/index");
        return mav;
    }

    //删除用户
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "userId") String userId,
                       HttpServletResponse response) {
        JSONObject result = new JSONObject();

        if (userService.delete(Long.parseLong(userId))){  //删除管理员
            result.put("msg", "success");
        }
        try {
            ResponseUtil.write(result, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用户添加前置
    @RequestMapping("/preinsert")
    public ModelAndView presave() {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Role> roleList = roleService.find(map); //查询出角色表的内容，方便页面取出角色名
        mav.addObject("roleList",roleList);
        mav.setViewName("user/addUser");//跳到指定页面
        return mav;
    }

    //编辑用户基本信息前置
    @RequestMapping("/preUserInfo")
    public ModelAndView preUserInfo(@RequestParam(value = "userId") String userId) {
        ModelAndView mav = new ModelAndView();
        User user1 = new User();
        user1.setUserId(Long.parseLong(userId));
        User user = userService.findOneById(user1);
        mav.addObject("user",user);
        mav.setViewName("user/updateUserInfo");//跳到指定页面
        return mav;
    }

    //修改用户基本信息
    @RequestMapping(value = "/upload")
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file,String userEmail,String telPhone,HttpServletRequest request,HttpServletResponse response) {
        UploadManager uploadManager = new UploadManager();
        ConfigToken ct = new ConfigToken();
        JSONObject obj = new JSONObject();
        User user = new User();
        user.setUserId(Long.parseLong(request.getParameter("userId")));
        //user.setUserEmail(userEmail);
        user.setTelPhone(Long.parseLong(telPhone));
        try {
            File newFile = FIleTranslate.MutiFileTranstoFile(request,file);
            // 调用put方法上传
            Response res = uploadManager.put(newFile, null, MyUploadToken.getMyUpToken());
            // 打印返回的信息
            JSONObject jsonObject = JSONObject.parseObject(res.bodyString());
            Map<String, Object> valueMap = new HashMap<String, Object>();
            valueMap.putAll(jsonObject);
            String img = ct.getDownloadToken(valueMap.get("key").toString());
            user.setImgUrl(img);
            if(userService.updateUserInifo(user)){
                HttpSession session = request.getSession();
                User currentUser = (User)session.getAttribute("currentUser");
                currentUser.setImgUrl(img);
                session.setAttribute("currentUser",currentUser);
                obj.put("mes", "更新成功!");
            }else{
                obj.put("mes", "更新失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.fmtError(getClass(), e, "更新用户信息失败，%s。", e.getMessage());
        }
        ResponseUtil.renderJson(response, obj.toString());
    }

    /**
     * 新增用户
     * @param
     * @return
     */
    @RequestMapping("/insert")
    public void insert(User user,HttpSession session,HttpServletResponse response) {
        JSONObject obj = new JSONObject();
        if (userService.existUserByName(user.getUserName())){//用户名不能相同
            session.setAttribute("user",user);
            //session.setAttribute("errorMsg2","保存失败，该用户已存在");
            obj.put("usertip",0);
            obj.put("mes","保存失败，该用户已存在!");
            //return "redirect:/user/preinsert.htm";
        }else {

            user.setPassword(Encrypt.Encrypt_md5(user.getPassword()));
            userService.insert(user);
            obj.put("usertip", 1);
            obj.put("mes", "保存成功!");
            //return "redirect:/user/list.htm";
        }
        ResponseUtil.renderJson(response, obj.toString());
    }
    //用户更新前置
    @RequestMapping("/preUpdate")
    public ModelAndView preUpdate(@RequestParam(value = "userId") String userId) {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> map = new HashMap<String, Object>();
        List<Role> roleList = roleService.find(map); //查询出角色表的内容，方便页面取出角色名
        mav.addObject("roleList",roleList);

        User user=new User();
        user = userService.findById(Long.parseLong(userId));//根据Id找出所选择用户的所有信息
        mav.addObject("user",user);
        mav.addObject("mainPage", "user/updateUser.jsp");
        mav.setViewName("user/updateUser");
        return mav;
    }

    //用户更新
    @RequestMapping("/update")
    public void update(User user, HttpServletResponse response) {
        JSONObject obj = new JSONObject();
        //user.setPassword(Encrypt.Encrypt_md5(user.getPassword()));
        userService.update(user);
        //return "redirect:/user/list.htm";
        obj.put("mes","更新成功!");
        ResponseUtil.renderJson(response,obj.toString());
    }

    //拉黑用户，将状态属性设置为1
    @RequestMapping("/defriend")
    public void defriend(@RequestParam(value = "userId") String userId,
                       HttpServletResponse response) {
        JSONObject result = new JSONObject();

        if (userService.defriend(Long.parseLong(userId))) {  //根据id将状图字段改为1，即拉黑
            result.put("msg", "ok");
        }
        try {
            ResponseUtil.write(result, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //解除用户黑名单，将状态属性设置为0
    @RequestMapping("/removeBlack")
    public void removeBlack(@RequestParam(value = "userId") String userId,
                         HttpServletResponse response) {
        JSONObject result = new JSONObject();

        if (userService.removeBlack(Long.parseLong(userId))) {  //根据id将状图字段改为1，即拉黑
            result.put("msg", "suc");
        }
        try {
            ResponseUtil.write(result, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //注销用户
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(){
    	try {
			TokenManager.logout();
			resultMap.put("status", 200);
		} catch (Exception e) {
			resultMap.put("status", 500);
			LoggerUtils.fmtError(getClass(), e, "退出出现错误，%s。", e.getMessage());
		}
		return JSONObject.toJSONString(resultMap);
    }

    //修改密码前置
    @RequestMapping("/passPre")
    public String passPre(){
        return "user/updatePassword";
    }

    //修改密码
    @RequestMapping("/updatepassword")
    public void updatePassword(User user, HttpServletResponse response,HttpServletRequest request,String password1,String password2) {
        JSONObject obj = new JSONObject();
        HttpSession session= request.getSession();//获取session实例
        User currentUser =  (User)session.getAttribute("currentUser");
        String userName=currentUser.getUserName();
        String oldPassword=currentUser.getPassword();//获取登录时的密码
        String inputPassword=Encrypt.Encrypt_md5(user.getPassword());//获取原密码并转化为加密形式
       if(oldPassword.equals(inputPassword)){//原密码与输入密码的比较
           if (password1.equals(password2)){//密码与确认密码的比较
               currentUser.setPassword(Encrypt.Encrypt_md5(password1));
               userService.updatePassword(currentUser);
               obj.put("mes","修改密码成功,请重新登录!");

           }else {
               obj.put("errorMsg", "输入密码不一致！请重新输入...");
           }
       } else {
           obj.put("error", "原密码输入错误！请重新输入...");
       }
        ResponseUtil.renderJson(response,obj.toString());
    }


    //绑定邮箱
    @RequestMapping("/bindEmail")
    public void bindEmail(User user,HttpServletResponse response) throws Exception{
        //判断邮箱是否已被绑定
        if(userService.existEmail(user)){
            ResponseUtil.renderText(response,"该邮箱已被绑定");
            return;
        }

        //开始绑定邮箱
        String key = UUID.randomUUID().toString(); //邮箱密钥，用于验证邮件的url。生成随机字母数字序列
        Date outDate = new Date(System.currentTimeMillis()+30*60*1000);    //设置邮件30分钟后过期
        //long date = outDate.getTime()/1000*1000;    //忽略毫秒数，mySql取出时间是忽略毫秒数的
        String sid = Encrypt.Encrypt_md5(user.getUserId()+"$"+ outDate.getTime()+"$"+key);   //数字签名
        user.setEmailKey(sid);
//        user.setOutDate(outDate);
        if(!userService.bindEmail(user)){
            ResponseUtil.renderText(response,"绑定邮箱失败，请重试！");
            return;
        }

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
        String to = user.getUserEmail();
        String subject = prop.getProperty("subject");
        // MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
        // 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "去死吧"); 返回“你好张三，你去死吧！”
        String content = MessageFormat.format(prop.getProperty("content"), sid,user.getUserId());
        Mail mail = new Mail(from, to, subject, content);

        // 发送邮件
        try {
            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ResponseUtil.renderText(response,"还差一步你就绑定邮箱啦，赶紧前往邮箱激活吧！");
    }

    //激活邮箱
    @RequestMapping("/activated")
    public ModelAndView activated(String sid,Long userId) {
        User user = new User();
        user.setUserId(userId);
        User oneUser = userService.findOneById(user);
        String tip = null;
        ModelAndView mav = new ModelAndView();

        if(oneUser==null){  //判断该用户是否存在
            tip = "该用户不存在!";
        }else if(oneUser.getOutDate().getTime() <= System.currentTimeMillis()) { //判断邮件链接是否过期
            tip = "链接已经过期，请重新申请绑定邮箱！";
        }else if(sid.equals(oneUser.getEmailKey())) {   //验证邮件链接的数字签名是否一致
            if(userService.activated(user)){    //激活邮箱
                tip = "恭喜你，你的邮箱已绑定成功！";
            }else{
                tip = "绑定邮箱失败，请重新申请绑定！";
            }
        }else{
            tip = "链接错误，请重新申请绑定邮箱！";
        }

        mav.addObject("tip",tip);
        mav.setViewName("user/emailTip");
        return mav;
    }

    //用邮箱找回密码
    @RequestMapping("/findPassByEmail")
    public void findPassByEmail(User user,HttpServletResponse response) throws Exception {
        switch (userService.existUserEmail(user)){
            case 0:
                ResponseUtil.renderText(response,"用户名不正确");
                return;
            case 1:
                ResponseUtil.renderText(response,"邮箱不正确或未绑定");
                return;
            default:
                break;
        }

        String key = UUID.randomUUID().toString(); //邮箱密钥，用于验证邮件的url
        Date outDate = new Date(System.currentTimeMillis()+30*60*1000);    //设置邮件30分钟后过期
        String sid = Encrypt.Encrypt_md5(user.getUserName()+"$"+outDate.getTime()+"$"+key);   //数字签名
        user.setEmailKey(sid);
        user.setOutDate(outDate);
        if(!userService.saveEmailMes(user)){
            ResponseUtil.renderText(response,"出现异常，请点击提交重试！");
            return;
        }

        //发邮件
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
        String to = user.getUserEmail();
        String subject = prop.getProperty("passSubject");
        String content = MessageFormat.format(prop.getProperty("contentPass"), sid,user.getUserName());
        Mail mail = new Mail(from, to, subject, content);
        // 发送邮件
        try {
            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResponseUtil.renderText(response,"邮件已发送，赶紧去邮箱找回密码吧!");
    }

    //重置密码前置
    @RequestMapping("/findPassPre")
    public ModelAndView findPassPre(String sid,String username){
        ModelAndView mav = new ModelAndView();

        User user = new User();
        user.setUserName(username);
        User oneUser = userService.findOneById(user);
        String tip = null;

        if(oneUser==null){  //判断该用户是否存在
            tip = username + "该用户不存在!";
        }else if(oneUser.getOutDate().getTime() <= System.currentTimeMillis()) { //判断邮件链接是否过期
            tip = "链接已经过期，请重新申请找回密码！";
        }else if (sid.equals(oneUser.getEmailKey())) {
            mav.addObject("username",username);
            mav.addObject("sid",sid);
            mav.setViewName("user/findPassword");
            return mav;
        }else{
            tip = "链接错误，请重新申请找回密码！";
        }

        mav.addObject("tip",tip);
        mav.setViewName("user/emailTip");
        return mav;
    }

    //重置密码
    @RequestMapping("/findPass")
    public void findPass(User user, HttpServletResponse response,String password1,String password2) {
        if (!password1.equals(password2)) {   //密码与确认密码的比较
            ResponseUtil.renderText(response,"两次密码输入不一致！请重新输入...");
            return;
        }

        String mes = null;
        User oneUser = userService.findOneById(user);

        if(oneUser==null){  //判断该用户是否存在
            mes = user.getUserName() + "该用户不存在!";
        }else if(oneUser.getOutDate().getTime() <= System.currentTimeMillis() ||
                !oneUser.getEmailKey().equals(user.getEmailKey())) { //再次匹配过期时间和数字签名，防止恶意重置他人密码
            mes = "找回密码操作可能已失效，请重新申请找回密码！";
        }else {
            user.setPassword(Encrypt.Encrypt_md5(password1));
            if(userService.updatePassword(user)){
                mes = "重置密码成功,请返回登录页面进行登录!";
            }else{
                mes = "重置密码失败，请重新输入!";
            }
        }

        ResponseUtil.renderText(response,mes);
    }

    /**
     * 批量删除用户
     *
     * @param userId
     * @param response
     */
    @RequestMapping("/delUsers")
    public void delUsers(String[] userId, HttpServletResponse response) {
        JSONObject obj = new JSONObject();

        //判断userId是否为空
        if (userId == null || userId.length == 0) {
            obj.put("mes", "请至少选择一项!");
            ResponseUtil.renderJson(response, obj.toString());
            return;
        }
        List<String> userIdList = new ArrayList<String>();
        Collections.addAll(userIdList,userId);//将前端用户ID由数组类型转化为list
          int rows = userService.deleteBatch(userIdList);
            int len = userId.length;
            if (rows == 0) {
                obj.put("mes", "用户删除失败!");
            } else if (rows < len) {
                obj.put("mes", "部分删除失败!");
            } else if (rows == len) {
                obj.put("mes", "删除成功!");
            } else {
                obj.put("mes", "未知错误!");
            }

        ResponseUtil.renderJson(response, obj.toString());
    }
    
    /**
	 * 登录跳转
	 * @return
	 */
	@RequestMapping(value="shiroLogin",method=RequestMethod.GET)
	public ModelAndView shiroLogin(){
		return new ModelAndView("user/login");
	}
	
}

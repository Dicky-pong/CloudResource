//package com.amazingfour.crms.controller.mobile;
//
//import com.amazingfour.common.shiro.token.manager.TokenManager;
//import com.amazingfour.common.utils.ConstantUtil;
//import com.amazingfour.common.utils.Encrypt;
//import com.amazingfour.common.utils.StringUtils;
//import com.amazingfour.crms.domain.User;
//import org.apache.shiro.authc.DisabledAccountException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping("/mobile/")
//public class MobileController {
//
//    /**
//     * 进入移动端登陆页面
//     * @param request
//     * @return
//     */
//    @RequestMapping("login")
//    public ModelAndView login(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/mobile/login2");
//        return mav;
//    }
//
//	/**
//	 * 进入移动端主页
//	 * @param request
//	 * @return
//	 */
//    @RequestMapping("index")
//    public ModelAndView list(HttpServletRequest request,User entity,Boolean rememberMe) {
//
//        try {
////            if (!(verifyCode.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
////                request.setAttribute("errorMsg", "验证码不正确");
////                return null;
////            }
//            entity.setPassword(Encrypt.Encrypt_md5(entity.getPassword()));
//            entity = TokenManager.login(entity,rememberMe);
//
//            String surl = "list.htm";
////			}
//            //设置用户信息Session
//            User token = TokenManager.getToken();
//            if(StringUtils.isBlank(token.getUserEmail()) || StringUtils.isBlank(token.getTelPhone())){
//                token.setIsComplete(ConstantUtil.G_ZERO);			//用户未填写基本信息
//            }else{
//                token.setIsComplete(ConstantUtil.G_ONE);
//            }
//            session.setAttribute("currentUser", token);//登录的token
//            //跳转地址
//            return "redirect:"+url;
//            /**
//             * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
//             */
//        } catch (DisabledAccountException e) {
//            request.setAttribute("errorMsg", "帐号已经禁用");
//        } catch (Exception e) {
//            request.setAttribute("errorMsg",  "帐号或密码错误");
//        }
//        return null;
//    }
//
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/mobile/mobileIndex");
//        return mav;
//    }
//
//
//
//
//}

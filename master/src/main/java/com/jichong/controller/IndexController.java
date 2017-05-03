package com.jichong.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.jichong.config.WxMpConfig;
import com.jichong.service.WeixinService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xl on 2017/5/3.
 */
@Controller
@RequestMapping("/entry")
public class IndexController {

    private Map<String,String> map=new HashMap<>();
    @Autowired
    WxMpConfig wxMpConfig;

    @Autowired
    WeixinService weixinService;
    @RequestMapping("/login")
    public void auth(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        // qrcode

        String r_url= URLEncoder.encode("http://wx.jichong.space/entry/auth?flag="+ UUID.randomUUID().toString().substring(0,6));

        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ wxMpConfig.getAppid()+"&redirect_uri="+r_url+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";


        String text = url;
        int width = 200;
        int height = 200;
        String format = "png";
        Hashtable hints= new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);
//        File outputFile = new File("new.png");
//        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        MatrixToImageWriter.writeToStream(bitMatrix,format,resp.getOutputStream());

    }

    @RequestMapping("/auth")
    public void getWxUser(HttpServletRequest req, HttpSession session,HttpServletResponse resp) throws Exception{
        // qrcode
        String code=req.getParameter("code");
        String flag=req.getParameter("flag");
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = weixinService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        session.setAttribute("flag",flag);
        session.setAttribute("openid",openId);
       req.getRequestDispatcher("/WEB-INF/jsp/ensure.jsp");



    }

    @RequestMapping("/ensureAuth")
    public void ensureAuth(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String ensure = req.getParameter("ensure");
        if("1".equals(ensure)){
            String  openid =(String) req.getSession().getAttribute("openid");
            String  flag =(String) req.getSession().getAttribute("flag");
            if(flag!=null&&openid!=null){
                map.put(flag,openid);
            }
            resp.getWriter().print("授权成功");
        }else {
            resp.getWriter().print("取消授权");
        }

    }



    @RequestMapping("/check")
    public void check(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        String flag=req.getParameter("flag");
        String openid = map.get("flag");
        JSONObject msg=new JSONObject();
        msg.put("code",0);
        if(openid!=null){
            msg.put("code",1);
            msg.put("openid",openid);
            req.getSession().setAttribute("user",openid);
        }
        resp.getWriter().print(msg.toJSONString());


    }



    @RequestMapping("/index")
    public void index(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        Object user = req.getSession().getAttribute("user");
        if(user!=null){
            req.setAttribute("user",user);
            req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req,resp);
        }

    }
}

package com.jichong.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jichong.service.WeixinService;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractBuilder {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public abstract WxMpXmlOutMessage build(String content,
      WxMpXmlMessage wxMessage, WeixinService service) ;
}

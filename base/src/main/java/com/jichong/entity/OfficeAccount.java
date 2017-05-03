package com.jichong.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 2017/4/23.
 */
@Getter
@Setter
@ToString
public class OfficeAccount extends BaseDomain{

    private String original_id;
    private String token;
    private String appid;
    private String appSecret;
    private String aesKey;
    private int status;


}

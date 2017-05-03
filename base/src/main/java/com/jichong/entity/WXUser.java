package com.jichong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by xl on 2017/5/3.
 *
 *  "_id" : "581ab3421a0e6137391e2820",
 "sex" : NumberInt(1),
 "nickname" : "pastry",
 "remark" : "",
 "original_id" : "gh_c23eed399388",
 "city" : "深圳",
 "country" : "中国",
 "subscribe_time" : "2016-12-13 14:58:09",
 "tagid_list" : [
 NumberInt(101)
 ],
 "subscribe" : NumberInt(0),
 "province" : "广东",
 "openid" : "oMaT6wu80r7mXZ_SgYU2mRsxWj3o",
 "groupid" : NumberInt(0),
 "language" : "zh_CN",
 "cdate" : "2016-12-13 14:58:09",
 "headimgurl" : "http://wx.qlogo.cn/mmopen/Q3auHgzwzM5pibkHv9zFSoQ4xDEFagy074JKBp4R7g2TgiccPWwpf7RuoXbGPeBnnF8GRpQTVDJ75eicqniatjlGfg5pxQcwIGMfp4wysNltx14/0",
 "sourceType" : "584f9bf11a0e61550eef0b65",
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WXUser extends BaseDomain{

    private Boolean subscribe;
    private String openId;
    private String nickname;
    private String sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headImgUrl;
    private Long subscribeTime;
    private String unionId;
    private Integer sexId;
    private String remark;
    private Integer groupId;
    private Long[] tagIds;

}

package com.fire.back.constant;

public class WxConstant {
    public static String appid = "wxdc895503894e9672";

    public static String appSecret = "68be338c2dacaaa70afe21b591e1ed56";
    //登陆请求地址
    public static String loginUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appSecret+"&grant_type=authorization_code&js_code=";
    //统一下单接口
    public static String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //支付成功后回调地址
    public static final String notifyUrl = "https://api.weixin.qq.com/sns/jscode2session";
    //微信支付主体
    public static final String title = "365集运";
    //交易类型
    public static final String tradeType = "JSAPI";
    //微信商户号
    public static String mchid = "";
    //微信商户密钥
    public static String mchkey = "";
    //签名方式
    public static String signType = "MD5";
    //失败"FAIL"
    public static final String FAIL = "FAIL";
    //成功"SUCCESS"
    public static final String SUCCESS  = "SUCCESS";
    //请求超时
    public static final String TIMEOUT = "TIMEOUT";


}

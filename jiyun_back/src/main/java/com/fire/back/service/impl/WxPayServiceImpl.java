package com.fire.back.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fire.back.common.FireResult;
import com.fire.back.config.PayConfig;
import com.fire.back.constant.WxConstant;
import com.fire.back.dao.UserTbMapper;
import com.fire.back.dao.WxPayTradeMapper;
import com.fire.back.entity.UserTb;
import com.fire.back.entity.WxPayTrade;
import com.fire.back.service.WxPayService;
import com.fire.back.util.StringUtils;
import com.fire.back.util.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class WxPayServiceImpl implements WxPayService {

    private Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    WxPayTradeMapper wxPayTradeMapper;
    @Autowired
    UserTbMapper userTbMapper;
    @Autowired
    PayConfig payConfig;

    /**
     * 微信支付
     * @param request
     * @param userId
     * @param amount
     * @return
     */
    @Override
    public Object wxPay(HttpServletRequest request, Long userId, String amount) {
        //查询用户信息
        UserTb user = userTbMapper.selectByPrimaryKey(userId);
        if(null == user || StringUtils.isEmpty(user.getOpenId())){
            return FireResult.build(0,"用户不存在",null);
        }
        String openid = user.getOpenId();
        try {
            //调用微信统一下单接口
            Map<String,String> resMap = wxUnifiedOrder(openid,amount);
            //获取请求结果
            String returnCode = (String)resMap.get("return_code");
            //给移动端返回参数
            SortedMap<String,String> response = new TreeMap<>();
            if(WxConstant.SUCCESS.equals(returnCode) && WxConstant.SUCCESS.equals(resMap.get("result_code"))){
                response.put("appId",payConfig.getAppid());
                response.put("timeStamp",new Long(System.currentTimeMillis()).toString());
                response.put("nonceStr",WxUtils.getRandomStringByLength(32));
                response.put("package","prepay_id="+resMap.get("prepay_id"));
                response.put("signType","MD5");
                response.put("sign",WxUtils.createSign(response,WxConstant.mchkey));
                response.put("returnCode","SUCCESS");
                response.put("returnMsg","OK");
                logger.info("【小程序支付】统一下单成功，返回参数：" + response);
                return FireResult.build(1,"统一下单成功",response);
            }else{
                //请求失败
                return FireResult.build(0,"统一下单失败",null);
            }
        }catch (Exception e){
            return FireResult.build(0,"统一下单接口异常");
        }
    }
    /**
     * 微信统一下单接口
     * @param openid
     * @param totalFee
     * @return
     */
    private Map<String,String> wxUnifiedOrder(String openid,String totalFee) throws Exception{
        //封装请求参数
        SortedMap<String,String> paramMap = new TreeMap<>();
        paramMap.put("appid", payConfig.getAppid());
        paramMap.put("mch_id",payConfig.getMchid());
        paramMap.put("nonce_str",WxUtils.getRandomStringByLength(32));
        paramMap.put("body",WxConstant.title);
        paramMap.put("out_trade_no",WxUtils.createOrderNo());
        paramMap.put("total_fee",WxUtils.changeY2F(totalFee));
        paramMap.put("spbill_create_ip",WxUtils.getLocalIp());
        paramMap.put("notify_url",WxConstant.notifyUrl);
        paramMap.put("trade_type",WxConstant.tradeType);
        paramMap.put("openid",openid);
        //签名
        paramMap.put("sign",WxUtils.createSign(paramMap,payConfig.getMchkey()));
        //将参数转换成xml格式
        String xmlParam = WxUtils.mapToXml(paramMap);
        logger.info("【微信小程序支付】统一下单请求参数xml：" + xmlParam);
        //发送支付请求
        String result = WxUtils.httpRequest(WxConstant.payUrl,"POST",xmlParam);
        logger.info("【微信小程序支付】统一下单请求结果：" + result);
        //将请求结果转换成map
        Map<String,String> resultMap = WxUtils.xmlToMap(result);
        //保存请求记录

        //返回map结果
        return resultMap;
    }


}

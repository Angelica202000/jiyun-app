package com.fire.back.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface WxPayService {

    public Object wxPay(HttpServletRequest request,Long userId, String amount);
}

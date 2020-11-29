package com.fire.back.controller.app;

import java.util.Map;

import com.fire.back.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fire.back.common.FireResult;
import com.fire.back.entity.UserTb;
import com.fire.back.service.UserService;
import com.fire.back.util.ParamUtil;

/**
 * @Title: UserController.java
 * @Description: 小程序用户信息
 * @author CloudDragon
 * @date 2020-02-23 10:35:14
 */
@RestController
@RequestMapping("/wx/user")
public class UserController {

    @Autowired
    UserService us;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     *
     * @param paramMap ->userId
     * @return data{UserTb}
     */
    @PostMapping("/getUserInfo")
    public FireResult getUserInfo(@RequestBody Map<String,Object> paramMap){
        try {
            Long userId = ParamUtil.getLong(paramMap,"userId");
            UserTb user =us.getUserInfobByPrimaryKey(userId);
            return FireResult.build(1,"用户信息获取成功",user);
        }catch(Exception e){
            logger.error("获取用户信息失败",e);
            return FireResult.build(0, "获取用户信息失败，请稍后再试");
        }
    }

    /**
     * 更新用户信息
     * @param  paramMap ->idCardNumber,mobile，name
     * @return data{null}
     */
    @PostMapping("/updateUserInfo")
    public FireResult updateUserInfo(@RequestBody  Map<String, Object> paramMap){
        try {
            Long id = ParamUtil.getLong(paramMap,"userId");
            //String idCardNumber = ParamUtil.getString(paramMap,"idCardNumber",null);
            String mobile = ParamUtil.getString(paramMap,"mobile",null);
            //String nickName = ParamUtil.getString(paramMap,"nickName",null);
            String realName = ParamUtil.getString(paramMap,"realName",null);
            Integer gender = ParamUtil.getInteger(paramMap,"gender",null);
            UserTb u = new UserTb();
            u.setId(id);
            //u.setIdCardNumber(idCardNumber);
            u.setMobile(mobile);
            //u.setNickName(nickName);
            u.setRealName(realName);
            u.setGender(gender);
            if(StringUtils.isNotEmpty(u.getRealName()) &&
                StringUtils.isNotEmpty(u.getMobile())){
                u.setIsFullInfo(1);
            }else{
                u.setIsFullInfo(0);
            }
            int result = us.UpdateUserInfo(u);
            return result>0?FireResult.build(1,"用户信息更新成功"):FireResult.build(0,"用户信息更新失败");
        }catch(Exception e){
            logger.error("用户信息更新失败",e);
           return FireResult.build(0,"用户信息更新异常，请稍后再试");
        }
    }
}

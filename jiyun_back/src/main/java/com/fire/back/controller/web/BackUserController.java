package com.fire.back.controller.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fire.back.common.FireResult;
import com.fire.back.entity.AddressTb;
import com.fire.back.entity.UserTb;
import com.fire.back.service.AddressService;
import com.fire.back.service.UserService;
import com.fire.back.util.ParamUtil;

@Controller
@RequestMapping("/back/user")
public class BackUserController extends BaseController{
    final
    UserService us;
    final
    AddressService as;
    private Logger logger = LoggerFactory.getLogger(BackUserController.class);

    public BackUserController(UserService us,AddressService as) {
        this.us = us;
        this.as = as;
    }

    @GetMapping("/")
    @RequiresPermissions("common:user:view")
    public String index(){
        return "user/user";
    }

    /**
     * 分页查询用户列表
     * @param user 可选则查询 name,idCardNumber,mobile,state,type,roleId,sex,address,
     *                  orgName,oldName,oldMobile,school,isDelete
     */
    @PostMapping("/list")
    @ResponseBody
    @RequiresPermissions("common:user:list")
    public FireResult getUsersList(@RequestBody UserTb user){
        try {
            user.setIsDelete(0);
            List<UserTb> list = us.selectUsersByPage(user);
            int count = us.selectUsersCount(user);
            return FireResult.build(1,"查询用户列表成功",list,count);
        } catch (Exception e) {
            logger.error("用户列表查询异常",e);
            e.printStackTrace();
            return FireResult.build(0,"查询用户列表失败");
        }
    }
    
    /**
     * 分页查询用户列表
     * @param user 可选则查询 name,idCardNumber,mobile,state,type,roleId,sex,address,
     *                  orgName,oldName,oldMobile,school,isDelete
     */
    @PostMapping("/getAddressList")
    @ResponseBody
    @RequiresPermissions("common:user:list")
    public FireResult getAddressList(@RequestBody Map<String, Object> paramMap){
        try {
			Long userId = ParamUtil.getLong(paramMap, "userId");
            List<AddressTb> list = as.selectAddressByUser(userId);
            return FireResult.build(1,"查询用户列表成功",list);
        } catch (Exception e) {
            logger.error("用户列表查询异常",e);
            e.printStackTrace();
            return FireResult.build(0,"查询用户列表失败");
        }
    }

}

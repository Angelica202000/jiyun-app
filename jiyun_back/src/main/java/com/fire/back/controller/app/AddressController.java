package com.fire.back.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.fire.back.common.FireResult;
import com.fire.back.entity.AddressTb;
import com.fire.back.service.AddressService;
import com.fire.back.util.ParamUtil;
import com.fire.back.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 小程序地址接口
 *  根据id查询地址信息
 *  根据userId查询地址列表
 *  更新地址信息
 *  批量更新地址信息（设置默认地址）
 *  删除地址信息
 */
@RestController
@RequestMapping("/wx/address")
public class AddressController {

    @Autowired
    AddressService as;

    private Logger logger = LoggerFactory.getLogger(AddressController.class);

    /**
     * 根据id获取收获地址信息
     * @param paramMap
     * @return
     */
    @PostMapping("/getAddressInfo")
    public FireResult getAddressInfo(@RequestBody Map<String,Object> paramMap){
        try{
            Long addressId = ParamUtil.getLong(paramMap,"addressId");
            AddressTb addressInfo = as.getAddressInfoByPrimaryKey(addressId);
            return FireResult.build(1,"获取收获地址信息成功",addressInfo);
        }catch (Exception e){
            logger.error("获取收货地址信息失败",e);
            return FireResult.build(0,"获取收货地址信息异常，请稍后再试");
        }
    }

    /**
     * 获取用户的地址列表
     * @param paramMap
     * @return
     */
    @PostMapping("/getAddressList")
    public FireResult getAddressList(@RequestBody Map<String,Object> paramMap){
        try{
            Long userId = ParamUtil.getLong(paramMap,"userId");
            if(userId == null){
                return FireResult.build(0,"请求超时",null);
            }
            List<AddressTb> addressTbs = as.selectAddressByUser(userId);
            return FireResult.build(1,"获取用户地址信息成功",addressTbs);
        }catch (Exception e){
            logger.error("获取用户地址信息失败",e);
            return FireResult.build(0,"获取用户地址信息异常，请稍后再试");
        }
    }
    @PostMapping("/addAddress")
    public FireResult addAddress(@RequestBody Map<String,Object> paramMap){
        try{
            Long userId = ParamUtil.getLong(paramMap,"userId");
            if(userId == null){
                return FireResult.build(0,"请求超时",null);
            }
            String realName = ParamUtil.getString(paramMap,"realName");
            String mobile = ParamUtil.getString(paramMap,"mobile");
            String position = ParamUtil.getString(paramMap,"position");
            String clearance = ParamUtil.getString(paramMap,"clearance");
            AddressTb addressTb = new AddressTb();
            addressTb.setRealName(realName);
            addressTb.setUserId(userId);
            addressTb.setMobile(mobile);
            addressTb.setPosition(position);
            addressTb.setClearance(clearance);
            if(as.insertAddress(addressTb)){
                return FireResult.build(1,"添加成功",null);
            }else{
                return FireResult.build(0,"添加地址异常");
            }
        }catch (Exception e){
            logger.error("新增地址异常",e);
            return FireResult.build(0,"添加地址异常");
        }
    }
    @PostMapping("/saveAddressInfo")
    public FireResult saveAddressInfo(@RequestBody Map<String,Object> paramMap){
        try{
            Long id = ParamUtil.getLong(paramMap,"id");
            String realName = ParamUtil.getString(paramMap,"realName");
            String mobile = ParamUtil.getString(paramMap,"mobile");
            String position = ParamUtil.getString(paramMap,"position");
            String clearance = ParamUtil.getString(paramMap,"clearance");
            AddressTb addressTb = new AddressTb();
            addressTb.setId(id);
            addressTb.setRealName(realName);
            addressTb.setMobile(mobile);
            addressTb.setPosition(position);
            addressTb.setClearance(clearance);
            boolean result = as.updateAddressInfo(addressTb);
            if(result){
                return FireResult.build(1,"更新地址信息成功",null);
            }else {
                return FireResult.build(0,"更新地址信息异常",null);
            }
        }catch (Exception e){
            logger.error("更新用户地址信息失败",e);
            return FireResult.build(0,"更新用户地址信息异常，请稍后再试");
        }
    }

    @PostMapping("/deleteAddress")
    public FireResult deleteAddress(@RequestBody Map<String,Object> paramMap){
        try{
            Long id = ParamUtil.getLong(paramMap,"id");
            AddressTb addressTb = as.getAddressInfoByPrimaryKey(id);
            if(addressTb.getIsDefault() == 1){
                return FireResult.build(2,"无法删除默认地址",null);
            }
            if(as.deleteAddress(id)){
                return FireResult.build(1,"删除地址成功",null);
            }else{
                return FireResult.build(0,"删除地址异常");
            }
        }catch (Exception e){
            logger.error("删除地址异常",e);
            return FireResult.build(0,"删除地址异常");
        }
    }
    @PostMapping("/updateAll")
    public FireResult updateAll(@RequestBody Map<String,Object> paramMap){
        try {
            String param = ParamUtil.getString(paramMap,"param");
            List<AddressTb> addressTbs = JSONArray.parseArray(param,AddressTb.class);
            int result = as.updateAll(addressTbs);
            if(result > 0){
                return FireResult.build(1,"设置成功",null);
            }else{
                return FireResult.build(0,"设置默认地址失败",null);
            }
        }catch (Exception e){
            logger.error("更新默认地址失败",e);
            return FireResult.build(0,"更新默认地址失败");
        }

    }

    /**
     * 获取用户默认地址
     */
    @PostMapping("/getDefaultAddress")
    public FireResult getDefaultAddress(@RequestBody Map<String,Object> paramMap){
        try {
            Long userId = ParamUtil.getLong(paramMap,"userId");
            if(userId == null){
                return FireResult.build(0,"请求超时",null);
            }
            AddressTb addressTb = as.getDefaultAddress(userId);
            return FireResult.build(1,"获取地址成功",addressTb);
        }catch (Exception e){
            logger.error("获取默认地址异常",e);
            return FireResult.build(0,"获取默认地址异常");
        }

    }

}

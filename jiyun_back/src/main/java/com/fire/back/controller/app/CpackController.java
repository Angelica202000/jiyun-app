package com.fire.back.controller.app;

import com.fire.back.common.FireResult;
import com.fire.back.entity.CpackTb;
import com.fire.back.service.CpackService;
import com.fire.back.util.ParamUtil;
import org.apache.ibatis.annotations.Param;
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
 * 子包裹controller
 * 添加子包裹
 * 查询子包裹信息
 * 删除子包裹
 *
 */
@RestController
@RequestMapping("/wx/cpack")
public class CpackController {

    private Logger logger = LoggerFactory.getLogger(CpackController.class);

    @Autowired
    CpackService cs;

    /**
     * 添加子包裹信息
     * @param param
     * @return
     */
    @PostMapping("/addCpack")
    public FireResult addCpack(@RequestBody Map<String,Object> param){
        try{
            String deliver = ParamUtil.getString(param,"deliver");
            String deliverNumber = ParamUtil.getString(param,"deliverNumber");
            String good = ParamUtil.getString(param,"goods");
            String goodNumber = ParamUtil.getString(param,"goodsNumber");
            String worth = ParamUtil.getString(param,"worth");
            Long packId = ParamUtil.getLong(param,"packId");
            String buyUrl = ParamUtil.getString(param,"buyUrl");
            CpackTb cpackTb = new CpackTb();
            cpackTb.setDeliver(deliver);
            cpackTb.setDeliverNumber(deliverNumber);
            cpackTb.setGoods(good);
            cpackTb.setGoosNumber(goodNumber);
            cpackTb.setWorth(worth);
            cpackTb.setPackId(packId);
            cpackTb.setBuyUrl(buyUrl);
            int result = cs.addCpackInfo(cpackTb);
            if(result > 0){
                return FireResult.build(1,"添加成功",null);
            }else{
                return FireResult.build(0,"添加子包裹异常");
            }
        }catch (Exception e){
            logger.error("添加子包裹异常",e);
            return FireResult.build(0,"添加子包裹异常");
        }
    }

    /**
     * 查询子包裹信息
     * @param param
     * @return
     */
    @PostMapping("/getCpackInfo")
    public FireResult getCpackInfo(@RequestBody Map<String,Object> param){
        try{
            Long cpackId = ParamUtil.getLong(param,"cpackId");
            if(cpackId == null){
                return FireResult.build(0,"查询子包裹信息异常",null);
            }
            CpackTb cpackTb = cs.getCPackInfo(cpackId);
            return FireResult.build(1,"查询子包裹信息成功",cpackTb);
        }catch (Exception e){
            logger.error("查询子包裹信息异常",e);
            return FireResult.build(0,"查询子包裹信息异常");
        }
    }

    /**
     * 删除子包裹
     * @param param
     * @return
     */
    @PostMapping("/deleteCpack")
    public FireResult deleteCpack(@RequestBody Map<String,Object> param){
        try{
            Long cpackId = ParamUtil.getLong(param,"cpackId");
            int result = cs.deleteCpackInfo(cpackId);
            if(result > 0){
                return FireResult.build(1,"删除子包裹成功");
            }else{
                return FireResult.build(0,"删除子包裹异常");
            }

        }catch (Exception e){
            logger.error("查询子包裹信息异常",e);
            return FireResult.build(0,"查询子包裹信息异常");
        }
    }

    /**
     * 获取包裹下的子包裹列表
     * @param param
     * @return
     */
    @PostMapping("/getCpackList")
    public FireResult getCpackList(@RequestBody Map<String,Object> param){
        try{
            Long packId = ParamUtil.getLong(param,"packId");
            List<CpackTb> cpackList = cs.getCpackList(packId);
            return FireResult.build(1,"删除子包裹成功",cpackList);
        }catch (Exception e){
            logger.error("查询子包裹信息异常",e);
            return FireResult.build(0,"查询子包裹信息异常");
        }
    }

}

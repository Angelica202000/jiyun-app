package com.fire.back.controller.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fire.back.common.FireResult;
import com.fire.back.entity.PackTb;
import com.fire.back.service.PackService;
import com.fire.back.util.ParamUtil;

/**
 * 小程序地址接口
 *  根据id查询地址信息
 *  根据userId查询地址列表
 *  更新地址信息
 *  批量更新地址信息（设置默认地址）
 *  删除地址信息
 */
@RestController
@RequestMapping("/wx/pack")
public class PackController {

    @Autowired
    PackService ps;

    private Logger logger = LoggerFactory.getLogger(PackController.class);
    
    @PostMapping("/addPack")
    public FireResult addPack(@RequestBody Map<String,Object> paramMap){
        try{
            Long userId = ParamUtil.getLong(paramMap,"userId");
            if(userId == null){
                return FireResult.build(0,"请求超时",null);
            }
            String realName = ParamUtil.getString(paramMap,"realName");
            String mobile = ParamUtil.getString(paramMap,"mobile");
            String position = ParamUtil.getString(paramMap,"position");
            String clearance = ParamUtil.getString(paramMap,"clearance");
            String packType = ParamUtil.getString(paramMap,"packType");
            String packNo = createPackNo();
            Long resultId = ps.addPack(userId, realName,mobile, position, clearance,packType,packNo);
            return FireResult.build(1,"添加"+(resultId>0?"成功":"包裹异常"),resultId);
        }catch (Exception e){
            logger.error("新增包裹异常",e);
            return FireResult.build(0,"新增包裹异常");
        }
    }

    @PostMapping("/submitPack")
    public FireResult submitPack(@RequestBody Map<String,Object> paramMap){
        try{
            Long id = ParamUtil.getLong(paramMap,"packId");
            int result = ps.changeState(id, 1);
            return FireResult.build(result,"修改"+(result>0?"成功":"包裹异常"));
        }catch (Exception e){
            logger.error("修改包裹异常",e);
            return FireResult.build(0,"修改包裹异常");
        }
    }

    /**
     * 删除包裹
     * @param paramMap
     * @return
     */
    @PostMapping("/deletePack")
    public FireResult deletePack(@RequestBody Map<String,Object> paramMap){
        try{
            Long id = ParamUtil.getLong(paramMap,"packId");
            int result = ps.deletePack(id);
            return FireResult.build(result,"删除"+(result>0?"成功":"包裹异常"));
        }catch (Exception e){
            logger.error("删除包裹异常",e);
            return FireResult.build(0,"删除包裹异常");
        }
    }
    @PostMapping("/getPackList")
    public FireResult getPackLists(@RequestBody Map<String,Object> paramMap){
        try{
            Long userId = ParamUtil.getLong(paramMap,"userId");
            if(userId == null){
                return FireResult.build(0,"请求超时",null);
            }
            Map<String,List<PackTb>> map = new HashMap<>();
            map.put("0", ps.getPackListByStatus(userId, 0));
            map.put("1", ps.getPackListByStatus(userId, 1));
            map.put("2", ps.getPackListByStatus(userId, 2));
            map.put("3", ps.getPackListByStatus(userId, 3));
            map.put("4", ps.getPackListByStatus(userId, 4));
            map.put("5", ps.getPackListByStatus(userId, 5));
            return FireResult.build(1,"获取包裹列表成功",map);
        }catch (Exception e){
            logger.error("获取包裹列表异常",e);
            return FireResult.build(0,"获取包裹列表异常");
        }
    }

    @PostMapping("/getPackInfo")
    public FireResult getPackInfo(@RequestBody Map<String,Object> paramMap){
        try{
            Long packId = ParamUtil.getLong(paramMap,"packId");
            PackTb packTb = ps.getPackInfo(packId);
            return FireResult.build(1,"获取包裹成功",packTb);
        }catch (Exception e){
            logger.error("获取包裹异常",e);
            return FireResult.build(0,"获取包裹异常");
        }
    }

    /**
     * 生成32位订单号
     * @return
     */
    private String createPackNo(){
        //获取当前时间
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String sysdate = sdf.format(new Date());

        return "365PK" + sysdate;
    }
}

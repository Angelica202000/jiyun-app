package com.fire.back.service.impl;

import com.fire.back.dao.CpackTbMapper;
import com.fire.back.entity.CpackTb;
import com.fire.back.entity.CpackTbExample;
import com.fire.back.service.CpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CpackServiceImpl implements CpackService {

    @Autowired
    CpackTbMapper cm;

    @Override
    public CpackTb getCPackInfo(Long cpackId) {
        return cm.selectByPrimaryKey(cpackId);
    }

    @Override
    public int addCpackInfo(CpackTb cpackTb) {
        cpackTb.setIsDelete(0);
        return cm.insertSelective(cpackTb);
    }

    @Override
    public int deleteCpackInfo(Long cpackId) {
        return cm.deleteByPrimaryKey(cpackId);
    }

    @Override
    public List<CpackTb> getCpackList(Long packId) {
        CpackTbExample example = new CpackTbExample();
        CpackTbExample.Criteria criteria = example.createCriteria();
        criteria.andPackIdEqualTo(packId);
        example.setOrderByClause("create_time desc");
        return cm.selectByExample(example);
    }

    /**
     * 子包裹信息补全
     * @param cpackTb
     * @return
     */
    private CpackTb CpackDefaultParamUtil(CpackTb cpackTb){
        cpackTb.setCreateTime(System.currentTimeMillis()/1000);
        cpackTb.setIsDelete(0);
        return cpackTb;
    }
}

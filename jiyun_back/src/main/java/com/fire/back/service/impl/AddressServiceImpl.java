package com.fire.back.service.impl;

import com.fire.back.common.CheckEmptyUtil;
import com.fire.back.dao.AddressTbMapper;
import com.fire.back.entity.AddressTb;
import com.fire.back.entity.AddressTbExample;
import com.fire.back.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressTbMapper am;

    @Override
    public AddressTb getAddressInfoByPrimaryKey(Long id) {
        return am.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateAddressInfo(AddressTb ad) {
        ad.setUpdateTime(System.currentTimeMillis()/1000);
        int result = am.updateByPrimaryKeySelective(ad);
        return result>0? true:false;
    }


    @Override
    public boolean insertAddress(AddressTb ad) {
        return am.insertSelective(AddressDefaultParamUtil(ad))>0? true:false;
    }

    @Override
    public int selectAddressCount(AddressTb ad) {
        return 0;
    }


    @Override
    public List<AddressTb> selectAddressByPage(AddressTb ad) {
        return null;
    }

    @Override
    public List<AddressTb> selectAddressByUser(Long userId) {
        AddressTbExample example = new AddressTbExample();
        AddressTbExample.Criteria criteria = example.createCriteria();
        if(CheckEmptyUtil.isNotEmpty(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        example.setOrderByClause("is_default desc");
        return am.selectByExample(example);
    }

    @Override
    public int updateAll(List<AddressTb> addressTbs) {
        //累计更新的记录数
        int count = 0;
        if(CheckEmptyUtil.isEmpty(addressTbs)){
            return 0;
        }
        AddressTbExample example = new AddressTbExample();
        for(AddressTb addressTb : addressTbs){
            addressTb.setUpdateTime(System.currentTimeMillis()/1000);
            int result = am.updateByPrimaryKeySelective(addressTb);
            count += result;
        }
        return count;
    }

    @Override
    public boolean deleteAddress(Long addressId) {
        return am.deleteByPrimaryKey(addressId)>0? true:false;
    }

    @Override
    public AddressTb getDefaultAddress(Long userId) {
        AddressTbExample example = new AddressTbExample();
        AddressTbExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIsDefaultEqualTo(1);
        List<AddressTb> addressTbs = am.selectByExample(example);
        if(CheckEmptyUtil.isNotEmpty(addressTbs)){
            return addressTbs.get(0);
        }else{
            return null;
        }
    }

    private AddressTb AddressDefaultParamUtil(AddressTb ad){
        ad.setCreateTime(System.currentTimeMillis()/1000);
        //判断此地址是否为用户首次添加
        List<AddressTb> addressTbs = selectAddressByUser(ad.getUserId());
        if(CheckEmptyUtil.isEmpty(addressTbs)){
            //首次添加地址为默认地址
            ad.setIsDefault(1);
        }else{
            ad.setIsDefault(0);
        }
        return ad;
    }
}

package com.fire.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.back.common.CheckEmptyUtil;
import com.fire.back.dao.extend.ExtendUserTbMapper;
import com.fire.back.entity.UserTb;
import com.fire.back.entity.UserTbExample;
import com.fire.back.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ExtendUserTbMapper um;

    @Override
    public UserTb getUserInfobByPrimaryKey(Long id) {
        return um.selectByPrimaryKey(id);
    }

    @Override
    public int UpdateUserInfo(UserTb u) {
        u.setUpdateTime(System.currentTimeMillis()/1000);
        return um.updateByPrimaryKeySelective(u);
    }

    @Override
    public int insertUser(UserTb u) {
        return um.insertSelective(UserDefaultParamUtil(u));
    }

    @Override
    public UserTb getUserInfoByOpenId(String openid) {
        UserTbExample userTbExample = new UserTbExample();
        userTbExample.createCriteria().andOpenIdEqualTo(openid);
        List<UserTb> list = um.selectByExample(userTbExample);
        if(CheckEmptyUtil.isEmpty(list))return null;
        return list.get(0);
    }

    @Override
    public List<UserTb> selectUsersByPage(UserTb user) {
        return um.selectUsersByPage(user);
    }

    @Override
    public int selectUsersCount(UserTb user){
        return um.selectUsersCount(user);
    }

    private UserTb UserDefaultParamUtil(UserTb u){
        u.setUpdateTime(System.currentTimeMillis()/1000);
        u.setIsDelete(0);
        u.setIsFullInfo(0);
        return u;
    }
}

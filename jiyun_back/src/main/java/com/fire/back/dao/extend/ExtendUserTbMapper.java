package com.fire.back.dao.extend;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fire.back.dao.UserTbMapper;
import com.fire.back.entity.UserTb;

@Repository
public interface ExtendUserTbMapper extends UserTbMapper {

    List<UserTb>selectUsersByPage(UserTb user);
    int selectUsersCount(UserTb user);
}
package com.fire.back.service;

import com.fire.back.entity.CpackTb;

import java.util.List;

public interface CpackService {

    /**
     * 根据id查询子包裹信息
     * @param cpackId
     * @return
     */
    CpackTb getCPackInfo(Long cpackId);

    /**
     * 新增子包裹信息
     * @param cpackTb
     * @return
     */
    int addCpackInfo(CpackTb cpackTb);

    /**
     * 根据id删除子包裹信息
     * @param cpackId
     * @return
     */
    int deleteCpackInfo(Long cpackId);

    /**
     * 查询包裹下的全部子包裹
     * @param packId
     * @return
     */
    List<CpackTb> getCpackList(Long packId);

}

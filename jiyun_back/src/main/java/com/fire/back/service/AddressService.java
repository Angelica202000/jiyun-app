package com.fire.back.service;

import com.fire.back.entity.AddressTb;

import java.util.List;

public interface AddressService {

    /**
     * 根据id获取地址信息
     * @param id
     * @return
     */
    AddressTb getAddressInfoByPrimaryKey(Long id);

    /**
     * 根据id更新地址信息
     * @param ad
     * @return
     */
    boolean updateAddressInfo(AddressTb ad);

    /**
     * 添加新地址
     * 所有参数可为空，id自动生成
     * @param ad
     * @return
     */
    boolean insertAddress(AddressTb ad);

    /**
     * 根据条件查询数据条数
     * @param ad
     * @return
     */
    int selectAddressCount(AddressTb ad);

    /**
     * 分页查询
     * @param ad
     * @return
     */
    List<AddressTb> selectAddressByPage(AddressTb ad);

    /**
     * 根据用户id查询所有地址
     * @param userId
     * @return
     */
    List<AddressTb> selectAddressByUser(Long userId);

    /**
     * 批量更新 一般用于修改默认地址
     * @param addressTbs
     * @return
     */
    int updateAll(List<AddressTb> addressTbs);

    /**
     * 删除地址
     * @param addressId
     * @return
     */
    boolean deleteAddress(Long addressId);

    /**
     * 查询用户的默认地址
     */
    AddressTb getDefaultAddress(Long userId);
}

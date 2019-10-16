package com.gp4.service;

import com.gp4.pojo.Address;
import com.gp4.pojo.User;

import java.util.List;

/**
 * ckd 2019/9/10 15:41
 */
public interface UserService {
    // 注册
    void register(User user);
    // 检查用户名
    User checkUserName(String username);
    // 登录
    User login(String username, String password);

    List<Address> getAddressByUid(int uid);

    void addAddress(Address address);

    void updateDefault(int aid, int uid);

    void deleteAddress(int id);

    void updateAddress(Address address);
}

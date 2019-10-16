package com.gp4.dao;

import com.gp4.pojo.Address;
import com.gp4.pojo.User;

import java.util.List;

/**
 * ckd 2019/9/26 17:29
 */
public interface UserMapper {
    void add(User user);

    User findByUserName(String username);

    User findByUserNameAndPassWord(String username, String password);

    List<Address> getAddressByUid(int uid);

    void addAddress(Address address);

    void updateDefault(int aid, int uid);

    void deleteAddress(int id);

    void updateAddress(Address address);
}

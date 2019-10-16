package com.gp4.service.impl;

import com.gp4.dao.UserMapper;
import com.gp4.pojo.Address;
import com.gp4.pojo.User;
import com.gp4.service.UserService;
import com.gp4.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ckd 2019/9/10 15:41
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;
    @Override
    public void register(User user) {
        // 密码加密
        user.setPassword(Md5Utils.md5(user.getPassword()));
        userDao.add(user);
        // 发送邮件
//        EmailUtils.sendEmail(user);

    }

    @Override
    public User checkUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User login(String username, String password) {
        password = Md5Utils.md5(password);
        return userDao.findByUserNameAndPassWord(username, password);
    }

    @Override
    public List<Address> getAddressByUid(int uid) {
        return userDao.getAddressByUid(uid);
    }

    @Override
    public void addAddress(Address address) {
        userDao.addAddress(address);
    }

    @Override
    public void updateDefault(int aid, int uid) {
        userDao.updateDefault(aid,uid);
    }

    @Override
    public void deleteAddress(int id) {
        userDao.deleteAddress(id);
    }

    @Override
    public void updateAddress(Address address) {
        userDao.updateAddress(address);
    }
}

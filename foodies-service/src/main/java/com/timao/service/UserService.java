package com.timao.service;


import com.timao.pojo.Users;
import com.timao.pojo.bo.UserBO;

public interface UserService {

    //判断用户名是否存在
    public boolean queryUsernameIsExist(String username);

    //创建用户
    public Users createUser(UserBO userBO);

}

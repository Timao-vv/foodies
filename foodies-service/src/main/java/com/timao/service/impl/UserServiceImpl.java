package com.timao.service.impl;


import com.timao.enums.Sex;
import com.timao.mapper.UsersMapper;
import com.timao.pojo.Users;
import com.timao.pojo.bo.UserBO;
import com.timao.service.UserService;
import com.timao.utils.DateUtil;
import com.timao.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper userMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "http://www.aspku.com/uploads/allimg/190819/221H3Mc-7.jpg";

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        Users user = userMapper.selectOneByExample(userExample);
        return user == null ? false : true;
    }

    @Transactional(propagation =  Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        String userId = sid.nextShort();
        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setNickname(userBO.getUsername());
        // 网上找的默认头像x
        user.setFace(USER_FACE);
        // 设置默认生日
        user.setBirthday(DateUtil.stringToDate("1999-01-01"));
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        userMapper.insert(user);
        return user;
    }
}

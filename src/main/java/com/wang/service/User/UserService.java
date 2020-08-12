package com.wang.service.User;

import com.wang.pojo.User.*;

import java.util.List;

public interface UserService {
    int addUser(User user);

    int deleteUser(int id);

    int updateUser(User user);

    User queryUserById(int id);

    List<User> queryAllUser();

}

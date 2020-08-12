package com.wang.dao.User;


import com.wang.pojo.User.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int addUser(User user);

    int deleteUser(@Param("id") int id);

    int updateUser(User user);

    User queryUserById(@Param("id") int id);

    List<User> queryAllUser();

}

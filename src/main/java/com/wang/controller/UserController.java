package com.wang.controller;

import com.wang.pojo.User.*;
import com.wang.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController ("UserController")
public class UserController {
    //controller层调service层
    @Autowired
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/queryAllUser")//查询所有用户
    public void queryAllUser(){
        List<User> list = userService.queryAllUser();
        System.out.println("查询出所有的职员：");
        System.out.println(list);
    }

    @RequestMapping("/queryUserById")//根据ID查询用户
    public void queryUserById(int id){
        User user = userService.queryUserById(id);
        System.out.println("根据ID查询出用户：");
        System.out.println(user);
    }

    @RequestMapping("/addUser")//增加用户
    public void addUser(User user){
        Scanner s = new Scanner(System.in);
        System.out.println("正在插入中！");
        int success = 0;
        success = userService.addUser(user);
        if(success==1){
            System.out.println("插入成功！");
        }
        else
            System.out.println("插入失败！");
    }

    @RequestMapping("/deleteUser")//根据ID删除用户
    public void deleteUser(int id){
        int success = 0;
        success = userService.deleteUser(id);
        if(success==1){
            System.out.println("删除成功！");
        }
        else
            System.out.println("删除失败！");
    }

    @RequestMapping("/updateUser")//根据ID修改用户
    public void updateUser(User user){
        System.out.println("修改中！");
        int success = 0;
        success = userService.updateUser(user);
        if(success==1){
            System.out.println("修改成功！");
        }
        else
            System.out.println("修改失败！");
    }


}

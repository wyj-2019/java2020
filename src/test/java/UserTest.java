import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wang.controller.UserController;
import com.wang.pojo.User.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class UserTest {

    @Test
    // controller/UserController.queryAllUser
        public void queryAllUsertest() {//查所有用户
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserController controller = context.getBean("UserController", UserController.class);
            controller.queryAllUser();
        }

    @Test
    // controller/UserController.queryUserById
    public void queryUserByIdtest() {//根据id查
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController controller = context.getBean("UserController", UserController.class);
        System.out.println("查询中");
        controller.queryUserById(2);
    }

    @Test
    // controller/UserController.addUser
    public void addUsertest() {//添加用户
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController controller = context.getBean("UserController", UserController.class);
        User user1 = new User(1,"张三",26,"男","清远");
        User user2 = new User(2,"倩怡",20,"女","肇庆");
        User user3 = new User(3,"锦进",21,"男","惠州");
        controller.addUser(user1);
        controller.addUser(user2);
        controller.addUser(user3);
    }

    @Test
    // controller/UserController.deleteUser
    public void deleteUsertest() {//根据id删除
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController controller = context.getBean("UserController", UserController.class);
        System.out.println("删除中！");
        controller.deleteUser(1);
    }

    @Test
    // controller/UserController.updateUser
    public void updateUsertest() {//更新用户
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController controller = context.getBean("UserController", UserController.class);
        User user = new User(3,"小朱",20,"男","广州");
        controller.updateUser(user);
    }





    @Test
    //阿里云短信服务测试
    public void sendTest(){

        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FyJqPbDcQG9Qu3Us4bA", "pPY3xldRMUuWiB2ijedV0y94eubApp");
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);//勿要改动
        request.setDomain("dysmsapi.aliyuncs.com");//勿要改动
        request.setVersion("2017-05-25");
        request.setAction("SendSms");//事件名称

        //自定义参数 手机号 验证码 签名 模板
        request.putQueryParameter("PhoneNumbers", "15217599142");//此处***写要发送到的手机号
        request.putQueryParameter("SignName", "途游游");
        request.putQueryParameter("TemplateCode", "SMS_189836374");

        //短信验证码
        HashMap<String, Object> map = new HashMap<>();
        int random = (int)((Math.random()*9+1)*100000);//生成随机数作验证码
        map.put("code",random);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}


package junit.test;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*.xml");
        UserService userService = ioc.getBean(UserService.class);
        for(int i = 0;i<100;i++){
            User user = new User();
            user.setLoginacct("test"+i);
            user.setUserpswd(MD5Util.digest("123"));
            user.setUsername("test"+i);
            user.setEmail("haokj@qq.com  ");
            user.setCreatetime("2020-09-02 19:00:00");
            userService.savaUser(user);
        }




    }

}

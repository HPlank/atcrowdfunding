package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    /**
     * 异步方式
     * ResponseBody  结合jackson组件，将返回结果转换为字符串。jiangJSON串以流的形式返回给客户端
     * @param loginacct
     * @param userpswd
     * @param type
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(String loginacct, String userpswd, String type, HttpSession session){

        AjaxResult result = new AjaxResult();

        try {
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("loginacct",loginacct);
            paramMap.put("userpswd", MD5Util.digest(userpswd));
            paramMap.put("type",type);

            User user = userService.queryUserLogin(paramMap);
            session.setAttribute(Const.LOGIN_USER,user);
            result.setSuccess(true);
            //{“success”:true}
        }catch (Exception e){
            result.setMessage("登录失败");
            e.printStackTrace();
            result.setSuccess(false);
            //{"success":false,"message":"登录失败"}
        }
        return result;
    }

    //同步方式
//    @RequestMapping("/doLogin")
//    public String doLogin(String loginacct, String userpswd, String type, HttpSession session){
//        Map<String,Object> paramMap = new HashMap<String,Object>();
//        paramMap.put("loginacct",loginacct);
//        paramMap.put("userpswd",userpswd);
//        paramMap.put("type",type);
//
//        User user = userService.queryUserLogin(paramMap);
//        session.setAttribute(Const.LOGIN_USER,user);
//
//        return "redirect:/main.html";
//    }




    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();//销毁session对象或清理session域
        return "redirect:/index.htm";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/main")
    public String main(HttpSession session){

//        List<Permission> root = new ArrayList<Permission>();
//
//        //加载当前登录用户的权限
//        User user = (User)session.getAttribute(Const.LOGIN_USER);
//
//        List<Permission> mypermissions = userService.queryPermissionByUserid(user.getId());
//
//        Map<Integer,Permission> map = new HashMap<Integer, Permission>();
//
//        for (Permission permission : mypermissions) {
//            map.put(permission.getId(),permission);
//        }
//
//        for (Permission child: mypermissions
//             ) {
//            if(child.getPid()==null){
//                root.add(child);
//            }else {
//                Permission parent = map.get(child.getPid());
//                parent.getChildren().add(child);
//            }
//        }
//
//        Permission permissionRoot = null;
//
//        session.setAttribute("permissionRoot",permissionRoot);

        return "main";
    }

    @RequestMapping("/member")
    public String member(){
        return "member";
    }

    @RequestMapping("/role")
    public String role(){
        return "index";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    @RequestMapping("/permission")
    public String permission(){
        return "role/permission";
    }

    @RequestMapping("/auth_cert")
    public String auth_cert(){
        return "auth/auth_cert";
    }

    @RequestMapping("/auth_adv")
    public String auth_adv(){
        return "auth/auth_adv";
    }

    @RequestMapping("/auth_project")
    public String auth_project(){
        return "auth/auth_project";
    }

    @RequestMapping("/cert")
    public String cert(){
        return "cert";
    }
    @RequestMapping("/type")
    public String type(){
        return "type";
    }

    @RequestMapping("/process")
    public String process(){
        return "process";
    }

    @RequestMapping("/advertisement")
    public String advertisement(){
        return "advertisement";
    }


    @RequestMapping("/project_type")
    public String project_type(){
        return "project_type";
    }
    @RequestMapping("/message")
    public String message(){
        return "message";
    }

    @RequestMapping("/tag")
    public String tag(){
        return "tag";
    }

    @RequestMapping("/param")
    public String param(){
        return "param";
    }

    @RequestMapping("/project")
    public String project(){
        return "project";
    }

    @RequestMapping("/projects")
    public String projects(){
        return "projects";
    }

    @RequestMapping("/accttype")
    public String accttype(){
        return "accttype";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/apply")
    public String apply(){
        return "apply";
    }

    @RequestMapping("/apply-1")
    public String apply1(){
        return "apply-1";
    }
    @RequestMapping("/apply-2")
    public String apply2(){
        return "apply-2";
    }
    @RequestMapping("/apply-3")
    public String apply3(){
        return "apply-3";
    }

    @RequestMapping("/assignPermission")
    public String assignPermission(){
        return "role/assignPermission";
    }

    @RequestMapping("/assignRole")
    public String assignRole(){
        return "user/assignRole";
    }

    @RequestMapping("/edit")
    public String edit(){
        return "user/edit";
    }

    @RequestMapping("/minecrowdfunding")
    public String minecrowdfunding(){
        return "minecrowdfunding";
    }

    @RequestMapping("/pay-step-1")
    public String paystep1(){
        return "pay-step-1";
    }
    @RequestMapping("/pay-step-2")
    public String paystep2(){
        return "pay-step-2";
    }

    @RequestMapping("/start")
    public String start(){
        return "start";
    }

    @RequestMapping("/start-step-1")
    public String startstep1(){
        return "start-step-1";
    }
    @RequestMapping("/start-step-2")
    public String startstep2(){
        return "start-step-2";
    }
    @RequestMapping("/start-step-2")
    public String startstep3(){
        return "start-step-3";
    }
    @RequestMapping("/start-step-4")
    public String startstep4(){
        return "start-step-4";
    }


}

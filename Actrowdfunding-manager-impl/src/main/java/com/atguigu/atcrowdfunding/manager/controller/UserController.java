package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "user/index";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Map map){
        User user = userService.getUserById(id);
        map.put("user",user);
        return "user/edit";
    }



    //条件查询
    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                        String queryText){

        AjaxResult result = new AjaxResult();
        try{
            Map paramMap = new HashMap();
            paramMap.put("pageno",pageno);
            paramMap.put("pagesize",pagesize);
            if(StringUtil.isNotEmpty(queryText)){

                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%","\\\\%");
                }

                paramMap.put("queryText",queryText);
            }

            Page page = userService.queryUserPage(paramMap);
            result.setSuccess(true);
            result.setPage(page);
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败!");
        }

        return result;  //将对象序列号为JSON字符串,以流的形式返回
    }
    //异步请求
//    @ResponseBody
//    @RequestMapping("/index")
//    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
//                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize){
//        AjaxResult result = new AjaxResult();
//        try{
//            Page page = userService.queryUserPage(pageno,pagesize);
//            result.setSuccess(true);
//            result.setPage(page);
//        }catch (Exception e){
//            result.setSuccess(false);
//            e.printStackTrace();
//            result.setMessage("查询数据失败!");
//        }
//
//        return result;  //将对象序列号为JSON字符串,以流的形式返回
//    }

    //同步请求
//    @RequestMapping("/index")
//    public String index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
//                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize, Map map){
//        Page page = userService.queryUserPage(pageno,pagesize);
//        map.put("page",page);
//        return "user/index";
//    }
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(User user){

        AjaxResult result = new AjaxResult();
        try{
            int count = userService.saveUser(user);
            result.setSuccess(count==1);
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败!");
        }
        return result;  //将对象序列号为JSON字符串,以流的形式返回
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(User user){

        AjaxResult result = new AjaxResult();
        try{
            int count = userService.updateUser(user);
            result.setSuccess(count==1);
        }catch (Exception e){
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("修改数据失败!");
        }
        return result;  //将对象序列号为JSON字符串,以流的形式返回
    }


}

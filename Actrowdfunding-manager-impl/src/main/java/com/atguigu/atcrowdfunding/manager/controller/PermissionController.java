package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.controller.BaseController;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jnlp.PersistenceService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "permission/add";
    }

    @RequestMapping("/toUptate")
    public String toUptate(Integer id,Map map){
        Permission permission = permissionService.getPermissionById(id);
        map.put("permission",permission);
        return "permission/uptate";
    }


    @ResponseBody
    @RequestMapping("/deletePermission")
    public Object deletePermission(Integer id){
        start();
        try{

            int count = permissionService.deletePermission(id);

            success(count == 1);

        }catch (Exception e){
            success(false);
            error("删除许可数失败！");
        }

        return end();
    }

//    @ResponseBody
//    @RequestMapping("/deletePermission")
//    public Object deletePermission(Integer id){
//        AjaxResult result = new AjaxResult();
//        try{
//
//            int count = permissionService.deletePermission(id);
//
//            result.setSuccess(count == 1);
//
//        }catch (Exception e){
//            result.setSuccess(false);
//            result.setMessage("删除许可数失败！");
//        }
//
//        return result;
//    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(Permission permission){
        AjaxResult result = new AjaxResult();
        try{

            int count = permissionService.updatePermission(permission);

            result.setSuccess(count == 1);

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("修改许可数失败！");
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Permission permission){
        AjaxResult result = new AjaxResult();
        try{

            int count = permissionService.savePermission(permission);

            result.setSuccess(count == 1);

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("保存许可数失败！");
        }

        return result;
    }



    /**
     * Demo5 -用Map集合来查找父，来组合父子关系。减少循环次数，提高次数
     * 1.数据库进行一次查询，将查询道得数据放入内存，经过一次循环放入Map中（key = id，value = Permission）
     * 2.进行内层循环，如果pid为空则设置为根节点
     *      若不为空则通过map查找该节点的父节点，并将该节点放入父结点中，建立父子关系
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        AjaxResult result = new AjaxResult();
        try{
            List<Permission> root = new ArrayList<Permission>();

            List<Permission> childrenPermissions = permissionService.queryAllPermission();
            Map<Integer,Permission> map = new HashMap<Integer, Permission>();

            for (Permission innerPermission :
                    childrenPermissions) {
                map.put(innerPermission.getId(),innerPermission);
            }

            for (Permission permission : childrenPermissions) {
                //通过夫查子
                //子菜单
                Permission child = permission;
                if(child.getPid() == null){
                    root.add(permission);
                }else {
                    //父节点
                    Permission parent = map.get(child.getPid());
                    parent.getChildren().add(child);
                }
            }
            result.setSuccess(true);
            result.setData(root);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("加载许可数失败！");
        }

        return result;
    }

//    //Demo4 -采用一次性加载所有permission数据，减少数据的交互次数。
//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//        AjaxResult result = new AjaxResult();
//        try{
//            List<Permission> root = new ArrayList<Permission>();
//
//            List<Permission> childrenPermissions = permissionService.queryAllPermission();
//            for (Permission permission : childrenPermissions) {
//                //通过夫查子
//                //子菜单
//                Permission child = permission;
//                if(child.getPid() == null){
//                    root.add(permission);
//                }else {
//                    //父节点
//                    for (Permission innerPermission :
//                            childrenPermissions) {
//                        if (child.getPid() == innerPermission.getId()) {
//                            Permission parent = innerPermission;
//                            parent.getChildren().add(child);
//                            break;//跳出内存循环，如果跳出外层循环，需要使用标签跳出
//                        }
//                        }
//                }
//            }
//            result.setSuccess(true);
//            result.setData(root);
//        }catch (Exception e){
//            result.setSuccess(false);
//            result.setMessage("加载许可数失败！");
//        }
//
//        return result;
//    }

//    //Demo3 采用递归数来解决，许可树多个层次的问题
//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//        AjaxResult result = new AjaxResult();
//        try{
//
//            List<Permission> root = new ArrayList<Permission>();
//
//            //父
//            Permission permission = permissionService.getRootPermission();
//
//            root.add(permission);
//
//            queryChildPermissions(permission);
//
//            result.setSuccess(true);
//            result.setData(permission);
//
//        }catch (Exception e){
//            result.setSuccess(false);
//            result.setMessage("加载许可数失败！");
//        }
//
//        return result;
//    }

    /**
     * 递归使用条件：
     *      1.调用自身方法
     *      2.不断调用自身方法时，操作范围应该不断变小
     *      3.一定要有跳出条件
     * @param permission
     */
    private void queryChildPermissions(Permission permission) {

        List<Permission> children = permissionService.getChildrenPremissionById(permission.getId());
        //组合父与子关系
        permission.setChildren(children);
        for(Permission innerChildren:children){
            queryChildPermissions(innerChildren);
        }

    }

//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//        AjaxResult result = new AjaxResult();
//        try{
//
//            List<Permission> root = new ArrayList<Permission>();
//
//            //父
//            Permission permission = permissionService.getRootPermission();
//
//            root.add(permission);
//
//            //子
//            List<Permission> children = permissionService.getChildrenPremissionById(permission.getId());
//
//            //设置父子关系
//            permission.setChildren(children);
//
//            for(Permission child : children){
//                child.setOpen(true);
//                //根据父查子
//                List<Permission> innerChildren = permissionService.getChildrenPremissionById(child.getId());
//                child.setChildren(innerChildren);
//            }
//
//            result.setSuccess(true);
//            result.setData(permission);
//
//        }catch (Exception e){
//            result.setSuccess(false);
//            result.setMessage("加载许可数失败！");
//        }
//
//        return result;
//    }



//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//        AjaxResult result = new AjaxResult();
//        try{
//            //父
//            Permission permission = new Permission();
//            permission.setName("系统权限菜单");
//            permission.setOpen(true);
//
//            //子
//            List<Permission> children = new ArrayList<Permission>();
//
//            Permission permission1 = new Permission();
//            permission1.setName("控制面板");
//
//            Permission permission2 = new Permission();
//            permission2.setName("权限管理");
//
//            children.add(permission1);
//            children.add(permission2);
//
//            permission.setChildren(children);
//
//            result.setSuccess(true);
//            result.setData(permission);
//
//        }catch (Exception e){
//            result.setSuccess(false);
//            result.setMessage("加载许可数失败！");
//        }
//
//        return result;
//    }

}

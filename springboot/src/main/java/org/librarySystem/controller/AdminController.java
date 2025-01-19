package org.librarySystem.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.librarySystem.Service.AdminService;
import org.librarySystem.common.Result;
import org.librarySystem.entity.Admin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    //    增添数据
    @PostMapping("/Post")
    public Result post(@RequestBody Admin admin) {
        adminService.post(admin);
        return Result.success();
    }

    //更新数据
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }
    //删除数据
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        adminService.deleteBatchById(ids);
        return Result.success();
    }

    /**
    * 查询所有
    */

    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        List<Admin> list = adminService.selectAll(admin);
        return Result.success(list);
    }
//    @GetMapping("/selectOne")
//    public Result selectOne(@RequestParam Integer id){
//        List<Admin> admin = adminService.selectById(id);
//        return Result.success(admin);
//    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer id){
        List<Admin> admin = adminService.selectById(id);
        return Result.success(admin);
    }
//   //变量多可以直接传对象
//    @GetMapping("/selectList")
//    public Result selectList(Admin admin){
//        List<Admin> list = adminService.selectList(admin);
//        return Result.success(list);
//    }

    /*
    * 分页查询
    * pageNum: 当前页码
    * pageSize: 每页个数
    * */
    @GetMapping("/selectPage")
    public Result selectByPage(Admin admin,
            @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "7") Integer pageSize){
        PageInfo<Admin> pageInfo= adminService.selectPage(admin,pageNum, pageSize);
        return Result.success(pageInfo);
    }


}

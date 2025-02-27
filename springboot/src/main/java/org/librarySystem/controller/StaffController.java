package org.librarySystem.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.librarySystem.Service.StaffService;
import org.librarySystem.common.Result;
import org.librarySystem.entity.Staff;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Resource
    private StaffService staffService;

    //    增添数据
    @PostMapping("/Post")
    public Result post(@RequestBody Staff staff) {
        staffService.post(staff);
        return Result.success();
    }

    //更新数据
    @PutMapping("/update")
    public Result update(@RequestBody Staff staff) {
        staffService.update(staff);
        return Result.success();
    }
    //删除数据
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        staffService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        staffService.deleteBatchById(ids);
        return Result.success();
    }

    /**
    * 查询所有
    */

    @GetMapping("/selectAll")
    public Result selectAll(Staff staff) {
        List<Staff> list = staffService.selectAll(staff);
        return Result.success(list);
    }
//    @GetMapping("/selectOne")
//    public Result selectOne(@RequestParam Integer id){
//        List<Staff> staff = staffService.selectById(id);
//        return Result.success(staff);
//    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer id){
        List<Staff> staff = staffService.selectById(id);
        return Result.success(staff);
    }
//   //变量多可以直接传对象
//    @GetMapping("/selectList")
//    public Result selectList(Staff staff){
//        List<Staff> list = staffService.selectList(staff);
//        return Result.success(list);
//    }

    /*
    * 分页查询
    * pageNum: 当前页码
    * pageSize: 每页个数
    * */
    @GetMapping("/selectPage")
    public Result selectByPage(Staff staff,
            @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "7") Integer pageSize){
        PageInfo<Staff> pageInfo= staffService.selectPage(staff,pageNum, pageSize);
        return Result.success(pageInfo);
    }


}

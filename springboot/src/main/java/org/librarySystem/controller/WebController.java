package org.librarySystem.controller;

import jakarta.annotation.Resource;
import org.librarySystem.Service.AdminService;
import org.librarySystem.Service.StaffService;
import org.librarySystem.common.Result;
import org.librarySystem.entity.Account;
import org.librarySystem.entity.Admin;
import org.librarySystem.entity.ChangePasswordDTO;
import org.librarySystem.entity.Staff;
import org.librarySystem.exception.CustomException;
import org.librarySystem.mapper.StaffMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class WebController {

    @Resource
    private StaffService staffService;

    @Resource
    private AdminService adminService;

    @GetMapping("/hello")
    public Result hello() {
        return Result.success("hello");
    }

    /**
     *  admin or staff login
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account staff) {
        Account account = null;
        if ("EMP".equals(staff.getRole())) {
            account = staffService.login(staff);
        } else if ("ADMIN".equals(staff.getRole())) {
            account =adminService.login(staff);
        } else {
            throw new CustomException("003", "Invalid role");
        }

        return Result.success(account);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Staff staff) {
        staffService.register(staff);
        return Result.success();
    }
    @PutMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePasswordDTO account) {
        if ("EMP".equals(account.getRole())){
            staffService.changePassword(account);
        }else if ("ADMIN".equals(account.getRole())){
            adminService.changePassword(account);
        }else{
            throw new CustomException("003", "Invalid role");
        }
        return Result.success();
    }

}



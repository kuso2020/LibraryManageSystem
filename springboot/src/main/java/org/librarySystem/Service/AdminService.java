package org.librarySystem.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.librarySystem.auth.PasswordUtils;
import org.librarySystem.entity.Account;
import org.librarySystem.entity.Admin;
import org.librarySystem.entity.ChangePasswordDTO;
import org.librarySystem.exception.CustomException;
import org.librarySystem.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public void post(Admin admin) { //add admin
        String username = admin.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin != null) {
            throw new CustomException("500", "用户名已存在");
        }
        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            admin.setPassword(PasswordUtils.hashPassword("admin"));
        }
        if (admin.getUsername() == null || admin.getUsername().isEmpty()) {
            String defaultUsername = "admin_" + System.currentTimeMillis();
            admin.setUsername(defaultUsername);
        }
        admin.setRole("ADMIN");
        adminMapper.insert(admin);
    }



    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }


    public List<Admin> selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    public PageInfo<Admin> selectPage(Admin admin,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    public void update(Admin admin) {
        adminMapper.updateById(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    public void deleteBatchById(List<Integer> ids) {
        for (Integer id : ids) {
            adminMapper.deleteById(id);
        }
    }

    public Admin login(Account admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin == null) {
            throw new CustomException("500", "用户名不存在");
        }
        if (!PasswordUtils.checkPassword(password, dbAdmin.getPassword())) {//加密后的
            throw new CustomException("500", "密码错误");
        }
        return dbAdmin;
    }

    public void changePassword(ChangePasswordDTO account) {
        String username = account.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin == null) {
            throw new CustomException("500", "用户名不存在");
        }
        if (!PasswordUtils.checkPassword(account.getOldPassword(), dbAdmin.getPassword())) {
            throw new CustomException("500", "旧密码错误");
        }
        if (account.getNewPassword() == null || account.getNewPassword().isEmpty()) {
            throw new CustomException("500", "新密码不能为空");
        }
        dbAdmin.setPassword(PasswordUtils.hashPassword(account.getNewPassword()));
        adminMapper.updateById(dbAdmin);

    }
}

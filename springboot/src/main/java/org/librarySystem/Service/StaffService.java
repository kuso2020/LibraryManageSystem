package org.librarySystem.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.librarySystem.auth.PasswordUtils;
import org.librarySystem.entity.Account;
import org.librarySystem.entity.Admin;
import org.librarySystem.entity.ChangePasswordDTO;
import org.librarySystem.entity.Staff;
import org.librarySystem.exception.CustomException;
import org.librarySystem.mapper.AdminMapper;
import org.librarySystem.mapper.StaffMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Resource
    private StaffMapper staffMapper;

    @Resource
    private AdminMapper adminMapper;

    public void post(Staff staff) { //add staff
        String username = staff.getUsername();
        Staff dbStaff = staffMapper.selectByUsername(username);

        if (dbStaff != null) {
            throw new CustomException("500", "用户名已存在");
        }
        if (staff.getPassword() == null || staff.getPassword().isEmpty()) {
            staff.setPassword(PasswordUtils.hashPassword("123456"));//default password
        }else{
            staff.setPassword(PasswordUtils.hashPassword(staff.getPassword()));
        }
        if (staff.getUsername() == null || staff.getUsername().isEmpty()) {
            String defaultUsername = "user_" + System.currentTimeMillis();
            staff.setUsername(defaultUsername);
        }
        if (staff.getName() == null || staff.getName().isEmpty()){
            staff.setName(staff.getUsername());
        }
        staff.setRole("EMP");
        staffMapper.insert(staff);
    }



    public List<Staff> selectAll(Staff staff) {
        return staffMapper.selectAll(staff);
    }


    public List<Staff> selectById(Integer id) {
        return staffMapper.selectById(id);
    }

    public PageInfo<Staff> selectPage(Staff staff,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Staff> list = staffMapper.selectAll(staff);
        return PageInfo.of(list);
    }

    public void update(Staff staff) {
        staffMapper.updateById(staff);
    }

    public void deleteById(Integer id) {
        staffMapper.deleteById(id);
    }

    public void deleteBatchById(List<Integer> ids) {
        for (Integer id : ids) {
            staffMapper.deleteById(id);
        }
    }

    public Staff login(Account staff) {
        String username = staff.getUsername();
        String password = staff.getPassword();
        Staff dbStaff = staffMapper.selectByUsername(username);
        if (dbStaff == null) {
            throw new CustomException("500", "用户名不存在");
        }
        if (!PasswordUtils.checkPassword(password, dbStaff.getPassword())) {
            throw new CustomException("500", "密码错误");
        }
        return dbStaff;
    }

    public void register(Staff staff) {
        this.post(staff);
    }

    public void changePassword(ChangePasswordDTO account) {

        String username = account.getUsername();
        Staff dbStaff = staffMapper.selectByUsername(username);
        if (dbStaff == null) {
            throw new CustomException("500", "用户名不存在");
        }
        if (!PasswordUtils.checkPassword(account.getOldPassword(), dbStaff.getPassword())) {
            throw new CustomException("500", "旧密码错误");
        }
        if (account.getNewPassword() == null || account.getNewPassword().isEmpty()) {
            throw new CustomException("500", "新密码不能为空");
        }
        dbStaff.setPassword(PasswordUtils.hashPassword(account.getNewPassword()));
        staffMapper.updateById(dbStaff);
    }
}

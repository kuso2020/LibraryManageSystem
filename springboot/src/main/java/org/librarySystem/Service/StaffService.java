package org.librarySystem.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.librarySystem.entity.Staff;
import org.librarySystem.mapper.StaffMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Resource
    private StaffMapper staffMapper;

    public void post(Staff staff) {
        staffMapper.insert(staff);
    }



    public List<Staff> selectAll() {
        return staffMapper.selectAll();
    }


    public List<Staff> selectById(Integer id) {
        return staffMapper.selectById(id);
    }

    public PageInfo<Staff> selectPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Staff> list = staffMapper.selectAll();
        return PageInfo.of(list);
    }

    public void update(Staff staff) {
        staffMapper.updateById(staff);
    }

    public void deleteById(Integer id) {
        staffMapper.deleteById(id);
    }


//    public List<Staff> selectList(Staff staff) {
//        System.out.println(staff.toString());
//        return null;
//    }


}

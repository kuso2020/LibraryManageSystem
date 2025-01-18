package org.librarySystem.mapper;


import org.apache.ibatis.annotations.Select;
import org.librarySystem.entity.Staff;

import java.util.List;

public interface StaffMapper {
    List<Staff> selectAll();

    @Select("select * from staff where id = #{id}")
    List<Staff> selectById(Integer id);

    void insert(Staff staff);

    void updateById(Staff staff);

    void deleteById(Integer id);
}

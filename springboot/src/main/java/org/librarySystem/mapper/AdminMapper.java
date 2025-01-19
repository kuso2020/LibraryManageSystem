package org.librarySystem.mapper;


import org.apache.ibatis.annotations.Select;
import org.librarySystem.entity.Admin;

import java.util.List;

public interface AdminMapper {
//    List<Admin> selectAll();
    List<Admin> selectAll(Admin admin);

    @Select("select * from admin where id = #{id}")
    List<Admin> selectById(Integer id);

    void insert(Admin admin);

    void updateById(Admin admin);

    void deleteById(Integer id);


    Admin selectByUsername(String username);
}

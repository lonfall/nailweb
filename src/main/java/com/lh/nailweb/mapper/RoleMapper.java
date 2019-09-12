package com.lh.nailweb.mapper;

import com.lh.nailweb.entity.sys.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/8/12
 * @description:
 */
@Mapper
public interface RoleMapper {
    Role selectRoleById(@Param("id") long id);

    List<Role> getRoleList();

    int insertRole(Role role);

    int updateRole(Role role);
}

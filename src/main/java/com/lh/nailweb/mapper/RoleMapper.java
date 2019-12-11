package com.lh.nailweb.mapper;

import com.lh.nailweb.entity.sys.Role;
import com.lh.nailweb.vo.page.RolePage;
import com.lh.nailweb.vo.sys.role.RoleVO;
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

    List<RoleVO> getRolePage(RolePage page);

    int getRolePageTotal(RolePage page);

    int deleteRole(@Param("id") long id);

    Long[] selectRoleMenuIds(@Param("id") long id);

    int deleteRoleMenuIds(@Param("id") long id);

    int insertRoleMenuIds(@Param("id") long id, @Param("menuIds") List<Long> menuIds);
}

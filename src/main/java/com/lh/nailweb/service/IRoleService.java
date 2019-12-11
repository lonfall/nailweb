package com.lh.nailweb.service;

import com.lh.nailweb.entity.sys.Role;
import com.lh.nailweb.vo.page.RolePage;
import com.lh.nailweb.vo.sys.role.RoleCreateVO;
import com.lh.nailweb.vo.sys.role.RoleEditVO;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/8/12
 * @description:
 */
public interface IRoleService {
    /**
     * 通过ID获取角色
     *
     * @param id
     * @return
     */
    Role getRoleById(long id);

    /**
     * 获取角色列表
     *
     * @return
     */
    List<Role> getRoleList();

    /**
     * 创建角色
     *
     * @param roleVO
     * @return
     */
    int createRole(RoleCreateVO roleVO);

    /**
     * 编辑角色
     *
     * @param roleVO
     * @return
     */
    int eidtRole(RoleEditVO roleVO);

    /**
     * 获取角色分页列表
     *
     * @param page
     * @return
     */
    RolePage getRolePage(RolePage page);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    int deleteRole(long id);

    /**
     * 获取角色菜单权限
     *
     * @param id
     * @return
     */
    Long[] getRoleMenuIds(long id);

    /**
     * 更新角色菜单权限
     *
     * @param id
     * @param menuIds
     * @return
     */
    int updateRoleMenuIds(long id, List<Long> menuIds);
}

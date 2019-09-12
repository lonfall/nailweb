package com.lh.nailweb.service;

import com.lh.nailweb.entity.sys.Role;
import com.lh.nailweb.vo.sys.role.RoleCreateVO;
import com.lh.nailweb.vo.sys.role.RoleEditVO;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/8/12
 * @description:
 */
public interface IRoleService {
    Role getRoleById(long id);

    List<Role> getRoleList();

    int createRole(RoleCreateVO roleVO);

    int eidtRole(RoleEditVO roleVO);
}

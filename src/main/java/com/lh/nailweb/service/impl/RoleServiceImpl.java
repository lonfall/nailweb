package com.lh.nailweb.service.impl;

import com.lh.nailweb.constant.enums.DelFlagEnum;
import com.lh.nailweb.entity.sys.Role;
import com.lh.nailweb.mapper.RoleMapper;
import com.lh.nailweb.service.IRoleService;
import com.lh.nailweb.util.SnowFlakeUtil;
import com.lh.nailweb.vo.sys.role.RoleCreateVO;
import com.lh.nailweb.vo.sys.role.RoleEditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/8/12
 * @description:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper mapper;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Override
    public Role getRoleById(long id) {
        return mapper.selectRoleById(id);
    }

    @Override
    public List<Role> getRoleList() {
        return mapper.getRoleList();
    }

    @Override
    public int createRole(RoleCreateVO roleVO) {
        Role role = new Role();
        role.setId(snowFlakeUtil.nextId());
        role.setName(roleVO.getName());
        role.setRemark(roleVO.getRemark());
        role.setCreateTime(new Date());
        role.setDelFlag(DelFlagEnum.NORMAL.getValue());
        return mapper.insertRole(role);
    }

    @Override
    public int eidtRole(RoleEditVO roleVO) {
        Role role = mapper.selectRoleById(roleVO.getId());
        role.setName(roleVO.getName());
        role.setRemark(roleVO.getRemark());
        role.setUpdateTime(new Date());
        return mapper.updateRole(role);
    }
}

package com.lh.nailweb.service.impl;

import com.lh.nailweb.constant.enums.DelFlagEnum;
import com.lh.nailweb.entity.sys.Role;
import com.lh.nailweb.mapper.RoleMapper;
import com.lh.nailweb.service.IRoleService;
import com.lh.nailweb.util.SnowFlakeUtil;
import com.lh.nailweb.vo.page.RolePage;
import com.lh.nailweb.vo.sys.role.RoleCreateVO;
import com.lh.nailweb.vo.sys.role.RoleEditVO;
import com.lh.nailweb.vo.sys.role.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (null == role) {
            return 0;
        }
        role.setName(roleVO.getName());
        role.setRemark(roleVO.getRemark());
        role.setUpdateTime(new Date());
        return mapper.updateRole(role);
    }

    @Override
    public RolePage getRolePage(RolePage page) {
        List<RoleVO> list = mapper.getRolePage(page);
        page.setData(list);
        page.setTotal(mapper.getRolePageTotal(page));
        return page;
    }

    @Override
    public int deleteRole(long id) {
        return mapper.deleteRole(id);
    }

    @Override
    public Long[] getRoleMenuIds(long id) {
        return mapper.selectRoleMenuIds(id);
    }

    @Transactional
    @Override
    public int updateRoleMenuIds(long id, List<Long> menuIds) {
        mapper.deleteRoleMenuIds(id);
        if (null != menuIds && menuIds.size() > 0) {
            return mapper.insertRoleMenuIds(id, menuIds);
        } else {
            return 0;
        }
    }
}

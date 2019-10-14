package com.lh.nailweb.service.impl;

import com.lh.nailweb.constant.enums.DelFlagEnum;
import com.lh.nailweb.entity.sys.Menu;
import com.lh.nailweb.mapper.MenuMapper;
import com.lh.nailweb.service.IMenuService;
import com.lh.nailweb.util.SnowFlakeUtil;
import com.lh.nailweb.vo.sys.menu.MenuCreateVO;
import com.lh.nailweb.vo.sys.menu.MenuEditVO;
import com.lh.nailweb.vo.sys.menu.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/9/20
 * @description:
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper mapper;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Override
    public int createMenu(MenuCreateVO menuCreateVO) {
        Menu menu = new Menu();
        menu.setId(snowFlakeUtil.nextId());
        menu.setPid(menuCreateVO.getPid());
        menu.setName(menuCreateVO.getName());
        menu.setUrl(menuCreateVO.getUrl());
        menu.setPermission(menuCreateVO.getPermission());
        menu.setType(menuCreateVO.getType());
        menu.setIcon(menuCreateVO.getIcon());
        menu.setSort(menuCreateVO.getSort());
        menu.setHide(menuCreateVO.isHide());
        menu.setCreateTime(new Date());
        menu.setDelFlag(DelFlagEnum.NORMAL.getValue());
        return mapper.createMenu(menu);
    }

    @Override
    public int editMenu(MenuEditVO menuEditVO) {
        Menu menu = new Menu();
        menu.setId(menuEditVO.getId());
        menu.setPid(menuEditVO.getPid());
        menu.setName(menuEditVO.getName());
        menu.setUrl(menuEditVO.getUrl());
        menu.setPermission(menuEditVO.getPermission());
        menu.setType(menuEditVO.getType());
        menu.setIcon(menuEditVO.getIcon());
        menu.setSort(menuEditVO.getSort());
        menu.setHide(menuEditVO.isHide());
        menu.setUpdateTime(new Date());
        menu.setDelFlag(DelFlagEnum.NORMAL.getValue());
        return mapper.updateMenu(menu);
    }

    @Override
    public int deleteMenu(long id) {
        return mapper.deleteMenu(id);
    }

    @Override
    public List<MenuVO> getMenuChildrenList(long pid) {
        return mapper.getMenuChildrenList(pid);
    }

    @Override
    public boolean hasChildren(long id) {
        return mapper.countChildren(id) > 0;
    }
}

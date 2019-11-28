package com.lh.nailweb.service.impl;

import com.lh.nailweb.constant.enums.DelFlagEnum;
import com.lh.nailweb.entity.sys.Menu;
import com.lh.nailweb.mapper.MenuMapper;
import com.lh.nailweb.service.IMenuService;
import com.lh.nailweb.util.SnowFlakeUtil;
import com.lh.nailweb.vo.element.EleTreeNode;
import com.lh.nailweb.vo.sys.menu.MenuCreateVO;
import com.lh.nailweb.vo.sys.menu.MenuEditVO;
import com.lh.nailweb.vo.sys.menu.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

    @Override
    public List<EleTreeNode<MenuVO>> getMenuTree() {
        List<MenuVO> menuList = mapper.getMenuChildrenList(0);
        return menuToTreeNode(menuList);
    }

    private List<EleTreeNode<MenuVO>> menuToTreeNode(List<MenuVO> list) {
        List<EleTreeNode<MenuVO>> nodes = new ArrayList<>();
        for (MenuVO menu : list) {
            EleTreeNode<MenuVO> node = new EleTreeNode<>();
            node.setId(menu.getId());
            node.setData(menu);
            node.setHasChildren(mapper.countChildren(menu.getId()) > 0);
            List<MenuVO> menuList = mapper.getMenuChildrenList(menu.getId());
            if (node.isHasChildren()) {
                node.setChildren(menuToTreeNode(menuList));
            }
            nodes.add(node);
        }
        return nodes;
    }
}
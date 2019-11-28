package com.lh.nailweb.service;

import com.lh.nailweb.vo.element.EleTreeNode;
import com.lh.nailweb.vo.sys.menu.MenuCreateVO;
import com.lh.nailweb.vo.sys.menu.MenuEditVO;
import com.lh.nailweb.vo.sys.menu.MenuVO;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/9/20
 * @description:
 */
public interface IMenuService {
    /**
     * 创建菜单
     *
     * @param menuCreateVO
     * @return
     */
    int createMenu(MenuCreateVO menuCreateVO);

    /**
     * 编辑菜单
     *
     * @param menuEditVO
     * @return
     */
    int editMenu(MenuEditVO menuEditVO);

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    int deleteMenu(long id);

    /**
     * 获取子菜单列表
     *
     * @param pid
     * @return
     */
    List<MenuVO> getMenuChildrenList(long pid);

    /**
     * 是否存在子菜单
     *
     * @param id
     * @return
     */
    boolean hasChildren(long id);

    /**
     * 获取菜单树
     *
     * @return
     */
    List<EleTreeNode<MenuVO>> getMenuTree();
}

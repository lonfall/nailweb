package com.lh.nailweb.mapper;

import com.lh.nailweb.entity.sys.Menu;
import com.lh.nailweb.vo.sys.menu.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/9/20
 * @description:
 */
@Mapper
public interface MenuMapper {
    int createMenu(Menu menu);

    int updateMenu(Menu menu);

    int deleteMenu(@Param("id") long id);

    List<MenuVO> getMenuChildrenList(@Param("pid") long pid);

    int countChildren(@Param("id") long id);
}

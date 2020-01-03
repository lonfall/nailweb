package com.lh.nailweb.controller;

import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.constant.enums.MenuTypeEnum;
import com.lh.nailweb.entity.BaseMsg;
import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.service.IMenuService;
import com.lh.nailweb.service.IUserService;
import com.lh.nailweb.util.JwtTokenUtil;
import com.lh.nailweb.util.LoginUtils;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.vo.element.EleTreeNode;
import com.lh.nailweb.vo.sys.menu.MenuCreateVO;
import com.lh.nailweb.vo.sys.menu.MenuEditVO;
import com.lh.nailweb.vo.sys.menu.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/9/20
 * @description: 菜单相关接口
 */
@Api(description = "菜单相关接口")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    @Autowired
    private LoginUtils loginUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PreAuthorize("hasAnyAuthority('menu:create')")
    @ApiOperation(value = "创建菜单", notes = "创建菜单")
    @ApiImplicitParams({@ApiImplicitParam(name = "menuCreateVO", value = "创建菜单VO")})
    @PostMapping("/create")
    public BaseMsg createMenu(@RequestBody MenuCreateVO menuCreateVO) {
        if (StringUtils.isBlank(menuCreateVO.getName())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "菜单名为空！");
        }
        menuService.createMenu(menuCreateVO);
        return MsgUtils.success();
    }

    @PreAuthorize("hasAnyAuthority('menu:edit')")
    @ApiOperation(value = "编辑菜单", notes = "编辑菜单")
    @ApiImplicitParams({@ApiImplicitParam(name = "menuEditVO", value = "编辑菜单VO")})
    @PutMapping("/edit")
    public BaseMsg editMenu(@RequestBody MenuEditVO menuEditVO) {
        if (StringUtils.isBlank(menuEditVO.getName())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "菜单名为空！");
        }
        menuService.editMenu(menuEditVO);
        return MsgUtils.success();
    }

    @PreAuthorize("hasAnyAuthority('menu:delete')")
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "菜单id")})
    @DeleteMapping("/{id}")
    public BaseMsg deleteMenu(@PathVariable("id") long id) {
        menuService.deleteMenu(id);
        return MsgUtils.success();
    }

    @ApiOperation(value = "获取子菜单列表", notes = "获取子菜单列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pid", value = "上级菜单id")})
    @GetMapping("/children/{pid}")
    public BaseMsg getMenuChildrenList(@PathVariable("pid") long pid) {
        List<MenuVO> list = menuService.getMenuChildrenList(pid);
        List<EleTreeNode<MenuVO>> nodes = new ArrayList<>();
        for (MenuVO menu : list) {
            EleTreeNode<MenuVO> node = new EleTreeNode<>();
            node.setId(menu.getId());
            node.setData(menu);
            node.setHasChildren(menuService.hasChildren(menu.getId()));
            nodes.add(node);
        }
        return MsgUtils.success(nodes);
    }

    @ApiOperation(value = "获取菜单树", notes = "获取菜单树")
    @GetMapping("/tree")
    public BaseMsg getMenuTree() {
        List<EleTreeNode<MenuVO>> tree = menuService.getMenuTree();
        return MsgUtils.success(tree);
    }

    @ApiOperation(value = "获取当前用户菜单树", notes = "获取当前用户菜单树")
    @GetMapping("/tree/current")
    public BaseMsg getMenuTreeCurrent(HttpServletRequest request, @RequestHeader(value = "token", required = false) String token) {
        // 如果header中没有获取到token则在cookie中获取
        if (org.springframework.util.StringUtils.isEmpty(token)) {
            token = loginUtils.getCookie(request, jwtTokenUtil.getHeader());
        }
        if (!StringUtils.isBlank(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (!StringUtils.isBlank(username)) {
                // 通过用户ID获取用户菜单
                User user = userService.getUserByUserName(username);
                List<EleTreeNode<MenuVO>> tree = menuService.getMenuTreeCurrent(user.getId());
                return MsgUtils.success(tree);
            }
        }
        return MsgUtils.error("未获取到当前用户！");
    }
}

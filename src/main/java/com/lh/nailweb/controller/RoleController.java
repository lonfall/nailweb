package com.lh.nailweb.controller;

import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.entity.BaseMsg;
import com.lh.nailweb.entity.sys.Role;
import com.lh.nailweb.service.IRoleService;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.vo.page.RolePage;
import com.lh.nailweb.vo.sys.role.RoleCreateVO;
import com.lh.nailweb.vo.sys.role.RoleEditVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/8/12
 * @description: 角色相关接口
 */
@Api(description = "角色相关接口")
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "根据ID获取角色", notes = "根据ID获取角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色ID", dataType = "int", required = true, example = "0")})
    @GetMapping("/{id}")
    public BaseMsg getRole(@PathVariable("id") long id) {
        Role role = roleService.getRoleById(id);
        return MsgUtils.success(role);
    }

    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @GetMapping("/list")
    public BaseMsg getRoleList() {
        List<Role> list = roleService.getRoleList();
        return MsgUtils.success(list);
    }

    @ApiOperation(value = "获取角色分页列表", notes = "获取角色分页列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "角色分页数据", required = true)})
    @GetMapping("/page")
    public BaseMsg getRolePage(@ModelAttribute RolePage page) {
        page = roleService.getRolePage(page);
        return MsgUtils.success(page);
    }

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "角色名称")
            , @ApiImplicitParam(name = "remark", value = "角色备注")})
    @PostMapping("/create")
    public BaseMsg createRole(@RequestBody RoleCreateVO roleVO) {
        if (StringUtils.isBlank(roleVO.getName())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "角色名为空！");
        }
        if (StringUtils.isBlank(roleVO.getRemark())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "备注为空！");
        }
        roleService.createRole(roleVO);
        return MsgUtils.success();
    }

    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "角色名称")
            , @ApiImplicitParam(name = "remark", value = "角色备注")})
    @PutMapping("/edit")
    public BaseMsg editRole(@RequestBody RoleEditVO roleVO) {
        if (StringUtils.isBlank(roleVO.getName())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "角色名为空！");
        }
        if (StringUtils.isBlank(roleVO.getRemark())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "备注为空！");
        }
        roleService.eidtRole(roleVO);
        return MsgUtils.success();
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色id")})
    @DeleteMapping("/{id}")
    public BaseMsg deleteRole(@PathVariable("id") long id) {
        roleService.deleteRole(id);
        return MsgUtils.success();
    }

    @ApiOperation(value = "获取角色菜单权限", notes = "获取角色菜单权限")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色id")})
    @GetMapping("/menu/{id}")
    public BaseMsg getRoleMenuIds(@PathVariable("id") long id) {
        Long[] menuIds = roleService.getRoleMenuIds(id);
        return MsgUtils.success(menuIds);
    }

    @ApiOperation(value = "更新角色菜单权限", notes = "更新角色菜单权限")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色id")})
    @PostMapping("/menu/{id}")
    public BaseMsg updateRoleMenuIds(@PathVariable("id") long id, @RequestBody List<Long> menuIds) {
        roleService.updateRoleMenuIds(id, menuIds);
        return MsgUtils.success();
    }
}

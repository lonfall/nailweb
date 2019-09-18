package com.lh.nailweb.controller;

import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.entity.BaseMsg;
import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.service.IUserService;
import com.lh.nailweb.util.JwtTokenUtil;
import com.lh.nailweb.util.LoginUtils;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.vo.page.UserPage;
import com.lh.nailweb.vo.sys.user.UserEditVO;
import com.lh.nailweb.vo.sys.user.UserRegisterVO;
import com.lh.nailweb.vo.sys.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/7/22
 * @description: 用户相关接口
 */
@Api(description = "用户相关接口")
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private LoginUtils loginUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "获取当前用户", notes = "获取当前用户")
    @GetMapping("/current")
    public BaseMsg getCurrentUser(HttpServletRequest request, @RequestHeader(value = "token", required = false) String token) {
        // 如果header中没有获取到token则在cookie中获取
        if (org.springframework.util.StringUtils.isEmpty(token)) {
            token = loginUtils.getCookie(request, jwtTokenUtil.getHeader());
        }
        if (!StringUtils.isBlank(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (!StringUtils.isBlank(username)) {
                User user = userService.getUserByUserName(username);
                return MsgUtils.success(userService.userToVO(user));
            }
        }
        return MsgUtils.error("未获取到当前用户！");
    }

    @ApiOperation(value = "根据ID获取用户", notes = "根据ID获取用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", required = true, example = "0")})
    @GetMapping("/{id}")
    public BaseMsg getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return MsgUtils.success(userService.userToVO(user));
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/list")
    public BaseMsg getUserList() {
        List<UserVO> list = userService.getUserList();
        return MsgUtils.success(list);
    }

    @ApiOperation(value = "获取用户分页列表", notes = "获取用户分页列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "用户分页数据", required = true)})
    @GetMapping("/page")
    public BaseMsg getUserPage(@ModelAttribute UserPage page) {
        page = userService.getUserPage(page);
        return MsgUtils.success(page);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名")
            , @ApiImplicitParam(name = "password", value = "密码")
            , @ApiImplicitParam(name = "repeatPassword", value = "重复输入密码")})
    @PostMapping("/register")
    public BaseMsg registerUser(@RequestBody UserRegisterVO userRegister) {
        if (StringUtils.isBlank(userRegister.getUserName())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "用户名为空！");
        }
        if (StringUtils.isBlank(userRegister.getPassword()) || StringUtils.isBlank(userRegister.getRepeatPassword())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "密码为空！");
        }
        if (!StringUtils.equals(userRegister.getPassword(), userRegister.getRepeatPassword())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "密码不相同！");
        }
        User user = userService.getUserByUserName(userRegister.getUserName());
        if (user == null) {
            userService.registerUser(userRegister.getUserName(), userRegister.getPassword(), userRegister.getNickname());
            return MsgUtils.success();
        } else {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "用户已存在！");
        }
    }

    @ApiOperation(value = "切换用户状态", notes = "切换用户状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id")})
    @PutMapping("state/{id}")
    public BaseMsg stateToggle(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (null != user) {
            switch (user.getState()) {
                case 0:
                    userService.setUserState(id, 1);
                    break;
                case 1:
                    userService.setUserState(id, 0);
                    break;
            }
            return MsgUtils.success();
        }
        return MsgUtils.error("未找到该用户！");
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id")})
    @DeleteMapping("/{id}")
    public BaseMsg deleteRole(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return MsgUtils.success();
    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "用户名称")
            , @ApiImplicitParam(name = "remark", value = "用户备注")})
    @PutMapping("/edit")
    public BaseMsg editRole(@RequestBody UserEditVO userVO) {
        if (StringUtils.isBlank(userVO.getNickname())) {
            return MsgUtils.error(Constant.HTTP_BADREQUEST, "昵称为空！");
        }
        userService.eidtUser(userVO);
        return MsgUtils.success();
    }
}

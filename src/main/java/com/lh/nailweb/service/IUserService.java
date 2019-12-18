package com.lh.nailweb.service;

import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.vo.page.UserPage;
import com.lh.nailweb.vo.sys.user.UserEditVO;
import com.lh.nailweb.vo.sys.user.UserVO;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/7/31
 * @description:
 */
public interface IUserService {
    /**
     * 根据ID获取用户
     *
     * @param id
     * @return
     */
    User getUserById(long id);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 获取用户列表
     *
     * @return
     */
    List<UserVO> getUserList();

    /**
     * 注册用户
     *
     * @param userName
     * @param password
     * @param nickname
     */
    int registerUser(String userName, String password, String nickname);

    /**
     * 获取用户分页列表
     *
     * @param page
     * @return
     */
    UserPage getUserPage(UserPage page);

    /**
     * 更新用户状态
     *
     * @param id
     * @param state
     * @return
     */
    int setUserState(long id, int state);

    /**
     * user对象转换为userVO对象
     *
     * @param user
     * @return
     */
    UserVO userToVO(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUser(long id);

    /**
     * 编辑用户
     *
     * @param userVO
     * @return
     */
    int eidtUser(UserEditVO userVO);

    /**
     * 更新用户头像
     *
     * @param id
     * @param img
     * @return
     */
    int updateAvatar(long id, String img);

    /**
     * 获取用户角色权限
     *
     * @param id
     * @return
     */
    Long[] getUserRoleIds(long id);

    /**
     * 更新用户角色权限
     *
     * @param id
     * @param roleIds
     * @return
     */
    int updateUserRoleIds(long id, List<Long> roleIds);
}

package com.lh.nailweb.service;

import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.vo.page.UserPage;
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
}

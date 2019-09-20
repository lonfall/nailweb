package com.lh.nailweb.service.impl;

import com.lh.nailweb.constant.enums.DelFlagEnum;
import com.lh.nailweb.constant.enums.UserStateEnum;
import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.mapper.UserMapper;
import com.lh.nailweb.service.IUserService;
import com.lh.nailweb.util.SnowFlakeUtil;
import com.lh.nailweb.vo.page.UserPage;
import com.lh.nailweb.vo.sys.user.UserEditVO;
import com.lh.nailweb.vo.sys.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/7/31
 * @description: 用户服务
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Override
    public User getUserById(long id) {
        return mapper.getUserById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return mapper.getUserByUserName(username);
    }

    @Override
    public List<UserVO> getUserList() {
        return mapper.getUserList();
    }

    @Override
    public int registerUser(String userName, String password, String nickname) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        User user = new User();
        user.setId(snowFlakeUtil.nextId());
        user.setUsername(userName);
        user.setPassword(encodePassword);
        user.setState(UserStateEnum.NORMAL.getValue());
        user.setNickname(nickname);
        user.setCreateTime(new Date());
        user.setDelFlag(DelFlagEnum.NORMAL.getValue());
        return mapper.createUser(user);
    }

    @Override
    public UserPage getUserPage(UserPage page) {
        List<UserVO> list = mapper.getUserPage(page);
        page.setData(list);
        page.setTotal(mapper.getUserPageTotal(page));
        return page;
    }

    @Override
    public int setUserState(long id, int state) {
        return mapper.updateState(id, state);
    }

    @Override
    public UserVO userToVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setState(user.getState());
        userVO.setNickname(user.getNickname());
        userVO.setAvatar(user.getAvatar());
        userVO.setCreateTime(user.getCreateTime());
        userVO.setUpdateTime(user.getUpdateTime());
        userVO.setDelFlag(user.getDelFlag());
        return userVO;
    }

    @Override
    public int deleteUser(long id) {
        return mapper.deleteUser(id);
    }

    @Override
    public int eidtUser(UserEditVO userVO) {
        User user = mapper.getUserById(userVO.getId());
        if (null == user) {
            return 0;
        }
        if (null != userVO.getNickname() && !"".equals(userVO.getNickname())) {
            user.setNickname(userVO.getNickname());
        }
        if (null != userVO.getAvatar() && !"".equals(userVO.getAvatar())) {
            user.setAvatar(userVO.getAvatar());
        }
        return mapper.updateUser(user);
    }

    @Override
    public int updateAvatar(long id, String img) {
        return mapper.updateAvatar(id, img);
    }
}

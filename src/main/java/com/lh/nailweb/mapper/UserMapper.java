package com.lh.nailweb.mapper;

import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.vo.page.UserPage;
import com.lh.nailweb.vo.sys.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/7/31
 * @description:
 */
@Mapper
public interface UserMapper {
    User getUserById(@Param("id") long id);

    User getUserByUserName(@Param("username") String username);

    List<UserVO> getUserList();

    int createUser(User user);

    List<UserVO> getUserPage(UserPage page);

    int getUserPageTotal(UserPage page);

    int updateState(@Param("id") long id, @Param("state") int state);

    int deleteUser(@Param("id") long id);

    int updateUser(User user);

    int updateAvatar(@Param("id") long id, @Param("img") String img);

    Long[] getUserRoleIds(@Param("id") long id);

    int deleteUserRoleIds(@Param("id") long id);

    int insertUserRoleIds(@Param("id") long id, @Param("roleIds") List<Long> roleIds);

    List<String> selectUserPermission(@Param("id") long id, @Param("type") int type);
}

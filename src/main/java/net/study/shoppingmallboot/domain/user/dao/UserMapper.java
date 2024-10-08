package net.study.shoppingmallboot.domain.user.dao;

import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int getTotalUserCount(Search search);

    void addUser(User user);

    User getUserById(String userId);

    User loginUser(User user);

    List<User> getUserList(Search search);

    void updateUser(User user);

    void deleteUser(String userId);
}
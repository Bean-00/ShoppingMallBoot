package net.study.shoppingmallboot.domain.user.service.impl;

import net.study.shoppingmallboot.domain.user.dao.UserMapper;
import net.study.shoppingmallboot.domain.user.service.UserService;
import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User userVO) {
        userMapper.addUser(userVO);
    }

    public User loginUser(User userVO) {
        User dbUser = userMapper.loginUser(userVO);

        return dbUser;
    }

    public User getUserByUserId(String userId) {
        return userMapper.getUserById(userId);
    }

    public List<User> getUserList(Search search) {
        return userMapper.getUserList(search);
    }

    public void updateUser(User userVO) {
        userMapper.updateUser(userVO);
    }

    public boolean checkDuplication(String userId) {
        boolean result = true;
        User userVO = userMapper.getUserById(userId);
        if (userVO != null) {
            result = false;
        }
        return result;
    }

    @Override
    public int getTotalUserCount(Search search) {
        return userMapper.getTotalUserCount(search);
    }

    @Override
    public void deleteUser(String userId) {
        userMapper.deleteUser(userId);
    }
}
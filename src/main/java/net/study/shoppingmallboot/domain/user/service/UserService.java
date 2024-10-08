package net.study.shoppingmallboot.domain.user.service;


import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.Search;

import java.util.List;


public interface UserService {
	
	void addUser(User userVO) throws Exception;

	User loginUser(User userVO) throws Exception;

	User getUserByUserId(String userId) throws Exception;
	
	List<User> getUserList(Search search) throws Exception;
	
	void updateUser(User userVO) throws Exception;
	
	boolean checkDuplication(String userId) throws Exception;

	int getTotalUserCount(Search search);

	void deleteUser(String testUserId);
}
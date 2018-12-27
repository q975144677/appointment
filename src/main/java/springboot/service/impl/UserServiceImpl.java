package springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.UserMapper;
import springboot.pojo.User;
import springboot.pojo.UserExample;
import springboot.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
userMapper.insert(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public boolean isExist(User user) {
		// TODO Auto-generated method stub
		List<User> list = list();
		for(User user2 : list) {
			if(user2.getUsername().equals(user.getUsername())) {
				return true;
				
			}
			
			
		}
		return false;
	}
@Override
public boolean login(User user) {
	// TODO Auto-generated method stub
	List<User> list = list();
	for(User user2 : list) {
		System.out.println(user2.getUsername().equals(user.getUsername()));	System.out.println(user2.getPassword().equals(user.getPassword()));
		if(user2.getUsername().equals(user.getUsername())&&user2.getPassword().equals(user.getPassword())) {
			return true;
			
		}
		
		
	}
	return false;
}
@Override
public User get(String username, String password) {
	// TODO Auto-generated method stub
UserExample example = new UserExample();
	example.createCriteria().andPasswordEqualTo(password).andUsernameEqualTo(username);
	List<User> list  = userMapper.selectByExample(example); 
	if(list.isEmpty()) {
	return null;
	
	}
	
	else
		return list.get(0);
}




}

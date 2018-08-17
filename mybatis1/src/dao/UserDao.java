package dao;

import java.util.List;

import pojo.User;

public interface UserDao {
	
	
   //根据Id查询user
      public User findUserById(int id);
	

	
	//根据username查询user
      
      public List<User> findUserByUserName(String userName);
}

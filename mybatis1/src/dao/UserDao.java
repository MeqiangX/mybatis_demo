package dao;

import java.util.List;

import pojo.User;

public interface UserDao {
	
	
   //����Id��ѯuser
      public User findUserById(int id);
	

	
	//����username��ѯuser
      
      public List<User> findUserByUserName(String userName);
}

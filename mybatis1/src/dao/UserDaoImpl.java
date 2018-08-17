package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pojo.User;

public class UserDaoImpl implements UserDao{
    private SqlSessionFactory sqlSessionFactory;

	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
    
    public User findUserById(int id) {
    	
    	//SqlSession 线程不安全 公用会造成 数据混乱
    	SqlSession openSession = sqlSessionFactory.openSession();
    	
    	User user = openSession.selectOne("test.findUserById", id);
//    	System.out.println(user);
    	
    	return user;
    }
    
    public List<User> findUserByUserName(String userName){
    	SqlSession openSession = sqlSessionFactory.openSession();
    	
    	List<User> list = openSession.selectList("test.findUserByUserName", userName);
    	
//    	System.out.println(list);
    	
    	return list;
    }
}

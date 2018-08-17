package mapper;

import java.util.List;

import pojo.Orders;
import pojo.User;
import pojo.Vo;

public interface UserMapper {
 
	//根据Id查询user
    public User findUserById(int id);
	
	
	//根据username查询user
    
    public List<User> findUserByUserName(String userName);
    
    //动态sql 拼接条件 查询
    public List<User> findUserByVo(Vo vo);
    
    // 传入集合 in 
    public List<User> findUserByIds(Vo vo);
    
    //一对一的关联关系的查询
    public List<Orders> findUserAndOrder();
    
    //一对多的关联关系的查询
    public List<User> findOrderAndUser();
}

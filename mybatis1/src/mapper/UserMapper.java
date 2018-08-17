package mapper;

import java.util.List;

import pojo.Orders;
import pojo.User;
import pojo.Vo;

public interface UserMapper {
 
	//����Id��ѯuser
    public User findUserById(int id);
	
	
	//����username��ѯuser
    
    public List<User> findUserByUserName(String userName);
    
    //��̬sql ƴ������ ��ѯ
    public List<User> findUserByVo(Vo vo);
    
    // ���뼯�� in 
    public List<User> findUserByIds(Vo vo);
    
    //һ��һ�Ĺ�����ϵ�Ĳ�ѯ
    public List<Orders> findUserAndOrder();
    
    //һ�Զ�Ĺ�����ϵ�Ĳ�ѯ
    public List<User> findOrderAndUser();
}

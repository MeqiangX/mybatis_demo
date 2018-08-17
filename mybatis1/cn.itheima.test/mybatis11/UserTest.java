package mybatis11;

import java.io.InputStream;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;
import mapper.UserMapper;
import pojo.Orders;
import pojo.User;
import pojo.Vo;

public class UserTest {
	
    @Test
	public void testFindUserById() throws Exception{
    	String resource = "SqlMapConfig.xml";
    	
    	InputStream inputStream = Resources.getResourceAsStream(resource);
    	
    	//�����ļ��м����������ļ� 
    	
    	//�����������ļ� �����Ự����
    	
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        
		//�õ�Sql�Ự
	    SqlSession openSession = factory.openSession();
	    
	    //����sql�Ự�еķ���  ��ִ�������ļ��е�sql
	    
	    User user = openSession.selectOne("test.findUserById", 1);
	    System.out.println(user);
    }
    
    
    @Test
    public void testFindUserByName() throws Exception{
    	
    	String resource = "SqlMapConfig.xml";
    	//��ȡ�ļ�����
    	InputStream inputStream = Resources.getResourceAsStream(resource);
    	
    	//�����������Ự����  ���ӳ�
    	SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
    	
    	//�����Ự
    	SqlSession openSession = factory.openSession();
    	
    	List<User> list = openSession.selectList("test.findUserByUserName","��");
    	
    	System.out.println(list);
    }
    
    
    @Test
    public void testInsertUser() throws Exception{
    	String resource = "SqlMapConfig.xml";
    	InputStream inputStream = Resources.getResourceAsStream(resource);
    	SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
    	
    	//���ػỰ����
    	
       //�õ��Ự
    	SqlSession openSession = factory.openSession();
    	
    	//���Ӽ�¼
    	User user = new User();
    	user.setUsername("lili");
    	user.setBirthday(new Date());
    	user.setSex("1");
    	
    	user.setAddress("��������");
    	
    	System.out.println("����ǰ��id  = "+user.getId());
    	//mybatis���Զ��������� ����Ҫ�Լ��ֶ��ύ����
    	openSession.insert("test.insertUser", user);
    	
    	System.out.println("����� :  id =  " + user.getId());
    	
    	openSession.commit();
    }
    
    
    //ɾ��
    @Test 
    public void testDeleteUserById() throws Exception{
    	
    	String resource = "SqlMapConfig.xml";
    	InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
   
        //�õ��Ự
        
        SqlSession openSession = factory.openSession();
        
        openSession.delete("test.deleteUserById", 29);
        
        openSession.commit();
    
    }
    
   //����
   
   @Test
   public void testUpdateUserNameById() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
   
       SqlSession openSession = factory.openSession();
       
       User user = new User();
       user.setUsername("����һ");
       user.setId(28);
       openSession.update("test.updateUserNameById", user);
       
       openSession.commit();
   }
   
   
   //ԭ��dao�ķ�ʽ
   @Test
   public void testDao() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
	   
	   UserDao ud = new UserDaoImpl(factory);
	   
	   User user = ud.findUserById(28);
	   System.out.println(user);
	   
	   
	   List<User> list = ud.findUserByUserName("����");
	  System.out.println(list);
	   
   }
   
   
   // @Before  \test֮ǰִ�е�Ƭ��
   
   //mybatis�Ķ�̬����dao��ʽ, ���ֻ�ǵ�������ɾ�Ĳ飬��ô��Щdaoʵ����Ͳ����Լ���д��Mybatis�Ѿ�д���ˣ�
   /**
    * ֻҪ����һ���Ĺ��� ���Ϳ���ʹ�� 
    * �ӿ��� �� ӳ���ļ�����ͬ���ҽӿں�ӳ���ļ���ͬһ��Ŀ¼��
    * id �� ��������ͬ
    * ����������ͺͷ���ֵ�Ĳ�������  ӳ���ļ��ͽӿڷ�����һ��
    * 
    * 
    */
   
   @Test
   public void testDailiDao() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
	    
	   SqlSession openSession = factory.openSession();
	   
	   UserMapper mapper = openSession.getMapper(UserMapper.class); //ͨ���ӿڵõ�ӳ�������
	  
	   User user = 
			   mapper.findUserById(28);
	   
	   System.out.println(user);
//	   
//	   List<User> list = mapper.findUserByUserName("����");
//	   System.out.println(list);
   }
   
   @Test  //if where ��ǩ ����ƴ��
   public void findUserByVo() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
   
       SqlSession openSession = factory.openSession();
       
       UserMapper mapper = openSession.getMapper(UserMapper.class);
       
       
       Vo vo = new Vo();
       
       User user = new User();
       //user.setUsername("��");
       
       user.setSex("1");
       vo.setUser(user);
       List<User> list = mapper.findUserByVo(vo);
       
       System.out.println(list);
   }
   
   
   //���뼯��  in  foreach ��ǩ
   
   @Test
   public void findUserByIds() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
  
       SqlSession session = factory.openSession();
       
       UserMapper mapper = session.getMapper(UserMapper.class);
   
       Vo vo = new Vo();
       
     //  List list = new ArrayList();
//       list.add(28);
//       list.add(22);
//       list.add(24);
//       list.add(25);
       
       
      // vo.setIds(list);
       
       List<User> lists = mapper.findUserByIds(vo);
       
       System.out.println(lists);
  
   
   }
   
   //������ѯ  һ��һ
   @Test
   public void testFindUserAndOrder() throws Exception{
	   String resource = "SqlmapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
   
	   
	   SqlSession session = factory.openSession();
	   
	   UserMapper mapper = session.getMapper(UserMapper.class);
	   
	   List<Orders> order = mapper.findUserAndOrder();
	   
	   System.out.println(order);
   
   }
   
   //һ�Զ�Ĺ�����ѯ
   @Test
   public void findOrderAndUser() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
  
	   SqlSession session = factory.openSession();
	   
	   UserMapper mapper = session.getMapper(UserMapper.class);
	   
	   List<User> list = mapper.findOrderAndUser();
	   
	   System.out.println(list);
   
   }
}

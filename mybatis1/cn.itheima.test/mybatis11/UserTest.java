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
    	
    	//从流文件中加载主配置文件 
    	
    	//根据主配置文件 创建会话工厂
    	
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        
		//得到Sql会话
	    SqlSession openSession = factory.openSession();
	    
	    //根据sql会话中的方法  来执行配置文件中的sql
	    
	    User user = openSession.selectOne("test.findUserById", 1);
	    System.out.println(user);
    }
    
    
    @Test
    public void testFindUserByName() throws Exception{
    	
    	String resource = "SqlMapConfig.xml";
    	//读取文件到流
    	InputStream inputStream = Resources.getResourceAsStream(resource);
    	
    	//根据流创建会话工厂  连接池
    	SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
    	
    	//创建会话
    	SqlSession openSession = factory.openSession();
    	
    	List<User> list = openSession.selectList("test.findUserByUserName","王");
    	
    	System.out.println(list);
    }
    
    
    @Test
    public void testInsertUser() throws Exception{
    	String resource = "SqlMapConfig.xml";
    	InputStream inputStream = Resources.getResourceAsStream(resource);
    	SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
    	
    	//加载会话工厂
    	
       //得到会话
    	SqlSession openSession = factory.openSession();
    	
    	//增加记录
    	User user = new User();
    	user.setUsername("lili");
    	user.setBirthday(new Date());
    	user.setSex("1");
    	
    	user.setAddress("北京朝阳");
    	
    	System.out.println("插入前：id  = "+user.getId());
    	//mybatis会自动开启事务 但是要自己手动提交事务
    	openSession.insert("test.insertUser", user);
    	
    	System.out.println("插入后 :  id =  " + user.getId());
    	
    	openSession.commit();
    }
    
    
    //删除
    @Test 
    public void testDeleteUserById() throws Exception{
    	
    	String resource = "SqlMapConfig.xml";
    	InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
   
        //得到会话
        
        SqlSession openSession = factory.openSession();
        
        openSession.delete("test.deleteUserById", 29);
        
        openSession.commit();
    
    }
    
   //更改
   
   @Test
   public void testUpdateUserNameById() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
   
       SqlSession openSession = factory.openSession();
       
       User user = new User();
       user.setUsername("王五一");
       user.setId(28);
       openSession.update("test.updateUserNameById", user);
       
       openSession.commit();
   }
   
   
   //原生dao的方式
   @Test
   public void testDao() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
	   
	   UserDao ud = new UserDaoImpl(factory);
	   
	   User user = ud.findUserById(28);
	   System.out.println(user);
	   
	   
	   List<User> list = ud.findUserByUserName("王五");
	  System.out.println(list);
	   
   }
   
   
   // @Before  \test之前执行的片段
   
   //mybatis的动态代理dao方式, 如果只是单纯的增删改查，那么这些dao实现类就不用自己再写，Mybatis已经写好了，
   /**
    * 只要符合一定的规则 ，就可以使用 
    * 接口名 和 映射文件名相同，且接口和映射文件在同一个目录下
    * id 和 方法名相同
    * 传入参数类型和返回值的参数类型  映射文件和接口方法的一样
    * 
    * 
    */
   
   @Test
   public void testDailiDao() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
	    
	   SqlSession openSession = factory.openSession();
	   
	   UserMapper mapper = openSession.getMapper(UserMapper.class); //通过接口得到映射代理类
	  
	   User user = 
			   mapper.findUserById(28);
	   
	   System.out.println(user);
//	   
//	   List<User> list = mapper.findUserByUserName("王五");
//	   System.out.println(list);
   }
   
   @Test  //if where 标签 条件拼接
   public void findUserByVo() throws Exception{
	   String resource = "SqlMapConfig.xml";
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
   
       SqlSession openSession = factory.openSession();
       
       UserMapper mapper = openSession.getMapper(UserMapper.class);
       
       
       Vo vo = new Vo();
       
       User user = new User();
       //user.setUsername("张");
       
       user.setSex("1");
       vo.setUser(user);
       List<User> list = mapper.findUserByVo(vo);
       
       System.out.println(list);
   }
   
   
   //传入集合  in  foreach 标签
   
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
   
   //关联查询  一对一
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
   
   //一对多的关联查询
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

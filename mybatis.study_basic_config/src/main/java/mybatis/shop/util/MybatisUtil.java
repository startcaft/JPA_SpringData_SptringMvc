package mybatis.shop.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	
	public static SqlSessionFactory factory;
	
	static{
		try {
			InputStream input = Resources.getResourceAsStream("mybatis_config.xml");
			factory = new SqlSessionFactoryBuilder().build(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建SqlSession
	 * @return
	 */
	public static SqlSession createSession(){
		return factory.openSession();
	}
	
	/**
	 * 关闭SqlSession
	 * @param session
	 */
	public static void closeSession(SqlSession session){
		
		if(session != null){
			session.close();
		}
	}
}

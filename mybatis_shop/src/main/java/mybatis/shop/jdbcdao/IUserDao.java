package mybatis.shop.jdbcdao;

import mybaitsi.shop.model.User;

public interface IUserDao {
	
	public User load(int userId);
}

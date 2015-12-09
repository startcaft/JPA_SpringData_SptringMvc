package mybatis.jdbcdao.test;


import java.util.List;

import org.junit.Test;

import mybaitsi.shop.model.Address;
import mybaitsi.shop.model.User;
import mybatis.shop.jdbcdao.DAOFactory;
import mybatis.shop.jdbcdao.IAddressDao;
import mybatis.shop.jdbcdao.IUserDao;

public class TestAddressDao {

	private IAddressDao addressDao = DAOFactory.getAddressDao();
	private IUserDao userDao = DAOFactory.getUserDao();
	
	@Test
	public void addTest(){
		
		Address address = new Address();
		address.setName("中国北京中关村111号");
		address.setPhone("1111111111");
		address.setPostcode("10010");
		
		addressDao.add(address, 1);
	}
	
	@Test
	public void listTest(){
		
		List<Address> address = addressDao.list(1);
		for (Address add : address) {
			System.out.println(add.getName() + ":" + add.getUser().getUsername());
		}
	}
	
	@Test
	public void loadTest(){
		
		User user = userDao.load(1);
		System.out.println(user.getUsername());
		for (Address address : user.getAddresses()) {
			System.out.println("\t"+address.getName());
		}
	}
}

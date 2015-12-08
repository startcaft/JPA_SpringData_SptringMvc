package mybatis.jdbcdao.test;


import org.junit.Test;

import mybaitsi.shop.model.Address;
import mybatis.shop.jdbcdao.AddressDao;
import mybatis.shop.jdbcdao.DAOFactory;
import mybatis.shop.jdbcdao.IAddressDao;

public class TestAddressDao {

	private IAddressDao addressDao = DAOFactory.getAddressDao();
	
	@Test
	public void addTest(){
		
		Address address = new Address();
		address.setName("中国北京中关村111号");
		address.setPhone("1111111111");
		address.setPostcode("10010");
		
		addressDao.add(address, 1);
	}
}

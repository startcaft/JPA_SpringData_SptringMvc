package mybatis.shop.jdbcdao;

import java.util.List;

import mybaitsi.shop.model.Address;

public interface IAddressDao {
	
	public void add(Address address,int userId);
	public void update(Address address);
	public void delete(int id);
	public Address address();
	public List<Address> list(int userId);
}

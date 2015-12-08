package mybatis.shop.jdbcdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import mybaitsi.shop.model.Address;
import mybatis.shop.util.DBUtil;

public class AddressDao implements IAddressDao {

	public void add(Address address, int userId) {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into t_address(name,phone,postcode,user_id) value (?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, address.getName());
			ps.setString(2,address.getPhone());
			ps.setString(3,address.getPostcode());
			ps.setInt(4, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}

	public void update(Address address) {
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	public Address address() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Address> list(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}

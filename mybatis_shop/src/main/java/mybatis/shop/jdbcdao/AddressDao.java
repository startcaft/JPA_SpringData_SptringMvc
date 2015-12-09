package mybatis.shop.jdbcdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mybaitsi.shop.model.Address;
import mybaitsi.shop.model.User;
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
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Address> addresses = new ArrayList<Address>();
		Address address = null;
		User user = null;
		String sql = "SELECT t1.*,t2.id AS 'uid',t2.username,t2.password,t2.nickname,t2.type FROM t_address t1 LEFT JOIN t_user t2 ON (t1.user_id = t2.id) WHERE t1.user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new Address();
				address.setId(rs.getInt("id"));
				address.setName(rs.getString("name"));
				address.setPhone(rs.getString("phone"));
				address.setPostcode(rs.getString("postcode"));
				
				user = new User();
				user.setId(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setType(rs.getInt("type"));
				
				address.setUser(user);
				addresses.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return addresses;
	}

}

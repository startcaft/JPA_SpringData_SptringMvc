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

public class UserDao implements IUserDao {
	
	private User loadByOneSql(int userId){
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Address> addresses = new ArrayList<Address>();
		Address address = null;
		User user = null;
		String sql = "select *,t2.id as 'a_id' "
				+ "from t_user t1 left join t_address t2 on(t1.id = t2.user_id) where t1.id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				if(user == null){
					user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setNickname(rs.getString("nickname"));
					user.setType(rs.getInt("type"));
				}
				
				address = new Address();
				address.setId(rs.getInt("a_id"));
				address.setName(rs.getString("name"));
				address.setPhone(rs.getString("phone"));
				address.setPostcode(rs.getString("postcode"));
				
				addresses.add(address);
			}
			user.setAddresses(addresses);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return user;
	}

	public User load(int userId) {
		
		
		return loadByOneSql(userId);
		/*
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Address> addresses = new ArrayList<Address>();
		Address address = null;
		User user = new User();
		String sql = "select * from t_user where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setType(rs.getInt("type"));
			}
			sql = "select * from t_address where user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new Address();
				address.setId(rs.getInt("id"));
				address.setName(rs.getString("name"));
				address.setPhone(rs.getString("phone"));
				address.setPostcode(rs.getString("postcode"));
				
				addresses.add(address);
			}
			user.setAddresses(addresses);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return user;
		*/
	}
}

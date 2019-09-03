package piano;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import piano.DBConnection;
import piano.User;

public class UserDeal {
	public static int insert(User user) throws SQLException{
		int ret=0;
		Connection conn=DBConnection.getConnection();
		String sqlInsert="insert into userinformation(UserName,Password,UserBirYear,UserBirMonth,UserBirDay,PhoneNumber,Email) values(?,?,?,?,?,?,?)";
		PreparedStatement pst=conn.prepareStatement(sqlInsert);
		pst.setString(1, user.getUserName());
		pst.setString(2, user.getPassword());
		pst.setString(3, user.getUserBirYear());
		pst.setString(4, user.getUserBirMonth());
		pst.setString(5, user.getUserBirDay());
		pst.setString(6, user.getPhoneNumber());
		pst.setString(7, user.getEmail());
		ret=pst.executeUpdate();
		DBConnection.close(null, pst, conn);
		return ret;
	}
	public static int insert(String UserName,String Password,String UserBirYear,String UserBirMonth,String UserBirDay,String PhoneNumber,String Email){
		return 0;
	}
	public static User getUserByUserName(String UserName) throws SQLException{
		User user=null;
		Connection conn=DBConnection.getConnection();
		String sqlSelect="select UserName,Password from userinformation where UserName=?";
		PreparedStatement pst=conn.prepareStatement(sqlSelect);
		pst.setString(1, UserName);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			user=new User();
			user.setUserName(rs.getString("UserName"));
			user.setPassword(rs.getString("Password"));
		}
		DBConnection.close(rs, pst, conn);
		return user;
	}
		

}

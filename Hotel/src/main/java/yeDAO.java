import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class yeDAO {
	private Statement stmt;
	private Connection conn;
	
	//SQL
	private void connDB() {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String userid="ora_user";
		String passcode="human123";
		try {
			Class.forName(driver);
			this.conn=DriverManager.getConnection(url,userid,passcode);
			if(conn==null) {
				System.out.println("데이터베이스 접속실패");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//SELECT 예약가능객실
	ArrayList<yeDTO> yeyaklist(int type, int num, String checkin, String checkout) {
		ArrayList<yeDTO> list=new ArrayList<yeDTO>();
		try
		{
			connDB();
			String query="select seq, name, price, num from hotel"
						+ " where type=? and num>=?"
						+ " and seq not in"
						+ " (select seq from control"
						+ " where type=? and tnum>=?"
						+ " and(checkin between ? and ? OR checkout between ? and ?"
						+ " or(checkin <= ? and checkout >= ?)))";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, type);
			psmt.setInt(2, num);
			psmt.setInt(3, type);
			psmt.setInt(4, num);
			psmt.setString(5, checkin);
			psmt.setString(6, checkout);
			psmt.setString(7, checkin);
			psmt.setString(8, checkout);
			psmt.setString(9, checkin);
			psmt.setString(10, checkout);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				yeDTO dto=new yeDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setNum(rs.getInt("num"));
				list.add(dto);
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//예약불가능객실
	ArrayList<yeDTO> notlist(int type, int tnum, String checkin, String checkout) {
		ArrayList<yeDTO> list=new ArrayList<yeDTO>();
		try
		{
			connDB();
			String query="select b.seq, b.name, a.tnum, a.checkin, a.checkout, a.tname, a.order_no"
						+ " from control a, hotel b"
						+ " where b.type=? and a.tnum>=?"
						+ " and (a.checkin between ? and ? or a.checkout between ? and ?"
						+ " or(a.checkin <= ? and a.checkout >=?))"
						+ " and a.seq=b.seq";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, type);
			psmt.setInt(2, tnum);
			psmt.setString(3, checkin);
			psmt.setString(4, checkout);
			psmt.setString(5, checkin);
			psmt.setString(6, checkout);
			psmt.setString(7, checkin);
			psmt.setString(8, checkout);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				yeDTO dto=new yeDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setTnum(rs.getInt("tnum"));
				dto.setCheckin(rs.getString("checkin"));
				dto.setCheckout(rs.getString("checkout"));
				dto.setTname(rs.getString("tname"));
				dto.setOrder_no(rs.getInt("order_no"));
				list.add(dto);
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

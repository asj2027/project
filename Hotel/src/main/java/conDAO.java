import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class conDAO {
	private Statement stmt;
	private Connection conn;
	
	//SQL 연결
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
	
	//SELECT
	ArrayList<conDTO> List(int order_no) {
		ArrayList<conDTO> list=new ArrayList<conDTO>();
		try
		{
			connDB();
			String query="select a.order_no, c.name, a.tnum, a.tprice, a.tname, a.mobile, a.checkin, a.checkout, b.typename"
					+ " from control a, room b, hotel c"
					+ " where a.seq=c.seq and a.type=b.type and a.order_no=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, order_no);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				conDTO dto=new conDTO();
				dto.setOrder_no(rs.getInt("order_no"));
				dto.setName(rs.getString("name"));
				dto.setTnum(rs.getInt("tnum"));
				dto.setTprice(rs.getInt("tprice"));
				dto.setTname(rs.getString("tname"));
				dto.setMobile(rs.getString("mobile"));
				dto.setCheckin(rs.getString("checkin"));
				dto.setCheckout(rs.getString("checkout"));
				dto.setTypename(rs.getString("typename"));
				list.add(dto);
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//INSERT
	public void addcon(conDTO dto) {
		try {
			connDB();
			String query="insert into control values(cseq.nextval,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt=conn.prepareStatement(query);
			Statement stmt=conn.createStatement();
			psmt.setInt(1, dto.getSeq());
			psmt.setInt(2, dto.getTnum());
			psmt.setString(3, dto.getCheckin());
			psmt.setString(4, dto.getCheckout());
			psmt.setString(5, dto.getTname());
			psmt.setString(6, dto.getMobile());
			psmt.setInt(7, dto.getTprice());
			psmt.setInt(8, dto.getType());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//UPDATE
	public void updatecon(conDTO dto) {
		try {
			connDB();
			String query="update control set tnum=?,tname=?,mobile=? where order_no=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, dto.getTnum());
			psmt.setString(2, dto.getTname());
			psmt.setString(3, dto.getMobile());
			psmt.setInt(4, dto.getOrder_no());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//DELETE
	public void deletecon(int order_no) {
		try {
			connDB();
			String query="delete from control where order_no=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, order_no);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

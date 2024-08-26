import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class hotelDAO {
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
	ArrayList<hotelDTO> List() {
		ArrayList<hotelDTO> list=new ArrayList<hotelDTO>();
		try
		{
			connDB();
			String query="select a.*,b.typename"
						+" from hotel a, room b"
						+" where a.type=b.type";
			this.stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				hotelDTO dto=new hotelDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setType(Integer.parseInt(rs.getString("type")));
				dto.setNum(Integer.parseInt(rs.getString("num")));
				dto.setPrice(Integer.parseInt(rs.getString("price")));
				dto.setTypename(rs.getString("typename"));;
				list.add(dto);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	ArrayList<hotelDTO>getlist(){
		ArrayList<hotelDTO> list = new ArrayList<hotelDTO>();
		try {
			connDB();
			String query ="select * from room";
			this.stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next()) {
				hotelDTO tdto = new hotelDTO();
				tdto.setType(rs.getInt("type"));
				tdto.setTypename(rs.getString("typename"));
				list.add(tdto);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch(Exception e) {
			
		}
		return list;
	}

	
	//Insert
	public void addhotel(hotelDTO dto) {
		try {
			connDB();
			String query="insert into hotel values(hseq.nextval,?,?,?,?)";
			PreparedStatement psmt=conn.prepareStatement(query);
			Statement stmt=conn.createStatement();
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getType());
			psmt.setInt(3, dto.getNum());
			psmt.setInt(4, dto.getPrice());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//UPDATE
	public void updatehotel(hotelDTO dto) {
		try {
			connDB();
			String query="update hotel set name=?,type=?,num=?,price=? where seq=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getType());
			psmt.setInt(3, dto.getNum());
			psmt.setInt(4, dto.getPrice());
			psmt.setInt(5, dto.getSeq());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//DELETE
	public void deletehotel(int seq) {
		try {
			connDB();
			String query="delete from hotel where seq=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, seq);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

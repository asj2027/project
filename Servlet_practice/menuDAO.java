import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class menuDAO {
	private Statement stmt;
	private Connection conn;
	
	//sql 연결
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
	
	//select 조회
			ArrayList<menuDTO> List() {
				ArrayList<menuDTO> list=new ArrayList<menuDTO>();
				try
				{
					connDB();
					String query="select * from menu";
					this.stmt=conn.createStatement();
					System.out.println(query);
					ResultSet rs=stmt.executeQuery(query);
					while(rs.next()) {
						menuDTO dto=new menuDTO();
						dto.setSeqno(rs.getInt("seqno"));
						dto.setName(rs.getString("name"));
						dto.setPrice(rs.getInt("price"));
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
			
	
	//insert
	public void addMenu(menuDTO dto) {
		try {
			connDB();
			String query="insert into menu values(sq.nextval,?,?)";
			PreparedStatement psmt=conn.prepareStatement(query);
			Statement stmt=conn.createStatement();
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getPrice());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete
	public void deleteMenu(int seqno) {
		try {
			connDB();
			String query="delete from menu where seqno=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, seqno);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public menuDTO getMenu(int seqno) {
		menuDTO dto=new menuDTO();
		try {
			connDB();
			String query="select * from menu where seqno='"+seqno+"'";
			this.stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			dto.setSeqno(rs.getInt("seqno"));
			dto.setName(rs.getString("name"));
			dto.setPrice(rs.getInt("price"));
			this.stmt.close();
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//update
	public void updateMenu(menuDTO dto) {
		try {
			connDB();
			String query="update menu set name=?,price=? where seqno=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getPrice());
			psmt.setInt(3, dto.getSeqno());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

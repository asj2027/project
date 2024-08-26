import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class IncomeDAO {
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
		ArrayList<IncomeDTO> List() {
			ArrayList<IncomeDTO> orderlist=new ArrayList<IncomeDTO>();
			try
			{
				connDB();
				String query="select a.mobile, b.name, a.qty, a.price, a.income_date"
						   + " from income a, menu b"
						   + " where a.seqno=b.seqno";
				this.stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next()) {
					IncomeDTO dto=new IncomeDTO();
					dto.setMobile(rs.getString("mobile"));
					dto.setMenuname(rs.getString("name"));
					dto.setQty(Integer.parseInt(rs.getString("qty")));
					dto.setPrice(Integer.parseInt(rs.getString("price")));
					dto.setIncome_date(rs.getString("income_date"));
					orderlist.add(dto);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return orderlist;
		}
		
		//insert
		public void addIncome(IncomeDTO dto) {
			try {
				connDB();
				String query="insert into income values(order_seq.nextval,?,?,?,?,to_char(sysdate,'YYYY-MM-DD HH24:mi:SS'))";
				PreparedStatement psmt=conn.prepareStatement(query);
				Statement stmt=conn.createStatement();
				psmt.setString(1, dto.getMobile());
				psmt.setInt(2, dto.getSeqno());
				psmt.setInt(3, dto.getQty());
				psmt.setInt(4, dto.getPrice());
				psmt.executeUpdate();
				psmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public int getSum() {
			int sum=0;
			try {
				connDB();
				String query="select sum(price) as total from income";
				this.stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(query);
				rs.next();
				sum=rs.getInt("total");
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println(sum);
			return sum;
		}
}

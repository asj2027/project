

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class IncomelistServlet
 */
@WebServlet("/Incomelist")
public class IncomelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomelistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//String outstr="";
		IncomeDAO dao=new IncomeDAO();
		ArrayList<IncomeDTO> orderlist=dao.List();
		
		JSONArray ja=new JSONArray();
		
		for(int i=0;i<orderlist.size();i++) {
			IncomeDTO data=new IncomeDTO();
			data=orderlist.get(i);
			JSONObject jo=new JSONObject();
			jo.put("mobile", data.getMobile());
			jo.put("menuname", data.getMenuname());
			jo.put("qty", data.getQty());
			jo.put("price", data.getPrice());
			jo.put("income_date", data.getIncome_date());
			ja.add(jo);
		//	outstr+="<option>"+data.getMobile()+" / "+data.getMenuname()+" / "+data.getQty()+" / "+data.getPrice()+" / "+data.getIncome_date()+"</option>";
		}
		//response.getWriter().print(outstr);
		response.getWriter().print(ja.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

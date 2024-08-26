

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
 * Servlet implementation class conlistServlet
 */
@WebServlet("/conlist")
public class conlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public conlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		conDAO dao=new conDAO();
		ArrayList<conDTO> list=dao.List(Integer.parseInt(request.getParameter("order_no")));
		
		JSONArray ja=new JSONArray();
		
		for(int i=0;i<list.size();i++) {
			conDTO data=new conDTO();
			data=list.get(i);
			JSONObject jo=new JSONObject();
			jo.put("order_no", data.getOrder_no());
			jo.put("name", data.getName());
			jo.put("tnum", data.getTnum());
			jo.put("tprice", data.getTprice());
			jo.put("tname", data.getTname());
			jo.put("mobile", data.getMobile());
			jo.put("checkin", data.getCheckin());
			jo.put("checkout", data.getCheckout());
			jo.put("typename", data.getTypename());
			ja.add(jo);
		}
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

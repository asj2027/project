

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
 * Servlet implementation class notServlet
 */
@WebServlet("/not")
public class notServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public notServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		int type=Integer.parseInt(request.getParameter("type"));
		int tnum=Integer.parseInt(request.getParameter("tnum"));
		String checkin=request.getParameter("checkin");
		String checkout=request.getParameter("checkout");
		
		yeDAO dao=new yeDAO();
		ArrayList<yeDTO> list=dao.notlist(type, tnum, checkin, checkout);
		
		JSONArray ja=new JSONArray();
		
		for(int i=0;i<list.size();i++) {
			yeDTO data=new yeDTO();
			data=list.get(i);
			JSONObject jo=new JSONObject();
			jo.put("name", data.getName());
			jo.put("seq", data.getSeq());
			jo.put("checkin", data.getCheckin());
			jo.put("checkout", data.getCheckout());
			jo.put("tname", data.getTname());
			jo.put("tnum", data.getTnum());
			jo.put("order_no", data.getOrder_no());
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

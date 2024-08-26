

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
 * Servlet implementation class yeServlet
 */
@WebServlet("/ye")
public class yeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public yeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		yeDAO dao=new yeDAO();
		int type=Integer.parseInt(request.getParameter("type"));
		int num=Integer.parseInt(request.getParameter("num"));
		String checkin=request.getParameter("checkin");
		String checkout=request.getParameter("checkout");
		JSONArray ja=new JSONArray();
		ArrayList<yeDTO> list=dao.yeyaklist(type, num, checkin, checkout);
		for(int i=0;i<list.size();i++) {
			yeDTO data=new yeDTO();
			data=list.get(i);
			JSONObject jo=new JSONObject();
			jo.put("seq", data.getSeq());
			jo.put("name", data.getName());
			jo.put("price", data.getPrice());
			jo.put("num", data.getNum());
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

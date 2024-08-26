

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
 * Servlet implementation class hlistServlet
 */
@WebServlet("/hlist")
public class hlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		hotelDAO dao=new hotelDAO();
		ArrayList<hotelDTO> list=dao.List();
		
		JSONArray ja=new JSONArray();
		
		for(int i=0;i<list.size();i++) {
			hotelDTO data=new hotelDTO();
			data=list.get(i);
			JSONObject jo=new JSONObject();
			jo.put("seq", data.getSeq());
			jo.put("name", data.getName());
			jo.put("type", data.getType());
			jo.put("num", data.getNum());
			jo.put("price", data.getPrice());
			ja.add(jo);
	//		outstr+="<option value="+data.getSeq()+">"+data.getName()+"/"+data.getType()+"/"+data.getNum()+"/"+data.getPrice()+"</option>";
		}
	//	response.getWriter().print(outstr);
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



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
 * Servlet implementation class typeServlet
 */
@WebServlet("/type")
public class typeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public typeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		hotelDAO tdao= new hotelDAO();
		ArrayList<hotelDTO> list=tdao.getlist();
		JSONArray ja = new JSONArray();
		for(int i=0;i<list.size();i++) {
			hotelDTO dto = new hotelDTO();
			dto=list.get(i);
			JSONObject jo = new JSONObject();
			jo.put("type", dto.getType());
			jo.put("typename", dto.getTypename());
			ja.add(jo);
		}
		response.setContentType("text/html; charset=utf-8");
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

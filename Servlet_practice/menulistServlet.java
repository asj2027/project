

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
 * Servlet implementation class menulistServlet
 */
@WebServlet("/menulist")
public class menulistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public menulistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
//		String outstr="";
		menuDAO dao=new menuDAO();
		ArrayList<menuDTO> list=dao.List();
		
		JSONArray ja=new JSONArray();
		
		for(int i=0;i<list.size();i++) {
			menuDTO data=new menuDTO();
			data=list.get(i);
			JSONObject jo=new JSONObject();
			jo.put("seqno", data.getSeqno());
			jo.put("name", data.getName());
			jo.put("price", data.getPrice());
			ja.add(jo);
//			outstr+="<option value="+data.getSeqno()+">"+data.getName()+": "+data.getPrice()+"</option>";
		}
		response.getWriter().print(ja.toJSONString());
//		response.getWriter().print(outstr);
//		System.out.println(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

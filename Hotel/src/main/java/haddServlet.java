

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class haddServlet
 */
@WebServlet("/hadd")
public class haddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public haddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		hotelDTO dto=new hotelDTO();
		String name=request.getParameter("name");
		int type=Integer.parseInt(request.getParameter("type"));
		int num=Integer.parseInt(request.getParameter("num"));
		int price=Integer.parseInt(request.getParameter("price"));
		dto.setName(name);
		dto.setType(type);
		dto.setNum(num);
		dto.setPrice(price);
		
		hotelDAO dao=new hotelDAO();
		dao.addhotel(dto);
		
		String outstr="";
		ArrayList<hotelDTO> list=dao.List();
		for(int i=0;i<list.size();i++) {
			hotelDTO data=new hotelDTO();
			data=list.get(i);
			outstr+="<option value="+data.getSeq()+">"+data.getName()+"/"+data.getType()+"/"+data.getNum()+"/"+data.getPrice()+"</option>";
		}
		response.getWriter().print(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

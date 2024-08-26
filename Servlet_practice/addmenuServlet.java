

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addmenuServlet
 */
@WebServlet("/addmenu")
public class addmenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addmenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		menuDTO dto=new menuDTO();
		String name=request.getParameter("name");
		int price=Integer.parseInt(request.getParameter("price"));
		dto.setName(name);
		dto.setPrice(price);
		
		menuDAO dao=new menuDAO();
		dao.addMenu(dto);
		
		String outstr="";
		ArrayList<menuDTO> list=dao.List();
		for(int i=0;i<list.size();i++) {
			menuDTO data=new menuDTO();
			data=list.get(i);
			outstr+="<option value="+data.getSeqno()+">"+data.getName()+": "+data.getPrice()+"</option>";
		}
		response.getWriter().print(outstr);
		System.out.println(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

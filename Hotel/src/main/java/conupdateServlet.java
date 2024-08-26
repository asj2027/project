

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class conupdateServlet
 */
@WebServlet("/conupdate")
public class conupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public conupdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int tnum=Integer.parseInt(request.getParameter("tnum"));
		String tname=request.getParameter("tname");
		String mobile=request.getParameter("mobile");
		int order_no=Integer.parseInt(request.getParameter("order_no"));
		
		conDTO dto=new conDTO();

		dto.setTnum(tnum);
		dto.setTname(tname);
		dto.setMobile(mobile);
		dto.setOrder_no(order_no);
		conDAO dao=new conDAO();
		dao.updatecon(dto);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

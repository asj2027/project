

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class hupdateServlet
 */
@WebServlet("/hupdate")
public class hupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hupdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name=request.getParameter("name");
		int type=Integer.parseInt(request.getParameter("type"));
		int num=Integer.parseInt(request.getParameter("num"));
		int price=Integer.parseInt(request.getParameter("price"));
		int seq=Integer.parseInt(request.getParameter("seq"));
		
		hotelDTO dto=new hotelDTO();
		dto.setName(name);
		dto.setType(type);
		dto.setNum(num);
		dto.setPrice(price);
		dto.setSeq(seq);
		hotelDAO dao=new hotelDAO();
		dao.updatehotel(dto);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class conaddServlet
 */
@WebServlet("/conadd")
public class conaddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public conaddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		conDTO dto=new conDTO();
		int seq=Integer.parseInt(request.getParameter("seq"));
		int tnum=Integer.parseInt(request.getParameter("tnum"));
		String checkin=request.getParameter("checkin");
		String checkout=request.getParameter("checkout");
		String tname=request.getParameter("tname");
		String mobile=request.getParameter("mobile");
		int tprice=Integer.parseInt(request.getParameter("tprice"));
		int type=Integer.parseInt(request.getParameter("type"));
		System.out.println(type);
		dto.setSeq(seq);
		dto.setTnum(tnum);
		dto.setCheckin(checkin);
		dto.setCheckout(checkout);
		dto.setTname(tname);
		dto.setMobile(mobile);
		dto.setTprice(tprice);
		dto.setType(type);
		
		conDAO dao=new conDAO();
		dao.addcon(dto);
		
//		String outstr="";
//		ArrayList<conDTO> list=dao.List(Integer.parseInt(request.getParameter("order_no")));
//		for(int i=0;i<list.size();i++) {
//			conDTO data=new conDTO();
//			data=list.get(i);
//			outstr+="<option value="+data.getOrder_no()+">"+data.getName()+" "
//					+data.getTnum()+" "+data.getTname()+" "
//					+data.getCheckin()+" ~ "+data.getCheckout()+" "+data.getMobile()+" "+data.getTprice()+" "+data.getTypename()+" "+data.getSeq()+" "+data.getType()+"</option>";
//		}
//		response.getWriter().print(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

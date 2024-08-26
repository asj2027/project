

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addIncomeServlet
 */
@WebServlet("/addIncome")
public class addIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addIncomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		IncomeDTO dto=new IncomeDTO();
		System.out.println(request.getParameter("mobile"));
		System.out.println(request.getParameter("seqno"));
		System.out.println(request.getParameter("qty"));
		System.out.println(request.getParameter("price"));
		String mobile=request.getParameter("mobile");
		int seqno=Integer.parseInt(request.getParameter("seqno"));
		int qty=Integer.parseInt(request.getParameter("qty"));
		int price=Integer.parseInt(request.getParameter("price"));
		dto.setMobile(mobile);
		dto.setSeqno(seqno);
		dto.setQty(qty);
		dto.setPrice(price);
		
		IncomeDAO dao=new IncomeDAO();
		dao.addIncome(dto);
		
		
//		String outstr="";
//		ArrayList<IncomeDTO> orderlist=dao.List();
//		for(int i=0;i<orderlist.size();i++) {
//			IncomeDTO data=new IncomeDTO();
//			data=orderlist.get(i);
//			outstr+="<option value="+data.getPrice()+"name="+data.getMenuname()+"id="+data.getPrice()+">"+data.getMobile()+"-"+data.getMenuname()+"-"+data.getQty()+"-"+data.getPrice()+"-"+data.getIncome_date()+"</option>";
//		}
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

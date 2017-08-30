package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.UserBean;

/**
 * Servlet implementation class ViewUnapprovedUserServlet
 */
@WebServlet("/ViewUnapprovedUserServlet")
public class ViewUnapprovedUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
		UserDAO dao = new UserDAO();
		List<UserBean> unapprovedUserList = dao.selectAllUnapprovedUser();
		int currentPageNum = 1;
		String code=request.getParameter("code");
		int unapprovedUserNum = unapprovedUserList.size();
		int pageNum = (int)Math.ceil((double)unapprovedUserNum/5);
		
		if(code!=null & code.equals("lastPage"))
		{
			currentPageNum = pageNum;
		}
		else if(code!=null & code.equals("firstPage"))
		{
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("unapprovedUserNum", unapprovedUserNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.setAttribute("unapprovedUserList", unapprovedUserList);
			request.getRequestDispatcher("signup_check.jsp").forward(request, response);
		}
		else if(code != null & (Integer.parseInt(code))>0)
		{
			currentPageNum = Integer.parseInt(code) + 1;
			if(currentPageNum>pageNum)
				currentPageNum = pageNum;
			
		}
		else if(code != null & (Integer.parseInt(code))<0)
		{
			currentPageNum = -1*(Integer.parseInt(code)) - 1;
			if(currentPageNum < 1)
				currentPageNum = pageNum;
		}
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("unapprovedUserNum", unapprovedUserNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.setAttribute("unapprovedUserList", unapprovedUserList);
		request.getRequestDispatcher("signup_check.jsp").forward(request, response);
		
	}
}

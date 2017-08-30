package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.DepartmentBean;
import conferenceManagement.Entity.UserBean;

public class ViewAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");

		UserDAO dao = new UserDAO();
		int currentPageNum = 1;
		request.setAttribute("flag", 0);
		if (code != null & code.equals("approve")) 
		{
			List<UserBean> unapprovedUserList = dao.selectAllUnapprovedUser();
			int unapprovedUserNum = unapprovedUserList.size();
			int pageNum = (int)Math.ceil((double)unapprovedUserNum/5);
			request.setAttribute("unapprovedUserList", unapprovedUserList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("unapprovedUserNum", unapprovedUserNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("signup_check.jsp").forward(request, response);
		}
		else if (code != null & code.equals("meeting")) 
		{
			List<UserBean> userList = dao.selectAllUser();
			DepartmentDAO ddao=new DepartmentDAO();
			List<DepartmentBean> departmentsList=ddao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("meeting_apply.jsp").forward(request, response);
		}
		else if(code != null & code.equals("manageLastPage"))
		{
			List<UserBean> userList = dao.selectAllUser();
			int userNum = userList.size();
			request.setAttribute("userNum", userNum);
			int pageNum = (int)Math.ceil((double)userNum/5);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("userList", userList);
			currentPageNum = pageNum;
			request.setAttribute("currentPageNum", currentPageNum);
			DepartmentDAO ddao=new DepartmentDAO();
			List<DepartmentBean> departmentsList=ddao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("user_management.jsp").forward(request, response);
		}
		else if(code != null & code.equals("manage"))
		{
			List<UserBean> userList = dao.selectAllUser();
			int userNum = userList.size();
			request.setAttribute("userNum", userNum);
			int pageNum = (int)Math.ceil((double)userNum/5);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("userList", userList);
			request.setAttribute("currentPageNum", currentPageNum);
			DepartmentDAO ddao=new DepartmentDAO();
			List<DepartmentBean> departmentsList=ddao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("user_management.jsp").forward(request, response);
		}
		else if(code != null & (Integer.parseInt(code))>0)
		{
			currentPageNum = Integer.parseInt(code) + 1;
			List<UserBean> userList = dao.selectAllUser();
			int userNum = userList.size();
			request.setAttribute("userNum", userNum);
			int pageNum = (int)Math.ceil((double)userNum/5);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("userList", userList);
			if(currentPageNum>pageNum)
				currentPageNum = pageNum;
			request.setAttribute("currentPageNum", currentPageNum);
			DepartmentDAO ddao=new DepartmentDAO();
			List<DepartmentBean> departmentsList=ddao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("user_management.jsp").forward(request, response);
		}
		else if(code != null & (Integer.parseInt(code))<0)
		{
			currentPageNum = -1*(Integer.parseInt(code)) - 1;
			List<UserBean> userList = dao.selectAllUser();
			int userNum = userList.size();
			request.setAttribute("userNum", userNum);
			int pageNum = (int)Math.ceil((double)userNum/5);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("userList", userList);
			if(currentPageNum < 1)
				currentPageNum = pageNum;
			request.setAttribute("currentPageNum", currentPageNum);
			DepartmentDAO ddao=new DepartmentDAO();
			List<DepartmentBean> departmentsList=ddao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("user_management.jsp").forward(request, response);
		}
		

	}

}

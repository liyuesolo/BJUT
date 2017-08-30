package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conferenceManagement.DAO.ConferenceDAO;
import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.DepartmentBean;
import conferenceManagement.Entity.UserBean;

/**
 * Servlet implementation class ApproveServlet
 */
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String user_name = request.getParameter("user_name");
		String oper =request.getParameter("oper");
		UserDAO dao=new UserDAO();
		
		if(oper!=null&&oper.equals("yes"))
		{
			DepartmentDAO ddao = new DepartmentDAO();
			UserBean user = dao.selectByUsername(user_name);
			DepartmentBean department = ddao.selectByDepartmentname(user.getDepartmentname());
			
			int staff_num = department.getStaffNumber();
			ddao.updateStaffNum(user.getDepartmentname(), staff_num+1);
			dao.updateStatus(user_name,1);
		}
		
		if(oper!=null&&oper.equals("no")){
			dao.updateStatus(user_name,2);
		}
		HttpSession session=request.getSession();
		UserDAO udao=new UserDAO();
		List<UserBean> unapprovedUserList = udao.selectAllUnapprovedUser();
		int unapprovedUserNum = unapprovedUserList.size();
		session.setAttribute("unapprovedUserNumCommon", unapprovedUserNum);
		request.getRequestDispatcher("ViewAllUserServlet?code=approve").forward(request, response);
		
	}
  

}

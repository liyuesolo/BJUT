package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.Entity.DepartmentBean;
import conferenceManagement.Entity.UserBean;
import conferenceManagement.Service.Service;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		request.setCharacterEncoding("utf-8");
		
		UserBean user = new UserBean();
		user.setUsername(request.getParameter("user_name"));
		user.setPassword(request.getParameter("password"));
		user.setUserID(request.getParameter("user_id"));
		user.setPhone(request.getParameter("phone"));
		user.setMail(request.getParameter("mail"));
		user.setAge(Integer.parseInt(request.getParameter("age")));
		user.setGender(Integer.parseInt(request.getParameter("gender")));
		user.setDepartmentname(request.getParameter("department_name"));
		
		Service service = new Service();
		int flag=service.regist(user);
		
		if(flag==1)
		{
			request.setAttribute("msg", "注册成功，正在审核。");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else
		{
			request.setAttribute("msg", "用户名已存在，请重新注册。");
			DepartmentDAO dao=new DepartmentDAO();		
			List<DepartmentBean> departmentsList=dao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
		
	}
}

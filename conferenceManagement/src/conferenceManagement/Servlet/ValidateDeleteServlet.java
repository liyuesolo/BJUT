package conferenceManagement.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.UserBean;
@WebServlet("/ValidateDeleteServlet")
public class ValidateDeleteServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String department_name = request.getParameter("department_name");
		UserDAO udao = new UserDAO();
		List<UserBean> userList = udao.selectAllUserInDepartment(department_name);
		PrintWriter out = response.getWriter();
		if(userList.size() == 0)
			out.println("-1");
		else
			out.println("1");

	}

}

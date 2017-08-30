package conferenceManagement.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.UserBean;

/**
 * Servlet implementation class UpdataProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String code = request.getParameter("code");
		UserDAO dao=new UserDAO();
		String user_name = request.getParameter("user_name");
		if(code!=null&code.equals("update"))
		{
			String password = request.getParameter("password");
			String mail = request.getParameter("mail");
			String phone = request.getParameter("phone");
			int age = Integer.parseInt(request.getParameter("age"));
			dao.updateAge(user_name, age);
			dao.updateMail(user_name, mail);
			dao.updatePassword(user_name, password);
			dao.updatePhone(user_name, phone);
			
		}
		UserBean currentUser = dao.selectByUsername(user_name);
		request.setAttribute("currentUser", currentUser);
		request.getRequestDispatcher("profile.jsp").forward(request, response);
		
	}

}

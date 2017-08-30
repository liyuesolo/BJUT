package conferenceManagement.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.UserBean;

public class ValidateUsernameServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		boolean flag = true;
		String message = "";
		UserDAO dao = new UserDAO();
		UserBean e = dao.selectByUsername(request.getParameter("user_name"));
		if (e == null) {
			message = "用户名可以使用";

		} else {
			flag = false;
			message = "用户名已经存在";
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String json = "{\"msg\":\"" + message + "\",\"flag\":" + flag + "}";
		out.print(json);

	}

}

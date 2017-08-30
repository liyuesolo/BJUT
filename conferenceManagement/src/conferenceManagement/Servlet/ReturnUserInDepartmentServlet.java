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

/**
 * Servlet implementation class ReturnUserInDepartmentServlet
 */
@WebServlet("/ReturnUserInDepartmentServlet")
public class ReturnUserInDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		UserDAO udao = new UserDAO();
		String department_name = request.getParameter("department_name");
		List<UserBean> userList = udao.selectAllUserInDepartment(department_name);
		String json="[";
		for(int i=0;i<userList.size();i++)
		{
			json+="\"" + userList.get(i).getUsername() + "\",";
		}
		json = (String) json.subSequence(0, json.length()-2);
		json+="\"]";
		
		PrintWriter out = response.getWriter();
		out.print(json);

	}

}

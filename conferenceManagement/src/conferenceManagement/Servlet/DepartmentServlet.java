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
import conferenceManagement.Service.Service;

/**
 * Servlet implementation class DepartmentServlet
 */

public class DepartmentServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        this.doPost(request, response);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException 
    {  
    	String code = request.getParameter("code");
		Service service = new Service();

		if (code != null && code.equals("add")) {
			service.addDepartment(request.getParameter("department_name"));
		}

		if (code != null && code.equals("delete")) {
			service.deleteDepartment(request.getParameter("department_name"));
		}

		request.getRequestDispatcher("ViewAllDepartmentsServlet?code=viewalldepartments").forward(request, response);
			 
    }  

}

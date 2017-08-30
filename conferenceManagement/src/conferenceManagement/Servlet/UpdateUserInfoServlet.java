package conferenceManagement.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.DepartmentBean;
import conferenceManagement.Entity.UserBean;

/**
 * Servlet implementation class UpdateUserInfoServlet
 */
@WebServlet("/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        this.doPost(request, response);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException 
    {
    	String user_name = request.getParameter("user_name");
    	DepartmentDAO ddao = new DepartmentDAO();
    	UserDAO dao=new UserDAO();
		UserBean user = dao.selectByUsername(user_name);
		DepartmentBean department = ddao.selectByDepartmentname(user.getDepartmentname());
		
		int staff_num = department.getStaffNumber();
		ddao.updateStaffNum(user.getDepartmentname(), staff_num-1);
    	
    	int del_flag = 0;
    	String del_flag_str = request.getParameter("del_flag");
    	String department_name = request.getParameter("department_name").trim();
    	if(del_flag_str.trim().equals("离职"))
    	{
    		del_flag = 1;
    	}
    	else
    	{
    		DepartmentBean newDepartment = ddao.selectByDepartmentname(department_name);
    		staff_num = newDepartment.getStaffNumber();
    		if(newDepartment.getDepartmentname().equals(department.getDepartmentname()))
    				ddao.updateStaffNum(newDepartment.getDepartmentname(), staff_num+2);
    		else
    			ddao.updateStaffNum(department_name, staff_num+1);
    	}
    	dao.updateDelflag(user_name, del_flag);
    	dao.updateDepartment(user_name, department_name);

	}

}

package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class SearchDepartmentServlet
 */
@WebServlet("/SearchDepartmentServlet")
public class SearchDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DepartmentDAO ddao=new DepartmentDAO();
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		int currentPageNum = 1;
		
		String department_name = request.getParameter("department_name");
		String code = request.getParameter("code");
		
		request.setAttribute("currentDepartmentname", department_name);
		
		request.setAttribute("flag", 1);
		
		DepartmentDAO dao = new DepartmentDAO();
		List<DepartmentBean> departmentsList = dao.selectAll();
		if(!department_name.equals(""))
		{
			for(int i=0;i<departmentsList.size();i++)
			{
				if(!departmentsList.get(i).getDepartmentname().equals(department_name))
				{
					departmentsList.remove(i);
					i--;
				}
			}
		}
		int departmentNum = departmentsList.size();
		int pageNum = (int)Math.ceil((double)departmentNum/5);
			
		if(code!=null & code.equals("lastPage"))
		{
			currentPageNum = pageNum;
		}
		else if(code!=null & code.equals("search"))
		{
			request.setAttribute("departmentsList", departmentsList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("departmentNum", departmentNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.setAttribute("departmentsList", departmentsList);
			request.setAttribute("department_name", department_name);
			request.getRequestDispatcher("department_management.jsp").forward(request, response);
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
		
		request.setAttribute("departmentsList", departmentsList);
		request.setAttribute("departmentNum", departmentNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.setAttribute("departmentsList", departmentsList);
		request.setAttribute("department_name", department_name);
		request.getRequestDispatcher("department_management.jsp").forward(request, response);

		
	}
}

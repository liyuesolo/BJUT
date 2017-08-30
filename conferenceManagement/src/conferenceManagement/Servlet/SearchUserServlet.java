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
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
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
		String user_name = request.getParameter("user_name");
		int user_status = Integer.parseInt(request.getParameter("user_status"));
		int del_flag = Integer.parseInt(request.getParameter("del_flag"));
		String department_name = request.getParameter("department_name");
		String code = request.getParameter("code");
		request.setAttribute("currentUsername", user_name);
		request.setAttribute("currentDelflag", del_flag);
		request.setAttribute("currentDepartmentname", department_name);
		request.setAttribute("currentUserStatus", user_status);
		request.setAttribute("flag", 1);
		
		UserDAO dao = new UserDAO();
		if(user_name.equals(""))
		{
			
			List<UserBean> userList = dao.selectAllUser();
			if(!department_name.equals("全部部门"))
			{
				 for(int  i = 0; i < userList.size(); i++) 
				 { 
					 if(!userList.get(i).getDepartmentname().equals(department_name))
					 {
						 userList.remove(i);
						 i--;
						 
					 }
				 }
			}
			if(user_status!=2)
			{
				for(int  i = 0   ; i < userList.size(); i++) 
				 { 
					 if(userList.get(i).getUserStatus()!=user_status)
					 {
						 userList.remove(i);
						 i--;
					 }
				 }
			}
			if(del_flag!=2)
			{
				for(int  i = 0   ; i < userList.size(); i++) 
				 { 
					 if(userList.get(i).getDelflag()!=del_flag)
					 {
						 userList.remove(i);
						 i--;
					 }
				 }
			}
			
			int userNum = userList.size();
			int pageNum = (int)Math.ceil((double)userNum/5);
			
			if(code!=null & code.equals("searchLastPage"))
			{
				currentPageNum = pageNum;
			}
			else if(code!=null & code.equals("search"))
			{
				request.setAttribute("userList", userList);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("userNum", userNum);
				request.setAttribute("currentPageNum", currentPageNum);
				List<DepartmentBean> departmentsList=ddao.selectAll();
				request.setAttribute("departmentsList", departmentsList);
				request.getRequestDispatcher("user_management.jsp").forward(request, response);
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
			
			request.setAttribute("userList", userList);
			request.setAttribute("userNum", userNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPageNum", currentPageNum);
			List<DepartmentBean> departmentsList=ddao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("user_management.jsp").forward(request, response);
			
		}
		else
		{
			List<UserBean> userList = new ArrayList<UserBean>();
			UserBean user = dao.selectByUsername(user_name);
			userList.add(user);
			request.setAttribute("userList", userList);
			request.setAttribute("userNum", 1);
			request.setAttribute("pageNum", 1);
			request.setAttribute("currentPageNum", 1);
			List<DepartmentBean> departmentsList=ddao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("user_management.jsp").forward(request, response);
		}
		
	}
}

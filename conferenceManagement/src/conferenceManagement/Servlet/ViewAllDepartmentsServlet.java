package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.Entity.DepartmentBean;

public class ViewAllDepartmentsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DepartmentDAO dao=new DepartmentDAO();
		int currentPageNum = 1;
		List<DepartmentBean> departmentsList=dao.selectAll();
		request.setAttribute("departmentsList", departmentsList);
		int department_num = departmentsList.size();
		int pageNum = (int)Math.ceil((double)department_num/5);
		String code=request.getParameter("code");
		request.setAttribute("flag", 0);
		
		if(code!=null&&code.equals("regist")){
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("viewalldepartments"))
		{
			request.setAttribute("department_num", department_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("department_management.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("lastPage"))
		{
			currentPageNum = pageNum;
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
		request.setAttribute("department_num", department_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.getRequestDispatcher("department_management.jsp").forward(request, response);
	}

}

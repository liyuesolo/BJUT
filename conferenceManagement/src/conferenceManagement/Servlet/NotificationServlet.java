package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conferenceManagement.DAO.NotificationDAO;
import conferenceManagement.Entity.NotificationBean;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		session.setAttribute("unreadNotificationNum", 0);
		String code = request.getParameter("code");
		String user_name = (String) session.getAttribute("user_name");
		int currentPageNum = 1;
		NotificationDAO ndao = new NotificationDAO();
		List<NotificationBean> notificationList = ndao.selectByUsername(user_name);
		for(int  i=0;i<notificationList.size();i++)
		{
			ndao.updateReadFlag(notificationList.get(i).getNotificationId(), 1);
		}
		request.setAttribute("notificationList", notificationList);
		int notificationNum = notificationList.size();
		int pageNum = (int)Math.ceil((double)notificationNum/5);
		if(code!=null & code.equals("display"))
		{
			request.setAttribute("currentPageNum", currentPageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("notificationNum", notificationNum);
			request.getRequestDispatcher("notice.jsp").forward(request, response);
		}
		else if(code!=null & code.equals("lastPage"))
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
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("notificationNum", notificationNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.getRequestDispatcher("notice.jsp").forward(request, response);
	}

}

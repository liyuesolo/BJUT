package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conferenceManagement.DAO.ConferenceDAO;
import conferenceManagement.DAO.NotificationDAO;
import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.NotificationBean;
import conferenceManagement.Entity.UserBean;
import conferenceManagement.Service.Service;
/** * Servlet implementation class LoginServlet */
public class LoginServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		String username=request.getParameter("user_name");
		String password=request.getParameter("password");

		Service service=new Service();
		int flag = service.login(username, password);
		
		if(flag==1)
		{
			HttpSession session=request.getSession();
			session.setAttribute("user_name", username);
			session.setAttribute("privilege", service.getLoginedUser().getPrivilege());
			NotificationDAO ndao = new NotificationDAO();
			List<NotificationBean> notifications = ndao.selectByUsername(username);
			session.setAttribute("unreadNotificationNum", notifications.size());
			ConferenceDAO cdao=new ConferenceDAO();
			UserDAO udao=new UserDAO();
			List<ConferenceBean> conferenceList = cdao.selectUnapprovedAll();
			int unapprovedConferenceNum = conferenceList.size();
			List<UserBean> unapprovedUserList = udao.selectAllUnapprovedUser();
			int unapprovedUserNum = unapprovedUserList.size();
			session.setAttribute("unapprovedConferenceNum", unapprovedConferenceNum);
			session.setAttribute("unapprovedUserNumCommon", unapprovedUserNum);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else{
			if(flag==0){
				request.setAttribute("msg", "正在审核，请耐心等待。");
			}
			
			if(flag==2){
				request.setAttribute("msg", "审核未通过，请核实后重新注册。");
			}
			
			if(flag==3){
				request.setAttribute("msg", "用户名或密码错误，请重试。");
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
                

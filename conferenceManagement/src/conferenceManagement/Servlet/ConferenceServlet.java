package conferenceManagement.Servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import conferenceManagement.DAO.ConferenceDAO;
import conferenceManagement.DAO.ConferenceRoomDAO;
import conferenceManagement.DAO.NotificationDAO;
import conferenceManagement.DAO.UserConferenceDAO;
import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.ConferenceRoomBean;
import conferenceManagement.Entity.NotificationBean;
import conferenceManagement.Entity.UserBean;
import conferenceManagement.Entity.UserConferenceBean;

/**
 * Servlet implementation class ConferenceServlet
 */
@WebServlet("/ConferenceServlet")
public class ConferenceServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ConferenceDAO cdao=new ConferenceDAO();
		ConferenceRoomDAO crdao=new ConferenceRoomDAO();
		List<ConferenceBean> conferenceList = cdao.selectUnapprovedAll();
		String code=request.getParameter("code");
		int conferenceNum = conferenceList.size();
		int pageNum = (int)Math.ceil((double)conferenceNum/5);
		int currentPageNum = 1;
		if(code!=null&&code.equals("check"))
		{
			request.setAttribute("conferenceList", conferenceList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("conferenceNum", conferenceNum);
			request.setAttribute("currentPageNum", currentPageNum);
			HttpSession session=request.getSession();
			List<ConferenceBean> unapprovedConferenceList = cdao.selectUnapprovedAll();
			int unapprovedConferenceNum = unapprovedConferenceList.size();
			session.setAttribute("unapprovedConferenceNum", unapprovedConferenceNum);
			request.getRequestDispatcher("meeting_apply_check.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("approve"))
		{
			int conference_id = Integer.parseInt(request.getParameter("conference_id"));
			cdao.updateConferenceStatus(conference_id, 1);
			NotificationDAO ndao = new NotificationDAO();
			NotificationBean notification = new NotificationBean();
			long now = System.currentTimeMillis();
			Date notification_date = new Date(now);
			notification.setNotificationDate(notification_date);
			String notification_destination = cdao.selectByConferenceid(conference_id).getConferenceRaisername();
			notification.setNotificationDestination(notification_destination);
			notification.setNotificationDetail("您的会议："+cdao.selectByConferenceid(conference_id).getConferencename()
					+"已经通过审批！");
			HttpSession session = request.getSession();
			String notification_source = (String) session.getAttribute("user_name");
			notification.setNotificationSource(notification_source);
			List<NotificationBean> notificationList = ndao.selectAll();
			notification.setNotificationId(notificationList.size());
			notification.setReadflag(0);
			ndao.insert(notification);
			
			UserConferenceDAO ucdao = new UserConferenceDAO();
			List<UserConferenceBean> ucList = ucdao.selectByConferenceid(conference_id);
			for (int i = 0; i < ucList.size(); i++) 
			{
				if(ucList.get(i).getUsername().equals(notification_destination))
					continue;
				String invitedUser = ucList.get(i).getUsername();
				notification.setNotificationDestination(invitedUser);
				notificationList = ndao.selectAll();
				notification.setNotificationId(notificationList.size());
				notification.setNotificationDate(notification_date);
				notification.setNotificationDetail("您有新的会议："+cdao.selectByConferenceid(conference_id).getConferencename()
						+"请查看！");
				notification.setNotificationSource(notification_source);
				notification.setReadflag(0);
				ndao.insert(notification);
				
			}
			conferenceList = cdao.selectUnapprovedAll();
			conferenceNum = conferenceList.size();
			pageNum = (int)Math.ceil((double)conferenceNum/5);
			request.setAttribute("conferenceList", conferenceList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("conferenceNum", conferenceNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_apply_check.jsp").forward(request, response);
			
		}
		else if(code!=null&&code.equals("cancel"))
		{
			int conference_id = Integer.parseInt(request.getParameter("conference_id"));
			request.setAttribute("conference_id", conference_id);
			request.getRequestDispatcher("conference_cancel.jsp").forward(request, response);
			
		}
		else if(code!=null&&code.equals("cancel_confirm"))
		{
			int conference_id = Integer.parseInt(request.getParameter("conference_id"));
			String conference_cancel_reason =request.getParameter("cancel_reason");
			cdao.updateConferencecancelreason(conference_id, conference_cancel_reason);
			cdao.updateConferenceStatus(conference_id, 3);
			
			NotificationDAO ndao = new NotificationDAO();
			NotificationBean notification = new NotificationBean();
			long now = System.currentTimeMillis();
			Date notification_date = new Date(now);
			notification.setNotificationDate(notification_date);
			String notification_destination = cdao.selectByConferenceid(conference_id).getConferenceRaisername();
			notification.setNotificationDestination(notification_destination);
			notification.setNotificationDetail("您的会议："+cdao.selectByConferenceid(conference_id).getConferencename()
					+"已被取消，原因为："+ conference_cancel_reason +"！");
			HttpSession session = request.getSession();
			String notification_source = (String) session.getAttribute("user_name");
			notification.setNotificationSource(notification_source);
			List<NotificationBean> notificationList = ndao.selectAll();
			notification.setNotificationId(notificationList.size());
			notification.setReadflag(0);
			ndao.insert(notification);
			
			UserConferenceDAO ucdao = new UserConferenceDAO();
			List<UserConferenceBean> ucList = ucdao.selectByConferenceid(conference_id);
			for (int i = 0; i < ucList.size(); i++) 
			{
				if(ucList.get(i).getUsername().equals(notification_destination))
					continue;
				String invitedUser = ucList.get(i).getUsername();
				notification.setNotificationDestination(invitedUser);
				notificationList = ndao.selectAll();
				notification.setNotificationId(notificationList.size());
				notification.setNotificationDate(notification_date);
				notification.setNotificationDetail("您的会议："+cdao.selectByConferenceid(conference_id).getConferencename()
						+"已被取消，原因为："+ conference_cancel_reason +"！");
				notification.setNotificationSource(notification_source);
				notification.setReadflag(0);
				ndao.insert(notification);
				
			}
			
			request.getRequestDispatcher("cancel_success.jsp").forward(request, response);
			
		}
		else if(code!=null&&code.equals("disApprove"))
		{
			int conference_id = Integer.parseInt(request.getParameter("conference_id"));
			cdao.updateConferenceStatus(conference_id, 2);
			conferenceList = cdao.selectUnapprovedAll();
			conferenceNum = conferenceList.size();
			pageNum = (int)Math.ceil((double)conferenceNum/5);
			
			NotificationDAO ndao = new NotificationDAO();
			NotificationBean notification = new NotificationBean();
			long now = System.currentTimeMillis();
			Date notification_date = new Date(now);
			notification.setNotificationDate(notification_date);
			String notification_destination = cdao.selectByConferenceid(conference_id).getConferenceRaisername();
			notification.setNotificationDestination(notification_destination);
			notification.setNotificationDetail("您的会议："+cdao.selectByConferenceid(conference_id).getConferencename()
					+"审核未通过！");
			HttpSession session = request.getSession();
			String notification_source = (String) session.getAttribute("user_name");
			notification.setNotificationSource(notification_source);
			List<NotificationBean> notificationList = ndao.selectAll();
			notification.setNotificationId(notificationList.size());
			notification.setReadflag(0);
			ndao.insert(notification);
			
			request.setAttribute("conferenceList", conferenceList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("conferenceNum", conferenceNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_apply_check.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("choose"))
		{
			conferenceList = cdao.selectApprovedAll();
			request.setAttribute("conferenceList", conferenceList);
			List<ConferenceRoomBean> roomList = crdao.selectAll();
			request.setAttribute("roomList", roomList);
			request.getRequestDispatcher("meeting_room_pick.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("staff"))
		{
			int conference_id = Integer.parseInt(request.getParameter("conference_id"));
			UserConferenceDAO ucdao = new UserConferenceDAO();
			List<UserConferenceBean> userConferenceList = ucdao.selectByConferenceid(conference_id);
			List<String> userList = new ArrayList<String>();
			for(int i=0;i<userConferenceList.size();i++)
			{
				userList.add(userConferenceList.get(i).getUsername());
			}
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("meeting_room_staff_detail.jsp").forward(request, response);
		}
		else if(code!=null & code.equals("lastPage"))
		{
			currentPageNum = pageNum;
		}
		else if(code!=null & code.equals("firstPage"))
		{
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("conferenceNum", conferenceNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("department_detail.jsp").forward(request, response);
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
		request.setAttribute("conferenceNum", conferenceNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.setAttribute("conferenceList", conferenceList);
		request.getRequestDispatcher("meeting_apply_check.jsp").forward(request, response);
		
	}
}

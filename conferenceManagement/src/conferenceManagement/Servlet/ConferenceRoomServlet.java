package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.ConferenceDAO;
import conferenceManagement.DAO.ConferenceRoomDAO;
import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.ConferenceRoomBean;
import conferenceManagement.Entity.DepartmentBean;
import conferenceManagement.Service.Service;

/**
 * Servlet implementation class ConferenceRoomServlet
 */

public class ConferenceRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
		String code=request.getParameter("code");
		int currentPageNum = 1;
		int pageNum = 0;
		request.setAttribute("flag", 0);
		ConferenceRoomDAO crdao = new ConferenceRoomDAO();
		List<ConferenceRoomBean> roomList = crdao.selectAll();
		int room_num = roomList.size();
		pageNum = (int)Math.ceil((double)room_num/5);
		if(code!=null&&code.equals("update"))
		{
			String conference_name = request.getParameter("conf_name");
			int conference_room_id = Integer.parseInt(request.getParameter("room_id"));
			ConferenceDAO cdao = new ConferenceDAO();
			cdao.updateRoomid(conference_name, conference_room_id);
			crdao = new ConferenceRoomDAO();
			crdao.updateStatus(conference_room_id, 2);
			request.getRequestDispatcher("meeting_room_pick.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("display"))
		{
			currentPageNum = 1;
			
			request.setAttribute("roomList", roomList);
			request.setAttribute("roomNum", room_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_room_management.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("detail"))
		{
			int room_id = Integer.parseInt(request.getParameter("room_id"));
			ConferenceDAO cdao = new ConferenceDAO();
			List<ConferenceBean> conferenceList = cdao.selectByRoomid(room_id);
			if(conferenceList.size() == 0)
			{
				request.setAttribute("hasConference", 0);
			}
			else
			{
				request.setAttribute("hasConference", 1);
				request.setAttribute("conference_id", conferenceList.get(0).getConferenceid());
				request.setAttribute("conference_name", conferenceList.get(0).getConferencename());
				request.setAttribute("conference_raiser_name",  conferenceList.get(0).getConferenceRaisername());
				request.setAttribute("conference_date",  conferenceList.get(0).getConferenceDate());
				request.setAttribute("start_time",  conferenceList.get(0).getConferenceStarttime());
				request.setAttribute("end_time",  conferenceList.get(0).getConferenceEndtime());
			}
			request.setAttribute("room_id", room_id);
			
			request.getRequestDispatcher("meeting_room_detail.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("delete"))
		{
			int conference_room_id = Integer.parseInt(request.getParameter("conference_room_id"));
			Service service = new Service();
			service.deleteConferenceRoom(conference_room_id);
			request.setAttribute("roomNum", room_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_room_management.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("modify"))
		{
			int conference_room_id = Integer.parseInt(request.getParameter("room_id"));
			int room_capacity = Integer.parseInt(request.getParameter("room_capacity"));
			int room_status = Integer.parseInt(request.getParameter("room_status"));
			crdao = new ConferenceRoomDAO();
			crdao.updateStatus(conference_room_id, room_status);
			crdao.updateCapacity(conference_room_id, room_capacity);
			roomList = crdao.selectAll();
			request.setAttribute("roomList", roomList);
			request.setAttribute("roomNum", room_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_room_management.jsp").forward(request, response);
		}
		else if(code!=null&&code.equals("add"))
		{
			int conference_room_id = Integer.parseInt(request.getParameter("room_id"));
			int room_capacity = Integer.parseInt(request.getParameter("room_capacity"));
			String room_name = request.getParameter("room_name");
			crdao = new ConferenceRoomDAO();
			ConferenceRoomBean bean = new ConferenceRoomBean();
			bean.setRoomcapacity(room_capacity);
			bean.setRoomid(conference_room_id);
			bean.setRoomname(room_name);
			bean.setRoomstatus(0);
			bean.setRoomremark("");
			crdao.insert(bean);
			roomList = crdao.selectAll();
			request.setAttribute("roomList", roomList);
			request.setAttribute("roomNum", room_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_room_management.jsp").forward(request, response);
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
		request.setAttribute("roomList", roomList);
		request.setAttribute("roomNum", room_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.getRequestDispatcher("meeting_room_management.jsp").forward(request, response);
		
	}
}

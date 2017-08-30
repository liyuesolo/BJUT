package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.ConferenceRoomDAO;
import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.Entity.ConferenceRoomBean;
import conferenceManagement.Entity.DepartmentBean;

/**
 * Servlet implementation class SearchRoomServlet
 */
@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {
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
		request.setAttribute("flag", 1);
		String room_id_str = request.getParameter("room_id");
		ConferenceRoomDAO dao = new ConferenceRoomDAO();
		List<ConferenceRoomBean> roomList = dao.selectAll();
		if(!room_id_str.equals(""))
		{
			int room_id = Integer.parseInt(room_id_str);
			for(int i=0;i<roomList.size();i++)
			{
				if(roomList.get(i).getRoomid()!=(room_id))
				{
					roomList.remove(i);
					i--;
				}
			}
			request.setAttribute("room_id", room_id);
		}
		else
		{
			request.setAttribute("room_id", room_id_str);
		}
			
		
		int room_status = Integer.parseInt(request.getParameter("room_status"));
		request.setAttribute("room_status", room_status);
		if(room_status!=2)
		{
			for(int i=0;i<roomList.size();i++)
			{
				if(roomList.get(i).getRoomstatus()!=(room_status))
				{
					roomList.remove(i);
					i--;
				}
			}
		}
		String code = request.getParameter("code");
		
		request.setAttribute("currentRoomStatus", room_status);
		
		
		
		int roomNum = roomList.size();
		int pageNum = (int)Math.ceil((double)roomNum/5);
			
		if(code!=null & code.equals("lastPage"))
		{
			currentPageNum = pageNum;
		}
		else if(code!=null & code.equals("firstPage"))
		{
			request.setAttribute("roomList", roomList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("roomNum", roomNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_room_management.jsp").forward(request, response);
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
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("roomNum", roomNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.getRequestDispatcher("meeting_room_management.jsp").forward(request, response);

		
	}

}

package conferenceManagement.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conferenceManagement.DAO.ConferenceDAO;
import conferenceManagement.DAO.ConferenceRoomDAO;
import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.DAO.UserConferenceDAO;
import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.ConferenceRoomBean;

/**
 * Servlet implementation class SearchConferenceServlet
 */
@WebServlet("/SearchConferenceServlet")
public class SearchConferenceServlet extends HttpServlet {
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
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");
		int conference_status = Integer.parseInt(request.getParameter("conference_status"));
		ConferenceDAO cdao = new ConferenceDAO();
		List<ConferenceBean> conferenceList = cdao.selectAll();
		UserConferenceDAO ucdao = new UserConferenceDAO();
		List<Integer> userConferenceList = ucdao.selectUserConference(user_name);
		request.setAttribute("userConferenceList", userConferenceList);
		request.setAttribute("conference_status", conference_status);
		
		if(conference_status == 1)
		{
			for(int i=0;i<conferenceList.size();i++)
			{
				if(conferenceList.get(i).getConferencestatus() != 0)
				{
					conferenceList.remove(i);
					i--;
				}
			}
		}
		else if(conference_status == 2)
		{
			for(int i=0;i<conferenceList.size();i++)
			{
				if(conferenceList.get(i).getConferencestatus() != 2)
				{
					conferenceList.remove(i);
					i--;
				}
			}
		}
		else if(conference_status == 3)
		{
			for(int i=0;i<conferenceList.size();i++)
			{
				if(conferenceList.get(i).getConferenceroomid() != 0)
				{
					conferenceList.remove(i);
					i--;
				}
				if(conferenceList.get(i).getConferencestatus() == 0)
				{
					conferenceList.remove(i);
					i--;
				}
				
			}
		}
		else if(conference_status == 4)
		{
			for(int i=0;i<conferenceList.size();i++)
			{
				if(conferenceList.get(i).getConferenceroomid() == 0)
				{
					conferenceList.remove(i);
					i--;
				}
			}
		}

		
		int conferenceNum = conferenceList.size();
		int pageNum = (int)Math.ceil((double)conferenceNum/5);
		if(code!=null & code.equals("display"))
		{
			request.setAttribute("privilege", (int)session.getAttribute("privilege"));
			request.setAttribute("conferenceList", conferenceList);
			request.setAttribute("conferenceNum", conferenceNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPageNum", currentPageNum);
			request.getRequestDispatcher("meeting_info_list.jsp").forward(request, response);
			
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
		request.setAttribute("privilege", (int)session.getAttribute("privilege"));
		request.setAttribute("conferenceList", conferenceList);
		request.setAttribute("conferenceNum", conferenceNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPageNum", currentPageNum);
		request.getRequestDispatcher("meeting_info_list.jsp").forward(request, response);
	}
}

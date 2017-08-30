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
import conferenceManagement.DAO.UserConferenceDAO;
import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.UserBean;


/**
 * Servlet implementation class ConferenceInfoServlet
 */
@WebServlet("/ConferenceInfoServlet")
public class ConferenceInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setAttribute("flag", 0);
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");
		int currentPageNum = 1;
		ConferenceDAO cdao = new ConferenceDAO();
		List<ConferenceBean> conferenceList = cdao.selectAll();
		int conferenceNum = conferenceList.size();
		int pageNum = (int)Math.ceil((double)conferenceNum/5);
		if(code!=null & code.equals("display"))
		{
			
			UserConferenceDAO ucdao = new UserConferenceDAO();
			List<Integer> userConferenceList = ucdao.selectUserConference(user_name);
			for(int i=0;i<conferenceList.size();i++)
			{
				if(conferenceList.get(i).getConferencestatus()==3)
				{
					conferenceList.remove(i);
					i--;
				}
			}
			conferenceNum = conferenceList.size();
			request.setAttribute("userConferenceList", userConferenceList);
			
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

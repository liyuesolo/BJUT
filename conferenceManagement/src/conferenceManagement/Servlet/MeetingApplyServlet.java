package conferenceManagement.Servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conferenceManagement.DAO.ConferenceDAO;
import conferenceManagement.DAO.UserConferenceDAO;
import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Service.Service;

/**
 * Servlet implementation class MeetingApplyServlet
 */
@WebServlet("/MeetingApplyServlet")
public class MeetingApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        this.doPost(request, response);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException 
    {  
    	request.setCharacterEncoding("UTF-8");
    	String conference_name = request.getParameter("conference_name");
    	String conference_raiser_name = request.getParameter("conference_raiser_name");    	
    	Date conference_date = java.sql.Date.valueOf(request.getParameter("date"));
    	Time start_time = java.sql.Time.valueOf(request.getParameter("start_time"));
    	Time end_time = java.sql.Time.valueOf(request.getParameter("end_time"));
    	int expected_num = Integer.parseInt(request.getParameter("expected_num"));
    	String conference_info = request.getParameter("conference_info");
    	String[] invited = request.getParameterValues("invited");
    	UserConferenceDAO ucdao = new UserConferenceDAO();
    	ConferenceDAO dao = new ConferenceDAO();
    	List<ConferenceBean> conferenceList = dao.selectAll();
    	for(int i = 0; i<invited.length; i++)
    	{
    		ucdao.insert(conferenceList.size(), invited[i]);
    	}
    	ConferenceBean bean = new ConferenceBean();
    	bean.setConferenceid(conferenceList.size());
    	bean.setConferencename(conference_name);
    	bean.setConferenceRaisername(conference_raiser_name);
    	bean.setConferenceroomid(0);
    	bean.setConferenceDate(conference_date);
    	bean.setConferenceStarttime(start_time);
    	bean.setConferenceEndtime(end_time);
    	bean.setExpectednum(expected_num);
    	bean.setConferenceinfo(conference_info);
    	bean.setConferencestatus(0);
    	bean.setConferencecancelreason("");
    	dao.insert(bean);
    	request.getRequestDispatcher("meeting_apply_success.jsp").forward(request, response);
		 
    }  

}

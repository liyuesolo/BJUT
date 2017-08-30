package conferenceManagement.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.DepartmentBean;
import conferenceManagement.Util.ConnectionManager;

public class ConferenceDAO 
{
	private Connection conn;


	public void insert(ConferenceBean bean)
	{
		  conn = ConnectionManager.getConnection();
		  String sql="insert into conference"
				  +
					"(conference_id,conference_name,conference_room_id,conference_raiser_name,conference_info,"
				  +"start_time,end_time,conference_date,expected_num,conference_status,conference_cancel_reason)"
				  + " values(?,?,?,?,?,?,?,?,?,?,?)";
		  try 
		  {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bean.getConferenceid());
			pstmt.setString(2,bean.getConferencename());
			pstmt.setInt(3,bean.getConferenceroomid() );
			pstmt.setString(4,bean.getConferenceRaisername());
			pstmt.setString(5,bean.getConferenceinfo());
			pstmt.setTime(6,bean.getConferenceStarttime());
			pstmt.setTime(7,bean.getConferenceEndtime());			
			pstmt.setDate(8,bean.getConferenceDate());
			pstmt.setInt(9,bean.getExpectednum());
			pstmt.setInt(10,0);
			pstmt.setString(11,bean.getConferencecancelreason());
			pstmt.executeUpdate();	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
	}
	
	public List<ConferenceBean> selectAll()
	{
		conn = ConnectionManager.getConnection();
		List<ConferenceBean> conferenceList = new ArrayList<ConferenceBean>();
		 try 
		 {
				Statement st=null;
				String sql="select * from conference";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				ConferenceBean conference;
				while(rs.next())
				{
					conference = new ConferenceBean();
					conference.setConferenceid(rs.getInt("conference_id"));
					conference.setConferencename(rs.getString("conference_name"));
					conference.setConferenceRaisername(rs.getString("conference_raiser_name"));
					conference.setConferenceDate(rs.getDate("conference_date"));
					conference.setConferenceStarttime(rs.getTime("start_time"));
					conference.setConferenceEndtime(rs.getTime("end_time"));
					conference.setConferenceinfo(rs.getString("conference_info"));
					conference.setConferenceroomid(rs.getInt("conference_room_id"));
					conference.setConferencestatus(rs.getInt("conference_status"));
					conference.setExpectednum(rs.getInt("expected_num"));
					conference.setConferencecancelreason(rs.getString("conference_cancel_reason"));
					conferenceList.add(conference);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		return conferenceList;
	}
	public List<ConferenceBean> selectByRoomid(int room_id)
	{
		conn = ConnectionManager.getConnection();
		List<ConferenceBean> conferenceList = new ArrayList<ConferenceBean>();
		 try 
		 {
				Statement st=null;
				String sql="select * from conference where conference_room_id='"+room_id+"'";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				ConferenceBean conference;
				while(rs.next())
				{
					conference = new ConferenceBean();
					conference.setConferenceid(rs.getInt("conference_id"));
					conference.setConferencename(rs.getString("conference_name"));
					conference.setConferenceRaisername(rs.getString("conference_raiser_name"));
					conference.setConferenceDate(rs.getDate("conference_date"));
					conference.setConferenceStarttime(rs.getTime("start_time"));
					conference.setConferenceEndtime(rs.getTime("end_time"));
					conference.setConferenceinfo(rs.getString("conference_info"));
					conference.setConferenceroomid(rs.getInt("conference_room_id"));
					conference.setConferencestatus(rs.getInt("conference_status"));
					conference.setExpectednum(rs.getInt("expected_num"));
					conference.setConferencecancelreason(rs.getString("conference_cancel_reason"));
					conferenceList.add(conference);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		return conferenceList;
	}
	
	public List<ConferenceBean> selectUnapprovedAll()
	{
		conn = ConnectionManager.getConnection();
		List<ConferenceBean> conferenceList = new ArrayList<ConferenceBean>();
		 try 
		 {
				Statement st=null;
				String sql="select * from conference where conference_status='0'";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				ConferenceBean conference;
				while(rs.next())
				{
					conference = new ConferenceBean();
					conference.setConferenceid(rs.getInt("conference_id"));
					conference.setConferencename(rs.getString("conference_name"));
					conference.setConferenceRaisername(rs.getString("conference_raiser_name"));
					conference.setConferenceDate(rs.getDate("conference_date"));
					conference.setConferenceStarttime(rs.getTime("start_time"));
					conference.setConferenceEndtime(rs.getTime("end_time"));
					conference.setConferenceinfo(rs.getString("conference_info"));
					conference.setConferenceroomid(rs.getInt("conference_room_id"));
					conference.setExpectednum(rs.getInt("expected_num"));
					conference.setConferencestatus(rs.getInt("conference_status"));
					conference.setConferencecancelreason(rs.getString("conference_cancel_reason"));
					conferenceList.add(conference);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		return conferenceList;
	}
	
	public List<ConferenceBean> selectApprovedAll()
	{
		conn = ConnectionManager.getConnection();
		List<ConferenceBean> conferenceList = new ArrayList<ConferenceBean>();
		 try 
		 {
				Statement st=null;
				String sql="select * from conference where conference_status='1' and conference_room_id=0";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				ConferenceBean conference;
				while(rs.next())
				{
					conference = new ConferenceBean();
					conference.setConferenceid(rs.getInt("conference_id"));
					conference.setConferencename(rs.getString("conference_name"));
					conference.setConferenceRaisername(rs.getString("conference_raiser_name"));
					conference.setConferenceDate(rs.getDate("conference_date"));
					conference.setConferenceStarttime(rs.getTime("start_time"));
					conference.setConferenceEndtime(rs.getTime("end_time"));
					conference.setConferenceinfo(rs.getString("conference_info"));
					conference.setConferenceroomid(rs.getInt("conference_room_id"));
					conference.setExpectednum(rs.getInt("expected_num"));
					conference.setConferencestatus(rs.getInt("conference_status"));
					conference.setConferencecancelreason(rs.getString("conference_cancel_reason"));
					conferenceList.add(conference);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		return conferenceList;
	}
	
	public ConferenceBean selectByConferenceid(int conference_id)
	{
		conn = ConnectionManager.getConnection();
		ConferenceBean conference = null;
		 try 
		 {
				Statement st=null;
				String sql="select * from conference where conference_id='"+conference_id+"'";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				while(rs.next())
				{
					conference = new ConferenceBean();
					conference.setConferenceid(rs.getInt("conference_id"));
					conference.setConferencename(rs.getString("conference_name"));
					conference.setConferenceRaisername(rs.getString("conference_raiser_name"));
					conference.setConferenceDate(rs.getDate("conference_date"));
					conference.setConferenceStarttime(rs.getTime("start_time"));
					conference.setConferenceEndtime(rs.getTime("end_time"));
					conference.setConferenceinfo(rs.getString("conference_info"));
					conference.setConferenceroomid(rs.getInt("conference_room_id"));
					conference.setExpectednum(rs.getInt("expected_num"));
					conference.setConferencestatus(rs.getInt("conference_status"));
					conference.setConferencecancelreason(rs.getString("conference_cancel_reason"));
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		return conference;
	}
	
	public void updateId(String conference_name, int conference_id)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set conference_id='"+conference_id+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateConferenceraisername(String conference_name, String conference_raiser_name)
	  {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set conference_raiser_name='"+conference_raiser_name+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateConferencedate(String conference_name, Date conference_date)
	  {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set conference_date='"+conference_date+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateStarttime(String conference_name, Date start_time)
	  {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set start_time='"+start_time+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateEndtime(String conference_name, Date end_time)
	  {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set end_time'"+end_time+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateInfo(String conference_name, String conference_info)
	  {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set conference_info='"+conference_info+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateRoomid(String conference_name, int conference_room_id)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set conference_room_id='"+conference_room_id+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateConferenceStatus(int conference_id, int conference_status)
	    {
			  conn = ConnectionManager.getConnection();
			  String sql="update conference set conference_status='"+conference_status+"'where conference_id="+conference_id;
			  try {		
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}	  
		  }
	  
	  public void updateExpectednum(String conference_name, int expected_num)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set expected_num='"+expected_num+"'where conference_name='"+conference_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	  
	  public void updateConferencecancelreason(int conference_id, String conference_cancel_reason)
	  {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference set conference_cancel_reason='"+conference_cancel_reason+"'where conference_id="+conference_id;
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	
	
}
package conferenceManagement.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conferenceManagement.Entity.ConferenceBean;
import conferenceManagement.Entity.UserBean;
import conferenceManagement.Entity.UserConferenceBean;
import conferenceManagement.Util.ConnectionManager;

public class UserConferenceDAO 
{
	private Connection conn;
	
	public void insert(int conference_id, String user_name) 
	{
		conn = ConnectionManager.getConnection();
		String sql = "insert into conference_user_link (conference_id,user_name) values(?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, conference_id);
			pstmt.setString(2, user_name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
	}
	public List<UserConferenceBean> selectByConferenceid(int conference_id)
	{
		conn = ConnectionManager.getConnection();
		List<UserConferenceBean> userConferenceList = new ArrayList<UserConferenceBean>();
		 try 
		 {
				Statement st=null;
				String sql="select * from conference_user_link where conference_id="+conference_id;
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				UserConferenceBean user_conference;
				while(rs.next())
				{
					user_conference = new UserConferenceBean();
					user_conference.setConferenceid(rs.getInt("conference_id"));
					user_conference.setUsername(rs.getString("user_name"));
					userConferenceList.add(user_conference);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		return userConferenceList;
	}
	
	public List<Integer> selectUserConference(String user_name)
	{
		conn = ConnectionManager.getConnection();
		List<Integer> userConferenceList = new ArrayList<Integer>();
		 try 
		 {
				Statement st=null;
				String sql="select * from conference_user_link where user_name='"+user_name+"'";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				while(rs.next())
				{
					userConferenceList.add(rs.getInt("conference_id"));
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		return userConferenceList;
	}
}

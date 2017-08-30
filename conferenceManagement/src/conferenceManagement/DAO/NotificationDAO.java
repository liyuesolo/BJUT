package conferenceManagement.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conferenceManagement.Entity.NotificationBean;
import conferenceManagement.Util.ConnectionManager;

public class NotificationDAO 
{
	 private Connection conn;	
	 
	 public List<NotificationBean> selectAll()
	 {
		 conn = ConnectionManager.getConnection();
		 List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
		 try {
				Statement st=null;
				String sql="select * from notification where read_flag=0";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				NotificationBean notification;
				while(rs.next()){
					notification=new NotificationBean();
					notification.setNotificationDate(rs.getDate("notification_date"));
					notification.setNotificationDetail(rs.getString("notification_detail"));
					notification.setNotificationId(rs.getInt("notification_id"));
					notification.setNotificationSource(rs.getString("notification_source"));
					notification.setNotificationDestination(rs.getString("notification_destination"));
					notification.setReadflag(rs.getInt("read_flag"));
					notificationList.add(notification);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		 return notificationList;
	 }
	 
	 public List<NotificationBean> selectRead()
	 {
		 conn = ConnectionManager.getConnection();
		 List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
		 try {
				Statement st=null;
				String sql="select * from notification where read_flag=1";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				NotificationBean notification;
				while(rs.next()){
					notification=new NotificationBean();
					notification.setNotificationDate(rs.getDate("notification_date"));
					notification.setNotificationDetail(rs.getString("notification_detail"));
					notification.setNotificationId(rs.getInt("notification_id"));
					notification.setNotificationSource(rs.getString("notification_source"));
					notification.setNotificationDestination(rs.getString("notification_destination"));
					notification.setReadflag(rs.getInt("read_flag"));
					notificationList.add(notification);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		 return notificationList;
	 }
	 
	 
		public void insert(NotificationBean notification) 
		{
			conn = ConnectionManager.getConnection();
			String sql = "insert into notification (notification_id,notification_detail,notification_source,notification_date,notification_destination,read_flag) values(?,?,?,?,?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, notification.getNotificationId());
				pstmt.setString(2, notification.getNotificationDetail());
				pstmt.setString(3, notification.getNotificationSource());
				pstmt.setDate(4, notification.getNotificationDate());
				pstmt.setString(5, notification.getNotificationDestination());
				pstmt.setInt(6, notification.getReadflag());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ConnectionManager.closeConnection();
			}
		}

		public void delete(int notification_id) {
			conn = ConnectionManager.getConnection();
			String sql = "delete from notification where notification_id=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, notification_id);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ConnectionManager.closeConnection();
			}
		}
		public List<NotificationBean> selectByUsername(String user_name)
		 {
			 conn = ConnectionManager.getConnection();
			 List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
			 try {
					Statement st=null;
					String sql="select * from notification where notification_destination='"+user_name+"'"
							+ "and read_flag=0";
			 		st = conn.createStatement();
					ResultSet rs =st.executeQuery(sql);
					NotificationBean notification;
					while(rs.next()){
						notification=new NotificationBean();
						notification.setNotificationDate(rs.getDate("notification_date"));
						notification.setNotificationDetail(rs.getString("notification_detail"));
						notification.setNotificationId(rs.getInt("notification_id"));
						notification.setNotificationSource(rs.getString("notification_source"));
						notification.setNotificationDestination(rs.getString("notification_destination"));
						notification.setReadflag(rs.getInt("read_flag"));
						notificationList.add(notification);
					}
				 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					ConnectionManager.closeConnection();
				}
			 return notificationList;
		 }

		public List<NotificationBean> selectReadByUsername(String user_name) 
		{
			 conn = ConnectionManager.getConnection();
			 List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
			 try {
					Statement st=null;
					String sql="select * from notification where notification_destination='"+user_name+"'"
							+ "and read_flag=1";
			 		st = conn.createStatement();
					ResultSet rs =st.executeQuery(sql);
					NotificationBean notification;
					while(rs.next()){
						notification=new NotificationBean();
						notification.setNotificationDate(rs.getDate("notification_date"));
						notification.setNotificationDetail(rs.getString("notification_detail"));
						notification.setNotificationId(rs.getInt("notification_id"));
						notification.setNotificationSource(rs.getString("notification_source"));
						notification.setNotificationDestination(rs.getString("notification_destination"));
						notification.setReadflag(rs.getInt("read_flag"));
						notificationList.add(notification);
					}
				 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					ConnectionManager.closeConnection();
				}
			 return notificationList;
		}
		public void updateReadFlag(int notification_id,int read_flag)
	    {
			  conn = ConnectionManager.getConnection();
			  String sql="update notification set read_flag="+read_flag+" where notification_id="+notification_id;
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

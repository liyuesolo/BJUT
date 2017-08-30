package conferenceManagement.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql. * ;

import conferenceManagement.Entity.ConferenceRoomBean;
import conferenceManagement.Util.ConnectionManager;

public class ConferenceRoomDAO 
{
	private Connection conn;
	
	public List<ConferenceRoomBean> selectAll()
	 {
		 conn = ConnectionManager.getConnection();
		 List<ConferenceRoomBean> conferenceroomList = new ArrayList<ConferenceRoomBean>();
		 try {
				Statement st=null;
				String sql="select * from conference_room";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				ConferenceRoomBean conferenceroom;
				while(rs.next()){
					conferenceroom=new ConferenceRoomBean();
					conferenceroom.setRoomname(rs.getString("room_name"));
					conferenceroom.setRoomid(rs.getInt("room_id"));
					conferenceroom.setRoomcapacity(rs.getInt("room_capacity"));
					conferenceroom.setRoomstatus(rs.getInt("room_status"));
					conferenceroom.setRoomremark(rs.getString("room_remark"));
					conferenceroomList.add(conferenceroom);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		 return conferenceroomList;
	 }


	public void insert(ConferenceRoomBean bean){
		conn = ConnectionManager.getConnection();
		String sql="insert into conference_room"
				+
				"(room_id,room_name,room_capacity,room_status,room_remark)" +
				" values(?,?,?,?,?)";
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bean.getRoomid());
			pstmt.setString(2,bean.getRoomname());
			pstmt.setInt(3,bean.getRoomcapacity() );
			pstmt.setInt(4,0);
			pstmt.setString(5,bean.getRoomremark());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
	}
	public void updateCapacity(int room_id,int room_capacity)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference_room set room_capacity='"+room_capacity+"'where room_id='"+room_id+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	
	public void updateStatus(int room_id,int room_status)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference_room set room_status='"+room_status+"'where room_id='"+room_id+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	
	public void updateRemark(int room_id,String room_remark)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference_room set room_remark='"+room_remark+"'where room_id='"+room_id+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	
	public void updateRoomName(int room_id,String room_name)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update conference_room set room_name='"+room_name+"'where room_id='"+room_id+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
	
	public void delete(int conference_room_id) {
		conn = ConnectionManager.getConnection();
		String sql = "delete from conference_room where room_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, conference_room_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
	}

}
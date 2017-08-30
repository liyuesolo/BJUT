package conferenceManagement.DAO;
import conferenceManagement.Entity.UserBean;
import conferenceManagement.Util.ConnectionManager;

import java.sql. * ;
import java.util.ArrayList;
import java.util.List;
public class UserDAO 
{
	private Connection conn = ConnectionManager.getConnection();

	 public UserBean selectByUsername(String username)
	 {
		 conn= ConnectionManager.getConnection();
		 UserBean user = null;	
		 try 
		 {
			PreparedStatement st=null;
			String sql="select * from user where user_name='"+username+"'";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
				user = new UserBean();
				user.setUserID(rs.getString("user_id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getInt("gender"));
				user.setAge(rs.getInt("age"));
				user.setPhone(rs.getString("phone"));
				user.setMail(rs.getString("mail"));
				user.setUserStatus(rs.getInt("user_status"));
				user.setDepartmentname(rs.getString("department_name"));
				user.setPrivilege(rs.getInt("privilege"));
				user.setDelflag(rs.getInt("del_flag"));
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
		 return user;
	 }
	 
	 public UserBean selectByNamePwd(String username,String pwd)
	 {
		 UserBean user = null;
		 try 
		 { 
			 PreparedStatement st=null;
			String sql="select * from user where user_name='"+username+"' and  password='"+pwd+"'";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
				user = new UserBean();
				user.setUserID(rs.getString("user_id"));
				user.setUsername(rs.getString("user_name"));
				user.setGender(rs.getInt("gender"));
				user.setAge(rs.getInt("age"));
				user.setPhone(rs.getString("phone"));
				user.setMail(rs.getString("mail"));
				user.setUserStatus(rs.getInt("user_status"));
				user.setDepartmentname(rs.getString("department_name"));
				user.setPrivilege(rs.getInt("privilege"));
				user.setDelflag(rs.getInt("del_flag"));
				user.setPrivilege(rs.getInt("privilege"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("user_name"));
			}
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
		 return user;
	 }
	
    public void insert(UserBean bean){
		  conn = ConnectionManager.getConnection();
		  String sql="insert into user"
				  +
					"(user_id,password,user_name,gender,age,phone,mail,department_name,user_status,privilege,del_flag)" +
					" values(?,?,?,?,?,?,?,?,?,?,?)";
		  try 
		  {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bean.getUserid());
			pstmt.setString(2,bean.getPassword());
			pstmt.setString(3,bean.getUsername() );
			pstmt.setInt(4,bean.getGender());
			pstmt.setInt(5,bean.getAge());
			pstmt.setString(6,bean.getPhone());
			pstmt.setString(7,bean.getMail());			
			pstmt.setString(8,bean.getDepartmentname());
			pstmt.setInt(9,0);
			pstmt.setInt(10,2);
			pstmt.setInt(11,0);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public List<UserBean> selectAllUnapprovedUser() 
    {
		conn = ConnectionManager.getConnection();
		List<UserBean> userlist = new ArrayList<UserBean>();
		UserBean user = null;
		try {
		PreparedStatement psd = null;
		String sql="select * from user where privilege='2' and user_status='0'";
		psd = conn.prepareStatement(sql);
		ResultSet rs = psd.executeQuery(sql);
		while(rs.next()){
			user = new UserBean();
			user.setUserID(rs.getString("user_id"));
			user.setUsername(rs.getString("user_name"));
			user.setAge(rs.getInt("age"));
			user.setPhone(rs.getString("phone"));
			user.setMail(rs.getString("mail"));
			user.setUserStatus(rs.getInt("user_status"));
			user.setDepartmentname(rs.getString("department_name"));
			user.setPassword(rs.getString("password"));
			user.setGender(rs.getInt("gender"));
			user.setDelflag(rs.getInt("del_flag"));
			user.setPrivilege(rs.getInt("privilege"));
			userlist.add(user);
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
		
		return userlist;

	}
    public List<UserBean> selectAllUser() 
    {
		conn = ConnectionManager.getConnection();
		List<UserBean> userlist = new ArrayList<UserBean>();
		UserBean user = null;
		try {
		PreparedStatement psd = null;
		String sql="select * from user";
		psd = conn.prepareStatement(sql);
		ResultSet rs = psd.executeQuery(sql);
		while(rs.next()){
			user = new UserBean();
			user.setUserID(rs.getString("user_id"));
			user.setUsername(rs.getString("user_name"));
			user.setAge(rs.getInt("age"));
			user.setPhone(rs.getString("phone"));
			user.setMail(rs.getString("mail"));
			user.setUserStatus(rs.getInt("user_status"));
			user.setDepartmentname(rs.getString("department_name"));
			user.setPassword(rs.getString("password"));
			user.setGender(rs.getInt("gender"));
			user.setDelflag(rs.getInt("del_flag"));
			user.setPrivilege(rs.getInt("privilege"));
			userlist.add(user);
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
		
		return userlist;

	}
    
    
    public List<UserBean> selectAllUserInDepartment(String department_name) 
    {
		conn = ConnectionManager.getConnection();
		List<UserBean> userlist = new ArrayList<UserBean>();
		UserBean user = null;
		try {
		PreparedStatement psd = null;
			
		String sql="select * from user where department_name='"+department_name+"'";
		psd = conn.prepareStatement(sql);
		ResultSet rs = psd.executeQuery(sql);
		while(rs.next()){
			user = new UserBean();
			user.setUserID(rs.getString("user_id"));
			user.setUsername(rs.getString("user_name"));
			user.setAge(rs.getInt("age"));
			user.setPhone(rs.getString("phone"));
			user.setMail(rs.getString("mail"));
			user.setUserStatus(rs.getInt("user_status"));
			user.setDepartmentname(rs.getString("department_name"));
			user.setPassword(rs.getString("password"));
			user.setGender(rs.getInt("gender"));
			user.setDelflag(rs.getInt("del_flag"));
			user.setPrivilege(rs.getInt("privilege"));
			userlist.add(user);
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
		
		return userlist;

	}
   
    public void updateStatus(String user_name,int user_status)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set user_status='"+user_status+"'where user_name='"+user_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public void updatePrivilege(String user_name,int privilege)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set privilege='"+privilege+"'where user_name='"+user_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public void updateDelflag(String user_name,int del_flag)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set del_flag='"+del_flag+"'where user_name='"+user_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public void updateDepartment(String user_name,String department_name)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set department_name='"+department_name+"'where user_name='"+user_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public void updateMail(String user_name,String mail)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set mail='"+mail+"'where user_name='"+user_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public void updatePhone(String user_name,String phone)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set phone='"+phone+"'where user_name='"+user_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public void updatePassword(String user_name,String password)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set password='"+password+"'where user_name='"+user_name+"'";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}	  
	  }
    
    public void updateAge(String user_name,int age)
    {
		  conn = ConnectionManager.getConnection();
		  String sql="update user set age='"+age+"'where user_name='"+user_name+"'";
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

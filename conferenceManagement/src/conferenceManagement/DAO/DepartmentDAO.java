package conferenceManagement.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conferenceManagement.Entity.DepartmentBean;
import conferenceManagement.Entity.UserBean;
import conferenceManagement.Util.ConnectionManager;

public class DepartmentDAO {
//	 DAO类关联连接工厂类
	 private Connection conn;	
	 
//	 查询所有部门信息，返回到集合中
	 public List<DepartmentBean> selectAll()
	 {
		 conn = ConnectionManager.getConnection();
		 List<DepartmentBean> departmentsList = new ArrayList<DepartmentBean>();
		 try {
				Statement st=null;
				String sql="select * from department";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				DepartmentBean department;
				while(rs.next()){
					department=new DepartmentBean();
					department.setDepartmentname(rs.getString("department_name"));
					department.setStaffNumber(rs.getInt("staff_num"));
					departmentsList.add(department);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}
		 return departmentsList;
	 }
	 
	 public DepartmentBean selectByDepartmentname(String department_name)
	 {
		 conn= ConnectionManager.getConnection();
		 DepartmentBean department = null;	
		 try 
		 {
			PreparedStatement st=null;
			String sql="select * from department where department_name='"+department_name+"'";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
				department = new DepartmentBean();
				department.setStaffNumber(rs.getInt("staff_num"));
				department.setDepartmentname(rs.getString("department_name"));
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection();
		}
		 return department;
	 }
	 
		public void insert(String departmentname) 
		{
			conn = ConnectionManager.getConnection();
			String sql = "insert into department (department_name,staff_num) values(?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, departmentname);
				pstmt.setInt(2, 0);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ConnectionManager.closeConnection();
			}
		}

		public void delete(String departmentname) {
			conn = ConnectionManager.getConnection();
			String sql = "delete from department where department_name=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, departmentname);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ConnectionManager.closeConnection();
			}
		}
		
		public void updateDepartmentname(String department_name, String new_department_name)
		  {
			  conn = ConnectionManager.getConnection();
			  String sql="update department set department_name='"+new_department_name+"'where department_name='"+department_name+"'";
			  try {		
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ConnectionManager.closeConnection();
			}	  
		  }
		
		public void updateStaffNum(String department_name, int staff_num)
		  {
			  conn = ConnectionManager.getConnection();
			  String sql="update department set staff_num='"+staff_num+"'where department_name='"+department_name+"'";
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

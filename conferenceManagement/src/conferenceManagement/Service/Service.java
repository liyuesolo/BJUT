package conferenceManagement.Service;

import java.util.List;

import conferenceManagement.DAO.ConferenceRoomDAO;
import conferenceManagement.DAO.DepartmentDAO;
import conferenceManagement.DAO.UserDAO;
import conferenceManagement.Entity.UserBean;
import conferenceManagement.Entity.DepartmentBean;

public class Service 
{
	
	private UserDAO userDao = new UserDAO();
	
	private UserBean loginedUser = new UserBean();
	
//	登录逻辑调用DAO中的查询方法，根据查询到的结果，返回4个不用的值
//	0：正在审核，登录失败；1：登录成功  2：审核未通过，登录失败  3：用户名或密码错误，登录失败
	public int login(String username,String pwd)
	{
		int flag=3;
		UserBean user = userDao.selectByNamePwd(username, pwd);
		if(user!=null){
			loginedUser = user;
			int status=user.getUserStatus();
			flag = status;
		}
		return flag;
	}

	
//	返回登录成功后的员工对象
	public UserBean getLoginedUser(){
		return loginedUser;
	}
	
//	注册功能，如果账号名存在，注册失败，返回0，否则注册成功，返回1
	public int regist(UserBean user)
	{
		int flag = 0;
		UserBean existUser = userDao.selectByUsername(user.getUsername());
		if(existUser == null)
		{
			flag=1;
			userDao.insert(user);
		}
		return flag;
	}
	
	private DepartmentDAO departmentDao=new DepartmentDAO();

	public List<DepartmentBean> viewAllDepartments(){
		return departmentDao.selectAll();
	}
	
	public void addDepartment(String departmentname)
	{
		departmentDao.insert(departmentname);
	}
	
	public void deleteDepartment(String departmentname)
	{
		departmentDao.delete(departmentname);
	}
	
	private ConferenceRoomDAO  crdao = new ConferenceRoomDAO();
	
	public void deleteConferenceRoom(int conference_room_id)
	{
		crdao.delete(conference_room_id);
	}
}


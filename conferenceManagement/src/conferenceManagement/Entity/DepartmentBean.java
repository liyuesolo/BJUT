package conferenceManagement.Entity;

public class DepartmentBean
{
	private String department_name;
	private int staff_num;
	
	public String getDepartmentname() {
		return department_name;
	}
	public void setDepartmentname(String department_name) {
		this.department_name = department_name;
	}
	public void setStaffNumber(int staff_num)
	{
		this.staff_num = staff_num;
	}
	public int getStaffNumber()
	{
		return this.staff_num;
	}

}

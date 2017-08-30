package conferenceManagement.Entity;

public class UserBean 
{
    private String userid;
	private String user_name;
    private String password;
    private int gender;
    private int age;
    private String phone;
    private String mail;
    private String department_name;
    private int user_status = 0;
    private int privilege = 0;
    private int del_flag = 0;
    public boolean valid = false;
    public boolean isValid()
    {
    	return valid;
    }
    public void setValid(boolean newValid)
    {
    	valid = newValid;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserID(String newUserid)
    {
        userid = newUserid;
    }
    
    public void setUsername(String newUsername)
    {
    	user_name = newUsername;
    }
    public String getUsername()
    {
    	return user_name;
    }
    public void setGender(int newGender) {
    	gender = newGender;
    }
    public int getGender()
    {
    	return gender;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String newPassword) {
        password = newPassword;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int newAge) {
        age = newAge;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String newPhone) {
        phone = newPhone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String newMail) {
        mail = newMail;
    }
    public void setDepartmentname(String newDepartmentname)
    {
    	department_name = newDepartmentname;
    }
    public String getDepartmentname()
    {
    	return department_name;
    }
    public void setPrivilege(int newPrivilege)
    {
    	privilege = newPrivilege;
    }
    public int getPrivilege()
    {
    	return privilege;
    }
    public void setUserStatus(int newStatus)
    {
    	user_status = newStatus;
    }
    public int getUserStatus()
    {
    	return user_status;
    }
    public int getDelflag()
    {
    	return del_flag;
    }
    public void setDelflag(int newDelflag)
    {
    	del_flag = newDelflag;
    }
}

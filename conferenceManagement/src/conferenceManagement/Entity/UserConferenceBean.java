package conferenceManagement.Entity;

public class UserConferenceBean 
{
	public String user_name;
	public int conference_id;
	
	public void setUsername(String user_name)
	{
		this.user_name = user_name;
	}
	public String getUsername()
	{
		return this.user_name;
	}
	
	public int getConferenceid()
	{
		return this.conference_id;
	}
	public void setConferenceid(int conference_id)
	{
		this.conference_id = conference_id;
	}
}

package conferenceManagement.Entity;

import java.sql.Date;

public class NotificationBean 
{
	private Date notification_date;
	private String notification_detail;
	private String notification_source;
	private String notification_destination;
	private int notification_id;
	private int read_flag;
	
	public void setNotificationDate(Date notification_date)
	{
		this.notification_date = notification_date;
	}
	public Date getNotificationDate()
	{
		return this.notification_date;
	}
	public void setNotificationDetail(String notification_detail)
	{
		this.notification_detail = notification_detail;
	}
	public String getNotificationDetail()
	{
		return this.notification_detail;
	}
	public void setNotificationSource(String notification_source)
	{
		this.notification_source = notification_source;
	}
	public String getNotificationSource()
	{
		return this.notification_source;
	}
	public void setNotificationDestination(String notification_destination)
	{
		this.notification_destination = notification_destination;
	}
	public String getNotificationDestination()
	{
		return this.notification_destination;
	}
	public void setNotificationId(int notification_id)
	{
		this.notification_id = notification_id;
	}
	public int getNotificationId()
	{
		return this.notification_id;
	}
	public int getReadflag()
	{
		return this.read_flag;
	}
	public void setReadflag(int read_flag)
	{
		this.read_flag = read_flag;
	}

}

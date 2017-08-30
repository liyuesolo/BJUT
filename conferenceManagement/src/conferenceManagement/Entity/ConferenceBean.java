package conferenceManagement.Entity;
import java.sql.Time;
import java.sql.Date;
public class ConferenceBean 
{
    private int conference_id;
    private String conference_name;
    private int conference_room_id;
    private String conference_raiser_name;
	private String conference_info;
	private Time start_time;
	private Time end_time;
	private Date date;
	private int expected_num;
	private int conference_status=0;
	private String conference_cancel_reason;

	public void setConferenceStarttime(Time start_time)
	{
		this.start_time = start_time;
	}
	public Time getConferenceStarttime()
	{
		return this.start_time;
	}
	public void setConferenceEndtime(Time end_time)
	{
		this.end_time = end_time;
	}
	public Time getConferenceEndtime()
	{
		return this.end_time;
	}
	public void setConferenceDate(Date date)
	{
		this.date = date;
	}
	public Date getConferenceDate()
	{
		return this.date;
	}
    public int getConferenceid() {
        return this.conference_id;
    }

    public void setConferenceid(int conference_id) {
        this.conference_id = conference_id;
    }

    public String getConferencename() {
        return this.conference_name;
    }

    public void setConferencename(String conference_name) {
        this.conference_name = conference_name;
    }
	
	public int getConferenceroomid() {
        return this.conference_room_id;
    }

    public void setConferenceroomid(int conference_room_id) {
        this.conference_room_id = conference_room_id;
    }
	
	public String getConferenceRaisername() {
        return this.conference_raiser_name;
    }

    public void setConferenceRaisername(String conference_raiser_name) {
        this.conference_raiser_name = conference_raiser_name;
    }

	public String getConferenceinfo() {
        return this.conference_info;
    }

    public void setConferenceinfo(String conference_info) {
        this.conference_info = conference_info;
    }
	
    public int getExpectednum() {
        return this.expected_num;
    }

    public void setExpectednum(int expected_num) {
        this.expected_num = expected_num;
    }
	public int getConferencestatus() {
        return this.conference_status;
    }

    public void setConferencestatus(int conference_status) {
        this.conference_status = conference_status;
    }
	
	public String getConferencecancelreason()
	{
        return this.conference_cancel_reason;
    }

    public void setConferencecancelreason(String conference_cancel_reason) {
        this.conference_cancel_reason = conference_cancel_reason;
    }
    
}